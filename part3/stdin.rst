.. _stdin:

#####
StdIn
#####

As we said before, it's useful to be able to download and use in our own programs Java code that was written by someone else.  The web page for Sedgewick and Wayne's Java book is here:

http://introcs.cs.princeton.edu/java/home/

They have written a number of libraries which "wrap" basic Java functions, making the life of the application programmer (that's us) much simpler.  We previously showed (see :ref:`libraries`) how to put the jar file in a place where the Java compiler and runtime can find and use it.

Here's an example from the ``StdIn`` library.

Let's start by looking at the input and output.  We have two files:

``x.txt``

.. sourcecode:: bash

    2 4 6

``y.txt``

.. sourcecode:: bash

    1 2 3

.. sourcecode:: bash

    > javac Test.java 
    > java Test x.txt < y.txt
    pp: 2 4 6 
    b: [1, 2, 3]
    >

We've read input from two sources here.  One is a file whose name was obtained as a String on the command line (``x.txt``).  The other is a file whose data was piped into ``System.in`` (filename ``y.txt``).  This functionality is wrapped by ``In`` and ``StdIn``.  Our code is:

import java.util.*;

.. sourcecode:: java

    import java.util.*;

    public class Test {
        public static void pp(int[] a){
            System.out.print("pp: ");
            for (int i:a) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        public static void main (String[] args) {
            In in = new In(args[0]);
            int[] a = in.readAllInts();
            pp(a);
            int n;
            List<Integer> b = new ArrayList<>();
            while (!StdIn.isEmpty()) {
                n = StdIn.readInt();
                b.add(n);
            }
            System.out.println("b: " + b);
        }
    }

We have a utility function to print an int array, then in the first part of ``main``, the ``In`` class (defined in ``In.java``) is used to read the filename on the command line, and subsequently read from that file an array of ints.  The code is just two lines:

.. sourcecode:: java

    In in = new In(args[0]);
    int[] a = in.readAllInts();

A simple array can hold them, because in the background ``readAllInts`` has already constructed the collection and so *we know how many ints we have*.

In the second part of ``main``, we read from the file that has been redirected.  At this point, we could stop.  We can simply use this functionality any time we want to input data by either of these methods.

However, it might be useful to look a closer look at how the magic is achieved.  It may help to write a program for a programming assignment that requires you to do everything with the Java utilities.  We will look at ``In`` (and save ``StdIn`` for the motivated reader).  I copied out the relevant sections of the library file.  The biggest change I made is to add the label ``static`` in a bunch of places (I have to explore yet how they got away without this).

To summarize the steps in the code:

    - try to read a filename from ``args[0]``
    - use a ``Scanner`` to read the file
    - construct a ``String[]`` from the file data
    - convert each string to an ``int``
    
For the first two steps, we exit gracefully upon failure.  This code will still fail miserably, if the file does not contain the expected data.

A successful run:

.. sourcecode:: bash

    > javac Test.java 
    > java Test x.txt
    2 4 6 
    > java Test x.txt
    Exception in thread "main" java.lang.NumberFormatException: For input string: "what"
    	at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
    	at java.lang.Integer.parseInt(Integer.java:580)
    	at java.lang.Integer.parseInt(Integer.java:615)
    	at Test.main(Test.java:70)
    > cat x.txt
    2 what 6
    >

If our data file contains a string that can't be converted to an integer, it blows up.  Of course, having found this problem we could either wrap *it* in a try-catch block or we could just skip that string.  It would also be a good idea to check the lengths of the arrays carefully, in that case.

In any event, this should serve as an example of how to do things right.  And also, it shows just how complicated dealing with user input can be.

.. sourcecode:: java

    import java.io.File;
    import java.io.IOException;
    import java.util.Scanner;
    import java.util.Locale;
    import java.util.regex.Pattern;


    public class Test {
        private static Scanner scanner;
        private static final String CHARSET_NAME = "UTF-8";
        private static final Locale LOCALE = Locale.US;
        private static final Pattern WHITESPACE_PATTERN
            = Pattern.compile("\\p{javaWhitespace}+");
        private static final Pattern EVERYTHING_PATTERN
            = Pattern.compile("\\A");
    
        public static String[] readAllStrings() {
            // we could use readAll.trim().split(), but that's not consistent
            // since trim() uses characters 0x00..0x20 as whitespace
            String[] tokens = WHITESPACE_PATTERN.split(readAll());
            if (tokens.length == 0 || tokens[0].length() > 0)
                return tokens;
            String[] decapitokens = new String[tokens.length-1];
            for (int i = 0; i < tokens.length-1; i++)
                decapitokens[i] = tokens[i+1];
            return decapitokens;
        }

        public static int[] readAllInts() {
            String[] fields = readAllStrings();
            int[] vals = new int[fields.length];
            for (int i = 0; i < fields.length; i++)
                vals[i] = Integer.parseInt(fields[i]);
            return vals;
        }

        public static String readAll() {
            if (!scanner.hasNextLine()) {
                return "";
            }
            String result = scanner.useDelimiter(EVERYTHING_PATTERN).next();
            // not that important to reset delimeter, since now scanner is empty
            scanner.useDelimiter(WHITESPACE_PATTERN); // but let's do it anyway
            return result;
        }

        public static void main (String[] args) {
            String s = "";
            try {
                s = args[0];
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("No filename was given.");
                System.exit(1);
            }
            try {
                File file = new File(s);
                if (file.exists()) {
                    scanner = new Scanner(file, CHARSET_NAME);
                    scanner.useLocale(LOCALE);
                }
            }
            catch (IOException ioe) {
                System.err.println("Could not open " + s);
                System.exit(1);
            }
            String[] fields = readAllStrings();
            int[] vals = new int[fields.length];
            for (int i = 0; i < fields.length; i++) {
                vals[i] = Integer.parseInt(fields[i]);
            }
            for (int i:vals) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

