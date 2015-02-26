# lindy-stat
liny effect maven repo calculator

The project is based on http://www.johndcook.com/blog/2012/12/17/the-lindy-effect/, https://en.wikipedia.org/wiki/Lindy_Effect

HOW-TO
======

1. Clone project to some folder:

$cd ~
$git clone https://github.com/bogdartysh/lindy-stat.git

2. build project

$mvn assembly:assembly

3. generate dependency tree file:

$cd ~/you_project
$mvn dependency:tree > dep.tree

4. copy dep.tree to folder_with_project/taget

$cp dep.tree ~/lindy-stat

5. cd lindy-stat target folder

$cd ~/lindy-stat

6. run jar file to generate data:

$java -jar lindy-stat-1.0-SNAPSHOT-jar-with-dependencies.jar tree dep.tree dep.tree.csv

it will print some logs and finally generate the csv file
