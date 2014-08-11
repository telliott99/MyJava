.. _graphics:

########
Graphics
########

One reason for the use of Java 

.. sourcecode:: java

    import java.awt.*;
    import javax.swing.*;

    public class BasicFrame extends JFrame{
        public BasicFrame(){
            super();
        }
        public void paint(Graphics g){
            // Draw a line from (10,10) to (150,150)
            g.drawLine(50,50,150,150); 
        }

        public static void main(String arg[]){
            BasicFrame frame = new BasicFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(200,200);
            frame.setVisible(true);
      }
    }
    
If you run this in the usual way, it should look like this:



