.. _arraylists:

##########
ArrayLists
##########

In a previous section we looked at Java arrays, which are similar to the arrays found in C and C++.

.. sourcecode:: java

    class Test {
        public static void main(String[] args) {
            int[] A = { 1, 3, 6, 4 };
            for (int i: A) { System.out.printf("%d ", i); }
            System.out.println();
        }
    }
    
This code does what you'd expect.  It prints:

.. sourcecode:: bash

    > javac Test.java 
    > java Test
    1 3 6 4 
    >

However, this type of array has several limitations.  Basically, the only way to work with the values is to access them like ``println(A[2]);``, or use the for-each construct as a way of doing the same thing behind the scenes.  There is a property ``length``.  If we add this:

.. sourcecode:: java

    System.out.println(A.length);

we get the additional output:

.. sourcecode:: bash

    4

But that's it.  There is no way to sort, or reverse, and such an array can only hold primitive types, not objects, with the exception of Strings.  The size (length) is fixed and cannot be altered.  Finally, one can crash the program by attempting to access an invalid index.

Consider this code, which is *almost* correct:

.. sourcecode:: java

    class Test {
        public static void main(String[] args) {
            int[] A = { 1, 3, 6, 4 };
            for (int i=0; i < A.length; i++) {
                System.out.printf("%d ", A[i]);
            }
            System.out.println();
        }
    }

.. sourcecode:: bash

    > javac Test.java 
    > java Test
    1 3 6 4 Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 4
    	at Test.main(Test.java:5)
    >

To solve these problems, Java provides a number of container classes, like the Array List.  Here is an example of how to use this container.  The example shows two ways of making an ArrayList, how to get an iterator to move through one, and a ``sort`` method, which works so long as the objects in the ArrayList have a notion of how to compare one with the other.

.. sourcecode:: java

    import java.util.*;

    class ArrayStuff {
        // iterator
        public static void pp(ArrayList<String> A) {
            Iterator<String> it = A.iterator();
            while(it.hasNext()) { 
                System.out.print(it.next()); 
            }
            System.out.println();        
        }

        public static void main(String[] args) {
            // ArrayList
            ArrayList<String> C = new ArrayList<String>() {{ 
                add("z");
                add("y");
                add("x"); }};
            // iterator
            pp(C);
            Collections.sort(C);
            pp(C);

            ArrayList<String> D = new ArrayList<String>();
            for (String s: Arrays.asList("j","k","l")) {
                D.add(s);
            }
            pp(D);
        }
    }

To extend this example, we add something to the ``Obj`` class definition shown previously

.. sourcecode:: java

    class Obj implements Comparable<Obj> {
        String name;
        // counter for total number of objects
        static int count = 0;
        public Obj(String s){ 
            name = s;
            count += 1;
        }
        public String toString() { 
            return String.format("%d: %s", count, name);
        }
        public int compareTo(Obj o) {
            return name.compareTo(o.name);
        }
    }

The first line has become

.. sourcecode:: java

    class Obj implements Comparable<Obj> {

and we have a new function:

.. sourcecode:: java

    public int compareTo(Obj o) {
        return name.compareTo(o.name);
    }

If we then paste this onto the end of ``main``:

.. sourcecode:: java

    System.out.println('sorted:');
    Collections.sort(A);
    for (Obj o:A) { System.out.println(o); }

From the command line we get the additional output:

.. sourcecode:: bash

    sorted:  
    3: Joan
    3: Sean
    3: Tom
    >

You can see that the objects are sorted lexicographically according to name, but they could be sorted according to any property by changing the ``compareTo`` function.