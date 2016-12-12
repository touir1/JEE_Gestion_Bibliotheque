package metier.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TypeLivre implements Serializable{
	
	private static final long serialVersionUID = -425066239559946826L;

	private Long id;
	
	private String nom;

	private Set<Livre> livres;
	
	public Set<Livre> getLivres() {
		return livres;
	}

	public void setLivres(Set<Livre> livres) {
		this.livres = livres;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public TypeLivre(String nom) {
		super();
		this.nom = nom;
	}

	public TypeLivre() {
		super();
	}
	
}
