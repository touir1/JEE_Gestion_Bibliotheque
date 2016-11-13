package metier.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "LIGNE_COMMANDE")
public class LigneCommande implements Serializable{
	
	private static final long serialVersionUID = -2109586081062636415L;
	
	@EmbeddedId
	private LigneCommandePK ligneCommandePk;
	private int quantite;
	private double prix;
	
	@ManyToOne
	@MapsId("num_commande")
	private Commande commande;
	
	@ManyToOne
	@MapsId("ID_livre")
	private Livre livre;
	
	public LigneCommande(int quantite, double prix, Commande commande, Livre livre) {
		super();
		this.quantite = quantite;
		this.prix = prix;
		this.commande = commande;
		this.livre = livre;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public LigneCommandePK getLigneCommandePk() {
		return ligneCommandePk;
	}

	public void setLigneCommandePk(LigneCommandePK ligneCommandePk) {
		this.ligneCommandePk = ligneCommandePk;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Livre getLivre() {
		return livre;
	}

	public void setLivre(Livre livre) {
		this.livre = livre;
	}

	public LigneCommande(Commande commande, Livre livre) {
		super();
		this.commande = commande;
		this.livre = livre;
	}

	public LigneCommande() {
		super();
	}
	
	
}
