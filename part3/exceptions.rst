.. _exceptions:

##########
Exceptions
##########

Exceptions are used when a program is going to try to do something that may cause an error.  The potentially troublesome code is wrapped like this:

.. sourcecode:: java

    try {
        doSomethingDangerous();
    }
    catch (Exception e) {
        e.printStackTrace();
        dealWithIt();
    }

But it should be possible to recover gracefully from the error.  Otherwise the program should probably just die.

A common situation is reading from and writing to the file system.  And user input is always fraught with danger.  

Here are some rather silly examples, just to show the syntax:

.. sourcecode:: java

    public class Test {
        public static void main(String[] args){
            int N;
            try {
                // command line argument?
                N = Integer.parseInt(args[0]);
            }
            catch (ArrayIndexOutOfBoundsException e) {
                //e.printStackTrace();
                N = Integer.MAX_VALUE;
            }
            StdOut.printf("N: %d\n", N);
            double d;
            try {
                // N might be zero
                d = 100/N;
            }
            catch (ArithmeticException e) {
                //e.printStackTrace();
                d = Double.POSITIVE_INFINITY;
            }
            StdOut.printf("d: %f\n", d);
        }
    }

And the output:

.. sourcecode:: bash

    > javac Test.java 
    > java Test 
    N: 2147483647
    d: 0.000000
    > java Test 0
    N: 0
    d: Infinity
    >