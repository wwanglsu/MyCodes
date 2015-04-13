
public class SumString {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String str1="123456789123";
        String str2="12344322343222";
        String result=sum(str1, str2);
        System.out.println( result );

    }

    private static String sum(String str1, String str2){
        if(str1==null && str2 !=null) {
            return str2;
        } else if(str1 !=null && str2==null) {
            return str1;
        } else if(str1==null && str2==null){
            return null;
        }

        StringBuilder sb=new StringBuilder();

        for(int i=str1.length()-1, j=str2.length()-1, carry=0;
                (i>=0 || j>=0) || carry!=0; i--, j--){
            int digit1 = i<0 ? 0 : Integer.parseInt(Character.toString(str1.charAt(i)));
            int digit2 = j<0 ? 0 : Integer.parseInt(Character.toString(str2.charAt(j)));
            int digit=digit1+digit2+carry;
            if(digit>9){
                carry=1;
            }else{
                carry=0;
            }
            digit=digit%10;


            sb.append(digit);
        }

        return sb.reverse().toString();
    }

}
