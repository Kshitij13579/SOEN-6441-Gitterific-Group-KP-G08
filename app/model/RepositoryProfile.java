package model;


/**
 * Class - RepositoryProfile is a model to store information about repository details
 * It includes following information
 * 1. login - UserName
 * 2. id - Repository name
 * 3. Node_id - Node Id details
 * 4. Avatar URL - GIT Avatar Details
 * 5. Collaborators - Collborator Details
 * 6. Issues - Issue Details
 * 7. Open Issue -  Open Issue details
 * @author Yogesh Yadav
 * @version 1.0
 * @since   2021-11-20 
 *
 */
public class RepositoryProfile {


	public String login; 

	public String id; 

	public String node_id; 
	public String avatar_url; 
	public String collaborators_url;
	public String issues_url;
	public String open_issues;
	

	/**
	 * Constructor : to Initialize RepositoryProfile object
	 */
	public RepositoryProfile() {
		this.login = "";
		this.id = "";
		this.node_id = "";
		this.avatar_url = "";
		this.collaborators_url = "";
		this.issues_url = "";
		this.open_issues="";
	}

	/**
	 * Constructor : to Initialize RepositoryProfile object
	 * @param login
	 * @param id
	 * @param node_id
	 * @param avatar_url
	 * @param collaborators_url
	 * @param issues_url
	 * @param open_issues
	 */
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
	
	/**
	 * Method to get login details of a git Repository
	 * @return login details of Repository
	 */
	public String getLogin() {
		return login;
	}
	
	/**
	 * Method to set login details in the model RepositoryProfile for the git Repository
	 * @param login details for Repository
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	
	/**
	 * Method to get node_id details of a git Repository
	 * @return node_id details of Repository
	 */
	public String getNode_id() {
		return node_id;
	}
	
	/**
	 * Method to set node_id details in the model RepositoryProfile for the git Repository
	 * @param node_id detail of Repository
	 */
	public void setNode_id(String node_id) {
		this.node_id = node_id;
	}
	
	/**
	 * Method to get avatar_url in the model RepositoryProfile for the git Repository
	 * @return avatar_url details of Repository
	 */
	public String getAvatar_url() {
		return avatar_url;
	}
	/**
	 * method  to set avatar_url details in the model RepositoryProfile for the git Repository
	 * @param avatar_url detail of user
	 */
	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}
	
	/**
	 * Method to get collaborators_url details of a git Repository
	 * @return collaborators_url detail of Repository
	 */
	public String getcollaborators_url() {
		return collaborators_url;
	}
	/**
	 * Method to set collaborators_url details in the model RepositoryProfile for the git Repository
	 * @param collaborators_url detail of Repository
	 */
	public void setcollaborators_url(String collaborators_url) {
		this.collaborators_url = collaborators_url;
	}
	
	/**
	 * Method to get issues_url details of a git Repository
	 * @return issues_url detail of Repository
	 */
	public String getissues_url() {
		return issues_url;
	}
	/**
	 * Method to set issues_url details in the model RepositoryProfile for the git Repository
	 * @param issues_url  detail of Repository
	 */
	public void setissues_url(String issues_url) {
		this.issues_url = issues_url;
	}
	
	/**
	 * Method to get open_issues details of a git Repository
	 * @return open_issues details of Repository
	 */
	public String getopen_issues() {
		return open_issues;
	}
	/**
	 * Method to set open_issues details in the model RepositoryProfile for the git Repository
	 * @param open_issues detail of Repository
	 */
	public void setopen_issues(String open_issues) {
		this.open_issues = open_issues;
	}
}

