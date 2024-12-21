package structures;

import exceptions.PriorityQueueException;
import org.junit.Test;
import org.junit.Assert;
public class PriorityQueueTest {
    private PriorityQueue<Integer> queue;
    public void defaultTest(){
        queue = new PriorityQueue<>();
    }
    @Test
    public void enqueueTest() throws PriorityQueueException {
        //init
        defaultTest();
        //act
        queue.enqueue(1);
        queue.enqueue(2);
        int value=queue.front();
        //assert
        Assert.assertEquals(2,value);
    }
    @Test
    public void dequeueTest() throws PriorityQueueException {
        //init
        defaultTest();
        //act
        queue.enqueue(1);
        queue.enqueue(2);
        queue.dequeue();
        int value=queue.front();
        //assert
        Assert.assertEquals(1,value);
    }
    @Test
    public void frontNullTest() throws PriorityQueueException {
        //init
        defaultTest();
        //act
        queue.enqueue(null);
        Integer value=queue.front();
        //assert
        Assert.assertNull(value);
    }
   @Test
    public void toStringTest() {
        //init
       defaultTest();
       //act
       queue.enqueue(1);
       queue.enqueue(2);
       queue.enqueue(3);
       queue.enqueue(4);
       String message=queue.toString();
       //assert
       Assert.assertEquals(message,"[4, 3, 2, 1]");
   }
   @Test
    public void isEmptyTest() {
        //init
       defaultTest();
       //act
       queue.enqueue(1);
       queue.enqueue(2);
       //assert
       Assert.assertFalse(queue.isEmpty());
   }
   
}
