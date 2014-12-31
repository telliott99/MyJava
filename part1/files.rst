.. _files:

############
Files and IO
############

Reading from and writing to files is probably the most complicated topic that a beginning Java programmer will need to learn about.

Here we look at a couple of possible approaches.  The complexity comes from having an appropriate concern for possible failures.  If we have in our program (or ask the user to enter) a string that is supposed to be a filename, that file may not actually exist or there may be an error when we ask the operating system to open it for reading. 

We can deal with this in one of two ways:  "catch" the possible exception or tell the Java runtime that we don't want to bother.

In general, there are two approaches to reading files:  Scanner and BufferedReader.  Scanner is good when you want to parse the input (e.g. to read numbers), and BufferedReader is preferred when you want to read line by line.  

Here is a simple Scanner in ``main``:

.. sourcecode:: java

    import java.io.*;
    import java.util.*;

    public class UseScanner {
        public static void main(String[] args)
                throws FileNotFoundException {
            Scanner sc = new Scanner(new File("UseScanner.java"));
            int count = 0;
            while (sc.hasNext()) {
                String word = sc.next();
                count++; 
            }
            System.out.println("total words = " + count);
        }
    }

And here are two examples of BufferedReader use.  Both save the lines in an ArrayList (which we will get to in a later section).  

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
    

.. sourcecode:: bash

    javac IOStuff.java 
    > java IOStuff
    35
    import java.util.*;
    > cat out.txt
    Sat Aug 16 15:04:48 EDT 2014
    >

This second BufferedReader example uses a separate class for the reader:

.. sourcecode:: java

    import java.util.*;
    import java.io.*;

    class MyReader {
        String fn;
        MyReader (String filename) {
            fn = filename;
        }
        public static List<String> processFile(String fn) {
            List<String> lines = new ArrayList<String>();
            try {
                FileReader fr = new FileReader(fn);
                BufferedReader br = new BufferedReader(fr);
                while (true) {
                    String s = br.readLine();
                    if (s == null) break;
                    lines.add(s);
                }
            }
            catch(IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
            return lines;
        }
    }

    public class ReadIt {
        public static void main(String[] args) {
            String fn = "ReadIt.java";
            MyReader r = new MyReader(fn);
            List<String> arr = r.processFile(fn);
            for (String s: arr) {
                System.out.println(s);
            }
        }
    }


UseScanner.java

.. sourcecode:: java

    import java.io.*;
    import java.util.*;

    public class UseScanner {
        public static void main(String[] args)
                throws FileNotFoundException {
            Scanner sc = new Scanner(new File("UseScanner.java"));
            int count = 0;
            while (sc.hasNext()) {
                String word = sc.next();
                count++;
            }
            System.out.println("words: " + count);
        }
    }