import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String args[]) {
        System.out.println("welcome to Conway's game of life");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the game");
        int size = sc.nextInt();
        int command = 0;
        Conway conway = new Conway(size);
        System.out.println("Enter total number of live cells");
        int totalLiveCells = sc.nextInt();
        List<Integer> xCoords = new ArrayList<>();
        List<Integer> yCoords = new ArrayList<>();

        System.out.println("Enter live cells's x and y co-ordinates");
        for (int itr = 0; itr < totalLiveCells; itr++) {
            xCoords.add(sc.nextInt());
            yCoords.add(sc.nextInt());
        }
        conway.loadState(totalLiveCells, xCoords, yCoords);
        conway.printGrid();
        System.out.println(
                "Enter following command \n0 For generating next 100 productions with 100 ms sleep\n 1 For next state \n 2 For previous state \n 3 For exit ");
        boolean isExit = false;
        while (!isExit) {
            command = sc.nextInt();
            switch (command) {
                case 0:
                    for (int itrProduction = 0; itrProduction < 100; itrProduction++) {
                        conway.generateNextState();
                        clearScreen();
                        conway.printGrid();
                    }
                    break;

                case 1:
                    conway.generateNextState();
                    conway.printGrid();
                    break;
                case 2:
                    conway.loadPreviousState();
                    conway.printGrid();
                    break;
                case 3:
                    isExit = true;
                    break;
                default:
                    System.out.println("Enter valid input");

            }

        }

    }
}