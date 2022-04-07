
public class Pair {
    private int xCoordinate;
    private int yCoordinate;

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

    Pair(int x, int y) {
        xCoordinate = x;
        yCoordinate = y;
    }
}
