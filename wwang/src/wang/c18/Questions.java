package wang.c18;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import wang.utility.SuffixTree;

public class Questions {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(sum(231, 4972));
        int[] cards=new int[52];
        for(int i=0; i<52; i++) {
            cards[i]=i+1;
        }
        System.out.println(Arrays.toString(cards));
        //shuffle1(cards, 51);
        shuffle3(cards);
        System.out.println(Arrays.toString(cards));
        System.out.println(numberOf2sInRange(23));
        System.out.println(count2sInRange(23));
        System.out.println(count2sRecurse(23));
        System.out.println("selection rank: 9th: "+rank(cards, 0, cards.length-1, 8));
        String[] strs={"a", "testingtester", "testing", "tester", "test", "er","e", "r", "abcdefghijklm", "abcdefg","hijklm"};
        System.out.println(getLongestWord(strs));

        SuffixTree tree=new SuffixTree("BIBS");
        System.out.println(tree.search("IB"));
    }

    /*****18.1 add two numbers, not use + or any arithmetic operator*************/
    static int sum(int a, int b){
        if(b==0) {
            return a;
        }
        int sum=a^b; //add without carrying
        int carry=(a&b)<<1; //carry, but don't add
        return sum(sum, carry); //recurse until there's nothing to carry
    }
    /*****18.1 add two numbers, not use + or any arithmetic operator*************/

    /*****18.2 shuffle poker cards*****************/
    static int random(int low, int high){
        return low + (int)(Math.random()*(high-low+1));
    }
    static void swap(int[] arr, int i, int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    static int[] shuffle1(int[] cards, int i){ //i=cards.length-1
        if(i==0) {
            return cards;
        }
        //shuffle elements 0 through index-1;
        shuffle1(cards, i-1);
        int j=random(0, i);
        swap(cards, i, j);
        return cards;
    }

    static void shuffle2(int[] cards){
        for(int i=cards.length-1; i>=0; i--){
            int j=random(0, i-1);
            swap(cards, i, j);
        }
    }

    static void shuffle3(int[] cards){
        for(int i=0; i<cards.length; i++){
            int k=random(0, i);
            swap(cards, i, k);
        }
    }

    static List<Integer> shuffle4(LinkedList<Integer> arr){
        for(int i=arr.size()-1;i>0;i--){
            int k=random(0,i-1);
            Integer temp=arr.get(i);
            arr.set(i,arr.get(k));
            arr.set(k,temp);
        }
        return arr;
    }
    /*****18.2 shuffle poker cards*****************/

    /*****18.3 randomly generate a set of m integers from an array, equal probability***************/
    static int[] pickMRecursive(int[] original, int m, int i){
        if(i+1<m) {//not enough elements
            return null;
        } else if(i+1==m){//base case --copy first m elements into array
            int[] set=new int[m];
            for(int k=0; k<m; k++){
                set[k]=original[k];
            }
            return set;
        }else{
            int[] set=pickMRecursive(original, m, i-1);
            int k=random(0, i);
            if(k<m) {
                set[k]=original[i];
            }
            return set;
        }
    }

    static int[] pickMIterative(int[] original, int m){
        int[] subset=new int[m];
        //fill in subset array with first part of origianl array
        for(int i=0; i<m; i++){
            subset[i]=original[i];
        }
        //go through rest of original array
        for(int i=m; i<original.length; i++){
            int k=random(0, i);
            if(k<m) {
                subset[k]=original[i];
            }
        }
        return subset;
    }
    /*****18.3 randomly generate a set of m integers from an array, equal probability***************/

    /******18.4 count the number of 2s btw 0 and n**************/
    static int numberOf2sInRange(int n){
        int count=0;
        for(int i=2; i<=n; i++){
            count += numberOf2s(i);
        }
        return count;
    }
    private static int numberOf2s(int num){
        int count=0;
        while(num>0){
            if(num%10==2){
                count++;
            }
            num=num/10;
        }
        return count;
    }
    //optimized below:
    static int count2sInRange(int num){
        int count=0;
        int len=String.valueOf(num).length();
        for(int digit=0; digit<len; digit++){
            count += count2sInRangeAtDigit(num, digit);
        }
        return count;
    }
    private static int count2sInRangeAtDigit(int num, int d){
        int powerOf10=(int)Math.pow(10, d);
        int nextPowerOf10= powerOf10 * 10;
        int right=num % powerOf10;

        int roundDown = num - num % nextPowerOf10;
        int roundUp= roundDown + nextPowerOf10;

        int digit=(num / powerOf10 )%10;
        if(digit < 2){
            return roundDown/10;
        }else if(digit ==2){
            return roundDown /10 + right +1;
        }else{
            return roundUp / 10;
        }
    }

    static int count2sRecurse(int n){
        if(n==0) {
            return 0; // base case
        }

        int power =1;
        while(10 * power < n){
            power *= 10;
        }
        int first= n / power;
        int remainder=n % power;
        //counts 2s from first digit
        int nTwosFirst=0;
        if(first > 2) {
            nTwosFirst +=power;
        } else if(first==2){
            nTwosFirst +=remainder +1;
        }
        //count 2s from all other digits
        int nTwosOther = first * count2sRecurse(power-1) + count2sRecurse(remainder);
        return nTwosFirst + nTwosOther;
    }
    /******18.4 count the number of 2s btw 0 and n**************/

    /******18.5 given any two words, find the shortest distance********/
    static int shortestDistance(String[] words, String word1, String word2){
        int min=Integer.MAX_VALUE;
        int lastPosWord1=-1;
        int lastPosWord2=-1;
        for(int i=0; i<words.length; i++){
            String currentWord=words[i];
            if(currentWord.equals(word1)){
                lastPosWord1=i;
                int distance=lastPosWord1-lastPosWord2;
                if(lastPosWord2 >=0 && min > distance) {
                    min=distance;
                }
            }else if(currentWord.equals(word2)){
                lastPosWord2=i;
                int distance=lastPosWord2 - lastPosWord1;
                if(lastPosWord1 >=0 && min >distance) {
                    min=distance;
                }
            }
        }
        return min;
    }
    private static String wordAtLocation(String[] words, int loc){
        if(loc <0 || loc >=words.length) {
            return null;
        }
        return words[loc];
    }
    private static boolean searchConfirm(String[] words, String word1, String word2, int distance){
        boolean found_at_distance=false;
        for(int i=0; i<words.length;i++){
            if(words[i].equals(word1)){
                for(int j=1; j <distance; j++){
                    String loc2a=wordAtLocation(words, i-j);
                    String loc2b=wordAtLocation(words, i+j);
                    if(word2.equals(loc2a) || word2.equals(loc2b)){
                        return false;
                    }
                }
                String loc2a=wordAtLocation(words, i-distance);
                String loc2b=wordAtLocation(words, i+distance);
                if(word2.equals(loc2a) || word2.equals(loc2b)){
                    found_at_distance=true;
                }
            }
        }
        return found_at_distance;
    }
    /******18.5 given any two words, find the shortest distance********/

    /******18.6 find ith smallest number, selection rank algorithm(quick select) or max heap*********/
    static int partition(int[] array, int left, int right, int pivot){
        while(true){
            while(left <=right && array[left]<=pivot){
                left++;
            }
            while(left<=right && array[right]>pivot){
                right--;
            }
            if(left>right) {
                return left-1;
            }
            swap(array, left, right);
        }
    }
    static int rank(int[] array, int left, int right, int rank){
        int pivot= array[random(left, right)];
        int leftEnd=partition(array, left, right, pivot);//return end of left partition
        int leftSize=leftEnd-left +1;

        if(leftSize==rank+1){
            return max(array, left, leftEnd);
        }else if(rank < leftSize){
            return rank(array, left, leftEnd, rank);
        }else{
            return rank(array, leftEnd+1, right, rank-leftSize);
        }        
    }
    private static int max(int[] array, int start, int end){
        int max=Integer.MIN_VALUE;
        for(int i=start; i<=end;i++){
            max=Math.max(array[i], max);
        }
        return max;
    }
    /******18.6 find ith smallest number, selection rank algorithm(quick select) or max heap*********/

    /******18.7 find the longest word made of any other words**************/
    static class LengthComparator implements Comparator<String>{
        @Override
        public int compare(String o1, String o2){
            if(o1.length() < o2.length() ) {
                return 1;
            }
            if(o1.length() > o2.length() ) {
                return -1;
            }
            return 0;
        }
    }

    static String getLongestWord(String arr[]){
        HashMap<String, Boolean> map=new HashMap<String, Boolean>();
        for(String str: arr){
            map.put(str, true);
        }
        Arrays.sort(arr, new LengthComparator());
        for(String s: arr){
            if(canBuildWord(s, true, map) ){
                System.out.println(s);
                return s;
            }
        }
        return "";
    }
    //DP
    static boolean canBuildWord(String str, boolean isOriginalWord, HashMap<String, Boolean> map){
        if(map.containsKey(str) && !isOriginalWord ){
            return map.get(str);
        }
        for(int i=1; i<str.length(); i++){
            String left=str.substring(0, i);
            String right=str.substring(i);
            if(map.containsKey(left) && map.get(left)==true && canBuildWord(right, false, map) ) {
                return true;
            }
        }
        map.put(str, false);
        return false;
    }
    /******18.7 find the longest word made of any other words**************/

    /******18.8 given string s and an array of smaller strings T, search s for each small string in T************************/
    //see suffix tree
    /******18.8 given string s and an array of smaller strings T, search s for each small string in T************************/

    /******18.9 find and maintain the median value as new values generated**************/

    /******18.9 find and maintain the median value as new values generated**************/

}
