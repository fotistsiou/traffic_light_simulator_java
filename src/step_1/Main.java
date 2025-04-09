package step_1;

/**
 * Open the control panel
 * ----------------------
 * Description
 * Let's output the starting menu that greets users and shows them a list of four possible options. We will use this
 * menu in later stages.
 * ----------------------
 * Objectives
 * As a start, develop a simple program that prints six non-empty lines to the output.
 * Being a very polite program, it greets users on the first line with the Welcome substring and tells them that
 * they've just started traffic management system.
 * The following line is the list's title, with Menu substring.
 * After that, finally, display the list line-by-line in exact order, indexing and substrings:
 * 1. Add
 * 2. Delete
 * 3. System
 * 0. Quit
 * In further stages we will control the traffic light system with these actions.
 */

public class Main {
    public static void main(String[] args){
        System.out.println(
            """
            Welcome to the traffic management system!
            Menu:
            1. Add
            2. Delete
            3. System
            0. Quit
            """
        );
    }
}
