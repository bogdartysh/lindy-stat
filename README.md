# lindy-stat
liny effect maven repo calculator

The project is based on [J. D. Cook blog](http://www.johndcook.com/blog/2012/12/17/the-lindy-effect/), [wikipage](https://en.wikipedia.org/wiki/Lindy_Effect), [Original blog] (http://bogdanartyushenko.blogspot.com/2015/03/lindy-effect-maven-analyzer-tool.html).
 

License
======
The software is licensed unde GPLv3 terms with the following exception:
using of the project in any form and by all means is forbiden to:

1. all governemnts and its agencies which support terrorism
2. all governemnts and its agencies which annexed territory of another state after 2000 year without UN or NATO permission
3. all agencies of Russian Federation.

HOW-TO
======

### Clone project to some folder:

    $cd ~
    $git clone https://github.com/bogdartysh/lindy-stat.git

### build project

    $mvn assembly:assembly

### generate dependency tree file:

    $cd ~/you_project
    $mvn dependency:tree > dep.tree
    $cp dep.tree ~/lindy-stat

### run jar file to generate data:

    $cd ~/lindy-stat/target
    $java -jar lindy-stat-1.0-SNAPSHOT-jar-with-dependencies.jar tree dep.tree dep.tree.csv

it will print some logs and finally generate the csv file. Enjoy
