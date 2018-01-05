.. _interface:

#################
Interface Example
#################

A simple example of an interface.  Each block of code needs to be in a separate file (to solve permissions issues, all classes are ``public``).

.. sourcecode:: java

    public interface I {
        public String speak(String s);
    }

.. sourcecode:: java

    public class A implements I {
        public String speak(String s) {
            return "Howdy " + s;
        }
    }
    
.. sourcecode:: java

    public class B implements I {
        public String speak(String s) {
            return "Hi " + s;
        }
    }

.. sourcecode:: java

    import java.util.ArrayList;

    public class X {
        public static void main(String [] args){
            I a = new A();
            I b = new B();
            ArrayList<I> arr = new ArrayList<I>();
            arr.add(a);
            arr.add(b);
            for (I o: arr) {
                System.out.println(o.speak("Tom"));
            }
        }
    }

.. sourcecode:: bash

    > javac X.java
    > java X
    Howdy Tom
    Hi Tom
    >
