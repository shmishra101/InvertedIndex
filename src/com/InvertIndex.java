package com;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.FSDirectory;


public class InvertIndex {

	public static void main(String[] args) throws IOException 
	{  
	  	String readerindexpath=args[0];
		String outpufilepath=args[1];
		String inputfilepath=args[2];
		Path path=Paths.get(readerindexpath);
		IndexReader indexreader=DirectoryReader.open(FSDirectory.open(path));
	    HashMap<String,LinkedList<Integer>> hash=CreateIndex.createPostings(indexreader);
	    List<String> termslist=IO.readFromFile(inputfilepath);
	    ArrayList<LinkedList<Integer>> daatpostings=null;
	    BooleanSearch taatquery=new BooleanSearch();
	    DAAT daat=new DAAT();
	    if(termslist!=null)
	    {   int i=0;
	        
	    	while(i<termslist.size())
	    	{   
	    		String termsline=termslist.get(i);
	    		String terms[]=termsline.split(" ");
	    		int j=0;
	    		daatpostings=new ArrayList<LinkedList<Integer>>();
	    		while(j<terms.length)
	    		{   // Adding terms posting to arraylist of linkedlist to calculate DAAT
	    			LinkedList<Integer> termpostings=CreateIndex.getPostingList(terms[j], hash);
	    			daatpostings.add(termpostings);
	    			if(termpostings!=null)
	    			{   //Wrting Postings list of each term to output file
	    				IO.writePostingToFile(outpufilepath,termpostings, terms[j]);
	    			}
	    			j++;
	    		}
	    		LinkedList<Integer> taator=taatquery.getTaator(terms, hash);
	    		int taatorcomparison=taatquery.getComparisons();
	    		LinkedList<Integer> daator=daat.getDAATOR(daatpostings);
	    		int daatorcomparison=daat.comparison;
	    		LinkedList<Integer> taatand=taatquery.getTaatand(terms, hash);
	    		int taatandcomparison=taatquery.getComparisons();
	    		System.out.println(taatandcomparison);
	    		LinkedList<Integer> daatand=daat.getDAATAND(daatpostings);
	    		int daatandcomparison=daat.comparison;
	    		IO.writeResultToFile(outpufilepath,taatand, terms, taatandcomparison, "TaatAnd");
	    		IO.writeResultToFile(outpufilepath,taator, terms,taatorcomparison, "TaatOr");
	    	    IO.writeResultToFile(outpufilepath,daatand, terms, daatandcomparison, "DaatAnd");
	    	    IO.writeResultToFile(outpufilepath,daator, terms, daatorcomparison, "DaatOr");
	    	    i++;
	    	}
	    }
	}

}
