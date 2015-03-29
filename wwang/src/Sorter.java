import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import wang.utility.ArrayUtility;


public class Sorter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a=ArrayUtility.getInstance().generateRandomArray(10, 30);
		System.out.println(Arrays.toString(a));
		//insertionSort_CLRS(a);
		//mergeSort(a);
		quickSort(a, 0, a.length-1);
		System.out.println(Arrays.toString(a));
		//countingSort(a, 0, 30);
		//System.out.println(Arrays.toString(a));
		//System.out.println(Arrays.toString(countingSort_CLRS(a,0,30) ));
		//System.out.println(Arrays.toString(radixSort(a) ));

		double[] arr={0.12,0.05,0.098,0.18,0.6,0.8,0.99,0.1,0.005,0.55,0.7,0.4,0.3};
		System.out.println(Arrays.toString(bucketSort(arr) ));

	}

	static void countingSort(int[] array, int min, int max){
		int[] c=new int[max-min+1];
		for(int i:array){
			c[i-min]++;
		}

		int index=0;
		for(int i=min; i<=max;i++){
			while(c[i-min]>0){
				array[index]=i;
				index++;
				c[i-min]--;
			}
		}
	}

	static int[] countingSort_CLRS(int[] array, int min, int max){//stable, better.
		int[] c=new int[max-min+1];
		for(int n:array){
			c[n-min]++;
		}
		for(int i=1; i<c.length; i++){
			c[i]=c[i]+c[i-1];
		}
		int[] b=new int[array.length];
		for(int i=array.length-1; i>=0; i--){
			int index=array[i]-min;
			int pos=--c[index];
			b[pos]=array[i];
		}
		return b;
	}

	static int[] radixSort(int[] array){
		int[] b=new int[array.length];
		int max=array[0];
		for(int i=1;i<array.length; i++){
			max=Math.max(max, array[i]);
		}
		int exp=1;
		while(max/exp >0){
			int[] c=new int[10];
			for(int i=0; i<array.length; i++){
				c[(array[i]/exp)%10]++;
			}
			for(int i=1; i<array.length; i++){
				c[i]=c[i]+c[i-1];
			}
			for(int i=array.length-1; i>=0; i--){
				b[--c[(array[i]/exp)%10] ]=array[i];
			}
			for(int i=0; i<array.length;i++){
				array[i]=b[i];
			}
			exp=exp*10;
		}
		return b;
	}

	static double[] bucketSort(double[] array){
		ArrayList<Double>[] buckets=new ArrayList[array.length];
		for(int i=0; i<buckets.length; i++){
			buckets[i]=new ArrayList<Double>(); //initialization
		}
		for(int i=0;i<array.length;i++){
			buckets[(int)(array[i]*array.length)].add(array[i]);
		}
		for(ArrayList<Double> e: buckets){
			Collections.sort(e);
		}

		int index=0;
		for(ArrayList<Double> e: buckets){
			if(e.isEmpty()) {
				continue;
			}
			for(int i=0; i<e.size(); i++){
				array[index]=e.get(i);
				index++;
			}
		}

		return array;
	}

	static void insertionSort_CLRS(int[] array){// better method.

		for(int i=1; i<array.length; i++){ //poker on the table
			int j=i;
			while(j-1>=0 && array[j-1]>array[j] ){
				swap(array, j-1, j);
				j--;
			}
		}

	}

	static void InsertionSort(int[] array){
		for(int i=1; i<array.length; i++){
			int next=array[i];
			int j=i-1;
			while(j>=0 && array[j] > next){
				array[j+1]=array[j];
				j--;
			}
			array[j]=next;
		}
	}

	static void swap(int[] array, int i, int j){
		int temp=array[i];
		array[i]=array[j];
		array[j]=temp;
	}

	static void mergeSort(int[] array){
		if(array==null || array.length<=1) {
			return;
		}

		int[] first=new int[array.length/2];
		int[] second=new int[array.length-first.length];

		for(int i=0; i<first.length; i++){
			first[i]=array[i];
		}
		for(int j=0; j<second.length; j++){
			second[j]=array[j+first.length];
		}

		mergeSort(first);
		mergeSort(second);

		merge(array, first, second);
	}
	private static void merge(int[] a, int[] first, int[] second){
		int iFirst=0, iSecond=0, j=0;
		while(iFirst<first.length && iSecond<second.length){
			if(first[iFirst]<second[iSecond]){
				a[j]=first[iFirst];
				iFirst++;
			}else{
				a[j]=second[iSecond];
				iSecond++;
			}
			j++;
		}
		while(iFirst<first.length){
			a[j]=first[iFirst];
			iFirst++;
			j++;
		}
		while(iSecond<second.length){
			a[j]=second[iSecond];
			iSecond++;
			j++;
		}
	}

	static void quickSort(int[] a, int from, int to){

		if(from<to){
			int p=partition(a, from, to);
			quickSort(a, from, p-1);
			quickSort(a, p+1, to);
		}
	}
	private static int partition(int[] array, int from, int to){
		int pivot=array[to];
		int i=from-1;
		for(int j=from; j<to; j++){
			if(array[j]<=pivot){
				i++;
				if(i!=j) {
					swap(array, i, j);
				}
			}
		}
		swap(array, i+1, to);
		return i+1;
	}



}