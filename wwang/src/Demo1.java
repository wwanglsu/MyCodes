import java.util.Hashtable;
import java.util.Map;


public class Demo1 {
	static int a;


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Map<String, String> map=new Hashtable<String, String>();
		map.put("aa", "hello");
		map.put("aa", "hello234");

		System.out.println(map.size()+" , "+map.get("aa"));

		System.out.println(new Double(25.69).hashCode());

		Pair<String, Integer> pair=new Pair<String, Integer>("hello", 8976);
		System.out.println(pair.getFirst() +" ; "+pair.getSecond()+" ;  -> "+a);

		pair.fill(new String[]{"hello","world","!\n******"});

		Demo1 ob=new Demo1();
		ob.fill( new Integer[]{ 10,20} );
	}



	public <E extends Comparable, Cloneable> void fill(E[] e){
		new Demo1.Pair<String, String>("arg1","arg2").fill(e);
	}

	static class Pair<T,S>{
		private T first;
		private S second;

		public Pair(T first, S second){
			this.first=first;
			this.second=second;
		}

		public <E extends Comparable , Cloneable > void fill(E... e){
			for(E a : e){
				System.out.print(a+" ");
			}
		}

		public T getFirst(){
			return first;
		}

		public S getSecond(){
			return second;
		}

	}

	static{
		System.out.println("First print...");
		//System.exit(0);
	}

}
