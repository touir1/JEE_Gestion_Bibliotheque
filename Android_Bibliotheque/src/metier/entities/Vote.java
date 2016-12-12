package metier.entities;

import java.io.Serializable;

public class Vote implements Serializable{
	
	private static final long serialVersionUID = -115882393019579209L;

	private Long id;
	
	private Long score;
	private String comment;
	
	private Client client;
	
	private Livre livre;
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Livre getLivre() {
		return livre;
	}

	public void setLivre(Livre livre) {
		this.livre = livre;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getScore() {
		return score;
	}
	
	public void setScore(Long score) {
		this.score = score;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public Vote(Long score, String comment) {
		super();
		this.score = score;
		this.comment = comment;
	}
	
	public Vote() {
		super();
	}
	
}
