package wang.c3;

public class Queue {
	public Node first;
	public Node last;

	public void offer(Object item){
		Node node=new Node(item);

		if(first==null){
			last=node;
			first=last;
		}else{
			last.next=node;
			last=last.next;
		}
	}

	public Object poll(){
		if(first==null) {
			return null;
		} else{
			Object item=first.data;
			first=first.next;
			return item;
		}
	}
}
