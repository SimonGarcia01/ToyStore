package structures;
import exceptions.HashTableException;
import org.junit.Test;
import org.junit.Assert;
public class HashTableTest {
    private HashTable<String,Integer> hashTable;
    public void defaultTest(){
        hashTable = new HashTable<>();
    }
    @Test
    public void addTest() throws HashTableException {
        //init
        defaultTest();
        //act
        hashTable.add("xry",5);
        int value=hashTable.search("xry");
        //assert
        Assert.assertEquals(value,5);
    }
    @Test
    public void maxAddTest() throws HashTableException {
        //init
        defaultTest();
        //act
        String message="";
        try{
            hashTable.add("xry",0);
            hashTable.add("rxy",0);
            hashTable.add("xsx",0);
            hashTable.add("rxy",0);
        }catch (HashTableException e){
            message = e.getMessage();
        }

        //assert
        Assert.assertEquals("The key rxy already exists.",message);
    }
    @Test
    public void removeTest() throws HashTableException {
        //init
        defaultTest();
        //act
        hashTable.add("xry",5);
        hashTable.add("rxy",5);
        hashTable.add("xsx",5);
        hashTable.delete("xry");
        Integer value=hashTable.search("xry");
        //assert
        Assert.assertEquals(null, value);
    }
    @Test
    public void removeNullTest() throws HashTableException {
        //init
        defaultTest();
        //act
        String message="";
        try {
            hashTable.add("xry",5);
            hashTable.delete("xry");
            hashTable.delete("xry");
        }catch (HashTableException e){
            message = e.getMessage();
        }
        //assert
        Assert.assertEquals("The key " + "xry" + " does not exist.",message);
    }
    @Test
    public void removeAllTest() throws HashTableException {
        //init
        defaultTest();
        //act
        hashTable.add("xry",5);
        hashTable.add("rxy",5);
        hashTable.add("xsx",5);
        hashTable.add("rxz",5);
        hashTable.delete("xry");
        hashTable.delete("rxy");
        hashTable.delete("xsx");
        hashTable.delete("rxz");
        Integer value=hashTable.search("xsx");
        Assert.assertEquals(null, value);
    }
    @Test
    public void searchTest() throws HashTableException {
        //init
        defaultTest();
        //act
        hashTable.add("xry",5);
        int value=hashTable.search("xry");
        //assert
        Assert.assertEquals(5,value);
    }
    @Test
    public void searchNullTest() throws HashTableException {
        //init
        defaultTest();
        //act
        hashTable.add("xry",5);
        hashTable.delete("xry");
        Integer value=hashTable.search("xry");
        Assert.assertEquals(null,value);

    }
    @Test
    public void searchTest2() throws HashTableException {
        //init
        defaultTest();
        //act
        hashTable.add("xry",5);
        hashTable.add("rxy",7);
        hashTable.add("xsx",5);
        int value=hashTable.search("rxy");
        //assert
        Assert.assertEquals(7,value);
    }
    @Test
    public void getValueTest() throws HashTableException {
        //init
        defaultTest();
        //act
        hashTable.add("xry",5);
        hashTable.add("rxy",7);
        hashTable.add("xsx",5);
        hashTable.add("zzz",7);
        hashTable.getValues();
        int value=hashTable.search("zzz");
        //assert
       Assert.assertEquals(7,value);
    }
    @Test
    public void allGetValueTest() throws HashTableException {
        //init
        defaultTest();
        //act
        int i=0;
        while(i<20){
            hashTable.add("xry"+i,i);
            i++;
        }
        hashTable.getValues();
        int value=hashTable.search("xry0");
        //assert
        Assert.assertEquals(value,0);
    }
    @Test
    public void getNullValueTest() throws HashTableException {
        //init
        defaultTest();
        //act
        hashTable.add("xry",5);
        hashTable.delete("xry");
        Integer value=hashTable.search("xry");
        //assert
        Assert.assertEquals(value,null);
    }

}
