package wang.c11;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import wang.utility.ArrayUtility;

public class Questions {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] arr = {5,7,9,9,10,21,25,30,32,0,0,0,0,0};//{1, 3, 4, 5, 6, 8, 10, 0, 0, 0, 0, 0};
        int[] b = {2,3,8,15,301};//{4, 7, 9, 10, 12};
        merge(arr, b, 9, 5); System.out.println(Arrays.toString(arr));

        int[] a = { 2, 3, 6, 2, 2, 2, 2, 2 , 2 , 2 };
        System.out.println(search(a, 0, a.length - 1, 2)); System.out.println(search(a, 0, a.length - 1, 3));
        System.out.println(search(a, 0, a.length - 1, 4)); System.out.println(search(a, 0, a.length - 1, 1)); System.out.println(search(a, 0, a.length - 1, 6));

        String[] array = {"duck", "apple", "banana", "carrot", "ele", "papel", "tarroc", "cudk", "eel", "lee"};
        groupAnagrams1(array); System.out.println(Arrays.toString(array));

        String[] stringList = {"apple", "", "", "appls", "banana", "", "", "", "carrot", "duck", "", "", "eel", "", "flower"};
        System.out.println("binary search strings: "+searchStrings(stringList, "banana", 0, stringList.length-1));

        int[][] matrix={{1,2,3,4,5},{3,7,11,15,19},{5,15,25,35,45},{20,40,60,80,100}};
        System.out.println(ArrayUtility.getInstance().printMatrix(matrix));
        System.out.println("binary search matrix: "+findElement1(matrix, 15));
    }

    /****11.1 merge two sorted arrays**********************************/
    static void merge(int[] a, int[] b, int aLength, int bLength){
        int indexA=aLength-1;
        int indexB=bLength-1;
        int mergeIndex=aLength+bLength-1;
        while(indexA >=0 && indexB >=0){
            if(a[indexA]>b[indexB]){
                a[mergeIndex]=a[indexA];
                indexA--;
            }else{
                a[mergeIndex]=b[indexB];
                indexB--;
            }
            mergeIndex--;
        }
        while(indexB>=0){ // copy remaining elements from b into place
            a[mergeIndex]=b[indexB];
            indexB--;
            mergeIndex--;
        }
    }
    /****11.1 merge two sorted arrays**********************************/

    /****11.2 group all anagrams*********************/
    static class AnagramComparator implements Comparator<String>{
        @Override
        public int compare(String str1, String str2){
            return sortChars(str1).compareTo(sortChars(str2));
        }
        public String sortChars(String string){
            char[] c=string.toCharArray();
            Arrays.sort(c);
            return new String(c);
        }
    }
    static String[] groupAnagrams1(String[] array){
        Arrays.sort(array, new AnagramComparator());
        return array;
    }

    static String[] groupAnagrams2(String[] array){
        if(array==null){
            return null;
        }
        Map<String, LinkedList<String>> table=new TreeMap<String, LinkedList<String>>();
        for(String e : array){
            String sortedString=new AnagramComparator().sortChars(e);
            if(!table.containsKey(sortedString)){
                table.put(sortedString, new LinkedList<String>());
            }
            table.get(sortedString).add(e);
        }
        int index = 0;
        for (String key : table.keySet()) {
            LinkedList<String> list = table.get(key);
            for (String t : list) {
                array[index] = t;
                index++;
            }
        }
        return array;
    }
    /****11.2 group all anagrams*********************/

    /****11.3 search within inflection point array***********/
    static int search(int[] a, int left, int right, int target){
        if(left>right) {
            return -1;
        }
        int mid=(left+right)/2;

        if(target==a[mid]) {
            return mid;
        }

        if(a[left] < a[mid]){
            if(target >=a[left] && target <=a[mid]){
                return search(a, left, mid-1, target);
            }else{
                return search(a, mid+1, right, target);
            }
        }else if(a[left]>a[mid]){
            if(target >=a[mid] && target<=a[right]){
                return search(a, mid+1, right, target);
            }else{
                return search(a, left, mid-1, target);
            }
        }else if(a[left]==a[mid]){
            if(a[mid]!=a[right]) {
                return search(a, mid+1, right, target);
            }else{ //search both half sides.
                int result=search(a, left, mid-1, target);
                if(result==-1) {
                    return search(a, mid+1, right, target);
                } else {
                    return result;
                }
            }
        }
        return -1;
    }
    /****11.3 search within inflection point array***********/

    /*****11.5 search strings with many empty strings*******/
    static int searchStrings(String[] array, String target, int first, int last){
        if(first>last) {
            return -1;
        }
        int mid=(first+last)/2;
        if(array[mid].isEmpty()){ // modify the mid value first, pointing to non-empty string index
            int left=mid-1;
            int right=mid+1;
            while(true){
                if(left<first && right>last) {
                    return -1;
                } else if(right<=last && !array[right].isEmpty()){
                    mid=right;
                    break;
                }else if(left>=first && !array[left].isEmpty()){
                    mid=left;
                    break;
                }
                left--;
                right++;
            }
        }

        if(array[mid].equals(target)){
            return mid;
        }else if(array[mid].compareTo(target)>0){
            return searchStrings(array, target, first, mid-1);
        }else{
            return searchStrings(array, target, mid+1, last);
        }
    }
    /*****11.5 search strings with many empty strings*******/

    /*****11.6 binary search M*N matrix****/
    static boolean findElement1(int[][] matrix, int target){
        if(matrix==null) {
            return false;
        }
        int row=0;
        int column=matrix[0].length-1;
        while(row<matrix.length && column>=0){
            if(matrix[row][column]==target) {
                return true;
            } else if(matrix[row][column]>target) {
                column--;
            } else {
                row++;
            }
        }
        return false;
    }


    /*****11.6 binary search M*N matrix****/
}
