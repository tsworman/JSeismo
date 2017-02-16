import java.awt.*;
import java.applet.*;
import java.net.URL;
import graph.*;
/*************************************************************************
**
**    Applet example1b
**                                              Version 1.0   September 1996
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
**    This is a simple applet that demonstrates how to use the basic features
**    of the Plotting Class library. The data is vector data calculated 
**    locally by the applet
**
*************************************************************************/

public class example1b extends Applet {

      G2Dint graph1;
      VectorSet data1;
      Axis    xaxis1;
      Axis    yaxis1;
      double data[];
      int npx = 20;
      int npy = 20;
      Label title;

      public void init() {
        int i;
        int j;
        int count;
        int kount;
        double x, y;
        double data[] = new double[4*npx*npy];
/*
**      Get the passed parameters
*/
        String st = getParameter("TITLE");
/*
**      Create the Graph instance and modify the default behaviour
*/
        graph1 = new G2Dint();
//        graph1.square = true;
        graph1.drawzero = false;
        graph1.drawgrid = false;
        graph1.setDataBackground(new Color(255,230,200));

/*
**      Build the title
*/
        title = new Label(st, Label.CENTER);
        title.setFont(new Font("TimesRoman",Font.PLAIN,20));


        this.setLayout( new BorderLayout() );
        this.add("North", title);
        this.add("Center", graph1);


/*
**      Calculate the Vector Set.
*/
        count = 0;
        kount = 0;

        for(j=0; j<npy; j++) {
           y = -1.0 + j*2.0/((double)(npy-1));

           for(i=0; i<npx; i++) {
              x = -1.0 + i*2.0/((double)(npx-1));
        
              if( x*x + y*y <= 1 ) {
                 kount++;
                 
                 data[count++] = x;
                 data[count++] = y;
                 data[count++] = x;
                 data[count++] = y;

              }
           }
        }

        try {
              data1 = new VectorSet(data,kount);
        } catch(Exception e) {
	  System.out.println("Failed to load Vector Set!");
        }

	/*
	** Set up the data
	*/
        graph1.borderTop = 50;
        graph1.borderRight = 40;
        data1.linecolor   =  Color.red;
        data1.legend("Standard Vector");
/*
**      Attach the data set to the graph
*/
        graph1.attachDataSet(data1);

/*
**      Attach data set to the Xaxes
*/
        xaxis1 = graph1.createXAxis();
        xaxis1.attachDataSet(data1);
        xaxis1.setTitleText("Xaxis");
        xaxis1.setTitleFont(new Font("TimesRoman",Font.PLAIN,20));
        xaxis1.setLabelFont(new Font("Helvetica",Font.PLAIN,15));

/*
**      Attach the data set to the YAxis
*/
        yaxis1 = graph1.createYAxis();
        yaxis1.attachDataSet(data1);
        yaxis1.setTitleText("Yaxis");
        yaxis1.setTitleFont(new Font("TimesRoman",Font.PLAIN,20));
        yaxis1.setLabelFont(new Font("Helvetica",Font.PLAIN,15));


      }




}
