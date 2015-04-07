package wang.c2;

import java.util.Hashtable;

public class Questions {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedListNode head=new LinkedListNode(3);
		head.next=new LinkedListNode(2); head.next.next=new LinkedListNode(2);
		head.next.next.next=new LinkedListNode(12); head.next.next.next.next=new LinkedListNode(2);
		head.next.next.next.next.next=new LinkedListNode(8); head.next.next.next.next.next.next=new LinkedListNode(7);
		//deleteDuplicate1(head);
		System.out.println(head);
		deleteDuplicate2(head);
		System.out.println(head);
		findKthLastNode1(head, 3);
		findKthLastNode2(head, 3, new Questions.IntWrapper());
		LinkedListNode node=findKthLastNode3(head, 3);
		System.out.println(node);

	}

	/*******Question 1 remove duplicates from unsorted linkedlist***/
	static void deleteDuplicate1(LinkedListNode head){
		Hashtable<Integer, Boolean> table=new Hashtable<Integer, Boolean>();
		LinkedListNode previous=null;
		LinkedListNode runner=head;
		while(runner!=null){
			if( table.containsKey(runner.data) ){
				previous.next=runner.next;
			}else{
				table.put(runner.data, true);
				previous=runner;
			}
			runner=runner.next;
		}
	}

	static void deleteDuplicate2(LinkedListNode head){
		if(head==null) {
			return;
		}

		LinkedListNode current=head;
		while(current !=null){
			LinkedListNode runner=current;
			while(runner.next!=null){
				if(current.data==runner.next.data){
					runner.next=runner.next.next;
				}else{
					runner=runner.next;
				}
			}
			current=current.next;
		}
	}
	/*******Question 1********/

	/*******Question 2 Kth to last element********/
	static int findKthLastNode1(LinkedListNode head, int Kth){
		if(head==null) {
			return 0;
		}
		int i= findKthLastNode1(head.next, Kth)+1;
		if(i==Kth){
			System.out.println(head.data);
		}
		return i;
	}

	static LinkedListNode findKthLastNode2(LinkedListNode head, int Kth, IntWrapper i){
		if(head==null) {
			return null;
		}
		LinkedListNode node=findKthLastNode2(head.next, Kth, i);
		i.value=i.value+1;
		if(i.value==Kth){
			System.out.println(head.data);
			return head;
		}
		return node;
	}
	private static class IntWrapper{
		int value=0;
	}

	static LinkedListNode findKthLastNode3(LinkedListNode head, int Kth){
		if(head==null) {
			return null;
		}
		LinkedListNode p1=head;
		LinkedListNode p2=head;

		for(int i=0; i<Kth-1; i++){
			p2=p2.next;
		}

		if(p2==null) {
			return null;
		}
		while(p2.next!=null){
			p1=p1.next;
			p2=p2.next;
		}
		return p1;
	}

	/*******Question 2********/


}
