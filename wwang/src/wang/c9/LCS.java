package wang.c9;

public class LCS {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        char[] a="AGGTABDC".toCharArray();
        char[] b="GXTXAYBC".toCharArray();
        System.out.println(LCS_recursive(a, a.length, b, b.length));
        System.out.println(LCS_DP(a, b));
    }

    static int LCS_recursive(char[] a, int aLength, char[] b, int bLength){
        if(aLength==0 || bLength==0) {
            return 0;
        }
        if(aLength>0 && bLength>0 && a[aLength-1]==b[bLength-1]) {
            return 1+LCS_recursive(a, aLength-1, b, bLength-1);
        }
        if(aLength>0 && bLength>0 && a[aLength-1] != b[bLength-1]) {
            return Math.max(LCS_recursive(a, aLength-1, b, bLength), LCS_recursive(a, aLength, b, bLength-1));
        }
        return 0;
    }

    static int LCS_DP(char[] a, char[] b){
        int aLength=a.length;
        int bLength=b.length;
        int[][] L=new int[aLength+1][bLength+1];

        for(int i=0; i<=aLength; i++){
            for(int j=0; j<=bLength; j++){
                if(i==0 || j==0) {
                    L[i][j]=0;
                }
                else if(a[i-1]==b[j-1]) {
                    L[i][j]=L[i-1][j-1]+1;
                }
                else {
                    L[i][j]= Math.max(L[i][j-1], L[i-1][j]);
                }
            }
        }
        return L[aLength][bLength];
    }

}
