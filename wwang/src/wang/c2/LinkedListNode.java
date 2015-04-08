package wang.c2;

public class LinkedListNode {
	public int data;
	public LinkedListNode next;

	public LinkedListNode(int data){
		this.data=data;
	}

	public void appendToTail(int d){
		LinkedListNode newNode=new LinkedListNode(d);
		LinkedListNode current=this;
		while(current.next!=null){
			current=current.next;			
		}

		current.next=newNode;		
	}

	public LinkedListNode deleteNode(LinkedListNode head, int d){
		LinkedListNode current=head;
		if(current.data==d) {
			return head.next;
		}
		while(current.next !=null){
			if(current.next.data==d){
				current.next=current.next.next;
				return head;
			}
			current=current.next;
		}
		return head;
	}

	@Override
	public String toString(){
		LinkedListNode runner=this;
		StringBuilder sb=new StringBuilder();

		while(runner != null){
			sb.append(runner.data);
			sb.append("  ");
			runner=runner.next;
		}

		return sb.toString();
	}

}
