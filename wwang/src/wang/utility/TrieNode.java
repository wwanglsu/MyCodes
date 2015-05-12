package wang.utility;

import java.util.LinkedList;

public class TrieNode {

    //The character stored in this node as data
    private char character;
    //The children of this node in the tree
    private LinkedList<TrieNode> children;
    private boolean terminates=false;

    //Root Node: Constructs an empty Trie Node and initializes the list of its children to an empty list.
    public TrieNode(){
        children=new LinkedList<TrieNode>();
    }
    //Constructs a Trie Node and stores the char in Node.
    public TrieNode(char character){
        this();
        this.character=character;
    }

    public char getChar(){
        return character;
    }
    //Find a child node of this node that has the char argument.
    public TrieNode getChild(char c){
        for(TrieNode t : children){
            if(t.getChar()==c){
                return t;
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

    /* Add the String passed in as argument to the trie, starting at a
     * child node of this node. If any prefix of this String is already
     * present in the trie starting from a child node of this node, only
     * add the remaining part of the String to the trie, at the
     * appropriate position in the trie.
     */
    public void addWord(String word){
        if(word==null | word.isEmpty()) {
            return;
        }

        TrieNode child;
        char firstChar=word.charAt(0);        
        TrieNode t=getChild(firstChar);

        if(t==null){
            child=new TrieNode(firstChar);
            children.add(child);
        }else{
            child=t;
        }

        if(word.length() > 1 ){
            child.addWord(word.substring(1));
        }else{
            setTerminates(true);
        }
    }
}
