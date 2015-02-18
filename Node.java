import java.awt.geom.Ellipse2D;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;
import java.util.*;


/*
 * For creating nodes:
 * - nodName: 	Takes in a String for a name
 * - Nxy:		Takes in coordinates
 * - aMatrix:	For the adjacency matrix
 */
class Node {
	public String[] nodName;		// Name of Node
	public int Nxy[][];	// X / Y of Node
	public Ellipse2D[] ellipses;
	int[][] aMatrix ;
    public int nodeCount = -1;
    public int lineCount = -1 ;
	
	public Node() {
		nodName = new String[100];
		Nxy = new int[100][2];
		ellipses = new Ellipse2D[100];	
		
	}
	
	/*
	 * 
	 */
	public void cleanNulls(){
		//clean nodName
		ArrayList<String> removed = new ArrayList<String>();
		   for (String str : nodName)
		      if (str != null)
		         removed.add(str);
		   nodName = removed.toArray(new String[removed.size()]);
		   
	//clean Nxy	  
		   ArrayList[][] removedNxy = new ArrayList[100][2];
	}
	
	/*
	 * Initializes the random method for generating random numbers.
	 * @param max		biggest integer for random number.
	 * @param min		smallest integer for random number.
	 * @return			the random number.
	 */
	public int getRand(int max, int min){
		Random random = new Random();
		return random.nextInt(max - min) + min;
	}
	
	/*
	 * initializes and array called "randArray" with a specific size.
	 * @param length		the size of the array.
	 * @return 				the array.
	 */
	public int[] getRandArray(int length){
		int[] randArray = new int[length];
		return randArray;
	}
	
	// randomvals
	/*
	 * 
	 */
	public void randomVals(int nodes, int lines){
		nodeCount = nodes;
		int[][] points  = new int[nodes][2];
		aMatrix = new int[nodes][nodes];
	
		
		// generate nodes
		for(int i = 0; i < nodes; i++){
			
			Nxy[i][0]= getRand(1000,200);
			Nxy[i][1]= getRand(680,170);
		    ellipses[i] = new Ellipse2D.Double(Nxy[i][0],Nxy[i][1], 30, 30);
		    nodName[i] = (char)(65 + i) + "";  
			
		}
		
		//generate adjacency matrix
		int maxN = (int) Math.ceil( (double) lines / (double)nodes );
		int count = 0;
		for(int i = 0; i < nodes; i++){
			if(count == lines)
				break;
			
			for(int j = 0; j < nodes; j++){
				//null on the same vals, 
				if(i == j){
					aMatrix[i][j] = 0;
				}
				
				//choose if to put numbers 
			   int yes = getRand(2,0);
			   if (yes == 1 && aMatrix[j][i] == 0 && i != j){
				   aMatrix[i][j] = getRand(100, 1);
				   count ++;   
			   }				
			}
		}//endfor
		lineCount = count;
	}//end randomVals
	
	/*
	 * Prints the coordinates and the adjacency matrix of the nodes
	 * on the console.
	 */
	public void printVals(){
		System.out.println("Printing Node coordinates");
		ArrayList<String> nodes = new ArrayList();
		
		for(int i = 0; i < nodName.length; i++){
			String s = nodName[i];
			if( s == null)
				continue;
			if(s.equals("null") || s.equals(""))
				continue;
			nodes.add(s);
		}
		
		System.out.println("========================");
		for(int i = 0; i < nodeCount; i++){
			System.out.print(nodes.get(i) + " (" + Nxy[i][0] +", " + Nxy[i][0]+ ")"  );
			System.out.println();
		}
		
		System.out.println();
		System.out.println("Printing Adjacency Matrix");
		System.out.println("========================");
		System.out.print("     ");
		for(int i = 0; i < nodeCount; i++){
			System.out.print(nodes.get(i) + "   "  );
		}
		
		System.out.println();
		for(int i = 0; i < nodeCount; i++){
			System.out.print(nodes.get(i) + " ");
			for(int j = 0; j < nodeCount; j++){
				System.out.printf("%4s",aMatrix[i][j]);				
			}
			System.out.println();
		}
		
	}
	
