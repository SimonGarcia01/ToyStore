package structures;
import org.junit.Test;
import org.junit.Assert;
import exceptions.*;

public class QueueTest {
    private IQueue<Integer> queue;
    public void defaultSet(){
        queue = new Queue<>();
    }
    @Test
    public void enqueueTest() throws QueueException {
        //init
        defaultSet();
        //act
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        int value= queue.front();
        //assert
        Assert.assertEquals(1,value);
    }
    @Test
    public void maxEnqueueTest() throws QueueException {
        int i=1;
        //init
        defaultSet();
        //act
        while(i<=100){
            queue.enqueue(i);
            i++;
        }
        int value= queue.front();
        //assert
        Assert.assertEquals(1,value);
    }
    @Test
    public void nullEnqueueTest() throws QueueException {
        //init
        defaultSet();
        //act
        queue.enqueue(null);
        //assert
        Assert.assertNull(queue.front());
    }
    @Test
    public void dequeueTest() throws QueueException {
        //init
        defaultSet();
        //act
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        int value=queue.dequeue();
        Assert.assertEquals(1,value);
    }
    @Test
    public void dequeueNullTest() throws QueueException {
        //init
        defaultSet();
        //act
        String message="";
        queue.enqueue(1);
        queue.dequeue();
        int value=queue.dequeue();

        //assert
        Assert.assertEquals(1,value);
    }
    @Test
    public void dequeueEmptyTest() throws QueueException {
        // init
        defaultSet();
        //act
        queue.enqueue(null);
        queue.dequeue();
        //assert
        Assert.assertNull(queue.front());

    }
    @Test
    public void isEmptyTest() throws QueueException {
        //init
        defaultSet();
        //act
        //assert
        Assert.assertTrue(queue.isEmpty());
    }
    @Test
    public void notEmptyTest() throws QueueException {
        //init
        defaultSet();
        //act
        queue.enqueue(1);
        //assert
        Assert.assertFalse(queue.isEmpty());
    }
    @Test
    public void multipleTest() throws QueueException {
        //init
        defaultSet();
        //act
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        int value=queue.dequeue()+queue.dequeue()+queue.dequeue()+queue.dequeue();
        //assert
        Assert.assertEquals(10,value);

    }
}
