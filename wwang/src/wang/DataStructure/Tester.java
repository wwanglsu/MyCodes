package wang.DataStructure;

import java.util.Iterator;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashSet set=new HashSet(10);
		set.add("a1");
		set.add("a2");
		set.add("a3");
		set.add("a4");
		set.add("a5");
		set.add("a6");
		set.add("a7");
		set.add("a8");
		set.add("a9");
		set.add("a10");
		set.add("a11");
		set.add("a12");

		System.out.println("size:  "+ set.size());
		System.out.println(set.contains("a1"));

		Iterator<String> iterator=set.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		if(iterator.hasNext()){
			iterator.next();
			System.out.println("remove now.");
			iterator.remove();
		}
		System.out.println(set.contains("a5"));
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}

	}

}
