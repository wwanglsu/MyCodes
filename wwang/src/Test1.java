import org.junit.Test;


public class Test1 {


	@Test
	public void test() {
		String str1="abcdefbghij";
		String str2="abcdefghij";
		char c1=str1.charAt(0);
		for(int i=1; i<str1.length(); i++){
			c1=(char)(c1^str1.charAt(i));
		}
		char c2=str2.charAt(0);
		for(int i=1; i<str2.length(); i++){
			c2=(char)(c2^str2.charAt(i));
		}
		System.out.println(  (char)(c1^c2) );
	}

	@Test
	public void test2() {
		//fail("Not yet implemented");
		//Assert.assertEquals(false, true);
		int res=1;
		for(int i=2; i<=35; i++){
			res=res^i;
		}
		System.out.println(res^2);
	}
}
