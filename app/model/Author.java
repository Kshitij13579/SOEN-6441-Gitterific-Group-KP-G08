package model;

public class Author {
    public String name;
    public String login;
    public int commits;
    
    public Author(String name,String login,int commits) {
    	this.name = name;
    	this.login = login;
    	this.commits =commits;
    }
    
    public int getCommits() {
		return commits;
	}

	public void setCommits(int commits) {
		this.commits = commits;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	
}
