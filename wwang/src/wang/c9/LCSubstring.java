package wang.c9;

public class LCSubstring {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        char a[] = "OldSite:GeeksforGeeks.org".toCharArray();
        char b[] = "NewSite:GeeksQuiz.com".toCharArray();
        System.out.println("LCSubstring length: "+lcsSubstring_DP(a, b));
    }

    static int lcsSubstring_DP(char[] a, char[] b){
        int aLength=a.length;
        int bLength=b.length;
        int[][] L=new int[aLength+1][bLength+1];
        int result=0;

        for(int i=0; i<=aLength; i++){
            for(int j=0; j<=bLength; j++){
                if(i==0 || j==0) {
                    L[i][j]=0;
                } else if(a[i-1]==b[j-1]){
                    L[i][j]=L[i-1][j-1]+1;
                    result=Math.max(result, L[i][j]);
                }
            }
        }
        return result;
    }
}
