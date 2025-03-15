# Traffic Light Simulator with Java

## Project Details

> - **Level**: 🌟🌟 Challenging | **Time**: 🕒 ~23 hours
> - **Course**: [Java Developer](https://hyperskill.org/courses/17-java-developer)
> - **Project**: [Traffic Light Simulator with Java](https://hyperskill.org/projects/288?track=17)
> - **Description**: By the end of this project, in addition to creating your own "traffic light", you will learn how to
    work with multi-threading, handle exceptions, inherit classes, and implement and apply the circular queue data
    structure
> - **Powered by**: [Hyperskill](https://hyperskill.org/)

## About

You will practice concepts frequently tested in technical interviews at top tech companies.
Do you know how traffic lights were invented? Some time ago, it was decided to replace the traffic police officers with
a looped automated system that gives information to drivers about whether they can continue driving or not. In this
project, we will implement a simplified version of such a system for a road junction in which many roads converge to
one.

## What you'll learn

Once you choose a project, we'll provide you with a study plan that includes all the necessary topics from your course
to get it built. Here’s what awaits you:

- Stage 1/6: Open the control panel
    - Output the simple list of options as a control panel for a future traffic light.
    - Stage Topics:
        - Introduction to Java
        - Basic literals: numbers, strings and characters
        - Writing first program
        - Printing data
- Stage 2/6: Set up the traffic light
    - Good traffic light knows how many roads it controls and the interval between the opening and closing of the road.
      Input these two values to a program and implement the looped menu's option selection with simple stubs.
    - Stage Topics:
        - Types and variables
        - Comments
        - Reading user's input with Scanner
        - Coding style conventions
        - Naming variables
        - Arithmetic operations
        - Integer types and operations
        - Increment and decrement
        - Floating-point types
        - Boolean type and operations. True and false
        - Comparing values. Relational operators
        - Conditional statement
        - One-line condition with ternary operator
        - For loop
        - While and do-while loops
        - Break and continue. Branching
        - Multiple conditions: switch
        - Characters
        - String
        - IDE
        - What are bugs
        - Debugging overview
        - IntelliJ IDEA
        - Run and debug with IntelliJ IDEA
        - Debugging simple constructs
- Stage 3/6: Oops, wrong button
    - What if the user inputs "Hello" as the number of roads? The program will terminate with an exception, but the good
      traffic light should not stop working because of the user's mistake. Handle it and print the appropriate feedback.
    - Stage Topics:
        - Data types and their sizes
        - Type casting
        - Calling methods
        - Declaring methods
        - Method "main"
        - Functional decomposition
        - Overloading
        - Primitive and reference types
        - Array
        - Arrays as parameters
        - Write, compile, and run
        - Errors in programs
        - First glance at exceptions
        - What is object-oriented programming
        - Defining classes
        - Grouping classes with packages
        - Instance methods
        - Initializing new instances. Constructor
        - Access modifiers
        - Getters and setters
        - Inheritance
        - Multiple constructors
        - Keyword "super"
        - Hierarchy of exceptions
        - Exception handling
- Stage 4/6: Like a clockwork
    - Let's continue working with our menu. When the initial settings are provided, create a new thread that will start
      counting the seconds.
    - Stage Topics:
        - Constants. Final variables
        - NullPointerException
        - Protected modifier
        - Referencing subclass objects
        - Objects and their properties
        - Static members
        - Adding annotations
        - Method overriding
        - Polymorphism
        - Declaring functionality with interfaces
        - Immutability
        - Wrapping classes. Boxing
        - Introduction to generic programming
        - Lambda expressions
        - Synchronous, asynchronous, parallel
        - Processes and threads
        - Threads as objects
        - Custom threads
        - Thread management
        - Exceptions in threads
- Stage 5/6: Over and over again
    - Let's give users the option to add and delete new roads. Roads open in a cyclical order, so don't forget to
      implement the circular queue as a buffer to store elements.
    - Stage Topics:
        - Data structures
        - Abstract and concrete data structures
        - Queue
        - Circular queue
- Stage 6/6: Red, yellow, green
    - Our traffic light doesn't work as expected; it only shows the information about roads that were added to a road
      junction. Each time the system updates the output, calculate the exact time to close/open for each road.