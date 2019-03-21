import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class Assignment1 
{
	public  HashSet<String> readFile(File f) throws FileNotFoundException //reads single file
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
	public  int rateMovie(File f, HashSet<String> neg, HashSet<String>pos) 
			throws FileNotFoundException // predicts movie rating based on words
	{
		HashSet<String> words = readFile(f);
		int rating = 0;
		for (String word: words)
		{
			if (neg.contains(word))
			{
				rating--;
			}
			if (pos.contains(word))
			{
				rating++;
			}
		}
		return rating; //
	}
	
	public  void predicted(String x)
	{
		System.out.println("predicted: "+x);
	}
	public  void actual(String x)
	{
		System.out.println("actual: "+x);
	}
	public  void printPredictedRating(File f, HashSet<String> neg, HashSet<String>pos) throws FileNotFoundException
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
	public  void printFinal(File[] f, HashSet<String> neg, HashSet<String>pos, String actualRating) throws FileNotFoundException
	{
		for (int i =0; i<f.length; i++)
    	{
    		System.out.println(f[i].getPath());
    		printPredictedRating(f[i], neg, pos);
    		actual(actualRating);
    		System.out.println();
    	}
	}
}
