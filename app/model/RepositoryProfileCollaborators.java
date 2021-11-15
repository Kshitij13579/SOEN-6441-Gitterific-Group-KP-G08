package model;

public class RepositoryProfileCollaborators {

    public String login;
	public String id;
    public String role_name;
    public String user_url;
    
    public RepositoryProfileCollaborators() {
    	this.login = "";
    	this.id = "";
    	this.role_name = "";
    	this.user_url = "";
    	
    }
    
    public RepositoryProfileCollaborators(String login,String id,String role_name,String user_url) {
    	this.login = login;
    	this.id = id;
    	this.role_name = role_name;
    	this.user_url = user_url;
    	
    }
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getRoleName() {
		return role_name;
	}
	public void setRoleName(String role_name) {
		this.role_name = role_name;
	}
	
	public String getUserUrl() {
		return user_url;
	}
	public void setUserUrl(String user_url) {
		this.user_url = user_url;
	}
}
