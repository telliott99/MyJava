.. _random:

##############
Random Numbers
##############

Random numbers (really "pseudo-random" numbers), can be obtained in a couple ways.  Here we use the function ``random`` from the ``Math`` package:

.. sourcecode:: java

    public class RandomInt { 
        public static void main(String[] args) { 
            double r = Math.random(); 
            System.out.printf("Your random number is: %3.5f", r);
            System.out.println();
        }
    }

.. sourcecode:: bash

    > javac RandomInt.java 
    > java RandomInt
    Your random number is: 0.91185
    >

In this version we used a "format string" ``"  %3.5f", r`` and the function ``printf``. We could get a newline by putting ``"  %3.5f\n", r``, if we did that then we wouldn't need the next call ``System.out.println();``.

The main point to make here is that the random number generator gives us a floating point number in the half-open range ``[0,1)``.  (The range includes 0 but not 1---although this specification only really makes sense for integers rather than real numbers).  

In the next code sample, we obtain an integer in the range ``[0,N)``:

.. sourcecode:: java

    public class RandomInt { 
        public static void main(String[] args) { 
            double r = Math.random();
            int N = 11;
            // a pseudo-random integer between 0 and N-1 (10)
            int n = (int) (r * N);
            System.out.println("Your random integer is: " + n);
        }
    }

In the above snippet, we use a *cast* to ``int``, which gives the "floor" or largest integer value that is less than the given floating point number.  In the ``println`` function we also (implicitly) cast from int to a String, which then got concatenated with the first part of the output.  Another way to do this would be to use a format string and do:

.. sourcecode:: java

    System.out.println("Your random integer is: %d", n);

``%d`` is the placeholder used for integers inside format strings.  Next, let's give the user the ability to input a value for ``N``, as follows.  Add the following line as the first line of the function ``main``:

.. sourcecode:: java

    int N = Integer.parseInt(args[0]);

Here is the result:

.. sourcecode:: bash

    > java RandomInt 1000
    Your random integer is: 659

As you might expect, if the user does not input a number (or inputs a string or a smiley face or something else), this code will just crash.  To do this right, we need to implement the ability to check for and deal appropriately with errors.  We'll come back to this issue later.
    
Finally, we provide the ability to specify a range of integers for the random number:

.. sourcecode:: java

    public class RandomInt { 
        public static void main(String[] args) { 
            int min = Integer.parseInt(args[0]);
            int max = Integer.parseInt(args[1]);
            double r = Math.random();
            // a pseudo-random integer in the range min..max
            int range = max - min;
            int n = (int) (r * range) + min;
            System.out.printf("Your random integer is: %d", n);
            System.out.println();
        }
    }

And the result:

.. sourcecode:: bash

    > java RandomInt 135 223
    143
    >

Actually testing this code, to see that the numbers are approximately uniform, and that the range matches what we specified, is also left for another time.


