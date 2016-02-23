import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class Demo1 {

	public Demo1(){}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		class InnerClass2{
			// TODO Auto-generated constructor stub
			void fill(){
				System.out.println("innerclass within main method.");
			}
		}
		
		Demo1.InnerClass1 inc1=new Demo1.InnerClass1();
		inc1.display();
		
		InnerClass2 inc2=new InnerClass2();
		inc2.fill();
		
		Demo1.InnerClass3 inc3=new Demo1().new InnerClass3();
		inc3.paint();
		
		Set<String> ss=new HashSet<String>();
		ss.add("Hello");
		ss.add("Hello1");
		ss.add("Hello2");
		System.out.println(ss.add("hello"));
		Iterator<String> iterator=ss.iterator();
		/*while(iterator.hasNext()){
			String ele=iterator.next();
			System.out.println(ele);
		}*/
		System.out.println(ss.size());
		
		if(iterator.hasNext()){
			String p=iterator.next();
			ss.remove(p);
			ss.add("welcome");
		}
		
		System.out.println("*************************");
		Map<String, String> map=new Hashtable<String, String>();
		map.put("elo","girl1");
		map.put("alo","girl2");
		map.put("elt","girl3");
		map.put("els","girl4");
		map.put("ecs","girl5");
		
		for(String s: map.keySet()){
			System.out.println(s+" : "+ map.get(s));
		}
		System.out.println( map.get("ds") );
		
		
	}
	
	static class InnerClass1{
		// TODO Auto-generated constructor stub
		void display(){
			System.out.println("inner class outside main method.");
		}		
	}
	
	class InnerClass3{
		void paint(){
			System.out.println("Inner class 3");
		}
	}

}
