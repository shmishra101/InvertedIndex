package com;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import org.apache.lucene.index.Fields;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.MultiFields;
import org.apache.lucene.index.PostingsEnum;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.search.DocIdSetIterator;
import org.apache.lucene.util.BytesRef;

public class CreateIndex 
{
      public static HashMap<String, LinkedList<Integer>> createPostings(IndexReader reader) throws IOException
      {   
    	  Fields fields= MultiFields.getFields(reader);
    	  fields.iterator().next();
    	  Iterator<String> field=fields.iterator();
    	  HashMap<String, LinkedList<Integer>>hash=new HashMap<>();
    	 while(field.hasNext())
    	  {  String fieldsdoc=field.next();
    		 if(!fieldsdoc.equals("id")) 
    		 {  Terms terms=fields.terms(fieldsdoc);
    		     
    		    if(terms!=null)
    		   { TermsEnum term=terms.iterator();
    		      BytesRef text;
    		     while((text = term.next()) != null)
    			   {  PostingsEnum post=MultiFields.getTermDocsEnum(reader, fieldsdoc, text);
    			      
    			      if(hash.get(text.utf8ToString())!=null)
    			      { LinkedList<Integer> l=new LinkedList<Integer>();
    			        l=hash.get(text.utf8ToString());
    			    	 while(post.nextDoc()!=DocIdSetIterator.NO_MORE_DOCS)
        			     {  
        			    	  l.add(post.docID());
        			     }
    			    	 Collections.sort(l);
    			    	 hash.put(text.utf8ToString(),l);
    			      }
    			      else
    			      {   LinkedList<Integer> lt= new LinkedList<Integer>();
    			    	  while(post.nextDoc()!=DocIdSetIterator.NO_MORE_DOCS)
    			             {  
    			    	      lt.add(post.docID());
    			             }
    			    	 
    			    	  hash.put(text.utf8ToString(), lt);
    			      }
    			   }
    			}
    		   }
    		 }
         return hash;	
    	   }
     
      public static LinkedList<Integer> getPostingList(String term,HashMap<String, LinkedList<Integer>> hash)
      {
    	    return hash.get(term);
      }
}
