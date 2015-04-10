package wang.c3;

public class Node {
	public Object data;
	public Node next;

	public Node(Object data){
		this.data=data;
	}

	@Override
	public String toString(){
		return data.toString();
	}
}
