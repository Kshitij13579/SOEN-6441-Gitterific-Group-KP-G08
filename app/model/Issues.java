package model;

/**
 * This class is the Model for Repository Issue Titles Statistics
 *  
 * @author Akshay
 * @version 1.0
 *
 *
 */
public class Issues {
	
	/**
	 * Declaring title variable of type String
	 */
	public String title;
	
	/**
	 * Creates Empty Constructor for class
	 * @author Akshay
	 */
	public Issues() {
		
	}
	
	/**
	 * Constructor for initializing title
	 * @author Akshay
	 * @param title This is the Repository issue title
	 * 
	 */
	public Issues(String title)
	{
		this.title=title;
	}

	/**
	 * Getter for title
	 * @author Akshay
	 * @return Title of type String
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Setter for title
	 * @author Akshay
	 * @param title set title value
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
