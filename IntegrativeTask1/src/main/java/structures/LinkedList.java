package structures;

import exceptions.KeyNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class LinkedList<K extends Comparable<K>,V> implements ILinkedList<K, V>{
    private KVNode<K,V> head;

    public LinkedList() {
        head = null;
    }

    public void add(K key, V value) {
        KVNode<K,V> newKVNode =new KVNode<>(key,value);

        if(head == null) {
            head = newKVNode;
        } else {
            if(search(key) == null){
                newKVNode.setNext(head);
                head.setPrevious(newKVNode);
                head = newKVNode;
            }
        }
    }

    public V search(K key) {
        V foundValue = null;

        KVNode<K,V> current = head;
        boolean isFound = false;

        while (current != null && !isFound) {
            if (current.getKey().compareTo(key) == 0) {
                foundValue = current.getValue();
                isFound = true;
            }
            current = current.getNext();
        }

        return foundValue;
    }

    public void delete(K key) throws KeyNotFoundException {

        if(search(key) == null){
            throw new KeyNotFoundException("The key was not found.");
        } else {
            KVNode<K,V> current = head;
            boolean isFound = false;

            while(current != null  && !isFound) {
                if (current.getKey().compareTo(key) == 0) {
                    if(current.getPrevious() == null){
                        head = current.getNext();
                        if(head != null) {
                            head.setPrevious(null);
                        }
                    } else {
                        current.getPrevious().setNext(current.getNext());
                        if (current.getNext() != null) {
                            current.getNext().setPrevious(current.getPrevious());
                        }
                    }
                    isFound = true;
                }
                current = current.getNext();
            }
        }
    }

    public List<V> getValues(){
        List<V> listValues = new ArrayList<>();

        KVNode<K,V> current = head;

        while(current != null){
            listValues.add(current.getValue());
            current = current.getNext();
        }

        return listValues;
    }
}
