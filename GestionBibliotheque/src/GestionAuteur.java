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

import java.util.List;

import metier.entities.Auteur;
import metier.sessions.IBiblioRemote;
import javax.swing.JButton;

public class GestionAuteur {
	
	public JFrame frame;
	private JTextField txt_nomAuteur;
	private JTextField txt_prenomAuteur;
	private JTable table_auteurs;
	private JRadioButton rdbtn_AjouterAuteur;
	private JRadioButton rdbtn_ModifierAuteur;
	private JRadioButton rdbtn_SupprimerAuteur;
	private JLabel lbl_titreFenetre;
	private JLabel lbl_NomAuteur;
	private JLabel lbl_PrenomAuteur;
	private JButton btn_Appliquer;
	private DefaultTableModel table_model;
	private JScrollPane scrollPane;
	
	private IBiblioRemote stub;
	
	private int selected;
	private List<Auteur> auteurs;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionAuteur window = new GestionAuteur();
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
	public GestionAuteur() {
		//connection
		stub = ClientEJB.getStub();
		
		initialize();
		PreExecution();
		
	}
	
	/**
	 * to execute after the frame.show
	 */
	private void PreExecution(){
		
		auteurs = stub.consulterAuteurs();
		
		if(table_model.getColumnCount()==0){
			table_model.addColumn("ID_auteur");
			table_model.addColumn("Nom de l'auteur");
			table_model.addColumn("Prenom de l'auteur");
		}
		
		while(table_model.getRowCount()!=0)
			table_model.removeRow(0);
		
		for(int i=0;i<auteurs.size();i++){
			String[] row = new String[3];
			if(auteurs.get(i).getID_auteur().toString()==null) row[0]="NULL";
			else row[0]=auteurs.get(i).getID_auteur().toString();
			if(auteurs.get(i).getNom()==null) row[1]="NULL";
			else row[1]=auteurs.get(i).getNom();
			if(auteurs.get(i).getPrenom()==null) row[2]="NULL";
			else row[2]=auteurs.get(i).getPrenom();
			table_model.addRow(row);
		}
		
	}
	
	private void ViderChamps(){
		txt_nomAuteur.setText("");
		txt_prenomAuteur.setText("");
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		
		//Instantiation + initialization
		frame = new JFrame();
		lbl_titreFenetre = new JLabel("Gestion des auteurs");
		lbl_NomAuteur = new JLabel("Nom de l'auteur :");
		lbl_PrenomAuteur = new JLabel("Prenom de l'auteur :");
		txt_nomAuteur = new JTextField();
		txt_prenomAuteur = new JTextField();
		rdbtn_AjouterAuteur = new JRadioButton("Ajouter Auteur");
		rdbtn_ModifierAuteur = new JRadioButton("Modifier Auteur");
		rdbtn_SupprimerAuteur = new JRadioButton("Supprimer Auteur");
		table_model = new DefaultTableModel(){
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		table_auteurs = new JTable(table_model);
		btn_Appliquer = new JButton("Appliquer");
		scrollPane = new JScrollPane();
		selected = -1;
		
		//parametres frame
		frame.setTitle("Gestion Auteur");
		frame.setSize(800, 500);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLocale(java.util.Locale.getDefault());
		frame.getContentPane().setLayout(null);
		
		//parametres composants
		lbl_titreFenetre.setForeground(new Color(100, 149, 237));
		lbl_titreFenetre.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_titreFenetre.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));		
		lbl_NomAuteur.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbl_PrenomAuteur.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		table_auteurs.setRowSelectionAllowed(false);
		table_auteurs.setEnabled(false);
		scrollPane.setSize(668, 188);
		scrollPane.setLocation(65, 247);
		scrollPane.setViewportView(table_auteurs);
		rdbtn_AjouterAuteur.setSelected(true);
		
		//emplacements
		table_auteurs.setBounds(156, 242, 557, 160);
		rdbtn_AjouterAuteur.setBounds(604, 96, 141, 23);
		rdbtn_ModifierAuteur.setBounds(604, 122, 141, 23);
		rdbtn_SupprimerAuteur.setBounds(604, 148, 141, 23);
		txt_prenomAuteur.setBounds(201, 122, 203, 20);
		txt_nomAuteur.setBounds(200, 93, 204, 20);
		lbl_NomAuteur.setBounds(80, 92, 110, 19);
		lbl_PrenomAuteur.setBounds(65, 122, 126, 19);
		lbl_titreFenetre.setBounds(171, 11, 419, 47);
		btn_Appliquer.setBounds(604, 193, 89, 23);
		