	/**
	 * generate adjacency matrix and nodes if not exist
	 * 	
	 */
	public void generateVars(Line ln){
		
	int count = 0;
	int lcount = 0;
	
	// make all 0
	aMatrix = new int[nodName.length][nodName.length]; 
	for( int i = 0; i < nodName.length; i++){
		for( int j = 0; j < nodName.length; j++){
			aMatrix[i][j] = 0;
		}
	}
	
	// copy to nodes
	ArrayList<String> nodes = new ArrayList();
	
	for(int i = 0; i < nodName.length; i++){
		String s = nodName[i];
		if( s == null)
			continue;
		if(s.equals("null") || s.equals(""))
			continue;
		nodes.add(s);
	}
		
		for( int i = 0; i < nodName.length; i++){
			String s = nodName[i];
			
			if(s == null)
				continue;
			if( s.equals("null") || s.equals("") || s.equals( " ") )
				continue;
			count ++;
			int x = Nxy[i][0];
			int y = Nxy[i][1];
			
			int row = nodes.indexOf(s);

			
						
			for(int j = 0; j < nodName.length; j++){

				if(i == j)
					continue;
				int x1 = Nxy[j][0];
				int y1 = Nxy[j][1];
				for( int k = 0; k < ln.Lvalue.length ; k++ ){
					 if(ln.Lvalue[k] == 0){
						 
						 continue;
					 }
					 
					 
					 
					 int col = nodes.indexOf(nodName[j]);
					 // find if lines exist between two points
				     if( x == ln.Lxy[k][0] && y == ln.Lxy[k][1] && x1 == ln.Lxy[k][2] && y1 == ln.Lxy[k][3]  ){
				    	 
				    	 aMatrix[row][col] = ln.Lvalue[k];
				    	 System.out.println("inserting");
				    	 System.out.println(row);
				    	 System.out.println(col);
				    	 
				    	 lcount++;
				     }
				     
				     if( x == ln.Lxy[k][2] && y == ln.Lxy[k][3] && x1 == ln.Lxy[k][0] && y1 == ln.Lxy[k][1]  ){
				    	 		lcount ++;
				    	 		 System.out.println("inserting");
						    	 System.out.println(row);
						    	 System.out.println(col);
				    	 		
				    	 aMatrix[row][col] = ln.Lvalue[k];
				     }
				     
				}
				
				
			}
			
		}
		nodeCount = count;
		lineCount = lcount;
		
	}
	

