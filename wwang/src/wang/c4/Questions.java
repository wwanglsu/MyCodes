package wang.c4;

import java.util.ArrayList;
import java.util.LinkedList;

import wang.DataStructure.Node;

public class Questions {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array={1,2,3,4,5,6,7,8,9};
		Node rootNode=createBST(array);
		rootNode.left.left.left=new Node(0);
		rootNode.left.left.left.left=new Node(-1);

		System.out.println("root value:  "+rootNode);
		TreeTraversal.levelOrder(rootNode);
		System.out.println("\ncheckHeight:  "+checkHeight(rootNode));
		System.out.println("getHeight:  "+getHeight(rootNode));

		Graph graph=createGraph();
		DirectedNode[] graphDirectedNodes=graph.getNodes();
		System.out.println("has route:  "+hasRoute(graph, graphDirectedNodes[2], graphDirectedNodes[5]));

		LinkedList<Integer> sl=new LinkedList<Integer>();
		sl.add(null);
		System.out.println(sl.size());
		System.out.println(sl.isEmpty());
		System.out.println(sl.get(0));

		Node root=new Node(20);
		root.left=new Node(10); root.right=new Node(30); //root.left.right=new Node(25);
		System.out.println("is valid BST: "+checkBST_MinMax(root));


	}
	private static Graph createGraph(){
		Graph graph=new Graph(6);
		DirectedNode[] nodes=new DirectedNode[6];
		nodes[0]=new DirectedNode("a", 3);
		nodes[1]=new DirectedNode("b", 0);
		nodes[2]=new DirectedNode("c", 0);
		nodes[3]=new DirectedNode("d", 1);
		nodes[4]=new DirectedNode("e", 1);
		nodes[5]=new DirectedNode("f", 0);

		nodes[0].addAdjacent(nodes[1]);
		nodes[0].addAdjacent(nodes[2]);
		nodes[0].addAdjacent(nodes[3]);
		nodes[3].addAdjacent(nodes[4]);
		nodes[4].addAdjacent(nodes[5]);

		for(int i=0; i<6; i++){
			graph.addNode(nodes[i]);
		}

		return graph;
	}

	/*********4.1 check tree is balance**********/
	public static boolean isBalance1(Node root){
		if(root==null) {
			return true;
		}
		int heightDiff=getHeight(root.left)-getHeight(root.right);
		if(Math.abs(heightDiff) >1) {
			return false;
		}
		return isBalance1(root.left) && isBalance1(root.right);
	}
	private static int getHeight(Node root){
		if(root==null) {
			return 0;
		}
		return Math.max(getHeight(root.left), getHeight(root.right))+1;

	}

	public static boolean isBalance2(Node root){
		if(checkHeight(root)==-1) {
			return false;
		} else {
			return true;
		}
	}
	private static int checkHeight(Node root){ //get balanced BST height
		if(root==null) {
			return 0;
		}

		int leftHeight=checkHeight(root.left);
		if(leftHeight==-1) {
			return -1;
		}

		int rightHeight=checkHeight(root.right);
		if(rightHeight==-1) {
			return -1;
		}

		int heightDiff=leftHeight-rightHeight;

		if(Math.abs(heightDiff) > 1) {
			return -1;
		} else {
			return Math.max(leftHeight, rightHeight)+1;
		}

	}
	/*********4.1 check tree is balance**********/

	/*********4.2 directed graph, find out whether there is a route**********/
	public static boolean hasRoute(Graph g, DirectedNode start, DirectedNode end){
		LinkedList<DirectedNode> queue=new LinkedList<DirectedNode>();
		for(DirectedNode e: g.getNodes() ){
			e.state=State.Unvisited;
		}

		start.state=State.Visiting;
		queue.offer(start);
		DirectedNode u;
		while(!queue.isEmpty()){
			u=queue.poll();
			if(u!=null){
				for(DirectedNode ee: u.getAdjacent()){
					if(ee.state==State.Unvisited){
						if(ee==end) {
							return true;
						} else{
							ee.state=State.Visiting;
							queue.offer(ee);
						}
					}
				}
				u.state=State.Visited;
			}
		}
		return false;
	}
	/*********4.2 directed graph, find out whether there is a route**********/

	/*********Question 4.3 create BST************/
	public static Node createBST(int[] array){
		return createBST(array, 0, array.length-1);
	}
	private static Node createBST(int[] array, int start, int end){
		if(start>end) {
			return null;
		}
		int mid=(start+end)/2;
		Node root=new Node(array[mid]);
		root.left=createBST(array, start, mid-1);
		root.right=createBST(array, mid+1, end);
		return root;
	}
	/*********Question 4.3 create BST**************/

	/*********Question 4.4 Level Traversal************/
	static ArrayList<LinkedList<Node>> createLevelList(Node root){
		ArrayList<LinkedList<Node>> lists=new ArrayList<LinkedList<Node>>();
		createLevelList(root, lists, 0);
		return lists;
	}
	private static void createLevelList(Node root, ArrayList<LinkedList<Node>> lists, int level){  //DFS
		if(root==null) {
			return;
		}
		LinkedList<Node> list=null;
		if(lists.size()==level){
			list=new LinkedList<Node>();
			lists.add(list);
		}else{
			list=lists.get(level);
		}

		list.add(root);
		createLevelList(root.left, lists, level+1);
		createLevelList(root.right, lists, level+1);
	}

	static ArrayList<LinkedList<Node>> BFSLevelList(Node root){
		ArrayList<LinkedList<Node>> result=new ArrayList<LinkedList<Node>>();
		LinkedList<Node> current=new LinkedList<Node>();

		if(root !=null ) {
			current.add(root);
		}

		while(current.size() > 0){
			result.add(current);
			LinkedList<Node> parents=current;
			current=new LinkedList<Node>();
			for(Node ee : parents){
				if(ee.left != null) {
					current.add(ee.left);
				}
				if(ee.right != null) {
					current.add(ee.right);
				}
			}
		}

		return result;
	}
	/*********Question 4.4 Level Traversal************/

	/*********Question 4.5 check valid BST************/
	static Integer last_printed=Integer.MIN_VALUE;
	static boolean checkBST(Node root){ // in-order recursive
		if(root==null ) {
			return true;
		}

		if(!checkBST(root.left)) {
			return false;
		}

		if((int)root.data <= last_printed) {
			return false;
		}

		last_printed=(int)root.data;

		if(!checkBST(root.right)) {
			return false;
		}

		return true;
	}

	static boolean checkBST_MinMax(Node root){
		return checkBST_MinMax(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	private static boolean checkBST_MinMax(Node root, int min, int max){
		if(root==null) {
			return true;
		}

		if((int)root.data <=min || (int)root.data >max) {
			return false;
		}

		if(!checkBST_MinMax(root.left, min, (int)root.data) || !checkBST_MinMax(root.right, (int)root.data, max)) {
			return false;
		}

		return true;
	}
	/*********Question 4.5 check valid BST************/

	/*********4.6 in-order successor******************/
	public static Node inorderSuccessor(Node root){
		if(root==null) {
			return null;
		}
		if(root.right != null){
			return minNode(root.right);
		}else{
			Node runner=root;
			Node parent=runner.parent;
			while(parent != null && runner==parent.right){
				runner=parent;
				parent=parent.parent;
			}
			return parent;
		}
	}
	private static Node minNode(Node root){
		if(root==null) {
			return null;
		}
		while(root.left != null){
			root=root.left;
		}
		return root;
	}
	/*********4.6 in-order successor******************/

	/*********Question 4.7 first common ancestor************/
	static Node commonAncestor(Node root, Node p, Node q){
		if(!covers(root, p) || !covers(root, q)) {
			return null;
		}
		return commonAncestor_Recursive(root, p, q);
	}
	static Node commonAncestor_Recursive(Node root, Node p, Node q){
		if(root==null) {
			return null;
		}
		if(root==p || root==q) {
			return root;
		}

		boolean is_p_onLeft=covers(root.left, p);
		boolean is_q_onLeft=covers(root.left, q);

		if(is_p_onLeft != is_q_onLeft)
		{
			return root; // p and q on different sides
		}

		Node child_side=is_p_onLeft ? root.left : root.right;

		return commonAncestor(child_side, p, q);

	}
	private static boolean covers(Node root, Node node){
		if(root==null) {
			return false;
		}
		if(root==node) {
			return true;
		}
		return covers(root, node.left) || covers(root, node.right);
	}


	/*********Question 4.7 first common ancestor************/

}
