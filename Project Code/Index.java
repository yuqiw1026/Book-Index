/**
 * Interface for book indices.
 *
 * @author Claire Liu, Renee Soika, and Yuqi Wang
 * @version November 22, 2020
 */
public interface Index extends Iterable<String>
{
    /**
     * Insert an instance of a word to the index
     * 
     * @param  s  the string of the word
     * @param  lineNumber  the line number of where the word was in the input file
     * 
     * @return  true if insertion was successful, false if the insertion was not successful (the word is not in the English dictionary)
     * 
     */
    public boolean insert(String s, Integer lineNumber);
}
