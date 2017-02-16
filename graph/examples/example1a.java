import java.awt.*;
import java.applet.*;
import java.net.URL;
import graph.*;
/*************************************************************************
**
**    Applet example1a
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

public class example1a extends Applet {

      Graph2D graph1;
      Graph2D graph2;
      DataSet data1;
      DataSet data2;
      Axis    xaxis1;
      Axis    xaxis2;
      Axis    yaxis1;
      Axis    yaxis2;
      double data[];
      int np = 25;
      URL markersURL;
      Markers markers;
      Panel      panel;
      Label title;

      public void init() {
        int i;
        int j;
        double data[] = new double[2*np];
/*
**      Get the passed parameters
*/
        String st = getParameter("TITLE");
        String mfile    = getParameter("MARKERS");
/*
**      Create the Graph instance and modify the default behaviour
*/
        graph1 = new Graph2D();
        graph1.drawzero = false;
        graph1.drawgrid = false;
        graph1.borderRight = 0;
        graph1.setDataBackground(new Color(255,230,200));


        graph2 = new Graph2D();
        graph2.drawzero = false;
        graph2.drawgrid = false;
        graph2.borderLeft =  0;
        graph2.setDataBackground(new Color(230,230,230));
/*
**      Build the title
*/
        title = new Label(st, Label.CENTER);
        title.setFont(new Font("TimesRoman",Font.PLAIN,20));



/*
**      Load a file containing Marker definitions
*/
        try {
           markersURL = new URL(getDocumentBase(),mfile);
           markers = new Markers(markersURL);
	   /*
	   ** Lets add our own marker to the marker list
           */
           int x[] = new int[12];
           int y[] = new int[12];
           boolean d[] = new boolean[12];
           x[0]=-2; y[0]= 4; d[0]=false;
           x[1]= 2; y[1]= 4; d[1]=true;
           x[2]= 0; y[2]= 4; d[2]=false;
           x[3]= 0; y[3]=-4; d[3]=true;
           x[4]=-2; y[4]=-4; d[4]=false;
           x[5]= 2; y[5]=-4; d[5]=true;
           x[6]=-4; y[6]= 0; d[6]=false;
           x[7]= 4; y[7]= 0; d[7]=true;
           x[8]=-4; y[8]= 2; d[8]=false;
           x[9]=-4; y[9]=-2; d[9]=true;
           x[10]=4; y[10]= 2; d[10]=false;
           x[11]=4; y[11]=-2; d[11]=true;

           markers.AddMarker(9,12,d,x,y);

        } catch(Exception e) {
           System.out.println("Failed to create Marker URL!");
        }
        
 
        graph1.setMarkers(markers);
        graph2.setMarkers(markers);

        panel = new Panel();
        panel.setLayout( new GridLayout(0,2,0,0) );
        panel.add(graph1);
        panel.add(graph2);

        this.setLayout( new BorderLayout() );
        this.add("North", title);
        this.add("Center", panel);


/*
**      Calculate the first data Set.
*/

        for(i=j=0; i<np; i++,j+=2) {
            data[j] = j-np;
            data[j+1] = 60000 * Math.pow( ((double)data[j]/(np-2) ), 2);
        }

        data1 = graph1.loadDataSet(data,np);
        data1.linecolor   =  Color.red;
        data1.linestyle = 1;
        data1.marker    = 9;
        data1.markerscale = 1.0;
        data1.markercolor = new Color(0,0,255);
        data1.legend(150,75,"y=6x10{^4}x^2");
        data1.legendColor(Color.black);
/*
**      Calculate the Second data Set.
*/

        for(i=j=0; i<np; i++,j+=2) {
            data[j] = j-np;
            data[j+1] = Math.pow( ((double)data[j]/(np-2) ), 3);
        }

        data2 = graph2.loadDataSet(data, np);
        data2.linecolor   =  new Color(0,255,0);
        data2.marker      = 3;
        data2.markercolor = new Color(100,100,255);
        data2.legend(100,75,"y=x^3");
        data2.legendColor(Color.black);
/*
**      Attach data sets to the Xaxes
*/
        xaxis1 = graph1.createAxis(Axis.BOTTOM);
        xaxis1.attachDataSet(data1);
        xaxis1.setTitleText("Xaxis Left");
        xaxis1.setTitleFont(new Font("TimesRoman",Font.PLAIN,20));
        xaxis1.setLabelFont(new Font("Helvetica",Font.PLAIN,15));

        xaxis2 = graph2.createAxis(Axis.BOTTOM);
        xaxis2.attachDataSet(data2);
        xaxis2.setTitleText("Xaxis Right");
        xaxis2.setTitleFont(new Font("TimesRoman",Font.PLAIN,20));
        xaxis2.setLabelFont(new Font("Helvetica",Font.PLAIN,15));
/*
**      Attach the first data set to the Left Axis
*/
        yaxis1 = graph1.createAxis(Axis.LEFT);
        yaxis1.attachDataSet(data1);
        yaxis1.setTitleText("y=6x10{^4}x^2");
        yaxis1.setTitleFont(new Font("TimesRoman",Font.PLAIN,20));
        yaxis1.setLabelFont(new Font("Helvetica",Font.PLAIN,15));
        yaxis1.setTitleColor( new Color(0,0,255) );
        yaxis1.setTitleRotation(0);
/*
**      Attach the second data set to the Right Axis
*/
        yaxis2 = graph2.createAxis(Axis.RIGHT);
        yaxis2.attachDataSet(data2);
        yaxis2.setTitleText("y=x^3");
        yaxis2.setTitleFont(new Font("TimesRoman",Font.PLAIN,20));
        yaxis2.setLabelFont(new Font("Helvetica",Font.PLAIN,15));
        yaxis2.setTitleColor(new Color(100,100,255) );
        yaxis2.setTitleRotation(0);
      }

}
