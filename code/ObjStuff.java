import java.util.*;

class Obj implements Comparable<Obj> {
    String name;
    // counter for total number of objects
    static int count = 0;
    public Obj(String s){ 
        name = s;
        count += 1;
    }
    public String toString() { 
        return String.format("%d: %s", count, name);
    }
    public int compareTo(Obj o) {
        return name.compareTo(o.name);
    }
}

class SortStuff {
    public static void main(String[] args) {
        Obj o1 = new Obj("Tom");
        System.out.println(o1);
        Obj o2 = new Obj("Joan");
        System.out.println(o1);
        Obj o3 = new Obj("Sean");
        ArrayList<Obj> A = new ArrayList<Obj>();
        for (Obj o: Arrays.asList(o1,o2,o3)){
            A.add(o);
        }
        System.out.println("unsorted:");
        for (Obj o:A) { System.out.println(o); }
        System.out.println("sorted:  ");
        Collections.sort(A);
        for (Obj o:A) { System.out.println(o); }
    }
}
