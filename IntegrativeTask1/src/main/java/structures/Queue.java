package structures;

import exceptions.QueueException;

public class Queue<T> implements IQueue<T> {

    private TNode<T> front;
    private TNode<T> back;

    public Queue() {
        front = null;
        back = null;
    }

    public boolean isEmpty() {
        return front == null && back == null;
    }

    public void enqueue(T item){
        TNode<T> newNode = new TNode<>(item);
        if(isEmpty()){
            front = newNode;
            back = newNode;
        } else {
            back.setNext(newNode);
            newNode.setPrev(back);
            back = newNode;
        }
    }

    public T dequeue() throws QueueException{
        if(isEmpty()){
            throw new QueueException("The Queue is empty.");
        }

        TNode<T> temp = front;
        front = front.getNext();
        if(front != null){
            front.setPrev(null);
        } else{
            back = null;
        }
        return temp.getItem();
    }

    public T front() throws QueueException{
        if(isEmpty()){
            throw new QueueException("The Queue is empty.");
        }

        return front.getItem();
    }
}
