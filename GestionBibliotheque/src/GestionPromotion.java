import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import metier.entities.Livre;
import metier.entities.Promotion;

import metier.sessions.IBiblioRemote;

public class GestionPromotion {
	
	public JFrame frame;
	private JTable table_promotions;
	private JTable table_livres;
	private JTable table_noLivres;
	private JRadioButton rdbtn_AjouterPromotion;
	private JRadioButton rdbtn_ModifierPromotion;
	private JRadioButton rdbtn_SupprimerPromotion;
	private JLabel lbl_DateDebut;
	private JLabel lbl_DateFin;
	private JLabel lbl_titreFenetre;
	private JDateChooser dateDebut;
	private JDateChooser dateFin;
	private JButton btn_Appliquer;
	private JLabel lbl_Pourcentage;
	private JTextField txt_Pourcentage;
	private JLabel lbl_Percent;
	private DefaultTableModel table_modelPromo;
	private DefaultTableModel table_modelLivre;
	private DefaultTableModel table_modelNoLivre;
	private JScrollPane scrollPanePromo;
	private JScrollPane scrollPaneLivre;
	private JLabel lbl_EnPromotion;
	private JLabel lbl_PasEnPromotion;
	private JScrollPane scrollPaneNoLivre;
	private JButton removeLivre;
	private JButton addLivre;
	
	private IBiblioRemote stub;
	
	private List<Livre> livres;
	private List<Livre> notLivres;
	private List<Livre> allLivres;
	private List<Promotion> promotions;
	private int selected;
	private int selected1;
	private int selected2;
	
	boolean isValidPourcentage(String per){
		try{
			int a=Integer.parseInt(per);
			if(a>0 && a<=100)
				return true;
			else
				return false;
		}
		catch(Exception e){
			return false;
		}
	}
	
