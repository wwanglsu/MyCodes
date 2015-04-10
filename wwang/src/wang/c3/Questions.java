package wang.c3;

import java.util.Arrays;
import java.util.Stack;

import wang.utility.ArrayUtility;

public class Questions {

	public static void main(String[] args){
		StackWithMin1 stack1=new StackWithMin1();

		int[] arr=ArrayUtility.getInstance().generateRandomArray(10, 20);
		System.out.println(Arrays.toString(arr));
		for(int e: arr){
			stack1.push(e);
		}
		System.out.print(stack1.min());
	}

	/*********3.2 stack with min() O(1) ************/
	static class StackWithMin1 extends Stack<NodeWithMin>{
		public void push(int value){
			int newMin=Math.min(value, min());
			super.push(new NodeWithMin(value, newMin));
		}

		public int min(){
			if(this.isEmpty()) {
				return Integer.MAX_VALUE;
			} else {
				return peek().min;
			}
		}
	}
	static class NodeWithMin{
		public int value;
		public int min;
		public NodeWithMin(int value, int min){
			this.value=value;
			this.min=min;
		}
	}
	/*********3.2 stack with min() O(1) ************/
	/*********3.2 stack with min() O(1) ************/


}
