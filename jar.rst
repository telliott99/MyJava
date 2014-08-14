.. _jar:

#########
Jar files
#########

Place the following code in a file ``ObjA.java``:

.. sourcecode:: java

    public class ObjA {
        String name;
        static String myclass = "ObjA";
        public ObjA(String s){ 
            name = s;
        }
        public String toString() { 
            return String.format("%s: %s", myclass, name);
        }
    }

Make another file that is identical except for the substitution of ``ObjB`` for ``ObjA`` everywhere including the filename.  Compile both classes:

.. sourcecode:: bash

    > javac ObjA.java 
    > javac ObjB.java

Put code for this class which uses the first two classes into ``ObjStuff.java``

.. sourcecode:: java

    class ObjStuff {
        public static void main(String[] args) {
            ObjA o1 = new ObjA("Tom");
            System.out.println(o1);
            ObjB o2 = new ObjB("Joan");
            System.out.println(o2);
        }
    }

Test that everything works:

.. sourcecode:: bash

    > javac ObjStuff.java
    > java ObjStuff
    ObjA: Tom
    ObjB: Joan
    >

Now, copy both classes into a jar file, and move the other copies out of the way:

.. sourcecode:: bash

    > jar cf myobj.jar ObjA.class ObjB.class
    > mkdir tmp
    > mv ObjA.* tmp
    > mv ObjB.* tmp
    > rm ObjStuff.class
    > ls
    ObjStuff.java	myobj.jar	tmp
    >

Finally, test compilation using the classpath mechanism from above:

.. sourcecode:: bash

    > javac -cp .:myobj.jar ObjStuff.java
    > java -cp .:myobj.jar ObjStuff
    ObjA: Tom
    ObjB: Joan
    >

It works!  Move the jar file to ``/Library/Java/Extensions``:

.. sourcecode:: bash

    > mv myobj.jar /Library/Java/Extensions
    > rm ObjStuff.class
    > javac ObjStuff.java
    > java ObjStuff
    Exception in thread "main" java.lang.IllegalAccessError: tried to access class ObjA from class ObjStuff
    	at ObjStuff.main(ObjStuff.java:3)
    >

I ran into this error the first time through.  The error was that I forgot to put the label public on the first line of ``ObjA.java`` (and ``ObjB.java``).  I'm getting an ``IllegalAccessError`` because classes are not public by default.  Interesting that this is not a problem when I am in the same directory as the jar file.

Fixed this and did it all again.  Now:

.. sourcecode:: bash

    > javac ObjStuff.java 
    > java ObjStuff
    ObjA: Tom
    ObjB: Joan
    >

It works!