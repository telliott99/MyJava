.. _numbers:

#######
Numbers
#######

I don't have much to say about numbers.  If you are looking for online resources, on programming fundamentals I can recommend:

http://www.greenteapress.com/thinkapjava/html/index.html

and at a somewhat more advanced level this book by Sedgewick and Wayne is excellent.

http://introcs.cs.princeton.edu/java/home/

If you don't know the difference between say, floats and integers, you can study up on it in those references.

One thing worth mentioning is that changes from one type of a number to another may happen implicitly, if the change conserves information, but the other direction requires a cast.  Here is an example:

.. sourcecode:: java

    class Test {
        public static void main(String[] args) {
        	int i = 3;
        	System.out.println("i = " + i);
        	float f = i;
        	System.out.println("f = " + f);
        	int j = (int)f;
        	System.out.println("j = " + j);
    	
        	Integer var = new Integer(i);
        	if (var.getClass().equals(Integer.TYPE)) {
        	    System.out.println("var is an Integer");
        	}
        	else {
        	    System.out.println("var is an " + Integer.TYPE);
        	}
        }
    }

This prints:

.. sourcecode:: bash

    > javac Test.java 
    > java Test
    i = 3
    f = 3.0
    j = 3
    var is an int
    >

and I am not sure yet why the equality is not correct.

