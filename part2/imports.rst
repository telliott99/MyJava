.. _imports:

#######
Imports
#######

In the previous object example, all the code was in a single file.  Now what we are going to do is split the code into two files, and compile them separately.  In ``Obj.java``:

.. sourcecode:: java

    class Obj {
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
    
Often at this point people will put the label public on ``Obj``, as in ``public class Obj ..``.  We won't need that here.  Also, previously, when we had both classes in the same file, there is a Java rule that only one class in a code file can be public, and it should be the one that has the same name as the file.  So that's why we didn't have ``public`` there before.

Here is our class that uses ``Obj`` objects in ``UseObj.java``:

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
            for (Obj o:A) { System.out.println(o); }
        }
    }

.. sourcecode:: bash

    > javac UseObj.java 
    > java UseObj
    1: Tom
    2: Tom
    3: Tom
    3: Joan
    3: Sean

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
    3: Tom
    3: Joan
    3: Sean
    >

