package metier.entities;

import java.io.Serializable;

public class Compte implements Serializable{
	
	private static final long serialVersionUID = 5891262750394012187L;

	private String login;
	
	private String password;
	
	private Client client;
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Compte() {
		super();
	}

	public Compte(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

	
	
	
}
