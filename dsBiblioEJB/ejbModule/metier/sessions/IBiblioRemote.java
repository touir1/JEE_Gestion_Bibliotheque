package metier.sessions;

import java.util.List;

import javax.ejb.Remote;

import metier.entities.Auteur;
import metier.entities.Client;
import metier.entities.Commande;
import metier.entities.Compte;
import metier.entities.Editeur;
import metier.entities.LigneCommande;
import metier.entities.Livre;
import metier.entities.ModePaiement;
import metier.entities.Panier;
import metier.entities.Promotion;
import metier.entities.TypeLivre;
import metier.entities.Vote;

@Remote
public interface IBiblioRemote {
	public void addLivre(Livre l);
	public List<Livre> consulterLivres();
	public Livre consulterLivre(Long id);
	public List<Livre> consulterLivresByAuteur(Auteur auteur);
	public List<Livre> consulterLivresByType(TypeLivre type);
	public List<Livre> consulterLivresByPromotion(Promotion promo);
	public List<Livre> consulterLivresEnPromotion();
	public void updateLivre(Livre l);
	public void supprimerLivre(Long id);
	
	public void addAuteur(Auteur auteur);
	public List<Auteur> consulterAuteurs();
	public List<Auteur> consulterAuteursByNom(String nom);
	public Auteur consulterAuteur(Long id);
	public void updateAuteur(Auteur auteur);
	public void supprimerAuteur(Long id);
	
	public void addTypeLivre(TypeLivre type);
	public List<TypeLivre> consulterTypeLivres();
	public List<TypeLivre> consulterTypeLivresByNom(String nom);
	public TypeLivre consulterTypeLivre(Long id);
	public List<TypeLivre> consulterTypeByLivre(Livre l);
	public void updateTypeLivre(TypeLivre type);
	public void supprimerTypeLivre(Long id);
	
	public void ajouterClient(Client client);
	public Client consulterClient(Long id);
	public Client consulterClient(Client client);
	public List<Compte> consulterClients();
	public void updateClient(Client client);
	public void supprimerClient(Long id);
	
	public void ajouterCompte(Compte compte);
	public void supprimerCompte(String id);
	public List<Compte> consulterComptes();
	public Compte consulterCompte(Long id);
	public void modifierCompte(Compte compte);
	public Compte identification(String user,String password);
	
	public void ajouterEditeur(Editeur editeur);
	public void supprimerEditeur(Long id);
	public List<Editeur> consulterEditeurs();
	public Editeur consulterEditeur(Long id);
	public void modifierEditeur(Editeur editeur);
	
	public void ajouterVote(Vote vote);
	public void supprimerVote(Long id);
	public List<Vote> consulterVotes();
	public Vote consulterVote(Long id);
	public List<Vote> consulterVoteParLivre(Livre l);
	public Vote consulterVoteParLivreEtClient(Livre l, Client c);
	public List<Vote> consulterVoteParClient(Client c);
	public void modifierVote(Vote vote);
	
	public void ajouterModePaiement(ModePaiement mp);
	public void supprimerModePaiement(Long id);
	public List<ModePaiement> consulterModePaiements();
	public ModePaiement consulterModePaiement(Long id);
	public void modifierModePaiement(ModePaiement mp);
	
	public void ajouterPromotion(Promotion promo);
	public void modifierPromotion(Promotion promo);
	public Promotion consulterPromotion(Long id);
	public List<Promotion> consulterPromotions();
	public void supprimerPromotion(Long id);
	public List<Promotion> consulterPromotionEnCours();
	
	public Commande enregistrerCommande(Panier p, Client c, ModePaiement mode);
	public Commande consulterCommande(Long id);
	public List<Commande> consulterCommandes();
	public List<Commande> consulterCommandesByClient(Client c);
	public List<Commande> consulterCommandesByModePaiement(ModePaiement mp);
	
	public Client connexionClient(Compte compte);
	
	public boolean authentification(Compte compte);
	
	public List<LigneCommande> consulterLigneCommande(Commande c);
	
}
