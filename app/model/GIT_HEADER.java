package model;

public enum GIT_HEADER {
   CONTENT_TYPE("Content-Type"),
   ACCEPT("Accept");
   
   public final String value;
	
	private GIT_HEADER(String value) {
		this.value = value;
	}
}
