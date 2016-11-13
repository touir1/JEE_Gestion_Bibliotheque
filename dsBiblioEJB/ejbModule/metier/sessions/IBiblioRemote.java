package metier.sessions;

import java.util.List;

import javax.ejb.Remote;

import metier.entities.Auteur;
import metier.entities.Livre;
import metier.entities.Promotion;
import metier.entities.TypeLivre;

@Remote
public interface IBiblioRemote {
	public void addLivre(Livre l);
	public List<Livre> consulterLivres();
	public Livre consulterLivre(Long id);
	public List<Livre> consulterLivres(Auteur auteur);
	public List<Livre> consulterLivres(TypeLivre type);
	public List<Livre> consulterLivresEnPromotion();
	public void updateLivre(Livre l);
	public void supprimerLivre(Long id);
	
	public void addPromotion(Promotion p);
	public List<Promotion> consulterPromotions();
	public Promotion consulterPromotion(Long id);
	public void updatePromotion(Promotion P);
	public void supprimerPromotion(Long id);
	
	public void addAuteur(Auteur auteur);
	public List<Auteur> consulterAuteurs();
	public List<Auteur> consulterAuteurs(String nom);
	public Auteur consulterAuteur(Long id);
	public void updateAuteur(Auteur auteur);
	public void supprimerAuteur(Long id);
	
	public void addTypeLivre(TypeLivre type);
	public List<TypeLivre> consulterTypeLivres();
	public TypeLivre consulterTypeLivre(Long id);
	public void updateTypeLivre(TypeLivre type);
	public void supprimerTypeLivre(Long id);
}
