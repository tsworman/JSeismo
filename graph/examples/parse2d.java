import java.awt.*;
import java.applet.*;

import graph.*;
/*
**************************************************************************
**
**                      Applet parse2d
**
**************************************************************************
**    Copyright (C) 1995, 1996 Leigh Brookshaw
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
*
* This applet uses the ParseFunction class to parse an input string
* and plot the result.
*
*************************************************************************/

public class parse2d extends Applet {

      Contour graph         = new Contour();   // Graph class to do the plotting
      Axis xaxis;
      Axis yaxis;
      DataSet data;

      TextField cinput     = new TextField(4);       // Number of contours 
      TextField xinput     = new TextField(4);       // X grid dimension 
      TextField yinput     = new TextField(4);       // Y grid dimension 
      TextField xmininput  = new TextField(10);      // Minimum x value input
      TextField xmaxinput  = new TextField(10);      // Maximum x value input
      TextField ymininput  = new TextField(10);      // Minimum y value input
      TextField ymaxinput  = new TextField(10);      // Maximum y value input
      TextField finput     = new TextField(40);      // Input for the function to plot
      Button plot          = new Button("Plot It!"); // Button to plot it.





      public void init() {
      	 Label title            = new Label(
                                "Function Parsing & Contouring",Label.CENTER);
      	 Panel panel            = new Panel();
         GridBagLayout gridbag  = new GridBagLayout();
         GridBagConstraints  c  = new GridBagConstraints();
         Font font              = new Font("TimesRoman",Font.PLAIN,15);

         title.setFont(new Font("TimesRoman",Font.PLAIN,25));
          
         
         setLayout(new BorderLayout() );
         add("North",title);
         add("Center",panel);

         finput.setText(getParameter("FUNCTION"));
         cinput.setText(getParameter("CONTOURS")); 
         xinput.setText(getParameter("XGRID")); 
         yinput.setText(getParameter("YGRID")); 
         xmininput.setText(getParameter("XMIN")); 
         xmaxinput.setText(getParameter("XMAX")); 
         ymininput.setText(getParameter("YMIN")); 
         ymaxinput.setText(getParameter("YMAX")); 


         panel.setLayout(gridbag);
	 /*
	 ** create labels for all the text input fields
	 */
         Label clabel    = new Label("Number of Contours");         
         Label xlabel    = new Label("X grid dimension");         
         Label ylabel    = new Label("Y grid dimension");         
         Label xminlabel = new Label("Minimum x Value");
         Label xmaxlabel = new Label("Maximum x Value");
         Label yminlabel = new Label("Minimum y Value");
         Label ymaxlabel = new Label("Maximum y Value");
         Label flabel    = new Label("Function");
         /*
	 ** Set the fonts and colors to use
	 */
         clabel.setFont(font);
         xlabel.setFont(font);
         ylabel.setFont(font);
         flabel.setFont(font);
         xminlabel.setFont(font);
         xmaxlabel.setFont(font);
         yminlabel.setFont(font);
         ymaxlabel.setFont(font);
         
         cinput.setFont(font);
         cinput.setBackground(Color.lightGray);
         xinput.setFont(font);
         xinput.setBackground(Color.lightGray);
         yinput.setFont(font);
         yinput.setBackground(Color.lightGray);
         finput.setFont(font);
         finput.setBackground(Color.lightGray);
         xmininput.setFont(font);
         xmininput.setBackground(Color.lightGray);
         xmaxinput.setFont(font);
         xmaxinput.setBackground(Color.lightGray);
         ymininput.setFont(font);
         ymininput.setBackground(Color.lightGray);
         ymaxinput.setFont(font);
         ymaxinput.setBackground(Color.lightGray);
         plot.setFont(font);
         plot.setBackground(Color.green);


	 /*
	 ** First row of the GridBag contains the plot
	 */
         c.weightx = 1.0;
         c.weighty = 1.0;
         c.gridwidth = 4;
         c.gridwidth=GridBagConstraints.REMAINDER;
         c.fill  =  GridBagConstraints.BOTH;
         
         gridbag.setConstraints(graph,c);
         

	 /*
	 ** Second row of the gridBag contains the function input and the
	 ** plotit button
	 */
         c.fill  =  GridBagConstraints.NONE;
         c.weightx=0.0;
         c.weighty=0.0;
         c.gridheight=1;
         
         c.gridwidth=1;
         c.anchor = GridBagConstraints.EAST;
         gridbag.setConstraints(flabel,c);
         
         c.anchor = GridBagConstraints.CENTER;
         c.gridwidth=GridBagConstraints.RELATIVE;
         c.fill  =  GridBagConstraints.HORIZONTAL;
         gridbag.setConstraints(finput,c);
         
         c.fill = GridBagConstraints.NONE;
         c.gridwidth=GridBagConstraints.REMAINDER;

         gridbag.setConstraints(plot,c);

	 /*
	 ** Third row contains only the Number of contours
	 */
         c.gridwidth=2;
         c.anchor = GridBagConstraints.EAST;
         gridbag.setConstraints(clabel,c);

         c.anchor = GridBagConstraints.WEST;
         c.gridwidth=GridBagConstraints.REMAINDER;
         gridbag.setConstraints(cinput,c);
         
	 /*
	 ** Fourth row contains the grid dimensions
	 */
         c.gridwidth=1;
         c.anchor = GridBagConstraints.EAST;
         gridbag.setConstraints(xlabel,c);

         c.anchor = GridBagConstraints.WEST;
         gridbag.setConstraints(xinput,c);

         c.anchor = GridBagConstraints.EAST;
         gridbag.setConstraints(ylabel,c);

         c.anchor = GridBagConstraints.WEST;
         c.gridwidth=GridBagConstraints.REMAINDER;
         gridbag.setConstraints(yinput,c);

	 /*
	 ** Fifth row contains the x range of the grid
	 */

         c.gridwidth=1;
         c.anchor = GridBagConstraints.EAST;
         gridbag.setConstraints(xminlabel,c);

         c.anchor = GridBagConstraints.WEST;
         gridbag.setConstraints(xmininput,c);
         
         c.anchor = GridBagConstraints.EAST;
         gridbag.setConstraints(xmaxlabel,c);

         c.anchor = GridBagConstraints.WEST;
         c.gridwidth=GridBagConstraints.REMAINDER;
         gridbag.setConstraints(xmaxinput,c);


	 /*
	 ** Sixth row contains the y range of the grid
	 */

         c.gridwidth=1;
         c.anchor = GridBagConstraints.EAST;
         gridbag.setConstraints(yminlabel,c);

         c.anchor = GridBagConstraints.WEST;
         gridbag.setConstraints(ymininput,c);
         
         c.anchor = GridBagConstraints.EAST;
         gridbag.setConstraints(ymaxlabel,c);

         c.anchor = GridBagConstraints.WEST;
         c.gridwidth=GridBagConstraints.REMAINDER;
         gridbag.setConstraints(ymaxinput,c);

         
         
         panel.add(graph);
         panel.add(flabel);
         panel.add(finput);
         panel.add(plot);
         panel.add(clabel);
         panel.add(cinput);
         panel.add(xlabel);
         panel.add(xinput);
         panel.add(ylabel);
         panel.add(yinput);
         panel.add(xminlabel);
         panel.add(xmininput);
         panel.add(xmaxlabel);
         panel.add(xmaxinput);
         panel.add(yminlabel);
         panel.add(ymininput);
         panel.add(ymaxlabel);
         panel.add(ymaxinput);

         
         xaxis = graph.createXAxis();
         xaxis.setTitleText("X_axis");

         yaxis = graph.createYAxis();
         yaxis.setTitleText("Y_axis");

         graph.setDataBackground(new Color(255,200,175));
         graph.setBackground(new Color(200,150,100));
         graph.setContourColor(Color.green);
         graph.setLabelledContourColor(Color.red);
         graph.setLabelLevels(3);
         graph.setDrawLabels(false);


         graph.square = true;

         plot();

      }



