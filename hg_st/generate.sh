hg annotate * | cut -d':' -f1 | sort | uniq -c  | sed -e 's/^ *//g;s/ *$//g' | tr -s ' ' > ../lindy-stat/$(hg log -l 1 | grep parent  | head -n1| cut -d':' -f2| tr -d ' ').all.csv
