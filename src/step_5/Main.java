package step_5;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Over and over again
 * -------------------
 * Description
 * Expand the created stopwatch into a road management system. Implement the circular queue that contains roads and add
 * functionality to the remaining options in the menu.
 * -------------------
 * Objectives
 * In this stage, we will add functionality to the Add road and the Delete road options. Expand the QueueThread system
 * by implementing a circular queue, where the maximum number of roads is the value provided in users' settings.
 * When the user selects 1 as an option, print a line containing the input substring, followed by an input for a new
 * element.
 * If the queue is full, the program should inform the user about that with the queue is full substring
 * Otherwise, add this element to the end of a queue and inform users with the add substring and the element's name
 * When the user selects 2 as an option:
 * If the queue is empty, the program should inform users with the queue is empty substring
 * Otherwise, delete the element from the start of the queue and inform users with the delete substring and the
 * element's name.
 * Also, expand the output of the system information. If the queue is not empty, print all the elements' names line by
 * line in the queue from front to rear, just like in the example.
 */

public class Main {
    static final Scanner scanner = new Scanner(System.in);

    static int roads;
    static int interval;

    // Thread Counter State Variables:
    // - inSystemState: Indicates whether the system is currently in an active/internal state.
    // - programRunning: Flag to control the main program loop; false stops the execution.
    // - timeSinceStartup: Tracks the elapsed time (in seconds/milliseconds) since the program started.
    static volatile boolean inSystemState = false;
    static volatile boolean programRunning = true;
    static int timeSinceStartup = 0;

    // Circular Queue Implementation using an Array:
    // - roadNames: The array that holds road name entries.
    // - roadNamesFront: Points to the front of the queue (oldest element).
    // - roadNamesRear: Points to the rear of the queue (next insertion point).
    static String[] roadNames;
    static int roadNamesRear = 0;
    static int roadNamesFront = 0;


    public static void main(String[] args) {
        System.out.println("Welcome to the traffic management system!");

        Main.roads = Main.definePositiveInteger("Input the number of roads");
        Main.roadNames = new String[Main.roads];

        Main.interval = Main.definePositiveInteger("Input the interval");

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
                            if (Main.roadNames[Main.roadNamesRear] == null) {
                                System.out.print("Input road name: ");
                                Main.roadNames[Main.roadNamesRear] = Main.scanner.nextLine();
                                System.out.println(Main.roadNames[Main.roadNamesRear] + " added. Press \"Enter\" to open menu.");

                                // Update rear index for circular queue:
                                // Calculates the next position using modulo to wrap around the array.
                                // Formula: nextRear = (currentRear + 1) % sizeOfArray
                                Main.roadNamesRear = (Main.roadNamesRear + 1) % Main.roads;
                            } else {
                                System.out.println("Queue is full. Press \"Enter\" to open menu.");
                            }
                            Main.scanner.nextLine(); // Wait for user to press Enter
                        }
                        case 2 -> {
                            if (Main.roadNames[roadNamesFront] != null) {
                                System.out.println(Main.roadNames[roadNamesFront] + " deleted. Press \"Enter\" to open menu.");
                                Main.roadNames[roadNamesFront] = null;

                                // Update front index for circular queue:
                                // Calculates the next position using modulo to wrap around the array.
                                // Formula: nextFront = (currentFront + 1) % sizeOfArray
                                Main.roadNamesFront = (Main.roadNamesFront + 1) % Main.roads;
                            } else {
                                System.out.println("Queue is empty. Press \"Enter\" to open menu.");
                            }
                            Main.scanner.nextLine(); // Wait for user to press Enter
                        }
                        case 3 -> {
                            inSystemState = true; // Set the system state to active, signaling other threads to print status

                            // Enter system state: wait for user input to exit this state
                            while (true) {
                                // Wait for the user to press Enter (empty line)
                                if (scanner.nextLine().isEmpty()) {
                                    inSystemState = false; // Signal other threads (e.g., queueThread) to stop printing
                                    break; // Exit system state loop
                                }
                            }
                        }
                        case 0 -> {
                            System.out.println("Buy!");
                            programRunning = false; // Signal all running threads to stop
                            queueThread.join(); // Wait for the queueThread to finish execution before exiting
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
                // Sleep for 1 second to regulate the printing interval
                TimeUnit.SECONDS.sleep(1);

                // Increment the time since the program started
                Main.timeSinceStartup++;

                // If the system is in an active state, print relevant information
                if (Main.inSystemState) {
                    System.out.println("! " + Main.timeSinceStartup + "s. have passed since system startup !");
                    System.out.println("! Number of roads: " + Main.roads + " !");
                    System.out.println("! Interval: " + Main.interval + " !");

                    // Print all the road names that have been added to the queue
                    System.out.println();
                    for (int i = 0; i < Main.roads; i++) {
                        if (Main.roadNames[i] != null) {
                            System.out.println(Main.roadNames[i]);
                        }
                    }
                    System.out.println();
                    System.out.println("! Press \"Enter\" to open menu !");
                }
            } catch (InterruptedException ignored) {
                // If the thread is interrupted, the exception is caught but ignored
                // This allows the thread to continue its normal operation
            }
        }
    }
}
