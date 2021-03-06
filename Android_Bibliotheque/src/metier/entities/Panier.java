package metier.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Panier implements Serializable{
	
	private static final long serialVersionUID = -1225128645920871786L;
	
	private Map<Long,LigneCommande>
	items = new HashMap<Long, LigneCommande>();

	public Collection<LigneCommande> getItems() {
		return items.values();
	}

	public void setItems(Map<Long, LigneCommande> items) {
		this.items = items;
	}

	public Panier() {
		super();
	}

	public Panier(Map<Long, LigneCommande> items) {
		super();
		this.items = items;
	}
	
	public void addArticle(Livre l,int quantite,Client c){
		if(items.get(l.getID_livre())==null){
			LigneCommande lc = new LigneCommande();
			lc.setCommande(new Commande());
			lc.getCommande().setClient(c);
			lc.setLivre(l);
			lc.setPrix(l.getPrix());
			lc.setQuantite(quantite);
			items.put(l.getID_livre(), lc);
		}
		else{
			LigneCommande lc = items.get(l.getID_livre());
			lc.setQuantite(lc.getQuantite()+quantite);
			items.put(l.getID_livre(), lc);
		}
	}
	
	public void changeArticle(Long id,int quantite){
		LigneCommande lc = new LigneCommande();
		Livre l = items.get(id).getLivre();
		lc.setLivre(l);
		lc.setPrix(l.getPrix());
		lc.setQuantite(quantite);
		items.put(id, lc);
	}
	
	public int getSize(){
		return items.size();
	}
	
	public double getTotal(){
		double total=0;
		for(LigneCommande lc : items.values()){
			total+=lc.getPrix()*lc.getQuantite();
		}
		return total;
	}
	
	public void deleteItem(Long ID_livre){
		items.remove(ID_livre);
	}
}
