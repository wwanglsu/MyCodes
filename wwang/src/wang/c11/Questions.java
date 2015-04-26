package wang.c11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

        for(int i=0; i<10; i++){
            int x=ArrayUtility.getInstance().randomIntRange(0, 30);
            track(x);
        }
        inOrder_Recursive(root);
        for(int i=0; i<10;i++){
            int rank=getRankOfNumber(i);
            System.out.println("\n"+i+" has rank: "+rank);
        }
    }

    static void inOrder_Recursive(RankNode root){
        if(root != null){
            inOrder_Recursive(root.left);
            System.out.print(root+ "   ");
            inOrder_Recursive(root.right);
        }
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

    static Coordinate findElement2(int[][] matrix, int target){
        Coordinate origin=new Coordinate(0, 0);
        Coordinate dest=new Coordinate(matrix.length-1, matrix[0].length-1);
        return findElement2(matrix, origin, dest, target);
    }
    private static Coordinate findElement2(int[][] matrix, Coordinate origin, Coordinate dest, int target){
        if(!origin.inBounds(matrix) && !dest.inBounds(matrix) ) {
            return null;
        }
        if(origin.isBefore(dest)) {
            return null;
        }
        if(matrix[origin.row][origin.column]==target) {
            return origin;
        }
        if(matrix[dest.row][dest.column]==target) {
            return dest;
        }

        Coordinate start=(Coordinate)origin.clone();
        int diagDist=Math.min(dest.row-origin.row, dest.column-origin.column);
        Coordinate end=new Coordinate(start.row+diagDist, start.column+diagDist);
        Coordinate p=new Coordinate(0, 0);
        //binary search on diagonal, look for the first element greater than target
        while(start.isBefore(end)){
            p.setToAverage(start, end);
            if(target > matrix[p.row][p.column]){
                start.row=p.row+1;
                start.column=p.column+1;
            }else{
                end.row=p.row-1;
                end.column=p.column-1;
            }
        }
        //split into quadrants, search the bottom left and top right.
        return partitionAndSearch(matrix, origin, dest, start, target);
    }
    private static Coordinate partitionAndSearch(int[][] matrix, Coordinate origin, Coordinate dest, Coordinate pivot, int target){
        Coordinate lowerLeftOrigin=new Coordinate(pivot.row, origin.column);
        Coordinate lowerLeftDest=new Coordinate(dest.row, pivot.column-1);
        Coordinate upperRightOrigin=new Coordinate(origin.row, pivot.column);
        Coordinate upperRightDest=new Coordinate(pivot.row-1, dest.column);

        Coordinate lowerLeft=findElement2(matrix, lowerLeftOrigin, lowerLeftDest, target);
        if(lowerLeft==null) {
            return findElement2(matrix, upperRightOrigin, upperRightDest, target);
        }
        return lowerLeft;

    }
    /*****11.6 binary search M*N matrix****/

    /*****11.7 circus, longest increasing subsequence*******************/
    @SuppressWarnings("unchecked")
    static ArrayList<People> getIncreasingSequence(ArrayList<People> items){
        Collections.sort(items);
        return longestIncreasingSubsequence(items);  //O(n^2)
    }
    private static ArrayList<People> longestIncreasingSubsequence(ArrayList<People> items){
        ArrayList<People>[] solutions=new ArrayList[items.size()];
        longestIncreasingSubsequence(items, solutions, 0);

        ArrayList<People> best_sequence=null;
        for(int i=0; i<solutions.length; i++){
            best_sequence=seqWithMaxLength(best_sequence, solutions[i]);
        }
        return best_sequence;
    }
    private static ArrayList<People> seqWithMaxLength(ArrayList<People> p, ArrayList<People> q){
        if(p==null) {
            return q;
        } else if(q==null) {
            return p;
        }
        return p.size() > q.size() ? p: q;
    }
    private static void longestIncreasingSubsequence(ArrayList<People> items, ArrayList<People>[] solutions, int current_index){
        if(current_index >= items.size() || current_index <0) {
            return;
        }
        People current=items.get(current_index);
        //find longest sequence that we can append current to
        ArrayList<People> best_sequence=null;
        for(int i=0; i<current_index; i++){
            if(items.get(i).isBefore(current)){
                best_sequence=seqWithMaxLength(best_sequence, solutions[i]);
            }
        }

        ArrayList<People> new_solution=new ArrayList<People>();
        if(best_sequence!=null){
            new_solution.addAll(best_sequence);
        }
        new_solution.add(current);

        solutions[current_index]=new_solution;
        longestIncreasingSubsequence(items, solutions, current_index+1);
    }
    /*****11.7 circus, longest increasing subsequence*******************/

    /******11.8 get rank of number************/
    static RankNode root=null;
    static void track(int num){
        if(root==null) {
            root=new RankNode(num);
        } else {
            root.insert(num);
        }
    }

    static int getRankOfNumber(int num){
        return root.getRank(num);
    }
    /******11.8 get rank of number************/
}
