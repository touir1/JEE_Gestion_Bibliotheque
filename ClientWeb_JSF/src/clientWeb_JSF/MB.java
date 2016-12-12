package clientWeb_JSF;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import metier.entities.Client;
import metier.entities.Commande;
import metier.entities.Compte;
import metier.entities.LigneCommande;
import metier.entities.Livre;
import metier.entities.ModePaiement;
import metier.entities.Panier;
import metier.entities.Promotion;
import metier.entities.TypeLivre;
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
	private Panier panier;
	private List<Livre> allLivres;
	private Livre toAdd;
	private Long idToAdd;
	private int quantite;
	private Long idToRemove;
	private Long idToChange;
	private List<ModePaiement> modePaiement;
	private Long idModePaiement;
	private HashMap<Long,Double> prixUnit;
	private List<LigneCommande> artPanier;
	private String selectedItem;
	private String hidden;
	private int qt;
	
	private String connectionError;
	private String inscriptionError;
	private String reussite;
	private String log;
	
	public class LivrePlus{
		private Livre livre;
		private double prix;
		private double originalPrix;
		private String promo;
		private int quantite = 1;
		
		public void setQuantite(int q){
			this.quantite = q;
		}
		
		public int getQuantite(){
			return this.quantite;
		}
		
		public void setOriginalPrix(double l){
			this.originalPrix = l;
		}
		
		public double getOriginalPrix(){
			return this.originalPrix;
		}
		
		public String getPromo(){
			return this.promo;
		}
		
		public void setPromo(String p){
			this.promo=p;
		}
		
		public Livre getLivre(){
			return this.livre;
		}
		
		public void setLivre(Livre l){
			this.livre = l;
		}
		
		public double getPrix(){
			return this.prix;
		}
		
		public void setPrix(double p){
			this.prix = p;
		}
	}
	
	public class Historique{
		private String dateCommande;
		private String modePaiement;
		private int quantite;
		private double prixUnitaire;
		private String nomLivre;
		private Long idLivre;
		
		public String getDateCommande() {
			return dateCommande;
		}
		public void setDateCommande(String dateCommande) {
			this.dateCommande = dateCommande;
		}
		public String getModePaiement() {
			return modePaiement;
		}
		public void setModePaiement(String modePaiement) {
			this.modePaiement = modePaiement;
		}
		public int getQuantite() {
			return quantite;
		}
		public void setQuantite(int quantite) {
			this.quantite = quantite;
		}
		public double getPrixUnitaire() {
			return prixUnitaire;
		}
		public void setPrixUnitaire(double prixUnitaire) {
			this.prixUnitaire = prixUnitaire;
		}
		public String getNomLivre() {
			return nomLivre;
		}
		public void setNomLivre(String nomLivre) {
			this.nomLivre = nomLivre;
		}
		public Long getIdLivre() {
			return idLivre;
		}
		public void setIdLivre(Long idLivre) {
			this.idLivre = idLivre;
		}
		
		
	}
	
	private List<LivrePlus> livres;
	
	public String getHidden(){
		return this.hidden;
	}
	
	public int getQt(){
		return this.qt;
	}
	
	public void setQt(int t){
		this.qt = t;
	}
	
	public void setHidden(String s){
		this.hidden = s;
	}
	
	public String getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}

	public List<LigneCommande> getArtPanier(){
		return this.artPanier;
	}
	
	public void setArtPanier(List<LigneCommande> l){
		this.artPanier = l;
	}
	
	public HashMap<Long,Double> getPrixUnit(){
		return this.prixUnit;
	}
	
	public void setPrixUnit(HashMap<Long,Double> l){
		this.prixUnit = l;
	}
	
	public List<LivrePlus> getLivres(){
		return this.livres;
	}
	
	public void setLivres(List<LivrePlus> l){
		this.livres = l;
	}
	
	public List<Livre> getAllLivres(){
		return this.allLivres;
	}
	
	public List<ModePaiement> getModePaiement(){
		return this.modePaiement;
	}
	
	public Long getIdModePaiement(){
		return this.idModePaiement;
	}
	
	public void setIdModePaiement(Long id){
		this.idModePaiement = id;
	}
	
	public void setIdToChange(Long id){
		this.idToChange = id;
	}
	
	public Long getIdToRemove(){
		return this.idToRemove;
	}
	
	public Long getIdToChange(){
		return this.idToChange;
	}
	
	public void setIdToRemove(Long id){
		this.idToRemove = id;
	}
	
	public int getQuantite(){
		return this.quantite;
	}
	
	public void setQuantite(int q){
		this.quantite = q;
	}
	
	public void setIdToAdd(Long id){
		this.idToAdd = id;
	}
	
	public Long getIdToAdd(){
		return this.idToAdd;
	}
	
	public Livre getToAdd(){
		return this.toAdd;
	}
	
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
	
	public Panier getPanier(){
		return this.panier;
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
				Panier p = panier;
				Client c = compte.getClient();
				init();
				client = c;
				panier = p;
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
		panier = new Panier();
		qt=1;
		selectedItem = "0";
		prixUnit = new HashMap<Long,Double>();
		log="";
		login ="";
		password = "";
		connectionError = "";
		inscriptionError = "";
		reussite="";
		client = new Client();
		modePaiement = metier.consulterModePaiements();
		allLivres = metier.consulterLivres();
		ArrayList<Livre> l= new ArrayList<Livre>();
		if(allLivres.size()>0)
			first = allLivres.get(0);
		for(int i=1;i<allLivres.size();i++){
			l.add(allLivres.get(i));
		}
		promoLivres = l;
		nb_promoLivres=promoLivres.size()+1;
		if(nb_promoLivres>1)
			nb = new int[nb_promoLivres-1];
		else
			nb=new int[1];
		//livres=new ArrayList<LivrePlus>();
		HashMap<Long,LivrePlus> lplus = new HashMap<Long,LivrePlus>();
		ArrayList<Promotion> pr=new ArrayList<Promotion>(metier.consulterPromotionEnCours());
		for(int i=0;i<pr.size();i++){
			Promotion p = pr.get(i);
			ArrayList<Livre> tmp = new ArrayList<Livre>(metier.consulterLivresByPromotion(p));
			for(int j=0;j<tmp.size();j++){
				Livre liv=tmp.get(j);
				if(lplus.get(liv.getID_livre())==null){
					LivrePlus livPlus = new LivrePlus();
					livPlus.setLivre(liv);
					livPlus.setOriginalPrix(liv.getPrix());
					livPlus.setPromo("En promo");
					livPlus.setPrix(liv.getPrix()-((liv.getPrix()*p.getPourcentage())/100));
					prixUnit.put(liv.getID_livre(), liv.getPrix()-((liv.getPrix()*p.getPourcentage())/100));
					lplus.put(liv.getID_livre(), livPlus);
				}
				else{
					LivrePlus livPlus = lplus.get(liv.getID_livre());
					livPlus.setPrix(livPlus.getPrix()-((livPlus.getPrix()*p.getPourcentage())/100));
					prixUnit.put(liv.getID_livre(), livPlus.getPrix()-((livPlus.getPrix()*p.getPourcentage())/100));
				}
			}
		}
		for(int i=0;i<allLivres.size();i++){
			Livre liv = allLivres.get(i);
			if(lplus.get(liv.getID_livre())==null){
				LivrePlus livPlus = new LivrePlus();
				livPlus.setLivre(liv);
				livPlus.setOriginalPrix(liv.getPrix());
				livPlus.setPromo("");
				//livPlus.setPrix(liv.getPrix());
				lplus.put(liv.getID_livre(), livPlus);
				prixUnit.put(liv.getID_livre(), liv.getPrix());
			}
		}
		livres = new ArrayList<LivrePlus>(lplus.values());
	}
	
	public void addLivre(int idx){
		LivrePlus lp = livres.get(idx);
		toAdd = lp.livre;
		panier.addArticle(toAdd, 1,client);
	}
	
	public List<TypeLivre> typeLivres(int idx){
		Livre l = livres.get(idx).getLivre();
		List<TypeLivre> retour= metier.consulterTypeByLivre(l);
		return retour;
	}
	
	public void removeLivre(int idx){
		Long id=artPanier.get(idx).getLivre().getID_livre();
		panier.deleteItem(id);
	}
	
	public int TaillePanier(){
		return panier.getSize();
	}
	
	public void changeQuantite(){
		panier.changeArticle(idToChange, quantite);
	}
	
	public List<LigneCommande> articlesPanier(){
		List<LigneCommande> lc = new ArrayList<LigneCommande>(panier.getItems());
		List<LigneCommande> retour = new ArrayList<LigneCommande>();
		for(int i=0;i<lc.size();i++){
			Client c = lc.get(i).getCommande().getClient();
			if(c.getNum_client() == client.getNum_client()){
				LigneCommande lig = lc.get(i);
				lig.setPrix(prixUnit.get(lc.get(i).getLivre().getID_livre()));
				lc.set(i, lig);
				retour.add(lig);
			}
		}
		artPanier = retour;
		return retour;
	}
	
	public void passerCommande(){
		metier.enregistrerCommande(panier, client, modePaiement.get(Integer.parseInt(selectedItem)));
		for(int i=0;i<artPanier.size();i++){
			Livre l=artPanier.get(i).getLivre();
			panier.deleteItem(l.getID_livre());
		}
	}
	
	public List<Historique> historiqueCommandes(){
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
