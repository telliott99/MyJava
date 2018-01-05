public class StringStuff {
    public static void main (String[] args) {
        String s = "abc";
        for (String c:s.split("")) {
            System.out.print(c + " ");
        }
        System.out.println();
        String t = s.replace("b","*");
        System.out.println(t);
    }
}
