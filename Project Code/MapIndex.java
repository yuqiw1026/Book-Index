import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * A base class for book indices based on maps.
 * @author Claire Liu, Renee Soika, Yuqi Wang
 * @version November 23, 2020
 */
public abstract class MapIndex implements Index
{
    private Map<String, TreeSet<Integer>> map;
    private String[] dict;
    
    /**
     * Creates a new book index based on a map.
     * @param wordMap       map to use to store the index
     * @param dictionary    dictionary of all allowed words in the index
     */
    public MapIndex(Map wordMap, String[] dictionary) {
        map = wordMap;
        dict = dictionary;
    }
    
    /**
     * Inserts a new line number for a word into this book index.
     * @param word      word to insert a line number for
     * @param lineNum   line number where word was encountered
     * @return whether the line number was added to the index for the word
     */
    @Override
    public boolean insert(String word, Integer lineNum) {
        
        // Only put recognized words in the index
        if (!searchDictionary(word)) {
            return false;
        }
        
        // Add a new set if this is the first line the word appears at
        if (!map.containsKey(word)) {
            map.put(word, new TreeSet<Integer>());
        }
        
        return map.get(word).add(lineNum);
    }
    
    /**
     * Gets an iterator for this index.
     * @return an iterator for this index
     */
    @Override
    public Iterator<String> iterator() {
        return new MapIndexStrIterator();
    }
    
    /**
     * Creates a string representation of this MapIndex (for unit testing only).
     * @return  string representation of this MapIndex
     */
    @Override
    public String toString() {
        String string = ""; 
        Iterator<String> itr = iterator();
        
        // Add all entries to the string
        while (itr.hasNext()) {
            string += itr.next();
        }
        
        return string; 
    }
    
    /**
     * Gets the set of the words in this index.
     * @return the set of words in this index
     */
    protected Set<String> wordSet() {
        return map.keySet();
    }
        
    /**
     * Gets the iterator for keys in this index.
     * @return the iterator for all keys in this index
     */
    protected abstract Iterator<String> wordIterator();
    
    /**
     * Searches the dictionary for a word.
     * @return true if the word is in the dictionary, false if not
     */
    boolean searchDictionary(String word) {

        // The word is in the array if binary search returns a valid index
        return Arrays.binarySearch(dict, word) >= 0;
        
    }
    
    /**
     * Iterates over this set with keys and entries as strings.
     */
    private class MapIndexStrIterator implements Iterator<String> {
        private Iterator<String> wordIterator;
        
        /**
         * Creates a new iterator.
         */
        public MapIndexStrIterator() {
            wordIterator = wordIterator();
        }
        
        /**
         * Determines if there is another word and line number list 
         * in this iterator.
         * @return whether the iterator has another string
         */
        @Override
        public boolean hasNext() {
            return wordIterator.hasNext();
        }
        
        /**
         * Gets the next word and line number list from this iterator.
         * @return the next word and line number list concatenated
         */
        @Override
        public String next() {
            String nextKey = wordIterator.next();
            String lines = map.get(nextKey).toString();
            
            return nextKey + " " + lines;
        }
        
    }
}
