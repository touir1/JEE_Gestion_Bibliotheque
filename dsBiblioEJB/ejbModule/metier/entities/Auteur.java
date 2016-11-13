package metier.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="AUTEUR")
public class Auteur implements Serializable{
	
	private static final long serialVersionUID = 5346175686021008087L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_auteur")
	private Long ID_auteur;
	
	private String nom;
	private String prenom;
	
	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@OneToMany(mappedBy="auteur", fetch = FetchType.LAZY)
	private Set<Livre> livres;

	public Set<Livre> getLivres() {
		return livres;
	}

	public void setLivres(Set<Livre> livres) {
		this.livres = livres;
	}

	public Long getID_auteur() {
		return ID_auteur;
	}

	public void setID_auteur(Long iD_auteur) {
		ID_auteur = iD_auteur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Auteur(String nom,String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}

	public Auteur() {
		super();
	}
	
	
}
