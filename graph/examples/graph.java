import java.awt.*;
import java.applet.*;
import java.net.URL;
import java.util.*;
import java.io.InputStream;
import graph.*;
/*************************************************************************
**
**    Applet graph
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
**    This applet uses the BuildGraph class to construct a plot from
**    description file. This applet is general enough to be used for
**    many sorts of plots.
**
*************************************************************************/

public class graph extends Applet {

      BuildGraph build;
      InputStream in;
      URL url;
      Graph2D graph;
      TextLine title;
      Label label = new Label();

      public void init() {

        setLayout( new BorderLayout() );

        String data  = getParameter("DATA");
        try {
              url = new URL(getDocumentBase(),data);
	    }
        catch(Exception e) {
              System.out.println("Failed to construct URL from data name");
              showStatus("Applet: Failed to construct URL from data name");
              return;
	    }

        try {
              in = url.openStream();
	    }
        catch(Exception e) {
              System.out.println("Failed to open Data Stream");
              showStatus("Applet: Failed to open Data Stream");
              return;
	    }
        
        build = new BuildGraph(in,this);

        showStatus("Applet: Started building graph!");

        build.parse();

        graph = (Graph2D)(build.getGraph());

        if(graph != null) add("Center",graph);

        title = build.getGraphTitle();

        if(title != null && !title.isNull()) {
              label.setText(title.getText());
              label.setAlignment(Label.CENTER);
              if( title.getFont() != null ) {
                 label.setFont(title.getFont());
	       }
              if( title.getColor() != null ) {
                 label.setForeground(title.getColor());
	       }

              add("North",label);
	}


      }

}




