import java.io.File; 
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;

/**
 * ExperimentController - Takes input files and creates indexes of the input file. 
 * User can test using a ListIndex, TreeIndex, and HashIndex
 * User can choose between different input files 
 *
 * @author Claire Liu, Renee Soika, and Yuqi Wang
 * @version November 21, 2020
 */
public class ExperimentController
{
    private String[] dict;
    
    /****************INPUT FILES********************/
    private final static String[] inputFiles = {"Metamorphosis.txt", "ThePrince.txt", "Frankenstein.txt", "GreatExpectations.txt", "DonQuixote.txt","WarAndPeace.txt", "Shakespeare.txt"}; 
    private final static int [] fileSize = {139, 299, 441, 1034, 2335, 3281, 5642}; // File sizes in KB 
    
    /**
     * Main method to start the experiment.
     * @param args  commandline arguements (none)
     */
    public static void main(String[] args) {
        String[] dictionary = readDictionary(new File("English.txt"));
        ExperimentController experiment = new ExperimentController(dictionary); 
        experiment.run(); 
    }
    
    /**
     * Read a dictionary file into an array.
     * @param dictionaryInput   input file for the dictionary
     * @return dictionary array
     */
    public static String[] readDictionary(File dictionaryInput) {
        Scanner scanner = null;
        String[] dict = null;
        
        // Try to get all dictionary words from the input file
        try {
            scanner = new Scanner(dictionaryInput);
            
            int dictionarySize = scanner.nextInt(); 
            dict = new String[dictionarySize];
            
            // Add all words to the dictionary
            for (int i = 0; i < dictionarySize; i++) {
                dict[i] = scanner.next(); 
            }
            
        } catch (Exception err) {
            err.printStackTrace();
        } finally {
            
            // Only close the scanner if it was created
            if (scanner != null) {
                scanner.close(); 
            }
            
        }
        
        return dict; 
    }
    
    /**
     * Creates a new experiment controller to time book indices.
     * @param dictionary     dictionary to use for this experiment
     */
    public ExperimentController(String[] dictionary) {
        dict = dictionary;
    }
    
    /**
     * Runs the experiment on files of different sizes 5 times.
     */
    public void run() {
        int inFileLen = inputFiles.length; 
        File input; 
        long[] averageInsertTimes = new long[5];
        long[] averageWriteTimes = new long[5];
        
        System.out.println("Input file size (KB)\tAverage time to insert (ms)\tAverage time to write to file (ms)\tAverage Total Time (ms)");
        
        // Run experiments for input files of all lengths
        for (int a = 0; a < inFileLen; a++) {
            input = new File(inputFiles[a]);
            System.out.print(fileSize[a] + "\t");
            
            
            // Run multiple trials for every input file
            for (int i = 0; i < 5; i++) {
                /******** CHANGE INDEX TYPE HERE*******/
                //time insert
                Index index = new ListIndex(dict);
                averageInsertTimes[i] = timeToInsert(input, index);
                
                //time writing to the file
                averageWriteTimes[i] = timeToWrite(new File(index.getClass() + ".txt"), index); 
                
                
            }
            
            long avgInsertTime = average(averageInsertTimes); //average time to insert words from input file
            long avgWriteTime = average(averageWriteTimes); //average time to write to output file
            long avgTime = avgInsertTime + avgWriteTime; //add average times for total average time
            System.out.println(avgInsertTime + "\t" + avgWriteTime + "\t" + avgTime); 
        }
        
    }
    
    /**
     * Reads the given input file and inserts words and line numbers into index.
     * @param input     input file for book
     * @param index     index for the book
     */
    private void readAndInsertAll(File input, Index index) {        
        Scanner scanner = null;
        int lineNumber = 1;
        
        // Try to scan the input file
        try {
            scanner = new Scanner(input);
            
            // Read all lines of the file
            while (scanner.hasNextLine()) {
                
                // Using scanner instead of DataInputStream as in the description
                String line = scanner.nextLine();
                
                String[] words = line.split("[^A-Za-z]+"); 
                
                // Insert all words on this line
                for (String word : words) {
                    
                    // Ignore empty strings
                    if (word.length() > 0) {
                        index.insert(word.toLowerCase(), lineNumber);
                    }
                    
                }
                
                lineNumber++; 
            }
            
        } catch (Exception err) {
            err.printStackTrace();
        } finally {
            scanner.close(); 
        }
        
    }
    
    /**
     * Times how long it takes to read and insert words into the index.
     * @param input     input file
     * @param index     index for the book
     * @return time to read/insert (in ms) 
     */
    private long timeToInsert(File input, Index index) {
 
        long startTime = System.currentTimeMillis(); //start timer
        readAndInsertAll(input, index); //call read and Insert method
        long stopTime = System.currentTimeMillis(); //end timer
        
        return stopTime - startTime;
    }
    
    /**
     * Writes the index to an output file.
     * @param output    the output file
     * @param index     index for this book
     */
    private void writeIndexToFile(File output, Index index) {
        PrintWriter writer = null;
        
        // Try to write to the output file
        try {
            writer = new PrintWriter(output);
            
            // Print every entry string in the index
            for (String entry : index) {
                writer.print(entry + "\n");
            }
            
        } catch (Exception err) {
            err.printStackTrace();
        } finally {
            
            // Close the writer if it was created
            if (writer != null) {
                writer.close();
            }
            
        }
        
    }
    
    /**
     * Times how long it takes to write the index to an output file.
     * @param output    the output file
     * @param index     index for this book
     */
    private long timeToWrite(File output, Index index) {
 
        long startTime = System.currentTimeMillis();//start timer
        writeIndexToFile(output, index); //call write to file method
        long stopTime = System.currentTimeMillis();//stop timer
        
        return stopTime - startTime;
    }
    
    /**
     * Computes the rounded average of longs.
     * @param nums  longs to average
     * @return rounded long average
     */
    private static long average(long[] nums) {
        long avg = 0;

        // Sum all values
        for (long num : nums) {
            avg += num;
        }

        return Math.round((double) avg / nums.length);
    }
    
}
