package wang.c10;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import wang.utility.ArrayUtility;

public class Questions {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        try {
            findMissingNum2("E:\\GitRepository\\git\\MyCodes\\wwang\\src\\wang\\c10\\input_data.txt");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //array elements must be greater than 0.
        int[] arr=ArrayUtility.getInstance().generateRandomArray(30, 20);
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        getDuplicate(arr);

        Cache cache = new Cache();
        LRUCache(cache);
    }

    /*********10.2 Social Network Data Structure****************/
    class SocialNetworkDataStructure {

        class Machine{
            public int machineID;
            public HashMap<Integer, Person> persons=new HashMap<Integer, Person>();

            public Person getPersonById(int personId){
                return persons.get(personId);
            }
        }

        class Person{
            private int personId;
            private String info;
            private ArrayList<Integer> friends;

            public Person(int id){
                this.personId=id;
            }

            public String getInfo(){
                return info;
            }
            public void setInfo(String info){
                this.info=info;
            }

            public int[] getFriends(){
                int[] temp=new int[friends.size()];
                for(int i=0; i<temp.length; i++){
                    temp[i]=friends.get(i);
                }
                return temp;
            }

            public int getId(){
                return personId;
            }

            public void addFriend(int id){
                friends.add(id);
            }
        }

        class Server{
            HashMap<Integer, Machine> machines=new HashMap<Integer, Machine>();
            HashMap<Integer, Integer> personToMachineMap=new HashMap<Integer, Integer>();

            public Machine getMachineById(int machineId){
                return machines.get(machineId);
            }

            public int getMachineIdForUser(int personId){
                Integer machineId=personToMachineMap.get(personId);
                return machineId==null? -1: machineId;
            }

            public Person getPersonById(int personId){
                Integer machineId=personToMachineMap.get(personId);
                if(machineId==null) {
                    return null;
                }
                Machine machine=machines.get(machineId);
                if(machine==null) {
                    return null;
                }
                return machine.getPersonById(personId);
            }
        }
    }
    /*********10.2 Social Network Data Structure****************/

    /*********10.3 Find Missing Number****************/
    static long numberOfInts=(long)Integer.MAX_VALUE+1;
    static byte[] bitField=new byte[(int)(numberOfInts/8)];
    @SuppressWarnings("resource")
    static void findMissingNum1(String filePath) throws FileNotFoundException{
        Scanner in=new Scanner(new FileReader(filePath));
        while(in.hasNextInt()){
            int num=in.nextInt();
            bitField[num/8] |= 1<< (num%8);
        }
        int cnt=0;
        for(int i=0; i<bitField.length; i++){
            for(int j=0;j<8; j++){
                cnt++;
                if((bitField[i] & (1<<j)) ==0){
                    System.out.println("cnt: "+cnt+" ; "+(8*i+j));
                    in.close();
                    return;
                }
            }
        }
    }

    //10MB memory
    static int bitsize=1048576;
    static int blockNum=4096;
    static byte[] bitfield=new byte[bitsize/8];
    static int[] blocks=new int[blockNum];
    static void findMissingNum2(String filePath) throws FileNotFoundException{
        @SuppressWarnings("resource")
        Scanner in=new Scanner(new FileReader(filePath));
        int start=0;
        while(in.hasNextInt()){
            int num=in.nextInt();
            blocks[num/(bitfield.length*8)]++;
        }
        System.out.println("blocks array length: "+blocks.length+"\narray: "+Arrays.toString(blocks));

        for(int i=0; i<blocks.length;i++){
            if(blocks[i]< bitfield.length*8){
                start=i*bitfield.length*8;
                break;
            }
        }

        in=new Scanner(new FileReader(filePath));
        while(in.hasNextInt()){
            int num=in.nextInt();
            if(num>=start && num < start+bitfield.length*8){
                bitfield[(num-start)/8] |= 1<< ((num-start)%8);
            }
        }

        System.out.println("\nstarting:  "+start+"\nbitfield.length: "+ bitfield.length); //Arrays.toString(bitfield)
        int cnt=0;
        for(int i=0; i<bitfield.length; i++){
            for(int j=0; j<8; j++){
                cnt++;
                if((bitfield[i] & (1<<j)) ==0 ){
                    System.out.println(cnt+" : "+(i*8+j+start)); // remember start
                    in.close();
                    return;
                }
            }
        }
    }
    /*********10.3 Find Missing Number****************/

    /*********10.4 Find Duplicate Number****************/
    static void getDuplicate(int[] arr){
        BitSet bs=new BitSet(32000);
        for(int i=0; i<arr.length; i++){
            int num=arr[i];
            int num0=num-1;//>=0? num-1: 0 ;
            if(bs.get(num0)){
                System.out.println(num);
            }else{
                bs.set(num0);
            }
        }
        ArrayList<Integer> list=new ArrayList<Integer>();
        for(int i=0; i<32000; i++){
            if(bs.get(i)){
                list.add(i+1);
            }
        }
        System.out.println(bs.get(1)+ " , "+ list);
    }
    /*********10.4 Find Duplicate Number****************/

    /*********10.7 LRU Cache****************/
    static void LRUCache(Cache cache){
        for (int i = 0; i < 20; i++) {
            String query = "query" + i;
            cache.set(query, generateResults(i));
            if (i == 9 || i == 16 || i == 19) {
                cache.getResults("query" + 2);
                cache.getResults("query" + 6);
                cache.getResults("query" + 9);
            }
        }

        for (int i = 0; i < 30; i++) {
            String query = "query" + i;
            String[] results = cache.getResults(query);
            System.out.print(query + ": ");
            if (results == null) {
                System.out.print("null");
            } else {
                for (String s : results) {
                    System.out.print(s + ", ");
                }
            }
            System.out.println("");
        }
    }
    private static String[] generateResults(int i){
        String[] results={"result A "+i,"result B "+i,"result C "+i};
        return results;
    }
    /*********10.7 LRU Cache****************/

}
