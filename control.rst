.. _control:

############
Flow Control
############

Basic flow control depends on expressions that evaluate to ``true`` or ``false`` (booleans).  For example

.. sourcecode:: java

    if (true) {
        System.out.println("Yes");
    }
    else {
        System.out.println("something is wrong");
    }
    
These next three generate the same output:
    
.. sourcecode:: java

    public class Test {
        public static void main(String[] args) {
            int N = 5;
            for (int i = 0; i < N; i++) {
                System.out.print(i + " ");
            }
            System.out.println();

            int i = 0;
            while (i < N) {
                System.out.print(i + " ");
                i += 1;
            }
            System.out.println();

            i = 0;
            while (true) {
                System.out.print(i + " ");
                i += 1;
                if (i == N) {
                    break;
                }
            }
            System.out.println();
        }
    }
    
.. sourcecode:: bash
    
    > javac Test.java
    > java Test
    0 1 2 3 4 
    0 1 2 3 4 
    0 1 2 3 4 
    >

It is interesting that, while the construct ``for (int i = 0; i < N; i++) {`` normally has an initialization statement, a boolean, and then an increment, there is some flexibility.

.. sourcecode:: java

    public class Test {
        public static void main(String[] args) {
            int N = 10;
            for (int i = 2; i < Math.pow(2,10)+1 ; i *= 2) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

.. sourcecode:: bash

    > javac Test.java
    > java Test
    2 4 8 16 32 64 128 256 512 1024 
    >

The ``elif`` construct in Python is not found in Java, use ``switch`` instead:

.. sourcecode:: java

    public class Test {
        public static void main(String[] args) {
            int N = 6;
            for (int i = 0; i < N ; i++ ) {
                int m = i % 3;
                switch(m) {
                    case(0):  System.out.println(i + " is evenly divisible");  break;
                    case(1):  System.out.println(i + " leaves 1");  break;
                    case(2):  System.out.println(i + " leaves 2");  break;
                    default:  System.out.println("whoaah..");
                }
            }
        }
    }

.. sourcecode:: bash

    > javac Test.java
    > java Test
    0 is evenly divisible
    1 leaves 1
    2 leaves 2
    3 is evenly divisible
    4 leaves 1
    5 leaves 2
    >
