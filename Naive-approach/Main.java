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
        int total_live = sc.nextInt();
        int x[] = new int[total_live];
        int y[] = new int[total_live];
        System.out.println("Enter live cells's x and y co-ordinates");
        for (int i = 0; i < total_live; i++) {
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
        }
        conway.setState(total_live, x, y);
        System.out.println("Enter following command \n 1 For next state \n 2 For exit \n 3 For previous state");
        while (true) {
            command = sc.nextInt();
            if (command == 2)
                break;
            else if (command == 1) {
                conway.generateNextState();
                conway.print();
            } else if (command == 3) {
                conway.loadPreviousState();
                conway.print();
            } else {
                System.out.println("Enter valid command");
            }

        }
    }

}
