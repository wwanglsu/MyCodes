package wang.DataStructure;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashSet extends AbstractSet{
	private int size;
	private Node[] buckets;

	public HashSet(int bucketLength) {
		// TODO Auto-generated constructor stub
		size=0;
		buckets=new Node[bucketLength];
	}

	@Override
	public boolean add(Object element){

		int h=element.hashCode();
		if(h<0) {
			h=-h;
		}
		h=h%buckets.length;

		Node current=buckets[h];
		while(current !=null){
			if(current.data.equals(element)){
				return false;
			}
			current=current.next;
		}
		Node newNode=new Node(element);
		newNode.next=buckets[h];
		buckets[h]=newNode; // add new element into the first position
		size++;
		return true;
	}

	@Override
	public boolean contains(Object element){
		int h=element.hashCode();
		if(h<0) {
			h=-h;
		}
		h=h%buckets.length;

		Node current=buckets[h];
		while(current!=null){
			if(current.data.equals(element)) {
				return true;
			}
			current=current.next;
		}
		return false;
	}

	@Override
	public boolean remove(Object element){
		int h=element.hashCode();
		if(h<0) {
			h=-h;
		}
		h=h%buckets.length;
		Node current=buckets[h];
		Node previous=null;
		while(current !=null){
			if(current.data.equals(element)){
				if(previous==null) {
					buckets[h]=current.next;
				} else {
					previous.next=current.next;
				}
				size--;
				return true;
			}
			previous=current;
			current=current.next;
		}
		return false;
	}

	@Override
	public Iterator iterator() {
		return new HashSetIterator();
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	class Node{
		Object data;
		Node next;
		Node(Object data){
			this.data=data;
		}
	}

	class HashSetIterator implements Iterator{
		private int previousBucket;
		private int bucket;
		private Node previous;
		private Node current;

		public HashSetIterator() {
			// TODO Auto-generated constructor stub
			previousBucket=-1;
			bucket=-1;
			previous=null;
			current=null;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			if(current!=null && current.next!=null) {
				return true;
			}
			for(int i=bucket+1; i<buckets.length; i++){
				if(buckets[i] !=null) {
					return true;
				}
			}
			return false;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			previous=current;
			previousBucket=bucket;
			if(current==null|| current.next==null){
				bucket++;
				while(bucket<buckets.length && buckets[bucket]==null){
					bucket++;
				}
				if(bucket<buckets.length) {
					current=buckets[bucket];
				} else {
					throw new NoSuchElementException();
				}
			}else{
				current=current.next;
			}
			return current.data;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			if(previous !=null && previous.next==current) {
				previous.next=current.next;
			}else if(previousBucket<bucket) {
				buckets[bucket]=current.next;
			} else {
				throw new IllegalStateException();
			}
			current=previous;
			bucket=previousBucket;
		}

	}

}
