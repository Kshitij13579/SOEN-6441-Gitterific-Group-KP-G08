package model;

import java.util.Objects;

public class Author {
    public String name;
    public String login;
    public int commits;
    
    public Author() {
    	
    }
    
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
	
	@Override
	public boolean equals(Object o) {
	    if (this == o)
	        return true;
	    if (o == null || getClass() != o.getClass())
	        return false;
	    Author a = (Author) o;
	    return Objects.equals(name, a.name) && Objects.equals(login, a.login) && Objects.equals(commits, a.commits);
	}

	@Override
	public int hashCode() {
	    return Objects.hash(name, login,commits);
	}

	
}
