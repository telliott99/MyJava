import java.util.*;

public class Args2 {
    public static void main (String[] args) {
        int firstArg;
        String msg;
        if (args.length > 0) {
            try {
                firstArg = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                msg = "Argument: " + args[0] + " must be an integer.";
                System.err.println(msg);
                System.exit(1);
            }
        }
    }
}
