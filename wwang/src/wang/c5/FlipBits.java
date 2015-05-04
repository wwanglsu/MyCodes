package wang.c5;

public class FlipBits {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] array=new int[]{1, 0, 1, 0, 0, 1, 0, 1};
        int num=maxFlipBitsNum(array);
        System.out.println(num);
    }
    //the minimal sum contains most -1s in the transformed array, which corresponds to most 1s in the original problem
    static int maxFlipBitsNum(int[] array){
        if(array==null || array.length==0) {
            return 0;
        }
        HelperClass obj1=new HelperClass();
        HelperClass obj2=new HelperClass();
        int sum=0;
        // count the 1s in the original array and
        // do the 0 -> 1 and 1 -> -1 conversion
        for(int i=0; i<array.length; i++){
            sum+= array[i];
            array[i] = array[i]==0? 1:-1;
        }
        //find the maximum subarray
        for(int i=0; i<array.length; i++){
            //update obj2
            if(obj2.value + array[i] >=0) {
                obj2.value +=array[i];
            } else{
                obj2.left=i+1;
                obj2.value=0;
            }
            //update obj1
            if(obj1.value < obj2.value){
                obj1.left=obj2.left;
                obj1.right=i;
                obj1.value=obj2.value;
            }
        }
        System.out.println("range: "+obj1.left+" , "+obj1.right);
        return sum+obj1.value;
    }
}
