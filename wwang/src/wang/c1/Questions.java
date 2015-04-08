package wang.c1;

import java.util.Arrays;

public class Questions {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String string1="a2cdfgehop";
		String string2="poa2cdhgef";
		System.out.println(string1+ " : "+ isUniqueCharacter1(string1));
		System.out.println(string1+ " : "+ isUniqueCharacter2(string1));
		System.out.println(string1+ " ->reverse : "+ reverseString(string1));
		System.out.println(string1+ " isAnagram : "+string2 +"  "+ isAnagram1(string1, string2));
		System.out.println(string1+ " isAnagram : "+string2 +"  "+ isAnagram2(string1, string2));
		String string3="aaaaaaaaaabbbccdfe";
		System.out.println(string3+" compressed1: "+compressString1(string3));
		System.out.println(string3+" compressed2: "+compressString2(string3));
		int[][] matrix={{1,0,3},{1,4,9},{6,7,8}};
		for(int[] e: matrix){
			System.out.println(Arrays.toString(e));
		}
		System.out.println("*************");
		setZeros(matrix);
		for(int[] e: matrix){
			System.out.println(Arrays.toString(e));
		}

		String[][] pairs = {{"apple", "pleap"}, {"waterbottle", "erbottlewat"}, {"camera", "macera"}};
		for (String[] pair : pairs) {
			String word1 = pair[0];
			String word2 = pair[1];
			boolean is_rotation = isRotation(word1, word2);
			System.out.println(word1 + ", " + word2 + ": " + is_rotation);
		}

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
	static boolean isAnagram1(String str1, String str2){
		if(str1==null || str2==null) {
			return false;
		}
		if(str1.length() != str2.length()) {
			return false;
		}

		int[] cnt=new int[256];
		for(char c : str1.toCharArray() ){
			cnt[c]++;
		}
		for(char c: str2.toCharArray() ){
			cnt[c]--;
			if(cnt[c]<0) {
				return false;
			}
		}

		return true;
	}

	static boolean isAnagram2(String str1, String str2){
		if(str1==null || str2==null) {
			return false;
		}
		return sortString(str1).equals(sortString(str2));
	}

	private static String sortString(String string){
		if(string==null) {
			return null;
		}
		char[] arr=string.toCharArray();
		Arrays.sort(arr);
		return String.valueOf(arr);
	}
	/***Question3****/

	/***Question5 Compression Strings****/
	static String compressString1(String string){
		if(string==null) {
			return null;
		}
		if(string.length()<countCompression(string)) {
			return string;
		}
		StringBuilder sb=new StringBuilder();
		char last=string.charAt(0);
		int cnt=1;
		for(int i=1; i<string.length(); i++){
			if(last==string.charAt(i)) {
				cnt++;
			}else{
				sb.append(last);
				sb.append(cnt);
				cnt=1;
				last=string.charAt(i);
			}
		}
		sb.append(last);
		sb.append(cnt);
		return sb.toString();
	}
	private static int countCompression(String string){
		char last=string.charAt(0);
		int size=0;
		int cnt=1;
		for(int i=1; i<string.length();i++){
			if(last==string.charAt(i)){
				cnt++;
			}else{
				size=size+1+String.valueOf(cnt).length();
				last=string.charAt(i);
				cnt=1;
			}
		}
		size=size+1+String.valueOf(cnt).length();
		return size;
	}

	static String compressString2(String string){
		if(string==null) {
			return null;
		}
		int size=countCompression(string);
		if(string.length()< size) {
			return string;
		}
		char[] array=new char[size];
		char last=string.charAt(0);
		int cnt=1;
		int index=0;
		for(int i=1; i<string.length(); i++){
			if(last==string.charAt(i)) {
				cnt++;
			} else{
				index=setChar(array, last, index, cnt);
				cnt=1;
				last=string.charAt(i);
			}
		}
		index=setChar(array, last, index, cnt);
		return String.valueOf(array);
	}
	private static int setChar(char[] array, char c, int index, int cnt){
		array[index]=c;
		index++;
		for(char a: String.valueOf(cnt).toCharArray()){
			array[index]=a;
			index++;
		}
		return index;
	}
	/***Question5 ****/

	/***Question7 MxN matrix set 0****/
	static void setZeros(int[][] matrix){
		boolean[] row=new boolean[matrix.length];
		boolean[] collum=new boolean[matrix[0].length];

		for(int i=0; i<matrix.length;i++){
			for(int j=0; j<matrix[0].length; j++){
				if(matrix[i][j]==0){
					row[i]=true;
					collum[j]=true;
				}
			}
		}

		for(int i=0; i<matrix.length;i++){
			for(int j=0; j<matrix[0].length; j++){
				if(row[i] || collum[j]){
					matrix[i][j]=0;
				}
			}
		}

	}
	/***Question7 ****/

	/***Question8 string rotation****/
	static boolean isRotation(String str1, String str2){
		if(str1.length()!=str2.length()) {
			return false;
		}
		String str=str1+str1;
		return isSubstring(str, str2);
	}
	private static boolean isSubstring(String str1, String str2){
		if(str1.indexOf(str2)>=0) {
			return true;
		} else {
			return false;
		}
	}
	/***Question8 ****/
}
