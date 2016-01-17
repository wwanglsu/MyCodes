package wang.c18;

import wang.utility.TrieTree;

// A class represents a rectangular array of letters.
public class Rectangle {
    //rectangle data
    public int height;
    public int length;
    public char[][] matrix;
    
    public Rectangle(int len){
        this.length = len;
    }
    
    public Rectangle(int length, int height, char[][] letters){
        this.height = letters.length;
        this.length = letters[0].length;
        matrix = letters;
    }
    
    public char getLetter(int i, int j){
        return matrix[i][j];
    }
    
    public String getColumn(int i){
        char[] column = new char[height];
        for(int j=0; j<height; j++){
            column[j] = getLetter(j, i);
        }
        return new String(column);
    }
    
    public boolean isComplete(int l, int h, WordGroup groupList){
        //check if we have formed a complete rectangle
        if(height == h){
            //check whether each column is a word in the dictionary
            for(int i=0; i<l;i++){
                String col=getColumn(i);
                if(!groupList.containsWord(col)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    public boolean isPartialOK(int l, TrieTree trie){
        if(height == 0) {
            return true;
        }
        for(int i=0; i < l; i++){
            String col = getColumn(i);
            if(!trie.contains(col)){
                return false;
            }
        }
        return true;
    }
    
    public Rectangle append(String s){
        if(s.length() == length){
            char[][] temp = new char[height+1][length];
            for(int i=0;i<height;i++){
                for(int j=0;j<length;j++){
                    temp[i][j] = matrix[i][j];
                }
            }
            s.getChars(0, length, temp[height], 0);
            return new Rectangle(length, height+1, temp);
        }
        return null;
    }
    
    public void print(){
        for(int i=0;i<height;i++){
            for(int j=0;j<length;j++){
                System.out.print(matrix[i][j]);
            }
            System.out.println(" ");
        }        
    }
}
