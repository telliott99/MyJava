package mystuff;

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
