package structures;

import exceptions.HashTableException;
import exceptions.KeyNotFoundException;
import java.util.List;
import java.util.ArrayList;

public class HashTable<K extends Comparable<K>, V> implements IHashTable<K, V> {

    private ILinkedList<K,V>[] table;
    private final int SIZE;
    private static final double HASHCONSTANT = (Math.sqrt(5)-1)/2;

    public HashTable() {
        SIZE = 100;
        table = new LinkedList[SIZE];

        for (int i = 0; i < SIZE; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hash(K key) {

        int generalHashCode = key.hashCode();

        return (int) Math.floor( SIZE * Math.abs((generalHashCode * HASHCONSTANT) % 1));
    }


    public void add(K key, V value) throws HashTableException {
        if(search(key) != null) {
            throw new HashTableException("The key " + key + " already exists.");
        }
        
        int index = hash(key);
        table[index].add(key, value);
    }

    public V search(K key) {
        int index = hash(key);
        return table[index].search(key);
    }

    public void delete(K key) throws HashTableException{
        int index = hash(key);
        try{
            table[index].delete(key); 
        } catch (KeyNotFoundException e) {
            throw new HashTableException("The key " + key + " does not exist.");
        }
    }

    public List<V> getValues(){
        List<V> tableList = new ArrayList<>();

        for(int n = 0; n < SIZE; n++){
            if(table[n] != null){
                List<V> valueList = table[n].getValues();
                tableList.addAll(valueList);
            }
        }

        return tableList;
    }

}

