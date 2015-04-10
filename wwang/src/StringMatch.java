

public class StringMatch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text="ababc34geabcaabc";
		String pattern="abc";

		matchString(text, pattern);

	}

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

}
