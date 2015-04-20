package wang.c8;

public class Cell<K, V> {
    private K key;
    private V value;

    public Cell(K key, V value){
        this.key=key;
        this.value=value;
    }

    public boolean equivalent(Cell<K, V> c){
        return equals(c.getKey());
    }

    public boolean equivalent(K key){
        return this.key.equals(key);
    }

    public K getKey(){
        return this.key;
    }

    public V getValue(){
        return this.value;
    }

    @Override
    public String toString(){
        return "( "+key+", "+value+")";
    }
}
