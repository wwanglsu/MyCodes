package wang.c9;

public class LPSubstring {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        char[] a="abacdfgdcaba".toCharArray();//forgeeksskeegfor
        StringBuffer sb=new StringBuffer("abacdfgdcaba");
        char[] b=sb.reverse().toString().toCharArray();
        System.out.println("abacdfgdcaba(Error)-using LCSubstring: "+LCSubstring.lcsSubstring_DP(a,b));
        // System.out.println(ss.[1]);
        char[] a1="aabaac".toCharArray();
        System.out.println("Method 2-using Brute Force: "+lpsubstring_BruteForce(a));
        System.out.println("Method 3-using DP: "+lpsubstring_DP(a));
    }

    //Time:O(n^3), space O(1)
    static int lpsubstring_BruteForce(char[] a){
        int result=0; int maxlength=1; int count=0;
        for(int i=0; i<a.length; i++){
            for(int j=i+1; j<a.length; j++){
                int left=i; int right=j;
                while(left<right){           
                    if(a[left]==a[right]){
                        left++; right--; count++;
                        result+=2;
                        if(left==right) {
                            result++;
                        }
                    }               
                    else { result-=2*count; break;}
                }
                maxlength=max(result, maxlength);
                result=0;            
            }
        }
        return maxlength;
    }   
    static int max(int i, int j){
        return (i>j) ? i: j;
    }
    //Time:O(n^2), space O(n^2)
    static int lpsubstring_DP(char[] a){
        int n=a.length;
        boolean[][] table=new boolean[n][n];
        // All substrings of length 1 are palindromes
        int maxLength = 1;
        for( int i = 0; i < n; ++i ) {
            table[i][i] = true;
        }        
        // check for sub-string of length 2.
        int start = 0;
        for( int i = 0; i < n-1; ++i )
        {
            if( a[i] == a[i+1] )
            {
                table[i][i+1] = true;
                start = i;
                maxLength = 2;
            }
        }       
        // Check for lengths greater than 2. k is length of substring
        for( int k = 3; k <= n; ++k )
        {
            // Fix the starting index
            for( int i = 0; i < n - k + 1 ; ++i )
            {
                // Get the ending index of substring from starting index i and length k
                int j = i + k - 1;    
                // checking for sub-string from ith index to jth index iff str[i+1]
                // to str[j-1] is a palindrome
                if( table[i+1][j-1] && a[i] == a[j] )
                {
                    table[i][j] = true;

                    if( k > maxLength )
                    {
                        start = i;
                        maxLength = k;
                    }
                }
            }
        }
        System.out.println("Palindrome Substring: "+String.valueOf(a).substring(start, start+maxLength));
        return maxLength; // return length of LPS
    }
}
