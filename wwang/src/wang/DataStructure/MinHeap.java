package wang.DataStructure;

import java.util.ArrayList;

public class MinHeap {

	private ArrayList<Comparable> elements;

	public MinHeap(){
		elements=new ArrayList<Comparable>();
		elements.add(null);
	}

	public void add(Comparable data){
		elements.add(null);
		int index=elements.size()-1;
		while(index>1 && getParent(index).compareTo(data)>0){
			elements.set(index, getParent(index));
			index=getParentIndex(index);
		}
		elements.set(index, data);
	}

	public Comparable peek(){
		return elements.get(1);
	}

	public int size(){
		return elements.size()-1;
	}

	public Comparable remove(){
		Comparable min=elements.get(1);
		//remove the last element
		int lastIndex=elements.size()-1;
		Comparable last=elements.remove(lastIndex);
		if(lastIndex>1){
			elements.set(1, last);
			fixHeap();
		}
		return min;
	}

	private void fixHeap(){
		Comparable root=elements.get(1);
		int lastIndex=elements.size()-1;
		int index=1;
		boolean done=false;
		while(!done){
			int l=getLeftChildIndex(index);
			if(l<=lastIndex){
				Comparable child=getLeftChild(index);
				//use right child if it is smaller.
				if(getRightChildIndex(index)<=lastIndex && getRightChild(index).compareTo(child)<0){
					l=getRightChildIndex(index);
					child=getRightChild(index);
				}
				//check the smaller child is still smaller than root
				if(child.compareTo(root)<0){
					elements.set(index, child);
					index=l;
				}
				else{
					done=true;
				}
			}else{
				done=true;
			}

		}
		elements.set(index, root);
	}

	private int getLeftChildIndex(int index){
		return 2*index;
	}

	private int getRightChildIndex(int index){
		return 2*index+1;
	}

	private Comparable getLeftChild(int index){
		return elements.get(2*index);
	}

	private Comparable getRightChild(int index){
		return elements.get(2*index+1);
	}

	private int getParentIndex(int index){
		return index/2;
	}

	private Comparable getParent(int index){
		return elements.get(index/2);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MinHeap minHeap=new MinHeap();
		minHeap.add(50); minHeap.add(150);
		minHeap.add(30); minHeap.add(10);
		minHeap.add(120); minHeap.add(5);
		minHeap.add(80); minHeap.add(76);
		minHeap.add(60); minHeap.add(12);
		minHeap.add(100); minHeap.add(19);

		for(Comparable e : minHeap.elements){
			if(e!=null) {
				System.out.print(e+"  ");
			}
		}

		System.out.println("\nsize:  "+minHeap.size());
		System.out.println("remove:  "+minHeap.remove());
		System.out.println("size:  "+minHeap.size());
		System.out.println("remove:  "+minHeap.remove());
		System.out.println("size:  "+minHeap.size());

	}

}