	/**
	 * Find shortest path using
	 * Dijkstra algorithm
	 * @param src
	 * @param dst
	 * @param mat
	 * @param nodenames
	 */
	public void BFS_Start( String src, String dst, int[][] mat, String[] nodenames ){
	
		
	
		
		//generate adjacency matrix if not available
	
		
	int [][] m = new int[nodeCount][nodeCount];
	
	//copying to new matrix to make a, b b,a 
		for(int i = 0;i< nodeCount; i++){
			for(int j= 0; j < nodeCount; j++){
				if(i ==  j)
					continue;
				
				if(mat[i][j] == 0){
					if( mat[j][i] != 0 )
						m[i][j] = mat[j][i];
				}
				else
					 m[i][j] = mat[i][j];				
					
			}
			
		}
		

		
		int sr = Arrays.asList(nodenames).indexOf(src);
		int ds = Arrays.asList(nodenames).indexOf(dst);
		
		ArrayList<String>prev = new ArrayList(nodeCount);
		
		ArrayList<Integer> dist = new ArrayList(nodeCount);
		ArrayList<String> nodes = new ArrayList<String>(Arrays.asList(nodenames));
		
		HashMap<String,Integer> distmap = new HashMap<String,Integer>();
		// initialize
		for (int i = 0; i < nodeCount; i++) {
			  dist.add(0);
			}
		
		//initialize
		for (int i = 0; i < nodeCount; i++) {
			  prev.add("undefined");
			}
		
		
		for (Iterator<String> iterator = nodes.iterator(); iterator.hasNext();) {
		    String string = iterator.next();
		    if (string.equals("null")) {
		        // Remove the current element from the iterator and the list.
		        iterator.remove();
		    }
		}
		
		
		
	// check if src or dst nodes are islands ! 
		
		boolean singular = true;
		
		int ins = nodes.indexOf(src);
		int ind = nodes.indexOf(dst);
		
		//check src
		
		for(int i = 0;i< nodeCount; i++){
			
			if (m[ins][i] != 0 )
				singular = false;
		}
		// check destination
	   for(int i = 0;i< nodeCount; i++){
			
			if (m[ind][i] != 0 )
				singular = false;
		}
	   if(singular){
	      System.out.println("Singular source  or destination! No connection possible! ");
	       return;   
	   }
		
		ArrayList<String> Q = new ArrayList(nodes);
		
		int in = nodes.indexOf(src);
		
		dist.set(in, 0);
		prev.set(in, "undefined" );
		distmap.put( src, 0 );
		
		for( String a : nodes ){
			int index = nodes.indexOf(a);
			if(!a.equals(src)){
				distmap.put( a , Integer.MAX_VALUE);
				dist.set(index, Integer.MAX_VALUE);
				prev.set(index, "undefined");
			}
			
		}
		
		HashMap<String,Integer> distmapcopy = new HashMap<String,Integer>(distmap);
		
		int length = nodes.size();
		while(  distmapcopy.size() != 0  ){
			
			//find minimum
			int min = Integer.MAX_VALUE;
			
			Iterator it = distmapcopy.entrySet().iterator();
			
			 while (it.hasNext()) {
			        Map.Entry pair = (Map.Entry) it.next();
			        
			    if ( (int)pair.getValue() <= min ) {
			        min = (int) pair.getValue();
			        
			    }
			}
			 
			 
			
			 
			 //getminKey
			 String minKey= "";
			 
				    for ( Map.Entry<String, Integer> entry : distmapcopy.entrySet( )) {
				        if ( min == (int)entry.getValue()  ) {
				             minKey  = entry.getKey();
				             
				        }
				    }
				    System.out.println(minKey);
				    
				 
				    System.out.println(distmapcopy.toString());
				    distmapcopy.remove(minKey);
				   
		
				    
		int  minIndex = nodes.indexOf(minKey);
		
		System.out.println(minIndex);
			
			
			
			
			for( int i = 0; i < length; i++  )
			{
				int d = m[minIndex][i];
				
				//continue if no connection
				if(d == 0)
					continue;	
				int newDist = dist.get(minIndex) + d;
				
				if( newDist < dist.get(i) ) {
					dist.set(i,newDist);
					distmap.put( nodes.get(i) , newDist);
					distmapcopy.put( nodes.get(i) , newDist);
					prev.set(i, nodenames[minIndex]);
				}			
				
				
				
			}
			
			System.out.print("Dst {");
			for(int i = 0; i < dist.size(); i++ ){
				System.out.print(dist.get(i) );
				System.out.print(",  ");
				
			}	
			System.out.println("}");
			
			System.out.print("Prev {");
			for(int i = 0; i < dist.size(); i++ ){
				System.out.print(prev.get(i) );
				System.out.print(",  ");
				
			}	
			System.out.println("}");
		}// end while not empty
		

		//shortest path
		
		System.out.println();
		System.out.printf("%s %s %s %s %s %s", "Shortest distance from ", src, "to ", dst , " : ", dist.get( nodes.indexOf(dst)) );
		System.out.println();
		
		String prev_hop = "";
		String path = dst;
		int p = nodes.indexOf(dst);
		while( !src.equals(prev_hop) &&  !prev_hop.equals("undefined")  ){
			
				
			prev_hop = prev.get(p);
			
			p = nodes.indexOf(prev_hop);
			path =  prev_hop+path;
		}
		
		System.out.println("shortest path: ");
	    System.out.println(path);
		
	} 
	
	
	
	
	public void BFS(){
		
		
	}
	
}
