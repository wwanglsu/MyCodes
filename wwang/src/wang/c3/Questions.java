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

        MyQueue<Integer> queue=new MyQueue<Integer>();
        queue.add(100); queue.add(29); queue.add(20); queue.add(4); queue.add(78); queue.add(60);

        System.out.println("queue size: "+queue.size());
        System.out.println(queue.remove());

        Stack ss=new Stack();
        ss.add(100); ss.add(29); ss.add(20); ss.add(4); ss.add(78); ss.add(60);
        Stack re=sort(ss);
        while(! re.isEmpty()){
            System.out.print(re.pop()+ "  " );
        }


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

    /********* 3.5 MyQueue with two stacks ************/
    static class MyQueue<T>{
        Stack<T> newStack;
        Stack<T> oldStack;

        public MyQueue(){
            newStack=new Stack<T>();
            oldStack=new Stack<T>();
        }

        public int size(){
            return oldStack.size()+newStack.size();
        }

        public void add(T e){
            newStack.add(e);
        }

        public T peek(){
            shiftStacks();
            return oldStack.peek();
        }

        public T remove(){
            shiftStacks();
            return oldStack.pop();
        }

        private void shiftStacks(){
            if(oldStack.isEmpty()){
                while(!newStack.isEmpty()) {
                    oldStack.push(newStack.pop());
                }
            }
        }
    }
    /********* 3.5 MyQueue with two stacks ************/

    /********* 3.6 Sort stack with ascending ************/
    static Stack<Integer> sort(Stack<Integer> s){
        Stack<Integer> r=new Stack<Integer>();
        while(!s.isEmpty()){
            int temp=s.pop();
            while(!r.isEmpty() && temp< r.peek()){
                s.push(r.pop());
            }
            r.push(temp);
        }
        return r;
    }
    /********* 3.6 Sort stack with ascending ************/







}
