package com;
import java.util.HashMap;
import java.util.LinkedList;
public class BooleanSearch 
{   int comparison=0;
    int midcomparison=0;
   public LinkedList<Integer> getTaator(String terms[],HashMap<String, LinkedList<Integer>> hash)
   {   int j=1;
       LinkedList<Integer> finalanswer=new LinkedList<Integer>();
       comparison=0;
       finalanswer=CreateIndex.getPostingList(terms[0], hash);
       if(terms.length==1)
       {   comparison=comparison+midcomparison;
    	   return finalanswer;
       }  
       else
       {	 while(j<terms.length)
	       { 
    	    LinkedList<Integer> nexttermposting=CreateIndex.getPostingList(terms[j], hash);
	        if(finalanswer.size()<=nexttermposting.size())
		     {   
	        	 finalanswer=computeTaatOr(finalanswer, nexttermposting);
	        	 comparison=comparison+midcomparison;
		     }
	         else 
	         {    
	        	 finalanswer=computeTaatOr(nexttermposting,finalanswer);
	        	 comparison=comparison+midcomparison;
	         }
	         j++;
		   }
	   }
          return finalanswer;
    }
   
   public  LinkedList<Integer> computeTaatOr(LinkedList<Integer> result,LinkedList<Integer> termpost)
   {   midcomparison=0;
	   LinkedList<Integer> answer=new LinkedList<Integer>();
	   int i=0; 
	   int j=0;
	   if(result.size()<=termpost.size())
	   {
		   while(i<result.size()&&j<termpost.size())
		   {
			   if(result.get(i).equals(termpost.get(j)))
			   {
				   answer.add(result.get(i));
				   i++;
				   j++;
				   midcomparison++;
			   }
			   else
			   { if(result.get(i)<termpost.get(j))
			     {
				   answer.add(result.get(i));
				   i++;
				   midcomparison++;
			     }
			    else
			     {  
                    answer.add(termpost.get(j));
                    j++;
                    midcomparison++;
                 }
               }
		    }
		     if(i==result.size())
		      {  int k=j;
			     for(k=j; k<termpost.size(); k++)
			     {
				  answer.add(termpost.get(k));
			     }
		      }
		     if(j==termpost.size())
			   {  int k=i;
				  for(k=i; k<result.size(); k++)
				  {
					  answer.add(result.get(k));
				  }
			   }
	      
         }
         return answer;
  }
   
   
   public  LinkedList<Integer> getTaatand(String terms[],HashMap<String, LinkedList<Integer>> hash)
   {   int j=1;
       LinkedList<Integer> finalanswer=new LinkedList<Integer>();
       comparison=0;
       finalanswer=CreateIndex.getPostingList(terms[0], hash);
       if(terms.length==1)
       {   comparison=comparison+midcomparison;
    	   return finalanswer;
       }  
       else
       {	   while(j<terms.length)
	       { 
		     finalanswer=computeTaatand(finalanswer, CreateIndex.getPostingList(terms[j], hash));
		     comparison=comparison+midcomparison;
		     j++;
	       }
          return finalanswer;
       }
      
   }
   
   
   
   public LinkedList<Integer> computeTaatand(LinkedList<Integer>result,LinkedList<Integer> termpost)
   {   int i=0; 
       int j=0;
       int resultp=(int)Math.sqrt(result.size());
       int termpostp=(int)Math.sqrt(termpost.size());
       LinkedList<Integer> answer=new LinkedList<Integer>();
       midcomparison=0;
       while(i<result.size()&&j<termpost.size())
	   {  
		   if(result.get(i).equals(termpost.get(j)))
		   {
			   answer.add(result.get(i));
			   i++;
			   j++;
			   if(i==j) 
			   {
				   midcomparison++;
			   }
			  
		   }
		   else 
		   {
			   if(result.get(i)<termpost.get(j))
			   {   midcomparison++;
				   if(resultp<result.size() && result.get(resultp)<=termpost.get(j))
				   {   
					   while(resultp<result.size() && result.get(resultp)<=termpost.get(j))
					   { 
					       i=resultp+1;
						   resultp=resultp+resultp;
						   midcomparison++;
					   }
				   }
				   else
				   {   midcomparison++;
					   i++;
				   }
			   }
			   else
			   {   
				   if(termpostp<termpost.size() && result.get(i)>=termpost.get(termpostp))
				   {   
					   while(termpostp<termpost.size() && result.get(i)>=termpost.get(termpostp))
					   {  
						   midcomparison++;
						   j=termpostp+1;
						   termpostp=termpostp+termpostp;
						   
					   }
				   }
				   else
				   {  if(termpostp>termpost.size()) {midcomparison++;}
					   j++;
				   }
			   }
		   }
	   }
	   return answer;
	   
   }
   
   public int getComparisons()
   {
	   return comparison;
   }
   

}
