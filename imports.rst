.. _imports:

#######
Imports
#######

In the previous object example, all the code was in a single file.  Now what we are going to do is split the code into two files, and compile them separately.  In ``Obj.java``:

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
    }

Next, the code that uses ``Obj`` objects in ``UseObj.java``:

.. sourcecode:: java

    import java.util.*;

    class UseObj {
        public static void main(String[] args) {
            Obj o1 = new Obj("Tom");
            System.out.println(o1);
            Obj o2 = new Obj("Joan");
            System.out.println(o1);
            Obj o3 = new Obj("Sean");
            ArrayList<Obj> A = new ArrayList<Obj>();
            for (Obj o: Arrays.asList(o1,o2,o3)){
                A.add(o);
            }
            System.out.println("unsorted:");
            for (Obj o:A) { System.out.println(o); }
            System.out.println("sorted:  ");
            Collections.sort(A);
            for (Obj o:A) { System.out.println(o); }
        }
    }

.. sourcecode:: bash

    > javac ObjStuff.java 
    > java ObjStuff
    Tom
    Joan
    >

As long as both ``Obj.java`` and ``UseObj.java`` are in the same directory and we don't need any names from ``Obj.java`` other than ``Obj``, this works.  The call ``javac UseObj.java`` results in the separate compilation of both classes.  

It also works to first compile ``javac Obj.java`` and then it is just imported when needed.

Packages are a little more complicated.  Create a new directory

.. sourcecode:: bash

    mkdir mystuff

Add the line 

.. sourcecode:: java

    package mystuff;

at the top of both ``Obj.java`` and ``UseObj.java``.  Compile the first on the Desktop (or in ``mystuff``), then copy the class file to the sub-directory.

.. sourcecode:: bash

    > javac Obj.class
    > mv Obj.class mystuff
   
Now, from the Desktop, do 

.. sourcecode:: bash

    > javac UseObj.java
    > mv UseObj.class mystuff
    > java mystuff/UseObj
    1: Tom
    2: Tom
    unsorted:
    3: Tom
    3: Joan
    3: Sean
    sorted:  
    3: Joan
    3: Sean
    3: Tom
    >

