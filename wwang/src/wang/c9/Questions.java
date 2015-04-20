package wang.c9;

import java.util.ArrayList;
import java.util.Hashtable;

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
        boolean success = getPath(7, 3, path, cache);
        System.out.println("path: "+path.size());
        int[] array={-10,-5,2,2,2,3,4,7,9,12,13};
        System.out.println("find magic index: "+findMagicIndex(array));
        System.out.println("find magic index: "+findMagicIndex_Recurse(array, 0, array.length-1));
        System.out.println("find magic index: "+findMagicIndex_DP(array, 0, array.length-1));
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


}
