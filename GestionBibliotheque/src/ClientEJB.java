import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;

import metier.sessions.IBiblioRemote;

public final class ClientEJB {
	
	private static Properties properties= null;
	private static Context context = null;
	private static IBiblioRemote stub = null;
	
	private ClientEJB(){}
	
	public static void initialisation(){
		//connection
		if(stub==null){
			try{
				properties = new Properties();
				properties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
				context = new InitialContext(properties);
				
				stub = (IBiblioRemote) context.lookup("ejb:WebServiceRest/dsBiblioEJB/dsBiblioEJB!metier.sessions.IBiblioRemote");
			}
			catch(Exception e){
				System.out.println("ERROR:");
				System.out.println(e.getMessage());
			}
		}
	}

	public static IBiblioRemote getStub() {
		return stub;
	}

}
