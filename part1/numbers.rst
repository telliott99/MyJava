.. _numbers:

#######
Numbers
#######

You can read elsewhere about all the different types of numbers and the operators that work on them.  For a quick review

http://docstore.mik.ua/orelly/java-ent/jnut/ch02_04.htm 

If you are new to programming and looking for online resources on programming fundamentals I can recommend:

http://www.greenteapress.com/thinkapjava/html/index.html

At a somewhat more advanced level this book by Sedgewick and Wayne is really excellent.

http://introcs.cs.princeton.edu/java/home/

Different types of number differ by how many bits are used to represent them.  A ``double`` is a floating point number (think:  scientific notation) with twice the precision (twice the number of digits) as a ``float``.  These are useful for approximately 15 and 7 places, respectively.  Similarly ``long`` (64), ``int`` (32), ``short`` (16) and ``byte`` (8) are different forms of integer, which differ in their maximum values, again, because of the number of bits used to represent them inside the program, as listed.

One point worth mentioning is that a change from one type of a number to another may happen implicitly, if the change conserves information, but changing in the other direction requires a cast.  Here is an example with floats (floating point numbers) and integers:

.. sourcecode:: java

    public class Test {
        public static void main(String[] args) {
        	int i = 3;
        	System.out.println("i = " + i);
        	double d = i;
        	System.out.println("d = " + d);
        	int j = (int) d;
        	System.out.println("j = " + j);
        }
    }

This prints:

.. sourcecode:: bash

    > javac Test.java 
    > java Test
    i = 3
    d = 3.0
    j = 3
    >

If you look carefully at what we did, we had

.. sourcecode:: java

    double d = i;
    
which assigns the value of the integer ``i`` to the double ``d``, a change from an integer to a floating point number.  The compiler accepts this happily and "promotes" the value in the process.  But going the other way:

.. sourcecode:: java

    int j = (int) d;

we have to tell the compiler explicitly that we really do mean to convert our float value to an integer.  The notation ``(int) d`` is a "cast", that returns a new value with the "type" of integer.  We can even do this:

.. sourcecode:: java

    int k = (int) (Math.PI);
    System.out.println(k);

.. sourcecode:: bash

    > javac Test.java
    > java Test
    3
    > 

Oops.

Operators have different precedence:  for example ``*`` (multiplication) is evaluated before ``+`` (addition).  Thus ``3 + 5 * 2`` is equal to 30, not 16.  However, it is safer not to rely on the rules, if for no other reason than to make your intention clear.  I always do this:  ``3 + (5 * 2)``.

A number of Java methods and class constructors require objects, and do not accept the ``int`` and ``double`` types.  To convert to an Integer object, do this:

.. sourcecode:: java

    public class Test {
        public static void main(String[] args) {
            System.out.println("If 6 turn out to be 9, I don't mind.");
        	int i = 6;
        	Integer myInt = i;
        	Integer oInt = new Integer(9);
        	System.out.println("myInt = " + myInt);
        	System.out.println("or..  = " + oInt);
            System.out.println("If all the hippies, cut off all their hair");
            System.out.println("I don't care..");
        }
    }

.. sourcecode:: bash

    > javac Test.java 
    > java Test
    If 6 turn out to be 9, I don't mind.
    myInt = 6
    or..  = 9
    If all the hippies, cut off all their hair
    I don't care..
    >

Various classes contain definitions of numeric constants, like ``Math.PI`` and ``Math.E``.  Two other values are ``Double.NEGATIVE_INFINITY`` and ``Integer.MIN_VALUE``.  The last one might be used in a routine to scan an array to find the maximum value.  We start by setting ``max`` to a value that is guaranteed to be less than (or equal to) the smallest possible number in the array.  Then go through the array.  If a value is larger than the current value for max, set max equal to that value.

.. sourcecode:: java

    import java.util.*;

    public class Test {
        public static void main(String[] args) {
            int [] A = {12,101,87,37};
            System.out.print("A: ");
            for (int i:A) { System.out.printf("%d ", i); }
            System.out.println();

            // find the maximum value
            int max = Integer.MIN_VALUE;
            System.out.printf("Integer.MIN_VALUE %d\n", max);

            for (int i:A) {
                if (i > max) { max = i; }
            }
            System.out.printf("max:  %d\n", max);
            
            int high = Integer.MAX_VALUE;
            System.out.printf("Integer.MAX_VALUE %d\n", high);
            int x = (int) Math.pow(2,31) - 1;
            System.out.printf("2^31 - 1 = %d\n", x);
        }
    }

.. sourcecode:: bash

    > javac Test.java 
    > java Test
    A: 12 101 87 37 
    Integer.MIN_VALUE -2147483648
    max:  101
    Integer.MAX_VALUE 2147483647
    2^31 - 1 = 2147483646
    >

