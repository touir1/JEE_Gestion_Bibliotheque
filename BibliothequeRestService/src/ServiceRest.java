import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

import metier.sessions.IBiblioLocal;
import metier.entities.Client;
import metier.entities.Commande;
import metier.entities.Compte;
import metier.entities.LigneCommande;
import metier.entities.Livre;
import metier.entities.ModePaiement;
import metier.entities.Panier;

@Stateless
@Path("/")
public class ServiceRest {
	@EJB
	private IBiblioLocal metier;
	
	@GET
	@Path(value="/livres")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Livre> consulterLivres(){
		return metier.consulterLivres();
	}
	
	@GET
	@Path(value="/livreByID/{Id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Livre consulterLivres(@PathParam(value="Id")Long ID_livre){
		return metier.consulterLivre(ID_livre);
	}
	
	@GET
	@Path(value="/test")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Livre> testing(){
		return metier.consulterLivresEnPromotion();
	}
	
	@GET
	@Path(value="/connection/{user}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public Compte connection(@PathParam(value="user")String user,@PathParam(value="password") String password){
		return metier.identification(user, password);
	}
	
	@GET
	@Path(value="/modepaiement")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ModePaiement> consulterModePaiements(){
		return metier.consulterModePaiements();
	}
	
	@GET
	@Path(value="/passerCommande/{livre}/{client}/{mode}")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean passerCommande(@PathParam(value="livre")Long _l,
								@PathParam(value="client")Long _c,
								@PathParam(value="mode") Long _m){
		try{
			Livre l = metier.consulterLivre(_l);
			Client c = metier.consulterClient(_c);
			ModePaiement m = metier.consulterModePaiement(_m);
			
			Panier p = new Panier();
			p.addArticle(l, 1, c);
			
			metier.enregistrerCommande(p, c, m);
			return Boolean.TRUE;
		}
		catch(Exception e){
			return false;
		}
	}
	
	@GET
	@Path(value="/historique/{client}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Historique> consulterHistorique(@PathParam(value="client") Long cli){
		Client client = metier.consulterClient(cli);
		List<Commande> cmd = metier.consulterCommandesByClient(client);
		List<Historique> retour = new ArrayList<Historique>();
		for(int i=0;i<cmd.size();i++){
			Commande c = cmd.get(i);
			List<LigneCommande> lc = new ArrayList<LigneCommande>(metier.consulterLigneCommande(c));
			for(int j=0;j<lc.size();j++){
				Historique h = new Historique();
				LigneCommande ligne = lc.get(j);
				h.setDateCommande(c.getDateCommande().toString());
				h.setIdLivre(ligne.getLivre().getID_livre());
				h.setModePaiement(c.getModePaiement().getName());
				h.setNomLivre(ligne.getLivre().getNomLivre());
				h.setPrixUnitaire(ligne.getPrix());
				h.setQuantite(ligne.getQuantite());
				retour.add(h);
			}
		}
		return retour;
	}
}
