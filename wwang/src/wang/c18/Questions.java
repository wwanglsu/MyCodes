package wang.c18;

public class Questions {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(sum(231, 4972));
    }

    /*****18.1 add two numbers, not use + or any arithmetic operator*************/
    static int sum(int a, int b){
        if(b==0) {
            return a;
        }
        int sum=a^b; //add without carrying
        int carry=(a&b)<<1; //carry, but don't add
        return sum(sum, carry); //recurse until there's nothing to carry
    }
    /*****18.1 add two numbers, not use + or any arithmetic operator*************/

    /*****18.2 shuffle poker cards*****************/

    /*****18.2 shuffle poker cards*****************/
}
