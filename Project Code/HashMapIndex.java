import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * A book index based on a HashMap.
 *
 * @author Claire Liu, Renee Soika, Yuqi Wang
 * @version November 23, 2020
 */
public class HashMapIndex extends MapIndex
{
    
    /**
     * Creates a new book index based on a HashMap.
     * @param dictionary    dictionary of words that are allowed in the index
     */
    public HashMapIndex(String[] dictionary) {
        super(new HashMap(), dictionary);
    }
    
    /**
     * Gets the iterator for keys in this index.
     * @return the iterator for all keys in this index
     */
    @Override
    protected Iterator<String> wordIterator() {
        Set<String> wordSet = wordSet();
        
        // There is no method to directly convert a set to a list
        String[] wordArr = wordSet.toArray(new String[wordSet.size()]);
        
        // HashMap keys are not sorted
        Arrays.sort(wordArr);
        
        // A list is needed to make the array iterable
        List<String> wordList = Arrays.asList(wordArr);
        
        return wordList.iterator();
    }
    
}