      void plot() {
         int levels;
         int nx;
         int ny;
         double xmax;
         double xmin;
         double ymax;
         double ymin;
         double x, y;
         int count = 0;
         boolean error = false;

         try {
              graph.setNLevels( Integer.parseInt(cinput.getText()) );
         } catch(Exception e) {
              this.showStatus("Error with number of contour levels!");
              System.out.println(
                             "Number of contour levels error "+e.getMessage());
              return;
         }

         try {
              nx   = Integer.parseInt(xinput.getText());
              ny   = Integer.parseInt(yinput.getText());

         } catch(Exception e) {
              this.showStatus("Error parsing grid dimensions!");
              System.out.println(
                             "Error parsing grid dimensions! "+e.getMessage());
              return;
         }

         try {
            xmax = Double.valueOf(xmaxinput.getText()).doubleValue();
            xmin = Double.valueOf(xmininput.getText()).doubleValue();
         } catch(Exception e) {
              this.showStatus("Error with X range!");
              System.out.println("X range error "+e.getMessage());
              return;
         }

         try {
            ymax = Double.valueOf(ymaxinput.getText()).doubleValue();
            ymin = Double.valueOf(ymininput.getText()).doubleValue();
         } catch(Exception e) {
              this.showStatus("Error with Y range!");
              System.out.println("Y range error "+e.getMessage());
              return;
         }

         if(xmin>=xmax || ymin>= ymax ) {
              this.showStatus("Error with Grid range!");
              System.out.println("grid range error! ");
              return;
         } 
         if( nx < 2 || ny < 2 ) {
              this.showStatus("Error with Grid dimensions!");
              System.out.println("grid dimension error!");
              return;
         }
             
         graph.setRange(xmin,xmax,ymin,ymax);

         this.showStatus("Parsing Function!");

         ParseFunction function = new ParseFunction(finput.getText());

         if(!function.parse()) {
              this.showStatus("Failed to parse function!");
              System.out.println("Failed to parse function!");
              return;
         }



         double d[] = new double[nx*ny];

         this.showStatus("Calculating Grid Values!");

         count = 0;
         for(int j=0; j<ny; j++) {
             y = ymin + j*(ymax-ymin)/(ny-1);
             for(int i=0; i<nx; i++) {
                x = xmin + i*(xmax-xmin)/(nx-1);
             try {
                   d[count++] = function.getResult(x,y);
             } catch(Exception e) {
                   d[count++] = 0.0;
                   error = true; }
	     }
         }
 
         if( error ) {
             this.showStatus("Error while Calculating points!"); 
             System.out.println("Error while calculating points!");
	 }

         graph.setGrid(d,nx,ny);

         this.showStatus("Calculating Contours!");

         graph.repaint();
      }



     public boolean action(Event e, Object a) {

         if(e.target instanceof Button) {
             if( plot.equals(e.target) ) {
                  plot();
                  return true;
             }
         }


         return false;
       }




}




