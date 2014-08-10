.. _files:

############
Files and IO
############

Here is a working example that shows reading from and writing to files in Java, with appropriate concern for failures.  It uses an ArrayList (which we will get to in a later section), and various other objects I don't know much about at the minute.  The main reason to put it here is as a working model when you want to write code to do this.

.. sourcecode:: java

    import java.util.*;
    import java.io.BufferedReader;
    import java.io.FileReader;
    import java.io.PrintWriter;
    import java.io.IOException;

    public class IOStuff {
        public static String line;
    	public static void main(String[] args) {
    	    ArrayList<String> data = new ArrayList<String>();
            String fn = "IOStuff.java";
    		try (BufferedReader br = new BufferedReader(new FileReader(fn)))
    		{
    			while ((line = br.readLine()) != null) {
    				//System.out.println(line);
    				data.add(line);
    			}

    		} catch (IOException e) {
    			e.printStackTrace();
    		} 
            System.out.println(data.size());
            System.out.println(data.get(0));

            Date date = new Date();
            fn = "out.txt";
            try {
        	    PrintWriter writer = new PrintWriter(fn, "UTF-8");
                writer.println(date.toString());
                writer.close();
        	} catch (IOException e) {
    			e.printStackTrace();
        	}	
    	}
    }