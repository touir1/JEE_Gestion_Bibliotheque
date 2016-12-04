package metier.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="CLIENT")
public class Client implements Serializable{
	
	private static final long serialVersionUID = -91957790878386372L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID_client")
	private Long num_client;
	
	private String nom;
	private String prenom;
	private String adresse;
	private String email;
	private String tel;
	
	@JsonIgnore
	@OneToMany(mappedBy="client",cascade = CascadeType.REMOVE)
	private Set<Vote> votes;
	
	@OneToOne(mappedBy = "client",cascade = CascadeType.REMOVE)
	private Compte compte;
	
	@JsonIgnore
	@OneToMany(mappedBy ="client",cascade = CascadeType.REMOVE)
	private Set<Commande> commandes;
	
	public Client(String nom, String prenom, String adresse, String email, String tel, Compte compte) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.email = email;
		this.tel = tel;
		this.compte = compte;
	}
	public Client(String nom, String prenom, String adresse, String email, String tel) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.email = email;
		this.tel = tel;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@XmlTransient
	public Set<Commande> getCommandes() {
		return commandes;
	}
	public void setCommandes(Set<Commande> commandes) {
		this.commandes = commandes;
	}
	public Long getNum_client() {
		return num_client;
	}
	public void setNum_client(Long num_client) {
		this.num_client = num_client;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Compte getCompte() {
		return compte;
	}
	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	@XmlTransient
	public Set<Vote> getVotes() {
		return votes;
	}
	public void setVotes(Set<Vote> votes) {
		this.votes = votes;
	}
	public Client() {
		super();
	}
	
	public Client(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}

	
	
}
