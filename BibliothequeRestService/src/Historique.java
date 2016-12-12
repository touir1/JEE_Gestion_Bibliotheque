import java.io.Serializable;

public class Historique implements Serializable {

	private static final long serialVersionUID = 4586079559897591574L;

	private String dateCommande;
	private String modePaiement;
	private int quantite;
	private double prixUnitaire;
	private String nomLivre;
	private Long idLivre;

	public String getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(String dateCommande) {
		this.dateCommande = dateCommande;
	}

	public String getModePaiement() {
		return modePaiement;
	}

	public void setModePaiement(String modePaiement) {
		this.modePaiement = modePaiement;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public double getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public String getNomLivre() {
		return nomLivre;
	}

	public void setNomLivre(String nomLivre) {
		this.nomLivre = nomLivre;
	}

	public Long getIdLivre() {
		return idLivre;
	}

	public void setIdLivre(Long idLivre) {
		this.idLivre = idLivre;
	}

}