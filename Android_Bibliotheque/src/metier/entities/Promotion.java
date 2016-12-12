package metier.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class Promotion implements Serializable{
	
	private static final long serialVersionUID = -3793655993025452745L;

	private Long ID_promotion;
	
	private int	 pourcentage;
	
	private Date dateDebut;
	
	private Date dateFin;
	
	private Set<Livre> livres;

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
