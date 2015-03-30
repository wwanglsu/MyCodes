package wang.maths;

public class N_Power {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		double result=n_power(2.5, 4);
		System.out.println(result);
		
	}
	
	private static double n_power(double base, int power){
		double a=base;
		int i=power;
		double result=1;
		while(i>0){
			if(i%2==0){
				a=a*a;
				i=i/2;
			}else{
				result=result*a;
				i--;
			}
		}
		return result;
	}

}
