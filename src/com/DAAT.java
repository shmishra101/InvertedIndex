package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class DAAT 
{   int comparison=0;
   public  ArrayList<LinkedList<Integer>> insertPostinglist(String term,HashMap<String, LinkedList<Integer>> hash)
   {   ArrayList<LinkedList<Integer>> posting=new ArrayList<LinkedList<Integer>>();
	   String terms[]=term.split(" ");
	   int i=0;
	   while(i<terms.length)
	   {
		   LinkedList<Integer> docposting=hash.get(terms[i]);
		   posting.add(docposting);
	   }
	   return posting;
   }
   
   public PriorityQueue<Integer> increaseIndex(int docid,ArrayList<LinkedList<Integer>> docposting,PriorityQueue<Integer> prior)
   {   int j=0;
       int increment=0;
	   while(j<docposting.size())
	   {
		   LinkedList<Integer> posting=docposting.get(j);
		   if(posting.contains(docid))
		   {   
			   increment=posting.indexOf(new Integer(docid))+1;
			  if(increment<posting.size())
			  {   
				  prior.add(posting.get(increment));
			  }
		   }
		   j++;
	   }
	   
	  // System.out.println(prior);
	   return prior;
   }
   
   public  List<Integer> getRemaining(int docid,ArrayList<LinkedList<Integer>> docposting)
	{    int j=0;
        int increment=0;
        List<Integer> remaining=new ArrayList<Integer>();
		while(j<docposting.size())
		   {
			   LinkedList<Integer> posting=docposting.get(j);
			   if(posting.contains(docid))
			   {
				   increment=posting.indexOf(new Integer(docid));
				  if(increment<posting.size())
				  { //  System.out.println(posting.subList(increment, posting.size()));
					 remaining.addAll(posting.subList(increment, posting.size()));
				  }
			   }
			   j++;
		   }
		return remaining;
	}
   public LinkedList<Integer> getDAATOR(ArrayList<LinkedList<Integer>> docposting)
   {   
	   comparison=0;
	   LinkedList<Integer> answer=new LinkedList<Integer>();
	   Comparator<Integer> myComoparator = new Comparator<Integer>() {
	    @Override
	    public int compare(Integer i1, Integer i2) {
	        if (i1 > i2) {             
	            return 1;         
	        } else {
	            return -1;
	        } 
	    }
	   };
	   PriorityQueue<Integer> prior=new PriorityQueue<Integer>(myComoparator);
	   int i=0;
	   if(docposting.size()==1)
	   {   
		   return docposting.get(0);
	   }
	   while(i<docposting.size())
       {   LinkedList<Integer> inter=docposting.get(i); 
        	prior.add(inter.get(0));
            i++;
       }
	   while(prior.size()>0)
	   {   comparison++; 
		   int temp=prior.peek();
		   if(prior.size()==1)
	       {   comparison--;
	    	   answer.addAll(getRemaining(temp, docposting));
	    	   return answer;
	       }
		   prior.removeAll(Arrays.asList(temp));
		   answer.add(temp);
		   prior=increaseIndex(temp, docposting, prior);
		  
	   }
	  return answer; 
     }
   
   public LinkedList<Integer> getDAATAND(ArrayList<LinkedList<Integer>> docposting)
   {   comparison=0;
	   LinkedList<Integer> answer=new LinkedList<Integer>();
	   Comparator<Integer> myComoparator = new Comparator<Integer>() {
	    @Override
	    public int compare(Integer i1, Integer i2) {
	        if (i1 > i2) {             
	            return 1;         
	        } else {
	            return -1;
	        } 
	    }
	   };
	   PriorityQueue<Integer> prior=new PriorityQueue<Integer>(myComoparator);
	   int i=0;
	   while(i<docposting.size())
       {   LinkedList<Integer> inter=docposting.get(i); 
        	prior.add(inter.get(0));
            i++;
       }
	   while(prior.size()==docposting.size())
	   {   comparison++; 
		   int temp=prior.peek();
		   prior.removeAll(Arrays.asList(temp));
		   if(prior.size()==0)
		   {
			   answer.add(temp);
		   }
		   prior=increaseIndex(temp, docposting, prior);
	   }
	  return answer; 
   }
}
