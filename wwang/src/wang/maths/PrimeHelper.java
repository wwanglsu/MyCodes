package wang.maths;

import java.util.ArrayList;

public class PrimeHelper {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("is prime 1: "+isPrime1(49));
        System.out.println("is prime 2: "+isPrime1(17));
        System.out.println(sieveOfEratosthenes1(50));
    }

    //check whether a prime number
    public static boolean isPrime1(int num){
        if(num<2) {
            return false;
        }
        for(int i=2; i<num; i++){
            if(num % i ==0) {
                return false;
            }
        }
        return true;
    }
    //check whether a prime, better
    public static boolean isPrime2(int num){
        if(num<2) {
            return false;
        }
        int sqrt=(int) Math.sqrt(num);
        for(int i=2; i<=sqrt; i++){
            if(num % i == 0) {
                return false;
            }
        }
        return true;
    }

    //generate a list of prime number
    public static ArrayList<Integer> sieveOfEratosthenes1(int num){
        boolean[] flags=new boolean[num+1];
        for(int i=0; i<=num; i++){
            flags[i]=true; // initialize to true
        }

        int prime=2;
        while(prime<=num){
            crossOff(flags, prime);
            prime=getNextPrime(flags, prime);
            if(prime>num) {
                break;
            }
        }

        ArrayList<Integer> list=new ArrayList<Integer>();
        for(int i=2; i<=num; i++){
            if(flags[i]) {
                list.add(i);
            }
        }
        return list;
    }
    private static void crossOff(boolean[] flags, int prime){
        for(int i=prime*prime; i<flags.length; i+=prime){
            flags[i]=false;
        }
    }
    private static int getNextPrime(boolean[] flags, int prime){
        int next=prime+1;
        while(next<flags.length && !flags[next]){
            next++;
        }
        return next;
    }
}
