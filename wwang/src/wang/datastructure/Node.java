package wang.datastructure;

public class Node implements Comparable{
	public Object data;
	public Node left;
	public Node right;
	public Node parent;
	
	public Node(Object data){
		this.data=data;
	}
	
	public int compareTo(Object other){
		if(this.data.equals(other))return 0;
		else if(this.data.hashCode() > other.hashCode() ) return 1;
		else return -1;
	}
	
	public String toString(){
		return data.toString();
	}
}
