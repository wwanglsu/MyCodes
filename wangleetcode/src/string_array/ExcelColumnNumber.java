package string_array;

public class ExcelColumnNumber {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        System.out.println("Excel Sheet Column Number: "+titleToNumber("ZRKAD"));
        System.out.println("NUMBER TO EXCEL COLUMN: "+numberToTitle(12205210));
    }
    
    /*
     * Excel Sheet Column Number:
     * Given a column title as appear in an Excel sheet, return its corresponding column number.
     */
    private static int titleToNumber(String s){
        if(s==null) return 0;
        
        int sum =0;
        int pos = s.length()-1;
        int e=0;
        while(pos >=0){
            sum += (int)Math.pow(26, e) * (s.charAt(pos)-'A' +1);
            pos--;
            e++;
        }
        return sum;
    }

    /*
     * NUMBER TO EXCEL COLUMN:
     * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
     * just like binary to decimal and vice versa
     */
    private static String numberToTitle(int n){
        StringBuilder sb = new StringBuilder();
        while(n>0){
            char c = (char)(65+(n-1)%26);
            sb.append(c);
            n=(n-1)/26;
        }
        return sb.reverse().toString();
    }
}
