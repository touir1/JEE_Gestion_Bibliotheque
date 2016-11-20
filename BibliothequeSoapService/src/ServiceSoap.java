
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import metier.entities.Livre;
import metier.sessions.IBiblioLocal;

@Stateless
@WebService
public class ServiceSoap {
	@EJB
	private IBiblioLocal metier;
	
	@WebMethod
	public List<Livre> consulterLivres(){
		return metier.consulterLivres();
	}
	
	@WebMethod
	public Livre consulterLivre(@WebParam(name="Id")Long ID_livre){
		return metier.consulterLivre(ID_livre);
	}
}
