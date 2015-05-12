package wang.utility;

import java.util.ArrayList;
import java.util.HashMap;
//The whole Suffix Tree is for one String word.
public class SuffixNode {
    private char value;
    private ArrayList<Integer> indexes=new ArrayList<Integer>();
    HashMap<Character, SuffixNode> children=new HashMap<Character, SuffixNode>();

    public SuffixNode(){

    }

    public void insert(String s, int index){
        indexes.add(index);

        if(s!=null && s.length() >0){
            value=s.charAt(0);
            SuffixNode child=null;

            if(children.containsKey(value)){
                child=children.get(value);
            }else{
                child=new SuffixNode();
                children.put(value, child);
            }
            String remainder=s.substring(1);
            child.insert(remainder, index);
        }
    }

    public ArrayList<Integer> search(String s){
        if(s==null || s.length() ==0) {
            return indexes;
        } else{
            char first=s.charAt(0);
            if(children.containsKey(first)){
                String remainder=s.substring(1);
                return children.get(first).search(remainder);                
            }
        }
        return null;
    }
}
