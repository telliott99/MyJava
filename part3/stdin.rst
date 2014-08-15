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

In the second part of ``main``, we read from the file that has been redirected.  At this point, we could move on.  We can simply use this functionality any time we want to input data by either of these methods.

However, it might be helpful to look a closer look at how the magic is achieved.  It may help if you need to write a program for a programming assignment that requires you to do everything with the Java utilities.  

We will look at ``In`` (and save ``StdIn`` for the motivated reader).  What I did was to copy out the relevant sections of the library file, then fix all the bugs this generated.  Then I broke the code up into more small methods.

To summarize the steps in the code:

    - try to read a filename from ``args[0]``
    - use a ``Scanner`` to read the file
    - construct a ``String[]`` from the file data
    - convert each string in the array to an ``int``
    
For the first two steps, we exit gracefully upon failure.  Another point of concern is mis-configured data.  If the data file has a string that can't be converted to an int, we need to deal with that, and I added code to accomplish that.

A successful run:

.. sourcecode:: bash

    > javac Test.java 
    > cat x.txt
    1 what 3
    > java Test x.txt
    1 3 
    >

I won't go through the code in detail.  I hope that by this point it mostly makes sense to you.  In any event, this should serve as a model of how to do things right.  It also shows just how complicated dealing with user input can be.

.. sourcecode:: java

    import java.io.File;
    import java.io.IOException;
    import java.lang.NumberFormatException;
    import java.util.Arrays;
    import java.util.Scanner;
    import java.util.Locale;
    import java.util.regex.Pattern;


    public class Test {
        private static Scanner scanner;  // global variable !

        public static String getFilename (String[] args){
            String fn = "";
            try { fn = args[0]; }
            catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("No filename was given.");
                System.exit(1);
            }
            return fn;
        }

        public static void openFile (String fn) {
            String CHARSET_NAME = "UTF-8";
            Locale LOCALE = Locale.US;
            try {
                File file = new File(fn);
                if (file.exists()) {
                    scanner = new Scanner(file, CHARSET_NAME);
                    scanner.useLocale(LOCALE);
                }
            }
            catch (IOException ioe) {
                System.err.println("Could not open " + fn);
                System.exit(1);
            }
        }

        public static int[] readAllInts(String[] tokens) {
            int[] vals = new int[tokens.length];
            int i = 0, j = 0;
            while (true) {
                // we may fail to convert a token to an int
                if (i == tokens.length) { break; }
                try { 
                    int value = Integer.parseInt(tokens[i]); 
                    vals[j] = value;
                }
                catch (NumberFormatException e) { 
                    vals[j] = 0; 
                    j--; 
                }
                i++;
                j++;
            }
            if (i != j) {  // we did have an issue
                // return a copy of the values up to index j
                return Arrays.copyOfRange(vals,0,j);
            }
            return vals;
        }

        public static String[] readAllStrings(String text) {
            Pattern WHITESPACE_PATTERN
                = Pattern.compile("\\p{javaWhitespace}+");
            String[] tokens = WHITESPACE_PATTERN.split(text);
            if (tokens.length == 0 || tokens[0].length() > 0)
                return tokens;
            // funny way to pop() tokens [0]
            String[] decapitokens = new String[tokens.length-1];
            for (int i = 0; i < tokens.length-1; i++)
                decapitokens[i] = tokens[i+1];
            return decapitokens;
        }

        public static String readAll() {
            // this is just a trick to get the whole content
            Pattern EVERYTHING_PATTERN
                = Pattern.compile("\\A");
            if (!scanner.hasNextLine()) {
                return "";
            }
            scanner.useDelimiter(EVERYTHING_PATTERN);
            return scanner.next();
            // not  important to reset delimeter
            // since now scanner is empty
        }

        public static void main (String[] args) {
            String fn = getFilename(args);
            openFile(fn);  // uses global Scanner
            String text = readAll();
            String [] tokens = readAllStrings(text);
            int[] vals = readAllInts(tokens);
             for (int i:vals) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    
