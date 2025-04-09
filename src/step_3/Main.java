package step_3;

/**
 * Oops, wrong button
 * ------------------
 * Description
 * What if users didn't get enough sleep? All night they controlled the movement of imaginary roads and in the morning,
 * struggling with sleep and misclicks, enter the incorrect parameters. The system should handle wrong input and print
 * appropriate feedback.
 * In this stage, let's expand our program with error handling and some visual improvements.
 * The number of roads and intervals at which the roads should open/close should be positive integer values (note, that
 * 0 is not a positive value), so if a user provided any other input, our system should print an error that contains the
 * Incorrect input and Try again substrings.
 * The selected option in the menu should be either 0, 1, 2 or 3, so if a user made a mistake, our system should print
 * the Incorrect option feedback.
 * To make the output of our program more convenient, we can clear the previous output after each menu option is
 * executed. Due to the cross-platform nature of Java, clearing the console output can be complicated. You can use this
 * snippet to remove the console output.
 * try {
 *   var clearCommand = System.getProperty("os.name").contains("Windows")
 *           ? new ProcessBuilder("cmd", "/c", "cls")
 *           : new ProcessBuilder("clear");
 *   clearCommand.inheritIO().start().waitFor();
 * }
 * catch (IOException | InterruptedException e) {}
 * However, it would be difficult for users to get familiar with the result of the execution before the information is
 * cleared, so after each operation, the program must wait for a new line to be entered before the next iteration.
 * ------------------
 * Note: Clearing won't work in IntelliJ IDEA console. For that to show up you'll need to run a solution from a
 * terminal.
 * We won't test how you clear the console. If you choose to display the output as solid text, make sure that your
 * program still waits for a new line after option execution.
 * ------------------
 * Objectives
 * To complete this stage, your program must comply with the following requirements:
 * If the provided input for the number of roads or interval is not a positive integer value, the program should print
 * a line, containing Incorrect input and again substrings, followed by a new input.
 * If the chosen option is something other than 0, 1, 2, or 3, the program should output an Incorrect option feedback.
 * Modify the infinite loop so that when the result of option execution is shown, the program requires any input before
 * the next iteration.
 */

public class Main {
}
