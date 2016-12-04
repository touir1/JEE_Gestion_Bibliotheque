package metier.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Mode_paiement")
public class ModePaiement implements Serializable{
	
	private static final long serialVersionUID = 4251022402963810046L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name = "";
	
	private boolean active;
	
	@JsonIgnore
	@OneToMany(mappedBy="modePaiement",cascade = CascadeType.REMOVE)
	private Set<Commande> commandes;
	
	public ModePaiement(String name,boolean active){
		super();
		this.name = name;
		this.active = active;
	}
	
	public ModePaiement(String name){
		super();
		this.name = name;
		this.active = false;
	}
	
	public ModePaiement(){
		super();
	}

	@XmlTransient
	public Set<Commande> getCommandes() {
		return commandes;
	}

	public void setCommandes(Set<Commande> commandes) {
		this.commandes = commandes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
