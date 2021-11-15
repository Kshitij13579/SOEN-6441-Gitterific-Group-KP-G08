package model;

public class RepositoryProfile {

	public String login; 
	public String id; 
	public String node_id; 
	public String avatar_url; 
	public String collaborators_url;
	public String issues_url;
	public String open_issues;
	
	public RepositoryProfile() {
		this.login = "";
		this.id = "";
		this.node_id = "";
		this.avatar_url = "";
		this.collaborators_url = "";
		this.issues_url = "";
		this.open_issues="";
	}
	public RepositoryProfile(String login, String id, String node_id, String avatar_url, String collaborators_url,
			String issues_url, String open_issues) {
		this.login = login;
		this.id = id;
		this.node_id = node_id;
		this.avatar_url = avatar_url;
		this.collaborators_url = collaborators_url;
		this.issues_url = issues_url;
		this.open_issues=open_issues;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getNode_id() {
		return node_id;
	}
	public void setNode_id(String node_id) {
		this.node_id = node_id;
	}
	
	public String getAvatar_url() {
		return avatar_url;
	}
	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}
	
	public String getcollaborators_url() {
		return collaborators_url;
	}
	public void setcollaborators_url(String collaborators_url) {
		this.collaborators_url = collaborators_url;
	}
	
	public String getissues_url() {
		return collaborators_url;
	}
	public void setissues_url(String issues_url) {
		this.issues_url = issues_url;
	}
	
	public String getopen_issues() {
		return open_issues;
	}
	public void setopen_issues(String open_issues) {
		this.open_issues = open_issues;
	}
}

