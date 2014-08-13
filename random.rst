.. _random:

##############
Random Numbers
##############

Random numbers (really "pseudo-random" numbers), can be obtained in several ways.  Here we use the function ``random`` from the ``Math`` package:

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

The main point to make here is that the random number generator gives us a floating point number in the half-open range ``[0,1)``.  (The range includes 0 but not 1---although this specification makes much more sense for integers than real numbers).  

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

Actually testing this code, to see that the numbers are approximately uniform, and that the range matches what we specified will be made easier by factoring the random choice code out into a new class.

----------------------
Random Choice, Shuffle
----------------------

Here is some code to pick a random item in a list of items, and also to shuffle a list of items randomly.  If you think this code is worth using, I would recommend that you read:

http://en.wikipedia.org/wiki/Random_permutation

and also note that ``Collections.shuffle`` will shuffle an Array List.  :)

The code also shows an introductory example of using generic functions.

.. sourcecode:: java

    import java.util.*;

    class MyRand {  
        public static int randIntInRange(int min, int max) {
            double r = Math.random();
            int range = max - min;
            return (int) (r * range) + min;  
        }
        static public <T> T randomChoice(ArrayList<T> A){
            int n = A.size();
            return A.get(randIntInRange(0,n));
        }
         static public <T> void randomShuffle(ArrayList<T> A){
            int n = A.size();
            int i,j;
            for (i=0; i < n-2; i++) {
                j = randIntInRange(i,n-1);
                Collections.swap(A, i, j);
            }
        }
    }

    public class Test {
        public static void main(String[] args) {
            ArrayList<Integer> A = new ArrayList<Integer>();
            for (int i=0; i<10; i++) {
                A.add(MyRand.randIntInRange(0,10));
            }
            System.out.println("A: " + A);
            ArrayList<String> B = new ArrayList<String>() {{ 
                add("x");
                add("y");
                add("z"); }};
            System.out.print("B: ");
            for (int i=0; i<30; i++) {
                 System.out.print(MyRand.randomChoice(B));
            }

            System.out.println();
            MyRand.randomShuffle(A);
            System.out.println("A: " + A);
        }
    }

Output:

.. sourcecode:: bash

    > javac Test.java 
    > java Test
    A: [2, 7, 5, 2, 5, 7, 9, 6, 3, 0]
    B: yyzzzyzzzxxyzxyxyyyzxxzyxyyzxx
    A: [9, 7, 2, 3, 2, 5, 5, 7, 6, 0]
    > java Test
    A: [3, 7, 4, 8, 6, 4, 2, 5, 4, 5]
    B: yyxyyzzzyyyyxxzxxxxyzzzyxyxyyx
    A: [4, 8, 4, 4, 3, 2, 5, 6, 7, 5]
    > java Test
    A: [3, 7, 8, 1, 2, 9, 0, 8, 8, 2]
    B: xyyxyyxzxyxyzzyxyxxxxzyzzxyyzz
    A: [8, 3, 7, 8, 8, 1, 9, 2, 0, 2]
    >

Note that your output will be different, since the random number generator starts up from a different position each time it runs (I believe it is initialized with the time).  If you wish to have reproducible output (useful for debugging), do:

.. sourcecode:: java

    import java.util.Random;

    class Test {
        public static void main(String[] args) {
            int seed = 137;
            Random gen = new Random(seed);
            System.out.printf("%3.5f\n", gen.nextFloat());
        }
    }

Output:

.. sourcecode:: bash

    > javac Test.java 
    > java Test
    0.74163
    > java Test
    0.74163
    > java Test
    0.74163
    >

One can also do ``gen.setSeed(seed);``.


