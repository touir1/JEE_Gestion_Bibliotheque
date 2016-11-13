import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.naming.Context;
import javax.naming.InitialContext;
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

import java.util.List;
import java.util.Properties;

import metier.entities.Livre;
import metier.sessions.IBiblioRemote;
import javax.swing.JButton;

public class GesiontLivre {
	
	private JFrame frame;
	private JTextField txt_nomLivre;
	private JTable table_livres;
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
	
	private Properties properties;
	private Context context;
	private IBiblioRemote stub;
	
	private List<Livre> livres;
	private int selected;
	
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
					GesiontLivre window = new GesiontLivre();
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
	public GesiontLivre() {
		//connection
		try{
			properties = new Properties();
			properties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			context = new InitialContext(properties);
			
			stub = (IBiblioRemote) context.lookup("ejb:/dsBiblioEJB/dsBiblioEJB!metier.sessions.IBiblioRemote");
			
			initialize();
			PreExecution();
		}
		catch(Exception e){
			System.out.println("ERROR:");
			System.out.println(e.getMessage());
		}
		
		
	}
	
	/**
	 * to execute after the frame.show
	 */
	private void PreExecution(){
		
		livres = stub.consulterLivres();
		
		if(table_model.getColumnCount()==0){
			table_model.addColumn("ID_livre");
			table_model.addColumn("Nom du livre");
			table_model.addColumn("Auteur");
			table_model.addColumn("Date d'apparition");
			table_model.addColumn("Prix");
		}
		
		while(table_model.getRowCount()!=0)
			table_model.removeRow(0);
		
		for(int i=0;i<livres.size();i++){
			String[] row = new String[5];
			if(livres.get(i).getID_livre().toString()==null) row[0]="NULL";
			else row[0]=livres.get(i).getID_livre().toString();
			if(livres.get(i).getNomLivre()==null) row[1]="NULL";
			else row[1]=livres.get(i).getNomLivre();
			if(livres.get(i).getAuteur()==null) row[2]="NULL";
			else row[2]=livres.get(i).getAuteur().getNom();
			if(livres.get(i).getDateApparition().toString()==null) row[3]="NULL";
			else row[3]=livres.get(i).getDateApparition().toString();
			if(Double.toString(livres.get(i).getPrix()) == null) row[4]="NULL";
			else row[4]=Double.toString(livres.get(i).getPrix());
			table_model.addRow(row);
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
	private void initialize() {
		
		//Instanciation + initialisation
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
		table_livres = new JTable(table_model);
		btn_Appliquer = new JButton("Appliquer");
		lbl_Prix = new JLabel("Prix :");
		txt_Prix = new JTextField();
		lbl_Dnt = new JLabel("TND");
		scrollPane = new JScrollPane();
		selected = -1;
		
		//parametres frame
		frame.setTitle("Gestion Livre");
		frame.setSize(800, 500);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLocale(java.util.Locale.getDefault());
		frame.getContentPane().setLayout(null);
		
		//parametres composants
		lbl_titreFenetre.setForeground(new Color(100, 149, 237));
		lbl_titreFenetre.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_titreFenetre.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));		
		lbl_NomLivre.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbl_DateApparition.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		table_livres.setRowSelectionAllowed(false);
		table_livres.setEnabled(false);
		lbl_Prix.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		scrollPane.setSize(581, 188);
		scrollPane.setLocation(112, 234);
		scrollPane.setViewportView(table_livres);
		
		//emplacements
		table_livres.setBounds(156, 242, 557, 160);
		rdbtn_AjouterLivre.setBounds(604, 96, 141, 23);
		rdbtn_ModifierLivre.setBounds(604, 122, 141, 23);
		rdbtn_SupprimerLivre.setBounds(604, 148, 141, 23);
		dateApparition.setBounds(201, 122, 203, 20);
		txt_nomLivre.setBounds(200, 93, 204, 20);
		lbl_NomLivre.setBounds(89, 92, 101, 19);
		lbl_DateApparition.setBounds(65, 122, 126, 19);
		lbl_titreFenetre.setBounds(171, 11, 419, 47);
		btn_Appliquer.setBounds(604, 193, 89, 23);
		lbl_Prix.setBounds(146, 153, 45, 19);
		txt_Prix.setBounds(201, 151, 180, 20);
		lbl_Dnt.setBounds(384, 153, 32, 14);
		
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
				ViderChamps();
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
				ViderChamps();
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
				ViderChamps();
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
		
	}
}
