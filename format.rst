.. _format:

##############
Format Strings
##############

A format string is a string containing the special symbol "%" followed by other stuff, such as ``%d`` or ``%s`` or ``%7.5f``.  A value is substituted into the string at that position, with appropriate formatting.  

For example, ``%7.5f`` means that a floating point value (here a floating point number with ``double`` precision) is substituted into the string at that point.  The "5" refers to the precision.  "7" refers to the total number of characters being substituted, including the decimal point.

.. sourcecode:: java

    public class Test {
        public static void main (String[] args) {
            double p = Math.PI;
            System.out.println(p);
            System.out.printf("pi is equal to %7.5f\n", p);
        }
    }

.. sourcecode:: bash

    > javac Test.java 
    > java Test
    3.141592653589793
    pi is equal to 3.14159
    >

To print the formatted string, we call ``System.out.printf`` rather than the usual ``System.out.println``.  Unlike ``println``, ``printf`` does not automatically print a newline at the end of output, so we add the newline character ``\n`` to the string as well.  (Windows users beware, newline is "\r\n" for you).

It is also possible to construct a format string using ``String.format``.  After we have the String, ``System.out.println`` works as desired.

In addition to floats, one can also substitute integers with ``%d`` and strings with ``%s``.
    
.. sourcecode:: java

    public class Test {
        public static void main (String[] args) {
            double p = Math.PI;
            System.out.println(p);
            System.out.printf("%7.5f\n", p);
            String s = String.format("%3.2f", p);
            System.out.println(s);
            System.out.printf("%d bottles of %s", 2, "beer");
            System.out.println();
        }
    }

.. sourcecode:: bash

    > javac Test.java 
    > java Test
    3.141592653589793
    3.14159
    3.14
    2 bottles of beer
    >

Format strings are much more sophisticated than this, there are lots of examples on the web.  Here is one with the Date class:

.. sourcecode:: java

    import java.util.Date;

    public class Test {
        public static void main (String[] args) {
            Date date = new Date();
            String s = String.format("Date/Time : %tc", date);
            System.out.println(s);
        }
    }

.. sourcecode:: bash

    > javac Test.java 
    > java Test
    Date/Time : Mon Aug 11 08:49:07 EDT 2014
    >
