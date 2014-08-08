.. _objects:

#######
Objects
#######

Of course, Java is all about "objects" and object-oriented programming.  Here is a relatively simple example where we define our own objects.  The code is in two parts, and should be placed in the single file:  ``SortStuff.java``.

.. sourcecode:: java

    import java.util.*;

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
    
The import is explained below.

This part gives the definition of our ``Obj`` class.  The compiler will use the definition to generate objects of this type when we ask for them.  Each object will have its own name.  The class as a whole will keep track of the count of all objects of class ``Obj``.  The variable ``count`` is static and there is one per class.  For "instantiation", the programmer will provide a ``name``, and the "constuctor" function (with the same name as the class: ``public Obj(String s)``) will be called, during which the count of objects will be incremented by one.

To give a nice printable description of the class, we provide the ``toString`` function.

The second section of code should go just below the above portion in the same file:

.. sourcecode:: java

    class ObjStuff {
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
            System.out.println("finally:");
            for (Obj o:A) { System.out.println(o); }
        }
    }
    
We "make" or "instantiate" three objects with the variable names as shown (and also ``names`` for each).  We make an ``ArrayList`` of the objects (there may be an easier way to do this that I don't know at the moment), and then use the ``for-each`` construct to iterate through the list and print out the description of each object.

From the command line:

.. sourcecode:: java

    > javac ObjStuff.java
    > java ObjStuff
    1: Tom
    2: Tom
    finally:
    3: Tom
    3: Joan
    3: Sean
    >

The numbers that shown are the ``count`` of objects of class ``Obj``.  We call ``println(o1)`` after instantiating the second object, and see that the count has been increased appropriately.  Even though we are asking the first object what the count is, it "knows" that a second object of its type has been created.  After making three objects, the count is 3.
