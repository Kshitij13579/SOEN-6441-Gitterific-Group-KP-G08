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
	 */
	public Issues() {
		
	}
	
	/**
	 * Constructor for initializing title
	 * @param title This is the Repository issue title
	 */
	public Issues(String title)
	{
		this.title=title;
	}

	/**
	 * Getter for title
	 * @return Title of type String
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Setter for title
	 * @param title set title value
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
