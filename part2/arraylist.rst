.. _arraylist:

#########
ArrayList
#########

In a previous section we looked at Java arrays, which are similar to the arrays found in C and C++.

.. sourcecode:: java

    class Test {
        public static void main(String[] args) {
            int[] A = { 1, 3, 6, 4 };
            for (int i: A) { 
                System.out.printf("%d ", i); 
            }
            System.out.println();
        }
    }
    
This code does what you'd expect:

.. sourcecode:: bash

    > javac Test.java 
    > java Test
    1 3 6 4 
    >

However, this type of array has several limitations.  The only way to work with the values is to access them like ``println(A[2]);``, or use the for-each construct as a way of doing the same thing behind the scenes.

There is also a ``length`` property (not a method, and doesn't get "called" with parentheses).  If we add this:

.. sourcecode:: java

    System.out.println(A.length);

we get the additional output:

.. sourcecode:: bash

    4

But that's it.  There is no built-in way to sort the list, or reverse it.  Such an array can only hold primitive types, not objects, with the exception of Strings.  The length (number of items) is fixed and cannot be altered.

One thing that is different in Java arrays is the ability to hold String types.  For example, the argument to ``main`` is an array of Strings:  ``String [] args``.

One can crash a program by attempting to access an invalid index.  We say that the compiler does not do "bounds checking" on array access.

Consider this code, which is *almost* correct:

.. sourcecode:: java

    class Test {
        public static void main(String[] args) {
            int[] A = { 1, 3, 6, 4 };
            for (int i=0; i <= A.length; i++) {
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

Can you see what the problem is?

To solve these difficulties, Java provides a number of container classes, like the Array List.  Here is an example of how to use this container.

.. sourcecode:: java

    import java.util.*; 

    public class Test {
        public static void main(String[] args) {
        List<String> myList = new ArrayList<>();
        myList.add("Tom"); 
        myList.add("Joan");
        for (String s : myList) {
            System.out.println(s); }
        } 
    }
    
The import statement is required so that the compiler knows about the names List and ArrayList.  The "concrete" type we are using is an ArrayList, but it is common to declare the type of the variable to be a List, which is technically called an interface.

In the line:

.. sourcecode:: java

    List<String> myList = new ArrayList<>();

We declare a variable ``myList`` which conforms to the interface expected of Lists.  This one holds String objects.  (Older Java examples may not have this).  On the right-hand side we call the constructor for an ArrayList.  The empty ``<>`` is OK because the compiler can infer the type of elements from the declaration on the left-hand side.

The next example shows two slightly different ways of making an ArrayList, the first one uses a special double brackets ``{{  }}`` notation.

.. sourcecode:: java

    import java.util.*;

    class ArrayStuff {
        // iterator
        public static void prettyPrint(ArrayList<String> A) {
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
            prettyPrint(C);
            Collections.sort(C);
            prettyPrint(C);

            ArrayList<String> D = new ArrayList<String>();
            for (String s: Arrays.asList("j","k","l")) {
                D.add(s);
            }
            prettyPrint(D);
        }
    }

A new idea here is shown by the accessory method ``prettyPrint`` which prints an ArrayList<String>.  We obtain an iterator to move through the ArrayList, and the code shown below is the characteristic way to use an iterator.

.. sourcecode:: java

    Iterator<String> it = A.iterator();
    while(it.hasNext()) { 
        System.out.print(it.next()); 
    }

We can also call the ``sort`` method, which works so long as the objects in the ArrayList have a notion of how to compare one to another.

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

and we have a new method:

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

You can see that the objects are sorted lexicographically according to name, but they could be sorted according to any property by changing the ``compareTo`` function appropriately.