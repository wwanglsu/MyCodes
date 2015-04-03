import junit.framework.Assert;

import org.junit.Test;


public class Test1 {


	@Test
	public void test() {
		//fail("Not yet implemented");
		Assert.assertEquals(true, true);
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
