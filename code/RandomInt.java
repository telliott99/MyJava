public class RandomInt { 
    public static void main(String[] args) { 
        int min = Integer.parseInt(args[0]);
        int max = Integer.parseInt(args[1]);
        double r = Math.random();
        // a pseudo-random integer in the range min..max
        int range = max - min;
        int n = (int) (r * range) + min;
        System.out.printf("Your random integer is: %d", n);
        System.out.println();
    }
}
