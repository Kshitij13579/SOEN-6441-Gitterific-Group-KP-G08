package model;

import java.util.ArrayList;

/**
 * This is the Model for Repository details based on search criteria
 * @author Kshitij
 * @version 1.0
 *
 */
public class Repository {
	
    /**
     * User Name of Repository Owner
     */
    public String login;
    /**
     * Name of Repository
     */
    public String name;
    /**
     * 
     * Repository Issues URL
     */
    public String issues_url;
    /**
     * Repository Commits URL
     */
    public String commits_url;
    /**
     * List of Topics
     */
    public ArrayList<String> topics;
    
    /**
     * Empty Constructor of Repository Model
     */
    public Repository() {
    	this.login ="";
    	this.name ="";
    	this.issues_url ="";
    	this.commits_url ="";
    	this.topics = new ArrayList<String>();
    }
    
    /**
     * 
     * Constructor to initialize Repository Model
     * @param login User Name of Repository
     * @param name Name of Repository
     * @param issues_url URL to issues
     * @param commits_url URL to commit
     * @param topics List of topics
     */
    public Repository(String login,String name,String issues_url,String commits_url, ArrayList<String> topics) {
    	this.login = login;
    	this.name = name;
    	this.commits_url = commits_url;
    	this.issues_url = issues_url;
    	this.topics = topics;
    }

    /**
     * This method add repository details to list of Repositories
     * * 
     * @param login User Name of Repository
     * @param name Name of Repository
     * @param issues_url URL to issues
     * @param commits_url URL to commit
     * @param topics List of topics
     * @return List of Repository
     */
    public ArrayList<Repository> Repository(String login,String name,String issues_url,String commits_url, ArrayList<String> topics){
    	ArrayList<Repository> repos= new ArrayList<Repository>();
    	repos.add(new Repository(login,name,issues_url,commits_url, topics));
		return repos ;
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
	 * Getter for repository name
	 * @return repository name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for repository name
	 * @param name repository name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for Issues URL
	 * @return Issues URL
	 */
	public String getIssues_url() {
		return issues_url;
	}

	/**
	 * Setter for Issues URL
	 * @param issues_url Issues URL
	 */
	public void setIssues_url(String issues_url) {
		this.issues_url = issues_url;
	}

	/** Getter for Commits URL
	 * @return commits URL
	 */
	public String getCommits_url() {
		return commits_url;
	}

	/**
	 * Setter for commits URL
	 * @param commits_url commits URL
	 */
	public void setCommits_url(String commits_url) {
		this.commits_url = commits_url;
	}
	
	/**
	 * Getter for Topics
	 * @return list of topics
	 */
	public ArrayList<String> getTopics() {
		return this.topics;
	}

	/** Setter for topics
	 * @param topics list of topics
	 */
	public void setTopics(ArrayList<String> topics) {
		this.topics = topics;
	}
}

