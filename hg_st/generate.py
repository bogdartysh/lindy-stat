
import os
for l in range(10000, 25000, 1000):
    os.system('hg update ' + str(l))
    os.system("hg annotate * | cut -d':' -f1 | sort | uniq -c  | sed -e 's/^ *//g;s/ *$//g' | tr -s ' ' > ../lindy-stat/$(hg identify --num | tr -s ' ').all.csv ")
stats = {}
for l in range(10000, 25000, 1000):
    stats[l] = {}
    with open('../lindy-stat/' + str(l) + '.all.csv') as ins:
        for line in ins:
            try:
                nums = [int(n) for n in line.split()]
                qty = nums[0]
                key = nums[1]//1000
                if key in stats[l]:
                    stats[l][key] = stats[l][key] + qty
                else:
                    stats[l][key] = qty
            except ValueError:
              pass
print 'when,'
for key, val in stats
    print str(key) + ',' + ', '.join(str(x) for x in val.values())





