package wang.c10;

import java.util.HashMap;

//implement LRU cache in detail
public class Cache {
    public static int MAX_SIZE=10;
    public DoubleLinkedNode head;
    public DoubleLinkedNode tail;
    public HashMap<String, DoubleLinkedNode> map;
    public int size=0;

    public Cache(){
        map=new HashMap<String, DoubleLinkedNode>();
    }

    public void moveToFront(String query){
        DoubleLinkedNode node=map.get(query);
        moveToFront(node);
    }

    public String[] getResults(String query){
        if(map.containsKey(query)){
            DoubleLinkedNode node=map.get(query);
            moveToFront(node);
            return node.results;
        }
        return null;
    }

    public void set(String query, String[] results){
        if(map.containsKey(query)){
            DoubleLinkedNode node=map.get(query);
            node.results=results;
            moveToFront(node);
            return;
        }
        DoubleLinkedNode node=new DoubleLinkedNode(query, results);
        moveToFront(node);
        map.put(query, node);
        if(size>MAX_SIZE){
            map.remove(tail.query);
            removeFromLinkedList(tail);
        }
    }

    private void moveToFront(DoubleLinkedNode node){
        if(node==head) {
            return;
        }
        removeFromLinkedList(node);
        node.next=head;
        if(head!=null){
            head.previous=node;
        }
        head=node;
        size++;
        if(tail==null) {
            tail=node;
        }
    }

    private void removeFromLinkedList(DoubleLinkedNode node){
        if(node==null) {
            return;
        }

        if(node.next !=null || node.previous !=null){
            size--;
        }

        DoubleLinkedNode pre=node.previous;
        if(pre!=null) {
            pre.next=node.next;
        }

        DoubleLinkedNode next=node.next;
        if(next !=null) {
            next.previous=pre;
        }

        if(node==head){
            head=next;
        }

        if(node==tail){
            tail=pre;
        }

        node.next=null;
        node.previous=null;
    }

}
