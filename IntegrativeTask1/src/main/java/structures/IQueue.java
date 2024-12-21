package structures;

import exceptions.QueueException;

public interface IQueue<T>{
    public abstract boolean isEmpty();
    public abstract void enqueue(T item);
    public abstract T dequeue() throws QueueException;
    public abstract T front() throws QueueException;
}
