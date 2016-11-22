package metier.sessions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.*;

import metier.entities.Auteur;
import metier.entities.Client;
import metier.entities.Commande;
import metier.entities.Compte;
import metier.entities.Editeur;
import metier.entities.Livre;
import metier.entities.ModePaiement;
import metier.entities.Panier;
import metier.entities.Promotion;
import metier.entities.TypeLivre;
import metier.entities.Vote;

@Stateless(name="dsBiblioEJB")
public class BiblioEJBImpl implements IBiblioRemote,IBiblioLocal{
	@PersistenceContext(unitName="dsBiblio")
	private EntityManager E;
	
	@Override
	public Commande enregistrerCommande(Panier p, Client c, ModePaiement mode) {
		E.persist(c);
		Commande cm = new Commande();
		cm.setClient(c);
		cm.setLigneCommandes(p.getItems());
		cm.setDateCommande(new Date());
		cm.setModePaiement(mode);
		E.persist(cm);
		return cm;
	}

	@Override
	public void addLivre(Livre l) {
		E.persist(l);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Livre> consulterLivres() {
		Query q=E.createQuery("select L from Livre L");
		return q.getResultList();
	}

	@Override
	public Livre consulterLivre(Long id) {
		Livre l = E.find(Livre.class, id);
		return l;
	}

	@Override
	public void updateLivre(Livre l) {
		E.merge(l);
	}

	@Override
	public void supprimerLivre(Long id) {
		Livre l = consulterLivre(id);
		E.remove(l);
	}

	@Override
	public void addPromotion(Promotion p) {
		E.persist(p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Promotion> consulterPromotions() {
		Query q=E.createQuery("select P from Promotion P");
		return q.getResultList();
	}

	@Override
	public Promotion consulterPromotion(Long id) {
		Promotion p = E.find(Promotion.class, id);
		return p;
	}

	@Override
	public void updatePromotion(Promotion P) {
		E.merge(P);
	}

	@Override
	public void supprimerPromotion(Long id) {
		Promotion p = consulterPromotion(id);
		E.remove(p);
	}

	@Override
	public void addTypeLivre(TypeLivre type) {
		E.persist(type);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TypeLivre> consulterTypeLivres() {
		Query q=E.createQuery("select T from TypeLivre T");
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TypeLivre> consulterTypeLivresByNom(String nom){
		Query q=E.createQuery("select T from TypeLivre T where T.nom LIKE '%"+nom+"%'");
		return q.getResultList();
	}

	@Override
	public TypeLivre consulterTypeLivre(Long id) {
		TypeLivre t = E.find(TypeLivre.class, id);
		return t;
	}

	@Override
	public void updateTypeLivre(TypeLivre type) {
		E.merge(type);
	}

	@Override
	public void supprimerTypeLivre(Long id) {
		TypeLivre t = consulterTypeLivre(id);
		E.remove(t);
	}

	@Override
	public void addAuteur(Auteur auteur) {
		E.persist(auteur);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Auteur> consulterAuteurs() {
		Query q=E.createQuery("select A from Auteur A");
		return q.getResultList();
	}

	@Override
	public Auteur consulterAuteur(Long id) {
		Auteur a = E.find(Auteur.class, id);
		return a;
	}

	@Override
	public void updateAuteur(Auteur auteur) {
		E.merge(auteur);
	}

	@Override
	public void supprimerAuteur(Long id) {
		Auteur a = consulterAuteur(id);
		E.remove(a);
	}

	@Override
	public List<Livre> consulterLivresByAuteur(Auteur auteur) {
		List<Auteur> a = consulterAuteursByNom(auteur.getNom());
		List<Livre> retour = new ArrayList<Livre>();
		for(int i=0;i<a.size();i++){
			retour.addAll(a.get(i).getLivres());
		}
		return retour;
	}

	@Override
	public List<Livre> consulterLivresByType(TypeLivre type) {
		List<TypeLivre> tl = consulterTypeLivresByNom(type.getNom());
		List<Livre> retour = new ArrayList<Livre>();
		for(int i=0;i<tl.size();i++){
			retour.addAll(tl.get(i).getLivres());
		}
		return retour;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Livre> consulterLivresEnPromotion() {
		Query q=E.createQuery("select LP.livre from LigneLivrePromotion LP"
							  +" where CURDATE() between LP.promotion.dateDebut and LP.promotion.dateFin");
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Auteur> consulterAuteursByNom(String nom) {
		Query q=E.createQuery("select A from Auteur A where A.nom LIKE '%"+nom+"%'");
		return q.getResultList();
	}

	@Override
	public Client connexionClient(Compte compte) {
		Compte com = E.find(Compte.class, compte.getLogin());
		return com.getClient();
	}

	@Override
	public boolean authentification(Compte compte) {
		Compte com = E.find(Compte.class, compte.getLogin().toLowerCase());
		if(com!=null && com.getPassword().equals(compte.getPassword()))
			return true;
		return false;
	}

	@Override
	public void ajouterClient(Client client) {
		E.persist(client);
		
	}

	@Override
	public Client consulterClient(Long id) {
		Client c = E.find(Client.class, id);
		return c;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Compte> consulterClients() {
		Query q=E.createQuery("select C from Client C");
		return q.getResultList();
	}

	@Override
	public void updateClient(Client client) {
		E.merge(client);
		
	}

	@Override
	public void supprimerClient(Long id) {
		Client c = E.find(Client.class, id);
		E.remove(c);
	}

	@Override
	public void ajouterCompte(Compte compte) {
		E.persist(compte);
	}

	@Override
	public void supprimerCompte(String id) {
		Compte c = E.find(Compte.class, id);
		E.remove(c);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Compte> consulterComptes() {
		Query q=E.createQuery("select C from Compte C");
		return q.getResultList();
	}

	@Override
	public Compte consulterCompte(Long id) {
		Compte c = E.find(Compte.class, id);
		return c;
	}

	@Override
	public void modifierCompte(Compte compte) {
		E.merge(compte);
	}

	@Override
	public void ajouterEditeur(Editeur editeur) {
		E.persist(editeur);
	}

	@Override
	public void supprimerEditeur(Long id) {
		Editeur e = E.find(Editeur.class, id);
		E.remove(e);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Editeur> consulterEditeurs() {
		Query q = E.createQuery("select E from Editeur E");
		return q.getResultList();
	}

	@Override
	public Editeur consulterEditeur(Long id) {
		Editeur e= E.find(Editeur.class, id);
		return e;
	}

	@Override
	public void modifierEditeur(Editeur editeur) {
		E.merge(editeur);
	}

	@Override
	public void ajouterVote(Vote vote) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void supprimerVote(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Vote> consulterVotes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vote consulterVote(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vote> consulterVoteParLivre(Livre l) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vote consulterVoteParLivreEtClient(Livre l, Client c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vote> consulterVoteParClient(Client c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifierVote(Vote vote) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Commande consulterCommande(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Commande> consulterCommandes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Commande> consulterCommandesByClient() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Commande> consulterCommandesByModePaiement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ajouterModePaiement(ModePaiement mode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifierModePaiement(ModePaiement mode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ModePaiement> consulterModePaiements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModePaiement consulterModePaiement(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void supprimerModePaiement(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ajouterPromotion(Promotion promo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifierPromotion(Promotion promo) {
		// TODO Auto-generated method stub
		
	}
	
	/*@Override
	public List<Livre> test(){
		Query q=E.createQuery("select L from Livre L  where L.dateApparition <= CURDATE()");
		return q.getResultList();
	}*/
	
}
