package model;

import java.util.List;

/**
* The CommitStat model class is used to store statistical information of all Commits
* retrieved from Github API.
* It has the following information
* 1. top_committers - top 10 committers list
* 2. avg_additions - average additions for commits
* 3. avg_deletions - average deletions for commits
* 4. max_additions - maximum additions for commits
* 5. max_deletions - maximum deletions for commits
* 6. min_additions - minimum additions for commits
* 7. min_deletions - minimum deletion for commits
* 8. repository
* 
* @author  Kshitij Yerande
* @version 1.0
* @since   2021-11-20 
*/
public class CommitStat {
    public List<Author> top_committers;
    public double avg_additions;
    public double avg_deletions;
    public int max_additions;
    public int max_deletions;
    public int min_additions;
    public int min_deletions;
    public String repository;
    
	public CommitStat() {
		
	}

	public CommitStat(List<Author> top_committers, double avg_additions, double avg_deletions, int max_additions,
			int max_deletions,int min_additions,int min_deletions,String repository) {

		this.top_committers = top_committers;
		this.avg_additions = avg_additions;
		this.avg_deletions = avg_deletions;
		this.max_additions = max_additions;
		this.max_deletions = max_deletions;
		this.min_additions = min_additions;
		this.min_deletions = min_deletions;
		this.repository    = repository;
	}
	
	/**
	 * This method gets minimum number of additions for commit.
	 * @return int returns minimum number of additions
	 */
	public int getMin_additions() {
		return min_additions;
	}
    
	/**
	 * This method sets minimum number of additions for commit.
	 * @param int sets minimum number of additions
	 */
	public void setMin_additions(int min_additions) {
		this.min_additions = min_additions;
	}
	
	/**
	 * This method returns minimum number of deletions for commit.
	 * @return int return minimum number of deletions
	 */
	public int getMin_deletions() {
		return min_deletions;
	}
    
	/**
	 * This method sets minimum number of deletions for commit.
	 * @param int sets minimum number of deletions
	 */
	public void setMin_deletions(int min_deletions) {
		this.min_deletions = min_deletions;
	}
	
	/**
	 * This method return repository name  of a commit.
	 * @return String repository name
	 */
	public String getRepository() {
		return repository;
	}
	
	/**
	 * This method sets repository name  of a commit.
	 * @param String repository name
	 */
	public void setRepository(String repository) {
		this.repository = repository;
	}
    
	/**
	 * This method return list of authors.
	 * @return List<Author> return list of authors.
	 */
	public List<Author> getTop_committers() {
		return top_committers;
	}
    
	/**
	 * This method sets top committers.
	 * @param top_committers sets list of authors
	 */
	public void setTop_committers(List<Author> top_committers) {
		this.top_committers = top_committers;
	}
    
	/**
	 * This method gets average additions for commits
	 * @return double returns average additions for commits
	 */
	public double getAvg_additions() {
		return avg_additions;
	}
    
	/**
	 * This method sets average additions for commits
	 * @param double sets average additions for commits
	 */
	public void setAvg_additions(double avg_additions) {
		this.avg_additions = avg_additions;
	}
    
	 
		/**
		 * This method gets average deletions for commits
		 * @return double returns average deletions for commits
		 */
	public double getAvg_deletions() {
		return avg_deletions;
	}
    
	 
		/**
		 * This method sets average deletions for commits
		 * @param double sets average deletions for commits
		 */
	public void setAvg_deletions(double avg_deletions) {
		this.avg_deletions = avg_deletions;
	}
    
	/**
	 * This method gets maximum additions for commits
	 * @return int returns maximum additions for commits
	 */
	public int getMax_additions() {
		return max_additions;
	}
    

	/**
	 * This method sets maximum additions for commits
	 * @param int sets maximum additions for commits
	 */
	public void setMax_additions(int max_additions) {
		this.max_additions = max_additions;
	}
    
	/**
	 * This method gets maximum deletions for commits
	 * @param int gets maximum deletions for commits
	 */
	public int getMax_deletions() {
		return max_deletions;
	}
    
	/**
	 * This method sets maximum deletions for commits
	 * @param int sets maximum deletions for commits
	 */
	public void setMax_deletions(int max_deletions) {
		this.max_deletions = max_deletions;
	}

}
