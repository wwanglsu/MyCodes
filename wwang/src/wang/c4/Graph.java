package wang.c4;

public class Graph {
	private DirectedNode[] vertex;
	private int capability;
	private int max;
	
	public Graph(int nodesNum){
		this.max=nodesNum;
		vertex=new DirectedNode[this.max];
	}
	
	public void addNode(DirectedNode node){
		if(capability<max){
			vertex[capability]=node;
			capability++;
		}else{
			System.out.println("Graph is full!");
		}
	}
	
	public DirectedNode[] getNodes(){
		return vertex;
	}
}
