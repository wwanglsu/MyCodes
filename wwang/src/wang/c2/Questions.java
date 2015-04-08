package wang.c2;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Stack;



public class Questions {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedListNode head=new LinkedListNode(5);
		head.next=new LinkedListNode(7); head.next.next=new LinkedListNode(7);
		head.next.next.next=new LinkedListNode(17); head.next.next.next.next=new LinkedListNode(7);
		head.next.next.next.next.next=new LinkedListNode(8); head.next.next.next.next.next.next=new LinkedListNode(4);
		head.next.next.next.next.next.next.next=new LinkedListNode(6); head.next.next.next.next.next.next.next.next=new LinkedListNode(3);
		System.out.println(head);
		deleteDuplicate2(head);
		System.out.println(head);
		findKthLastNode1(head, 2);
		LinkedListNode node=findKthLastNode(head, 2, new Questions.IntWrapper());
		System.out.println("Kth last node: "+ node.data);
		LinkedListNode node2=findKthLastNode_Iterative(head, 3);
		System.out.println(node2.data);
		LinkedListNode partitioNode=partitionLinkedList2(head, 6);
		//LinkedListNode partitioNode2=reverseLinkedList(head);
		System.out.println(partitioNode);

		LinkedListNode num1=new LinkedListNode(5); num1.next=new LinkedListNode(3); num1.next.next=new LinkedListNode(9);
		LinkedListNode num2=new LinkedListNode(8); num2.next=new LinkedListNode(7); num2.next.next=new LinkedListNode(6);
		//LinkedListNode result=sum_recursive(reverseLinkedList(num1), reverseLinkedList(num2), 0);
		//System.out.println("Sum: " + reverseLinkedList(result));
		//LinkedListNode result=sum_iterative(reverseLinkedList(num1), reverseLinkedList(num2), 0);
		//System.out.println("Sum: " + reverseLinkedList(result));

		LinkedList result=sum_stack(num1, num2, 0);
		System.out.println("Sum: " + result);

