package wang.c9;

public class LIS {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] a={0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
        System.out.println("Longest Increasing Subsequence: "+lis_DP(a, a.length));
        System.out.println("Longest Increasing Subsequence: "+lis_BinarySearch(a, a.length));
    }

    static int lis_DP(int[] array, int length){
        int[] table=new int[length];
        for(int i=0; i<length;i++) {
            table[i]=1;
        }

        for(int i=1; i<length; i++){
            for(int j=0; j<i; j++){
                if(array[j]<array[i] && table[i]<table[j]+1){
                    table[i]=table[j]+1;
                }
            }
        }

        int maxLength=0;
        for(int i=0; i<length; i++){
            if(maxLength<table[i]) {
                maxLength=table[i];
            }
        }
        return maxLength;
    }

    //O(n lg n)
    static int lis_BinarySearch(int[] a, int length){
        int sz=1;
        int[] c=new int[length+1];
        int[] dp=new int[length];
        c[1]=a[0];

        dp[0]=1;
        for(int i=1;i<length; i++){
            if(a[i]<c[1]){
                c[1]=a[i];

                dp[i]=1;
            }
            else if(a[i]>c[sz]){
                c[sz+1]=a[i];
                dp[i]=sz+1;
                sz++;
            }
            else{
                int k=BinarySearch(c,sz, a[i]);
                c[k]=a[i];
                dp[i]=k;
            }
        }      
        return sz;
    }
    static int BinarySearch(int[] a, int rightIndex, int key){
        int low=1, high=rightIndex, mid=0;
        while(high-low>1){
            mid=low+(high-low)/2;
            if(a[mid]>=key){
                high=mid;
            } else {
                low=mid;
            }
        }
        return high;
    }
}
