package wang.c7;

import java.util.Arrays;
import java.util.Random;

public class Shuffle {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] array=new int[52];
        for(int i=0; i<array.length;i++){
            array[i]=i;
        }
        System.out.println(Arrays.toString(array));
        shuffle(array);
        System.out.println(Arrays.toString(array));
    }

    static int[] shuffle(int[] array){
        if(array==null) {
            return null;
        }
        Random random=new Random();        
        for(int i=array.length-1; i>0; i--){
            swap(array, i, random.nextInt(i));
        }
        return array;       
    }

    static void swap(int[] array, int i, int j){
        int temp=array[i];
        array[i]=array[j];
        array[j]=temp;
    }

}
