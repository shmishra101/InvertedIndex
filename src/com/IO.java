package com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class IO 
{
	public static List<String> readFromFile(String path) throws IOException
	   {   File filedir = new File(path);
		 BufferedReader in =new BufferedReader(new InputStreamReader(new FileInputStream(filedir), "UTF-8"));
		String str;
		List<String> terms=new ArrayList<String>();
		while((str=in.readLine())!=null)
		{
			terms.add(str);
		}
		in.close();
		   return terms;
	   }
	public static void writePostingToFile(String outputfilepath,LinkedList<Integer> postings,String term) throws IOException
	{   File filedir = new File(outputfilepath);
		FileOutputStream fos = new FileOutputStream(filedir,true);
	    BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(fos,Charset.forName("UTF-8")));
	    writer.append("GetPostings \n");
	    writer.append(term);
	    writer.append("\n");
	    writer.append("Postings list: ");
	    int j=0;
	    while(j<postings.size())
	    {
	    	writer.append(postings.get(j)+" ");
	    	j++;
	    }
	    writer.append("\n");
	    writer.close();
		
	}
	public static void writeResultToFile(String outputfilepath,LinkedList<Integer> postings,String terms[],int comparison,String query) throws IOException
	{
		File filedir = new File(outputfilepath);
		FileOutputStream fos = new FileOutputStream(filedir,true);
	    BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(fos,Charset.forName("UTF-8")));
	    writer.append(query+"\n");
	    int j=0;
	    while(j<terms.length)
	    {
	    	writer.append(terms[j]+" ");
	    	j++;
	    }
	    writer.append("\nResults: ");
	    if(postings.size()==0)
	    {  
	    	writer.append("empty");
	    	writer.append("\nNumber of documents in results: 0");
	    	writer.append("\nNumber of comparisons: "+comparison);
	    	writer.append("\n");
	    	writer.close();
	    }
	    else 
	    { 
	    	j=0;
	        while(j<postings.size())
	           {
	    	     writer.append(postings.get(j)+" ");
	    	     j++;
	           }
	           writer.append("\n"+"Number of documents in results: "+postings.size());
	           writer.append("\n"+"Number of comparisons: "+comparison);
	           writer.append("\n");
	           writer.close();
	    }
	}
	

}
