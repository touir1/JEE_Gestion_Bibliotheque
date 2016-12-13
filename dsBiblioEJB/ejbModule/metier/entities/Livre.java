package metier.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table (name="LIVRE")

public class Livre implements Serializable {

	private static final long serialVersionUID = -7607777551940094080L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column (name="ID_livre")
	private Long ID_livre;
	
	@Column(name="nom_livre")
	private String nomLivre;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATE_apparition")
	private Date dateApparition;
	
	private double prix;
	
	@JsonIgnore
	@OneToMany(mappedBy= "livre", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
	private Set<LigneCommande> ligneCommandes;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "livres", fetch = FetchType.LAZY)
	private Set<Promotion> promotions;
	
	@ManyToOne
	private Auteur auteur;

	@JsonIgnore
	@ManyToMany(fetch=FetchType.LAZY)
	private Set<TypeLivre> types;
	
	@JsonIgnore
	@OneToMany(mappedBy="livre", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
	private Set<Vote> votes;
	
	@ManyToOne
	private Editeur editeur;

	public Editeur getEditeur() {
		return editeur;
	}

	public void setEditeur(Editeur editeur) {
		this.editeur = editeur;
	}

	@XmlTransient
	public Set<Vote> getVotes() {
		return votes;
	}

	public void setVotes(Set<Vote> votes) {
		this.votes = votes;
	}

	@XmlTransient
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

	@XmlTransient
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
	
	@XmlTransient
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
