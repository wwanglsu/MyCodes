package wang.utility;

import java.util.Random;

public class ArrayUtility {
	
	private static ArrayUtility Instance=null;
	Random generator =new Random();
	
	private ArrayUtility(){}
	
	public static synchronized ArrayUtility getInstance(){
		if(Instance==null){
			Instance=new ArrayUtility();
		}
		return Instance;
	}
	
	public int[] generateRandomArray(int length, int maxNum){
		int[] a=new int[length];
		for(int i=0; i<length; i++){
			a[i]=generator.nextInt(maxNum+1);
		}
		return a;
	}
}
