
hg -l 1 | grep parent  | head -n1| cut -d':' -f2| tr -d ' '
