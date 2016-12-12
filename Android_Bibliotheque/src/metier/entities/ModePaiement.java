package metier.entities;

import java.io.Serializable;
import java.util.Set;

public class ModePaiement implements Serializable{
	
	private static final long serialVersionUID = 4251022402963810046L;

	private Long id;
	
	private String name = "";
	
	private boolean active;
	
	private Set<Commande> commandes;
	
	public ModePaiement(String name,boolean active){
		super();
		this.name = name;
		this.active = active;
	}
	
	public ModePaiement(String name){
		super();
		this.name = name;
		this.active = false;
	}
	
	public ModePaiement(){
		super();
	}

	public Set<Commande> getCommandes() {
		return commandes;
	}

	public void setCommandes(Set<Commande> commandes) {
		this.commandes = commandes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
