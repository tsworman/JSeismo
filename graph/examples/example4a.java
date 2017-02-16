import java.awt.*;
import java.applet.*;
import java.net.URL;
import java.util.*;
import graph.*;
/*************************************************************************
**
**    Applet example4a
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
**    This is a simple applet that demonstrates how to use the TextLine
**    Class
**
*************************************************************************/

public class example4a extends Applet {

      RTextLine text = new RTextLine();


      public void init() {
        text.setDrawingComponent(this);
      }

      public void paint(Graphics g) {
           drawtext(g);
      }

      public void update(Graphics g) {
           drawtext(g);
      }

      public void drawtext(Graphics g) {
           Rectangle r = bounds();
           int x = r.x+r.width/2;
           int y = r.y;

           text.setFont(new Font("TimesRoman",Font.PLAIN,20));


           text.setColor( new Color(0,200,0) );
           text.setText("Examples of the Rotated TextLine Class");
           y += 2*text.getHeight(g);
           text.draw(g,x,y,TextLine.CENTER);

           text.setColor(Color.magenta);
           text.setText("Rotated 90 Degrees");
           text.setRotation(90);
           text.draw(g,x+text.getRWidth(g),
                          y+text.getRHeight(g),TextLine.LEFT);
           text.setRotation(0);
	   
           text.setColor(Color.magenta);
           text.setText("Rotated -90 Degrees");
           text.setRotation(-90);
           text.draw(g,x+3*text.getRWidth(g),
                          y+text.getRHeight(g),TextLine.LEFT);

           y += text.getRHeight(g)+text.getHeight(g);

           text.setColor(Color.magenta);
           text.setText("Rotated 180 Degrees");
           text.setRotation(180);
           text.draw(g,x,
                          y+text.getRHeight(g),TextLine.LEFT);

	   
      }

}




