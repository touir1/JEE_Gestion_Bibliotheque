package metier.entities;

import java.io.Serializable;

public class LigneCommandePK implements Serializable{
	
	private static final long serialVersionUID = -819763680193954907L;
	
	private Long num_commande;
	private Long ID_livre;
	
	public Long getNum_commande() {
		return num_commande;
	}

	public void setNum_commande(Long num_commande) {
		this.num_commande = num_commande;
	}

	public Long getID_livre() {
		return ID_livre;
	}

	public void setID_livre(Long iD_livre) {
		ID_livre = iD_livre;
	}

	public LigneCommandePK() {
		super();
	}

	public LigneCommandePK(Long num_commande, Long iD_livre) {
		super();
		this.num_commande = num_commande;
		ID_livre = iD_livre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID_livre == null) ? 0 : ID_livre.hashCode());
		result = prime * result + ((num_commande == null) ? 0 : num_commande.hashCode());
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
		LigneCommandePK other = (LigneCommandePK) obj;
		if (ID_livre == null) {
			if (other.ID_livre != null)
				return false;
		} else if (!ID_livre.equals(other.ID_livre))
			return false;
		if (num_commande == null) {
			if (other.num_commande != null)
				return false;
		} else if (!num_commande.equals(other.num_commande))
			return false;
		return true;
	}
	
	
}
