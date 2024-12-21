package structures;

import exceptions.KeyNotFoundException;

import java.util.List;

public interface ILinkedList<K extends Comparable<K>, V> {
    public abstract void add(K key, V value);
    public abstract V search(K key);
    public abstract void delete(K key) throws KeyNotFoundException;
    public abstract List<V> getValues();
}
