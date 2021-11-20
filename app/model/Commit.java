package model;

/**
* The Commit model class is used to store information about Commit 
* from github API.
* It has the following information
* 1. Author - Author object containing details
* 2. Sha - unique id of commit 
* 3. Additions - number of file addition for commit
* 4. Deletions - number of file deletion for commit
* 
* @author  Kshitij Yerande
* @version 1.0
* @since   2021-11-20 
*/
public class Commit {
   public Author author;
   public String sha;
   public int additions;
   public int deletions;
   
   public Commit(Author author,String sha,int additions,int deletions) {
	   this.author = author;
	   this.sha = sha;
	   this.additions = additions;
	   this.deletions = deletions;
   }
   
   public Commit() {
	   
   }
    
    /** This method returns Author object for a commit
     * @return Author returns Author object
     */
	public Author getAuthor() {
		return author;
	}
	
	/** This method sets Author object for a commit
     * @param Author sets Author object
     */
	public void setAuthor(Author author) {
		this.author = author;
	}
	
	/** This method gets sha (unique id) for a commit
     * @return String returns sha value
     */
	public String getSha() {
		return sha;
	}
	
	/** This method sets sha(unique id) value for a commit
     * @param String sets sha value
     */
	public void setSha(String sha) {
		this.sha = sha;
	}
	
	/** This method gets number of additions in a commit.
     * @return int returns number of additions
     */
	public int getAdditions() {
		return additions;
	}
	
	/** This method sets number of additions for a commit
     * @param int sets number of additions.
     */
	public void setAdditions(int additions) {
		this.additions = additions;
	}
    
	/** This method gets number of deletions for a commit
     * @return int returns deletions
     */
	public int getDeletions() {
		return deletions;
	}
	
	/** This method sets number of deletions for a commit
     * @return int sets number of deletions
     */
	public void setDeletions(int deletions) {
		this.deletions = deletions;
	};
}
