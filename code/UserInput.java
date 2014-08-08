import java.util.*;
import java.io.IOException;

public class UserInput {

    public static void main(String args[]) throws IOException {
      
        System.out.println("Please enter your name: ");
        Scanner inputReader = new Scanner(System.in);
       
        //Getting input in String format
        String name = inputReader.nextLine();
        System.out.println("Hi " + name);
      
        //Getting number as input from command line in Java
        System.out.println("Please enter a number: ");
        int number = inputReader.nextInt();
        System.out.println("You have entered : " + number);
      
        //Getting floating point as input from command line in Java
        System.out.println("Please enter a floating point number: ");
        float decimal = inputReader.nextFloat();
        System.out.println("You have entered : " + decimal);
      
    }
    
}
