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
		System.out.println("successor node: "+ successor_InOrder(root.right.left));



	}
	/**********Recursive**************/
	static void inOrder_Recursive(Node root){
		if(root != null){
			inOrder_Recursive(root.left);
			System.out.print(root+ "   ");
			inOrder_Recursive(root.right);
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

	/*********************************/
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



}
