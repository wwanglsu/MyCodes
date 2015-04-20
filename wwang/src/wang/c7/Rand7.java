package wang.c7;

public class Rand7 {
    public static void main(String[] args){
        System.out.println(rand7());
        System.out.println(rand7_());
    }

    static int rand5(){
        int num=(int)(Math.random()*100) % 5;
        return num;
    }

    static int rand7(){
        while(true){
            int num= 5* rand5()+rand5();
            if(num<21) {
                return num%7;
            }
        }
    }

    static int rand7_(){
        while(true){
            int r1= 2* rand5();
            int r2=rand5();
            if(r2!=4){
                int rand=r2%2;
                int num=r1+rand;
                if(num<7) {
                    return num;
                }
            }
        }
    }
}
