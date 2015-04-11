package wang.maths;

import java.util.Arrays;

public class GCD {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("GCD recursive: "+gcd_Recursive(99, 78));
        System.out.println("GCD iterative: "+gcd_Iterative(99, 78));
        System.out.println("GCD extend: "+ Arrays.toString(gcd_extend(36, 24, new int[3]) ) );
    }

    static int gcd_Recursive(int a, int b){
        if(b==0) {
            return a;
        } else {
            return gcd_Recursive(b, a%b);
        }
    }

    static int gcd_Iterative(int a, int b){
        if(b==0) {
            return a;
        }
        while(a%b !=0){
            int olda=a;
            int oldb=b;
            a=oldb;
            b=olda % oldb;
        }
        return b;
    }

    // d= gcd(a,b)= ax=by =>  (d, x, y)
    static int[] gcd_extend(int a, int b, int[] arr){
        int temp;
        if(b==0){
            arr[0]=a; arr[1]=1; arr[2]=0;
            return arr;
        }else{
            arr=gcd_extend(b, a%b, arr);
            temp=arr[1];
            arr[1]=arr[2];
            arr[2]=temp-a/b * arr[1];
            return arr;
        }
    }

}
