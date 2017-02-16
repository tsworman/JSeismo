/**********************************************************
 * Sima Java Client 2.0
 * By: Tyler Worman
 * About: Connects to SIMA Servers and Graphs their data
 * References and much thanks to the publishers of the
 * following websites:
 * 
 * http://www.rgagnon.com/javadetails/java-0024.html
 * http://www.rgagnon.com/javadetails/java-0004.html
 * http://www.sci.usq.edu.au/staff/leighb/graph/
 * http://www.mindprod.com/jgloss/endian.html
 */

import java.awt.*;
import graph.*;
import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.text.*;
import javax.swing.*;

public class jClient extends javax.swing.JFrame {
    Socket sock;
    private OutputStream remoteOut;
    //Options
    Properties settings;
    String hostname;
    int port;
    int numTraces;
    String inifile;
    boolean cLogging;
    boolean logging;

    //Graph Items
    Graph2D graph;
    DataSet data[];
    Axis    yaxis_right;
    int maximum        = 25;
    //End Graph Items

    //Window Items
    private javax.swing.JPanel optionsPanel;
    private javax.swing.JPanel graphPanel;
    private javax.swing.JPanel mainPanel;
    /**
       public static void main(String args[]) {
       Starts the program and connects
    */
    public static void main(String args[]) {
	if (args.length < 1) {
	    System.out.println("No Settings file specified");
	    System.exit(1);
	}
        new jClient(args[0]).setVisible(true);
    }

    /**
       public jClient()
       Called when the jClient is instantiated
    */
    public jClient(String inifile) {
	//Read in the ini
	settings = new Properties();
	try {
	    settings.load(new FileInputStream(inifile));
	} catch (Exception exp) {
	    System.out.println("Specified settings file can't be found.");
	    System.exit(1);
	}
	numTraces = Integer.valueOf(settings.getProperty("TraceNUM")).intValue();
	hostname = settings.getProperty("Server");
	port = Integer.valueOf(settings.getProperty("Port")).intValue();
// 	if(settings.getProperty("Logging").matches("1")) {
// 	    logging = true;
// 	} else {
// 	    logging = false;
// 	}
// 	if(settings.getProperty("ConsoleLogging").matches("1")) {
// 	    cLogging = true;
// 	} else {
// 	    cLogging = false;
// 	}
	//Setup all array's
	data = new DataSet[numTraces];
	for(int i =0; i < numTraces; i++) {
	    data[i] = new DataSet();
	}
	//Start the monitor window.
	setupWindow();
       	graphIt();
	clientConnect();
    }

    public void graphIt() {
        graph = new Graph2D();
        graph.zerocolor = new Color(0,255,0);
        graph.borderTop    = 10;
        graph.borderBottom = 10;
        graph.setDataBackground(Color.black);
        graphPanel.add("Center", graph);
        yaxis_right = graph.createAxis(Axis.RIGHT);
        yaxis_right.setTitleText("Seismometer Voltage");
	//yaxis_right.setLabelFont(new Font("Helvetica",Font.PLAIN,20));
	for (int i = 0; i < numTraces; i++) {
	    int tempR = Integer.valueOf(settings.getProperty("TraceColor" + i + "R")).intValue();
	    int tempG = Integer.valueOf(settings.getProperty("TraceColor" + i + "G")).intValue();
	    int tempB = Integer.valueOf(settings.getProperty("TraceColor" + i + "B")).intValue();
	    data[i].linecolor = new Color(tempR,tempG,tempB);
	    data[i].marker = 1;
	    data[i].markercolor = new Color(100,100,255);
	    yaxis_right.attachDataSet(data[i]);
	    graph.attachDataSet(data[i]);
	}
    }
     
