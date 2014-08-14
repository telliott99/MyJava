.. _objects:

#######
Objects
#######

Of course, Java is all about "objects" and object-oriented programming.  Here is a relatively simple example where we define our own objects.  The code is in two parts, and for this example should be placed in the single file:  ``ObjStuff.java``.

.. sourcecode:: java

    public class Obj {
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

The class as a whole will keep track of the count of all objects of class ``Obj``.  The variable ``count`` is static, meaning that there is just one per class.  For "instantiation", the programmer will provide a String to use as the ``name``.

The "constuctor" function will be called (it has the same name as the class: ``public Obj(String s)``), during construction the count of objects will be incremented by one.  The constructor is distinguished by not having a return type.

Once all the objects are constructed, the count will remain constant, and each object will know the count of all ``Obj`` objects.

To give a nice printable description of the class, we provide the ``toString`` function.

The second section of code should go just below the above portion in the same file:

.. sourcecode:: java

    import java.util.*;

    public class ObjStuff {
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
    
We "make" or "instantiate" three objects with the variable names shown (and also ``names`` are provided for each).  

We make an ``ArrayList`` of the objects (there may be an easier way to do this that I don't know at the moment);  we'll have more to say about arrays and ArrayLists in just a bit.  Notice that this ArrayList has a function ``add`` for adding new elements to the array.

We use the ``for-each`` construct to iterate through the list and print out the description of each object.

From the command line:

.. sourcecode:: bash

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

__________________
More about objects
__________________

Why have objects?  One reason is to be able to build complex data structures.  For example, we could have "members" of ``Obj`` other than name and count that might include more Strings, ints, or doubles, or even arrays or different kinds of objects (or even ``Obj`` objects).

However, this can also be accomplished without objects, as C does it.  

The real reasons are three.  First, objects package together data and the methods that work on data.  Second, objects provide a means for encapsulation, a way to hide the implementation inside an object, but expose to programmers who may use an object only certain methods, the so-called application programming interface (API).  Here is an alternative implementation of ``Obj``

.. sourcecode:: java

    public class Obj {
        private String name;
        // counter for total number of objects
        private static int count = 0;
        public Obj(String s){ 
            name = s;
            count += 1;
        }
        public String toString() { 
            return String.format("%d: %s", count, name);
        }
        public String getName() { return name; }
        public void setName(String s) { name = s; }
        public String getCount() { return name; }
    }

We allow users of ``Obj`` to change the name.  But we don't give them access to ``name`` itself.  It doesn't even have to be a string, but could be, say, a series of Egyptian hieroglyphics corresponding to Unicode code points, or something.

The last reason for Object-oriented programming is the idea of modeling a problem in terms of objects that may be related to each other but slightly different, using inheritance.  Here is a silly example:

Four very short files:

``Animal.java``

.. sourcecode:: java

    public abstract class Animal {
        public abstract void speak();
    }

``Cat.java``

.. sourcecode:: java

    public class Cat extends Animal {
        public void speak() {
        System.out.println("Meoww");
        }
    }

``Dog.java``

.. sourcecode:: java

    public class Dog extends Animal {
        public void speak() {
            System.out.println("Whoof");
        }
    }

and the test harness:

``Test.java``

.. sourcecode:: java

    import java.util.*;

    public class Test {
        public static void main(String[] args) {
            Animal d = new Dog();
            Animal c = new Cat();
            List<Animal> aL = new ArrayList<>();
            aL.add(d);
            aL.add(c);
            for (Animal a: aL){
                a.speak();
            }
        }
    }

.. sourcecode:: bash

    > javac Test.java 
    > javac Test.java 
    > java Test
    Whoof
    Meoww
    >

``Animal`` is an abstract class.  You can't make an ``Animal``.  But because ``Cat`` and ``Dog`` objects are both ``Animal`` objects, we can declare them as such:  ``Animal d = new Dog();`` and we can put them together inside an ``ArrayList<Animal>``.  That's very powerful.
