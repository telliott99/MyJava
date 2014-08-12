.. _numbers:

#######
Numbers
#######

I don't have a lot to say about numbers.  If you are looking for online resources on programming fundamentals I can recommend:

http://www.greenteapress.com/thinkapjava/html/index.html

and at a somewhat more advanced level this book by Sedgewick and Wayne is excellent.

http://introcs.cs.princeton.edu/java/home/

There are several different types of integers, as well as several types of floating point numbers.  These differ by how many bits are used to represent them.  A ``double`` is a floating point number with twice the precision (twice the number of digits) as a ``float``.  These are useful for approximately 15 and 7 places, respectively.  Similarly ``long``, ``int``, ``short`` and ``byte`` are different forms of integer, which differ in their maximum values, again, because of the number of bits used to represent them inside the program.

One point worth mentioning is that changes from one type of a number to another may happen implicitly, if the change conserves information, but the other direction requires a cast.  Here is an example with floats (floating point numbers) and integers:

.. sourcecode:: java

    public class Test {
        public static void main(String[] args) {
        	int i = 3;
        	System.out.println("i = " + i);
        	double d = i;
        	System.out.println("d = " + d);
        	int j = (int)d;
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
    
which assigns the value of the integer ``i`` to the double ``d``, a floating point number.  The compiler accepts this happily and "promotes" the value in the process.  But going the other way:

.. sourcecode:: java

    int j = (int) d;

We have to tell the compiler explicitly that we really do mean to convert our float value to an integer.  The notation ``(int) d`` is a "cast", that returns a new value with the "type" of integer.  We can even do this:

.. sourcecode:: java

    int k = (int)(Math.PI);
    System.out.println(k);

.. sourcecode:: bash

    > javac Test.java
    > java Test
    3
    > 

Oops.

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
    