    /**
       public void setupWindow()
       Sets up the window
    */
    public void setupWindow() {
	optionsPanel = new javax.swing.JPanel();
	graphPanel = new javax.swing.JPanel();
	mainPanel = new javax.swing.JPanel();
	mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
	mainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
	mainPanel.add(Box.createRigidArea(new Dimension(0,5)));
	mainPanel.add(optionsPanel);
	mainPanel.add(Box.createRigidArea(new Dimension(0,5)));
	mainPanel.add(graphPanel);
	setContentPane(mainPanel);
	setLocationRelativeTo(null);
	setTitle("Sima Version 2.0");
	addWindowListener(new java.awt.event.WindowAdapter() {
		public void windowClosing(java.awt.event.WindowEvent evt) {
		    System.exit(0);
		}
	    });
	//Add in zooming controls
	optionsPanel.setBackground(new java.awt.Color(0,0,0));
	optionsPanel.setForeground(new java.awt.Color(255,255,0));
	optionsPanel.setBorder(new javax.swing.border.TitledBorder(null, "Graph Controls", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog",0,11), new java.awt.Color(255,255,255)));
	optionsPanel.setToolTipText("This makes the graph do fun stuff!");
	graphPanel.setBackground(new java.awt.Color(0,0,0));
	graphPanel.setForeground(new java.awt.Color(255,255,0));
	graphPanel.setBorder(new javax.swing.border.TitledBorder(null, "Graph", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog",0,11), new java.awt.Color(255,255,255)));
	graphPanel.setLayout(new BorderLayout());
	graphPanel.setToolTipText("The Graph");
	pack();
        //Figure out window size and placement                                  
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setSize(new java.awt.Dimension(740, 310));
        setLocation((screenSize.width-740)/2,(screenSize.height-310)/2);
    }
   
    /**
       public void clientConnect()
       Initiates the connection to the server;
    */
    public void clientConnect() {
	try {
	    InetAddress servAddr = InetAddress.getByName(hostname);
	    sock = new Socket(servAddr.getHostName(), port);
	    remoteOut = new DataOutputStream(sock.getOutputStream());
	    System.out.println("Connected to the Server " + servAddr.getHostName() + " on port " + sock.getPort());
	    new simaClientReceive(this, numTraces, data, graph, maximum).start();
	} catch(IOException e) {
	    System.out.println(e.getMessage() + " : Failed to connect to server.");
	    System.exit(1);
	}
    }
}


/**
 class simaClientReceive extends Thread 
 This handles all the recieving and parsing of the data
*/
class simaClientReceive extends Thread {
    private jClient parent;
    private int numTraces;
    private int maximum; //max num of point to plot
    private DataSet data[];
    private Graph2D graph;
    /**
       simaClientReceive
       Called once a connection is made with the server
    */
    simaClientReceive(jClient parent, int numTraces, DataSet data[], Graph2D graph, int maximum) {
	this.parent = parent;
	this.numTraces = numTraces;
	this.maximum = maximum;
	this.data = new DataSet[numTraces];
	this.data = data;
	this.graph = graph;
    }
  
    /**
       public synchronized void run()
       This starts when the thread for Receiving is started
    */
    public synchronized void run() {
	DataInputStream remoteIn;
	int hour;
	int minute;
	int second;
	int hundreth;
	int month;
	int day;
	int year;
	int year2;
	double dataTemp[] = new double[2];	
	int count = 0;
	try {
	    remoteIn = new DataInputStream(parent.sock.getInputStream());	
	    while(true) { //loop forever.
		count++;
		if (count >= maximum) {
		    for (int i =0; i < numTraces; i++) {
			data[i].delete(0,0); //clear old points
		    }
		    count =0;
		}
						   
		//read the data that is available
		    hour = remoteIn.readUnsignedByte();
		    minute = remoteIn.readUnsignedByte();
		    second = remoteIn.readUnsignedByte();
		    hundreth = remoteIn.readByte();
		    month =remoteIn.readUnsignedByte();
		    day = remoteIn.readUnsignedByte();
		    year = remoteIn.readUnsignedByte();
		    year2 = remoteIn.readUnsignedByte();
		    year = 256 * year2 + year; //endianness
		    System.out.println("Time: " + hour + ":" + minute + ":" + second + ":" + hundreth + "UTC. Date " + month + "/" + day + "/"+ year);
		    System.out.println("Volatages from Seismometers:");
		    for (int i =0; i < numTraces; i++) {
			dataTemp[1] = readDoubleLittleEndian(remoteIn);
			System.out.println(dataTemp[1]);
			dataTemp[1] += i;
			dataTemp[0] = count;
			try{
		        data[i].append(dataTemp,1);
			data[i].yaxis.maximum = 6.0;
			data[i].yaxis.minimum = 0.0;
			}catch (Exception e) {}
		    }

		    graph.repaint(0);
		    //Read the crap
		    remoteIn.skipBytes(32);
	    }
	} catch (IOException e) {
	    System.out.println("Connection Closed by Peer");
	} finally {
	}
    } //End run
    
    /**
       double readDoubleLittleEndian(DataInputStream input )
       When connecting to the original Server it sends littleEndian
       data
       Code from: mindprod.com/jgloss/endian.html
    */
    double readDoubleLittleEndian(DataInputStream input )
    {
	long accum = 0;
	for ( int shiftBy=0; shiftBy<64; shiftBy+=8 )
	    {
		// must cast to long or shift done modulo 32
		try {
		    accum |= ( (long)( input.readByte() & 0xff ) ) << shiftBy;
		} catch (IOException e) {System.out.println("Connection Closed by Peer");}
	    }
	return Double.longBitsToDouble(accum);
    }

}//End Recieve


