package clientWeb_JSF;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import metier.entities.Client;
import metier.entities.Compte;
import metier.entities.Livre;
import metier.sessions.IBiblioRemote;

@ManagedBean
@SessionScoped
public class MB implements Serializable{
	
	@EJB
	private IBiblioRemote metier;
	
	private String test;
	private List<Livre> promoLivres;
	private int nb_promoLivres;
	private Livre first;
	private int[] nb;
	private String login;
	private String password;
	private String nom;
	private String prenom;
	private String numero;
	private String email;
	private String adresse;
	private String _login;
	private String _password;
	private String _password2;
	private Client client;
	private Compte compte;
	
	private String connectionError;
	private String inscriptionError;
	private String reussite;
	private String log;
	
	public String getLog(){
		return this.log;
	}
	
	public String getTest(){
		return test;
	}
	
	public void setTest(String t){
		this.test=t;
	}
	
	public List<Livre> getPromoLivres(){
		return promoLivres;
	}
	
	public int getNb_promoLivres() {
		return nb_promoLivres;
	}
	
	public int[] getNb(){
		return nb;
	}
	
	public Livre getFirst(){
		return first;
	}
	
	public String getLogin(){
		return this.login;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public void setLogin(String login){
		this.login = login;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getConnectionError(){
		return this.connectionError;
	}
	
	
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String get_login() {
		return _login;
	}

	public void set_login(String _login) {
		this._login = _login;
	}

	public String get_password() {
		return _password;
	}

	public void set_password(String _password) {
		this._password = _password;
	}

	public String getInscriptionError() {
		return inscriptionError;
	}

	public String getReussite() {
		return reussite;
	}
	
	public String get_password2(){
		return _password2;
	}
	
	public void set_password2(String _password2){
		this._password2 = _password2;
	}

	private boolean isPhoneNumber(String s){
		if(s==null || s.equals("") || s.length()!=8)
			return false;
		try{
			long n=Integer.parseInt(s);
			if(n>99999999 || n<9999999)
				return false;
		}
		catch(Exception e){
			return false;
		}
		return true;
	}
	
	public String Connection(){
		//log+=" in Connection function<br>";
		if(login.equals("") || password.equals("") || login==null || password == null){
			connectionError ="Veuillez remplir tout les champs";
			return "Accueil.xhtml?faces-redirect=true";
			//log+=" login ou pass empty<br>";
		}
		else{
			//log+=" login and pass not empty<br>";
			//log+=" login:"+login+"<br>";
			//log+=" password:"+password+"<br>";
			compte = metier.identification(login, password);
			if(compte==null){
				connectionError = "nom d'utilisateur ou mot de passe incorrect";
				return "Accueil.xhtml?faces-redirect=true";
			}
			else{
				return "Principal.xhtml?faces-redirect=true";
			}
		}
	}
	
	public String Inscription(){
		//mail
		Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
												+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		if(email==null || email.equals("")){
			inscriptionError = "Veuillez remplir tout les champs";
			vider();
			return "Accueil.xhtml";
		}
		
		if(adresse==null || adresse.equals("")){
			inscriptionError = "Veuillez remplir tout les champs";
			vider();
			return "Accueil.xhtml";
		}
		
		if(nom==null || nom.equals("")){
			inscriptionError = "Veuillez remplir tout les champs";
			vider();
			return "Accueil.xhtml";
		}
		
		if(prenom==null || prenom.equals("")){
			inscriptionError = "Veuillez remplir tout les champs";
			vider();
			return "Accueil.xhtml";
		}
		
		if(_login==null || _login.equals("")){
			inscriptionError = "Veuillez remplir tout les champs";
			vider();
			return "Accueil.xhtml";
		}
		
		if(_password==null || _password.equals("")){
			inscriptionError = "Veuillez remplir tout les champs";
			vider();
			return "Accueil.xhtml";
		}
		
		if(_password2==null || _password2.equals("")){
			inscriptionError = "Veuillez remplir tout les champs";
			vider();
			return "Accueil.xhtml";
		}
		
		if(numero == null || numero.equals("")){
			inscriptionError = "Veuillez remplir tout les champs";
			vider();
			return "Accueil.xhtml";
		}
		
		if(!isPhoneNumber(numero)){
			inscriptionError = "Numero incorrecte";
			vider();
			return "Accueil.xhtml";
		}
		
		if (!pattern.matcher(email).matches()) {
	        inscriptionError = "Email incorrect";
	        vider();
	        return "Accueil.xhtml";
	    }
		
		if(!_password2.equals(_password)){
			inscriptionError = "password incorrect";
			vider();
	        return "Accueil.xhtml";
		}
		
		Compte c = new Compte();
		
		
		client.setEmail(email);
		client.setNom(nom);
		client.setPrenom(prenom);
		client.setTel(numero);
		client.setAdresse(adresse);
		
		metier.ajouterClient(client);
		
		Client cc = metier.consulterClient(client);
		
		c.setLogin(_login);
		c.setPassword(_password);
		c.setClient(cc);
		metier.ajouterCompte(c);
		
		reussite = "Le compte a été créer";
		vider();
		return "Accueil.xhtml";
	}

	private void vider(){
		_password="";
		_password2="";
		_login="";
		nom="";
		prenom="";
		email="";
		adresse="";
		numero="";
		login="";
		password="";
	}
	
	@PostConstruct
	public void init(){
		log="";
		login ="";
		password = "";
		connectionError = "";
		inscriptionError = "";
		reussite="";
		client = new Client();
		List<Livre> temp = metier.consulterLivres();
		ArrayList<Livre> l= new ArrayList<Livre>();
		if(temp.size()>0)
			first = temp.get(0);
		for(int i=1;i<temp.size();i++){
			l.add(temp.get(i));
		}
		promoLivres = l;
		nb_promoLivres=promoLivres.size()+1;
		if(nb_promoLivres>1)
			nb = new int[nb_promoLivres-1];
		else
			nb=new int[1];
	}
}