	Date today(){
		Calendar c = Calendar.getInstance();

		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		return c.getTime();
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionPromotion window = new GestionPromotion();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GestionPromotion() {
		//connection
		stub = ClientEJB.getStub();
		
		initialize();
		PreExecution();
		
	}
	
	/**
	 * to execute after the frame.show
	 */
	private void PreExecution(){
		allLivres = stub.consulterLivres();
		promotions = stub.consulterPromotions();
		
		livres = new ArrayList<Livre>();
		
		notLivres = new ArrayList<Livre>();
		notLivres.addAll(allLivres);
		notLivres.removeAll(livres);
		
		if(table_modelLivre.getColumnCount()==0){
			table_modelLivre.addColumn("Nom livre");
			table_modelLivre.addColumn("Auteur");
		}
		
		if(table_modelNoLivre.getColumnCount()==0){
			table_modelNoLivre.addColumn("Nom livre");
			table_modelNoLivre.addColumn("Auteur");
		}
		
		if(table_modelPromo.getColumnCount()==0){
			table_modelPromo.addColumn("ID_Promotion");
			table_modelPromo.addColumn("Date debut");
			table_modelPromo.addColumn("Date fin");
			table_modelPromo.addColumn("Pourcentage");
		}
		
		
		while(table_modelPromo.getRowCount()!=0)
			table_modelPromo.removeRow(0);
		while(table_modelLivre.getRowCount()!=0)
			table_modelLivre.removeRow(0);
		while(table_modelNoLivre.getRowCount()!=0)
			table_modelNoLivre.removeRow(0);
		
		for(int i=0;i<promotions.size();i++){
			String[] row = new String[4];
			if(promotions.get(i).getID_promotion().toString()==null) row[0]="NULL";
			else row[0]=promotions.get(i).getID_promotion().toString();
			if(promotions.get(i).getDateDebut()==null) row[1]="NULL";
			else row[1]=promotions.get(i).getDateDebut().toString();
			if(promotions.get(i).getDateFin()==null) row[2]="NULL";
			else row[2]=promotions.get(i).getDateFin().toString();
			if(promotions.get(i).getPourcentage()==0) row[3]="NULL";
			else row[3]=""+promotions.get(i).getPourcentage();
			table_modelPromo.addRow(row);
		}
		
		for(int i=0;i<livres.size();i++){
			String[] row = new String[2];
			if(livres.get(i).getNomLivre().toString()==null) row[0]="NULL";
			else row[0]=livres.get(i).getNomLivre();
			if(livres.get(i).getAuteur()==null) row[1]="NULL";
			else row[1]=livres.get(i).getAuteur().getNom()+" "+livres.get(i).getAuteur().getPrenom();
			table_modelLivre.addRow(row);
		}
		
		for(int i=0;i<notLivres.size();i++){
			String[] row = new String[2];
			if(notLivres.get(i).getNomLivre().toString()==null) row[0]="NULL";
			else row[0]=notLivres.get(i).getNomLivre();
			if(notLivres.get(i).getAuteur()==null) row[1]="NULL";
			else row[1]=notLivres.get(i).getAuteur().getNom()+" "+notLivres.get(i).getAuteur().getPrenom();
			table_modelNoLivre.addRow(row);
		}
		
	}
	
	void initLivres(){
		allLivres = stub.consulterLivres();
		
		livres = new ArrayList<Livre>();
		
		notLivres = new ArrayList<Livre>();
		notLivres.addAll(allLivres);
		
		if(table_modelLivre.getColumnCount()==0){
			table_modelLivre.addColumn("Nom livre");
			table_modelLivre.addColumn("Auteur");
		}
		
		if(table_modelNoLivre.getColumnCount()==0){
			table_modelNoLivre.addColumn("Nom livre");
			table_modelNoLivre.addColumn("Auteur");
		}
		
		while(table_modelLivre.getRowCount()!=0)
			table_modelLivre.removeRow(0);
		while(table_modelNoLivre.getRowCount()!=0)
			table_modelNoLivre.removeRow(0);
		
		for(int i=0;i<livres.size();i++){
			String[] row = new String[2];
			if(livres.get(i).getNomLivre().toString()==null) row[0]="NULL";
			else row[0]=livres.get(i).getNomLivre();
			if(livres.get(i).getAuteur()==null) row[1]="NULL";
			else row[1]=livres.get(i).getAuteur().getNom()+" "+livres.get(i).getAuteur().getPrenom();
			table_modelLivre.addRow(row);
		}
		
		for(int i=0;i<notLivres.size();i++){
			String[] row = new String[2];
			if(notLivres.get(i).getNomLivre().toString()==null) row[0]="NULL";
			else row[0]=notLivres.get(i).getNomLivre();
			if(notLivres.get(i).getAuteur()==null) row[1]="NULL";
			else row[1]=notLivres.get(i).getAuteur().getNom()+" "+notLivres.get(i).getAuteur().getPrenom();
			table_modelNoLivre.addRow(row);
		}
	}
	
	void showLivres(Long id){
		allLivres = stub.consulterLivres();
		
		Promotion p = new Promotion();
		p.setID_promotion(id);
		
		livres = new ArrayList<Livre>();
		livres.addAll(stub.consulterLivresByPromotion(p));
		
		notLivres = new ArrayList<Livre>();
		notLivres.addAll(allLivres);
		for(int i=0;i<livres.size();i++){
			for(int j=0;j<notLivres.size();j++){
				if(notLivres.get(j).getID_livre()==livres.get(i).getID_livre()){
					notLivres.remove(j);
					break;
				}
			}
		}
		
		if(table_modelLivre.getColumnCount()==0){
			table_modelLivre.addColumn("Nom livre");
			table_modelLivre.addColumn("Auteur");
		}
		
		if(table_modelNoLivre.getColumnCount()==0){
			table_modelNoLivre.addColumn("Nom livre");
			table_modelNoLivre.addColumn("Auteur");
		}
		
		while(table_modelLivre.getRowCount()!=0)
			table_modelLivre.removeRow(0);
		while(table_modelNoLivre.getRowCount()!=0)
			table_modelNoLivre.removeRow(0);
		
		for(int i=0;i<livres.size();i++){
			String[] row = new String[2];
			if(livres.get(i).getNomLivre().toString()==null) row[0]="NULL";
			else row[0]=livres.get(i).getNomLivre();
			if(livres.get(i).getAuteur()==null) row[1]="NULL";
			else row[1]=livres.get(i).getAuteur().getNom()+" "+livres.get(i).getAuteur().getPrenom();
			table_modelLivre.addRow(row);
		}
		
		for(int i=0;i<notLivres.size();i++){
			String[] row = new String[2];
			if(notLivres.get(i).getNomLivre().toString()==null) row[0]="NULL";
			else row[0]=notLivres.get(i).getNomLivre();
			if(notLivres.get(i).getAuteur()==null) row[1]="NULL";
			else row[1]=notLivres.get(i).getAuteur().getNom()+" "+notLivres.get(i).getAuteur().getPrenom();
			table_modelNoLivre.addRow(row);
		}
	}
	
	private void ViderChamps(){
		txt_Pourcentage.setText("");
		dateDebut.setDate(null);
		dateFin.setDate(null);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		
		//Instantiation + initialization
		frame = new JFrame();
		lbl_titreFenetre = new JLabel("Gestion des promotions");
		lbl_DateDebut = new JLabel("Date début :");
		dateDebut = new JDateChooser();
		rdbtn_AjouterPromotion = new JRadioButton("Ajouter Promotion");
		rdbtn_ModifierPromotion = new JRadioButton("Modifier Promotion");
		rdbtn_SupprimerPromotion = new JRadioButton("Supprimer Promotion");
		table_modelPromo = new DefaultTableModel(){
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		table_modelLivre = new DefaultTableModel(){
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		table_modelNoLivre = new DefaultTableModel(){
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		table_promotions = new JTable(table_modelPromo);
		table_livres = new JTable(table_modelLivre);
		table_noLivres = new JTable(table_modelNoLivre);
		btn_Appliquer = new JButton("Appliquer");
		lbl_Pourcentage = new JLabel("Pourcentage :");
		txt_Pourcentage = new JTextField();
		lbl_Percent = new JLabel("  %");
		scrollPanePromo = new JScrollPane();
		scrollPaneLivre = new JScrollPane();
		dateFin = new JDateChooser();
		lbl_DateFin = new JLabel("Date fin :");
		scrollPaneNoLivre = new JScrollPane();
		removeLivre = new JButton(">");
		addLivre = new JButton("<");
		lbl_EnPromotion = new JLabel("En promotion");
		lbl_PasEnPromotion = new JLabel("Pas en promotion");
		selected = -1;
		selected1 = -1;
		selected2 = -1;
		
		//parametres frame
		frame.setTitle("Gestion Promotion");
		frame.setSize(900, 500);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLocale(java.util.Locale.getDefault());
		frame.getContentPane().setLayout(null);
		
		//parametres composants
		lbl_titreFenetre.setForeground(new Color(100, 149, 237));
		lbl_titreFenetre.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_titreFenetre.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));
		lbl_DateDebut.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		table_promotions.setRowSelectionAllowed(false);
		//table_livres.setRowSelectionAllowed(false);
		//table_noLivres.setRowSelectionAllowed(false);
		table_promotions.setEnabled(false);
		//table_livres.setEnabled(false);
		lbl_Pourcentage.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		scrollPanePromo.setSize(606, 188);
		scrollPanePromo.setLocation(10, 253);
		scrollPanePromo.setViewportView(table_promotions);
		scrollPaneLivre.setViewportView(table_livres);
		scrollPaneNoLivre.setViewportView(table_noLivres);
		rdbtn_AjouterPromotion.setSelected(true);
		lbl_DateFin.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbl_EnPromotion.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbl_PasEnPromotion.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		//emplacements
		table_promotions.setBounds(156, 242, 557, 160);
		rdbtn_AjouterPromotion.setBounds(647, 253, 166, 23);
		rdbtn_ModifierPromotion.setBounds(647, 279, 166, 23);
		rdbtn_SupprimerPromotion.setBounds(647, 305, 166, 23);
		dateDebut.setBounds(146, 85, 203, 20);
		lbl_DateDebut.setBounds(47, 85, 89, 19);
		lbl_titreFenetre.setBounds(171, 11, 419, 47);
		btn_Appliquer.setBounds(657, 335, 89, 23);
		lbl_Pourcentage.setBounds(40, 146, 96, 19);
		txt_Pourcentage.setBounds(146, 144, 180, 20);
		lbl_Percent.setBounds(329, 146, 32, 14);
		scrollPaneLivre.setBounds(395, 86, 195, 144);
		dateFin.setBounds(146, 115, 203, 20);
		lbl_DateFin.setBounds(68, 115, 68, 19);
		lbl_EnPromotion.setBounds(446, 56, 96, 19);
		scrollPaneNoLivre.setBounds(675, 85, 195, 144);
		lbl_PasEnPromotion.setBounds(713, 55, 120, 19);
		removeLivre.setBounds(600, 165, 59, 23);
		addLivre.setBounds(600, 131, 59, 23);
		
		//listeners
		rdbtn_AjouterPromotion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtn_AjouterPromotion.setSelected(true);
				rdbtn_ModifierPromotion.setSelected(false);
				rdbtn_SupprimerPromotion.setSelected(false);
				
				table_promotions.setRowSelectionAllowed(false);
				table_promotions.setEnabled(false);
				table_promotions.getSelectionModel().clearSelection();
				selected = -1;
				selected1 = -1;
				selected2 = -1;
				ViderChamps();
				initLivres();
			}
		});
		
