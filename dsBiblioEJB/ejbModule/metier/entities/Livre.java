package metier.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

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
	
	@OneToMany(mappedBy= "livre", fetch = FetchType.LAZY)
	private Set<LigneCommande> ligneCommandes;
	
	@OneToMany(mappedBy = "livre", fetch = FetchType.LAZY)
	private Set<LigneLivrePromotion> ligneLivrePromotions;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Auteur auteur;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<TypeLivre> types;
	
	@OneToMany(mappedBy="livre", fetch = FetchType.LAZY)
	private Set<Vote> votes;

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

	public Set<LigneLivrePromotion> getLigneLivrePromotions() {
		return ligneLivrePromotions;
	}

	public void setLigneLivrePromotions(Set<LigneLivrePromotion> ligneLivrePromotions) {
		this.ligneLivrePromotions = ligneLivrePromotions;
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

	public Livre(String nomLivre, Date dateApparition, Set<LigneCommande> ligneCommandes, double prix) {
		super();
		this.nomLivre = nomLivre;
		this.dateApparition = dateApparition;
		this.ligneCommandes = ligneCommandes;
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
