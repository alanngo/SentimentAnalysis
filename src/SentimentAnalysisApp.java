import java.io.*;
import java.util.*;


public class SentimentAnalysisApp 
{
	public static HashSet<String> readFile(File f) throws FileNotFoundException //reads single file
	{
		Scanner s = new Scanner(f);
		HashSet<String> words = new HashSet<String>();
		while (s.hasNext())
		{
			words.add(s.next().toLowerCase());
		}
		s.close();
		return words;
	}
	public static int rateMovie(File f, HashSet<String> neg, HashSet<String>pos) throws FileNotFoundException // predicts movie rating based on words
	{
		HashSet<String> words = readFile(f);
		int rating = 0;
		for (String word: words)
		{
			if (neg.contains(word))
			{
				rating --;
			}
			if (pos.contains(word))
			{
				rating++;
			}
		}
		return rating; //
	}
	
	public static void predicted(String x)
	{
		System.out.println("predicted: "+x);
	}
	public static void actual(String x)
	{
		System.out.println("actual: "+x);
	}
	public static void printPredictedRating(File f, HashSet<String> neg, HashSet<String>pos) throws FileNotFoundException
	{
		int rating = rateMovie(f, neg, pos);
		if (rating>0)
		{
			predicted("positive "+rating);
		}
		else 
		{
			predicted("negative "+ rating);
		}
	}
	public static void printFinal(File[] f,HashSet<String> neg, HashSet<String>pos, String actualRating) throws FileNotFoundException
	{
		for (int i =0; i<f.length; i++)
    	{
    		System.out.println(f[i].getPath());
    		printPredictedRating(f[i], neg, pos);
    		actual(actualRating);
    		System.out.println();
    	}
	}
	
    
	public static void main(String[] args) throws IOException 
    {
		/* 
		windows directories used for testing
		
		File negWordsWindows = new File("D:\\Java Workspace\\SentimentAnalysis\\Data\\negative-words.txt");
    	File posWordsWindows = new File("D:\\Java Workspace\\SentimentAnalysis\\Data\\positive-words.txt");
    	
    	//linux directories
    	File negWordsLinux = new File("/mnt/d/Java Workspace/SentimentAnalysis/Data/positive-words.txt"); 
    	File posWordsLinux = new File("/mnt/d/Java Workspace/SentimentAnalysis/Data/negative-words.txt");
    	*/

    	
    	//terminal args
    	File posWords = new File(args[0]);
    	File negWords = new File(args[1]);
    	File posFolderPath = new File(args[2]);
    	File negFolderPath = new File(args[3]);
    	
    	//word sets
    	HashSet<String> negWordSet = readFile(negWords);
    	HashSet<String> posWordSet = readFile(posWords);
    	
    	//array of file reviews
    	File[] posList = posFolderPath.listFiles();
    	File[] negList = negFolderPath.listFiles();
    	
    	printFinal(posList, negWordSet, posWordSet, "positive");//prints all in positive folder
    	printFinal(negList, negWordSet, posWordSet, "negative");//prints all in negative folder
    	
    	
    }
}

