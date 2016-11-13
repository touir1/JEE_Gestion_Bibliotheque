package metier.entities;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class LigneLivrePromotionPK  implements Serializable{
	
	private static final long serialVersionUID = 2670711729674237474L;
	
	private Long ID_livre;
	private Long ID_promotion;
	
	public Long getID_livre() {
		return ID_livre;
	}
	
	public void setID_livre(Long iD_livre) {
		ID_livre = iD_livre;
	}
	
	public Long getID_promotion() {
		return ID_promotion;
	}
	
	public void setID_promotion(Long iD_promotion) {
		ID_promotion = iD_promotion;
	}
	
	public LigneLivrePromotionPK(Long iD_livre, Long iD_promotion) {
		super();
		ID_livre = iD_livre;
		ID_promotion = iD_promotion;
	}
	
	public LigneLivrePromotionPK() {
		super();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID_livre == null) ? 0 : ID_livre.hashCode());
		result = prime * result + ((ID_promotion == null) ? 0 : ID_promotion.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LigneLivrePromotionPK other = (LigneLivrePromotionPK) obj;
		if (ID_livre == null) {
			if (other.ID_livre != null)
				return false;
		} else if (!ID_livre.equals(other.ID_livre))
			return false;
		if (ID_promotion == null) {
			if (other.ID_promotion != null)
				return false;
		} else if (!ID_promotion.equals(other.ID_promotion))
			return false;
		return true;
	}
	
	
}
