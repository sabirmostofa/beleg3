import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


class theFrame extends JFrame {
	
	// Initialize Variable:
	public JButton 		B_nodeMk, B_nodeRm, B_lineMk, B_lineRm, B_save, B_load, B_reset, B_random, B_AdjMatrix, B_short;
	public JTextField 	T_nodeMk, T_nodeRm, T_lineMk_name, T_lineMk_start, T_lineMk_dest,
					  	T_lineRm_start, T_lineRm_dest, T_save, T_load, Short_node, Short_node2;
	public JLabel 		L_start, L_start2, L_dest, L_dest2;
	public Node nod = new Node();
	public Line Ln = new Line();
	public boolean notEmpty = false, mouseOn = false, checkNodeMk = false, checkLineMk = false, lChecked = false, removeOn = false;
	public int nCount = 0, lCount = 0, searchCount = 0;
	
	
	
	public theFrame() {
		setTitle("Graph Maker");
		setBounds(0,0,1300,800);
		setLayout(null);
		addMouseListener(new My_Mouse());
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		addButtons();
		
		
	}
	
	public void addButtons(){

		// ---------------Creating nodes and lines:--------------
		add(T_nodeMk = new JTextField(15));
		T_nodeMk.setBounds(50,25,125,30);
		T_nodeMk.setBackground(Color.gray.brighter());
		T_nodeMk.addActionListener(new Action(1));
		add(B_nodeMk = new JButton("Create Node"));
		B_nodeMk.setBounds(225,25,125,30);
		B_nodeMk.setBackground(Color.gray.brighter());
		B_nodeMk.addActionListener(new Action(2));
		B_nodeMk.setEnabled(false);
		
		add(T_lineMk_name = new JTextField(15));
		T_lineMk_name.setBounds(50,55,125,30);
		T_lineMk_name.setBackground(Color.gray.brighter());
		T_lineMk_name.addActionListener(new Action(3));
		add(B_lineMk = new JButton("Create Line"));
		B_lineMk.setBounds(225,55,125,30);
		B_lineMk.setBackground(Color.gray.brighter());
		B_lineMk.addActionListener(new Action(6));
		B_lineMk.setEnabled(false);
		
		add(L_start = new JLabel("Start:"));
		L_start.setBounds(90,85,125,30);
		add(T_lineMk_start = new JTextField(15));
		T_lineMk_start.setBounds(50,115,125,30);
		T_lineMk_start.setBackground(Color.gray.brighter());
		T_lineMk_start.addActionListener(new Action(4));
		add(L_dest = new JLabel("Destination:"));
		L_dest.setBounds(245,85,125,30);
		add(T_lineMk_dest = new JTextField(15));
		T_lineMk_dest.setBounds(225,115,125,30);
		T_lineMk_dest.setBackground(Color.gray.brighter());
		T_lineMk_dest.addActionListener(new Action(5));
		
		
		// ---------------Deleting nodes and lines:--------------
		//add(T_nodeRm = new JTextField(15));
		//T_nodeRm.setBounds(450,25,125,30);
		//T_nodeRm.setBackground(Color.gray.brighter());
	//	T_nodeRm.addActionListener(new Action(1));
		add(B_nodeRm = new JButton("Delete Node/Line"));
		B_nodeRm.setBounds(480,115,200,30);
		B_nodeRm.setBackground(Color.gray.brighter());
	    B_nodeRm.addActionListener(new Action(7));
	    
	    // shortest path
	    
	    add(Short_node = new JTextField(15));
		Short_node.setBounds(480,25,90,30);
		
	    add(Short_node2 = new JTextField(15));
		Short_node2.setBounds(590,25,90,30);
		
		add(B_short = new JButton("Get shortest path"));
		B_short.setBounds(480,60,200,30);
		B_short.setBackground(Color.gray.brighter());
		B_short.addActionListener(new Action(50));
		
		//B_lineRm.setBackground(Color.gray.brighter());
	//	B_lineRm.addActionListener(new Action(1));
		
		//add(L_start2 = new JLabel("Start:"));
		//L_start2.setBounds(490,85,125,30);
			//add(T_lineRm_start = new JTextField(15));
		//T_lineRm_start.setBounds(450,115,125,30);
		//T_lineRm_start.setBackground(Color.gray.brighter());
	//	T_lineRm_start.addActionListener(new Action(1));
		//add(L_dest2 = new JLabel("Destination:"));
		//L_dest2.setBounds(645,85,125,30);
		//add(T_lineRm_dest = new JTextField(15));
		//T_lineRm_dest.setBounds(625,115,125,30);
		//T_lineRm_dest.setBackground(Color.gray.brighter());
	//	T_lineRm_dest.addActionListener(new Action(1));
			
		//-----------------Save and load graphs----------------------
		add(T_save = new JTextField(15));
		T_save.setBounds(850,25,125,30);
		T_save.setBackground(Color.gray.brighter());
	//	T_save.addActionListener(new Action(12));
		add(B_save = new JButton("Save Graph"));
		B_save.setBounds(1025,25,125,30);
		B_save.setBackground(Color.gray.brighter());
		B_save.addActionListener(new Action(13));
		
		add(T_load = new JTextField(15));
		T_load.setBounds(850,55,125,30);
		T_load.setBackground(Color.gray.brighter());
	//	T_load.addActionListener(new Action(1));
		add(B_load = new JButton("Load Graph"));
		B_load.setBounds(1025,55,125,30);
		B_load.setBackground(Color.gray.brighter());
		B_load.addActionListener(new Action(15));
		
		
		//-----------------Reset, Random and Adjazenzmatrix---------------------
		add(B_reset = new JButton("Reset"));
		B_reset.setBounds(850,85,125,30);
		B_reset.setBackground(Color.gray.brighter());
		B_reset.addActionListener(new Action(16));
		add(B_random = new JButton("Random"));
		B_random.setBounds(850,115,125,30);
		B_random.setBackground(Color.gray.brighter());
	    B_random.addActionListener(new Action(17));	
		add(B_AdjMatrix = new JButton("Adj. Matrix"));
		B_AdjMatrix.setBounds(1025,115,125,30);
		B_AdjMatrix.setBackground(Color.gray.brighter());
	    B_AdjMatrix.addActionListener(new Action(20));
		
		
	}

	
	// MouseAdapter is always active and waits for one of the methods to be executed.
	class My_Mouse extends MouseAdapter {
		public void mousePressed(MouseEvent e) {	// Method: Executed when mouse is pressed.
			
			// if to remove 
			if(removeOn == true){
				int x =  e.getX();
				int y = e.getY();
				
				//delete node
				for( int i= 0; i< nCount ; i++){
					if(nod.ellipses[i] == null)
						continue;
					if(nod.ellipses[i].contains(x,y)){
						System.out.println("deleting node");
						int prex = nod.Nxy[i][0];
						int prey = nod.Nxy[i][1];
						nod.Nxy[i][0] = 0;
						nod.Nxy[i][1] = 0;
						nod.nodName[i] = null;
						nod.ellipses[i] = null;
						
						
						//delete respective lines offset set to 15
						for(int j= 0; j< lCount ; j++){
							if((Ln.Lxy[j][0] == prex && Ln.Lxy[j][1] == prey) ||(Ln.Lxy[j][2] == prex && Ln.Lxy[j][3] == prey) ){
								Ln.Lvalue[j] = 0;
								Ln.Lxy[j][0] =0;
								Ln.Lxy[j][1] =0;
								Ln.Lxy[j][2] =0;
								Ln.Lxy[j][3] =0;
								Ln.lines[j] = null; // set line2d to null
								System.out.println("deleting line according to the node");
								
								
							}						
						}			
						
					}
						
				}// end delete node
				
				//remove null elements from nod an Ln
				
			
				
				//delete line
	           for( int i= 0; i< lCount ; i++){
	        	   if(Ln.lines[i] == null)
	        		   continue;
	        	   if(Ln.lines[i].intersects(x, y,10,10)){
	        		   System.out.println("deleting line");
	        		   Ln.Lvalue[i] = 0;
	        		   Ln.Lxy[i][0] = 0;
	        		   Ln.Lxy[i][1] = 0;
	        		   Ln.Lxy[i][2] = 0;
	        		   Ln.Lxy[i][3] = 0;
	        		   Ln.lines[i] = null;
	        		   
	        	   }
					
				}
	           
	           nod.generateVars(Ln);
	         
				
				
				
				removeOn = false;
				repaint();
			} //end if removeon
			
			// Save mouse position
			nod.Nxy[nCount][0] = e.getX();
			nod.Nxy[nCount][1] = e.getY();
		    nod.ellipses[nCount] =  new Ellipse2D.Double(e.getX(),e.getY(), 30, 30);
						
			// new circle: saves position up to 30 times (see initialization)
			if(mouseOn == true) 
				nCount++;
			
			//System.out.println("a:" + nod.Nxy[0][0]);
			
			B_nodeMk.setEnabled(false);
			mouseOn = false;
			nod.generateVars(Ln);
			repaint();
		}
	}
	
	
	public void paint(Graphics g) {
		super.paint(g);
	     Graphics2D g2 = (Graphics2D) g;
	//	g.setColor(Color.black);
	//	g.fillRect(0,155,1300,685);
		
		// Draw Circles:
		for (int i = 0; i < nCount; i++) {
			
			//continue if deleted
			if(nod.ellipses[i] == null || nod.nodName[i].equals("null"))
				continue;
			g.setColor(Color.green);
			/**
			g.fillOval(nod.Nxy[i][0] - 10, nod.Nxy[i][1] - 30, 60, 60);
			g.setColor(Color.black);
			*/
			g2.fill(nod.ellipses[i]);
			
			g2.setColor(Color.black);
			g2.drawString("" + nod.nodName[i], nod.Nxy[i][0], nod.Nxy[i][1]);
		}
		
		// Draw Lines:
		for (int i = 0; i < lCount; i++) {
			if(Ln.lines[i] == null || Ln.Lvalue[i] == 0)
				continue;
			g.setColor(Color.red);
			g2.setStroke(new BasicStroke(3));
			
			//g.drawLine(Ln.Lxy[i][0], Ln.Lxy[i][1], Ln.Lxy[i][2], Ln.Lxy[i][3]);
			g2.draw(Ln.lines[i]);
			g2.setColor(Color.black);
			g2.drawString("" + Ln.Lvalue[i],((Ln.Lxy[i][0] + Ln.Lxy[i][2]) / 2),((Ln.Lxy[i][1] + Ln.Lxy[i][3]) / 2) );
		}
	}
	
