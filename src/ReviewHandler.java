import java.io.*;
import java.util.*;
import java.util.Map.*;


public class ReviewHandler extends AbstractReviewHandler 
{
	private Random rand; //used to generate ids
	private final int ID = 1000000; //how many movies we can use
	
	//constructor
	public ReviewHandler()
	{ 
		super();
		rand = new Random();
	}
	
	//helper functions
	private String getWords(File f) throws FileNotFoundException
	{
		Scanner s = new Scanner(f);
		StringBuilder sb = new StringBuilder();
		while (s.hasNextLine())
		{
			sb.append(s.nextLine());
		}
		s.close();
		return sb.toString();
	}
	private int generateID()
	{
		int key = rand.nextInt(ID);
		Integer x = new Integer(key);
		if (!database.containsKey(x))
		{
			return key;
		}	
		return generateID();
	}

	public String toString()
	{
		return database.toString();
	}
	public void writeTo(String output) throws IOException
	{
		FileWriter w = new FileWriter(output);
		w.write(database.toString());
		w.close();
	}

	// inherited functions
	@Override
	 /**
     * Searches the review database by id.
     * @param id The id to search for.
     * @return The review that matches the given id or null if the id does not 
     * exist in the database.
     */
	public MovieReview searchById(int id) 
	{
		Integer x = new Integer(id); //key to search by
		if (database.containsKey(x))
		{
			return database.get(x);
		}
		return null;
	}

	@Override
    /**
     * Searches the review database for reviews matching a given substring.
     * @param substring The substring to search for.
     * @return A list of review objects matching the search criterion.
     */
	public List<MovieReview> searchBySubstring(String substring) 
	{
		// TODO Auto-generated method stub
		List<MovieReview> list = new ArrayList<MovieReview>();
		for (Entry<Integer, MovieReview> entry: database.entrySet())
		{
			String text = entry.getValue().getText();
			if (text.contains(substring))
			{
				list.add(entry.getValue());
			}
		}
		return list;
	}

	@Override
    /**
     * Loads reviews from a given path. If the given path is a .txt file, then
     * a single review is loaded. Otherwise, if the path is a folder, all reviews
     * in it are loaded.
     * @param filePath The path to the file (or folder) containing the review(sentimentModel).
     * @param realClass The real class of the review (0 = Negative, 1 = Positive
     * 2 = Unknown).
     * @return A list of reviews as objects.
     */
	public void loadReviews(String filePath, int realClass) throws IOException
	{
		// TODO Auto-generated method stub
			MovieReview m = null;
			File f = new File(filePath);
			if (f.isDirectory())
			{
				File [] files = f.listFiles();
				for (File fi: files)
				{
					m = readReview(fi.getPath(), realClass);
					database.put(new Integer(m.getId()), m);
				}
			}
			else
			{
				m = readReview(filePath, realClass);
				database.put(new Integer(m.getId()) , m);
			}		
	}

	@Override
	/**
     * Reads a single review file and returns it as a MovieReview object. 
     * This method also calls the method classifyReview to predict the polarity
     * of the review.
     * @param reviewFilePath A path to a .txt file containing a review.
     * @param realClass The real class entered by the user.
     * @return a MovieReview object.
     * @throws IOException if specified file cannot be opened.
     */
	public MovieReview readReview(String reviewFilePath, int realClass) throws IOException 
	{
		// TODO Auto-generated method stub
		File f = new File(reviewFilePath);
		String text = getWords(f);
		int id = generateID();
		MovieReview tmp = new MovieReview(id, text, realClass);
		int predictedPolarity = classifyReview(tmp);
		tmp.setPredictedPolarity(predictedPolarity);
		return tmp;
	}

	@Override
    /**
     * Deletes a review from the database, given its id.
     * @param id The id value of the review.
     */
	public void deleteReview(int id) 
	{
		Integer x = new Integer(id); //Integer representation of id
		if (database.containsKey(x))
		{
			database.remove(x);
		}
		else
		{
			System.out.println("ID does not exsist!");
		}
	}

	@Override
	public void loadSerialDB() throws IOException, ClassNotFoundException 
	{
		// TODO Auto-generated method stub
	}
}
