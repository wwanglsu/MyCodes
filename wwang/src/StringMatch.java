

public class StringMatch {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String text="ababc34geabcaabc";
        String pattern="abc";

        matchString(text, pattern);
        System.out.println();
        KMP_StringMatch(text, pattern);

    }
    //method 1: navie string match  O((n-m+1)*m)
    static void matchString(String text, String pattern){
        int n=text.length();
        int m=pattern.length();

        for(int i=0; i<=n-m; i++){
            for(int j=0; j<m; j++){
                if(text.charAt(i+j) != pattern.charAt(j)) {
                    break;
                }
                if(j==m-1) {
                    System.out.print(i+"  ");
                }
            }
        }
    }
    //method 2: KMP total O(n)+O(m) 
    static void KMP_StringMatch(String text, String pattern){
        int n=text.length();
        int m=pattern.length();
        int[] array=prefixComputer(pattern); //O(m)
        int q=0; // match number
        for(int i=1; i<n; i++){
            while(q>0 && pattern.charAt(q) != text.charAt(i)){
                q=array[q-1]; // keep and remember the longest prefix
            }
            if(pattern.charAt(q)==text.charAt(i))
            {
                q=q+1; //next character match
            }
            if(q==m){
                System.out.println("KMP:  "+ (i-m+1 ) );
                q=array[q-1]; //keep the longest prefix
            }
        }
    }

    static int[] prefixComputer(String pattern){
        if(pattern==null) {
            return null;
        }
        int m=pattern.length();
        int len=0; // longest prefix length
        int[] array=new int[m];
        array[0]=0;
        for(int i=1; i<m; i++){
            while(len>0 && pattern.charAt(len) != pattern.charAt(i)){
                len=array[len-1];// keep the longest prefix
            }
            if(pattern.charAt(len)==pattern.charAt(i)){
                len+=1;
            }
            array[i]=len;
        }
        return array;
    }

}
