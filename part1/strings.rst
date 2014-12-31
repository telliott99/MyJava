.. _strings:

#######
Strings
#######

Let's take a look at the ``String`` class. Strings are like words, a sequence of letters in a particular order.  The length of a string can be as short as zero (the empty string ``""``).  The value of a string is always given surrounded by double quotes, like this:  ``"abc"``.  This value by itself is called a string literal.

In Java, any time we declare a new variable, we must give its type, so the following code declares a new String variable, ``s``:

.. sourcecode:: java

    String s;
    
We then assign to the variable a value by doing:

.. sourcecode:: java

    s = "abc";
    
A word about variable names:  for demonstration code I prefer simple variable names, often only a single character.  For example, ``s`` is *always* a String (and perhaps ``t`` as well), and ``c`` is a char.  ``n`` and ``i`` are integers (``i`` is usually for indexing or counting our way through a loop). ``A`` is an array, ``M`` is a map and ``fn`` a filename, and so on.

For real code it can be valuable to give longer and more descriptive names to variables, to make clear what the function of each is.  However, I find this a distraction for code "snippets", so I generally won't do that here.

Combine declaration and initialization of a new String variable ``s`` with the value ``"abc"`` by writing:

.. sourcecode:: java

    String s = "abc";

To print the value of ``s``:

.. sourcecode:: java

    public class Test {
        public static void main (String[] args) {
            String s = "abc";
            System.out.println(s);
        }
    }
    
Output:

.. sourcecode:: bash

    > javac Test.java 
    > java Test
    abc
    > 

Strings know their length.  Add the line 

.. sourcecode:: java

    System.out.println(s.length());
    
Output:

.. sourcecode:: bash

    > javac Test.java 
    > java Test
    abc
    3
    >

We can iterate through each character as follows:

.. sourcecode:: java

    public class Test {
        public static void main (String[] args) {
            String s = "abc";
            for (int i = 0; i < s.length(); i++) {
                System.out.print(s.charAt(i) + " ");
            }
            System.out.println();
        }
    }

Output:

.. sourcecode:: bash

    > javac Test.java 
    > java Test
    a b c 
    >

The code ``c + " "`` is a string *concatenation*;  this places a space after each character.  We got all the output on one line because we used ``System.out.print`` (rather than ``println``) inside the loop.

For another approach to printing out each character of a string, we could also first obtain an array by use of the function ``split`` (more about arrays later).  This generates an array of Strings of length 1 each.

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

The ``for-each`` loop uses a special syntax which uses a single variable (here, the String ``c``), and assigns each value in an array to that variable in succession.  We then print the result.
    
From the command line:

.. sourcecode:: bash

    > javac StringStuff.java 
    > java StringStuff
    a b c 
    >

There are many string functions.  Some of the most important ones are:

    - ``charAt(index)``
    - ``contains(substring)``
    - ``indexOf(substring)``
    - ``lastIndexOf(substring,index)``
    - ``length()``
    - ``replace(c1,c2)``
    - ``split(substring)``
    - ``startsWith(prefix)``
    - ``substring(start,stop)``
    - ``toUpperCase()``, ``toLowerCase()``
    - ``trim()``
    
Strings are immutable, they can't be changed.  You can't do ``s[index] = new_value``.  In fact, the ``[index]`` notation only works with arrays.

So, if we call a function to "change" a string, it generates a new string with the desired change and returns it to the caller.  The function ``"aBC".toLowerCase()`` will return the String ``"abc"``.

There are other functions to deal with Unicode, but that's a more advanced topic I would rather evade at the moment.

Let's look at ``replace``.  Add this to the previous class:

.. sourcecode:: java

    String t = s.replace("b","*");
    System.out.println(t);

.. sourcecode:: bash

    > java StringStuff
    a b c 
    a*c
    >

Here is an example that shows two ways of constructing a ``char`` type and then converting the result to be a string.  Also use ``==`` to compare characters but ``equals`` to compare Strings.  (``==`` means "is exactly the same object").

.. sourcecode:: java

    import java.util.*;
    class X {
        public static void main(String[] args) {
            char c1 = 'A';
            char c2 = (char) 65;
            System.out.println(c2);
            if (c1 == c2) {
                System.out.println("Yes");
            }
            String s = Character.toString(c1);
            String t = new String(new char[]{c2});
            if (s.equals(t)) {
                System.out.println("Yes");
            }
        }
    }