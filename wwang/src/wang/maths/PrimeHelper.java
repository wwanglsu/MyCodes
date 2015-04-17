package wang.maths;

import java.util.ArrayList;

public class PrimeHelper {
    private static volatile PrimeHelper instance;
    private PrimeHelper(){}

    public static PrimeHelper getInstance(){
        if(instance==null){
            synchronized (PrimeHelper.class) {
                if(instance==null){
                    instance=new PrimeHelper();                    
                }                    
            }
        }
        return instance;
    }

    //check whether a prime number
    @Deprecated
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
    public ArrayList<Integer> sieveOfEratosthenes1(int num){
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
    private void crossOff(boolean[] flags, int prime){
        for(int i=prime*prime; i<flags.length; i+=prime){
            flags[i]=false;
        }
    }
    private int getNextPrime(boolean[] flags, int prime){
        int next=prime+1;
        while(next<flags.length && !flags[next]){
            next++;
        }
        return next;
    }

    public ArrayList<Integer> sieveOfEratosthenes2(int num){
        boolean[] flags=new boolean[num+1];
        ArrayList<Integer> list=new ArrayList<Integer>();
        for(int i=2; i<=num; i++){
            flags[i]=true; // initialize to true
        }
        for(int i=2; i<=(int)Math.sqrt(num); i++){
            if(flags[i]){
                for(int j=i*i; j<=num; j+=i){
                    if(flags[j]) {
                        flags[j]=false;
                    }
                }
            }
        }
        for(int i=2; i<=num; i++){
            if(flags[i]) {
                list.add(i);
            }
        }        
        return list;
    }

}
