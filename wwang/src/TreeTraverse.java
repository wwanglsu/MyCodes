import java.util.LinkedList;

import wang.DataStructure.Node;

public class TreeTraverse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node root=new Node(7);
		root.left=new Node(5); root.right=new Node(10);
		root.left.parent=root; root.right.parent=root;
		root.left.left=new Node(3); root.left.right=new Node(6);
		root.left.left.parent=root.left; root.left.right.parent=root.left;
		root.right.left=new Node(9); root.right.right=new Node(12);
		root.right.left.parent=root.right; root.right.right.parent=root.right;

		inOrder_Recursive(root);
		System.out.println();
		preOrder_Recursive(root);
		System.out.println();
		postOrder_Recursive(root);
		System.out.println();
		System.out.println("Minimum node: "+ minNode(root));
		System.out.println("Maximum node: "+ maxNode(root));
		System.out.println("Search node: "+ searchNode(root, 10));
		System.out.println("successor node: "+ successor_InOrder(root.right));
		System.out.println("predecessor node: "+ predecessor_InOrder(root.left.left));
		System.out.println("last k nodes");
		last_K_inOrder_Recursive(root, 4);
		System.out.println("\nIn Order Iterative:");
		inOrder_Iterative(root);
		System.out.println("\nPre Order Iterative:");
		preOrder_Iterative(root);
		System.out.println("\nPost Order Iterative 2 stacks:");
		postOrder_Iterative_TwoStacks(root);
		System.out.println("\nPost Order Iterative:");
		postOrder_Iterative(root);
		System.out.println("\nLevel Order Iterative:");
		levelOrder(root);

	}

	/**********Recursive**************/
	static void inOrder_Recursive(Node root){
		if(root != null){
			inOrder_Recursive(root.left);
			System.out.print(root+ "   ");
			inOrder_Recursive(root.right);
		}
	}
	static int count;
	static void last_K_inOrder_Recursive(Node root, int k){
		count=k;
		if(root!=null && count>0){
			last_K_inOrder_Recursive(root.right, k);
			if(count>0) {
				System.out.print(root+","+count+"  ");
			}
			count--;
			k=count;
			last_K_inOrder_Recursive(root.left, k);
		}
	}

	static void preOrder_Recursive(Node root){
		if(root != null){
			System.out.print(root+"   ");
			preOrder_Recursive(root.left);
			preOrder_Recursive(root.right);
		}
	}

	static void postOrder_Recursive(Node root){
		if(root != null){
			postOrder_Recursive(root.left);
			postOrder_Recursive(root.right);
			System.out.print(root+"   ");
		}
	}

	static Node minNode(Node root){
		if(root != null && root.left !=null){
			return minNode(root.left);
		}else {
			return root;
		}
	}

	static Node maxNode(Node root){
		if(root != null && root.right != null){
			return maxNode(root.right);
		}else{
			return root;
		}
	}

	static Node searchNode(Node root, Object target){
		if(root !=null){
			if(root.compareTo(target)==0) {
				return root;
			} else if(root.compareTo(target)>0) {
				return searchNode(root.left, target);
			} else {
				return searchNode(root.right, target);
			}
		}else {
			return root;
		}
	}

	/***************Iterative******************/
	static void inOrder_Iterative(Node root){
		if(root==null) {
			return;
		}
		LinkedList<Node> stack=new LinkedList<Node>();
		while(true){
			while(root!=null){
				stack.push(root);
				root=root.left;
			}
			if(stack.isEmpty()) {
				return;
			}
			root=stack.pop();
			System.out.print(root+"   ");
			root=root.right;
		}
	}

	static void preOrder_Iterative(Node root){
		if(root==null) {
			return;
		}
		LinkedList<Node> stack=new LinkedList<Node>();
		boolean done=false;
		while(true){
			while(root!=null){
				System.out.print(root+"   ");
				stack.push(root);
				root=root.left;
			}
			if(stack.isEmpty()){
				return;
			}
			root=stack.pop();
			root=root.right;
		}
	}

	static void postOrder_Iterative_TwoStacks(Node root){
		if(root==null) {
			return;
		}
		LinkedList<Node> stack=new LinkedList<Node>();
		LinkedList<Node> output=new LinkedList<Node>();
		stack.push(root);
		while(!stack.isEmpty()){
			output.push(stack.peek());
			root=stack.pop();
			if(root.left!=null) {
				stack.push(root.left);
			}
			if(root.right!=null) {
				stack.push(root.right);
			}
		}
		while(!output.isEmpty()){
			System.out.print(output.pop()+ "   " );
		}
	}

	static void postOrder_Iterative(Node root){
		if(root==null) {
			return;
		}
		LinkedList<Node> stack=new LinkedList<Node>();
		while(true){
			if(root!=null){
				if(root.right!=null) {
					stack.push(root.right);
				}
				stack.push(root);
				root=root.left;
				continue;
			}
			if(stack.isEmpty()) {
				return;
			}
			root=stack.pop(); //it is the leaf now
			if(root.right!=null && !stack.isEmpty() && root.right==stack.peek()){
				stack.pop();
				stack.push(root);
				root=root.right;
			}else{
				System.out.print(root+"   ");
				root=null;
			}
		}
	}

	static void levelOrder(Node root){
		if(root==null) {
			return;
		}
		LinkedList<Node> queue=new LinkedList<Node>();
		queue.offer(root);
		while(!queue.isEmpty()){
			root=queue.poll();
			System.out.print(root+"   ");
			if(root.left!=null) {
				queue.offer(root.left);
			}
			if(root.right!=null) {
				queue.offer(root.right);
			}
		}
	}


	/******************************************/
	static Node successor_InOrder(Node node){
		if(node==null) {
			return null;
		}

		if(node.parent==null || node.right !=null){
			return minNode(node.right);
		}else{
			Node runner=node;
			Node parent=runner.parent;
			while(parent!=null && runner ==parent.right){
				runner=parent;
				parent=parent.parent;
			}
			return parent;
		}
	}


	static Node predecessor_InOrder(Node node){
		if(node==null) {
			return null;
		}

		if(node.parent==null || node.left !=null){
			return maxNode(node.left);
		}else{
			Node runner=node;
			Node parent=runner.parent;
			while(parent!=null && parent.left==runner){
				runner=parent;
				parent=parent.parent;
			}
			return parent;
		}
	}



}
