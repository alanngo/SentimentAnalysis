import java.io.IOException;
import java.util.Scanner;


public class Assignment2 
{
	private final String POS_KEYWORD ="pos";
	private final String NEG_KEYWORD ="neg";
	private final int POS_RATING=1;
	private final int NEG_RATING=0;
	private Scanner in;
	
	public Assignment2()
	{
		in = new Scanner(System.in);
	}
	public void menu()
    {
    	System.out.println("0. Exit Program");
    	System.out.println("1. Load new movie review collection (given a folder or a file path)");
    	System.out.println("2. Delete movie review from database (given its id)");
    	System.out.println("3. Search movie reviews in database by id ");
    	System.out.println("4. Search reviews by substring");
    }
	public void loadReview( ReviewHandler rv) throws IOException
	{
		System.out.print("Enter the file path: ");
		String filePath = in.nextLine(); //input from scanner
		if (filePath.contains(NEG_KEYWORD))
		{
			rv.loadReviews(filePath, NEG_RATING);
		}
		else if (filePath.contains(POS_KEYWORD))
		{
			rv.loadReviews(filePath, POS_RATING);
		}
		rv.writeTo("out.txt");
	}
    public void deleteReview(ReviewHandler rv)
    {
		System.out.print("Enter an id to delete: ");
		int id = in.nextInt();
		rv.deleteReview(id);
    }
    public void searchByID(ReviewHandler rv)
    {
		System.out.print("Enter an id to seach: ");
		int id = in.nextInt();
		System.out.println("Movie: "+ rv.searchById(id));
	
    }
    public void searchBySubString(ReviewHandler rv)
    {
    	in.nextLine();
    	System.out.print("Enter a substring: ");
		String substring = in.next();
		System.out.println("Results with "+ substring);
		System.out.println(rv.searchBySubstring(substring));
    }
}
