package model;

/**
 * This class is the Model for User Repository
 *  
 * @author Siddhartha
 * @version 1.0
 *
 *
 */

public class UserRepository {
	
	/**
	 * Declaring login variable of type String
	 */
	public String login;
	
	/**
	 * Declaring name variable of type String
	 */	
    public String name;
    
	/**
	 * Declaring reponame variable of type String
	 */    
    public String reponame;
	
    /**
	  * Empty Constructor of UserRepository Model
	  */    
    public UserRepository() {
    	this.login = "";
    	this.name = "";
		this.reponame = "";
	}

	 /**
	  * 
	  * Constructor to initialize UserRepository Model
	  * @param login Name of User
	  * @param name of Repository
	  * @param reponame URL to repository
	  */
    public UserRepository(String login, String name, String reponame) {
    	this.login = login;
		this.name = name;
		this.reponame = reponame;
	}
	
    /**
	  * Getter for Name of repository
	  * @return name
	  */
	public String getName() {
		return name;
	}
	
	/**
	  * Setter for Name of repository
	  * @param name set repository name 
	  */
	public void setName(String name) {
		this.name = name;
	}

	/**
	  * Getter for repository URL
	  * @return repository URL
	  */
	public String getReponame() {
		return reponame;
	}

	/**
	  * Setter for repository URL
	  * @param reponame set repository URL 
	  */
	public void setReponame(String reponame) {
		this.reponame = reponame;
	}

	/**
	  * Getter for User Name
	  * @return login name
	  */
	public String getLogin() {
		return login;
	}
	
	/**
	  * Setter for User Name
	  * @param login set name 
	  */
	public void setLogin(String login) {
		this.login = login;
	}

}
