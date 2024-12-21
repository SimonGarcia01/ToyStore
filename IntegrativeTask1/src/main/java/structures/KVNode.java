package structures;

public class KVNode<K,V> {
    private K key;
    private V value;

    private KVNode<K,V> next;
    private KVNode<K,V> previous;

    public KVNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public void setValue(V value){
        this.value = value;
    }
    public K getKey(){
        return key;
    }
    public V getValue(){
        return value;
    }

    public KVNode<K, V> getNext() {
        return next;
    }

    public void setNext(KVNode<K, V> next) {
        this.next = next;
    }

    public KVNode<K, V> getPrevious() {
        return previous;
    }

    public void setPrevious(KVNode<K, V> previous) {
        this.previous = previous;
    }
}
