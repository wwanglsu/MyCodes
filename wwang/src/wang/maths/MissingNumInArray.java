package wang.maths;

public class MissingNumInArray {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] array={4,6,7,9,1,8,2,5,15,13,12,14,0, 10,11,16};
        System.out.println(findMissingNum(array));
        System.out.print(Integer.MAX_VALUE);
    }

    static int findMissingNum(int[] array){
        byte[] bitField=new byte[array.length];
        for(int e: array){
            bitField[e/8] |= 1<< (e%8);
        }

        for(int i=0; i<bitField.length; i++){
            for(int j=0; j<8; j++){
                if( (bitField[i] & (1<<j)) ==0){
                    return i*8+j;
                }
            }
        }
        return -1;
    }

}
