import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;

/**
 * The test class TreeMapIndexTest.
 *
 * @author  Claire Liu, Renee Soika, Yuqi Wang
 * @version November 29, 2020
 */
public class TreeMapIndexTest
{
    /**
     * Default constructor for test class TreeMapIndexTest
     */
    public TreeMapIndexTest()
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
    public void testinsertTrue() {
        String[] dic = {"ab", "c", "def", "ghi"};
        TreeMapIndex tm = new TreeMapIndex(dic);
        
        assertEquals(true, tm.insert("c", 2)); 
    }

    @Test
    public void testInsertFalse() {
        String[] dic = {"ab", "c", "def", "ghi"};
        TreeMapIndex tm = new TreeMapIndex(dic);
        assertEquals(false, tm.insert("f", 2));   
    }

    @Test
    public void testSearchDictTrue() {
        String[] dic = {"ab", "c", "def", "ghi"};
        TreeMapIndex tm = new TreeMapIndex(dic);
        assertEquals(true, tm.searchDictionary("c"));
    }

    @Test
    public void testSearchDictFalse() {
        String[] dic = {"ab", "c", "def", "ghi"};
        TreeMapIndex tm = new TreeMapIndex(dic);
        assertEquals(false, tm.searchDictionary("abc"));
    }

    @Test
    public void testInsertOne() {
        String[] dic = {"ab", "c", "def", "ghi"};
        TreeMapIndex tm = new TreeMapIndex(dic);
        tm.insert("def",1);
        assertEquals("def [1]",tm.toString());

    }

    @Test
    public void testInsertMore() {
        String[] dic = {"ab", "c", "def", "ghi"};
        TreeMapIndex tm = new TreeMapIndex(dic);
        tm.insert("def",1);
        tm.insert("ghi",2);
        tm.insert("c",2);

        assertEquals("c [2]def [1]ghi [2]",tm.toString());
    }

    @Test
    public void testInsertMultiline() {
        String[] dic = {"ab", "c", "def", "ghi"};
        TreeMapIndex tm = new TreeMapIndex(dic);
        tm.insert("ghi", 1);
        tm.insert("ghi", 2);
        assertEquals("ghi [1, 2]",tm.toString());
    }
    
    @Test
    public void testInsertMultiLineDuplicate() {
        String[] dic = {"ab", "c", "def", "ghi"};
        TreeMapIndex tm = new TreeMapIndex(dic);
        assertEquals(true, tm.insert("ab", 1));
        assertEquals(true, tm.insert("c", 1)); 
        assertEquals(true, tm.insert("ab", 2)); 
        assertEquals(false, tm.insert("c", 1));
        assertEquals("ab [1, 2]c [1]", tm.toString()); 
    }
    
    @Test
    public void testWordSetOneLine() {
        String[] dic = {"ab", "c", "def", "ghi"};
        TreeMapIndex tm = new TreeMapIndex(dic);
        assertEquals(true, tm.insert("ab", 1));
        assertEquals(true, tm.insert("ghi", 1));
        assertEquals(2, tm.wordSet().size());
        assertEquals(true, tm.wordSet().contains("ab"));
        assertEquals(true, tm.wordSet().contains("ghi"));
    }
    
    @Test
    public void testWordSetMultiLine() {
        String[] dic = {"ab", "c", "def", "ghi"};
        TreeMapIndex tm = new TreeMapIndex(dic);
        assertEquals(true, tm.insert("ab", 1));
        assertEquals(true, tm.insert("ghi", 1));
        assertEquals(true, tm.insert("ghi", 2));
        assertEquals(2, tm.wordSet().size());
        assertEquals(true, tm.wordSet().contains("ab"));
        assertEquals(true, tm.wordSet().contains("ghi"));
    }
    
    @Test
    public void testWordIteratorEmpty() {
        String[] dic = {"ab", "c", "def", "ghi"};
        TreeMapIndex tm = new TreeMapIndex(dic);
        Iterator<String> itr = tm.wordIterator();
        
        assertEquals(false, itr.hasNext());
    }
    
    @Test
    public void testWordIteratorOneLine() {
        String[] dic = {"ab","c", "def", "ghi"};
        TreeMapIndex tm = new TreeMapIndex(dic);
        assertEquals(true, tm.insert("ab", 1));
        assertEquals(true, tm.insert("c", 2));
        assertEquals(true, tm.insert("def", 5));
        assertEquals(true, tm.insert("ghi", 3));
        
        Iterator<String> itr = tm.wordIterator();
        
        assertEquals(true, itr.hasNext());
        assertEquals("ab", itr.next());
        assertEquals(true, itr.hasNext());
        assertEquals("c", itr.next());
        assertEquals(true, itr.hasNext());
        assertEquals("def", itr.next());
        assertEquals(true, itr.hasNext());
        assertEquals("ghi", itr.next());
        assertEquals(false, itr.hasNext());
    }
    
}
