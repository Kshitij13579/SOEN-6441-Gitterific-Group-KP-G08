package model;

import java.util.List;

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
	
	public int getMin_additions() {
		return min_additions;
	}

	public void setMin_additions(int min_additions) {
		this.min_additions = min_additions;
	}
	
	public int getMin_deletions() {
		return min_deletions;
	}

	public void setMin_deletions(int min_deletions) {
		this.min_deletions = min_deletions;
	}
	
	public String getRepository() {
		return repository;
	}
	
	public void setRepository(String repository) {
		this.repository = repository;
	}

	public List<Author> getTop_committers() {
		return top_committers;
	}

	public void setTop_committers(List<Author> top_committers) {
		this.top_committers = top_committers;
	}

	public double getAvg_additions() {
		return avg_additions;
	}

	public void setAvg_additions(double avg_additions) {
		this.avg_additions = avg_additions;
	}

	public double getAvg_deletions() {
		return avg_deletions;
	}

	public void setAvg_deletions(double avg_deletions) {
		this.avg_deletions = avg_deletions;
	}

	public int getMax_additions() {
		return max_additions;
	}

	public void setMax_additions(int max_additions) {
		this.max_additions = max_additions;
	}

	public int getMax_deletions() {
		return max_deletions;
	}

	public void setMax_deletions(int max_deletions) {
		this.max_deletions = max_deletions;
	}

}
