package metier.sessions;

import java.util.List;

import javax.ejb.Local;

import metier.entities.Auteur;
import metier.entities.Client;
import metier.entities.Commande;
import metier.entities.Compte;
import metier.entities.LigneCommande;
import metier.entities.Livre;
import metier.entities.ModePaiement;
import metier.entities.Panier;
import metier.entities.Promotion;
import metier.entities.TypeLivre;
import metier.entities.Vote;

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
	
	public Commande enregistrerCommande(Panier p, Client c, ModePaiement mode);
	public Commande consulterCommande(Long id);
	public List<Commande> consulterCommandes();
	public List<Commande> consulterCommandesByClient(Client c);
	public List<Commande> consulterCommandesByModePaiement(ModePaiement mp);
	
	public void ajouterClient(Client client);
	public Client consulterClient(Long id);
	public Client connexionClient(Compte compte);
	public void updateClient(Client client);
	
	public void ajouterCompte(Compte compte);
	public boolean authentification(Compte compte);
	public void modifierCompte(Compte compte);
	
	public void ajouterVote(Vote vote);
	public void modifierVote(Vote vote);
	public Vote consulterVoteParLivreEtClient(Livre l, Client c);
	public List<Vote> consulterVoteParClient(Client c);
	
	public List<ModePaiement> consulterModePaiements();
	public ModePaiement consulterModePaiement(Long id);
	
	public Promotion consulterPromotion(Long id);
	public List<Promotion> consulterPromotions();
	
	public Compte identification(String user,String password);
	
	public List<LigneCommande> consulterLigneCommande(Commande c);
	
	//public List<Livre> test();
}
