package wang.maths;

import java.util.ArrayList;

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

    static int findMissing(ArrayList<BitInteger> array) {
        return findMissing(array, BitInteger.INTEGER_SIZE - 1);
    }        

    private static int findMissing(ArrayList<BitInteger> input, int column) {
        if (column < 0) { // Base case and error condition
            return 0;
        }
        ArrayList<BitInteger> oneBits = new ArrayList<BitInteger>(input.size()/2);
        ArrayList<BitInteger> zeroBits = new ArrayList<BitInteger>(input.size()/2);
        for (BitInteger t : input) {
            if (t.fetch(column) == 0) {
                zeroBits.add(t);
            } else {
                oneBits.add(t);
            }
        }
        if (zeroBits.size() <= oneBits.size()) {
            int v = findMissing(zeroBits, column - 1);
            System.out.print("0");
            return (v << 1) | 0;
        } else {
            int v = findMissing(oneBits, column - 1);
            System.out.print("1");
            return (v << 1) | 1;
        }
    }

}
