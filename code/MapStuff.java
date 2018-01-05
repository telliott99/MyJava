import java.util.*;

class MapStuff {
    public static void pp(HashMap<String,Integer> map){
        for (String k: map.keySet()) {
            System.out.printf(k + " " + map.get(k) + " ");
        }
        System.out.println();    
    }

    public static void main(String[] args) {
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        map.put("a", 1);
        pp(map);    
        map.put("b",2);
        pp(map);
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
