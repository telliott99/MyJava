.. _array:

######
Arrays
######

There are a number of different "collections" in Java, but the most basic one is the array.  The Java array shares some properties with arrays in C and C++, but has some additional methods that can operate on it (provided by the ``Arrays`` class).

Here's an example:

.. sourcecode:: java

    import java.util.Arrays;

    class Test {
        public static void main(String[] args) {
            int[] A = { 1, 2, 3, 4, 5 };
               
            // for-each loop
            for (int i: A) {
                System.out.printf("%d ", i);
            }
            System.out.println();
            
            System.out.println(Arrays.toString(A));
                
            // iterate using an index
            System.out.print("A:  ");
            for (int i = 0; i < A.length; i++) {
                if (i < A.length - 1) {
                    System.out.printf("%d: %d, ", i, A[i]);
                }
                else {
                    System.out.printf("%d: %d.", i, A[i]);
                 }
            }
            System.out.println();
        }
    }

The import statement at the top is needed for the ``Arrays.toString`` method, which gives a pretty formatting to the array.  This line

.. sourcecode:: java

    int[] A = { 1, 2, 3, 4, 5 };
    
combines declaration and initialization.  

Then, we go through the array and print the values in three ways:  using a for-each loop and a format string, using the ``Arrays.toString`` method, and using an index that ranges from ``0`` to ``i < A.length``.  Having the index allows us to print the last value in a special way.

The output:

.. sourcecode:: bash

    > javac MyArray.java
    > java MyArray
    1 2 3 4 5 
    [1, 2, 3, 4, 5]
    A:  0: 1, 1: 2, 2: 3, 3: 4, 4: 5.
    >

We can spiff things up a little.  A common usage is to ``join`` the elements of an array of strings joined by a string separator.  This construct doesn't exist in standard Java, though it does in certain add-on libraries.

We will roll our own:

``Joiner.java``:

.. sourcecode:: java

    public class Joiner {
        public static String join(String[] words, String sep){
            StringBuilder sb = new StringBuilder();
            int count = 0;
            for (String s:words) {
                sb.append(s);
                count += 1;
                if (count < words.length) {
                    sb.append(sep);
                }
            }
            return sb.toString();
        }
    }

``Test.java``:

.. sourcecode:: java

    import java.util.Arrays;

    class Test {
        public static void main(String[] args) {
            String[] C = { "a","b","c","d","e"};
            StringBuilder sb = new StringBuilder();

            for (String s: C) { sb.append(s); }
            System.out.println(sb.toString());

            boolean b;
            b = Arrays.asList(C).contains("a");
            System.out.println(b);

            Joiner J = new Joiner();
            System.out.println(J.join(C,"*"));
        }
    }

In the ``main`` function, we look at the contents of the array of Strings in two ways.  First, we use a ``StringBuilder`` to construct a string from a list of values.  Then we just do ``sb.toString()`` to get the string from it.  

In the middle part we test whether the array contains a particular value.  Since the only way to access the array elements directly is using the ``[]`` operator, we must first cast the array using the call:  ``Arrays.asList``.

In the last part of ``main``, we use the ``Joiner`` class to join the components of the array, inserting the String ``*`` between each element.

Here is the output:

.. sourcecode:: bash

    > java MyArray.java
    > java MyArray
    abcde
    true
    a*b*c*d*e*
    >

Quite to my surprise, I learned that even the basic array type can be sorted, the method is in ``Arrays``.  (To be technically accurate, I should say that the Arrays class has a method that takes an array argument and sorts it, in place).

.. sourcecode:: java

    import java.util.*;

    public class Test{
        static void pp(int[] a){
            for (int i: a) {
                System.out.printf("%d ", i);
            }
            System.out.println();
        }
    
        public static void main(String[] args){
            int[] a = { 6, 1, 2, 4, 3, 0, 5 };
            pp(a);
            Arrays.sort(a);
            pp(a);
        }
    }

.. sourcecode:: bash

    > javac Test.java 
    > java Test
    6 1 2 4 3 0 5 
    0 1 2 3 4 5 6 
    >
    
One can also call ``Arrays.sort(array,low,high)`` to sort only those values of the array that lie in the range ``[low,high)``.  Additional methods in this class include ``binarySearch``, ``fill`` and ``copyOfRange``.

Here is another example from Sedgewick and Wayne.  Suppose we have a collection of values like [0:10) and we draw randomly with replacement.  On the average, how long will it take before we see every value (collect every coupon)?

.. sourcecode:: java

    public class CouponCollector {
        public static void main(String[] args) {
            int N = Integer.parseInt(args[0]);
        
            // depends on the fact that default value is false
            boolean[] found = new boolean[N]; 
                   
            int count,values,v;
            // how many random numbers we've tested
            count = 0;
            // how many of the numbers in the range have we seen?
            values = 0;
        
            while (values < N) {
                count += 1;
                // get a random int from [0..N)
                v = (int) (Math.random() * N);
                if (found[v]) {
                    // we already saw this, move on
                }
                else {
                    values += 1;
                    found[v] = true;
                }
            }
            System.out.println(count + " values tested");
        }
    }

    /*
    > javac CouponCollector.java 
    > java CouponCollector 100
    352 values tested
    > java CouponCollector 100
    427 values tested
    >
    */
