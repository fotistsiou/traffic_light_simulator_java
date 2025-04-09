package step_6;

/**
 * Red, yellow, green
 * ------------------
 * Description
 * So far, the information on which roads are part of the road junction means nothing to a driver and does not help
 * users to control traffic. In this stage, we will finish our traffic management system. As a result, the system will
 * control a set of roads, opening and closing them at intervals specified by the user.
 * The program first welcomes the user and prompts them to input the number of roads and the interval between the
 * opening and closing of each road. Then the control panel with menu is displayed.
 * Let's consider a scenario where we want to control 4 roads, with an interval of 8 seconds. We add all the roads
 * sequentially via the 1. Add option: "First", "Second", "Third", "Fourth". After all the roads are added and the
 * interval has been specified, we can start our system with 3. System:
 * ! Number of roads: 4 !
 * ! Interval: 8 !
 * Road "First" will be open for 8s.
 * Road "Second" will be closed for 8s.
 * Road "Third" will be closed for 16s.
 * Road "Fourth" will be closed for 24s.
 * ! Press "Enter" to open menu !
 * It might be difficult to understand how this system should work, so here is a brief explanation with examples:
 * The roads are managed using a circular queue, where the road at the front of the queue is open, and the rest are
 * closed. In this case, "First" was added first, so it is also the first to be open. It will remain open for the
 * specified interval of 8 seconds before switching to the next road in line. So, "Second" will open after "First" in 8
 * seconds, "Third" will open after "Second" in 16 seconds, and "Fourth" will open after "Third" in 24 seconds.
 * For example, if the remaining time for "First" is 1 second, the system will display:
 * Road "First" will be open for 1s.
 * Road "Second" will be closed for 1s.
 * Road "Third" will be closed for 9s.
 * Road "Fourth" will be closed for 17s.
 * Once the timer for the open road expires, it moves to the back of the queue, and the next road becomes open:
 * Road "First" will be closed for 24s.
 * Road "Second" will be open for 8s.
 * Road "Third" will be closed for 8s.
 * Road "Fourth" will be closed for 16s.
 * In this system, once the last added road, "Fourth", is closed, "First" will open again, and the cycle will repeat.
 * Overall, the program displays the current state of each road (open/close) and calculates the time, until this road
 * closes/opens.
 * Let's consider corner cases. The time for each road should be calculated before the information is displayed, so if
 * a road is deleted, the time for the other roads will change. Let's say we've just deleted one road from system. The
 * next output (after 1 second) will be:
 * Road "First" will be closed for 15s
 * Road "Second" will be open for 7s.
 * Road "Third" will be closed for 7s.
 * Similarly, the time for other roads will change when adding roads.
 * Let's continue exploring our example. If the opened road is deleted, there should be no roads in state open until the
 * next one opens. Let's say we've just deleted two roads from system (sequentially). The next output (after 1 second)
 * will be:
 * Road "Third" will be closed for 6s.
 * Output after 6 more seconds will be:
 * Road "Third" will be open for 6s.
 * If the only road that exists is the opened one - it should never close (its state should be always open), but still
 * should count down the time to close, so the output after 7 seconds will be:
 * Road "Third" will be open for 5s.
 * When there are no roads in the system, the next added road must be open for an interval. Let's say we've deleted last
 * road from system, leaving it empty and added one more road. The next output will be:
 * Road "Fifth" will be open for 8s.
 * Also, you might want to make your program more user-friendly and beautiful. You can achieve it by adding color to the
 * output — green, yellow, and red with these ANSI color codes:
 * ANSI_RED = "\u001B[31m"
 * ANSI_GREEN = "\u001B[32m"
 * ANSI_YELLOW = "\u001B[33m"
 * ANSI_RESET = "\u001B[0m"
 * System.out.println("\u001B[32m" + "Hello World!" + "\u001B[0m");
 * The snippet above will give you Hello World!
 * ------------------
 * Objectives
 * Your task for the final stage will be to add the road's state and timing information to each of the elements in
 * System mode. Add the open/close substring, according to it's state and calculate the time, until this road switches
 * to another state.
 */

public class Main {
}
