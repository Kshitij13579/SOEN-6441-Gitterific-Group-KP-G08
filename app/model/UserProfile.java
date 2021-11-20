package model;

/**
 * This class is the Model for User Profile
 *  
 * @author Siddhartha
 * @version 1.0
 *
 *
 */

public class UserProfile {
	
	/**
	 * Declaring login variable of type String
	 */
	 public String login;
	 
	 /**
	  * Declaring id variable of type String
	  */	 
	 public String id;
	 
	 /**
	  * Declaring node_id variable of type String
	  */
	 public String node_id;
	 
	 /**
	  * Declaring avatar_url variable of type String
	  */
	 public String avatar_url;
	 
	 /**
	  * Declaring repos_url variable of type String
	  */
	 public String repos_url;
	 
	 /**
	  * Declaring email variable of type String
	  */
	 public String email;
	 
	 /**
	  * Declaring twitter_username variable of type String
	  */
	 public String twitter_username;
	 
	 /**
	  * Declaring followers variable of type String
	  */
	 public String followers;
	 
	 /**
	  * Declaring following variable of type String
	  */
	 public String following;
	 
	 /**
	  * Declaring subscriptions_url variable of type String
	  */
	 public String subscriptions_url;
	 
	 /**
	  * Declaring organizations_url variable of type String
	  */
	 public String organizations_url;
	 
	 /**
	  * Empty Constructor of UserProfile Model
	  */
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
	 
	 /**
	  * 
	  * Constructor to initialize UserProfile Model
	  * @param login Name of User
	  * @param id ID of User
	  * @param node_id URL to issues
	  * @param avatar_url URL to avatar
	  * @param repos_url URL to repos
	  * @param email mail id of user
	  * @param twitter_username twitter id of user
	  * @param followers number of followers of user
	  * @param following number of following of user
	  * @param subscriptions_url URL to subscriptions
	  * @param organizations_url URL to organizations
	  */
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

	
	 /**
	   * Getter for User Email
	   * @return email
	   */
	 public String getEmail() {
		return email;
	}

	 /**
	   * Setter for User Email
	   * @param email set email 
	   */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	   * Getter for Twitter User Name
	   * @return Twitter User Name
	   */
	public String getTwitter_username() {
		return twitter_username;
	}

	/**
	 * Setter for Twitter User Name
	 * @param twitter_username User Name
	 */
	public void setTwitter_username(String twitter_username) {
		this.twitter_username = twitter_username;
	}

	/**
	   * Getter for User Followers
	   * @return user followers
	   */
	public String getFollowers() {
		return followers;
	}

	/**
	 * Setter for User Followers
	 * @param followers user followers
	 */
	public void setFollowers(String followers) {
		this.followers = followers;
	}

	/**
	   * Getter for User Following
	   * @return user following
	   */
	public String getFollowing() {
		return following;
	}

	/**
	 * Setter for User Following
	 * @param following user following
	 */
	public void setFollowing(String following) {
		this.following = following;
	}

	/**
	   * Getter for User Subscriptions
	   * @return user subscriptions
	   */
	public String getSubscriptions_url() {
		return subscriptions_url;
	}

	/**
	 * Setter for User Subscriptions
	 * @param subscriptions_url user subscriptions
	 */
	public void setSubscriptions_url(String subscriptions_url) {
		this.subscriptions_url = subscriptions_url;
	}

	/**
	   * Getter for User Organizations
	   * @return user organizations
	   */
	public String getOrganizations_url() {
		return organizations_url;
	}

	/**
	 * Setter for User Organizations
	 * @param organizations_url user organizations
	 */
	public void setOrganizations_url(String organizations_url) {
		this.organizations_url = organizations_url;
	}

	/**
	   * Getter for User Name
	   * @return user name
	   */	 
	public String getLogin() {
		return login;
	}
	
	/**
	 * Setter for User Name
	 * @param login user name
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	
	/**
	   * Getter for User Id
	   * @return user id
	   */
	public String getId() {
		return id;
	}
	
	/**
	 * Setter for User Id
	 * @param id user id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	   * Getter for Node Id
	   * @return node id
	   */
	public String getNode_id() {
		return node_id;
	}
	
	/**
	 * Setter for Node Id
	 * @param node_id Node Id
	 */
	public void setNode_id(String node_id) {
		this.node_id = node_id;
	}
	
	/**
	   * Getter for User Avatar Url
	   * @return user avatar url
	   */
	public String getAvatar_url() {
		return avatar_url;
	}
	
	/**
	 * Setter for User Avatar Url
	 * @param avatar_url user avatar url
	 */
	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}
	
	/**
	   * Getter for User Repository Url
	   * @return user repository url
	   */
	public String getRepos_url() {
		return repos_url;
	}
	
	/**
	 * Setter for User Repository Url
	 * @param repos_url user repository
	 */
	public void setRepos_url(String repos_url) {
		this.repos_url = repos_url;
	}

}
