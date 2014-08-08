.. _strings:

#######
Strings
#######

Here is a first example using the ``String`` class in Java. We initialize a new String variable ``s`` with the value ``"abc"``. 

To print out each character of the string, we obtain an array by use of the function ``split``, which generates an array (of String values).

The ``for-each`` loop uses a special syntax which assigns each value in an array to the variable ``c`` in succession, and then we print the result.

.. sourcecode:: java

    public class StringStuff {
        public static void main (String[] args) {
            String s = "abc";
            for (String c: s.split("")) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
    
We also use string concatenation when we do ``c + " "``, which puts a space after each character (and an extra one at the end which is invisible here).

From the command line:

.. sourcecode:: bash

    > javac StringStuff.java 
    > java StringStuff
    a b c 
    >

Other string functions include:

    - ``length()``
    - ``charAt(index)``
    - ``toUpperCase()``
    - ``indexOf(char)``
    - ``trim()``
    - ``replace(c1,c2)``

Add this to the previous class:

.. sourcecode:: java

    String t = s.replace("b","*");
    System.out.println(t);

.. sourcecode:: bash

    > java StringStuff
    a b c 
    a*c
    >

It's worth pointing out that the ``split`` method can take a "regular expression" to split on.  An example might be:

.. sourcecode:: java

    public class StringStuff {
        public static void main (String[] args) {
            String s = "a#b@c.d";
            for (String c: s.split("[#@\\.]")) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

.. sourcecode:: bash

    > javac StringStuff.java 
    > java StringStuff
    a b c d 
    >
    
The "pattern" we gave to be matched was ``"[#@\\.]"``.  The quotes enclose the pattern.  The brackets mean to match any one of these characters, that is ``#``, ``@`` and ``\\.``, which is just a period.  (To understand why we need the double backslash before the period, you should consult a reference on regular expressions).

    
