
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

class CurrentState {
    private List<Integer> xCoordinates;
    private List<Integer> yCoordinates;
    private int rowSize;
    private int colSize;

    CurrentState(int rowSize, int colSize, List<Integer> xCoordinates, List<Integer> yCoordinates) {
        this.xCoordinates = xCoordinates;
        this.yCoordinates = yCoordinates;
        this.rowSize = rowSize;
        this.colSize = colSize;
    }

    CurrentState(List<Integer> xCoordinates, List<Integer> yCoordinates) {
        this.xCoordinates = xCoordinates;
        this.yCoordinates = yCoordinates;
    }

    public List<Integer> getXCoordinates() {
        return this.xCoordinates;
    }

    public void setXCoordinates(List<Integer> xCoordinates) {
        this.xCoordinates = xCoordinates;
    }

    public List<Integer> getYCoordinates() {
        return this.yCoordinates;
    }

    public void setYCoordinates(List<Integer> yCoordinates) {
        this.yCoordinates = yCoordinates;
    }

    public int getRowSize() {
        return this.rowSize;
    }

    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }

    public int getColSize() {
        return this.colSize;
    }

    public void setColSize(int colSize) {
        this.colSize = colSize;
    }

}

public class Conway {

    private int totalLiveCells;
    private List<Integer> xCoordinates;
    private List<Integer> yCoordinates;
    private int rowSize;
    private int colSize;
    private List<CurrentState> history;

    private HashMap<Pair, Integer> indexing;

    Conway(int size) {
        rowSize = size;
        colSize = size;
        history = new ArrayList<CurrentState>();
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
        history = new ArrayList<CurrentState>();

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
        history = new ArrayList<CurrentState>();

    }

    public void printGrid() {

        for (int itr = 0; itr <= 2 * colSize; itr++) {
            System.out.print("-");
        }

        for (int itrRow = 0; itrRow < rowSize; itrRow++) {
            System.out.print("|");
            for (int itrCol = 0; itrCol < colSize; itrCol++) {
                if (indexing.containsKey(new Pair(itrRow, itrCol))) {
                    System.out.print("\u2593 ");
                } else
                    System.out.print("  ");
            }
            System.out.print("|\n");
        }
        for (int itr = 0; itr <= 2 * colSize + 1; itr++) {
            System.out.print("-");
        }
        System.out.println("");
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
        history.add(new CurrentState(xCoordinates, yCoordinates));
        totalLiveCells = newXCords.size();
        xCoordinates = newXCords;
        yCoordinates = newYCords;

        indexing = new HashMap<Pair, Integer>();
        for (int index = 0; index < totalLiveCells; index++) {
            indexing.put(new Pair(xCoordinates.get(index), yCoordinates.get(index)), 1);
        }
    }

    public void loadPreviousState() {
        if (history.size() <= 0) {
            System.out.println("Not enough generations");
            return;
        }
        this.xCoordinates = history.get(history.size() - 1).getXCoordinates();
        this.yCoordinates = history.get(history.size() - 1).getYCoordinates();
        history.remove(history.size() - 1);
        this.totalLiveCells = xCoordinates.size();
        indexing = new HashMap<Pair, Integer>();
        for (int index = 0; index < totalLiveCells; index++) {
            indexing.put(new Pair(xCoordinates.get(index), yCoordinates.get(index)), 1);
        }
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
