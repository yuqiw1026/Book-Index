# Book-Index
The purpose of this project is to implement a program that creates a book index of any book given in txt format in three ways—using a sorted list, a tree map, and a hash map. The book index lists all the English words that appear in the book and the line numbers where each word appears. 

<h2>Findings</h2>
Between all three types of indices, HashMapIndex had the lowest total runtime, on average. TreeMapIndex was the second-fastest, while ListIndex was the slowest. Even though this order was reversed for writing to a file because HashMapIndex had to be sorted, the time required to insert all words and their line numbers into the index dominated the runtime. In addition, the potential for rehashing and collisions in a hash map did not outweigh its average O(1) lookup time. However, the differences between all three data structures for this purpose were slight, even for large files. The graphs for all three indices' total runtime appeared almost linear, and ListIndex's total time for the largest book we tested did not even exceed three seconds. For a typical book, any of these data structures would be an efficient choice.

In highly specific cases that we did not cover in this project, certain index implementations may be a poor selection. If memory is a major concern, then the O(n) additional storage space required to merge sort the words in HashMapIndex could outweigh its efficient performance time-wise. Books with many extremely long words would also be a poor situation to use a hash map because the time to hash words would approach O(n) instead of O(1). Similarly, books that create many collisions in a hash map would also make HashMapIndex a poor choice, but that would be difficult to predict from the outset. In books with many unique words, ListIndex would be inefficient because it takes O(n) time to insert a newly-encountered word. However, tree maps have guaranteed O(lg n) lookup and insertion times for one word and already store words in alphabetical order. TreeMapIndex would be the best choice when an index being inefficient for a fraction of books or using extra memory would be unacceptable.

Although asymptotic analysis is an effective estimation tool, the leading terms did not tell the full story in this project. Even though we predicted that retrieving and writing an index to a file would take O(n2) time because O(n) words would each have O(n) line numbers, the trendlines appeared linear. Many words would only occur infrequently in a book, so reading line numbers would take closer to O(1) time. Reading line numbers is also only done once for each word in the index, whereas insertion is performed multiple times for many words, so the insertion time dominated the total runtime, even though its leading big-O terms were smaller. In addition, we noted that adding newly-encountered words to ListIndex would take O(n2) time, but the trendline for its insertion time was barely curved and appeared nearly linear; quadratic operations were performed infrequently enough that they did not dramatically affect ListIndex's runtime. Similarly, rehashing and collisions in HashMapIndex were not prevalent enough to prevent it from being the fastest of the three indices.

The book index that we implemented is a good starting point to create a real index for any file. If we were to implement this for real world and practical use, we could improve our book index program by having the program omit commonly used words (like “a”, “the”, “he”, etc. ) and focus on the important words that the user would want to find. This could be done by removing common words from the dictionary. We would need to use a database of common words to remove them from a real dictionary before generating any indices. Omitting the most common words would decrease the number of total words and likely get rid of the words with the largest number of repeats, thus decreasing the write-to-file time and making the book index more useful to the user. Another way to improve the program would be to have the program only print a user specified number of most common words in the book index. We could also let the user specify words to omit in the command line and remove those from the dictionary. Conversely, we could let the user specify words to include in the index in the command line and have the index only be of those words. Furthermore, if we did not want the entries as a string or wanted to distribute the program to the public, we may have had the iterators for each index return instances of a custom Entry class, which would make each word and its line numbers accessible.

Implementing the book index was an excellent way to explore the performances of different data structures like ArrayLists, TreeSets, TreeMaps, and HashMaps. Knowing the pros and cons of each kind of data structure will help us to make better design decisions when there are certain space or time constraints in other real life applications. 


<h2>Running Instructions and Collabotors</h2>
This project was created and should be run in BlueJ

Collaborators: Renee Soika and Claire Liu 
