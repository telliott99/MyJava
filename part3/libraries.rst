.. _libraries:

#########
Libraries
#########

It's very useful to be able to download and use new Java code written by someone else.  As an example, we'll look at the web page for Sedgewick and Wayne's Java book:

http://introcs.cs.princeton.edu/java/home/

One of the resources on that site is a collection of classes packaged into a ``jar`` file, which is like a zip or archive file, but one which Java knows how to navigate, finding the classes packaged inside.

The link to the jar file is on this page

http://introcs.cs.princeton.edu/java/stdlib/

and this is the link

http://introcs.cs.princeton.edu/java/stdlib/stdlib.jar

As the docs say, there are several possibilities to use the jar file after downloading it.  One way is to "un-jar" it:

.. sourcecode:: bash

    > jar xf stdlib.jar

(The usage is styled after ``tar``, where the flag ``-x`` means to un-archive).
    
It will really make a mess if you do this on your Desktop.  Another possibility is to just grab the particular ``.java`` file that you need to import from the website, and then do what we've been doing all along.

However, the normal way to handle this is to set the "classpath", the list of directories where Java should look for class definitions.  Here is ``Test.java`` on the Desktop:

.. sourcecode:: java

    public class Test {
        public static void main(String[] args) {
            StdOut.println("Hello StdOut World");
        }
    }

We're using something (``StdOut``) from the jar file.

If I don't do anything, I can't load the classes I need:

.. sourcecode:: bash

    > javac Test.java
    Test.java:3: error: cannot find symbol
            StdOut.println("Hello StdOut World");
            ^
      symbol:   variable StdOut
      location: class Test
    1 error
    >

I need to tell the compiler and the Java runtime where to find this code.

.. sourcecode:: bash

    > javac -cp .:stdlib.jar Test.java
    > java -cp .:stdlib.jar Test
    Hello StdOut World
    >

The "flag" -cp places the ``stdlib.jar`` in the current directory (that's the ".") on the classpath.

It's a pain to type this every time.  One solution is to use an alias.  I checked to make sure that the command ``c`` by itself doesn't do anything on the command line.  A check of ``r`` shows that it does, but I can live without that for now (it runs the statistical software R, and I'm not so into statistics at the moment).

.. sourcecode:: bash

    > alias c="javac -cp .:stdlib.jar"
    > alias r="java -cp .:stdlib.jar"
    > c Test.java
    > r Test
    Hello StdOut World
    >

These aliases will last only until you kill the Terminal.  If you want, you can put them in the file which controls options, for me this is ``~/.bash_profile``.

For that matter, you could write a shell script that would take the name of the ``.java`` file you're working on, compile it and then run it.  This is not working yet (it fails on step 2), but you get the idea:

.. sourcecode:: bash

    #!/bin/bash
    echo "working on" $1
    eval "javac -cp ./stdlib.jar $1.java && java -cp ./stdlib.jar $1"

Probably the easiest solution of all:  on OS X just copy the file to

.. sourcecode:: bash

    > cp ~/Desktop/stdlib.jar /Library/Java/Extensions

Here is a more extensive use case.  I grabbed Sedgewick & Wayne's code for a function that takes a data file containing pairs of ``double`` values (longitude and latitude for __ in the U.S.) called ``USA.txt``.  See the code below for the URL for this data file.

Here is the code in ``PlotFilter.java``:

.. sourcecode:: java

    /*************************************************************************
     *  Compilation:  javac PlotFilter.java
     *  Execution:    java PlotFilter < input.txt
     *  Dependencies: StdDraw.java StdIn.java
     *  
     *  % java PlotFilter < USA.txt
     *
     *  Datafiles:    http://www.cs.princeton.edu/IntroProgramming/15inout/USA.txt
     *
     *************************************************************************/

    public class PlotFilter { 

        public static void main(String[] args) {

            // read in bounding box and rescale
            double x0 = StdIn.readDouble();
            double y0 = StdIn.readDouble();
            double x1 = StdIn.readDouble();
            double y1 = StdIn.readDouble();
            StdDraw.setXscale(x0, x1);
            StdDraw.setYscale(y0, y1);

            // turn on animation mode to defer displaying all of the points
            // StdDraw.show(0);

            // plot points, one at a time
            while (!StdIn.isEmpty()) {
                double x = StdIn.readDouble();
                double y = StdIn.readDouble();
                StdDraw.point(x, y);
            }

            // display all of the points now
            StdDraw.show(0);
        }
    }

We compile and run it as follows:

.. sourcecode:: bash

    > javac -cp .:stdlib.jar PlotFilter.java
    > java -cp .:stdlib.jar PlotFilter < USA.txt
    >

The "<" is a Unix redirect that feeds the file directly to the Java program.

A new window opens and the points are plotted.  Here is a screenshot of the result.  You have to quit the window application to terminate.

.. image:: /figures/USA.png
   :scale: 75 %

