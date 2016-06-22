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
	//better version
	public List<Integer> preorderTraversal(TreeNode root) {
	        Stack<TreeNode> s = new Stack<TreeNode>();
	        List<Integer> res = new LinkedList<Integer>();
	        if(root!=null) s.push(root);
	        while(!s.isEmpty()){
	            TreeNode curr = s.pop();
	            res.add(curr.val);
	            if(curr.right!=null) s.push(curr.right);
	            if(curr.left!=null) s.push(curr.left);
	        }
	        return res;
	}
	//better version
	public List<Integer> preorderTraversal(TreeNode root) {
	        Stack<TreeNode> stack = new Stack<>();
	        List<Integer> result = new ArrayList<>();
	        pushAllLeft(stack, root, result);
	        while (!stack.isEmpty()) {
	            TreeNode cur = stack.pop();//栈顶必定是已经添加到过结果里并且访问完了左孩子，该访问右孩子了
	            if (cur.right != null) {
	                cur = cur.right;
	                pushAllLeft(stack, cur, result);
	            }
	        }
	        return result;
	}
	public void pushAllLeft(Stack<TreeNode> stack, TreeNode root, List<Integer> result) {
	    while (root != null) {
	        stack.push(root);
	        result.add(root.val);
	        root = root.left;
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
	
	//better version
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		//先将最左边的节点都push进栈
		if(root != null){
		    pushAllTheLeft(stack, root);
		}
		
		while(!stack.isEmpty()){
		    TreeNode curr = stack.pop();
		    res.add(curr.val); // pre-order, 这个代码 放在pushAllTheLeft()
		    if(curr.right != null){
		        pushAllTheLeft(stack, curr.right);
		    }
		}
		
		return res;
	}
	//push all root's left and left's left ... (including root itself) into the stack
	private void pushAllTheLeft(Stack<TreeNode> stack, TreeNode root){
		while(root!=null){
		    stack.push(root);
		    root = root.left;
		}
	}
	
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
	
	//better version
	//Post-Order: 这个版本，跟pre-order的第一版本很类似，也类似BFS的Queue写法
	//后序遍历的顺序是left - right - root，虽然我们不方便直接得到这个顺序，但是它的逆序还是很好得到的，我们可以用
	//root - right - left的顺序遍历树，然后反向添加结果就行: （这个更好，简洁）
	public List<Integer> postorderTraversal(TreeNode root) {
	        Stack<TreeNode> stk = new Stack<TreeNode>();
	        if(root != null) stk.push(root);
	        LinkedList<Integer> res = new LinkedList<Integer>();
	        while(!stk.isEmpty()){
	            TreeNode curr = stk.pop();
	            // 先添加左后添加右，就是先访问右后访问左
	            if(curr.left != null) stk.push(curr.left);
	            if(curr.right != null) stk.push(curr.right);
	            // 反向添加结果，每次加到最前面
	            res.offerFirst(curr.val);
	        }
	        return res;
	}
	//OR: 下面这个版本，跟pre-order，in-order 很类似。 better version
	public List<Integer> postorderTraversal(TreeNode root) {
	        Stack<TreeNode> stack = new Stack<>();
	        LinkedList<Integer> result = new LinkedList<Integer>();//注意这里要用linkedlist声明
	        pushAllRight(stack, root, result);
	        while (!stack.isEmpty()) {
	            TreeNode cur = stack.pop();
	            if (cur.left != null) {
	                cur = cur.left;
	                pushAllRight(stack, cur, result);
	            }
	        }
	        return result;
	} 
	public void pushAllRight(Stack<TreeNode> stack, TreeNode root, LinkedList<Integer> result) {
	        while (root != null) {
	            stack.push(root);
	            result.addFirst(Integer.valueOf(root.val));
	            root = root.right;
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

	/****diameter of a tree****************/
	static int getDiameter(Node root){
		if(root==null) {
			return 0;
		}
		int rootDiameter=getHeight(root.left)+getHeight(root.right)+1;
		int leftDiameter=getDiameter(root.left);
		int rightDiameter=getDiameter(root.right);
		return Math.max(rootDiameter, Math.max(leftDiameter, rightDiameter));
	}

	static int getHeight(Node root){
		if(root==null) {
			return 0;
		}
		return Math.max(getHeight(root.left), getHeight(root.right))+1;
	}

}
