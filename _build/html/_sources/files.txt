.. _files:

##########
Files:  IO
##########


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