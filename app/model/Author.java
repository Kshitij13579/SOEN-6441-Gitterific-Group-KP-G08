package model;


import java.util.Objects;

/**
* The Author model class is used to store information about Author 
* in a commit.
* This class is used in Commit.java
*
* @author  Kshitij Yerande
* @version 1.0
* @since   2021-11-20 
*/
public class Author {
    public String name;
    public String login;
    public int commits;
    
    public Author() {
    	
    }
    
    public Author(String name,String login,int commits) {
    	this.name = name;
    	this.login = login;
    	this.commits =commits;
    }
    
    /**This method is used to get total number of commits of Author
     * @return int total number of commits of author
     */
    public int getCommits() {
		return commits;
	}
    
    /**This method is used to set total number of commits of Author
     * @param int total number of commits of author
     */
	public void setCommits(int commits) {
		this.commits = commits;
	}
   
	/**This method is used to get Author Name
	 * @return String Author Name
	 * */
	public String getName() {
		return name;
	}
    
	/**This method is used to set Author Name
	 * @param String Author Name
	 * */
	public void setName(String name) {
		this.name = name;
	}
    
	/**This method is used to get Login Id of Author
	 * @return String Author Login Id
	 * */
	public String getLogin() {
		return login;
	}
    
	/**This method is used to set Login Id of Author
	 * @param String Author Login id
	 * */
	public void setLogin(String login) {
		this.login = login;
	}
	
	/**
	 * This method is used to compare two Author Objects based on Name, Login and Commits
	 * @return Boolean true if both the objects are equal
	 *                 false if both the objects are not equal
	 */
	@Override
	public boolean equals(Object o) {
	    if (this == o)
	        return true;
	    if (o == null || getClass() != o.getClass())
	        return false;
	    Author a = (Author) o;
	    return Objects.equals(name, a.name) && Objects.equals(login, a.login) && Objects.equals(commits, a.commits);
	}
    
	/** This method return hash code of objects.
	 * @return int hash code of object
	 */
	@Override
	public int hashCode() {
	    return Objects.hash(name, login,commits);
	}

	
}
