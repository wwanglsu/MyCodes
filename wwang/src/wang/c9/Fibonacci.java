package wang.c9;

public class Fibonacci {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(fibonacci(23));
        int[] cache=new int[100];
        System.out.println(fibonacci_DP(23, cache));

    }

    static int fibonacci(int i){ //recursive
        if(i==0) {
            return 0;
        }
        if(i==1) {
            return 1;
        }
        return fibonacci(i-1)+fibonacci(i-2);
    }

    static int fibonacci_DP(int i, int[] cache){
        if(i==0) {
            return 0;
        }
        if(i==1) {
            return 1;
        }
        if(cache[i] != 0) {
            return cache[i];
        }
        cache[i]=fibonacci_DP(i-1, cache) + fibonacci_DP(i-2, cache);        
        return cache[i];
    }
}