	public boolean checkInputMake (JTextField TextField) {
		notEmpty = false;
		if (TextField.equals("")) 
			notEmpty = false;
		else if (checkNodeMk == true) {		// For nodeMk
			notEmpty = true;
			checkNodeMk = false;
			nod.nodName[nCount] = TextField.getText();
		}
		else if (checkLineMk == true) {		// For lineMk
			notEmpty = true;
			checkLineMk = false;
			Ln.Lvalue[lCount] = Integer.parseInt(TextField.getText()); 
		}
		return notEmpty;
	}
	
	public void checkXY (JTextField TF_XY) {		// For lineMk_start
		if (lChecked == false) {
			for (int i = 0; i < nCount; i++) {
				if (nod.nodName[i].equals(TF_XY.getText()) ) {
					Ln.Lxy[lCount][0] = nod.Nxy[i][0];
					Ln.Lxy[lCount][1] = nod.Nxy[i][1];
					System.out.println("Line start:  " + nod.nodName[i]);
				}
			}
		}
		if (lChecked == true) {						// For lineMk_dest
			for (int i = 0; i < nCount; i++) {
				if (nod.nodName[i].equals(TF_XY.getText()) ) {
					Ln.Lxy[lCount][2] = nod.Nxy[i][0];
					Ln.Lxy[lCount][3] = nod.Nxy[i][1];
					B_lineMk.setEnabled(true);
					lChecked = false;
					System.out.println("Line end: " + nod.nodName[i]);
				}
			}
		}
	}
	
