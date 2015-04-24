package wang.c11;

import java.util.Arrays;

import wang.utility.ArrayUtility;

public class Preface {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] array=ArrayUtility.getInstance().generateRandomArray(12, 30);
        System.out.println(Arrays.toString(array));
        //Arrays.sort(array);
        //mergeSort(array);
        quickSort(array, 0, array.length-1);
        System.out.println(Arrays.toString(array));
        //System.out.println(binarySearch(array, 10));
        System.out.println(binarySearch(array, 0, array.length-1, 10));


    }

    static int binarySearch(int[] array, int target){
        int left=0, right=array.length-1, mid;
        while(left<right){
            mid=(left+right)/2;
            if(target==array[mid]) {
                return mid;
            } else if(target>array[mid]) {
                left=mid+1;
            } else {
                right=mid-1;
            }
        }
        return -1;
    }

    static int binarySearch(int[] array, int left, int right, int target){
        if(left>right) {
            return -1;
        }
        int mid=(left+right) /2;
        if(target==array[mid]) {
            return mid;
        } else if(target > array[mid]) {
            return binarySearch(array, mid+1, right, target);
        } else {
            return binarySearch(array, left, mid-1, target);
        }
    }

    static void mergeSort(int[] array){
        if(array.length<=1 ) {
            return;
        }
        int[] left=new int[array.length/2];
        int[] right=new int[array.length-left.length];

        for(int i=0; i<left.length; i++){
            left[i]=array[i];
        }
        for(int j=0; j<right.length; j++){
            right[j]=array[j+left.length];
        }

        mergeSort(left);
        mergeSort(right);
        merge(array, left, right);
    }
    private static void merge(int[] array, int[] left, int[] right){
        int i=0, j=0, index=0;
        while(i<left.length && j<right.length){
            if(left[i]<right[j] ){
                array[index]=left[i];
                i++;
            }else{
                array[index]=right[j];
                j++;
            }
            index++;
        }
        while(i<left.length){
            array[index]=left[i];
            i++;
            index++;
        }
        while(j<right.length){
            array[index]=right[j];
            j++;
            index++;
        }
    }

    static void quickSort(int[] array, int from, int to){
        if(from < to){
            int p=partition(array, from, to);
            quickSort(array, from, p-1);
            quickSort(array, p+1, to);            
        }
    }
    private static int partition(int[] array, int from, int to){
        int pivot=array[to];
        int i=from-1;
        for(int j=from; j<to; j++){
            if(array[j]<=pivot){
                i++;
                if(j!=i){
                    swap(array, i,j);
                }
            }
        }
        swap(array, i+1, to);
        return i+1;
    }

    private static void swap(int[] array, int i, int j){
        int temp=array[i];
        array[i]=array[j];
        array[j]=temp;
    }

}
