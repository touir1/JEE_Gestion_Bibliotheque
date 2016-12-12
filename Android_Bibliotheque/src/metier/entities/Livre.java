package metier.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class Livre implements Serializable {

	private static final long serialVersionUID = -7607777551940094080L;

	private Long ID_livre;
	
	private String nomLivre;
	
	private Date dateApparition;
	
	private double prix;
	
	private Set<LigneCommande> ligneCommandes;
	
	private Set<Promotion> promotions;
	
	private Auteur auteur;

	private Set<TypeLivre> types;
	
	private Set<Vote> votes;
	
	private Editeur editeur;

	public Editeur getEditeur() {
		return editeur;
	}

	public void setEditeur(Editeur editeur) {
		this.editeur = editeur;
	}

	public Set<Vote> getVotes() {
		return votes;
	}

	public void setVotes(Set<Vote> votes) {
		this.votes = votes;
	}

	public Set<TypeLivre> getTypes() {
		return types;
	}

	public void setTypes(Set<TypeLivre> types) {
		this.types = types;
	}

	public Auteur getAuteur() {
		return auteur;
	}

	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}

	public Set<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(Set<Promotion> promotions) {
		this.promotions = promotions;
	}

	public Livre(String nomLivre, double prix) {
		super();
		this.nomLivre = nomLivre;
		this.prix = prix;
	}
	
	public Livre(String nomLivre) {
		super();
		this.nomLivre = nomLivre;
	}
	
	public Livre(String nomLivre, Date dateApparition, double prix) {
		super();
		this.nomLivre = nomLivre;
		this.dateApparition = dateApparition;
		this.prix = prix;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Long getID_livre() {
		return ID_livre;
	}

	public void setID_livre(Long iD_livre) {
		ID_livre = iD_livre;
	}

	public String getNomLivre() {
		return nomLivre;
	}

	public void setNomLivre(String nom) {
		this.nomLivre = nom;
	}

	public Date getDateApparition() {
		return dateApparition;
	}

	public void setDateApparition(Date dateApparition) {
		this.dateApparition = dateApparition;
	}
	
	public Set<LigneCommande> getLigneCommandes() {
		return ligneCommandes;
	}

	public void setLigneCommandes(Set<LigneCommande> ligneCommandes) {
		this.ligneCommandes = ligneCommandes;
	}

	public Livre() {
		super();
	}
}
