.. _default:

#################
Default Arguments
#################

Sometimes the programmer wants to provide a default value for an argument.  In Python, for example, one might say:

.. sourcecode:: python

    def f(x,v=False)
    
where the function would normally be called like ``f(5)``, but for debugging purposes the verbose flag would be passed as ``f(x,v=True), and more information would be printed to the console.

In Jave, function overloading is used instead.  Whereas in Python, there would be only a single function named ``f``, in Java there might be multiple functions with the same label, distinguished by variations in the arguments they accept.

Here is an example 

.. sourcecode:: java

    class Default {
        public static void f(int n, String s) {
            System.out.printf("%d: %s\n", n, s);
        }
        public static void f(String s) {
            f(5,s);
        }
        public static void f(int n) {
            f(n,"abc");
        }
        public static void f() {
            f(5,"abc");
        }

        public static void main(String[] args) {
            f(10,"xyz");
            f(10);
            f("xyz");
            f();
        }
    }

and from the command line:

.. sourcecode:: bash

    > javac Default.java 
    > java Default
    10: xyz
    10: abc
    5: xyz
    5: abc
    > 

For the second and fourth examples, the default value of ``"abc"`` is used;  similarly, for the third and fourth examples the default value of ``5`` is used.