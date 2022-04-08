# Conway-s-Game-of-Life

Implementation of Conway's game of Life

Author: Shreeshiv

Sample output of Glider:

![Glider in Game of Life](/assets/conway_game.gif)

Steps to run:

1. Naive approach:

- Steps to run. Go to the Naive-approach directory. <br>`cd Naive-approach`

- `javac Main.java`

- `java Main`

- It contains two java files, One Conway.java and other is Main.java Conway is main file and Main is driving file that will create an object of conway and test various functinos.

- Time and Space complexity to generate next generations: Since in this method we are storing state of every cells, it consume NxM for each generations. (Where N is number of rows in grid and M is number of columns in grid).

- Since we know that most of cells are dead we can improve space by storing co-ordinates of only live cells. THis will lead to reduce space complexity from NxM to 2xNumber of live cells.

2. Space Optimizes Approach:

- Go to Neighborcount directory <br>

`cd NeighborCount`

- `javac Main.java`

- `java Main`
