package wang.c9;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import wang.c7.Point;
import wang.utility.ArrayUtility;


public class Questions {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(runStair_Recursive(4));
        System.out.println(runStair_DP(4));
        System.out.println(runStair_DP(4, new int[5]));
        maze=ArrayUtility.getInstance().randomMatrix(10, 10, 0, 5);
        ArrayList<Point> path = new ArrayList<Point>();
        Hashtable<Point, Boolean> cache = new Hashtable<Point, Boolean>();
        boolean success = getPath(9, 9, path, cache);
        System.out.println("path: "+path.size());
        int[] array={-10,-5,2,2,2,3,4,7,9,12,13};
        System.out.println("find magic index: "+findMagicIndex(array));
        System.out.println("find magic index: "+findMagicIndex_Recurse(array, 0, array.length-1));
        System.out.println("find magic index: "+findMagicIndex_DP(array, 0, array.length-1));

        System.out.println("all sets: "+getSubsets(new ArrayList<Integer>(Arrays.asList(1,2,3)), 0) );
        System.out.println("all sets: "+getSubsets_Iterative(new ArrayList<Integer>(Arrays.asList(1,2,3))) );

        System.out.println("string permutation: "+getAllPermutation("abc"));
        System.out.println("all parentheses: "+getAllParentheses(3) );

