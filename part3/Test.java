import java.util.*;
import java.io.*;

public class Test {
    static public void main (String[] args) {
        Properties properties = System.getProperties();
        for (Object o:properties.keySet()) {
            if (o.toString().contains("path")) {
                String p = properties.get(o).toString();
                String[] a = p.split(":");
                StdOut.println(o.toString());
                for (String s:a) {
                    StdOut.println(s);
                }
                StdOut.println("");
            }
        }
    }
}