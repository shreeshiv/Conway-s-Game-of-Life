import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Conway {
    private int size;
    private boolean grid[][];
    private List<boolean[][]> history;

    Conway(int size) {
        this.size = size;
        grid = new boolean[size][size];
        history = new ArrayList<boolean[][]>();
    }

    Conway() {
        size = 100;
        grid = new boolean[100][100];
        history = new ArrayList<boolean[][]>();
    }

    void print() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j]) {
                    System.out.print("x");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("\n");
        }
    }

    void generateNextState() {
        boolean nextGrid[][] = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int count = 0;
                nextGrid[i][j] = grid[i][j];
                if (i - 1 >= 0 && grid[i - 1][j])
                    count++;
                if (j - 1 >= 0 && grid[i][j - 1])
                    count++;
                if (j + 1 < size && grid[i][j + 1])
                    count++;
                if (i - 1 >= 0 && j + 1 < size && grid[i - 1][j + 1])
                    count++;
                if (i - 1 >= 0 && j - 1 >= 0 && grid[i - 1][j - 1])
                    count++;
                if (i + 1 < size && grid[i + 1][j])
                    count++;
                if (i + 1 < size && j + 1 < size && grid[i + 1][j + 1])
                    count++;
                if (i + 1 < size && j - 1 >= 0 && grid[i + 1][j - 1])
                    count++;
                if (count < 2 && grid[i][j]) {
                    nextGrid[i][j] = false;
                } else if (count > 3 && grid[i][j]) {
                    nextGrid[i][j] = false;
                } else if (grid[i][j] == false && count == 3)
                    nextGrid[i][j] = true;
            }
        }
        history.add(grid);

        grid = nextGrid;
    }

    void setState(boolean[][] newGrid, int size) {
        if (this.size != size) {
            System.out.print("Input size mis-match");
            return;
        }
        grid = newGrid;
    }

    void setState(int total_live_cells, int[] x, int[] y) {
        for (int i = 0; i < total_live_cells; i++)
            grid[x[i]][y[i]] = true;
    }

    void loadPreviousState() {
        if (history.size() <= 0) {
            System.out.println("There are not enough moves happened");
            return;
        }
        grid = history.get(history.size() - 1);
        history.remove(history.size() - 1);

    }
}