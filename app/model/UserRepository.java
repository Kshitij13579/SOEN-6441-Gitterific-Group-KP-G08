package model;

public class UserRepository {
	
	public String login;
    public String id;
    public String name;
	
    public UserRepository() {
		this.id = "";
		this.name = "";
		this.login = "";
	}
    
    public UserRepository(String login, String id, String name) {
		this.id = id;
		this.name = name;
		this.login = login;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
