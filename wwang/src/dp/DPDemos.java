package dp;

public class DPDemos {

	public static void main(String[] args){
		
		lpsubstring_DP("akbcecbpooi".toCharArray());
	}
	
	static int lpsubstring_DP(char[] a){
	      int n=a.length;
	      boolean[][] table=new boolean[n][n];
	      // All substrings of length 1 are palindromes
	      int maxLength = 1;
	      for( int i = 0; i < n; ++i )
	        table[i][i] = true;        
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
	       System.out.println("Palindrome Substring: "+String.valueOf(a).substring(start, start+maxLength)+"; maxLength is: "+maxLength);
	       return maxLength; // return length of LPS
	   }
	
}
