package metier.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="ligne_livre_promotion")
public class LigneLivrePromotion implements Serializable{
	
	private static final long serialVersionUID = 6509027718649760715L;

	@EmbeddedId
	private LigneLivrePromotionPK ligneLivrePromotionPK;
	
	private double prix_pro;
	
	@ManyToOne
	@MapsId("ID_promotion")
	private Promotion promotion;
	
	@ManyToOne
	@MapsId("ID_livre")
	private Livre livre;

	public LigneLivrePromotionPK getLigneLivrePromotionPK() {
		return ligneLivrePromotionPK;
	}

	public void setLigneLivrePromotionPK(LigneLivrePromotionPK ligneLivrePromotionPK) {
		this.ligneLivrePromotionPK = ligneLivrePromotionPK;
	}

	public double getPrix_pro() {
		return prix_pro;
	}

	public void setPrix_pro(double prix_pro) {
		this.prix_pro = prix_pro;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public Livre getLivre() {
		return livre;
	}

	public void setLivre(Livre livre) {
		this.livre = livre;
	}

	public LigneLivrePromotion(double prix_pro) {
		super();
		this.prix_pro = prix_pro;
	}

	public LigneLivrePromotion() {
		super();
	}

	public LigneLivrePromotion(double prix_pro, Promotion promotion, Livre livre) {
		super();
		this.prix_pro = prix_pro;
		this.promotion = promotion;
		this.livre = livre;
	}
	
	
}