		LinkedListNode aHead=new LinkedListNode(0); aHead.next=new LinkedListNode(1); aHead.next.next=new LinkedListNode(2); aHead.next.next.next=new LinkedListNode(2);
		aHead.next.next.next.next=new LinkedListNode(1); aHead.next.next.next.next.next=new LinkedListNode(0);
		System.out.println(aHead);
		//boolean ispalindrome= checkPalindrome2(aHead);
		//System.out.println("ispalindrome: "+ispalindrome);
		Helper ispalindrome= checkPalindromeRecurse(aHead, 6);
		System.out.println("ispalindrome: "+ispalindrome);


	}

	/***********Question 1 delete duplicates*****/
	static void deleteDuplicate1(LinkedListNode head){
		LinkedListNode runner=head;
		LinkedListNode previous=null;
		Hashtable<Integer, Boolean> table=new Hashtable<Integer, Boolean>();
		while(runner!=null){
			if(table.containsKey(runner.data)){
				previous.next=runner.next;
			}else{
				table.put(runner.data, true);
				previous=runner;
			}
			runner=runner.next;
		}
	}

	static void deleteDuplicate2(LinkedListNode head){
		LinkedListNode current=head;
		while(current!=null){
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

	/***********Question 1*************************/

	/***********Question 2 Kth last element*************************/
	static int findKthLastNode1(LinkedListNode head, int Kth){
		if(head==null) {
			return 0;
		}

		int i=(findKthLastNode1(head.next, Kth))+1;
		if(i==Kth){
			System.out.println(head.data);
		}
		return i;
	}

	static LinkedListNode findKthLastNode(LinkedListNode head, int Kth, IntWrapper i){
		if(head==null) {
			return null;
		}

		LinkedListNode node=findKthLastNode(head.next, Kth, i);
		i.value=i.value+1;
		if(Kth==i.value){
			System.out.println(head.data);
			return head;
		}
		return node;
	}
	private static class IntWrapper{
		int value=0;
		public IntWrapper() {

		}
	}

	static LinkedListNode findKthLastNode_Iterative(LinkedListNode head, int Kth){
		LinkedListNode p1=head;
		LinkedListNode p2=head;

		for(int i=0; i<Kth-1; i++){
			if(p2==null) {
				return null;
			}
			p2=p2.next;
		}
		if(p2==null) {
			return null;
		}

		while(p2.next!=null){
			p2=p2.next;
			p1=p1.next;
		}
		return p1;
	}
	/***********Question 2 Kth last element*************************/

	/***********Question 3 delete a node in the middle*************************/
	static boolean deleteNode(LinkedListNode node){
		if(node==null || node.next==null) {
			return false;
		}

		LinkedListNode next=node.next;
		node.data=next.data;
		node.next=next.next;
		return true;
	}
	/***********Question 3 delete a node in the middle*************************/

	/***********Question 4 partition Linkedlist *******************************/
	static LinkedListNode partitionLinkedList1(LinkedListNode head, int x){
		if(head==null) {
			return null;
		}

		LinkedListNode leftStart=null;
		LinkedListNode leftRunner=null;
		LinkedListNode rightStart=null;
		LinkedListNode rightRunner=null;

		while(head != null){
			LinkedListNode next=head.next;
			head.next=null;
			if(head.data < x){
				if(leftStart==null){
					leftStart=head;
					leftRunner=leftStart;
				}else{
					leftRunner.next=head;
					leftRunner=head;
				}
			}else{
				if(rightStart==null){
					rightStart=head;
					rightRunner=rightStart;
				}else{
					rightRunner.next=head;
					rightRunner=head;
				}
			}
			head=next;
		}

		if(leftStart==null) {
			return rightStart;
		}

		leftRunner.next=rightStart;
		return leftStart;
	}

	static LinkedListNode partitionLinkedList2(LinkedListNode head, int x){
		if(head==null) {
			return null;
		}

		LinkedListNode left=null;
		LinkedListNode right=null;

		while(head!=null){
			LinkedListNode next=head.next;
			head.next=null;
			if(head.data < x){
				if(left==null){
					left=head;
				}else{
					head.next=left;
					left=head;
				}
			}else{
				if(right==null){
					right=head;
				}else{
					head.next=right;
					right=head;
				}
			}
			head=next;
		}

		if(left==null) {
			return right;
		}
		LinkedListNode dummy=left;
		while(left.next!=null){
			left=left.next;
		}
		left.next=right;
		return dummy;
	}

	static LinkedListNode reverseLinkedList(LinkedListNode head){
		if(head==null) {
			return null;
		}

		LinkedListNode dummy=null;
		while(head!=null){
			LinkedListNode next=head.next;
			head.next=dummy;
			dummy=head;
			head=next;
		}
		return dummy;
	}
	/***********Question 4 partition Linkedlist *******************************/

	/***********Question 5 sum Linkedlist, considering n1 n2 already reversed*******************************/
	static LinkedListNode sum_recursive(LinkedListNode n1, LinkedListNode n2, int carry){
		if(n1==null && n2==null && carry==0) {
			return null;
		}

		LinkedListNode result=new LinkedListNode(carry);
		int value=carry;

		if(n1!=null){
			value+=n1.data;
		}
		if(n2!=null){
			value+=n2.data;
		}
		result.data=value%10;
		//recurse
		if(n1!=null || n2!=null){
			LinkedListNode more=sum_recursive(n1==null? null: n1.next, n2==null? null : n2.next, value>9? 1: 0);
			result.next=more;
		}
		return result;
	}

	static LinkedListNode sum_iterative(LinkedListNode n1, LinkedListNode n2, int carry){
		if(n1==null && n2==null) {
			return null;
		}

		LinkedListNode head=null;
		LinkedListNode runner=null;

		int value=carry;
		while(n1 !=null || n2 != null){
			LinkedListNode result=new LinkedListNode(value);
			if(n1 != null){
				value += n1.data;
				n1=n1.next;
			}
			if(n2 != null){
				value +=n2.data;
				n2=n2.next;
			}
			result.data=value%10;
			value= value>9 ? 1 : 0;
			if(head==null){
				head=result;
				runner=head;
			}else{
				runner.next=result;
				runner=result;
			}
		}

		if(value==1){
			runner.next=new LinkedListNode(value);
		}
		return head;
	}

	static LinkedList sum_stack(LinkedListNode n1, LinkedListNode n2, int carry){
		if(n1==null && n2==null) {
			return null;
		}
		int value=carry;
		LinkedList<LinkedListNode> stack1=new LinkedList<LinkedListNode>();
		LinkedList<LinkedListNode> stack2=new LinkedList<LinkedListNode>();
		LinkedList<LinkedListNode> stack=new LinkedList<LinkedListNode>();
		while(n1!=null){
			stack1.push(n1);
			n1=n1.next;
		}
		while(n2!=null){
			stack2.push(n2);
			n2=n2.next;
		}

		while(stack1.peek() != null || stack2.peek() !=null){
			LinkedListNode result=new LinkedListNode(value);
			if(stack1.peek() !=null){
				value += stack1.pop().data;
			}
			if(stack2.peek() != null){
				value += stack2.pop().data;
			}
			result.data=value % 10;
			value= value>9 ? 1: 0;
			stack.push(result);
		}
		if(value==1){
			stack.push(new LinkedListNode(value));
		}
		return stack;
	}
	/***********Question 5 sum Linkedlist *******************************/

	/***********Question 6 return the node at the begining of the loop*******************************/
	static LinkedListNode findBegining(LinkedListNode head){
		LinkedListNode slow=null;
		LinkedListNode fast=null;

		while(fast!=null && fast.next!=null){
			fast=fast.next.next;
			slow=slow.next;
			if(slow==fast) {
				break;
			}
		}

		if(fast==null || fast.next==null){
			return null;
		}

		slow=head;
		while(fast!=slow){
			fast=fast.next;
			slow=slow.next;
		}
		return fast;
	}

	/***********Question 6 *******************************/

	/***********Question 7 check a palindrome*******************************/
	static boolean checkPalindrome1(LinkedListNode head){
		if(head==null) {
			return true;
		}
		LinkedListNode aHead=head;
		LinkedListNode node=null;
		LinkedListNode dummy=new LinkedListNode(0);

		while(head !=null){
			if(node==null){
				node=new LinkedListNode(head.data);
				dummy.next=node;
			}else{
				node.next=new LinkedListNode(head.data);
				node=node.next;
			}
			head=head.next;
		}
		dummy=dummy.next;
		LinkedListNode reversed=reverseLinkedList(aHead);
		while(dummy!=null){
			if(dummy.data != reversed.data) {
				return false;
			}
			dummy=dummy.next;
			reversed=reversed.next;
		}
		return true;
	}

	static boolean checkPalindrome2(LinkedListNode head){
		if(head==null || head.next==null) {
			return true;
		}
		LinkedListNode slow=head;
		LinkedListNode fast=head;
		Stack<LinkedListNode> stack=new Stack<LinkedListNode>();
		while(fast!=null && fast.next !=null){
			stack.push(slow);
			slow=slow.next;
			fast=fast.next.next;
		}
		if(fast!=null){  //odd number
			slow=slow.next;
		}
		while(slow != null){
			if(stack.pop().data != slow.data){
				return false;
			}
			slow=slow.next;
		}
		return true;
	}

	static Helper checkPalindromeRecurse(LinkedListNode head, int length){
		if(head==null || length==0) {
			return new Helper(null, true);
		} else if(length==1) {
			return new Helper(head.next, true);
		} else if(length==2) {
			return new Helper(head.next.next, head.data==head.next.data);
		}

		Helper res=checkPalindromeRecurse(head.next, length-2);
		if( ! res.result || res.node==null){
			return res;
		}else{
			res.result=head.data==res.node.data;
			res.node=res.node.next;
			return res;
		}

	}
	private static class Helper{

		public LinkedListNode node;
		public boolean result;

		public Helper(LinkedListNode object, boolean b) {
			// TODO Auto-generated constructor stub
			this.node=object;
			this.result=b;
		}

		@Override
		public String toString(){
			return String.valueOf(result)+" -> boolean.";
		}
	}
	/***********Question 7 *******************************/



}
