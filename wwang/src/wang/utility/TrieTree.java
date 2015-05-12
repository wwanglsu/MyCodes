package wang.utility;

import java.util.ArrayList;

public class TrieTree {
    //The root of this Trie Tree.
    private TrieNode root;

    public TrieTree(ArrayList<String> list){
        root=new TrieNode();
        for(String word : list){
            root.addWord(word);
        }
    }

    public TrieTree(String[] list){
        root=new TrieNode();
        for(String word : list){
            root.addWord(word);
        }
    }

    //check whether the Trie Tree contains a string with this prefix
    public boolean contains(String prefix){
        return contains(prefix, false);
    }

    public boolean contains(String prefix, boolean exact){
        TrieNode lastNode=root;

        for(int i=0; i<prefix.length(); i++){
            lastNode=lastNode.getChild(prefix.charAt(i));
            if(lastNode==null){
                return false;
            }
        }
        return !exact || lastNode.terminates();
    }
}
