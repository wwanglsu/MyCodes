package wang.c1;

public class Questions {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String string="abcdfgehop";
		System.out.println(string+ " : "+ isUniqueCharacter1(string));
		System.out.println(string+ " : "+ isUniqueCharacter2(string));
		System.out.println(string+ " reverse : "+ reverseString(string));
	}

	/***Question1****/
	static boolean isUniqueCharacter1(String string){
		int check=0;
		for(int i=0; i<string.length(); i++){
			int var=string.charAt(i)-'a';
			if((check & (1<<var) ) > 0 ){
				return false;
			}
			check = check | (1<<var);
		}
		return true;
	}

	static boolean isUniqueCharacter2(String string){
		boolean[] array=new boolean[256];
		for(int i=0; i< string.length(); i++){
			int pos=string.charAt(i);
			if(array[pos]){
				return false;
			}
			array[pos]=true;
		}
		return true;
	}
	/***Question1****/

	/***Question2****/
	static String reverseString(String string){
		if(string==null) {
			return null;
		}
		char[] array=string.toCharArray();
		int left=0, right=array.length-1;
		while(left<right){
			char temp=array[left];
			array[left]=array[right];
			array[right]=temp;
			left++; right--;
		}
		return new String(array);
	}
	/***Question2****/

	/***Question3****/
	static boolean isAnagram(String str1, String str2){
		if(str1==null || str2==null) {
			return false;
		}
		if(str1.length() != str2.length()) {
			return false;
		}
		return false;
	}
	/***Question3****/



}
