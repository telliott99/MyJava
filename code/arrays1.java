import java.util.*;

class arrays {
    public static void main(String[] args) {
        int[] A = { 1, 2, 3, 4, 5 };
               
        // for-each
        for (int i: A) {
            System.out.printf("%d ", i);
        }
        System.out.println();
        System.out.println(Arrays.toString(A));
                
        // using an index
        System.out.print("A:  ");
        for (int i = 0; i < A.length; i++) {
            if (i < A.length - 1) {
                System.out.printf("%d: %d, ", i, A[i]);
            }
            else {
                System.out.printf("%d: %d.", i, A[i]);
             }
        }
        System.out.println();
    }
}
