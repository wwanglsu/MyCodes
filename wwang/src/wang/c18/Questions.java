package wang.c18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import wang.utility.ArrayUtility;
import wang.utility.TrieTree;

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

        SuffixTree tree=new SuffixTree("BIBSIBCIB");
        System.out.println("SuffixTree: "+tree.search("IB"));

        int arraySize=10;
        int range=20;
        maxHeapComparator=new MaxHeapComparator();
        minHeapComparator=new MinHeapComparator();
        maxHeap=new PriorityQueue<Integer>(arraySize-arraySize/2, maxHeapComparator);
        minHeap=new PriorityQueue<Integer>(arraySize/2, minHeapComparator);
        for(int i=0; i<arraySize; i++){
            int randomNumber=(int)(Math.random() * (range+1) );
            addNewNumberAndPrintMedian(randomNumber);
        }

        String startWord="hit", stopWord="cog";
        Set<String> dict=new HashSet<String>();
        dict.add("HOT");dict.add("DOT");dict.add("DOG");dict.add("LOT");dict.add("LOG");
        System.out.println(transform(startWord, stopWord, dict));

        int[][] matrix=ArrayUtility.getInstance().randomMatrix(7, 7, 0, 1);
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                System.out.print(matrix[i][j]+"  ");
            }
            System.out.print("\n");
        }
        Subsquare square=findSquare(matrix);
        square.print();

        int[] aaa={0,1,1,1,1,1,0,0,0,2,2,0,1,1};
        sort012(aaa, 14);
        System.out.println(Arrays.toString(aaa));
        
        int[][] amatrix={{2,-1,3},{1,4,2},{0,5,3},{0,5,3}};
        //System.out.println("\n18.12: SubMatrix with largest sum: "+getMaxMatrix(amatrix));
        System.out.println("\n18.12: SubMatrix with largest sum: "+maxSubMatrix(amatrix));

        String[] dict_RectangleStrings={"egg","ego","feo","fee","fog","cog","lot"};
        Questions.RectangleWordDemo rectangleWordDemo =  new Questions().new RectangleWordDemo(dict_RectangleStrings);
        Rectangle rectangle = rectangleWordDemo.maxRectangle();
        if(rectangle==null){
            System.out.println ("No rectangle exists");
        }else{
            System.out.println ("Question 18.13: ");
            rectangle.print();
        }
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
        //fill in subset array with first part of original array
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
    private static class SuffixNode{
        HashMap<Character, SuffixNode> lookup=new HashMap<Character, SuffixNode>();
        char value;
        ArrayList<Integer> indexes=new ArrayList<Integer>();
        public SuffixNode(){}

        public void insert(String s, int index){
            indexes.add(index);

            if(s !=null && s.length() > 0){
                value=s.charAt(0);
                SuffixNode child=null;

                if(lookup.containsKey(value)){
                    child=lookup.get(value);
                }else{
                    child=new SuffixNode();
                    lookup.put(value, child);
                }

                String remaind=s.substring(1);
                child.insert(remaind, index);
            }
        }

        public ArrayList<Integer> search(String s){
            if(s==null || s.length() ==0 ){
                return indexes;
            }else{
                char first=s.charAt(0);
                if(lookup.containsKey(first)){
                    return lookup.get(first).search(s.substring(1));
                }
            }

            return null;
        }

    }

    private static class SuffixTree{
        SuffixNode root=new SuffixNode();

        public SuffixTree(String s){

            for(int i=0; i<s.length(); i++){
                String suffix = s.substring(i);
                root.insert(suffix, i);
            }

        }

        public ArrayList<Integer> search(String s){
            return root.search(s);
        }
    }
    /******18.8 given string s and an array of smaller strings T, search s for each small string in T************************/

    /******18.9 find and maintain the median value as new values generated**************/
    static class MinHeapComparator implements Comparator<Integer>{
        @Override
        public int compare(Integer o1, Integer o2){
            if(o1>o2) {
                return 1;
            } else if(o1==o2) {
                return 0;
            } else {
                return -1;
            }
        }
    }
    static class MaxHeapComparator implements Comparator<Integer>{
        @Override
        public int compare(Integer o1, Integer o2){
            if(o1<o2) {
                return 1;
            } else if(o1==o2) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    private static Comparator<Integer> maxHeapComparator;
    private static Comparator<Integer> minHeapComparator;
    private static PriorityQueue<Integer> maxHeap; // sorted array left part
    private static PriorityQueue<Integer> minHeap; // sorted array right part
    //method below is to maintains maxHeap.size()>=minHeap.size()
    static void addNewNumber(int randomNumber){
        if(maxHeap.size()==minHeap.size()){
            if(minHeap.peek() != null && randomNumber > minHeap.peek() ){
                maxHeap.offer(minHeap.poll());
                minHeap.offer(randomNumber);
            }else{
                maxHeap.offer(randomNumber);
            }            
        }else{
            if(randomNumber < maxHeap.peek()){
                minHeap.offer(maxHeap.poll());
                maxHeap.offer(randomNumber);
            }else{
                minHeap.offer(randomNumber);
            }            
        }
    }
    //maxHeap is always at least as big as minHeap.
    static double getMedian(){
        if(maxHeap.isEmpty()){
            return 0;
        }
        if(maxHeap.size() == minHeap.size() ){
            return (minHeap.peek() + maxHeap.peek() )/2.0;
        }else{
            return maxHeap.peek();
        }
    }    

    static void addNewNumberAndPrintMedian(int randomNumber){
        addNewNumber(randomNumber);
        System.out.println("Random Number = "+randomNumber);
        printMinHeapAndMaxHeap();
        System.out.println("\nMedian = "+getMedian()+"\n");
    }
    static void printMinHeapAndMaxHeap(){
        Integer[] minHeapArray=minHeap.toArray(new Integer[minHeap.size()]);
        Integer[] maxHeapArray=maxHeap.toArray(new Integer[maxHeap.size()]);
        Arrays.sort(minHeapArray, minHeapComparator);
        Arrays.sort(maxHeapArray, maxHeapComparator);
        System.out.print("MinHeap =");
        for(int i=minHeapArray.length-1; i>=0; i--){
            System.out.print(" "+minHeapArray[i]);
        }
        System.out.print("\nMaxHeap =");
        for(int i=0; i<maxHeapArray.length; i++){
            System.out.print(" "+maxHeapArray[i]);
        }
    }
    /******18.9 find and maintain the median value as new values generated**************/

    /******18.10 Word Ladder. start:hit, end:cog, dict:hot,dot,dog,lot,log**************/
    //BFS, level order
    static LinkedList<String> transform(String startWord, String stopWord, Set<String> dictionary){
        startWord=startWord.toUpperCase();
        stopWord=stopWord.toUpperCase();
        Queue<String> actionQueue=new LinkedList<String>();
        Set<String> visitedSet=new HashSet<String>();
        Map<String, String> backtrackMap=new TreeMap<String, String>();

        actionQueue.add(startWord);
        visitedSet.add(startWord);

        while(! actionQueue.isEmpty()){
            String w=actionQueue.poll();
            //for each possible word v from w with one edit operation
            for(String v: getOneEditWords(w)){
                if(v.equals(stopWord)){
                    //found out words. Now back track
                    LinkedList<String> list=new LinkedList<String>();
                    //append v to list
                    list.add(v);
                    while(w != null){
                        list.add(0, w);
                        w=backtrackMap.get(w);
                    }
                    return list;
                }
                //if v is a dictionary word
                if(dictionary.contains(v)){
                    if(! visitedSet.contains(v)){
                        actionQueue.add(v);
                        visitedSet.add(v);//mark visited
                        backtrackMap.put(v, w);
                    }
                }
            }
        }
        return null;        
    }

    private static Set<String> getOneEditWords(String word){
        Set<String> words=new TreeSet<String>();
        //for every letter
        for(int i=0; i<word.length(); i++){
            char[] wordArray=word.toCharArray();
            for(char c='A';c<='Z';c++){
                if(c != word.charAt(i)){
                    wordArray[i]=c;
                    words.add(new String(wordArray));
                }
            }
        }
        return words;
    }
    /******18.10 Word Ladder. start:hit, end:cog, dict:hot,dot,dog,lot,log**************/

    /******18.11 find the maximum subsquare such that all four borders are filled with black pixels******/
    //method 1:
    static class Subsquare{
        public int row, column, size;
        public Subsquare(int r, int c, int sz){
            row=r; column=c; size=sz;
        }
        public void print(){
            System.out.println("(" + row + ", " + column + ", " + size + ")");
        }
    }
    static Subsquare findSquare(int[][] matrix){
        int N=matrix.length;

        for(int i=N; i>=1; i--){
            Subsquare square=findSquareWithSize(matrix, i);
            if(square != null){
                return square;
            }
        }
        return null;        
    }
    private static Subsquare findSquareWithSize(int[][] matrix, int squareSize){
        //On an edge of length N, there are (N-sz+1) squares of length sz.
        int count=matrix.length - squareSize +1;

        //iterate through all squares with side length square_size
        for(int row=0; row<count; row++){
            for(int col=0; col<count; col++){
                if(isSquare(matrix, row, col, squareSize) ){
                    return new Subsquare(row, col, squareSize);
                }
            }
        }
        return null;
    }
    private static boolean isSquare(int[][] matrix, int row, int col, int size){
        //check top and bottom border
        for(int j=0; j<size;j++){
            if(matrix[row][col+j]==1) {
                return false;
            }
            if(matrix[row+size-1][col+j]==1) {
                return false;
            }
        }
        //check left and right border
        for(int i=1; i<size-1; i++){
            if(matrix[row+i][col]==1) {
                return false;
            }
            if(matrix[row+i][col+size-1]==1) {
                return false;
            }
        }
        return true;
    }
    //method 2 efficient:
    static class SquareCell{
        public int zerosRight=0;
        public int zerosBelow=0;
        public SquareCell(int right, int below){
            zerosRight=right;
            zerosBelow=below;
        }
        public void setZerosRight(int right){
            zerosRight=right;
        }
        public void setZerosBelow(int below){
            zerosBelow=below;
        }
    }
    static Subsquare findSquareWithSize(SquareCell[][] processed, int square_size) {
        // On an edge of length N, there are (N - sz + 1) squares of length sz.
        int count = processed.length - square_size + 1; 

        // Iterate through all squares with side length square_size.
        for (int row = 0; row < count; row++) {
            for (int col = 0; col < count; col++) {
                if (isSquare(processed, row, col, square_size)) {
                    return new Subsquare(row, col, square_size);
                }
            }
        }
        return null;
    }

    static Subsquare findSquare2(int[][] matrix){
        assert(matrix.length > 0);
        for (int row = 0; row < matrix.length; row++){
            assert(matrix[row].length == matrix.length);
        }

        SquareCell[][] processed = processSquare(matrix);

        int N = matrix.length;

        for (int i = N; i >= 1; i--) {
            Subsquare square = findSquareWithSize(processed, i);
            if (square != null) {
                return square;
            }
        }
        return null;
    }   

    private static boolean isSquare(SquareCell[][] matrix, int row, int col, int size) {
        SquareCell topLeft = matrix[row][col];
        SquareCell topRight = matrix[row][col + size - 1];
        SquareCell bottomRight = matrix[row + size - 1][col];
        if (topLeft.zerosRight < size) { // Check top edge
            return false;
        }
        if (topLeft.zerosBelow < size) { // Check left edge
            return false;
        }
        if (topRight.zerosBelow < size) { // Check right edge
            return false;
        }
        if (bottomRight.zerosRight < size) { // Check bottom edge
            return false;
        }
        return true;
    }

    static SquareCell[][] processSquare(int[][] matrix) {
        SquareCell[][] processed = new SquareCell[matrix.length][matrix.length];

        for (int r = matrix.length - 1; r >= 0; r--) {
            for (int c = matrix.length - 1; c >= 0; c--) {
                int rightZeros = 0;
                int belowZeros = 0;
                if (matrix[r][c] == 0) { // only need to process if it's a black cell
                    rightZeros++;
                    belowZeros++;
                    if (c + 1 < matrix.length) { // next column over is on same row
                        SquareCell previous = processed[r][c + 1];
                        rightZeros += previous.zerosRight;
                    }
                    if (r + 1 < matrix.length) {
                        SquareCell previous = processed[r + 1][c];
                        belowZeros += previous.zerosBelow;
                    }
                }
                processed[r][c] = new SquareCell(rightZeros, belowZeros);
            }
        }   
        return processed;
    }
    /******18.11 find the maximum subsquare such that all four borders are filled with black pixels******/
    static void sort012(int a[], int arr_size)
    {
        int lo = 0;
        int hi = arr_size - 1;
        int mid = 0;

        while (mid <= hi)
        {
            switch (a[mid])
            {
                case 0:
                    swapArr(a, lo++, mid++);
                    break;
                case 1:
                    mid++;
                    break;
                case 2:
                    swapArr(a, mid, hi--);
                    break;
            }
        }
    }

    static void swapArr(int[] arr, int i, int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
    /******18.12 N*N matrix with positive and negative, find the submatrix with largest possible sum*************/
	//brute force O(N^6): we iterate through O(N^4) submatrices and it takes O(N^2) time to compute the area of each.
    //DP O(N^4) solution 1
    static int getMaxMatrix(int[][] original){
        int maxArea=Integer.MIN_VALUE;
        int rowCount=original.length;
        int columnCount=original[0].length;
        int[][] matrix=precomputeMatrix(original);
        for(int row1=0; row1<rowCount;row1++){
            for(int row2=row1; row2<rowCount;row2++){
                for(int col1=0; col1<columnCount;col1++){
                    for(int col2=col1; col2<columnCount;col2++){
                        int sum=computeSum(matrix, row1, row2, col1, col2);
                        if(sum>maxArea){
                            System.out.println("New Max of " + sum + ": (rows " + row1 + " to " + row2 + ") and (columns " + col1 + " to " + col2 + ")");
                            maxArea=sum;
                        }
                    }
                }
            }
        }
        return maxArea;
    }
    
    private static int[][] precomputeMatrix(int[][] matrix){
        int[][] sumMatrix=new int[matrix.length][matrix[0].length];
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix.length; j++){
                if(i==0 && j==0){//first cell
                    sumMatrix[i][j]=matrix[i][j];
                }else if(j==0){//cell in first column
                    sumMatrix[i][j]=sumMatrix[i-1][j]+matrix[i][j];
                }else if(i==0){//cell in first row
                    sumMatrix[i][j]=sumMatrix[i][j-1]+matrix[i][j];
                }else{
                    sumMatrix[i][j]=sumMatrix [i-1][j]+sumMatrix[i][j-1]-sumMatrix[i-1][j-1]+matrix[i][j];
                }
            }
        }
        return sumMatrix;
    }
    
    private static int computeSum(int[][] sumMatrix, int row1, int row2, int col1, int col2){
        if(row1==0 && col1==0){//starts at row0 and column0
            return sumMatrix[row2][col2];
        }else if(row1==0){//starts at row0
            return sumMatrix[row2][col2]-sumMatrix[row2][col1-1];
        }else if(col1==0){//starts at column0
            return sumMatrix[row2][col2]-sumMatrix[row1-1][col2];
        }else{
            return sumMatrix[row2][col2]-sumMatrix[row2][col1-1]-sumMatrix[row1-1][col2]+sumMatrix[row1-1][col1-1];
        }
    }
    
    //O(R^2*C) R rows and C columns Optimized Solution (similar: find subarray with largest sum O(N)) ***
    public static int maxSubMatrix (int[][] matrix) {
        int rowCount = matrix.length;
        int columnCount = matrix[0].length;
        
        int[] partialSum = new int[columnCount];
        int maxSum = 0;
        
        for(int rowStart=0; rowStart < rowCount; rowStart++){
            clearArray(partialSum);
            for(int rowEnd=rowStart; rowEnd<rowCount; rowEnd++){
                for(int col=0; col<columnCount;col++){
                    partialSum[col] += matrix[rowEnd][col];
                }
                int tempMaxSum = maxSubArray(partialSum, columnCount);
                maxSum=Math.max(maxSum, tempMaxSum);
            }
        }
        return maxSum;
    }
    
    private static void clearArray(int[] arr){
        if(arr==null || arr.length==0) return;
        for(int i=0; i<arr.length; i++){
            arr[i] = 0;
        }
    }
    
    private static int maxSubArray(int[] arr, int length){
        int max=0;
        int max_so_far=0;
        for(int i=0; i<length;i++){
            max_so_far += arr[i];
            max=Math.max(max, max_so_far);
            
            if(max_so_far<0) max_so_far =0;
        }
        return max;
    }
    /******18.12 N*N matrix with positive and negative, find the submatrix with largest possible sum*************/

    /******18. 13 create the largest rectangle of letters such that every row/column forms a word***********/
    class RectangleWordDemo{
        private int maxWordLength;
        private WordGroup[] groupList;
        private TrieTree[] trieList;
        
        public RectangleWordDemo(String[] list){
            groupList = WordGroup.createWordGroups(list);
            maxWordLength = groupList.length;
            trieList = new TrieTree[maxWordLength];
        }
        
        public Rectangle maxRectangle(){
            int maxSize = maxWordLength * maxWordLength;
            
            for(int z=maxSize; z>0; z--){
                for(int i=1; i<=maxWordLength; i++){
                    if(z%i==0){
                        int j=z/i;
                        if(j<=maxWordLength){
                            Rectangle rectangle = makeRectangle(i,j);
                            if(rectangle != null){
                                return rectangle;
                            }
                        }
                    }
                }
            }
            return null;
            
        }
        
        private Rectangle makeRectangle(int length, int height){
            if(groupList[length-1]==null || groupList[height-1]==null) return null;
            
            if(trieList[height-1]==null){
                ArrayList<String> words=groupList[height-1].getWords();
                trieList[height-1] = new TrieTree(words);
            }
            return makePartialRectangle(length, height, new Rectangle(length));
        }
        
        private Rectangle makePartialRectangle(int l, int h, Rectangle rectangle){
            if(rectangle.height ==h){
                if(rectangle.isComplete(l, h, groupList[h-1])){
                    return rectangle;
                }else{
                    return null;
                }
            }
            
            if(!rectangle.isPartialOK(l, trieList[h-1])){
                return null;
            }
            
            for(int i=0; i<groupList[l-1].length();i++){
                Rectangle origPlusOne = rectangle.append(groupList[l-1].getWord(i));
                Rectangle rect = makePartialRectangle(l, h, origPlusOne);
                if(rect != null){
                    return rect;
                }
            }
            return null;
        }        
    }
    /******18. 13 create the largest rectangle of letters such that every row/column forms a word***********/
}
