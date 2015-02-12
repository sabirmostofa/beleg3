import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Random;



class Node {
	public String[] nodName;		// Name of Node
	public int Nxy[][];	// X / Y of Node
	public Ellipse2D[] ellipses;
	int[][] aMatrix ;
    public int nodeCount;
    public int lineCount;
	
	public Node() {
		nodName = new String[100];
		Nxy = new int[100][2];
		ellipses = new Ellipse2D[100];	
		
	}
	
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
	
	public int getRand(int max, int min){
		Random random = new Random();
		return random.nextInt(max - min) + min;
	}
	
	
	public int[] getRandArray(int length){
		int[] randArray = new int[length];
		
		
		
		return randArray;
	}
	
	// randomvals
	public void randomVals(int nodes, int lines){
		nodeCount = nodes;
		int[][] points  = new int[nodes][2];
		aMatrix = new int[nodes][nodes];
	
		
		// generate nodes
		for(int i = 0; i < nodes; i++){
			
			Nxy[i][0]= getRand(900,200);
			Nxy[i][1]= getRand(600,160);
		    ellipses[i] = new Ellipse2D.Double(Nxy[i][0],Nxy[i][1], 30, 30);
		    nodName[i] = (char)(65 + i) + "";  
			
		}
		
		//generate adjacency matrix
		
	int maxN = (int)	Math.ceil( (double) lines / (double)nodes );
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
			   if (yes == 1 && aMatrix[j][1] == 0 && i != j){
				   aMatrix[i][j] = getRand(100, 1);
				   count ++;
				   
			   }				
				
			}
		}//endfor
		lineCount = count;
		
	}
	
	//end randomVals
	
	public void printVals(){
		System.out.println("Prining Node coordinates");
		System.out.println("========================");
		
		for(int i = 0; i < nodeCount; i++){
			System.out.print(nodName[i] + " (" + Nxy[i][0] +", " + Nxy[i][0]+ ")"  );
			System.out.println();
			
		}
		
		System.out.println();
		System.out.println("Prining Adjacency Matrix");
		System.out.println("========================");
		
		for(int i = 0; i < nodeCount; i++){
			System.out.print(nodName[i] + "    "  );
			
			
		}
		
		System.out.println();
		for(int i = 0; i < nodeCount; i++){
			System.out.print(nodName[i] + " ");
			for(int j = 0; j < nodeCount; j++){
				System.out.printf("%4s",aMatrix[i][j]);				
			}
			System.out.println();
		}
		
	}
	
}
