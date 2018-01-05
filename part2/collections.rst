.. _collections:

###########
Collections
###########

ArrayLists, LinkedLists, Maps and Sets are all collections.  Conversions between different collections are common tasks.  Here are some conversions to ArrayList.  We start with two standard construction methods, then show three conversions.

.. sourcecode:: java

    import java.util.*;

    class Test {
        public static void main(String[] args) {
            List<String> A = new ArrayList<String>() {{
                add("a");
                add("b");
            }};
            System.out.println("A: " + A);
        
            A = new ArrayList<String>();
            A.add("a");
            A.add("b");
            System.out.println("A: " + A);
        
            String[] a = {"a","b"};
            A = Arrays.asList(a);
            System.out.println("A: " + A);
        
            Map<String,String> m = new HashMap<>();
            m.put("a","apple");
            m.put("b","banana");
            A = new ArrayList<String>(m.keySet());
            System.out.println("A: " + A);
        
            Set<String> set = new HashSet<>(); 
            set.add("a");
            set.add("b"); 
            A = new ArrayList<String>(set);
        }
    }

All of these print statements have the same output:

.. sourcecode:: bash

    > javac Test.java
    > java Test
    A: [a, b]
    A: [a, b]
    A: [a, b]
    A: [a, b]
    >

Constructing a HashMap from two lists:  unfortunately there is no equivalent to Python's ``dict(zip(list1,list2)))``.

.. sourcecode:: java

    import java.util.HashMap;

    class Test {
        public static void main(String[] args) {
        	String[] keys = {"a", "b", "c"};
        	int[] vals = {1, 2, 3};
        	Map<String, Integer> m = new HashMap<>();
        	for(int i= 0; i < keys.length; i++){
        	   m.put(keys[i], vals[i]);
            }
            System.out.println("m: " + m.entrySet());
        }
    }

.. sourcecode:: bash

    > java Test
    m: [a=1, b=2, c=3]
    > 

Notice that the HashMap must contain Java objects, and thus it contains Integer values rather than just plain ints.  Since we used an array ``int[] vals``, there is a conversion from ``int`` to ``Integer`` that happens silently when we do ``m.put`` in the loop.