	/**
	 * Saves the line value to line 2D object
	 */
	public void saveLine(){
		Ln.lines[lCount] = new Line2D.Double(Ln.Lxy[lCount][0]+15, Ln.Lxy[lCount][1]+15,Ln.Lxy[lCount][2]+15, Ln.Lxy[lCount][3] +15);
	}
	
	/*
	 * Resets all painted elements, given values, names and coordinates.
	 * Additionally, it prints the operation on the console.
	 */
	public void resetGraph() {
		// reset nodes:
		nCount = 0;
		for (int i = 0; i < 100; i++) {
			nod.nodName[i] = "null";
			nod.Nxy[i][0] = 0;
			nod.Nxy[i][1] = 0;
		}
		System.out.println("--------Reseted nodes");
		// reset lines:
		lCount = 0;
		for (int i = 0; i < 300; i++) {
			Ln.Lvalue[i] = 0;
			Ln.Lxy[i][0] = 0;
			Ln.Lxy[i][1] = 0;
			Ln.Lxy[i][2] = 0;
			Ln.Lxy[i][3] = 0;
		}
		System.out.println("--------Reseted lines");
	}
	
	/*
	 * Saves the painted elements and its values / names in a text-file.
	 * Additionally, it prints the operation on the console.
	 * 
	 * @param TF_save		Uses the text field on the left side of the 
	 * 						save button for the name of the text-file.
	 */
	public void saveGraph(JTextField TF_save) {
		String fileName = TF_save.getText();
		try {
			PrintWriter print = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
			
			//save counters:
			print.println("" + nCount);	
			print.println("" + lCount);	
			
			// save node: saves 20 nodes
			for (int i = 0; i < 20; i++) {
				print.println("" + nod.nodName[i]);
				print.println("" + nod.Nxy[i][0]);
				print.println("" + nod.Nxy[i][1]);
			}
			
			// save lines:	saves 60 lines
			for (int i = 0; i < 60; i++) {
				print.println("" + Ln.Lvalue[i]);
				print.println("" + Ln.Lxy[i][0]);
				print.println("" + Ln.Lxy[i][1]);
				print.println("" + Ln.Lxy[i][2]);
				print.println("" + Ln.Lxy[i][3]);
			}
			
			print.close();
			System.out.println("*****Graph saved");
		}
		catch (IOException iox) {
			System.out.println("Problem writing " + fileName);
		}
	}
	
