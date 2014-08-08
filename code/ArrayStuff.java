import java.util.*;

class ArrayStuff {
    // iterator
    public static void pp(ArrayList<String> A) {
        Iterator<String> it = A.iterator();
        while(it.hasNext()) { 
            System.out.print(it.next()); 
        }
        System.out.println();        
    }
    
    public static void main(String[] args) {
        // ArrayList
        ArrayList<String> C = new ArrayList<String>() {{ 
            add("z");
            add("y");
            add("x"); }};
        // iterator
        pp(C);
        Collections.sort(C);
        pp(C);
        
        ArrayList<String> D = new ArrayList<String>();
        for (String s: Arrays.asList("j","k","l")) {
            D.add(s);
        }
        pp(D);
    }
}
