.. _map:

###
Map
###

After the array or ArrayList or LinkedList, the next most useful data structure is the HashMap (called a dictionary in Python).  We can talk generally about this structure as a map, implemented by use of a hash (like md5 or sha256).

http://en.wikipedia.org/wiki/MD5

A map associates keys with values, just like association between a word and its definition contained in a dictionary.

One important application of maps is to quickly determine whether a particular value or object is present in a collection, which may have millions of items.  In an unsorted list, the only way to answer this question is to go through the whole list and examine each item.  Even in a sorted list, one must search.  A binary search with a sorted list is most efficient.  However, keeping a list sorted is a lot of work when items are being added all the time. 

A map works by associating an index into an array with each key, so that given the key the implementation can go quickly to that index and retrieve the value.  The HashMap does this by computing a "hash" of the key and using the hash to find the index.  If there is no value corresponding to a particular key one finds out immediately.  

It is possible to have "collisions" where two different keys have the same hash and the same index, and in fact efficient use of memory requires a tradeoff in this respect.  Collisions are dealt with by storing those values in a linked list.

Here is a simple example of a HashMap in Java:

.. sourcecode:: java

    import java.util.*;

      public class Test {
          public static void main(String[] args){
              Map<String,String> m = new HashMap<String,String>();
              m.put("c","cookie");
              m.put("e","elmo");
              System.out.println(m.get("c"));
              for (String k : m.keySet()){
                  System.out.println(k + " is for " + m.get(k));
              }
              System.out.println();
              System.out.println("keySet: " + m.keySet());
              System.out.println("values: " + m.values());
              System.out.println("entries: " + m.entrySet());
          }
      }
    
Output on the command line:

.. sourcecode:: bash

    > javac Test.java 
    > java Test
    cookie
    e is for elmo
    c is for cookie

    keySet: [e, c]
    values: [elmo, cookie]
    entries: [e=elmo, c=cookie]
    >
    
Note that the keys are not in sorted order, nor are they in the order we input them into the map.
    
Map and HashMap are defined in ``java.util`` and one could directly import just those names if you wanted.  The methods to add new key, value pairs are ``put`` and ``get``.  To get all the keys or all the values or both at once we call the methods ``keySet()`` and ``values()`` as shown.  

The resulting collections are sets, as suggested by ``keySet()``.  They can be iterated as shown in the next bit of code.  If we add this:

.. sourcecode:: java

    for (Map.Entry<String,String> e : m.entrySet()){
        System.out.println(e.getKey());
        System.out.println(e.getValue());
        System.out.println(e.hashCode());
        e.setValue("blank");
        System.out.println();
    }
    System.out.println("entries: " + m.entrySet());

You should see this:

.. sourcecode:: bash

    c
    cookie
    -1354757625

    e
    elmo
    3116268

    entries: [c=blank, e=blank]
    >

A slightly more complicated example, with a dedicated function for printing (``prettyprint``).  Also, note the use of an iterator to go through the keys:

.. sourcecode:: java

    import java.util.*;

    class MapStuff {
        public static void prettyprint(HashMap<String,Integer> map){
            for (String k: map.keySet()) {
                System.out.printf(k + " " + map.get(k) + " ");
            }
            System.out.println();    
        }

        public static void main(String[] args) {
            HashMap<String,Integer> map = new HashMap<String,Integer>();
            map.put("a", 1);
            prettyprint(map);    
            map.put("b",2);
            prettyprint(map);
            String s = "abcdef";
            String[] letters = s.split("");
            Integer counter = 0;
            for (String c: letters) {
                counter += 1;
                map.put(c, counter); 
            }
            System.out.println("Size of Map: " + map.size());
            // another way to iterate
            Iterator<String> it = map.keySet().iterator();
            while(it.hasNext()){
                String key = it.next();
                System.out.printf("k: %s, v: %d \n", key, map.get(key));
            }
            map.remove("a");
            System.out.println(map.containsKey("j"));
        }
    }

Last example:

.. sourcecode:: java

    import java.util.*;

    public class Test {
        public static void main(String[] args) {
            Map<String, String> map = new HashMap<>(); 
            map.put("a","apple");
            map.put("b","banana");
            Set<String> S = map.keySet();
            System.out.println("keys: " + S);

            // convert to array
            int sz = S.size();
            String[] A = map.keySet().toArray(new String[sz]);
            System.out.println("A: " + Arrays.toString(A));

            // convert to ArrayList
            List<String> L = new ArrayList<String>(S);
            System.out.println("L: " + L);
        }
    }

.. sourcecode:: bash

    > javac Test.java
    > java Test
    keys: [a, b]
    A: [a, b]
    L: [a, b]
    >
    