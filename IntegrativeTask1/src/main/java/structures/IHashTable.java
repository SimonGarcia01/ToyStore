package structures;

import exceptions.HashTableException;

import java.util.List;

public interface IHashTable<K extends Comparable<K>, V> {
    public abstract void add(K key, V value) throws HashTableException;
    public abstract V search(K key);
    public abstract void delete(K key) throws HashTableException;
    public abstract List<V> getValues();
}
