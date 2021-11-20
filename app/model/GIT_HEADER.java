package model;

/**
* The GIT_HEADER class stores ENUM for headers for Github API request
* 1. Content-Type
* 2. Accept
* 
* @author  Kshitij Yerande
* @version 1.0
* @since   2021-11-20 
*/
public enum GIT_HEADER {
   CONTENT_TYPE("Content-Type"),
   ACCEPT("Accept");

   public final String value;
	
	private GIT_HEADER(String value) {
		this.value = value;
	}
}
