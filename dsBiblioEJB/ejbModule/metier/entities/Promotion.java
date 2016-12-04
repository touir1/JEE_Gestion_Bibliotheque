package metier.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="PROMOTION")
public class Promotion implements Serializable{
	
	private static final long serialVersionUID = -3793655993025452745L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_promotion")
	private Long ID_promotion;
	
	private int	 pourcentage;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATE_debut")
	private Date dateDebut;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATE_fin")
	private Date dateFin;
	
	@JsonIgnore
	@ManyToMany
	private Set<Livre> livres;

	@XmlTransient
	public Set<Livre> getLivres() {
		return livres;
	}

	public void setLivres(Set<Livre> livres) {
		this.livres = livres;
	}

	public Long getID_promotion() {
		return ID_promotion;
	}

	public void setID_promotion(Long iD_promotion) {
		ID_promotion = iD_promotion;
	}

	public int getPourcentage() {
		return pourcentage;
	}

	public void setPourcentage(int pourcentage) {
		this.pourcentage = pourcentage;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date datefin) {
		this.dateFin = datefin;
	}

	public Promotion(int pourcentage, Date dateDebut, Date datefin) {
		super();
		this.pourcentage = pourcentage;
		this.dateDebut = dateDebut;
		this.dateFin = datefin;
	}

	public Promotion() {
		super();
	}
	
	
}
