import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;

/**
 * The test class ListIndexTest.
 *
 * @author  Claire Liu, Renee Soika, Yuqi Wang
 * @version November 29, 2020
 */
public class ListIndexTest
{
    /**
     * Default constructor for test class ListIndexTest
     */
    public ListIndexTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void testSearchDictionaryTrue() {
        String[] testDict = {"apple", "bear", "cat", "dog"}; 
        ListIndex li = new ListIndex(testDict); 
        assertEquals(true, li.searchDictionary("cat")); 
    }
    
    @Test
    public void testSearchDictionaryFalse() {
        String[] testDict = {"apple", "bear", "cat", "dog"}; 
        ListIndex li = new ListIndex(testDict); 
        assertEquals(false, li.searchDictionary("emu")); 
    }
    
    @Test
    public void testIndexInsert() {
        String[] testDict = {"apple", "bear", "cat", "dog"}; 
        ListIndex li = new ListIndex(testDict);
        li.insert("bear", 1);
        li.insert("apple", 2); 
        ListIndex.Entry temp = new ListIndex.Entry("apple", 3); 
        assertEquals(true, li.indexInsert(temp, 0, 3));
        assertEquals("apple [2, 3]bear [1]", li.toString());
    }
    
    @Test
    public void testIndexInsertNewEntry() {
        String[] testDict = {"apple", "bear", "cat", "dog"}; 
        ListIndex li = new ListIndex(testDict);
        li.insert("bear", 1);
        li.insert("apple", 2); 
        ListIndex.Entry temp = new ListIndex.Entry("cat", 5); 
        assertEquals(true, li.indexInsert(temp, -3, 5));
        assertEquals("apple [2]bear [1]cat [5]", li.toString());
    }
    
    @Test
    public void testIndexInsertDuplicateFalse() {
        String[] testDict = {"apple", "bear", "cat", "dog"}; 
        ListIndex li = new ListIndex(testDict);
        li.insert("bear", 1);
        li.insert("apple", 2); 
        assertEquals(false, li.insert("bear", 1));
        assertEquals("apple [2]bear [1]", li.toString());
    }
    @Test
    public void testInsertOne() {
        String[] testDict = {"apple", "bear", "cat", "dog"}; 
        ListIndex li = new ListIndex(testDict);
        assertEquals(true, li.insert("bear", 1)); 
        assertEquals("bear [1]", li.toString()); 
    }
    
    @Test
    public void testInsertMore() {
        String[] testDict = {"apple", "bear", "cat", "dog"}; 
        ListIndex li = new ListIndex(testDict);
        assertEquals(true, li.insert("bear", 1));
        assertEquals(true, li.insert("dog", 1)); 
        assertEquals(true, li.insert("apple", 2)); 
        assertEquals("apple [2]bear [1]dog [1]", li.toString()); 
    }
    
    @Test
    public void testInsertMultiLine() {
        String[] testDict = {"apple", "bear", "cat", "dog"}; 
        ListIndex li = new ListIndex(testDict);
        assertEquals(true, li.insert("bear", 1));
        assertEquals(true, li.insert("dog", 1)); 
        assertEquals(true, li.insert("dog", 2)); 
        assertEquals("bear [1]dog [1, 2]", li.toString()); 
    }
    
    @Test
    public void testInsertMultiLineDuplicates() {
        String[] testDict = {"apple", "bear", "cat", "dog"}; 
        ListIndex li = new ListIndex(testDict);
        assertEquals(true, li.insert("bear", 1));
        assertEquals(true, li.insert("bear", 2));
        assertEquals(true, li.insert("dog", 1)); 
        assertEquals(true, li.insert("dog", 2)); 
        assertEquals("bear [1, 2]dog [1, 2]", li.toString()); 
    }
    
    @Test 
    public void testInsertTrue() {
        String[] testDict = {"apple", "bear", "cat", "dog"}; 
        ListIndex li = new ListIndex(testDict);
        assertEquals(true, li.insert("cat", 11)); 
    }
    
    @Test 
    public void testInsertFalseDict() {
        String[] testDict = {"apple", "bear", "cat", "dog"}; 
        ListIndex li = new ListIndex(testDict);
        assertEquals(false, li.insert("emu", 11)); 
    }
    
    @Test
    public void testInsertFalseDuplicate() {
        String[] testDict = {"apple", "bear", "cat", "dog"}; 
        ListIndex li = new ListIndex(testDict);
        li.insert("bear", 1);
        li.insert("bear", 2);
        li.insert("dog", 1); 
        li.insert("dog", 2); 
        assertEquals(false, li.insert("dog", 2)); 
    }
    
    @Test
    public void testIteratorEmpty() {
        String[] testDict = {"apple", "bear", "cat", "dog"};
        ListIndex li = new ListIndex(testDict);
        Iterator<String> itr = li.iterator();
        
        assertEquals(false, itr.hasNext());
    }
    
    @Test
    public void testIteratorOneLine() {
        String[] testDict = {"apple", "bear", "cat", "dog", "llama", "moose"};
        ListIndex li = new ListIndex(testDict);
        assertEquals(true, li.insert("bear", 1));
        assertEquals(true, li.insert("dog", 1));
        assertEquals(true, li.insert("apple", 5));
        assertEquals(true, li.insert("moose", 3));
        assertEquals(true, li.insert("llama", 2));
        Iterator<String> itr = li.iterator();
        
        assertEquals(true, itr.hasNext());
        assertEquals("apple [5]", itr.next());
        assertEquals(true, itr.hasNext());
        assertEquals("bear [1]", itr.next());
        assertEquals(true, itr.hasNext());
        assertEquals("dog [1]", itr.next());
        assertEquals(true, itr.hasNext());
        assertEquals("llama [2]", itr.next());
        assertEquals(true, itr.hasNext());
        assertEquals("moose [3]", itr.next());
        assertEquals(false, itr.hasNext());
    }
    
    @Test
    public void testIteratorMultiLine() {
        String[] testDict = {"apple", "bear", "cat", "dog", "llama", "moose"};
        ListIndex li = new ListIndex(testDict);
        assertEquals(true, li.insert("bear", 1));
        assertEquals(true, li.insert("moose", 6));
        assertEquals(true, li.insert("dog", 1));
        assertEquals(true, li.insert("apple", 5));
        assertEquals(true, li.insert("moose", 3));
        assertEquals(true, li.insert("dog", 3));
        assertEquals(true, li.insert("llama", 2));
        assertEquals(true, li.insert("llama", 9));
        Iterator<String> itr = li.iterator();
        
        assertEquals(true, itr.hasNext());
        assertEquals("apple [5]", itr.next());
        assertEquals(true, itr.hasNext());
        assertEquals("bear [1]", itr.next());
        assertEquals(true, itr.hasNext());
        assertEquals("dog [1, 3]", itr.next());
        assertEquals(true, itr.hasNext());
        assertEquals("llama [2, 9]", itr.next());
        assertEquals(true, itr.hasNext());
        assertEquals("moose [3, 6]", itr.next());
        assertEquals(false, itr.hasNext());
    }
}
