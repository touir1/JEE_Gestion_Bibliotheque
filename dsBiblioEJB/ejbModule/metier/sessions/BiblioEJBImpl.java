package metier.sessions;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.*;

import metier.entities.Auteur;
import metier.entities.Client;
import metier.entities.Commande;
import metier.entities.Livre;
import metier.entities.Panier;
import metier.entities.Promotion;
import metier.entities.TypeLivre;

@Stateless(name="dsBiblioEJB")
public class BiblioEJBImpl implements IBiblioRemote,IBiblioLocal{
	@PersistenceContext(unitName="dsBiblio")
	private EntityManager E;
	
	@Override
	public Commande enregistrerCommande(Panier p, Client c) {
		E.persist(c);
		Commande cm = new Commande();
		cm.setClient(c);
		cm.setLigneCommandes(p.getItems());
		E.persist(cm);
		return cm;
	}

	@Override
	public void addLivre(Livre l) {
		E.persist(l);
	}

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

	@Override
	public List<TypeLivre> consulterTypeLivres() {
		Query q=E.createQuery("select T from type_livre T");
		return q.getResultList();
	}
	
	public List<TypeLivre> consulterTypeLivresByNom(String nom){
		Query q=E.createQuery("select T from type_livre T where T.nom LIKE '%"+nom+"%'");
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

	@Override
	public List<Auteur> consulterAuteurs() {
		Query q=E.createQuery("select A from AUTEUR A");
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

	@Override
	public List<Livre> consulterLivresEnPromotion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Auteur> consulterAuteursByNom(String nom) {
		Query q=E.createQuery("select A from AUTEUR A where A.nom LIKE '%"+nom+"%'");
		return q.getResultList();
	}
	
}
