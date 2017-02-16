import java.awt.*;
import java.applet.*;
import graph.*;
/*************************************************************************
**
**    Applet example0
**                                              Version 1.0   January 1996
**
**************************************************************************
**    Copyright (C) 1996 Leigh Brookshaw
**
**    This program is free software; you can redistribute it and/or modify
**    it under the terms of the GNU General Public License as published by
**    the Free Software Foundation; either version 2 of the License, or
**    (at your option) any later version.
**
**    This program is distributed in the hope that it will be useful,
**    but WITHOUT ANY WARRANTY; without even the implied warranty of
**    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
**    GNU General Public License for more details.
**
**    You should have received a copy of the GNU General Public License
**    along with this program; if not, write to the Free Software
**    Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
**************************************************************************
**
**
*************************************************************************/

public class example0 extends Applet implements Runnable {

      Graph2D graph;
      DataSet data;
      double max;
      double min;
      int steps;
      Thread thread;

      public void init() {
        int i;
        int j;

        graph = new Graph2D();

        setLayout( new BorderLayout() );
        add("Center", graph);

        graph.framecolor = new Color(0,0,0);
        graph.borderTop = 0;
        graph.borderBottom = 1;
        graph.borderLeft = 0;
        graph.borderRight = 1;
        graph.setGraphBackground(new Color(50,50,200));

        max = 15*Math.PI;
        min = Math.PI;

        steps = 20;

        thread = new Thread(this);
        thread.start();

      }

      public void run() {
          DataSet data = null;
          int count = 1;
          int direction = 1;
          double range;
          Color c = new Color(255,255,0);
          
          while(true) {

              graph.detachDataSet(data);
              data = null;

              if(count >= steps ) direction = -1;
              else
              if(count <= 0 )     direction = 1;

              count += direction;

              range = min + (max-min)*count/steps;

//              System.out.println("Range="+range);

              data = sinx(range);

//              data.xmax =  max;
//              data.xmin = -max;
              data.ymax = 1.0;
              data.linecolor = c;              

              graph.repaint(75);

              try { thread.sleep(150); }
              catch(Exception e) { }

          }

      }



      public DataSet sinx(double range) {
            int i, j;
            int np = 100;
            double data[] = new double[2*np];
            double x, y;

            

            for(i=j=0; i<np; i++,j+=2) {
                x = (i-np/2)*range/(np/2);
                if( x == 0.0 )  y = 1.0;
                else           y = Math.sin(x)/x;
                data[j]   = x;
                data[j+1] = y;
            }
            
            return graph.loadDataSet(data,np);


      }
}
