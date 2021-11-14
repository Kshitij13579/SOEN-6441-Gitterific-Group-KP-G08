package model;

public class Commit {
   public Author author;
   public String sha;
   public int additions;
   public int deletions;
   
   public Commit(Author author,String sha,int additions,int deletions) {
	   this.author = author;
	   this.sha = sha;
	   this.additions = additions;
	   this.deletions = deletions;
   }
   
   public Commit() {
	   
   }

	public Author getAuthor() {
		return author;
	}
	
	public void setAuthor(Author author) {
		this.author = author;
	}
	
	public String getSha() {
		return sha;
	}
	
	public void setSha(String sha) {
		this.sha = sha;
	}
	
	public int getAdditions() {
		return additions;
	}
	
	public void setAdditions(int additions) {
		this.additions = additions;
	}

	public int getDeletions() {
		return deletions;
	}
	
	public void setDeletions(int deletions) {
		this.deletions = deletions;
	};
}
