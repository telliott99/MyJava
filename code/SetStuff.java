import java.util.*;

class RandomInt {
    static Random rand;
    public RandomInt(){
        rand = new Random();
    }
    int getOne(int min, int max) {
        int range = max - min + 1;
        return rand.nextInt(range) + min;
    }
}

class SetStuff {
    /*
    static int getOne(Integer N) {
        double r = Math.random(); 
        // a pseudo-random integer between 0 and N-1
        return (int) (r * N);
    }
    */
    static RandomInt ri = new RandomInt();
    public static void main(String[] args) {
        int[] A = new int[1000];
        for (int i = 0; i < 100; i++){
            A[i] = ri.getOne(0,10);
            //System.out.println(A[i]);
        }
        Set<Integer> S = new HashSet<Integer>();
        for (int i: A) {
            S.add((Integer)i);
        }
        System.out.println(S);
    }
}
