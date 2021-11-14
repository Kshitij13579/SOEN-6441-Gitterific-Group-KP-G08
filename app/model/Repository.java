package model;

import java.util.ArrayList;

public class Repository {
    public String login;
    public String name;
    public String issues_url;
    public String commits_url;
    public ArrayList<String> topics;
    
    public Repository() {
    	this.login ="";
    	this.name ="";
    	this.issues_url ="";
    	this.commits_url ="";
    	this.topics = new ArrayList<String>();
    }
    
    public Repository(String login,String name,String issues_url,String commits_url, ArrayList<String> topics) {
    	this.login = login;
    	this.name = name;
    	this.commits_url = commits_url;
    	this.issues_url = issues_url;
    	this.topics = topics;
    }

    public ArrayList<Repository> Repository(String login,String name,String issues_url,String commits_url, ArrayList<String> topics){
    	ArrayList<Repository> repos= new ArrayList<Repository>();
    	repos.add(new Repository(login,name,issues_url,commits_url, topics));
		return repos ;
    }
    
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIssues_url() {
		return issues_url;
	}

	public void setIssues_url(String issues_url) {
		this.issues_url = issues_url;
	}

	public String getCommits_url() {
		return commits_url;
	}

	public void setCommits_url(String commits_url) {
		this.commits_url = commits_url;
	}
	
	public ArrayList<String> getTopics() {
		return this.topics;
	}

	public void setTopics(ArrayList<String> topics) {
		this.topics = topics;
	}
}
