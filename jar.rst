.. _jar:

#########
Jar files
#########

Place the following code in a file ``Obj1.java``:

.. sourcecode:: java

    public class Obj1 {
        String name;
        static String myclass = "Obj1";
        public Obj1(String s){ 
            name = s;
        }
        public String toString() { 
            return String.format("%s: %s", myclass, name);
        }
    }

Make another file that is identical except for the substitution of ``Obj2`` for ``Obj1`` everywhere including the filename.  Compile both classes:

.. sourcecode:: bash

    > javac Obj1.java 
    > javac Obj2.java

Put code for this class which uses the first two classes into ``ObjStuff.java``

.. sourcecode:: java

    class ObjStuff {
        public static void main(String[] args) {
            Obj1 o1 = new Obj1("Tom");
            System.out.println(o1);
            Obj2 o2 = new Obj2("Joan");
            System.out.println(o2);
        }
    }

Test that everything works:

.. sourcecode:: bash

    > javac ObjStuff.java
    > java ObjStuff
    Obj1: Tom
    Obj2: Joan
    >

Now, copy both classes into a jar file, and move the other copies out of the way:

.. sourcecode:: bash

    > jar cf myobj.jar Obj1.class Obj2.class
    > mkdir tmp
    > mv Obj1.* tmp
    > mv Obj2.* tmp
    > rm ObjStuff.class
    > ls
    ObjStuff.java	myobj.jar	tmp
    >

Finally, test compilation using the classpath mechanism from above:

.. sourcecode:: bash

    > javac -cp .:myobj.jar ObjStuff.java
    > java -cp .:myobj.jar ObjStuff
    Obj1: Tom
    Obj2: Joan
    >

It works!  Move the jar file to ``/Library/Java/Extensions``:

.. sourcecode:: bash

    > mv myobj.jar /Library/Java/Extensions
    > rm ObjStuff.class
    > javac ObjStuff.java
    > java ObjStuff
    Exception in thread "main" java.lang.IllegalAccessError: tried to access class Obj1 from class ObjStuff
    	at ObjStuff.main(ObjStuff.java:3)
    >

I ran into this error the first time through.  The error was that I forgot to put the label public on the first line of ``Obj1.java`` (and ``Obj2.java``).  I'm getting an ``IllegalAccessError`` because classes are not public by default.  Interesting that this is not a problem when I am in the same directory as the jar file.

Fixed this and did it all again.  Now:

.. sourcecode:: bash

    > javac ObjStuff.java 
    > java ObjStuff
    Obj1: Tom
    Obj2: Joan
    >

It works!