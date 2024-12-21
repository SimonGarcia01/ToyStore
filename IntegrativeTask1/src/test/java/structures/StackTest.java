package structures;
import exceptions.*;
import org.junit.Assert;
import org.junit.Test;

public class StackTest {
    private IStack<Integer> stack;
    public void defaultSet(){
        //init
        stack = new Stack<>();

    }
    @Test
    public void pushTest() throws StackException {

        defaultSet();
        //act
        stack.push(1);
        stack.push(2);
        stack.push(3);

        int value= stack.top();
        //assert
        Assert.assertEquals(3, value);
    }
    @Test
    public void maxPushTest() throws StackException {
        //init
        defaultSet();
        //act
        for(int i=0;i<999;i++){
            stack.push(4+i);
        }
        stack.push(777);
        int value= stack.top();
        //assert
        Assert.assertEquals(777, value);

    }
    @Test
    public void pushNullTest() throws StackException {
        //init
        defaultSet();
        //act
        stack.push(null);
        //assert
        Assert.assertNull(stack.top());
    }
    @Test
    public void popTest() throws StackException {
        //init
        defaultSet();
        //act
        stack.push(-1);
        stack.push(2);
        stack.pop();
       int value= stack.top();
        //assert
        Assert.assertEquals(-1,value);
    }
    @Test
    public void maxPopTest() throws StackException {
        //init
        defaultSet();
        //act
        for(int i=0;i<100;i++){
            stack.push(i);
        }
        for(int i=0;i<99;i++){
            stack.pop();
        }
        int value= stack.top();
        //assert
        Assert.assertEquals(0,value);
    }
    @Test
    public void manyPopTest() throws StackException {
        //init
        String message="";
        defaultSet();
        //act
        try{
            stack.push(-1);
            stack.push(2);
            stack.push(2450000);
            stack.pop();
            stack.pop();
            stack.pop();
            stack.pop();
        }catch (StackException e){
            message=e.getMessage();
        }
        //assert
        Assert.assertEquals("The Stack is empty.",message);

    }
    @Test
    public void topTest() throws StackException {
        //init
        defaultSet();
        //act
        stack.push(-1);
        stack.push(2);
        stack.push(2450000);
        stack.top();
        stack.pop();
        stack.pop();
        int value=stack.top();
        //assert
        Assert.assertEquals(-1,value);
    }
    @Test
    public void nullTopTest() throws StackException {
        //init
        String message="";
        defaultSet();
        //act
        try{
            stack.push(-1);
            stack.pop();
            stack.top();
        }catch (StackException e){
            message=e.getMessage();
        }
        //assert
        Assert.assertEquals("Nothing is saved in the stack.",message);
    }
    @Test
    public void emptyTopTest() throws StackException {
        //init
        defaultSet();
        //act
        stack.push(-1);
        stack.push(5);
        int value= stack.top();
        Assert.assertEquals(5,value);
        stack.pop();
        int value2= stack.top();
        Assert.assertEquals(-1,value2);
    }
}
