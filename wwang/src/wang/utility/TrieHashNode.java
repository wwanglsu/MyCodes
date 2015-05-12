package wang.utility;

import java.util.Hashtable;

public class TrieHashNode {

    private char character;    
    private boolean terminates=false;
    private Hashtable<Character, TrieHashNode> children;

    public TrieHashNode(){
        children=new Hashtable<Character, TrieHashNode>();
    }

    public TrieHashNode(char character){
        this();
        this.character=character;
    }

    public char getChar(){
        return character;
    }

    public void addWord(String word){
        if(word==null || word.isEmpty()) {
            return;
        }

        TrieHashNode child;
        char firstChar=word.charAt(0);

        TrieHashNode t=getChild(firstChar);
        if(t==null){
            child=new TrieHashNode(firstChar);
            children.put(firstChar, child);
        }else{
            child=t;
        }

        if(word.length()>1){
            child.addWord(word.substring(1));
        }else{
            child.setTerminates(true);
        }
    }

    public TrieHashNode getChild(char c){
        for(Character e: children.keySet()){
            if(c==e){
                return children.get(e);
            }
        }
        return null;
    }

    public boolean terminates(){
        return terminates;
    }

    public void setTerminates(boolean t){
        terminates=t;
    }
}
