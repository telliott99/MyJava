.. _strings:

#######
Strings
#######

Let's take a look at the ``String`` class in Java. We combine declaration and initialization of a new String variable ``s`` with the value ``"abc"`` by writing:

.. sourcecode:: java

    String s = "abc";

To print out each character of the string, first we obtain an array by use of the function ``split``.  This generates an array of Strings of length 1 each.

The ``for-each`` loop uses a special syntax which assigns each value in an array to a variable in succession (here, the String ``c``), and then the result is printed.

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
    
The code ``c + " "`` is a string *concatenation*;  this places a space after each character.

From the command line:

.. sourcecode:: bash

    > javac StringStuff.java 
    > java StringStuff
    a b c 
    >

To examine each character of a String, one could also convert the String to an array of ``char``, something like this:

.. sourcecode:: bash

    for (char c: s.toCharArray()) {
        System.out.println(c);
         }
    }
    
Or

.. sourcecode:: java

    for (int i = 0;i < s.length(); i++){
        System.out.println(s.charAt(i));
    }

There are many other string functions including:

    - ``length()``
    - ``charAt(index)``
    - ``substring(start,stop)``
    - ``contains(sequence)``
    - ``startsWith(prefix)``
    - ``toUpperCase()``
    - ``indexOf(string)``
    - ``lastIndexOf(string,index)``
    - ``trim()``
    - ``replace(c1,c2)``
    
Remember that strings are immutable, they can't be changed.  So, if we call a function to "change" a string, it generates a new string with the desired change and returns it to the caller.  The function ``"  abc".trim()`` will return the String ``"abc"``.

There are other functions to deal with Unicode, but that is a more advanced topic I would rather evade at the moment.

Let's look at ``replace``.  Add this to the previous class:

.. sourcecode:: java

    String t = s.replace("b","*");
    System.out.println(t);

.. sourcecode:: bash

    > java StringStuff
    a b c 
    a*c
    >

The ``split`` method takes a "regular expression" to split on (though a single character or a String will also work).  An example might be:

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
    
The "pattern" we gave to be matched was ``"[#@\\.]"``.  The quotes enclose the pattern.  The brackets mean *match any one of these characters*, i.e. ``#``, ``@`` or ``\\.``.  The last of these, ``\\.``, is really just a period.  (To understand why we need the double backslash before the period, you should consult a reference on regular expressions).

    
