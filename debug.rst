.. _debug:

#########
Debugging
#########

I suppose it is possible that I once entered a new program correctly on the first try, but nearly always there is an error.  These "bugs" are of various kinds including 

    - spelling mistakes
    - missing or extra punctuation
    - missing symbols
    - logical errors
    
Furthermore, errors may reveal themselves upon careful reading, during compilation, at run-time, and even with some inputs but not with others.  Logical errors may never be revealed without careful analysis.

Let's restrict ourselves here to errors due to leaving out an import letter or symbol.

One way to learn about bugs is to randomly leave out pieces of a program that works.  Find the error in the following versions of "Hello World!".

Example 1:

.. sourcecode:: java

    public class Hello {
        public static void main(String[] args) {
            System.out.println("Hello World!");
    }
    
.. sourcecode:: bash

    > javac Hello.java 
    Hello.java:4: error: reached end of file while parsing
    }
     ^
    1 error
    >
    
Example 2:

.. sourcecode:: java

    public class Hello {
        public static void main(String[] args) {
            System.out.println("Hello World!");
        
    }
    
.. sourcecode:: bash

    > javac Hello.java 
    > java Hello
    Error: Main method not found in class Hello, please define the main method as:
       public static void main(String[] args)
    or a JavaFX application class must extend javafx.application.Application
    >
    
Example 3:

.. sourcecode:: java

    public class Hello {
        public static void main(String[] args) {
            System.println("Hello World!");
        }
    }
    
.. sourcecode:: bash

    > javac Hello.java 
    Hello.java:3: error: cannot find symbol
            System.println("Hello World!");
                  ^
      symbol:   method println(String)
      location: class System
    1 error
    >

In general, I would suggest that you:

    - always read the error message very carefully
    - if there are multiple errors, fix only one, then retest
    - if in doubt, insert extra print statements.

This last approach has a disparaging label:  "caveman" debugging.  But for simple programs, it can be as fast as using a real debugger, which is a relatively complicated program that can analyze your program and help you find bugs.

Do not be overwhelmed if there are dozens of errors listed by the compiler.  This can often be the result of a single mistake.

Build programs in small increments.  Write a short piece of code that works, test it, add a little bit more.  Move forward from the known to the unknown.  Finally, if everything looks correct but there is still an error, find an example on the web and see what's different.  StackOverflow is filled with great analysis.