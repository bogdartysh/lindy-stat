
import os, math

step = 10000
lower_bound = step
upper_bound = 230000
project_name = "mozilla_central"

def generate_stat() :
    os.system('mkdir -p ~/tmp/lindy_stat/' + project_name)
    for l in range(lower_bound, upper_bound, step):
        os.system('hg update --clean ' + str(l))
        os.system("hg annotate * | cut -d':' -f1 | sort | uniq -c  | sed -e 's/^ *//g;s/ *$//g' | tr -s ' ' > ~/tmp/lindy_stat/" + project_name + "/" + str(l) +".csv ")

def get_stats_from_files():
    stats = {}
    for l in range(10000, upper_bound, step):
        stats[l] = {}
        with open('/home/bartyushenko/tmp/lindy_stat/' + project_name + '/' + str(l) + '.csv') as ins:
            for line in ins:
                try:
                    nums = [int(n) for n in line.split()]
                    qty = nums[0]
                    key = nums[1]//step
                    if key in stats[l]:
                        stats[l][key] = stats[l][key] + qty
                    else:
                        stats[l][key] = qty
                except ValueError:
                    pass
    return stats
#print 'when,'
#for key, val in stats
#    print str(key) + ',' + ', '.join(str(x) for x in val.values())

def get_acc_stat(stats, age) :
    qty = 0;    surv = 0.0    
    for when in (stat for stat in stats if stat +  step in stats):
        for cf in (s for s in stats[when] if when > (s + 1) *  step + age):
            if when + step in stats and when in stats and cf in stats[when+step] and cf in stats[when]:
                add_surv = 1.0*stats[when +  step ][cf] / stats[when][cf]
                add_qty = stats[when][cf]
                surv = (add_surv * add_qty + surv * qty) / (add_qty + qty)
                qty += add_qty
    return round(surv*100,2)

def get_current_stat(stats, age) :
    qty = 0;    surv = 0.0    
    for when in (stat for stat in stats if stat +  step in stats):
        for cf in (s for s in stats[when] if when == (s + 1) *  step + age and s in stats[when+step]):
            add_surv = 1.0*stats[when +  step ][cf] / stats[when][cf]
            add_qty = stats[when][cf]
            surv = (add_surv * add_qty + surv * qty) / (add_qty + qty)
            qty += add_qty
    return round(surv*100,2)

def get_surv() :
    stats = get_stats_from_files()
    ages = range(lower_bound, upper_bound - 3 * step, step)
    return {'ages':ages, 'accumulated_survivabilities':[get_acc_stat(stats, age) for age in ages], 'current_survivability': [get_current_stat(stats, age) for age in ages]}
    


