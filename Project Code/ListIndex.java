import java.util.ArrayList;
import java.util.Arrays; 
import java.util.Collections; 
import java.util.Iterator;
import java.util.TreeSet;

/**
 * ListIndex creates an index using ArryList and Entry objects.
 *
 * @author Claire Liu, Renee Soika, Yuqi Wang
 * @version November 22, 2020
 */
public class ListIndex implements Index
{
    //instance variables
    private ArrayList<Entry> arr; 
    private String[] dict; 

    /**
     * Constructor. Creates a new ListIndex.
     * @param  dictionary   an array of Strings of words in a dictionary
     */
    public ListIndex(String[] dictionary){
        arr = new ArrayList<Entry>(); 
        dict = dictionary; 
    }
    
    /**
     * Insert an instance of a word to the index
     * @param  s            the string of the word
     * @param  lineNumber   the line number of where the word was in the input file
     * @return  true if insertion was successful, 
     *          false if the insertion was not successful 
     *          (the word is not in the English dictionary, it is a duplicate)
     */
    public boolean insert(String s, Integer lineNumber){
        
        // Search dictionary
        boolean dictSearchResult = searchDictionary(s);
        if (!dictSearchResult) {
            return false;
        }
        
        // Create a temp entry
        Entry tempEntry = new Entry(s, lineNumber); 

        // Search for Entry in index 
        int indexSearchResult = Collections.binarySearch(arr, tempEntry); 
        
        return indexInsert(tempEntry, indexSearchResult, lineNumber); 
    }
    
    /**
     * Gets an iterator for this index.
     * @return an iterator for this index
     */
    @Override
    public Iterator<String> iterator() {
        return new ListIndexStrIterator();
    }
    
    /**
     * Search the dictionary for a word s
     * @param  word  word to search for
     * @return true if word is in dictionary, false if it is not
     */
    boolean searchDictionary(String word){
        int dictSearchResult = Arrays.binarySearch(dict, word); 
        
        // The word is only present if the index is positive or zero
        return dictSearchResult >= 0; 
    }
    
    /**
     * Insert an entry into the index.
     * @param  e            Entry to insert
     * @param  pos          insertion position returned by the binarySearch
     * @param  lineNumber   the line number of the string in Entry
     * @return true if the insertion was successful
     *         false if the entry was a duplicate
     */
    boolean indexInsert(Entry e, int pos, int lineNumber) {
        
        // If Entry is in the index, just add the line number
        if (pos >= 0) {
            return arr.get(pos).addLine(lineNumber); 
        } else {
            
            // Entry is not in the index so add the new entry to the right position in the index
            int rightPos = -(pos + 1); 
            arr.add(rightPos, e); 
            return true;
        }
        
    }
    
    
    /**
     * Creates a string representation of ListIndex (for unit testing only)
     * @return  string representation of ListIndex. 
     */
    @Override
    public String toString(){
        String string = ""; 
        int arrSize = arr.size(); 
        //for each entry in the ArrayList, add the string representation of the entry to string variable.
        for(int i = 0; i < arrSize; i++){
            string += arr.get(i).toString(); 
        }
        return string; 
    }
    
    /**
     * An entry in an array book index.
     *
     * @author Claire Liu, Renee Soika, Yuqi Wang
     * @version November 22, 2020
     */
    static class Entry implements Comparable<Entry>
    {
        //instance variables
        private String word; 
        private TreeSet<Integer> numLine; 
    
        /**
         * Constructor. Creates a new entry in this list index.
         * @param w     word for this entry
         * @param k     first line number for this word
         */
        public Entry(String w, Integer k) { 
            word = w; 
            numLine = new TreeSet<Integer>(); 
            numLine.add(k); 
        }
        
        /**
         * Adds a line number to this entry.
         * @param k     line number to add
         * @return whether this line number was added (true if not a duplicate)
         */
        public boolean addLine(Integer k) {
            return numLine.add(k);
        }
        
        /**
         * Compares this entry to another alphabetically.
         * @param x     the other entry to compare this entry to
         * @return  integer > 0 if this entry's word comes after the other
         *          integer < 0 if this entry's word comes before the other
         *          0 if the two words are the same
         */
        @Override
        public int compareTo(Entry x) {
            return word.compareTo(x.word); 
        }
        
        /**
         * Converts this entry to a string.
         * @return the word and line numbers for this entry as a string
         */
        @Override
        public String toString() {
            return word + " " + numLine.toString();
        }
        
    }
    
    /**
     * Iterates over this set with keys and entries as strings.
     * @author Claire Liu, Renee Soika, Yuqi Wang
     * @version November 22, 2020
     */
    private class ListIndexStrIterator implements Iterator<String> {
        private Iterator<Entry> arrIterator;
        
        /**
         * Constructor. Creates a new iterator.
         */
        public ListIndexStrIterator() {
            arrIterator = arr.iterator();
        }
        
        /**
         * Determines if there is another word and line number list 
         * in this iterator.
         * @return whether the iterator has another string
         */
        @Override
        public boolean hasNext() {
            return arrIterator.hasNext();
        }
        
        /**
         * Gets the next word and line number list from this iterator.
         * @return the next word and line number list concatenated
         */
        @Override
        public String next() {
            return arrIterator.next().toString();
        }
        
    }
}
