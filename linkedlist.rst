.. _linkedlist:

##########
LinkedList
##########

Efficiency may frequently be an issue in programming, particularly for professionals.  In this regard, a standard array presents the problem that insertions and deletions in the middle of the list are expensive, since all the elements after the insertion or deletion point must be moved to expand or contract as needed.

This is true even for an implementation like the Java ArrayList, which accomodates objects by storing pointers to the objects, which are themselves stored elsewhere.

Hence, the linked list or ``LinkedList`` type.

Here is a demo from the web.  One addition I had to make was to add the type information for the elements it contains, the original version said simply ``LinkedList ll = new LinkedList();``

.. sourcecode:: java

    import java.util.*;

    public class LinkedListDemo {

       public static void main(String args[]) {
          LinkedList<String> ll = new LinkedList<String>();
          ll.add("F");
          ll.add("B");
          ll.add("D");
          ll.add("E");
          ll.add("C");
          ll.addLast("Z");
          ll.addFirst("A");
          ll.add(1, "A2");
          System.out.println("Original contents of ll: " + ll);

          // remove elements from the linked list
          ll.remove("F");
          ll.remove(2);
          System.out.println("Contents of ll after deletion: "
           + ll);
      
          // remove first and last elements
          ll.removeFirst();
          ll.removeLast();
          System.out.println("ll after deleting first and last: "
           + ll);

          // get and set a value
          Object val = ll.get(2);
          ll.set(2, (String) val + " Changed");
          System.out.println("ll after change: " + ll);
       }
    }