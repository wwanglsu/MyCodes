package wang.c3;

import java.util.Arrays;
import java.util.Stack;

import wang.utility.ArrayUtility;

public class Questions {

    public static void main(String[] args) {
        StackWithMin1 stack1 = new StackWithMin1();
        StackWithMin2 stack2 = new StackWithMin2();

        int[] arr = ArrayUtility.getInstance().generateRandomArray(10, 20);
        System.out.println(Arrays.toString(arr));
        for (int e : arr) {
            stack1.push(e);
            stack2.push(e);
        }
        System.out.println(stack1.min());
        System.out.println(stack1.pop().value);
        System.out.println(stack2.min());
        System.out.println(stack2.pop());
    }

    /********* 3.2 stack with min() O(1) ************/
    static class StackWithMin1 extends Stack<NodeWithMin> {
        public void push(int data) {
            int newMin = Math.min(data, this.min());
            super.push(new NodeWithMin(data, newMin));
        }
        public int min() {
            if (this.isEmpty()) {
                return Integer.MAX_VALUE;
            } else {
                return this.peek().min;
            }
        }
        @Override
        public NodeWithMin pop() {
            if (this.isEmpty()) {
                return null;
            } else {
                return super.pop();
            }
        }
    }
    private static class NodeWithMin {
        int value;
        int min;
        NodeWithMin(int value, int min) {
            // TODO Auto-generated constructor stub
            this.value = value;
            this.min = min;
        }
    }

    static class StackWithMin2 extends Stack<Integer> {
        Stack<Integer> minsStack;

        public StackWithMin2() {
            minsStack = new Stack<Integer>();
        }
        public void push(int value) {
            if (value < min()) {
                minsStack.push(value);
            }

            super.push(value);
        }

        public Integer min() {
            if (minsStack.isEmpty()) {
                return Integer.MAX_VALUE;
            } else {
                return minsStack.peek();
            }
        }

        @Override
        public Integer peek(){
            return super.peek();
        }

        @Override
        public Integer pop() {
            Integer value = super.pop();
            if (value == min()) {
                minsStack.pop();
            }
            return value;
        }

    }
    /********* 3.2 stack with min() O(1) ************/
    /********* 3.2 stack with min() O(1) ************/

}
