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
    static volatile boolean inSystemState = false;
    static volatile boolean programRunning = true;
    static int timeSinceStartup = 0;
    static int roads;
    static String[] roadNames;
    static int roadCounterLast = 0;
    static int roadCounterFirst = 0;
    static int interval;

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
                scanner.nextLine(); // clean the input
                if (choice >= 0 && choice <= 3) {
                    switch (choice) {
                        case 1 -> {
                            if (Main.roadCounterLast < Main.roadNames.length) {
                                System.out.print("Input road name: ");
                                Main.roadNames[Main.roadCounterLast] = Main.scanner.nextLine();
                                System.out.println(Main.roadNames[Main.roadCounterLast] + " added. Press \"Enter\" to open menu.");
                                Main.roadCounterLast++;
                            } else {
                                System.out.println("Queue is full. Press \"Enter\" to open menu.");
                            }
                            Main.scanner.nextLine(); // wait for press enter
                        }
                        case 2 -> {
                            if (Main.roadCounterLast > 0) {
                                System.out.println(Main.roadNames[roadCounterFirst] + " deleted. Press \"Enter\" to open menu.");
                                Main.roadNames[roadCounterFirst] = null;
                                Main.roadCounterFirst++;
                            } else {
                                System.out.println("Queue is empty. Press \"Enter\" to open menu.");
                            }
                            Main.scanner.nextLine(); // wait for press enter
                        }
                        case 3 -> {
                            inSystemState = true;
                            while (true) {
                                if (scanner.nextLine().isEmpty()) { // wait for press enter to exit from state
                                    inSystemState = false; // stop print message from queueThread
                                    break; // exit from state
                                }
                            }
                        }
                        case 0 -> {
                            System.out.println("Buy!");
                            programRunning = false;
                            queueThread.join(); // waits until the thread finishes
                            return; // terminate the program
                        }
                    }
                } else {
                    System.out.println("Incorrect option. Press \"Enter\" to open menu and try again.");
                    scanner.nextLine(); // wait for press enter
                }
            } catch (Exception e) {
                System.out.println("Incorrect option. Press \"Enter\" to open menu and try again.");
                scanner.nextLine(); // clear invalid input
                scanner.nextLine(); // wait for press enter
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
                    break;
                }
                System.out.print("Incorrect input. Try again: ");
            } catch (Exception e) {
                System.out.print("Incorrect input. Try again: ");
            } finally {
                scanner.nextLine();
            }
        }
        return promptTypeNumber;
    }
}

class QueueThread extends Thread {
    @Override
    public void run() {
        while (Main.programRunning) {
            try {
                TimeUnit.SECONDS.sleep(1);
                Main.timeSinceStartup++;
                if (Main.inSystemState) {
                    System.out.println("! " + Main.timeSinceStartup + "s. have passed since system startup !");
                    System.out.println("! Number of roads: " + Main.roads + " !");
                    System.out.println("! Interval: " + Main.interval + " !");
                    System.out.println();
                    for (int i = 0; i < Main.roads; i++) {
                        if (Main.roadNames[i] != null) {
                            System.out.println(Main.roadNames[i]);
                        }
                    }
                    System.out.println();
                    System.out.println("! Press \"Enter\" to open menu !");
                }
            } catch (InterruptedException ignored) {}
        }
    }
}
