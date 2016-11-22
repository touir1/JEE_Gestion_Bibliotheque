package metier.entities;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name="EDITEUR")
public class Editeur implements Serializable{

	private static final long serialVersionUID = -6056284737705602678L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_editeur")
	private Long ID_editeur;
	
	private String nom;
	
	@JsonIgnore
	@OneToMany(mappedBy="editeur")
	private Set<Livre> livres;

	@XmlTransient
	public Set<Livre> getLivres() {
		return livres;
	}

	public void setLivres(Set<Livre> livres) {
		this.livres = livres;
	}

	public Long getID_editeur() {
		return ID_editeur;
	}

	public void setID_editeur(Long iD_editeur) {
		ID_editeur = iD_editeur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Editeur(String nom) {
		super();
		this.nom = nom;
	}

	public Editeur() {
		super();
	}
	
	
}
