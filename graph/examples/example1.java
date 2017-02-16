import java.awt.*;
import java.applet.*;
import java.net.URL;
import graph.*;
/*************************************************************************
**
**    Applet example1
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
**    This is a simple applet that demonstrates how to use the basic features
**    of the Plotting Class library. The data is calculated locally by
**    the applet
**
*************************************************************************/

public class example1 extends Applet {

      Graph2D graph;
      DataSet data1;
      DataSet data2;
      Axis    xaxis;
      Axis    yaxis_left;
      Axis    yaxis_right;
      double data[];
      int np = 100;
      URL markerURL;


      public void init() {
        int i;
        int j;
        double data[] = new double[2*np];
/*
**      Get the passed parameters
*/

        String mfile    = getParameter("MARKERS");
/*
**      Create the Graph instance and modify the default behaviour
*/
        graph = new Graph2D();
        graph.drawzero = false;
        graph.drawgrid = false;
        graph.borderTop = 50;
/*
**      Load a file containing Marker definitions
*/
        try {
           markerURL = new URL(getDocumentBase(),mfile);
           graph.setMarkers(new Markers(markerURL));
        } catch(Exception e) {
           System.out.println("Failed to create Marker URL!");
        }


        setLayout( new BorderLayout() );
        add("Center", graph);
/*
**      Calculate the first data Set.
*/

        for(i=j=0; i<np; i++,j+=2) {
            data[j] = j-np;
            data[j+1] = 60000 * Math.pow( ((double)data[j]/(np-2) ), 2);
        }

        data1 = graph.loadDataSet(data,np);
        data1.linestyle = 0;
        data1.marker    = 1;
        data1.markerscale = 1.5;
        data1.markercolor = new Color(0,0,255);
        data1.legend(200,100,"y=6x10{^4}x^2");
        data1.legendColor(Color.black);
/*
**      Calculate the Second data Set.
*/

        for(i=j=0; i<np; i++,j+=2) {
            data[j] = j-np;
            data[j+1] = Math.pow( ((double)data[j]/(np-2) ), 3);
        }

        data2 = graph.loadDataSet(data, np);
        data2.linecolor   =  new Color(0,255,0);
        data2.marker      = 3;
        data2.markercolor = new Color(100,100,255);
        data2.legend(200,120,"y=x^3");
        data2.legendColor(Color.black);
/*
**      Attach both data sets to the Xaxis
*/
        xaxis = graph.createAxis(Axis.BOTTOM);
        xaxis.attachDataSet(data1);
        xaxis.attachDataSet(data2);
        xaxis.setTitleText("Xaxis");
        xaxis.setTitleFont(new Font("TimesRoman",Font.PLAIN,20));
        xaxis.setLabelFont(new Font("Helvetica",Font.PLAIN,15));
/*
**      Attach the first data set to the Left Axis
*/
        yaxis_left = graph.createAxis(Axis.LEFT);
        yaxis_left.attachDataSet(data1);
        yaxis_left.setTitleText("y=6x10{^4}x^2");
        yaxis_left.setTitleFont(new Font("TimesRoman",Font.PLAIN,20));
        yaxis_left.setLabelFont(new Font("Helvetica",Font.PLAIN,15));
        yaxis_left.setTitleColor( new Color(0,0,255) );
/*
**      Attach the second data set to the Right Axis
*/
        yaxis_right = graph.createAxis(Axis.RIGHT);
        yaxis_right.attachDataSet(data2);
        yaxis_right.setTitleText("y=x^3");
        yaxis_right.setTitleFont(new Font("TimesRoman",Font.PLAIN,20));
        yaxis_right.setLabelFont(new Font("Helvetica",Font.PLAIN,15));
        yaxis_right.setTitleColor(new Color(100,100,255) );
      }

}
