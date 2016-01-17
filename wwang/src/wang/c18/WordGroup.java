package wang.c18;

import java.util.Hashtable;
import java.util.ArrayList;

public class WordGroup {
    private Hashtable<String, Boolean> lookup=new Hashtable<String, Boolean>();
    private ArrayList<String> group=new ArrayList<String>();
    
    public WordGroup(){
        
    }
    
    public boolean containsWord(String s){
        return lookup.containsKey(s);
    }
    
    public void addWord(String s){
        group.add(s);
        lookup.put(s, true);
    }
    
    public int length(){
        return group.size();
    }
    
    public String getWord(int i){
        return group.get(i);
    }
    
    public ArrayList<String> getWords(){
        return group;
    }
    
    public static WordGroup[] createWordGroups(String[] list){
        WordGroup[] groupList;
        int maxWordLength=0;
        //find out the length of the longest word
        for(int i=0; i<list.length;i++){
            if(maxWordLength < list[i].length()){
                maxWordLength = list[i].length();
            }
        }
        
        groupList = new WordGroup[maxWordLength];
        for(int i=0; i<list.length;i++){
            int wordLength=list[i].length()-1;
            if(groupList[wordLength]==null){
                groupList[wordLength] = new WordGroup();
            }
            groupList[wordLength].addWord(list[i]);
        }
        return groupList;
    }
}
