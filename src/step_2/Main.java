package step_2;

import java.util.Scanner;

/**
 * Set up the traffic light
 * ------------------------
 * Description
 * What is a system without parameters? The traffic light should work the way users want it. Everything is simple —
 * provide input and get the corresponding program's output.
 * ------------------------
 * Objectives
 * In this stage, after the welcoming line, ask the users to input the desired number of roads and input the interval
 * at which the roads should open/close. After each request, read the value that a user provides.
 * Next, implement a looped selection menu. The loop (as well as the program execution) ends when a user selects 0 as
 * the desired option. Any other option (1, 2, 3) prints an informational text on the action performed (add, delete,
 * system) for each option.
 */

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        System.out.println("Welcome to the traffic management system!");
        System.out.println("Input the number of roads:");
        int roads = scanner.nextInt();
        System.out.println("Input the interval:");
        int interval = scanner.nextInt();
        while (true) {
            System.out.println(
                """
                Menu:
                1. Add road
                2. Delete road
                3. Open system
                0. Quit
                """
            );
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Road added");
                    break;
                case 2:
                    System.out.println("Road deleted");
                    break;
                case 3:
                    System.out.println("System opened");
                    break;
                case 0:
                    System.out.println("Buy");
                    return;
            }
        }
    }
}