        System.out.println(makeChange(61));
    }
    /****9.1*runing stair with n steps********/
    static int runStair_Recursive(int num){
        if(num<0) {
            return 0;
        }
        if(num==0) {
            return 1;
        }
        return runStair_Recursive(num-1)+runStair_Recursive(num-2)+runStair_Recursive(num-3);
    }

    static int runStair_DP(int num){
        int[] table=new int[num+1];
        table[0]=0; table[1]=1; table[2]=2; table[3]=4;
        for(int i=4; i<=num; i++){
            table[i]=table[i-3]+table[i-2]+table[i-1];
        }
        return table[num];
    }

    static int runStair_DP(int num, int[] table){
        if(num<0) {
            return 0;
        } else if(num==0) {
            return 1;
        } else if(table[num] != 0) {
            return table[num];
        } else{
            table[num]=runStair_DP(num-3, table)+runStair_DP(num-2, table)+runStair_DP(num-1, table);
            return table[num];
        }

    }
    /****9.1*runing stair with n steps********/

    /****9.2 robat find a path***************/
    static int[][] maze = new int[10][10];
    static boolean isFree(int x, int y) {
        if (maze[x][y] == 0) {
            return false;
        } else {
            return true;
        }
    }

    static boolean getPath(int x, int y, ArrayList<Point> path, Hashtable<Point, Boolean> cache) {
        Point p = new Point(x, y);
        if (cache.containsKey(p)) { // Already visited this cell
            return cache.get(p);
        }
        if (x == 0 && y == 0) {
            return true; // found a path
        }
        boolean success = false;
        if (x >= 1 && isFree(x - 1, y)) { // Try left
            success = getPath(x - 1, y, path, cache); // Free!  Go left
        }
        if (!success && y >= 1 && isFree(x, y - 1)) { // Try up
            success = getPath(x, y - 1, path, cache); // Free!  Go up
        }
        if (success) {
            path.add(p); // Right way! Add to path.
        }
        cache.put(p, success); // Cache result
        return success;
    }
    /****9.2 robat find a path***************/

    /****9.3 magic index***********/
    static int findMagicIndex(int[] array){
        if(array==null) {
            return -1;
        }
        for(int i=0; i<array.length; i++){
            if(i==array[i] ) {
                return i;
            }
        }
        return -1;
    }
    static int findMagicIndex_Recurse(int[] array, int start, int end){
        if(start<0 || end<start|| end>=array.length) {
            return -1;
        }
        int mid=(start+end)/2;
        if(array[mid]==mid) {
            return mid;
        }
        if(array[mid]>mid) {
            return findMagicIndex_Recurse(array, start, mid-1);
        } else {
            return findMagicIndex_Recurse(array, mid+1, end);
        }
    }
    static int findMagicIndex_DP(int[] array, int start, int end){
        if(start<0 || end<start || end >=array.length){
            return -1;
        }
        int midIndex=(start+end)/2;
        int midValue=array[midIndex];
        if(midIndex==midValue) {
            return midIndex;
        }
        int leftIndex=Math.min(midIndex, midValue);
        int left=findMagicIndex_DP(array, start, leftIndex);
        if(left>=0) {
            return left;
        }

        int rightIndex=Math.max(midIndex+1, midValue);
        int right=findMagicIndex_DP(array, rightIndex, end);

        return right;
    }
    /****9.3 magic index***********/

    /*****9.4 get all subset of a set*************************/
    static ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set, int index){
        ArrayList<ArrayList<Integer>> allsubsets;
        if(set.size()==index){
            allsubsets=new ArrayList<ArrayList<Integer>>();
            allsubsets.add(new ArrayList<Integer>());
        }else{
            allsubsets=getSubsets(set, index+1);
            int item=set.get(index);
            ArrayList<ArrayList<Integer>> more=new ArrayList<ArrayList<Integer>>();
            for(ArrayList<Integer> subset: allsubsets){
                ArrayList<Integer> newsubset=new ArrayList<Integer>();
                newsubset.addAll(subset);
                newsubset.add(item);
                more.add(newsubset);
            }
            allsubsets.addAll(more);
        }
        return allsubsets;
    }

    static ArrayList<ArrayList<Integer>> getSubsets_Iterative(ArrayList<Integer> set){
        ArrayList<ArrayList<Integer>> allsubset=new ArrayList<ArrayList<Integer>>();
        int num=1<< set.size();
        for(int i=0; i<num; i++){
            ArrayList<Integer> subset=convertIntToSet(i, set);
            allsubset.add(subset);
        }
        return allsubset;
    }
    private static ArrayList<Integer> convertIntToSet(int k, ArrayList<Integer> set){
        ArrayList<Integer> subset=new ArrayList<Integer>();
        int index=0;
        for(int i=k; i>0; i=i/2){
            if(i % 2 !=0){
                subset.add(set.get(index));
            }
            index++;
        }
        return subset;
    }
    /*****9.4 get all subset of a set*************************/

    /*****9.5 computer all permutations of a string***********/
    static ArrayList<String> getAllPermutation(String str){
        if(str==null) {
            return null;
        }
        ArrayList<String> permutations=new ArrayList<String>();
        if(str.isEmpty()){
            permutations.add("");
            return permutations;
        }
        char first=str.charAt(0);
        String remainder=str.substring(1);
        ArrayList<String> words=getAllPermutation(remainder);
        for(String word: words){
            for(int i=0; i<=word.length(); i++){
                String s=insertCharAt(word, first, i);
                permutations.add(s);                
            }
        }
        return permutations;
    }
    private static String insertCharAt(String word, char c, int position){
        String left=word.substring(0, position);
        String right=word.substring(position);
        return left+c+right;
    }
    /*****9.5 computer all permutations of a string***********/

    /*****9.6 all valid parentheses*************************/
    static Set<String> getAllParentheses(int num){
        Set<String> set=new HashSet<String>();
        if(num==0) {
            set.add("");
        } else{
            Set<String> pre=getAllParentheses(num-1);
            for(String str:pre){
                for(int i=0; i<str.length(); i++){
                    if(str.charAt(i)=='('){
                        String s=insertInside(str, i);
                        set.add(s);
                    }
                }
                if(!set.contains("()"+str)){
                    set.add("()"+str);
                }
            }
        }
        return set;
    }
    private static String insertInside(String str, int leftIndex){
        String left=str.substring(0, leftIndex+1);
        String right=str.substring(leftIndex+1);
        return left+"()"+right;
    }
    /*****9.6 all valid parentheses*************************/

    /******9.7 paint fill***********************/
    static boolean paintFill(Color[][] screen, int x, int y, Color newColor){
        return paintFill(screen, x, y, screen[y][x], newColor);
    }
    private static boolean paintFill(Color[][] screen, int x, int y, Color oColor, Color nColor){
        if(x<0 || x>=screen[0].length || y<0 || y>=screen.length) {
            return false;
        }
        if(screen[y][x]==oColor){
            screen[y][x]=nColor;
            paintFill(screen, x-1, y, oColor, nColor);
            paintFill(screen, x+1, y, oColor, nColor);
            paintFill(screen, x, y-1, oColor, nColor);
            paintFill(screen, x, y+1, oColor, nColor);
        }
        return true;
    }
    /******9.7 paint fill***********************/

    /******9.8 represent n cents************************/
    static int quarters=2, dimes=4, nickles=5, pennies=7;
    static int makeChange(int cents){
        int[] denominations={25,10,5,1};
        int x=makeChange(cents, 25);
        int y=makeChange(cents, denominations, 0);
        if(x!=y) {
            System.out.println("Error");
        }
        return x;
    }

    static int makeChange(int cents, int denomination){
        int next_denom=0;
        switch (denomination) {
            case 25 :
                next_denom=10;
                break;
            case 10:
                next_denom=5;
                break;
            case 5:
                next_denom=1;
                break;
            case 1:
                return 1;
        }
        int way=0;
        for(int i=0; i*denomination <= cents; i++){
            way=way+makeChange(cents-i*denomination, next_denom);
        }
        return way;
    }
    static int makeChange(int cents, int[] denoms, int choice){
        if(choice +1 >= denoms.length) {
            return 1;
        }
        int way=0;
        for(int i=0; i*denoms[choice]<=cents; i++){
            way+= makeChange(cents-i*denoms[choice], denoms, choice+1);
        }
        return way;
    }
    /******9.8 represent n cents************************/
}
