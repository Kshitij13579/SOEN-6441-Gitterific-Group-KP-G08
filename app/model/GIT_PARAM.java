package model;

public enum GIT_PARAM {
	QUERY("q"),
	PER_PAGE("per_page"),
	PAGE("page");
	
    public final String value;
	
	private GIT_PARAM(String value) {
		this.value = value;
	}
}
