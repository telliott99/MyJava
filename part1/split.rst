.. _split:

#####
Split
#####

Now that we know about arrays, another very common String method is ``split``, which can be used to obtain an array of lines from a long string with newlines in it, or words, or something else.  Here is a quick demo that splits on whitespace ``\\s`` (a space or a newline).  

The split method takes an optional int parameter, which tells how many values to return (not how many times to split).  Thus ``split(s,1)`` does not actually split the String ``s`` at all.

.. sourcecode:: java

    import java.util.*;
    public class Test{
        static void pp (String [] a) {
            for (int i = 0; i < a.length; i++) {
                String s = String.format("%d: ", i+1);
                System.out.println(s + a[i]);
            }
            System.out.println();
        }
        public static void main (String[] args) {
            String s = "Hello world\nagain";
            String[] a;
            \\ this gives three words
            a = s.split("\\s");
            pp(a);
            \\ this does not split at all
            a = s.split("\\s",1);
            pp(a);
            \\ this gives ["Hello","world\again"]
            a = s.split("\\s",2);
            pp(a);
            a = "one\ntwo\nthree".split("\n",2);
            pp(a);
        }
    }

.. sourcecode:: bash

    > java Test
    1: Hello
    2: world
    3: again

    1: Hello world
    again

    1: Hello
    2: world
    again

    1: one
    2: two
    three

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

More to say ..