package model;

/**
 
 * Class - RepositoryProfileCollaborators is a model to store information about repository collaboration details
 * It includes following information
 * 1. login - UserName
 * 2. id - Repository name
 * 3. role_name - Role of User details
 * 4. user_url - User Details
 * @author Yogesh Yadav
 * @version 1.0
 * @since   2021-11-20 
 */
public class RepositoryProfileCollaborators {

    public String login;
	public String id;
    public String role_name;
    public String user_url;
    
    /**
     * Constructor to Initialize RepositoryProfileCollaborators object
     */
    public RepositoryProfileCollaborators() {
    	this.login = "";
    	this.id = "";
    	this.role_name = "";
    	this.user_url = "";
    	
    }
    
    /**
     * Constructor to Initialize RepositoryProfileCollaborators object
     * @param login
     * @param id
     * @param role_name
     * @param user_url
     */
    public RepositoryProfileCollaborators(String login,String id,String role_name,String user_url) {
    	this.login = login;
    	this.id = id;
    	this.role_name = role_name;
    	this.user_url = user_url;
    	
    }
    /**
	 * Method to get login details of a git Repository
	 * @return login details of Repository
	 */
	public String getLogin() {
		return login;
	}
	/**
	 * Method to set login details in the model RepositoryProfileCollaborators for the git Repository
	 * @param login details for Repository
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	
	   /**
		 * Method to get id details of a git Repository
		 * @return id details of Repository
		 */
	public String getId() {
		return id;
	}
	/**
	 * Method to set id details in the model RepositoryProfileCollaborators for the git Repository
	 * @param id details for Repository
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	 /**
	 * Method to get role_name details of a git Repository
	* @return role_name details of Repository
	*/
	public String getRoleName() {
		return role_name;
	}
	/**
	 * Method to set role_name details in the model RepositoryProfileCollaborators for the git Repository
	 * @param role_name details for Repository
	 */
	public void setRoleName(String role_name) {
		this.role_name = role_name;
	}
	
	 /**
	 * Method to get user_url details of a git Repository
	* @return user_url details of Repository
	*/
	public String getUserUrl() {
		return user_url;
	}
	/**
	 * Method to set user_url details in the model RepositoryProfileCollaborators for the git Repository
	 * @param user_url details for Repository
	 */
	public void setUserUrl(String user_url) {
		this.user_url = user_url;
	}
}
