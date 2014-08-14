.. _debug:

#########
Debugging
#########

I suppose it is possible that I once entered a new program correctly on the first try, but nearly always there is an error.  These "bugs" are of various kinds including 

    - spelling mistakes
    - punctuation errors
    - missing symbols
    - missing definitions
    - logical errors
    
Furthermore, errors may reveal themselves upon careful reading, during compilation, or at run-time.  Some errors may even manifest themselves with certain inputs but not with others.  Some logical errors may never be revealed without careful analysis.

Let's restrict ourselves here to errors due to leaving out an important letter or symbol.

One way to learn about bugs is to start with a program that works and then randomly leave out parts of it.  (Conversely, a good strategy for minimizing bugs is to start with a small program that works, and then make small additions to it, testing at every step).

Here is a working program, in the file ``Hello.java``:

.. sourcecode:: java

    public class Test {
        public static void main(String[] args) {
            System.out.print("Hello, World!");
            System.out.println();
        }
    }
    
.. sourcecode:: bash

    > javac Test.java
    > java Test
    Hello, World!
    >

Find the error in the following versions of ``Hello.java``:

Example 1:

.. sourcecode:: java

    public class Test {
        public static void main(String[] args) {
            System.out.print("Hello, World!");
            System.out.println();
        }
    
    
.. sourcecode:: bash

    > javac Test.java 
    Hello.java:5: error: reached end of file while parsing
    }
     ^
    1 error
    >
    
Example 2:

.. sourcecode:: java

    public class Test {
        public void main(String[] args) {
            System.out.print("Hello, World!");
            System.out.println();
        }
    }
    
.. sourcecode:: bash

    > javac Test.java
    > java Test
    Exception in thread "main" java.lang.NoSuchMethodError: main
    >
    
Example 3:

.. sourcecode:: java

    public class Test {
        public static void main(String[] args) {
            System.print("Hello, World!");
            System.out.println();
        }
    }
    
.. sourcecode:: bash

    > javac Test.java
    Test.java:3: cannot find symbol
    symbol  : method print(java.lang.String)
    location: class java.lang.System
            System.print("Hello, World!");
                  ^
    1 error
    >

Example 4:

.. sourcecode:: java

    public class Test {
        public static void main(String[] args) {
            System.out.print("Hello, World!");
            System.out.println;
        }
    }
    
.. sourcecode:: bash

    > javac Test.java
    Test.java:4: not a statement
            System.out.println;
                      ^
    1 error
    >

When debugging, I would suggest that you:

    - always read the error message very carefully
    - if there are multiple errors, fix only one (usually number one), then retest
    - if in doubt, insert extra print statements

This last approach has a disparaging label:  "caveman" debugging.  But for simple programs, it can be as fast as using a real debugger, which is a relatively complicated program that can analyze your program and help you find bugs.

Do not be overwhelmed if there are dozens of errors listed by the compiler.  This can often be the result of a single mistake.

Build programs in small increments.  Write a short piece of code that works, test it, and only after it works, add a little bit more.  Move forward from the known to the unknown.  Finally, if everything looks correct but there is still an error, find an example on the web and see what's different.  StackOverflow is filled with great analysis.  With experience the official documentation will become helpful.

Footnote:  while working on this section on a different computer, I found a weird bug.  The filename for the test code used ``Hello.java``:

.. sourcecode:: java

    public class Hello {
        public static void main(String[] args) {
            System.out.print("Hello World!");
            System.out.println();
        }
    }

And the output:
    
.. sourcecode:: bash

    > javac Hello.java
    > java Hello
    Hello, World!
    >

That is very strange.  It looks like the computer is correcting my grammar by inserting a comma between ``Hello`` and ``World``.

The first step toward a solution came when I removed both files:

.. sourcecode:: bash

    > ls
    Hello.class	Hello.java
    > rm Hello.*
    > ls
    > 
    > java Hello
    Hello, World
    >

How can ``java Hello`` work?  There is no class file on the Desktop.  I infer that there must be another Hello.class somewhere on the computer, and in searching for classes the Java runtime is finding this other class first, even though the *compiler* finds this one first.  I can tell because the time stamp changes when I compile.

In Python I would look at ``sys.path`` to find the list of directories to search, but I am not sure how to do this in Java yet.  In any case, the take-home lesson is that bugs can be subtle and require inspired use of the scientific method to diagnose them.