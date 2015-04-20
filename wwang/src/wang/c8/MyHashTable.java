package wang.c8;

import java.util.LinkedList;

public class MyHashTable<K, V> {
    private final int MAX_SIZE=10;
    LinkedList<Cell<K, V>>[] items;

    public MyHashTable(){
        items=new LinkedList[MAX_SIZE];
    }

    public int hashCodeOfKey(K key){
        int num=key.hashCode() % items.length;
        return Math.abs(num);
    }

    public void put(K key, V value){
        int x=hashCodeOfKey(key);
        if(items[x]==null){
            items[x]=new LinkedList<Cell<K,V>>();
        }
        LinkedList<Cell<K, V>> collion=items[x];
        for(Cell<K, V> c: collion){
            if(c.equivalent(key)){
                collion.remove(c);
                break;
            }
        }
        Cell<K, V> cell=new Cell<K,V>(key, value);
        collion.add(cell);
    }

    public V get(K key){
        int x=hashCodeOfKey(key);
        if(items[x]==null){
            return null;
        }

        LinkedList<Cell<K, V>> collision=items[x];
        for(Cell<K, V> c: collision){
            if(c.equivalent(key)){
                return c.getValue();
            }
        }
        return null;
    }
}
