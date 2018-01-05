package mystuff;
import java.util.*;

class UseObj {
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
