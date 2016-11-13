package metier.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="type_livre")
public class TypeLivre implements Serializable{
	
	private static final long serialVersionUID = -425066239559946826L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String nom;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany
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
