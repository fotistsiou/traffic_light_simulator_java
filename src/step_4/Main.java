package step_4;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Like a clockwork
 * ----------------
 * Description
 * Creating a whole system at once is challenging, so we will start with a simple stopwatch in the new thread.
 * Let's assume that our program has three possible states:
 * Not Started — the state until the initial settings have been provided;
 * Menu — the state we worked with earlier. It shows possible options and processes the user's input;
 * System — the state that shows the user information about our traffic light, such as time from startup and initial
 * settings for now.
 * When the user provided input for initial settings (both the number of roads and the interval), create a new thread to
 * implement the System state. Name it as QueueThread by calling its setName() method with the corresponding string
 * argument. The actions this newly-created thread should perform for now each second are:
 * Increasing the variable that corresponds to the amount of time since the "system startup" each second (1000
 * milliseconds);
 * (if in System state) Printing the system information.
 * Let's add new functionality to the Open system menu option. By choosing 3 option in Menu, the program switches to the
 * System state, and the main thread waits for input from the user. To return to the Menu state, the user should press
 * Enter, in other words, an empty string should be provided as input.
 * Example of system information output:
 * ! 3s. have passed since system startup !
 * ! Number of roads: 1 !
 * ! Interval: 1 !
 * ! Press "Enter" to open menu !
 * From this stage and beyond, the testing process can take some time.
 * ----------------
 * Objectives
 * Implement the System state as described above and set its thread name as QueueThread. While your program is in the
 * System state, the output should contain the following information, updated (printing out) each second:
 * The line that shows the time since the program's start (contains only one integer — the number of seconds);
 * The line that indicates the number of roads provided by the user (includes the number substring and only one integer
 * — the same value that was provided in the settings);
 * The line that shows the interval provided by the user (contains the interval substring and only one integer — the
 * same value that was provided in the settings);
 * The line that asks the user to press Enter to open the menu (should contain the Enter substring)
 * When the user provided an empty input, the program should switch back to the Menu state and show the previously
 * implemented menu.
 * The created QueueThread thread should be terminated when the program is finished.
 */

public class Main {
    static final Scanner scanner = new Scanner(System.in);

    // User Inputs Variables
    static int numberOfRoads;
    static int interval;

    // Thread Counter State Variables
    static volatile boolean inSystemState = false;
    static volatile boolean programRunning = true;
    static int timeSinceStartup = 0;

    // Circular Queue Implementation Variables
    static String[] roadNames;
    static int[] roadIntervals;

    public static void main(String[] args) {
        System.out.println("Welcome to the traffic management system!");

        Main.numberOfRoads = Main.definePositiveInteger("Input the number of roads");
        Main.roadNames = new String[Main.numberOfRoads];

        Main.interval = Main.definePositiveInteger("Input the interval");
        Main.roadIntervals = new int[Main.numberOfRoads];

        QueueThread queueThread = new QueueThread();
        queueThread.setName("QueueThread");
        queueThread.start();

        while (true) {
            System.out.print(
                    """
                    Menu:
                    1. Add road
                    2. Delete road
                    3. Open system
                    0. Quit
                    """
            );

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Clear the buffer (consume leftover newline)

                if (choice >= 0 && choice <= 3) {
                    switch (choice) {
                        case 1 -> {
                            System.out.println("Road added. Press \"Enter\" to open menu.");
                            scanner.nextLine(); // Wait for user to press Enter
                        }
                        case 2 -> {
                            System.out.println("Road deleted. Press \"Enter\" to open menu.");
                            scanner.nextLine(); // Wait for user to press Enter
                        }
                        case 3 -> {
                            inSystemState = true; // Signal the queueThread for opening system state

                            while (true) {
                                if (scanner.nextLine().isEmpty()) { // Wait for user to press Enter
                                    inSystemState = false; // Signal the queueThread for closing system state
                                    break; // Exit from system state
                                }
                            }
                        }
                        case 0 -> {
                            System.out.println("Buy!");
                            programRunning = false; // Signal the queueThread to stop
                            queueThread.join(); // Wait for the queueThread to stop
                            return; // Terminate the program
                        }
                    }
                } else {
                    System.out.println("Incorrect option. Press \"Enter\" to open menu and try again.");
                    scanner.nextLine(); // Wait for user to press Enter
                }
            } catch (Exception e) {
                System.out.println("Incorrect option. Press \"Enter\" to open menu and try again.");
                scanner.nextLine(); // Clear the invalid input
                scanner.nextLine(); // Wait for user to press Enter
            }
        }
    }

    static int definePositiveInteger(String promptType) {
        System.out.print(promptType + ": ");
        int promptTypeNumber;
        while (true) {
            try {
                promptTypeNumber = Main.scanner.nextInt();
                if (promptTypeNumber > 0) {
                    break; // Valid positive integer entered
                }
                System.out.print("Incorrect input. Try again: ");
            } catch (Exception e) {
                System.out.print("Incorrect input. Try again: ");
            } finally {
                scanner.nextLine(); // Clear the buffer (consume leftover newline)
            }
        }
        return promptTypeNumber;
    }
}

class QueueThread extends Thread {
    @Override
    public void run() {
        while (Main.programRunning) { // Keep the thread running while the program is active
            try {
                // If the system is in an active state, print relevant information
                if (Main.inSystemState) {
                    // Print Standard Message
                    System.out.println("! " + Main.timeSinceStartup + "s. have passed since system startup !");
                    System.out.println("! Number of roads: " + Main.numberOfRoads + " !");
                    System.out.println("! Interval: " + Main.interval + " !");
                    System.out.println("! Press \"Enter\" to open menu !");
                }

                // Sleep for 1 second to regulate the printing interval
                TimeUnit.SECONDS.sleep(1);

                // Increment the time since the program started
                Main.timeSinceStartup++;
            } catch (InterruptedException ignored) {}
        }
    }
}
