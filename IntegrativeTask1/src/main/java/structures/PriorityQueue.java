package structures;

import exceptions.PriorityQueueException;

import java.util.ArrayList;
import java.util.List;

public class PriorityQueue<T extends Comparable<T>> implements IPriorityQueue<T> {

    private List<T> elements;
    private int heapSize;

    public PriorityQueue() {
        this.elements = new ArrayList<>();
        this.heapSize = 0;
    }

    public boolean isEmpty(){
        return heapSize == 0;
    }

    public void enqueue(T element){
        elements.add(element);
        heapSize++;
        buildMaxHeap();
    }

    public T dequeue() throws PriorityQueueException {
        if(isEmpty()){
            throw new PriorityQueueException("The priority queue is empty.");
        }

        T maxElement = elements.get(0);
        elements.set(0, elements.get(heapSize - 1));
        elements.remove(heapSize - 1);
        heapSize--;

        maxHeapify(0);
        return maxElement;
    }

    public T front() throws PriorityQueueException {
        if(isEmpty()){
            throw new PriorityQueueException("The priority queue is empty.");
        }

        return elements.get(0);
    }

    private void maxHeapify(int i){
        int left = getLeftChild(i);
        int right = getRightChild(i);

        int largest = i;

        if(left < heapSize && elements.get(left).compareTo(elements.get(i)) > 0){
            largest = left;
        }

        if(right < heapSize && elements.get(right).compareTo(elements.get(largest)) > 0){
            largest = right;
        }

        if(largest != i){
            T temp = elements.get(i);
            elements.set(i, elements.get(largest));
            elements.set(largest, temp);
            maxHeapify(largest);
        }
    }

    private void buildMaxHeap(){
        for(int i = (heapSize / 2) - 1; i>= 0; i--){
            maxHeapify(i);
        }
    }

    @Override
    public String toString(){
        return elements.toString();
    }

    private int getLeftChild(int i){
        return (2 * i) + 1;
    }

    private int getRightChild(int i){
        return (2 * i) + 2;
    }
}
