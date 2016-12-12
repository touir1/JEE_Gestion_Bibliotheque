package metier.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class Commande implements Serializable{
	
	private static final long serialVersionUID = 2239125052227981235L;

	private Long num_commande;
	
	private Date dateCommande;
	
	private ModePaiement modePaiement;
	
	Client client;
	
	private Set<LigneCommande> ligneCommandes;
	
	public Set<LigneCommande> getLigneCommandes() {
		return ligneCommandes;
	}

	public void setLigneCommandes(Set<LigneCommande> ligneCommandes) {
		this.ligneCommandes = ligneCommandes;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Long getNum_commande() {
		return num_commande;
	}

	public void setNum_commande(Long num_commande) {
		this.num_commande = num_commande;
	}

	public Date getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	public ModePaiement getModePaiement() {
		return modePaiement;
	}

	public void setModePaiement(ModePaiement modePaiement) {
		this.modePaiement = modePaiement;
	}

	public Commande() {
		super();
	}

	public Commande(Date dateCommande) {
		super();
		this.dateCommande = dateCommande;
	}
	
	
}
