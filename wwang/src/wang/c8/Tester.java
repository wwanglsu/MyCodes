package wang.c8;

public class Tester {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        People bob = new People("Bob", 20);
        People jim = new People("Jim", 25);
        People alex = new People("Alex", 30);
        People tim = new People("Tim", 35);
        People maxwell = new People("Maxwell", 40);
        People john = new People("John", 45);
        People julie = new People("Julie", 50);
        People christy = new People("Christy", 55);
        People tim2 = new People("Tim", 100);

        People[] peoples={bob, jim, alex, tim, maxwell, john, julie, christy, tim2};
        MyHashTable<String, People> table=new MyHashTable<String, People>();
        for(People p:peoples){
            table.put(p.getName(), p);
        }

        for(People p: peoples){
            String name=p.getName();
            People people =table.get(name);
            System.out.println("People named " + name + ": " + people.toString());
        }
        System.out.println(("Bob".hashCode() % 10 ));
        System.out.println("Tim".equals("Tim"));
    }

}

class People{
    private String name;
    private int age;

    public People(String n, int a){
        this.name=n;
        this.age=a;
    }

    public int getAge(){return age;}
    public String getName(){return name;}

    @Override
    public String toString(){
        return "(" + name + ", " + age + ")";
    }
}