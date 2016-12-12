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
import com.toedter.calendar.JDateChooser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import metier.entities.Auteur;
import metier.entities.Editeur;
import metier.entities.Livre;
import metier.entities.TypeLivre;
import metier.sessions.IBiblioRemote;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class GestionLivre {
	
	public JFrame frame;
	private JTextField txt_nomLivre;
	private JTable table_livres;
	private JTable table_type;
	private JTable table_noType;
	private DefaultTableModel table_modelType;
	private DefaultTableModel table_modelNoType;
	//private JScrollPane scrollPanePromo;
	//private JScrollPane scrollPaneLivre;
	private JScrollPane scrollPaneType;
	private JScrollPane scrollPaneNoType;
	private JLabel lbl_Type;
	private JLabel lbl_notType;
	private JButton btn_removeType;
	private JButton btn_addType;
	private JRadioButton rdbtn_AjouterLivre;
	private JRadioButton rdbtn_ModifierLivre;
	private JRadioButton rdbtn_SupprimerLivre;
	private JLabel lbl_DateApparition;
	private JLabel lbl_titreFenetre;
	private JLabel lbl_NomLivre;
	private JDateChooser dateApparition;
	private JButton btn_Appliquer;
	private JLabel lbl_Prix;
	private JTextField txt_Prix;
	private JLabel lbl_Dnt;
	private DefaultTableModel table_model;
	private JScrollPane scrollPane;
	private JComboBox<String> cb_Auteur;
	private JComboBox<String> cb_Editeur;
	private JLabel lbl_Editeur;
	private JLabel lbl_Auteur;
	
	private IBiblioRemote stub;
	
	private List<Livre> livres;
	private List<TypeLivre> types;
	private List<TypeLivre> notTypes;
	private List<TypeLivre> allTypes;
	private int selected;
	private int selected1;
	private int selected2;
	private List<Auteur> auteurs;
	private List<Editeur> editeurs;
	
	boolean isDouble(String d){
		try{
			Double.parseDouble(d);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionLivre window = new GestionLivre();
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
	public GestionLivre() {
		//connection
		stub = ClientEJB.getStub();
		
		initialize();
		PreExecution();
		
	}
	
	/**
	 * to execute after the frame.show
	 */
	private void PreExecution(){
		
		livres = stub.consulterLivres();
		auteurs = stub.consulterAuteurs();
		editeurs = stub.consulterEditeurs();
		allTypes = stub.consulterTypeLivres();
		
		types = new ArrayList<TypeLivre>();
		
		notTypes = new ArrayList<TypeLivre>();
		notTypes.addAll(allTypes);
		notTypes.removeAll(types);
		
		cb_Auteur.removeAllItems();
		cb_Editeur.removeAllItems();
		
		for(int i=0;i<auteurs.size();i++){
			cb_Auteur.addItem(auteurs.get(i).getNom()+" "+auteurs.get(i).getPrenom());
		}
		
		for(int i=0;i<editeurs.size();i++){
			cb_Editeur.addItem(editeurs.get(i).getNom());
		}
		
		if(table_model.getColumnCount()==0){
			table_model.addColumn("ID_livre");
			table_model.addColumn("Nom du livre");
			table_model.addColumn("Auteur");
			table_model.addColumn("Editeur");
			table_model.addColumn("Date d'apparition");
			table_model.addColumn("Prix");
		}
		
		if(table_modelType.getColumnCount()==0){
			table_modelType.addColumn("Type");
		}
		
		if(table_modelNoType.getColumnCount()==0){
			table_modelNoType.addColumn("Type");
		}
		
		while(table_model.getRowCount()!=0)
			table_model.removeRow(0);
		while(table_modelType.getRowCount()!=0)
			table_modelType.removeRow(0);
		while(table_modelNoType.getRowCount()!=0)
			table_modelNoType.removeRow(0);
		
		for(int i=0;i<livres.size();i++){
			String[] row = new String[6];
			if(livres.get(i).getID_livre().toString()==null) row[0]="NULL";
			else row[0]=livres.get(i).getID_livre().toString();
			if(livres.get(i).getNomLivre()==null) row[1]="NULL";
			else row[1]=livres.get(i).getNomLivre();
			if(livres.get(i).getAuteur()==null) row[2]="NULL";
			else row[2]=livres.get(i).getAuteur().getNom()+" "+livres.get(i).getAuteur().getPrenom();
			if(livres.get(i).getEditeur()==null) row[3]="NULL";
			else row[3]=livres.get(i).getEditeur().getNom();
			if(livres.get(i).getDateApparition().toString()==null) row[4]="NULL";
			else row[4]=livres.get(i).getDateApparition().toString();
			if(Double.toString(livres.get(i).getPrix()) == null) row[5]="NULL";
			else row[5]=Double.toString(livres.get(i).getPrix());
			table_model.addRow(row);
		}
		
		for(int i=0;i<types.size();i++){
			String[] row = new String[1];
			if(types.get(i).getNom().toString()==null) row[0]="NULL";
			else row[0]=types.get(i).getNom();
			table_modelType.addRow(row);
		}
		
		for(int i=0;i<notTypes.size();i++){
			String[] row = new String[1];
			if(notTypes.get(i).getNom().toString()==null) row[0]="NULL";
			else row[0]=notTypes.get(i).getNom();
			table_modelNoType.addRow(row);
		}
		
	}
	
	private void initTypes(){
		allTypes = stub.consulterTypeLivres();
		
		types = new ArrayList<TypeLivre>();
		
		notTypes = new ArrayList<TypeLivre>();
		notTypes.addAll(allTypes);
		
		if(table_modelType.getColumnCount()==0){
			table_modelType.addColumn("Type");
		}
		
		if(table_modelNoType.getColumnCount()==0){
			table_modelNoType.addColumn("Type");
		}
		
		while(table_modelType.getRowCount()!=0)
			table_modelType.removeRow(0);
		while(table_modelNoType.getRowCount()!=0)
			table_modelNoType.removeRow(0);
		
		for(int i=0;i<types.size();i++){
			String[] row = new String[1];
			if(types.get(i).getNom().toString()==null) row[0]="NULL";
			else row[0]=types.get(i).getNom();
			table_modelType.addRow(row);
		}
		
		for(int i=0;i<notTypes.size();i++){
			String[] row = new String[1];
			if(notTypes.get(i).getNom().toString()==null) row[0]="NULL";
			else row[0]=notTypes.get(i).getNom();
			table_modelNoType.addRow(row);
		}
	}
	
	void showTypes(Long id){
		allTypes = stub.consulterTypeLivres();
		
		Livre l = new Livre();
		l.setID_livre(id);
		
		types = new ArrayList<TypeLivre>();
		types.addAll(stub.consulterTypeByLivre(l));
		
		notTypes = new ArrayList<TypeLivre>();
		notTypes.addAll(allTypes);
		for(int i=0;i<types.size();i++){
			for(int j=0;j<notTypes.size();j++){
				if(notTypes.get(j).getId()==types.get(i).getId()){
					notTypes.remove(j);
					break;
				}
			}
		}
		
		if(table_modelType.getColumnCount()==0){
			table_modelType.addColumn("Type");
		}
		
		if(table_modelNoType.getColumnCount()==0){
			table_modelNoType.addColumn("Type");
		}
		
		while(table_modelType.getRowCount()!=0)
			table_modelType.removeRow(0);
		while(table_modelNoType.getRowCount()!=0)
			table_modelNoType.removeRow(0);
		
		for(int i=0;i<types.size();i++){
			String[] row = new String[1];
			if(types.get(i).getNom().toString()==null) row[0]="NULL";
			else row[0]=types.get(i).getNom();
			table_modelType.addRow(row);
		}
		
		for(int i=0;i<notTypes.size();i++){
			String[] row = new String[1];
			if(notTypes.get(i).getNom().toString()==null) row[0]="NULL";
			else row[0]=notTypes.get(i).getNom();
			table_modelNoType.addRow(row);
		}
	}
	
	private void ViderChamps(){
		txt_nomLivre.setText("");
		txt_Prix.setText("");
		dateApparition.setDate(null);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		
		//Instantiation + initialization
		frame = new JFrame();
		lbl_titreFenetre = new JLabel("Gestion des livres");
		lbl_NomLivre = new JLabel("Nom du livre :");
		lbl_DateApparition = new JLabel("Date d'apparition :");
		txt_nomLivre = new JTextField();
		dateApparition = new JDateChooser();
		rdbtn_AjouterLivre = new JRadioButton("Ajouter Livre");
		rdbtn_ModifierLivre = new JRadioButton("Modifier Livre");
		rdbtn_SupprimerLivre = new JRadioButton("Supprimer Livre");
		table_model = new DefaultTableModel(){
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		table_modelType = new DefaultTableModel(){
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		table_modelNoType = new DefaultTableModel(){
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		table_type = new JTable(table_modelType);
		table_noType = new JTable(table_modelNoType);
		table_livres = new JTable(table_model);
		scrollPaneType = new JScrollPane();
		scrollPaneNoType = new JScrollPane();
		btn_removeType = new JButton(">");
		btn_addType = new JButton("<");
		lbl_Type = new JLabel("Types ajoutés");
		lbl_notType = new JLabel("Types livres");
		selected1 = -1;
		selected2 = -1;
		btn_Appliquer = new JButton("Appliquer");
		lbl_Prix = new JLabel("Prix :");
		txt_Prix = new JTextField();
		lbl_Dnt = new JLabel("TND");
		scrollPane = new JScrollPane();
		selected = -1;
		cb_Auteur= new JComboBox<String>();
		cb_Editeur = new JComboBox<String>();
		lbl_Auteur = new JLabel("Auteur :");
		lbl_Editeur = new JLabel("Editeur :");
		
		//parametres frame
		frame.setTitle("Gestion Livre");
		frame.setSize(900, 500);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLocale(java.util.Locale.getDefault());
		frame.getContentPane().setLayout(null);
		
		//parametres composants
		scrollPaneType.setViewportView(table_type);
		scrollPaneNoType.setViewportView(table_noType);
		lbl_Type.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbl_notType.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbl_titreFenetre.setForeground(new Color(100, 149, 237));
		lbl_titreFenetre.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_titreFenetre.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));		
		lbl_NomLivre.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbl_DateApparition.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		table_livres.setRowSelectionAllowed(false);
		table_livres.setEnabled(false);
		lbl_Prix.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		scrollPane.setSize(668, 188);
		scrollPane.setLocation(21, 249);
		scrollPane.setViewportView(table_livres);
		lbl_Auteur.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbl_Editeur.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		rdbtn_AjouterLivre.setSelected(true);
		
		//emplacements
		scrollPaneType.setBounds(395, 86, 195, 144);
		scrollPaneNoType.setBounds(675, 85, 195, 144);
		lbl_Type.setBounds(446, 56, 96, 19);
		lbl_notType.setBounds(736, 56, 120, 19);
		btn_removeType.setBounds(600, 165, 59, 23);
		btn_addType.setBounds(600, 131, 59, 23);
		table_livres.setBounds(156, 242, 557, 160);
		rdbtn_AjouterLivre.setBounds(715, 264, 141, 23);
		rdbtn_ModifierLivre.setBounds(715, 290, 141, 23);
		rdbtn_SupprimerLivre.setBounds(715, 316, 141, 23);
		dateApparition.setBounds(170, 115, 203, 20);
		txt_nomLivre.setBounds(169, 86, 204, 20);
		lbl_NomLivre.setBounds(58, 85, 101, 19);
		lbl_DateApparition.setBounds(34, 115, 126, 19);
		lbl_titreFenetre.setBounds(171, 11, 419, 47);
		btn_Appliquer.setBounds(715, 361, 89, 23);
		lbl_Prix.setBounds(115, 146, 45, 19);
		txt_Prix.setBounds(170, 144, 180, 20);
		lbl_Dnt.setBounds(353, 146, 32, 14);
		cb_Auteur.setBounds(170, 175, 203, 20);
		cb_Editeur.setBounds(170, 206, 203, 20);
		lbl_Editeur.setBounds(97, 205, 61, 19);
		lbl_Auteur.setBounds(99, 176, 61, 19);
		
		//listeners
		rdbtn_AjouterLivre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtn_AjouterLivre.setSelected(true);
				rdbtn_ModifierLivre.setSelected(false);
				rdbtn_SupprimerLivre.setSelected(false);
				
				table_livres.setRowSelectionAllowed(false);
				table_livres.setEnabled(false);
				table_livres.getSelectionModel().clearSelection();
				selected = -1;
				selected1 = -1;
				selected2 = -1;
				ViderChamps();
				initTypes();
			}
		});
		
		rdbtn_ModifierLivre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtn_AjouterLivre.setSelected(false);
				rdbtn_ModifierLivre.setSelected(true);
				rdbtn_SupprimerLivre.setSelected(false);
				
				table_livres.setRowSelectionAllowed(true);
				table_livres.setEnabled(true);
				table_livres.getSelectionModel().clearSelection();
				selected = -1;
				selected1 = -1;
				selected2 = -1;
				ViderChamps();
				initTypes();
			}
		});
		
		rdbtn_SupprimerLivre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtn_AjouterLivre.setSelected(false);
				rdbtn_ModifierLivre.setSelected(false);
				rdbtn_SupprimerLivre.setSelected(true);
				
				table_livres.setRowSelectionAllowed(true);
				table_livres.setEnabled(true);
				table_livres.getSelectionModel().clearSelection();
				selected = -1;
				selected1 = -1;
				selected2 = -1;
				ViderChamps();
				initTypes();
			}
		});
		
		ListSelectionModel mod = table_livres.getSelectionModel();
		mod.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				
				selected=table_livres.getSelectedRow();
				if(selected>=0){
					Livre l = livres.get(selected);
					txt_nomLivre.setText(l.getNomLivre());
					txt_Prix.setText(Double.toString(l.getPrix()));
					dateApparition.setDate(l.getDateApparition());
					int find=0;
					for(int i=0;i<auteurs.size();i++){
						if(auteurs.get(i).getID_auteur()==l.getAuteur().getID_auteur()){
							find = i;
							break;
						}
					}
					if(auteurs.size()!=0)
						cb_Auteur.setSelectedIndex(find);
					find=0;
					for(int i=0;i<editeurs.size();i++){
						if(editeurs.get(i).getID_editeur()==l.getEditeur().getID_editeur()){
							find = i;
							break;
						}
					}
					if(editeurs.size()!=0)
						cb_Editeur.setSelectedIndex(find);
					showTypes(l.getID_livre());
				}
				
			}
			
		});
		
		ListSelectionModel mod1 = table_noType.getSelectionModel();
		mod1.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				selected1=table_noType.getSelectedRow();
			}
		});
		
		ListSelectionModel mod2 = table_type.getSelectionModel();
		mod2.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				selected2=table_type.getSelectedRow();
			}
		});
		
		btn_addType.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				if(selected1>=0){
					TypeLivre t = notTypes.get(selected1);
					notTypes.remove(selected1);
					table_modelNoType.removeRow(selected1);
					types.add(t);
					String[] row = new String[1];
					row[0]=t.getNom();
					table_modelType.addRow(row);
				}
				else{
					JOptionPane.showMessageDialog(null, "Veuillez selectionner un type à ajouter", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		btn_removeType.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				if(selected2>=0){
					TypeLivre t = types.get(selected2);
					types.remove(selected2);
					table_modelType.removeRow(selected2);
					notTypes.add(t);
					String[] row = new String[1];
					row[0]=t.getNom();
					table_modelNoType.addRow(row);
				}
				else{
					JOptionPane.showMessageDialog(null, "Veuillez selectionner un type à enlever", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		
		btn_Appliquer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtn_AjouterLivre.isSelected()){
					if(txt_nomLivre.getText().equals("")
							|| dateApparition.getDate()==null
							|| txt_Prix.getText().equals("")
					)
						JOptionPane.showMessageDialog(null, "Veuillez remplir tout les champs", "Warning", JOptionPane.WARNING_MESSAGE);
					else if(!isDouble(txt_Prix.getText())){
						JOptionPane.showMessageDialog(null, "Veuillez saisir un prix valide", "Warning", JOptionPane.WARNING_MESSAGE);
					}
					else if(Double.parseDouble(txt_Prix.getText())<0){
						JOptionPane.showMessageDialog(null, "Veuillez saisir un prix valide", "Warning", JOptionPane.WARNING_MESSAGE);
					}
					else{
						Livre l = new Livre(txt_nomLivre.getText(),dateApparition.getDate(),Double.parseDouble(txt_Prix.getText()));
						l.setAuteur(auteurs.get(cb_Auteur.getSelectedIndex()));
						l.setEditeur(editeurs.get(cb_Editeur.getSelectedIndex()));
						l.setTypes(new HashSet<TypeLivre>(types));
						stub.addLivre(l);
						PreExecution();
						ViderChamps();
						JOptionPane.showMessageDialog(null, "Le livre a été ajouté", "Information", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else if(rdbtn_ModifierLivre.isSelected()){
					if(selected>=0){
						if(txt_nomLivre.getText().equals("")
								|| dateApparition.getDate()==null
								|| txt_Prix.getText().equals("")
						)
							JOptionPane.showMessageDialog(null, "Veuillez remplir tout les champs", "Warning", JOptionPane.WARNING_MESSAGE);
						else if(!isDouble(txt_Prix.getText())){
							JOptionPane.showMessageDialog(null, "Veuillez saisir un prix valide", "Warning", JOptionPane.WARNING_MESSAGE);
						}
						else if(Double.parseDouble(txt_Prix.getText())<0){
							JOptionPane.showMessageDialog(null, "Veuillez saisir un prix valide", "Warning", JOptionPane.WARNING_MESSAGE);
						}
						else{
							Livre l = livres.get(selected);
							l.setDateApparition(dateApparition.getDate());
							l.setNomLivre(txt_nomLivre.getText());
							l.setPrix(Double.parseDouble(txt_Prix.getText()));
							l.setAuteur(auteurs.get(cb_Auteur.getSelectedIndex()));
							l.setEditeur(editeurs.get(cb_Editeur.getSelectedIndex()));
							l.setTypes(new HashSet<TypeLivre>(types));
							stub.updateLivre(l);
							PreExecution();
							ViderChamps();
							JOptionPane.showMessageDialog(null, "Le livre a été modifié", "Information", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Veuillez selectionner un livre à modifier", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
				else if(rdbtn_SupprimerLivre.isSelected()){
					if(selected>=0){
						Livre l = livres.get(selected);
						stub.supprimerLivre(l.getID_livre());
						PreExecution();
						ViderChamps();
						JOptionPane.showMessageDialog(null, "Le livre a été supprimé", "Information", JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null, "Veuillez selectionner un livre à supprimer", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		
		//Ajout au frame
		frame.getContentPane().add(lbl_titreFenetre);
		frame.getContentPane().add(rdbtn_AjouterLivre);
		frame.getContentPane().add(dateApparition);
		frame.getContentPane().add(rdbtn_ModifierLivre);
		frame.getContentPane().add(rdbtn_SupprimerLivre);
		frame.getContentPane().add(scrollPane);
		frame.getContentPane().add(txt_nomLivre);
		frame.getContentPane().add(lbl_DateApparition);
		frame.getContentPane().add(lbl_NomLivre);
		frame.getContentPane().add(btn_Appliquer);
		frame.getContentPane().add(lbl_Prix);
		frame.getContentPane().add(txt_Prix);
		frame.getContentPane().add(lbl_Dnt);
		frame.getContentPane().add(cb_Editeur);
		frame.getContentPane().add(cb_Auteur);
		frame.getContentPane().add(lbl_Editeur);
		frame.getContentPane().add(lbl_Auteur);
		frame.getContentPane().add(lbl_Type);
		frame.getContentPane().add(btn_removeType);
		frame.getContentPane().add(btn_addType);
		frame.getContentPane().add(lbl_notType);
		frame.getContentPane().add(scrollPaneNoType);
		frame.getContentPane().add(scrollPaneType);
		
	}
}
