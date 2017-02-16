import java.awt.*;
import java.applet.*;

import graph.*;

public class parser extends Applet {


    public static void main( String arg[] ) {
        int l = arg.length;
        double d;

        if( l < 1 || l > 4 ) {
          System.out.println(
           "Usage: java parser \"function\" [x value] [y value] [z value]");
          return;
        }

        System.out.println("Parse Function: "+arg[0]);

        ParseFunction function = new ParseFunction(arg[0]);


        if( l >= 2 ) {
                      d = Double.valueOf(arg[1]).doubleValue();
                      System.out.println("x = "+d);
                      function.setX(d);
        }
        if( l >= 3 ) {
                      d = Double.valueOf(arg[2]).doubleValue();
                      System.out.println("y = "+d);
                      function.setY(d);
        }
        if( l >= 4 ) {
                      d = Double.valueOf(arg[3]).doubleValue();
                      System.out.println("z = "+d);
                      function.setZ(d);
        }

        function.debug = true;

        if( !function.parse() ) {
          System.out.println("Error: Failed to parse function");
          return;
        }

        try {
             System.out.println("Solution: "+function.getResult());
        } catch(Exception e) {
             e.printStackTrace();
        }

    }


}




