.. _imports:

#################
Importing classes
#################

In the previous object example, all the code was in a single file.  Now what we are going to do is split the code into two files, and compile them separately.  In ``Obj.java``:

.. sourcecode: java

    class Obj {
        String name;
        public Obj(String s){
            name = s;
        }
        public String toString() {
            return name;
        }
    }

Put this on the Desktop and compile as usual.

.. sourcecode: bash

    > javac Obj.java 
    >

Next, the code that uses ``Obj`` objects in ``ObjStuff.java``:

.. sourcecode: java

    class ObjStuff {
        public static void main(String[] args) {
            Obj o = new Obj("Tom");
            System.out.println(o);
            o.name = "Joan";
            System.out.println(o);
        }
    }

.. sourcecode: bash

    > javac ObjStuff.java 
    > java ObjStuff
    Tom
    Joan
    >


