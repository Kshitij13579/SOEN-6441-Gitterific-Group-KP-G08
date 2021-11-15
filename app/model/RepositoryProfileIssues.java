package model;

public class RepositoryProfileIssues {
 public String issue_number;
 public String issue_title;
 public String state;
 public String created_at;
 public String updated_at;
 
 public RepositoryProfileIssues() {
	 this.issue_number = "";
	 this.issue_title = "";
	 this.state = "";
	 this.created_at = "";
	 this.updated_at = "";
 }

 public RepositoryProfileIssues(String issue_number, String issue_title, String state, String created_at, String updated_at) {
	 this.issue_number = issue_number;
	 this.issue_title = issue_title;
	 this.state = state;
	 this.created_at = created_at;
	 this.updated_at = updated_at;
 }
 
	public String getIssueNumber() {
		return issue_number;
	}
	public void setIssueNumber(String issue_number) {
		this.issue_number = issue_number;
	}
	
	public String getIssueTitle() {
		return issue_title;
	}
	public void setIssueTitle(String issue_title) {
		this.issue_title = issue_title;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String getCreatedAt() {
		return created_at;
	}
	public void setCreatedAt(String created_at) {
		this.created_at = created_at;
	}
	
	public String getUpdateAt() {
		return updated_at;
	}
	public void setUpdateAt(String updated_at) {
		this.updated_at = updated_at;
	}
	
 
}

