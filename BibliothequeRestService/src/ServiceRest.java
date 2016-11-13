import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import metier.sessions.IBiblioLocal;
import metier.entities.Livre;

@Stateless
@Path("/livre")
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
	@Path(value="/livresParID/{Id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Livre consulterLivres(@PathParam(value="Id")Long ID_livre){
		return metier.consulterLivre(ID_livre);
	}
	
}
