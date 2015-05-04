package wang.c10;

import java.util.ArrayList;
import java.util.HashMap;

public class SocialNetworkDataStructure {

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
