package model;

public class UserProfile {
	
	 public String login;
	 public String id;
	 public String node_id;
	 public String avatar_url;
	 public String repos_url;
	 
	 public UserProfile() {
			this.login = "";
			this.id = "";
			this.node_id = "";
			this.avatar_url = "";
			this.repos_url = "";
		}
	 
	 public UserProfile(String login, String id, String node_id, String avatar_url, String repos_url) {
			this.login = login;
			this.id = id;
			this.node_id = node_id;
			this.avatar_url = avatar_url;
			this.repos_url = repos_url;
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
	public String getRepos_url() {
		return repos_url;
	}
	public void setRepos_url(String repos_url) {
		this.repos_url = repos_url;
	}

}
