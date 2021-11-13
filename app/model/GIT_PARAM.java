package model;

public enum GIT_PARAM {
	QUERY("q"),
	PER_PAGE("per_page"),
	PAGE("page"),
	SORT("sort");
	
    public final String value;
	
	private GIT_PARAM(String value) {
		this.value = value;
	}
}
