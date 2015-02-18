import java.awt.geom.Line2D;

/*
 * Creates a line:
 * - Lvalue: lines can carry a user given value.
 * - Lxy:	 coordinates for the lines
 */
class Line {
	public int[] Lvalue;
	public int[][] Lxy;
	public Line2D[] lines;
	public Line() {
		Lvalue = new int[300];	
		Lxy = new int[300][4];
		lines = new Line2D[300];
	}

	/**
	 * Creates lines from given Adjacency matrix, nodes with coordinates and the number of of nodes
	 * @param m
	 * @param nodes
	 * @param nodeNum
	 */
	
	public void linesFromMatrix(int[][] m, int[][] nodes,int nodeNum){
		int length = nodeNum;
		int c = 0;
		for(int i=0; i<length; i++){
			for(int j=0; j<length; j++){
				if(m[i][j] != 0 ){
					Lxy[c][0] = nodes[i][0];
					Lxy[c][1] = nodes[i][1];
					Lxy[c][2] = nodes[j][0];
					Lxy[c][3] = nodes[j][1];
					
					//value
					Lvalue[c] = m[i][j];
					lines[c] = new Line2D.Double(Lxy[c][0]+15, Lxy[c][1]+15,Lxy[c][2]+15, Lxy[c][3] +15);
					
					c++;
				}
			}
		}
		
	}
}	
