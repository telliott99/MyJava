.. _regex:

#####
Regex
#####

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