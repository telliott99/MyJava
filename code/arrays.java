import java.util.*;

// no standard "join" method, so roll our own
class Joiner {
    public static String join(String[] words, String sep){
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (String s:words) { 
            sb.append(s);
            count += 1;
            if (count != words.length-1) {
                sb.append(sep);
            }
        }
        return sb.toString();
    }
}

class arrays {
    public static void main(String[] args) {
        String[] C = { "a","b","c","d","e"};
        StringBuilder sb = new StringBuilder();
        
        for (String s: C) { sb.append(s); }
        System.out.println(sb.toString());
        
        boolean b;
        b = Arrays.asList(C).contains("a");
        System.out.println(b);
        
        Joiner J = new Joiner();
        System.out.println(J.join(C,"*"));
    }
}
