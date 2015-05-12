package wang.utility;

import java.util.ArrayList;

public class SuffixTree {

    SuffixNode root=new SuffixNode();
    //constructor
    public SuffixTree(String s){
        for(int i=0; i<s.length(); i++){
            String suffix=s.substring(i);
            root.insert(suffix, i);
        }
    }
    public ArrayList<Integer> search(String s){
        return root.search(s);
    }
}