	/*
	 * Loads a specific text-file to print the graph.
	 * Additionally, it prints the operation on the console.
	 * 
	 * @param TF_load		the specific text-file given by the user by
	 * 						writing on the text field on the left of the 
	 * 						load-button.
	 */
	public void loadGraph(JTextField TF_load) {
		String fileName = TF_load.getText();;
		
		try {
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			try {
				
				// reads counters for nodes and lines:
				nCount = Integer.parseInt(input.readLine());	
				lCount = Integer.parseInt(input.readLine());
				
				// read node:		reads 20 nodes
				for (int i = 0; i < 20; i ++) {
					nod.nodName[i] = input.readLine();
					nod.Nxy[i][0] = Integer.parseInt(input.readLine());
					nod.Nxy[i][1] = Integer.parseInt(input.readLine());
					
					if( !nod.nodName[i].equals("null") && (nod.Nxy[i][0] !=0  && nod.Nxy[i][1] != 0) )
					   nod.ellipses[i] = new Ellipse2D.Double(nod.Nxy[i][0],nod.Nxy[i][1], 30, 30);
				}
			
				// read line:		reads 60 lines
				for (int i = 0; i < 60; i++) {
					Ln.Lvalue[i] = Integer.parseInt(input.readLine());
					Ln.Lxy[i][0] = Integer.parseInt(input.readLine());
					Ln.Lxy[i][1] = Integer.parseInt(input.readLine());
					Ln.Lxy[i][2] = Integer.parseInt(input.readLine());
					Ln.Lxy[i][3] = Integer.parseInt(input.readLine());
					if( Ln.Lvalue[i] != 0) 
					   Ln.lines[i] = new Line2D.Double(Ln.Lxy[i][0]+15, Ln.Lxy[i][1]+15,Ln.Lxy[i][2]+15, Ln.Lxy[i][3] +15);
				}
				
				
			}
			
			
			
			catch (EOFException eof) {				// Reads Integers until EOF.
				System.out.println("EOF reached");
				input.close();
			}	
		}
		catch (IOException iox) {
			System.out.println("Problem reading " + fileName);
		}
		
		//genereate variables
		nod.generateVars(Ln);
		
		System.out.println("*****Graph loaded");
	}
		
	
//  Button-Listeners:
	class Action implements ActionListener {
		int typ;
		Action(int i) {
			typ = i;
		}
		public void actionPerformed(ActionEvent evt) {
			switch(typ) {
			case 1:			// T_nodeMk
				
				System.out.println("test");
				
				checkNodeMk = true;
				if (checkInputMake(T_nodeMk) == true) 
					B_nodeMk.setEnabled(true);
				else
					checkNodeMk = false;
				System.out.println("+++ added node: " + nod.nodName[nCount]);
				
				break;
			case 2:			// B_nodeMk
				mouseOn = true;
				nod.generateVars(Ln);
				break;
			case 3:			// T_lineMk_value
				checkLineMk = true;
				if (checkInputMake(T_lineMk_name) == false) 
					checkLineMk = false;
				System.out.println("++ line value: " + Ln.Lvalue[lCount]);
				break;
			case 4:			// T_lineMk_start
				if ((!T_lineMk_start.equals("")) == true) {
					checkXY(T_lineMk_start);
					lChecked = true;
				}
				break;
			case 5:			// T_lineMk_dest
				if ((!T_lineMk_dest.equals("")) == true) 
					checkXY(T_lineMk_dest);
				break;
			case 6:			// B_lineMk
				saveLine();
				lCount++;
				B_lineMk.setEnabled(false);
				nod.generateVars(Ln);
				repaint();
				break;
			case 7:			// T_nodeRm
				removeOn = true;
				break;
				
			case 8:			// B_nodeRm
				
			case 9:			// B_lineRm
				
			case 10:		// T_lineRm_start
				
			case 11: 		// T_lineRm_dest
				
			case 12:		// T_save
				if ((!T_save.equals("")) == true) 
					B_save.setEnabled(true);
				break;
			case 13:		// B_save
				saveGraph(T_save);
				break;
			case 14:		// T_load
				if ((!T_load.equals("")) == true) 
					B_load.setEnabled(true);
				break;
			case 15:		// B_load
				resetGraph();
				repaint();
				loadGraph(T_load);
				repaint();
				break;
			case 16:		// B_reset
				resetGraph();
				getContentPane().removeAll();
				addButtons();
				repaint();
				break;
			case 17:		// B_random
				resetGraph();
				getContentPane().removeAll();
				addButtons();
				int numNodes = 9;
				// maximum lines
				int numLines = 14;
				 
				nod.randomVals(numNodes, numLines);
				Ln.linesFromMatrix(nod.aMatrix, nod.Nxy , numNodes);
				lCount = nod.lineCount;
				nCount = numNodes;
				nod.printVals();
				nod.BFS_Start("B", "D" , nod.aMatrix, nod.nodName);
				repaint();
				break;
			case 20: // print adjacent matrix
				nod.printVals();
				break;
			case 50: // get shortest path
				String a = Short_node.getText();
				String b = Short_node2.getText();
				nod.BFS_Start(a, b , nod.aMatrix, nod.nodName);
				break;
				
			}
		}
	}
	
	
}
