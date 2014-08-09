.. _maps:

####
Maps
####

After the array (list or ArrayList or LinkedList), the next useful data structure is the Map (or HashMap or dictionary).  

These solve the following problem, suppose we want to know whether a particular value or object is present in a collection.  In an unsorted list, the only way to tell is to go through the whole list and examine each item.  Even in a sorted list, one must search (a binary search is most efficient).  

However, a map is much faster to search.  A "hash" of key is computed by one of several methods, and the result leads to a location where the value is found.  If there is no value corresponding to a particular key one finds out immediately.

Here is a simple example of a HashMap in Java:

.. sourcecode:: java

    import java.util.HashMap;
    import java.util.Map;

    public class Test {
        public static void main(String[] args) {
            Map<String, String> map = new HashMap<>(); 
            map.put("a","x");
            map.put("b","y");
            for (String key : map.keySet()) {
                System.out.println(key + " " + map.get(key));
            }
        }
    }

Output on the command line:

.. sourcecode:: bash

    > javac Test.java 
    > java Test
    a x
    b y
    >

A slightly more complicated example, with a dedicated function for printing (``pp``):

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

Another example:

.. sourcecode:: java

    import java.util.*;

    public class Test {
        private static String[] keysAsArray(Map<String, String> map) {
            return map.keySet().toArray(new String[map.keySet().size()]); 
        }
    
        private static List<String> keysAsList(Map<String, String> map) { 
            List<String> list = new ArrayList<String>(map.keySet());
            return list;
        }
    
        public static void main(String[] args) {
            Map<String, String> map = new HashMap<>(); 
            map.put("a","x");
            map.put("b","y");
            for (String key : map.keySet()) {
                System.out.println(key + " " + map.get(key));
            }
        
            // convert keys to array
            String[] A = keysAsArray(map); 
            for (String s : A) {
                System.out.println(s); 
            }
            // convert keys to list
            List<String> L = keysAsList(map); 
            for (String s : L) {
                System.out.println(s);
            }
        
        }
    }

As you can see from the last example, we can get the keys of a map as its ``keySet``

.. sourcecode:: java

    import java.util.*;

    public class Test {
        public static void main(String[] args) {
            Map<String, String> map = new HashMap<>(); 
            map.put("a","apple");
            map.put("b","banana");
            Set<String> S = map.keySet();
            // believe this is a set?
            System.out.println(S);
        
            // convert to array
            int sz = S.size();
            String[] A = map.keySet().toArray(new String[sz]);
            // what was fancy print method?
            // System.out.println("A: " + asString(A));

            // or to ArrayList
            List<String> list = new ArrayList<String>(S);
        
        }
    }
    