package wang.c4;

public class DirectedNode {
	private DirectedNode[] adjacent;
	public State state;
	public int adjacentCount;
	public int adjacentMax;
	private String vertex;
	
	public DirectedNode(String name, int count){
		this.vertex=name;
		this.adjacentCount=0;
		this.adjacentMax=count;
		adjacent=new DirectedNode[adjacentMax];
	}
	
	public void addAdjacent(DirectedNode x){
		if(adjacentCount<adjacentMax){
			this.adjacent[adjacentCount]=x;
			adjacentCount++;
		}else{
			System.out.println("No more adjacent nodes can be added!");
		}		
	}
	
	public DirectedNode[] getAdjacent(){
		return this.adjacent;
	}
	
	public String getVertexName(){
		return vertex;
	}
}

enum State{
	Unvisited, Visiting, Visited
}
