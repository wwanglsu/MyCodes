package wang.c17;




public class Questions {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        swap(3.9888888, 20.123);
        swap_xor(34, 89);
        char[][] board = { 
                {'x', 'x', 'o'},
                {' ', 'x', ' '},
                {' ', ' ', 'x'}};
        int v = convertBoardToInt(board);
        System.out.println(v);

        System.out.println(12^20^12);
        System.out.println(factorial(20));
        System.out.println(countFactZeros(20) +" , "+countFactZeros2(20));

        System.out.println(getMaxNaive(230, 112) + " , " +getMax2(230, 989));
        System.out.println(estimate("RGBY", "GGRR"));
        int[] array=new int[]{1,2,4,7,10,11,7,12,6,7,16,18,19};
        findUnsortedSequence(array);
        System.out.println(numToString(191323984));
        int[] array1=new int[]{1,-2,4,7,-10,-11,7,-12,6,7,16,-18,19};
        System.out.println(getContinuousMaxSum1(array1));
        System.out.println(getContinuousMaxSum2(array1, 0, array1.length-1));
    }

    /*****17.1 swap a number in place without temporary variable*********/
    static void swap(double a, double b){
        a=a-b;
        b=a+b;
        a=b-a;
        System.out.println("( "+a+", "+b+" )");
    }

    static void swap_xor(long a, long b){
        a=a^b;
        b=a^b;
        a=a^b;
        System.out.println("( "+a+", "+b+" )");
    }
    /*****17.1 swap a number in place without temporary variable*********/

    /*****17.2 design an algorithm to fix who has won tic-tac-toe game**************/
    enum Piece{ Empty, Red, Blue}

    Piece hasWon1(Piece[][] board){
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                //check rows
                if(board[i][0] !=Piece.Empty && board[i][0]==board[i][1]&&board[i][0]==board[i][2]) {
                    return board[i][0];
                }
                //check columns
                if(board[0][i]!=Piece.Empty && board[1][i]==board[0][i]&&board[0][i]==board[2][i]) {
                    return board[0][i];
                }
                //check diagonal
                if(board[0][0]!=Piece.Empty && board[0][0]==board[1][1] && board[0][0]==board[2][2]) {
                    return board[0][0];
                }
                //check reverse diagonal
                if(board[0][2]!=Piece.Empty &&board[0][2]==board[1][1]&&board[0][2]==board[2][0] ) {
                    return board[0][2];
                }
            }
        }
        return Piece.Empty;
    }

    static int convertBoardToInt(char[][] board) {
        int factor = 1;
        int sum = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int v = 0;
                if (board[i][j] == 'x') {
                    v = 1;
                } else if (board[i][j] == 'o') {
                    v = 2;
                }
                sum += v * factor;
                factor *= 3;
            }
        }
        return sum;
    }

    /*****17.2 design an algorithm to fix who has won tic-tac-toe game**************/

    /*****17.3 get trailing zeros in factorial****/
    static int countFactZeros(int num){
        int cnt=0;
        for(int i=2; i<=num; i++){
            cnt+= factorsOfFive(i);
        }
        return cnt;
    }
    private static int factorsOfFive(int i){
        int count=0;
        while(i%5==0){
            count++;
            i=i/5;
        }
        return count;
    }

    static int countFactZeros2(int num){
        int cnt=0;
        if(num<0) {
            return -1;
        }
        for(int i=5; num/i>0; i*=5){
            cnt += num/i;
        }
        return cnt;
    }
    static int factorial(int num){
        if(num==1) {
            return 1;
        } else if(num>1){
            return num * factorial(num-1);
        }else{
            return -1; // error
        }
    }
    /*****17.3 get trailing zeros in factorial****/

    /*****17.4 find the maximum of two numbers, without using if-else***************/
    static int getMaxNaive(int a, int b){
        int k=sign(a-b);
        int q=flip(k);
        return a*k + b*q;
    }
    private static int flip(int bit){
        return 1^bit;
    }
    private static int sign(int a){
        return flip((a>>31) & 0x1);
    }

    static int getMax2(int a, int b){
        int c=a-b;
        int sa=sign(a);
        int sb=sign(b);
        int sc=sign(c);

        int use_sign_of_a=sa^sb;
        int use_sign_of_c=flip(sa^sb);

        int k=use_sign_of_a * sa + use_sign_of_c * sc;
        int q=flip(k);
        return a*k + b*q;
    }
    /*****17.4 find the maximum of two numbers, without using if-else***************/

    /*****17.5 hit and pseudo-hit in Game of Master**********/
    static class Result{
        public int hits=0;
        public int pseudoHits=0;
        @Override
        public String toString(){
            return "("+hits +", "+pseudoHits+")";
        }
    }
    private static int code(char c){
        switch(c){
            case 'B': 
                return 0;
            case 'G':
                return 1;
            case 'R':
                return 2;
            case 'Y':
                return 3;
            default:
                return -1;            
        }
    }
    private static int MAX_COLOR=4;
    static Result estimate(String guess, String solution){
        if(guess.length() != solution.length()) {
            return null;
        }

        Result result=new Result();
        int[] frequency=new int[MAX_COLOR];

        for(int i=0; i<guess.length(); i++){
            if(guess.charAt(i)==solution.charAt(i)) {
                result.hits++;
            } else{
                int index=code(solution.charAt(i));
                if(index>=0) {
                    frequency[index]++;
                }
            }
        }

        //calculate pseudo-hits
        for(int i=0; i<guess.length(); i++){
            int index=code(guess.charAt(i));
            if(index>=0 && frequency[index] >0 && guess.charAt(i)!=solution.charAt(i)){
                result.pseudoHits++;
                frequency[index]--;
            }
        }
        return result;
    }
    /*****17.5 hit and pseudo-hit in Game of Master**********/

    /*****17.6 find index m and n, so that sort elements m through n, the entire array would be sorted.*******************/
    private static int findEndOfLeftSubsequence(int[] array){
        for(int i=1; i<array.length; i++){
            if(array[i]<array[i-1]) {
                return i-1;
            }
        }
        return array.length-1;
    }
    private static int findStartOfRightSubsequence(int[] array){
        for(int i=array.length-2; i>=0; i--){
            if(array[i]>array[i+1]) {
                return i+1;
            }
        }
        return 0;
    }
    private static int shrinkLeft(int[] array, int min_index, int leftEnd){
        int comp=array[min_index];
        for(int i=leftEnd-1; i>=0; i--){
            if(array[i]<=comp) {
                return i+1;
            }
        }
        return 0;
    }
    private static int shrinkRight(int[] array, int max_index, int start){
        int comp=array[max_index];
        for(int i=start; i<array.length; i++){
            if(array[i]>=comp) {
                return i-1;
            }
        }
        return array.length-1;
    }
    private static boolean validate(int[] array, int left_index, int right_index) {
        int[] middle = new int[right_index - left_index + 1];
        for (int i = left_index; i <= right_index; i++) {
            middle[i - left_index] = array[i];
        }
        java.util.Arrays.sort(middle);
        for (int i = left_index; i <= right_index; i++) {
            array[i] = middle[i - left_index];
        }
        for (int i = 1; i < array.length; i++) {
            if (array[i-1] > array[i]) {
                return false;
            }
        }
        return true;
    }

    static void findUnsortedSequence(int[] array){
        //find left subsequence
        int end_left=findEndOfLeftSubsequence(array);
        //find right subsequence
        int start_right=findStartOfRightSubsequence(array);
        //find min and max element of middle
        int min_index=end_left+1;
        if(min_index>=array.length)
        {
            return;//already sorted
        }

        int max_index=start_right-1;

        for(int i=end_left; i<=start_right;i++){
            if(array[i] < array[min_index]){
                min_index=i;
            }
            if(array[i]>array[max_index]) {
                max_index=i;
            }
        }
        //slide left until less than array[min_index]
        int left_index=shrinkLeft(array, min_index, end_left);
        int right_index=shrinkRight(array, max_index, start_right);

        if (validate(array, left_index, right_index)) {
            System.out.println("TRUE: " + left_index + " " + right_index);
        } else {
            System.out.println("FALSE: " + left_index + " " + right_index);
        }
    }
    /*****17.6 find index m and n, so that sort elements m through n, the entire array would be sorted.*******************/

    /*****17.7 print English phrase describing integer*********/
    public static String[] digits = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    public static String[] teens = {"Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    public static String[] tens = {"Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    public static String[] bigs = {"", "Thousand", "Million"};

    static String numToString(int num){
        if(num==0) {
            return "Zero";
        }
        if(num<0) {
            return "Negative"+ numToString(-1*num);
        }

        int cnt=0;
        String str="";

        while(num > 0){
            if(num % 1000 != 0){
                str= numToString100(num % 1000) + bigs[cnt]+" " +str;
            }
            num /=1000;
            cnt++;
        }
        return str;
    }
    private static String numToString100(int num){
        String str="";
        //convert hundreds place
        if(num >=100){
            str += digits[num/100-1] +" Hundred ";
            num %=100;
        }
        //convert tens place
        if(num >=11 && num <=19){
            return str + teens[num-11] +" ";
        }else if(num ==10 || num >=20){
            str += tens[num/10-1]+ " ";
            num %= 10;
        }
        //convert ones place
        if(num >=1 && num <=9){
            str += digits[num -1] + " ";
        }
        return str;
    }
    /*****17.7 print English phrase describing integer*********/

    /*****17.8 find the contiguous max sum***************/
    static int getContinuousMaxSum1(int[] array){  //DP
        int current_max=array[0];
        int max_so_far=array[0];
        for(int i=1; i<array.length; i++){
            current_max= Math.max(array[i], array[i]+current_max);
            max_so_far=Math.max(max_so_far, current_max);
        }
        return max_so_far;
    }

    static int getContinuousMaxSum2(int[] arr, int low, int high){
        int mid=(low+high)/2, left_sum=0, right_sum=0, cross_sum=0;   
        if(low>=high) {
            return arr[low];
        } else{
            left_sum=getContinuousMaxSum2(arr, low, mid);
            right_sum=getContinuousMaxSum2(arr, mid+1, high);
            cross_sum=getMaxCrossSum(arr, low, high);
        }
        if(left_sum>right_sum && left_sum>cross_sum) {
            return left_sum;
        } else if(right_sum>left_sum && right_sum>cross_sum) {
            return right_sum;
        } else {
            return cross_sum;
        }
    }
    private static int getMaxCrossSum(int[] array, int low, int high){
        int mid=(low+high)/2;
        int left_sum=Integer.MIN_VALUE;
        int curr_l_sum=0;
        for(int i=mid; i>=0; i--){
            curr_l_sum +=array[i];
            if(curr_l_sum >left_sum){
                left_sum=curr_l_sum;
            }
        }

        int right_sum=Integer.MIN_VALUE;
        int curr_r_sum=0;
        for(int i=mid+1; i<=high; i++){
            curr_r_sum += array[i];
            if(curr_r_sum >right_sum) {
                right_sum=curr_r_sum;
            }
        }

        if(left_sum>0 && right_sum>0) {
            return left_sum+right_sum;
        } else {
            return Math.max(left_sum, right_sum);
        }
    }
    /*****17.8 find the contiguous max sum***************/

}
