.. _objects:

#######
Objects
#######

Of course, Java is all about "objects" and object-oriented programming.  Here is a relatively simple example where we define our own objects.  The code is in two parts, and for this example should be placed in the single file:  ``ObjStuff.java``.

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
    
This part gives the definition of our ``Obj`` class.  The compiler will use the definition to generate objects of this type when we ask for them.  Each object will have its own name, provided by us.  

The class as a whole will keep track of the count of all objects of class ``Obj``.  The variable ``count`` is static, meaning that there is just one per class.  For "instantiation", the programmer will provide a String to use as the ``name``, and the "constuctor" function will be called (it has the same name as the class: ``public Obj(String s)``), during construction the count of objects will be incremented by one.

Once all the objects are constructed, the count remains constant, and each object will know the count of all ``Obj`` objects.

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
    
At the very top of the file add:

.. sourcecode:: java

    import java.util.*
    
The import makes the call ``Arrays.asList`` work.
    
We "make" or "instantiate" three objects with the variable names shown (and also ``names`` are provided for each).  We make an ``ArrayList`` of the objects (there may be an easier way to do this that I don't know at the moment);  we'll have more to say about arrays and ArrayLists in just a bit.  Notice that this ArrayList has a function ``add`` for adding new elements to the array.

We use the ``for-each`` construct to iterate through the list and print out the description of each object.

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

The numbers shown are the total ``count`` of all objects of class ``Obj`` at the time the ``println`` function executes.  We call ``println(o1)`` after instantiating the second object, and see that the count has been increased appropriately.  Even though we are asking the first object what the count is, it "knows" that a second object of this type has been created.  After making three objects, the count is 3, naturally enough.
