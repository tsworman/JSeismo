import java.awt.*;
import java.applet.*;
import java.net.URL;
import java.util.*;
import graph.*;
/*************************************************************************
**
**    Applet example4
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
**    This is a simple applet that demonstrates how to use the TextLine
**    Class
**
*************************************************************************/

public class example4 extends Applet {

      TextLine text = new TextLine();


      public void init() {
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
           text.setText("Examples of the TextLine Class");
           y += text.getHeight(g);
           text.draw(g,x,y,TextLine.CENTER);


           text.setColor(Color.black);
           text.setText("y=x$^2 ");
           y += 1.5*text.getHeight(g);
           text.draw(g,x,y,TextLine.RIGHT);

           text.setColor(Color.magenta);
           text.setText(" y=x^2");
           text.draw(g,x,y,TextLine.LEFT);



           text.setColor(Color.black);
           text.setText("ln(y${$^x$_i$}) ");
           y += 1.5*text.getHeight(g);
           text.draw(g,x,y,TextLine.RIGHT);

           text.setColor(Color.magenta);
           text.setText(" ln(y{^x_i})");
           text.draw(g,x,y,TextLine.LEFT);


           text.setColor(Color.black);
           text.setText("text ${$_subscripted$} or ${$^superscripted$} ");
           y += 1.5*text.getHeight(g);
           text.draw(g,x,y,TextLine.RIGHT);

           text.setColor(Color.magenta);
           text.setBackground(Color.black);
           text.setText(" text {_subscripted} or {^superscripted}");
           text.draw(g,x,y,TextLine.LEFT);
           text.setBackground(null);


           text.setColor(Color.black);
           text.setText("ln(y${$^x$_i$})$^cos(z) ");
           y += 1.5*text.getHeight(g);
           text.draw(g,x,y,TextLine.RIGHT);

           text.setColor(Color.magenta);
           text.setText(" ln(y{^x_i})^cos(z) ");
           text.draw(g,x,y,TextLine.LEFT);


           text.setColor(Color.black);
           text.setText("A${$^b$}${$_c$}D ");
           y += 1.5*text.getHeight(g);
           text.draw(g,x,y,TextLine.RIGHT);

           text.setColor(Color.magenta);
           text.setText(" A{^b}{_c}D");
           text.draw(g,x,y,TextLine.LEFT);

           text.setColor( new Color(0,200,0) );
           text.setText("Examples of the TextLine.parseDouble() method");
           y += 2.0*text.getHeight(g);
           text.draw(g,x,y,TextLine.CENTER);

           text.setColor(Color.black);
           text.setText("0.000103452789  ");
           y += 1.5*text.getHeight(g);
           text.draw(g,x,y,TextLine.RIGHT);

           text.setColor(Color.magenta);
           text.parseDouble(0.000103452789,7,5,TextLine.ALGEBRAIC);
           text.draw(g,x,y,TextLine.LEFT);

           text.setColor(Color.black);
           text.setText("1.03452789E10  ");
           y += 1.5*text.getHeight(g);
           text.draw(g,x,y,TextLine.RIGHT);

           text.setColor(Color.magenta);
           text.setBackground(Color.black);
           text.parseDouble(1.03452789e10,4,3,TextLine.ALGEBRAIC);
           text.draw(g,x,y,TextLine.LEFT);
           text.setBackground(null);

           text.setColor(Color.black);
           text.setText("-1.03452789E10  ");
           y += 1.5*text.getHeight(g);
           text.draw(g,x,y,TextLine.RIGHT);

           text.setColor(Color.magenta);
           text.setBackground(Color.black);
           text.parseDouble(-1.03452789e10,5,5,TextLine.ALGEBRAIC);
           text.draw(g,x,y,TextLine.LEFT);
           text.setBackground(null);

           text.setColor(Color.black);
           text.setText("-1.03452789E7  ");
           y += 1.5*text.getHeight(g);
           text.draw(g,x,y,TextLine.RIGHT);

           text.setColor(Color.magenta);
           text.setBackground(Color.black);
           text.parseDouble(-1.03452789e7,6,6,TextLine.SCIENTIFIC);
           text.draw(g,x,y,TextLine.LEFT);
           text.setBackground(null);
      }

}




