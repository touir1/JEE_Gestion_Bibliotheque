package metier.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="COMMANDE")
public class Commande implements Serializable{
	
	private static final long serialVersionUID = 2239125052227981235L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_commande")
	private Long num_commande;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATE_commande")
	private Date dateCommande;
	
	private String modePayement;
	
	@ManyToOne
	Client client;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy= "commande")
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

	public String getModePayement() {
		return modePayement;
	}

	public void setModePayement(String modePayement) {
		this.modePayement = modePayement;
	}

	public Commande() {
		super();
	}

	public Commande(Date dateCommande, String modePayement) {
		super();
		this.dateCommande = dateCommande;
		this.modePayement = modePayement;
	}
	
	
}
