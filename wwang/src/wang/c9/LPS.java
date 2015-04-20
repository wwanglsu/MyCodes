package wang.c9;

public class LPS {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //longest palindrome subsequence
        char[] a="character".toCharArray();
        System.out.println(lps_recursive(a, 0, a.length-1));
        System.out.println(lps_DP(a));
    }

    static int lps_recursive(char[] a, int start, int end){
        if(start==end) {
            return 1;
        }
        if(a[start]==a[end] && (start+1)==end) {
            return 2;
        }
        if(a[start]==a[end]) {
            return lps_recursive(a, start+1, end-1)+2;
        }
        return Math.max(lps_recursive(a, start+1, end), lps_recursive(a, start, end-1));
    }

    static int lps_DP(char[] a){
        int n=a.length;
        int[][] L=new int[n][n];
        int cl, j;
        for(int i=0; i<n;i++){L[i][i]=1;}

        for(cl=2; cl<=n; cl++){
            for(int i=0; i<n-cl+1; i++){
                j=i+cl-1;
                if(a[i]==a[j] && cl==2) {
                    L[i][j]=2;
                } else if(a[i]==a[j]) {
                    L[i][j]=L[i+1][j-1]+2;
                } else {
                    L[i][j]=Math.max(L[i][j-1], L[i+1][j]);
                }
            }
        }
        return L[0][n-1];
    }

}
