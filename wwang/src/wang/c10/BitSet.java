package wang.c10;

public class BitSet {
    int[] bitset;

    public BitSet(int size){
        bitset=new int[size>>5];
    }

    boolean get(int pos){
        int wordNum= (pos /32);
        int bitNum = (pos % 32);
        return (bitset[wordNum] & (1<<bitNum) )  != 0;
    }

    void set(int pos){
        int wordNum=(pos / 32);
        int bitNum=(pos % 32);

        bitset[wordNum] |= (1<<bitNum);
    }
}
