This Project is about the implementation of Document at a Time and Term at a Time method of  Information Retrieval for searching.
In this Project first of all Inverted Indexing is done using HashMap,LinkedList collection of Java.Inverted Indexing is done using PubMed Dataset.

CreateIndex.java 
  ---> createPostings method creates the posting list of all the terms in a document and store it in a HashMap with key as term and value as a LinkedList of document IDs
  ---> getPostingsList returns the Psoting list of given term.

Term at a Time OR method is implemented using traversing the whole linkedlist of all given terms.
Term at a Time AND method is implemented using skip pointers.

BooleanSearch.java 
 --> getTaator
 --> computeTaator
 --> getTaatand
 --> computeTaatand

Document at A Time OR and AND is implemented using Priority Queue

DAAT.java 
  --> getDaatOR
  --> getDAATAND


Searching the Terms 
Specify the query terms in input.txt files.Every line specifies the single input query.
InvertedIndex.java files read the query terms line by line and generates the output file with all posting lists of all terms and their TAAT,DAAT  OR and AND 

java -jar InvertedIndex.jar path_of_index output.txt input.txt
