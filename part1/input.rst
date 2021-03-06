.. _input:

##########
User Input
##########

IO usually refers to input and output from a program by reading from and writing to the file system.  But even before that, there is a way to feed a limited amount of data into a program using what are called command-line arguments.  Here is the basic usage:

.. sourcecode:: java

    public class Args1 {
        public static void main (String[] args) {
            for (String s: args) {
                System.out.println(s);
            }
        }
    }
    
The statement

.. sourcecode:: java

    main (String[] args)
    
says that the variable ``args`` is an array of String variables.  This array will contain any values entered on the command line after the program name, like so:

.. sourcecode:: bash

    > java Args1 Hello world 1
    Hello
    world
    1
    >
    
In C or C++ we would have an additional variable to tell how many different values are in the array, but Java takes care of that for us more or less automatically, either by using the for-each construct or by accessing ``args.length`` 

Here is a second example that checks to see that the argument is the correct type:

.. sourcecode:: java

    import java.util.*;

    public class Args2 {
        public static void main (String[] args) {
            int firstArg;
            String msg;
            if (args.length > 0) {
                try {
                    firstArg = Integer.parseInt(args[0]);
                } catch (NumberFormatException e) {
                    msg = "Argument: " + args[0] + " must be an integer.";
                    System.err.println(msg);
                    System.exit(1);
                }
            }
        }
    }

.. sourcecode:: java

    > javac Args2.java
    > java Args2 x
    Argument: x must be an integer.
    > java Args2 3
    >

The line ``import java.util.*`` is needed for the "exception" type we use:  ``NumberFormatException``.

Here is an example of interactive input from the user:

.. sourcecode:: java

    import java.util.Scanner;

    public class Questions {
        public static void main(String[] args) {
            String s;
            System.out.println("Hello");
            Scanner sc = new Scanner(System.in);
            System.out.println("Wanna play a game?\n  Enter a letter: ");
            while (true) {
                s = sc.nextLine();
                if (s.equals("q") || s.equals("Q")) {
                    System.exit(0);
                }
                System.out.println(s);
                System.out.println("Again?  Enter q to quit");
            }
        }
    }

.. sourcecode:: bash

    > javac Questions.java 
    > java Questions
    Hello
    Wanna play a game?
      Enter a letter: 
    a
    a
    Again?  Enter q to quit
    b
    b
    Again?  Enter q to quit
    q
    >

And here is a more extensive example:

.. sourcecode:: java

    import java.util.*;
    import java.io.IOException;

    public class UserInput {

        public static void main(String args[]) throws IOException {
      
            System.out.println("Please enter your name: ");
            Scanner inputReader = new Scanner(System.in);
       
            //Getting input in String format
            String name = inputReader.nextLine();
            System.out.println("Hi " + name);
      
            //Getting number as input from command line in Java
            System.out.println("Please enter an integer: ");
            int number = inputReader.nextInt();
            System.out.println("You have entered : " + number);
      
            //Getting floating point as input from command line in Java
            System.out.println("Please enter a floating point number: ");
            float decimal = inputReader.nextFloat();
            System.out.println("You have entered : " + decimal);
      
        }
    
    }

.. sourcecode:: bash

    > javac UserInput.java 
    > java UserInput
    Please enter your name: 
    Tom
    Hi Tom
    Please enter an integer: 
    123
    You have entered : 123
    Please enter a floating point number:
    3.1415
    You have entered : 3.1415
    >