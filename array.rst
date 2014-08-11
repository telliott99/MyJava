.. _array:

######
Arrays
######

There are a number of different "collections" in Java, but the most fundamental is the array, which shares properties with arrays in C and C++.

Here's an example:

.. sourcecode:: java

    import java.util.*;

    class MyArrays {
        public static void main(String[] args) {
            int[] A = { 1, 2, 3, 4, 5 };
               
            // for-each
            for (int i: A) {
                System.out.printf("%d ", i);
            }
            System.out.println();
            System.out.println(Arrays.toString(A));
                
            // using an index
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

Then, we go through the array and print the values in three ways:  using a for-each loop and a format string, using the ``Arrays.toString`` method, and using an index that ranges from ``0`` to ``i < A.length``.  Using the index allows us to print the last value in a special way.

The output:

.. sourcecode:: bash

    > javac MyArray.java
    > java MyArray
    1 2 3 4 5 
    [1, 2, 3, 4, 5]
    A:  0: 1, 1: 2, 2: 3, 3: 4, 4: 5.
    >

We can spiff things up a little by doing the following.  A common usage is to ``join`` the elements of an array of strings joined by a string separator.  This construct doesn't exist in standard Java, though it does in certain add-on libraries.

We will roll our own:

.. sourcecode:: bash

    import java.util.*;

    class Joiner {
        public static String join(String[] words, String sep){
            StringBuilder sb = new StringBuilder();
            int count = 0;
            for (String s:words) { 
                sb.append(s);
                count += 1;
                if (count != words.length-1) {
                    sb.append(sep);
                }
            }
            return sb.toString();
        }
    }

    class MyArray {
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

In the ``main`` function, we examine the contents of our array of Strings in two ways.  First, we use a ``StringBuilder`` to construct a string from a list of values.  Then we just do ``sb.toString()`` to get the string from it.  

In the middle part we test whether the array contains a particular value.  Since the only way to access the array elements directly is using the ``[]`` operator, we must first cast the array using the call:  ``Arrays.asList``.

In the last part of ``main``, we use the ``Joiner`` class to join the components of the array, inserting the String ``*`` between each element.

Here is the output:

.. sourcecode:: bash

    > java MyArray.java
    > java MyArray
    abcde
    true
    a*b*c*de*
    >

