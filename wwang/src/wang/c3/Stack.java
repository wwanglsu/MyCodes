package wang.c3;

public class Stack {
	public Node top;

	public Object pop(){
		if(top==null) {
			return null;
		}

		Object item=top.data;
		top=top.next;
		return item;
	}

	public void push(Object item){
		Node node=new Node(item);
		node.next=top;
		top=node;
	}

	public Object peek(){
		if(top!=null) {
			return top.data;
		}
		return null;
	}
}

