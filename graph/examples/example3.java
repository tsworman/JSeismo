import java.awt.*;
import java.applet.*;
import java.net.URL;
import java.util.*;
import graph.*;

/*************************************************************************
**
**    Applet example3
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
**    This applet demonstrates the interactive event handling of
**    the G2Dint class
**
*************************************************************************/

public class example3 extends Applet {

      LoadData dynamic;
      G2Dint graph;
      Label title;
      DataSet data1;
      Axis    xaxis;
      Axis    yaxis;


      public void init() {
        int i;
        int j;

/*
**      Get the title of the plot and the data URL as parsed parameters
*/
        String st = getParameter("TITLE");
        String data  = getParameter("DATA");
/*
**      Instantiate the Graph class and the LoadData class
*/
        graph = new G2Dint();
        dynamic = new LoadData();

        graph.borderTop    = 30;
        graph.borderBottom = 10;
        graph.borderRight  = 40;
        graph.setDataBackground(new Color(50,50,200));
        graph.setGraphBackground(new Color(0,200,255));
/*
**      Build the title and place it at the top of the graph
*/
        graph.setFont(new Font("TimesRoman",Font.PLAIN,25));
        title = new Label(st, Label.CENTER);
        title.setFont(new Font("TimesRoman",Font.PLAIN,25));

        setLayout( new BorderLayout() );
        add("North",  title);
        add("Center", graph);
/*
**      Start a new thread and load the data
*/
        try {
        data1 = dynamic.loadDataSet(new URL(getDocumentBase(),data), graph);
        } catch (Exception e) {
          System.out.println("Failed to load data file!");
        }
/*
**      Specify the data line color
*/
        data1.linecolor = new Color(255,255,0);
/*
**      Instantiate the xaxis and attach the dataset.
*/
        xaxis = graph.createXAxis();
        xaxis.attachDataSet(data1);
        xaxis.setTitleText("Wavelength_(angstroms)");
        xaxis.setTitleColor(Color.magenta);
        xaxis.setTitleFont(new Font("TimesRoman",Font.ITALIC,25));
        xaxis.setLabelFont(new Font("Helvetica",Font.PLAIN,20));


/*
**      Instantiate the yaxis and attach the dataset.
*/
        yaxis = graph.createYAxis();
        yaxis.attachDataSet(data1);
        yaxis.setTitleText("Flux");
        yaxis.setTitleColor(Color.magenta); 
        yaxis.setTitleFont(new Font("TimesRoman",Font.ITALIC,25));
        yaxis.setLabelFont(new Font("Helvetica",Font.PLAIN,20));


      }




}




