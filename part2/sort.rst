.. _sort:

#######
Sorting
#######

Sorting is a common operation in computer science.  If we have an array of Integers, it's pretty clear what we want when we ask for a sorted array.  

For Strings, "a" comes before "b" in the same order as the standard alphabet, but if we want "A" to sort just the same as "a", or just after "a" (these are different things), we need to take an extra step.  That's because *lexicographically*, the default sorting method puts all the capital letters before all the lowercase letters (ordered based on ASCII encoding).

To extend sorting to user-defined objects, let's add an "interface" to the ``Obj`` class definition shown previously

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

If we then paste this code onto the end of ``main``:

.. sourcecode:: java

    System.out.println("sorted:"'");
    Collections.sort(A);
    for (Obj o:A) { System.out.println(o); }

From the command line we get the output:

.. sourcecode:: bash

    > java ObjStuff
    1: Tom
    2: Tom
    finally:
    3: Tom
    3: Joan
    3: Sean
    sorted:
    3: Joan
    3: Sean
    3: Tom
    >
    
The new part is what comes after "sorted:".

You can see that the objects have been sorted lexicographically according to name.  But they could be sorted according to any property by changing the ``compareTo`` function appropriately.

The full listing for ``ObjStuff`` is:

.. sourcecode:: java

    import java.util.*;
    
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
            System.out.println('sorted:');
            Collections.sort(A);
            for (Obj o:A) { System.out.println(o); }
        }
    }
    
