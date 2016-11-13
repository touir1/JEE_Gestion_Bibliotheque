package metier.sessions;

import java.util.List;

import javax.ejb.Local;

import metier.entities.Auteur;
import metier.entities.Client;
import metier.entities.Commande;
import metier.entities.Compte;
import metier.entities.Livre;
import metier.entities.Panier;
import metier.entities.TypeLivre;

@Local
public interface IBiblioLocal {
	public List<Livre> consulterLivres();
	public Livre consulterLivre(Long id);
	public List<Livre> consulterLivresByAuteur(Auteur auteur);
	public List<Livre> consulterLivresByType(TypeLivre type);
	public List<Livre> consulterLivresEnPromotion();
	
	public List<TypeLivre> consulterTypeLivres();
	public List<TypeLivre> consulterTypeLivresByNom(String nom);
	public TypeLivre consulterTypeLivre(Long id);
	
	public List<Auteur> consulterAuteurs();
	public List<Auteur> consulterAuteursByNom(String nom);
	public Auteur consulterAuteur(Long id);
	
	public Commande enregistrerCommande(Panier p, Client c);
	
	//public void ajouterClient(Client client);
	//public void updateClient(Client client);
	//public void supprimerClient(Client client);
	
	//public void ajouterCompte(Compte compte);
	//public void supprimerCompte(Compte compte);
}