		rdbtn_ModifierPromotion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtn_AjouterPromotion.setSelected(false);
				rdbtn_ModifierPromotion.setSelected(true);
				rdbtn_SupprimerPromotion.setSelected(false);
				
				table_promotions.setRowSelectionAllowed(true);
				table_promotions.setEnabled(true);
				table_promotions.getSelectionModel().clearSelection();
				selected = -1;
				selected1 = -1;
				selected2 = -1;
				ViderChamps();
				initLivres();
			}
		});
		
		rdbtn_SupprimerPromotion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtn_AjouterPromotion.setSelected(false);
				rdbtn_ModifierPromotion.setSelected(false);
				rdbtn_SupprimerPromotion.setSelected(true);
				
				table_promotions.setRowSelectionAllowed(true);
				table_promotions.setEnabled(true);
				table_promotions.getSelectionModel().clearSelection();
				selected = -1;
				selected1 = -1;
				selected2 = -1;
				ViderChamps();
				initLivres();
			}
		});
		
		ListSelectionModel mod = table_promotions.getSelectionModel();
		mod.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				
				selected=table_promotions.getSelectedRow();
				if(selected>=0){
					Promotion l = promotions.get(selected);
					txt_Pourcentage.setText(Integer.toString(l.getPourcentage()));
					dateDebut.setDate(l.getDateDebut());
					dateFin.setDate(l.getDateFin());
					showLivres(l.getID_promotion());
					
				}
			}
			
		});
		
		ListSelectionModel mod1 = table_noLivres.getSelectionModel();
		mod1.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				selected1=table_noLivres.getSelectedRow();
			}
		});
		
		ListSelectionModel mod2 = table_livres.getSelectionModel();
		mod2.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				selected2=table_livres.getSelectedRow();
			}
		});
		
		
		addLivre.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				if(selected1>=0){
					Livre l = notLivres.get(selected1);
					notLivres.remove(selected1);
					table_modelNoLivre.removeRow(selected1);
					livres.add(l);
					String[] row = new String[2];
					row[0]=l.getNomLivre();
					row[1]=l.getAuteur().getNom()+" "+l.getAuteur().getPrenom();
					table_modelLivre.addRow(row);
				}
				else{
					JOptionPane.showMessageDialog(null, "Veuillez selectionner un livre à ajouter", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		removeLivre.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				if(selected2>=0){
					Livre l = livres.get(selected2);
					livres.remove(selected2);
					table_modelLivre.removeRow(selected2);
					notLivres.add(l);
					String[] row = new String[2];
					row[0]=l.getNomLivre();
					row[1]=l.getAuteur().getNom()+" "+l.getAuteur().getPrenom();
					table_modelNoLivre.addRow(row);
				}
				else{
					JOptionPane.showMessageDialog(null, "Veuillez selectionner un livre à enlever", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		btn_Appliquer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(rdbtn_AjouterPromotion.isSelected()){
					if(dateDebut.getDate()==null
							|| txt_Pourcentage.getText().equals("")
							|| dateFin.getDate()==null
					)
						JOptionPane.showMessageDialog(null, "Veuillez remplir tout les champs", "Warning", JOptionPane.WARNING_MESSAGE);
					else if(!isValidPourcentage(txt_Pourcentage.getText())){
						JOptionPane.showMessageDialog(null, "Veuillez saisir un pourcentage valide", "Warning", JOptionPane.WARNING_MESSAGE);
					}
					else if(dateDebut.getDate().compareTo(dateFin.getDate())==1
							|| dateDebut.getDate().compareTo(today())==-1
							|| dateFin.getDate().compareTo(today())==-1
					){
						JOptionPane.showMessageDialog(null, "Veuillez saisir des dates valides", "Warning", JOptionPane.WARNING_MESSAGE);
					}
					else{
						Promotion l = new Promotion(Integer.parseInt(txt_Pourcentage.getText()),dateDebut.getDate(),dateFin.getDate());
						l.setLivres(new HashSet<Livre>(livres));
						stub.ajouterPromotion(l);
						PreExecution();
						
						ViderChamps();
						JOptionPane.showMessageDialog(null, "La promotion a été ajoutée", "Information", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else if(rdbtn_ModifierPromotion.isSelected()){
					if(selected>=0){
						if(dateDebut.getDate()==null
								|| txt_Pourcentage.getText().equals("")
						)
							JOptionPane.showMessageDialog(null, "Veuillez remplir tout les champs", "Warning", JOptionPane.WARNING_MESSAGE);
						else if(!isValidPourcentage(txt_Pourcentage.getText())){
							JOptionPane.showMessageDialog(null, "Veuillez saisir un pourcentage valide", "Warning", JOptionPane.WARNING_MESSAGE);
						}
						else{
							Promotion l = promotions.get(selected);
							l.setDateDebut(dateDebut.getDate());
							l.setDateFin(dateFin.getDate());
							l.setPourcentage(Integer.parseInt(txt_Pourcentage.getText()));
							stub.modifierPromotion(l);
							PreExecution();
							ViderChamps();
							JOptionPane.showMessageDialog(null, "La promotion a été modifiée", "Information", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Veuillez selectionner un livre à modifier", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
				else if(rdbtn_SupprimerPromotion.isSelected()){
					if(selected>=0){
						Promotion l = promotions.get(selected);
						stub.supprimerPromotion(l.getID_promotion());
						PreExecution();
						ViderChamps();
						JOptionPane.showMessageDialog(null, "La promotion a été supprimée", "Information", JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null, "Veuillez selectionner une promotion à supprimer", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		
		//Ajout au frame
		frame.getContentPane().add(lbl_titreFenetre);
		frame.getContentPane().add(rdbtn_AjouterPromotion);
		frame.getContentPane().add(dateDebut);
		frame.getContentPane().add(rdbtn_ModifierPromotion);
		frame.getContentPane().add(rdbtn_SupprimerPromotion);
		frame.getContentPane().add(scrollPanePromo);
		frame.getContentPane().add(lbl_DateDebut);
		frame.getContentPane().add(btn_Appliquer);
		frame.getContentPane().add(lbl_Pourcentage);
		frame.getContentPane().add(txt_Pourcentage);
		frame.getContentPane().add(lbl_Percent);
		frame.getContentPane().add(scrollPaneLivre);
		frame.getContentPane().add(dateFin);
		frame.getContentPane().add(lbl_DateFin);
		frame.getContentPane().add(lbl_EnPromotion);
		frame.getContentPane().add(removeLivre);
		frame.getContentPane().add(addLivre);
		frame.getContentPane().add(lbl_PasEnPromotion);
		frame.getContentPane().add(scrollPaneNoLivre);
	}
}
