import java.util.Iterator;
import java.util.TreeMap;

/**
 * A book index based on a TreeMap.
 *
 * @author Claire Liu, Renee Soika, Yuqi Wang
 * @version November 23, 2020
 */
public class TreeMapIndex extends MapIndex
{
    
    /**
     * Creates a new book index based on a TreeMap.
     * @param dictionary    dictionary of words that are allowed in the index
     */
    public TreeMapIndex(String[] dictionary) {
        super(new TreeMap(), dictionary);
    }
    
    /**
     * Gets the iterator for keys in this index.
     * @return the iterator for all keys in this index
     */
    @Override
    protected Iterator<String> wordIterator() {
        return wordSet().iterator();
    }
    
}
