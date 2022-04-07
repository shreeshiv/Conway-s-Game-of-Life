
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

class Pair {
    private int xCoordinate;
    private int yCoordinate;
    private int hashCode;

    Pair(int x, int y) {
        xCoordinate = x;
        yCoordinate = y;
        this.hashCode = Objects.hash(x, y);
    }

    public int getXCoordinate() {
        return this.xCoordinate;
    }

    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getYCoordinate() {
        return this.yCoordinate;
    }

    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pair other = (Pair) obj;
        if (xCoordinate != other.getXCoordinate() || yCoordinate != other.getYCoordinate())
            return false;

        return true;
    }
}

public class Conway {

    private int totalLiveCells;
    private List<Integer> xCoordinates;
    private List<Integer> yCoordinates;
    private int rowSize;
    private int colSize;

    private HashMap<Pair, Integer> indexing;

    Conway(int size) {
        rowSize = size;
        colSize = size;
    }

    Conway(int size, int totalLiveCells, List<Integer> xCoords, List<Integer> yCoords) {
        rowSize = size;
        colSize = size;
        this.totalLiveCells = totalLiveCells;
        xCoordinates = xCoords;
        yCoordinates = yCoords;
        indexing = new HashMap<>();
        for (int index = 0; index < totalLiveCells; index++) {
            indexing.put(new Pair(xCoords.get(index), yCoords.get(index)), 1);
        }
    }

    Conway(int rowSize, int colSize, int totalLiveCells, List<Integer> xCoords, List<Integer> yCoords) {
        this.rowSize = rowSize;
        this.colSize = colSize;
        this.totalLiveCells = totalLiveCells;
        xCoordinates = xCoords;
        yCoordinates = yCoords;
        indexing = new HashMap<>();
        for (int index = 0; index < totalLiveCells; index++) {
            indexing.put(new Pair(xCoords.get(index), yCoords.get(index)), 1);
        }
    }

    public void printGrid() {
        System.out.println(rowSize + " " + colSize);
        System.out.println("" + indexing.size());

        for (int itrRow = 0; itrRow < rowSize; itrRow++) {
            for (int itrCol = 0; itrCol < colSize; itrCol++) {
                if (indexing.containsKey(new Pair(itrRow, itrCol))) {
                    System.out.print("\u2593");
                } else
                    System.out.print(" ");
            }
            System.out.println("");
        }
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void generateNextState() {
        List<Integer> newXCords = new ArrayList<>();
        List<Integer> newYCords = new ArrayList<>();
        HashMap<Pair, Integer> countNeighbors = new HashMap<>();

        for (int itr = 0; itr < totalLiveCells; itr++) {
            int xCoordinate = xCoordinates.get(itr);
            int yCoordinate = yCoordinates.get(itr);

            if (xCoordinate - 1 >= 0) {
                Pair p = new Pair(xCoordinate - 1, yCoordinate);
                if (countNeighbors.containsKey(p)) {
                    countNeighbors.put(p, countNeighbors.get(p) + 1);
                } else {
                    countNeighbors.put(p, 1);
                }
            }
            if (xCoordinate + 1 < rowSize) {
                Pair p = new Pair(xCoordinate + 1, yCoordinate);
                if (countNeighbors.containsKey(p)) {
                    countNeighbors.put(p, countNeighbors.get(p) + 1);
                } else {
                    countNeighbors.put(p, 1);
                }
            }
            if (xCoordinate - 1 >= 0 && yCoordinate - 1 >= 0) {
                Pair p = new Pair(xCoordinate - 1, yCoordinate - 1);
                if (countNeighbors.containsKey(p)) {
                    countNeighbors.put(p, countNeighbors.get(p) + 1);
                } else {
                    countNeighbors.put(p, 1);
                }
            }
            if (xCoordinate + 1 < rowSize && yCoordinate - 1 >= 0) {
                Pair p = new Pair(xCoordinate + 1, yCoordinate - 1);
                if (countNeighbors.containsKey(p)) {
                    countNeighbors.put(p, countNeighbors.get(p) + 1);
                } else {
                    countNeighbors.put(p, 1);
                }
            }

            if (xCoordinate - 1 >= 0 && yCoordinate + 1 < colSize) {
                Pair p = new Pair(xCoordinate - 1, yCoordinate + 1);
                if (countNeighbors.containsKey(p)) {
                    countNeighbors.put(p, countNeighbors.get(p) + 1);
                } else {
                    countNeighbors.put(p, 1);
                }
            }
            if (xCoordinate + 1 < rowSize && yCoordinate + 1 < colSize) {
                Pair p = new Pair(xCoordinate + 1, yCoordinate + 1);
                if (countNeighbors.containsKey(p)) {
                    countNeighbors.put(p, countNeighbors.get(p) + 1);
                } else {
                    countNeighbors.put(p, 1);
                }
            }
            if (yCoordinate - 1 >= 0) {
                Pair p = new Pair(xCoordinate, yCoordinate - 1);
                if (countNeighbors.containsKey(p)) {
                    countNeighbors.put(p, countNeighbors.get(p) + 1);
                } else {
                    countNeighbors.put(p, 1);
                }
            }
            if (yCoordinate + 1 < colSize) {
                Pair p = new Pair(xCoordinate, yCoordinate + 1);
                if (countNeighbors.containsKey(p)) {
                    countNeighbors.put(p, countNeighbors.get(p) + 1);
                } else {
                    countNeighbors.put(p, 1);
                }
            }

        }

        // Checking the following three conditions:
        /**
         * Any live cell with fewer than two live neighbours dies, as if by
         * underpopulation.
         * Any live cell with two or three live neighbours lives on to the next
         * generation.
         * Any live cell with more than three live neighbours dies, as if by
         * overpopulation.
         */

        for (int itr = 0; itr < totalLiveCells; itr++) {
            Pair curPair = new Pair(xCoordinates.get(itr), yCoordinates.get(itr));
            if (countNeighbors.containsKey(curPair)) {
                switch (countNeighbors.get(curPair)) {
                    case 2:
                        newXCords.add(xCoordinates.get(itr));
                        newYCords.add(yCoordinates.get(itr));
                        break;

                    case 3:
                        newXCords.add(xCoordinates.get(itr));
                        newYCords.add(yCoordinates.get(itr));
                        break;

                }
            }
        }

        /**
         * Handling following case
         * Any dead cell with exactly three live neighbours becomes a live cell, as if
         * by reproduction.
         */
        for (Map.Entry mapElement : countNeighbors.entrySet()) {
            int count = (int) mapElement.getValue();
            Pair curP = (Pair) mapElement.getKey();
            if (count == 3 && indexing.containsKey(curP) == false) {
                newXCords.add(curP.getXCoordinate());
                newYCords.add(curP.getYCoordinate());
            }
        }
        totalLiveCells = newXCords.size();
        xCoordinates = newXCords;
        yCoordinates = newYCords;

        indexing = new HashMap<Pair, Integer>();
        for (int index = 0; index < totalLiveCells; index++) {
            indexing.put(new Pair(xCoordinates.get(index), yCoordinates.get(index)), 1);
        }
    }

    public void loadPreviousState() {

    }

    public void loadState(int totalLiveCells, List<Integer> xCoords, List<Integer> yCoords) {
        this.totalLiveCells = totalLiveCells;
        xCoordinates = xCoords;
        yCoordinates = yCoords;
        indexing = new HashMap<>();
        for (int index = 0; index < totalLiveCells; index++) {
            indexing.put(new Pair(xCoords.get(index), yCoords.get(index)), 1);
        }

    }
}
