package structures;

import exceptions.PriorityQueueException;

public interface IPriorityQueue<T extends Comparable<T>> {
    public abstract boolean isEmpty();
    public abstract void enqueue(T item);
    public abstract T dequeue() throws PriorityQueueException;
    public abstract T front() throws PriorityQueueException;
}
