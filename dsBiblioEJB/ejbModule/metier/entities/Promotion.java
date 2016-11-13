package metier.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="PROMOTION")
public class Promotion implements Serializable{
	
	private static final long serialVersionUID = -3793655993025452745L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_promotion")
	private Long ID_promotion;
	
	private int pourcentage;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATE_debut")
	private Date dateDebut;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATE_fin")
	private Date datefin;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "promotion")
	private Set<LigneLivrePromotion> ligneLivrePromotions;

	public Set<LigneLivrePromotion> getLigneLivrePromotions() {
		return ligneLivrePromotions;
	}

	public void setLigneLivrePromotions(Set<LigneLivrePromotion> ligneLivrePromotions) {
		this.ligneLivrePromotions = ligneLivrePromotions;
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

	public Date getDatefin() {
		return datefin;
	}

	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}

	public Promotion(int pourcentage, Date dateDebut, Date datefin) {
		super();
		this.pourcentage = pourcentage;
		this.dateDebut = dateDebut;
		this.datefin = datefin;
	}

	public Promotion() {
		super();
	}
	
	
}
