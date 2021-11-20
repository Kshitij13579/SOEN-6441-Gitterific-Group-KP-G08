package model;

/**
* The GIT_PARAM class stores ENUM for parameter keys for Github API request parameters
* 1. Query - search query 
* 2. PER_PAGE - number of responses per page
* 3. page - page number of response
* 4. sort - to get sorted values of API response
* 
* @author  Kshitij Yerande , Mrinal Rai
* @version 1.1
* @since   2021-11-20 
*/
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
