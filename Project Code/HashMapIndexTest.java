import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;

/**
 * The test class HashMapIndexTest.
 *
 * @author  Claire Liu, Renee Soika, Yuqi Wang
 * @version November 24, 2020
 */
public class HashMapIndexTest
{
    /**
     * Default constructor for test class HashMapIndexTest
     */
    public HashMapIndexTest()
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
        HashMapIndex hmi = new HashMapIndex(testDict); 
        assertEquals(true, hmi.searchDictionary("cat")); 
    }
    
    @Test
    public void testSearchDictionaryFalse() {
        String[] testDict = {"apple", "bear", "cat", "dog"}; 
        HashMapIndex hmi = new HashMapIndex(testDict); 
        assertEquals(false, hmi.searchDictionary("emu")); 
    }
    
    @Test
    public void testInsertOne() {
        String[] testDict = {"apple", "bear", "cat", "dog"}; 
        HashMapIndex hmi = new HashMapIndex(testDict);
        assertEquals(true, hmi.insert("bear", 1)); 
        assertEquals("bear [1]", hmi.toString()); 
    }
    
    @Test
    public void testInsertMore() {
        String[] testDict = {"apple", "bear", "cat", "dog"}; 
        HashMapIndex hmi = new HashMapIndex(testDict);
        assertEquals(true, hmi.insert("bear", 1));
        assertEquals(true, hmi.insert("dog", 1)); 
        assertEquals(true, hmi.insert("apple", 2)); 
        assertEquals("apple [2]bear [1]dog [1]", hmi.toString()); 
    }
    
    @Test
    public void testInsertMultiLine() {
        String[] testDict = {"apple", "bear", "cat", "dog"}; 
        HashMapIndex hmi = new HashMapIndex(testDict);
        assertEquals(true, hmi.insert("bear", 1));
        assertEquals(true, hmi.insert("dog", 1)); 
        assertEquals(true, hmi.insert("dog", 2)); 
        assertEquals("bear [1]dog [1, 2]", hmi.toString()); 
    }
    
    @Test
    public void testInsertMultiLineDuplicate() {
        String[] testDict = {"apple", "bear", "cat", "dog"}; 
        HashMapIndex hmi = new HashMapIndex(testDict);
        assertEquals(true, hmi.insert("bear", 1));
        assertEquals(true, hmi.insert("dog", 1)); 
        assertEquals(true, hmi.insert("dog", 2)); 
        assertEquals(false, hmi.insert("bear", 1));
        assertEquals("bear [1]dog [1, 2]", hmi.toString()); 
    }
    
    @Test 
    public void testInsertInDictionary() {
        String[] testDict = {"apple", "bear", "cat", "dog"}; 
        HashMapIndex hmi = new HashMapIndex(testDict);
        assertEquals(true, hmi.insert("cat", 11)); 
    }
    
    @Test 
    public void testInsertNotInDictionary() {
        String[] testDict = {"apple", "bear", "cat", "dog"}; 
        HashMapIndex hmi = new HashMapIndex(testDict);
        assertEquals(false, hmi.insert("emu", 11)); 
    }
    
    @Test
    public void testWordSetEmpty() {
        String[] testDict = {"apple", "bear", "cat", "dog"};
        HashMapIndex hmi = new HashMapIndex(testDict);
        assertEquals(0, hmi.wordSet().size());
    }
    
    @Test
    public void testWordSetOneLine() {
        String[] testDict = {"apple", "bear", "cat", "dog"};
        HashMapIndex hmi = new HashMapIndex(testDict);
        assertEquals(true, hmi.insert("bear", 1));
        assertEquals(true, hmi.insert("dog", 1));
        assertEquals(2, hmi.wordSet().size());
        assertEquals(true, hmi.wordSet().contains("bear"));
        assertEquals(true, hmi.wordSet().contains("dog"));
    }
    
    @Test
    public void testWordSetMultiLines() {
        String[] testDict = {"apple", "bear", "cat", "dog"};
        HashMapIndex hmi = new HashMapIndex(testDict);
        assertEquals(true, hmi.insert("bear", 1));
        assertEquals(true, hmi.insert("dog", 1));
        assertEquals(true, hmi.insert("dog", 2));
        assertEquals(2, hmi.wordSet().size());
        assertEquals(true, hmi.wordSet().contains("bear"));
        assertEquals(true, hmi.wordSet().contains("dog"));
    }
    
    @Test
    public void testWordIteratorEmpty() {
        String[] testDict = {"apple", "bear", "cat", "dog"};
        HashMapIndex hmi = new HashMapIndex(testDict);
        Iterator<String> itr = hmi.wordIterator();
        
        assertEquals(false, itr.hasNext());
    }
    
    @Test
    public void testWordIteratorOneLine() {
        String[] testDict = {"apple", "bear", "cat", "dog", "llama", "moose"};
        HashMapIndex hmi = new HashMapIndex(testDict);
        assertEquals(true, hmi.insert("bear", 1));
        assertEquals(true, hmi.insert("dog", 1));
        assertEquals(true, hmi.insert("apple", 5));
        assertEquals(true, hmi.insert("moose", 3));
        assertEquals(true, hmi.insert("llama", 2));
        Iterator<String> itr = hmi.wordIterator();
        
        assertEquals(true, itr.hasNext());
        assertEquals("apple", itr.next());
        assertEquals(true, itr.hasNext());
        assertEquals("bear", itr.next());
        assertEquals(true, itr.hasNext());
        assertEquals("dog", itr.next());
        assertEquals(true, itr.hasNext());
        assertEquals("llama", itr.next());
        assertEquals(true, itr.hasNext());
        assertEquals("moose", itr.next());
        assertEquals(false, itr.hasNext());
    }
    
    @Test
    public void testWordIteratorMultiLine() {
        String[] testDict = {"apple", "bear", "cat", "dog", "llama", "moose"};
        HashMapIndex hmi = new HashMapIndex(testDict);
        assertEquals(true, hmi.insert("bear", 1));
        assertEquals(true, hmi.insert("moose", 6));
        assertEquals(true, hmi.insert("dog", 1));
        assertEquals(true, hmi.insert("apple", 5));
        assertEquals(true, hmi.insert("moose", 3));
        assertEquals(true, hmi.insert("dog", 3));
        assertEquals(true, hmi.insert("llama", 2));
        assertEquals(true, hmi.insert("llama", 9));
        Iterator<String> itr = hmi.wordIterator();
        
        assertEquals(true, itr.hasNext());
        assertEquals("apple", itr.next());
        assertEquals(true, itr.hasNext());
        assertEquals("bear", itr.next());
        assertEquals(true, itr.hasNext());
        assertEquals("dog", itr.next());
        assertEquals(true, itr.hasNext());
        assertEquals("llama", itr.next());
        assertEquals(true, itr.hasNext());
        assertEquals("moose", itr.next());
        assertEquals(false, itr.hasNext());
    }
    
    @Test
    public void testIteratorEmpty() {
        String[] testDict = {"apple", "bear", "cat", "dog"};
        HashMapIndex hmi = new HashMapIndex(testDict);
        Iterator<String> itr = hmi.iterator();
        
        assertEquals(false, itr.hasNext());
    }
    
    @Test
    public void testIteratorOneLine() {
        String[] testDict = {"apple", "bear", "cat", "dog", "llama", "moose"};
        HashMapIndex hmi = new HashMapIndex(testDict);
        assertEquals(true, hmi.insert("bear", 1));
        assertEquals(true, hmi.insert("dog", 1));
        assertEquals(true, hmi.insert("apple", 5));
        assertEquals(true, hmi.insert("moose", 3));
        assertEquals(true, hmi.insert("llama", 2));
        Iterator<String> itr = hmi.iterator();
        
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
        HashMapIndex hmi = new HashMapIndex(testDict);
        assertEquals(true, hmi.insert("bear", 1));
        assertEquals(true, hmi.insert("moose", 6));
        assertEquals(true, hmi.insert("dog", 1));
        assertEquals(true, hmi.insert("apple", 5));
        assertEquals(true, hmi.insert("moose", 3));
        assertEquals(true, hmi.insert("dog", 3));
        assertEquals(true, hmi.insert("llama", 2));
        assertEquals(true, hmi.insert("llama", 9));
        Iterator<String> itr = hmi.iterator();
        
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
