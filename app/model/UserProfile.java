package model;

public class UserProfile {
	
	 public String login;
	 public String id;
	 public String node_id;
	 public String avatar_url;
	 public String repos_url;
	 public String email;
	 public String twitter_username;
	 public String followers;
	 public String following;
	 public String subscriptions_url;
	 public String organizations_url;
	 
	 
	 public UserProfile() {
			this.login = "";
			this.id = "";
			this.node_id = "";
			this.avatar_url = "";
			this.repos_url = "";
			this.email = "";
			this.twitter_username = "";
			this.followers = "";
			this.following = "";
			this.subscriptions_url = "";
			this.organizations_url = "";			
		}
	 
	 public UserProfile(String login, String id, String node_id, String avatar_url, String repos_url, String email,
			String twitter_username, String followers, String following, String subscriptions_url,
			String organizations_url) {
		
		this.login = login;
		this.id = id;
		this.node_id = node_id;
		this.avatar_url = avatar_url;
		this.repos_url = repos_url;
		this.email = email;
		this.twitter_username = twitter_username;
		this.followers = followers;
		this.following = following;
		this.subscriptions_url = subscriptions_url;
		this.organizations_url = organizations_url;
	}

	
	  
	 public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTwitter_username() {
		return twitter_username;
	}

	public void setTwitter_username(String twitter_username) {
		this.twitter_username = twitter_username;
	}

	public String getFollowers() {
		return followers;
	}

	public void setFollowers(String followers) {
		this.followers = followers;
	}

	public String getFollowing() {
		return following;
	}

	public void setFollowing(String following) {
		this.following = following;
	}

	public String getSubscriptions_url() {
		return subscriptions_url;
	}

	public void setSubscriptions_url(String subscriptions_url) {
		this.subscriptions_url = subscriptions_url;
	}

	public String getOrganizations_url() {
		return organizations_url;
	}

	public void setOrganizations_url(String organizations_url) {
		this.organizations_url = organizations_url;
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
