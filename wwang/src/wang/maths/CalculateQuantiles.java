package wang.maths;


public class CalculateQuantiles {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        int[] res=new int[]{1,2,2,2,3,4,4,5,20,20,20,20,20,20,20,34,45,55,56,89};
        System.out.println("length: "+res.length);
        int[] ans=getQuantiles(res, 4);
        for(int iinn: ans){
            System.out.println(iinn);
        }
    }

    private static int[] getQuantiles(int[] array, int Q){
        int[] ans=new int[Q-1];
        for(int i=1; i<Q; i++){
            int j=(array.length * i/Q);
            System.out.println(j+", "+Q);
            ans[i-1]=array[j];
        }
        return ans;
    }
}
