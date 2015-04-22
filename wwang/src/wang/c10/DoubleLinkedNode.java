package wang.c10;

public class DoubleLinkedNode {
    public DoubleLinkedNode previous;
    public DoubleLinkedNode next;
    public String query;
    public String[] results;

    public DoubleLinkedNode(String q, String[] r){
        this.query=q;
        this.results=r;
    }
}