		//listeners
		rdbtn_AjouterAuteur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtn_AjouterAuteur.setSelected(true);
				rdbtn_ModifierAuteur.setSelected(false);
				rdbtn_SupprimerAuteur.setSelected(false);
				
				table_auteurs.setRowSelectionAllowed(false);
				table_auteurs.setEnabled(false);
				table_auteurs.getSelectionModel().clearSelection();
				selected = -1;
				ViderChamps();
			}
		});
		
		rdbtn_ModifierAuteur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtn_AjouterAuteur.setSelected(false);
				rdbtn_ModifierAuteur.setSelected(true);
				rdbtn_SupprimerAuteur.setSelected(false);
				
				table_auteurs.setRowSelectionAllowed(true);
				table_auteurs.setEnabled(true);
				table_auteurs.getSelectionModel().clearSelection();
				selected = -1;
				ViderChamps();
			}
		});
		
		rdbtn_SupprimerAuteur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtn_AjouterAuteur.setSelected(false);
				rdbtn_ModifierAuteur.setSelected(false);
				rdbtn_SupprimerAuteur.setSelected(true);
				
				table_auteurs.setRowSelectionAllowed(true);
				table_auteurs.setEnabled(true);
				table_auteurs.getSelectionModel().clearSelection();
				selected = -1;
				ViderChamps();
			}
		});
		
		ListSelectionModel mod = table_auteurs.getSelectionModel();
		mod.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				
				selected=table_auteurs.getSelectedRow();
				if(selected>=0){
					Auteur l = auteurs.get(selected);
					txt_nomAuteur.setText(l.getNom());
					txt_prenomAuteur.setText(l.getPrenom());
				}
				
			}
			
		});
		
		btn_Appliquer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtn_AjouterAuteur.isSelected()){
					if(txt_nomAuteur.getText().equals("")
						|| txt_prenomAuteur.getText().equals("")
					)
						JOptionPane.showMessageDialog(null, "Veuillez remplir tout les champs", "Warning", JOptionPane.WARNING_MESSAGE);
					else{
						Auteur l = new Auteur(txt_nomAuteur.getText(),txt_prenomAuteur.getText());
						stub.addAuteur(l);
						PreExecution();
						ViderChamps();
						JOptionPane.showMessageDialog(null, "L'auteur a été ajouté", "Information", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else if(rdbtn_ModifierAuteur.isSelected()){
					if(selected>=0){
						if(txt_nomAuteur.getText().equals("")
								|| txt_prenomAuteur.getText().equals("")
						)
							JOptionPane.showMessageDialog(null, "Veuillez remplir tout les champs", "Warning", JOptionPane.WARNING_MESSAGE);
						else{
							Auteur l = auteurs.get(selected);
							l.setNom(txt_nomAuteur.getText());
							l.setPrenom(txt_prenomAuteur.getText());
							stub.updateAuteur(l);
							PreExecution();
							ViderChamps();
							JOptionPane.showMessageDialog(null, "L'auteur a été modifié", "Information", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Veuillez selectionner un auteur à modifier", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
				else if(rdbtn_SupprimerAuteur.isSelected()){
					if(selected>=0){
						Auteur l = auteurs.get(selected);
						stub.supprimerAuteur(l.getID_auteur());
						PreExecution();
						ViderChamps();
						JOptionPane.showMessageDialog(null, "L'auteur a été supprimé", "Information", JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null, "Veuillez selectionner un auteur à supprimer", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		
		//Ajout au frame
		frame.getContentPane().add(lbl_titreFenetre);
		frame.getContentPane().add(rdbtn_AjouterAuteur);
		frame.getContentPane().add(rdbtn_ModifierAuteur);
		frame.getContentPane().add(rdbtn_SupprimerAuteur);
		frame.getContentPane().add(scrollPane);
		frame.getContentPane().add(txt_nomAuteur);
		frame.getContentPane().add(txt_prenomAuteur);
		frame.getContentPane().add(lbl_NomAuteur);
		frame.getContentPane().add(lbl_PrenomAuteur);
		frame.getContentPane().add(btn_Appliquer);
		
	}
}
