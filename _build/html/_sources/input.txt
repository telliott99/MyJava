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

.. sourcecode:: bash

    > java Args1 Hello world 1
    Hello
    world
    1
    >

And here is an example that does a check that the argument is the correct type:

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