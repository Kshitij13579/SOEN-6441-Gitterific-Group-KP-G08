package model;

/**

 * Class - RepositoryProfileCollaborators is a model to store information about repository issues details
 * It includes following information
 * 1. issue_number - Issue Number
 * 2. issue_title - Issue Title
 * 3. state - State of Issue
 * 4. created_at - Issue Created At
 * 4. updated_at - Issue Update At
 * @author Yogesh Yadav
 * @version 1.0
 * @since   2021-11-20 
 */
public class RepositoryProfileIssues {
 public String issue_number;
 public String issue_title;
 public String state;
 public String created_at;
 public String updated_at;
 
 /**
  * Constructor to Initialize RepositoryProfileIssues object
  */
public RepositoryProfileIssues() {
	 this.issue_number = "";
	 this.issue_title = "";
	 this.state = "";
	 this.created_at = "";
	 this.updated_at = "";
 }

 /**
 * Constructor to Initialize RepositoryProfileIssues object
 * @param issue_number
 * @param issue_title
 * @param state
 * @param created_at
 * @param updated_at
 */
public RepositoryProfileIssues(String issue_number, String issue_title, String state, String created_at, String updated_at) {
	 this.issue_number = issue_number;
	 this.issue_title = issue_title;
	 this.state = state;
	 this.created_at = created_at;
	 this.updated_at = updated_at;
 }
 
/**
	 * Method to get issue_number details of a git Repository
	 * @return issue_number details of Repository
	 */
	public String getIssueNumber() {
		return issue_number;
	}
	/**
	 * Method to set issue_number details in the model RepositoryProfileIssues for the git Repository
	 * @param issue_number details for Repository
	 */
	public void setIssueNumber(String issue_number) {
		this.issue_number = issue_number;
	}
	
	/**
	 * Method to get issue_title details of a git Repository
	 * @return issue_title details of Repository
	 */
	public String getIssueTitle() {
		return issue_title;
	}
	/**
	 * Method to set issue_title details in the model RepositoryProfileIssues for the git Repository
	 * @param issue_title detail for Repository
	 */
	public void setIssueTitle(String issue_title) {
		this.issue_title = issue_title;
	}
	
	/**
	 * Method to get state details of a git Repository
	 * @return state details of Repository
	 */
	public String getState() {
		return state;
	}
	/**
	 * Method to set state details in the model RepositoryProfileIssues for the git Repository
	 * @param state detail for Repository
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * Method to get created_at details of a git Repository
	 * @return created_at details of Repository
	 */
	public String getCreatedAt() {
		return created_at;
	}
	/**
	 * Method to set created_at details in the model RepositoryProfileIssues for the git Repository
	 * @param created_at detail for Repository
	 */
	public void setCreatedAt(String created_at) {
		this.created_at = created_at;
	}
	
	/**
	 * Method to get updated_at details of a git Repository
	 * @return updated_at details of Repository
	 */
	public String getUpdateAt() {
		return updated_at;
	}
	/**
	 * Method to set updated_at details in the model RepositoryProfileIssues for the git Repository
	 * @param updated_at detail for Repository
	 */
	public void setUpdateAt(String updated_at) {
		this.updated_at = updated_at;
	}
	
 
}

