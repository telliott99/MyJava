.. _graphics:

########
Graphics
########

One reason for the use of Java is the graphics libraries that allow you to make a (reasonably platform-independent) GUI.  This can be fairly tedious to do using the basic routines.  But since we have the ``StdDraw`` library (see :ref:`libraries`), we can make a simple example easily:

.. sourcecode:: java

    import java.util.Arrays;

    public class Test {
        public static void main(String[] args) {
            StdDraw.setXscale(0,200);
            StdDraw.setYscale(0,200);
            StdDraw.clear(StdDraw.GREEN);
            double x,y,r;
            int N = 20;
            double[] xlist = new double[N];
            double[] ylist = new double[xlist.length];
            // xlist = [0,0.1 .. 0.9]
            for (int i = 0; i < N; i++) {
                xlist[i] = (i+1)/10.0;
            }
            for (int i = 0; i < xlist.length; i++) {
                x = xlist[i];
                y = Math.sin(x * Math.PI);
                ylist[i] = y;
            }

            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.line(0,100,200,100);

            StdDraw.setPenColor(StdDraw.RED);
            r = 6;
            for (int i = 0; i < xlist.length; i++) {
                x = xlist[i];
                y = ylist[i];
                StdDraw.filledCircle(100*x,50*y+100,r);
            }

            StdDraw.show(0);
            StdDraw.save("example.png");
            //System.exit(0);
        }
    }
    
If you run this in the usual way, it will show the graphic in a window and also save it to a file on the Desktop.  You will have to close the window manually.  If you'd rather not, un-comment the last line ``System.exit(0);``

The image looks like this:

.. image:: /figures/example.png
   :scale: 75 %

Here are two more examples that also use ``StdDraw`` and in addition use ``StdRandom``.  They are taken directly from code on Sedgewick and Wayne's site and/or in the book.

.. sourcecode:: java

    public class Test {
        public static void main(String[] args) {
            double x,y;
            int N = Integer.parseInt(args[0]);
            for (int i = 0; i < N; i++) {
                x = StdRandom.gaussian(0.5,0.2);
                y = StdRandom.gaussian(0.5,0.2);
                StdDraw.filledCircle(x,y,0.01);
            }
            StdDraw.save("gaussian.png");
            System.exit(0);
        }
    }
    
.. sourcecode:: bash

    > javac Test.java
    > java Test 250
    >

.. image:: /figures/gaussian.png
   :scale: 75 %
   
The following is spiffed up a little bit.  One problem I couldn't solve easily, how to draw the array of random numbers before *and* after sorting.  The drawing interferes with the sort.  To see what I mean, uncomment the line below.

A relatively easy fix would be to make a copy of the first array, sort that, and then draw both.

.. sourcecode:: java

   import java.util.*;

   public class Test {
       public static double[] getNumbers(int N){
           double[] a = new double[N];
           StdRandom.setSeed(137);
           for (int i = 0; i < N; i++) {
               a[i] = StdRandom.uniform();
           }
           return a;
       }

       public static void drawIt(double[] a, String fn) {
           int N = a.length;
           for (int i = 0; i < N; i++) {
               double x = 1.0*i/N;
               double y = a[i]/2.0;
               double rw = 0.4/N;
               double rh = a[i]/2.0;
               StdDraw.filledRectangle(x,y,rw,rh);
           }
           StdDraw.save(fn);
       }

       public static void main (String[] args) {
           int N = 50;
           double [] a = getNumbers(N);
           //drawIt(a, "dist.png");
           Arrays.sort(a);
           drawIt(a, "sorted.png");
           System.exit(0);
       }
   }
       
.. sourcecode:: bash

   > javac Test.java
   > java Test
   >

.. image:: /figures/dist.png
  :scale: 75 %

.. image:: /figures/sorted.png
  :scale: 75 %
