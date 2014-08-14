.. _overload:

###########
Overloading
###########

Sometimes you will want to provide a default value for an argument.  In Python, for example, one might say:

.. sourcecode:: python

    def my_func(x,v=False)
    
where the function would normally be called like ``my_func(5)``, but for debugging purposes the verbose flag would be passed as ``my_func(x,v=True)``, and more information would be printed to the console.

In Jave, function overloading is used instead.  Whereas in Python, there would be only a single function named ``my_func`` (at least in the available namespace), in Java there might be multiple functions with the same label.

Here is an example 

.. sourcecode:: java

    class Default {
        public static void my_func(int n, String s) {
            System.out.printf("%d: %s\n", n, s);
        }
        public static void my_func(String s) {
            my_func(5,s);
        }
        public static void my_func(int n) {
            my_func(n,"abc");
        }
        public static void my_func() {
            my_func(5,"abc");
        }

        public static void main(String[] args) {
            my_func(10,"xyz");
            my_func(10);
            my_func("xyz");
            my_func();
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

Basically, the way this works is that the function arguments are part of its "signature", distinguishing these alternatives by variations in the arguments they accept.  ``my_func()`` and ``my_func(String s)`` are *different functions*.  And internally, both of these call the function ``my_func(int n, String s)``.

Note that whereas, in C and C++ the return type is part of the function signature, this is not true is Java, and will result in the error ``method is already defined in class``.

For the second and fourth examples, the default value of ``"abc"`` is used;  similarly, for the third and fourth examples the default value of ``5`` is used.