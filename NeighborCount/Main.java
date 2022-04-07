import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
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
        System.out.println("Enter following command \n 1 For next state \n 2 For exit \n 3 For previous state");
        while (true) {
            command = sc.nextInt();
            if (command == 2)
                break;
            else if (command == 1) {
                conway.generateNextState();
                conway.printGrid();
            } else if (command == 3) {
                conway.loadPreviousState();
                conway.printGrid();
            } else {
                System.out.println("Enter valid command");
            }

        }

    }
}