import java.io.*;
import java.util.*;

public class SentimentAnalysisApp 
{
	//global constants
	
	static Scanner in = new Scanner(System.in);
	
	public static void assignment1(String [] args) throws FileNotFoundException
    {
		/* 
		windows directories used for testing
		
		File negWordsWindows = new File("D:\\Java Workspace\\SentimentAnalysis\\Data\\negative-words.txt");
    	File posWordsWindows = new File("D:\\Java Workspace\\SentimentAnalysis\\Data\\positive-words.txt");
    	
    	//linux directories
    	File negWordsLinux = new File("/mnt/d/Java Workspace/SentimentAnalysis/Data/positive-words.txt"); 
    	File posWordsLinux = new File("/mnt/d/Java Workspace/SentimentAnalysis/Data/negative-words.txt");
    	*/

    	Assignment1 a = new Assignment1();
    	//terminal args
    	File posWords = new File(args[0]);
    	File negWords = new File(args[1]);
    	File posFolderPath = new File(args[2]);
    	File negFolderPath = new File(args[3]);
    	
    	//word sets
    	HashSet<String> negWordSet = a.readFile(negWords);
    	HashSet<String> posWordSet = a.readFile(posWords);
    	
    	//array of file reviews
    	File[] posList = posFolderPath.listFiles();
    	File[] negList = negFolderPath.listFiles();
    	
    	a.printFinal(posList, negWordSet, posWordSet, "positive");//prints all in positive folder
    	a.printFinal(negList, negWordSet, posWordSet, "negative");//prints all in negative folder
    }
    public static void assignment2(String []args) throws IOException
    {
    	Assignment2 a = new Assignment2();
    	
    	ReviewHandler rv = new ReviewHandler();
    	char again ='y';
    	int choice = 0;
    	
    	while (again=='y')
    	{
    		a.menu();
    		choice = in.nextInt();
    		//in.reset();
    		switch (choice)
    		{
	    		case 0: //exit
	    			System.exit(0);
	    		case 1://load review 
	    			a.loadReview(rv);
	    			rv.saveSerialDB();
	    			break;
	    		case 2:// delete movie review
	    			a.deleteReview(rv);
	    			rv.saveSerialDB();
	    			break;
	    		case 3: //search by id
	    			a.searchByID(rv);
	    			break;
	    		case 4://search by substring
	    			a.searchBySubString(rv);
	    			break;
	    		default://save database
	    			rv.saveSerialDB();
	    			break;
    		}	
    	}  	
    }
    
    public static void main(String[] args)  
    {
    	try
    	{
    		assignment2(args);
    	}
    	catch (Exception e)
    	{
    		System.out.println("YA DUN GOOFED");
    		//e.printStackTrace();
    	}
    }
}

