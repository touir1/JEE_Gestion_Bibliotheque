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

import metier.entities.Editeur;
import metier.sessions.IBiblioRemote;
import javax.swing.JButton;

public class GestionEditeur {
	
	public JFrame frame;
	private JTextField txt_nomEditeur;
	private JTable table_editeurs;
	private JRadioButton rdbtn_AjouterEditeur;
	private JRadioButton rdbtn_ModifierEditeur;
	private JRadioButton rdbtn_SupprimerEditeur;
	private JLabel lbl_titreFenetre;
	private JLabel lbl_NomEditeur;
	private JButton btn_Appliquer;
	private DefaultTableModel table_model;
	private JScrollPane scrollPane;
	
	private IBiblioRemote stub;
	
	private int selected;
	private List<Editeur> editeurs;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionEditeur window = new GestionEditeur();
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
	public GestionEditeur() {
		//connection
		stub = ClientEJB.getStub();
		
		initialize();
		PreExecution();
		
	}
	
	/**
	 * to execute after the frame.show
	 */
	private void PreExecution(){
		
		editeurs = stub.consulterEditeurs();
		
		if(table_model.getColumnCount()==0){
			table_model.addColumn("ID_Editeur");
			table_model.addColumn("Nom de l'editeur");
		}
		
		while(table_model.getRowCount()!=0)
			table_model.removeRow(0);
		
		for(int i=0;i<editeurs.size();i++){
			String[] row = new String[2];
			if(editeurs.get(i).getID_editeur().toString()==null) row[0]="NULL";
			else row[0]=editeurs.get(i).getID_editeur().toString();
			if(editeurs.get(i).getNom()==null) row[1]="NULL";
			else row[1]=editeurs.get(i).getNom();
			table_model.addRow(row);
		}
		
	}
	
	private void ViderChamps(){
		txt_nomEditeur.setText("");
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		
		//Instantiation + initialization
		frame = new JFrame();
		lbl_titreFenetre = new JLabel("Gestion des editeurs");
		lbl_NomEditeur = new JLabel("Nom de l'editeur :");
		txt_nomEditeur = new JTextField();
		rdbtn_AjouterEditeur = new JRadioButton("Ajouter Editeur");
		rdbtn_ModifierEditeur = new JRadioButton("Modifier Editeur");
		rdbtn_SupprimerEditeur = new JRadioButton("Supprimer Editeur");
		table_model = new DefaultTableModel(){
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		table_editeurs = new JTable(table_model);
		btn_Appliquer = new JButton("Appliquer");
		scrollPane = new JScrollPane();
		selected = -1;
		
		//parametres frame
		frame.setTitle("Gestion Editeur");
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
		lbl_NomEditeur.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		table_editeurs.setRowSelectionAllowed(false);
		table_editeurs.setEnabled(false);
		scrollPane.setSize(668, 188);
		scrollPane.setLocation(65, 247);
		scrollPane.setViewportView(table_editeurs);
		rdbtn_AjouterEditeur.setSelected(true);
		
		//emplacements
		table_editeurs.setBounds(156, 242, 557, 160);
		rdbtn_AjouterEditeur.setBounds(604, 96, 141, 23);
		rdbtn_ModifierEditeur.setBounds(604, 122, 141, 23);
		rdbtn_SupprimerEditeur.setBounds(604, 148, 141, 23);
		txt_nomEditeur.setBounds(200, 93, 204, 20);
		lbl_NomEditeur.setBounds(80, 92, 110, 19);
		lbl_titreFenetre.setBounds(171, 11, 419, 47);
		btn_Appliquer.setBounds(604, 193, 89, 23);
		
		//listeners
		rdbtn_AjouterEditeur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtn_AjouterEditeur.setSelected(true);
				rdbtn_ModifierEditeur.setSelected(false);
				rdbtn_SupprimerEditeur.setSelected(false);
				
				table_editeurs.setRowSelectionAllowed(false);
				table_editeurs.setEnabled(false);
				table_editeurs.getSelectionModel().clearSelection();
				selected = -1;
				ViderChamps();
			}
		});
		
		rdbtn_ModifierEditeur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtn_AjouterEditeur.setSelected(false);
				rdbtn_ModifierEditeur.setSelected(true);
				rdbtn_SupprimerEditeur.setSelected(false);
				
				table_editeurs.setRowSelectionAllowed(true);
				table_editeurs.setEnabled(true);
				table_editeurs.getSelectionModel().clearSelection();
				selected = -1;
				ViderChamps();
			}
		});
		
		rdbtn_SupprimerEditeur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtn_AjouterEditeur.setSelected(false);
				rdbtn_ModifierEditeur.setSelected(false);
				rdbtn_SupprimerEditeur.setSelected(true);
				
				table_editeurs.setRowSelectionAllowed(true);
				table_editeurs.setEnabled(true);
				table_editeurs.getSelectionModel().clearSelection();
				selected = -1;
				ViderChamps();
			}
		});
		
		ListSelectionModel mod = table_editeurs.getSelectionModel();
		mod.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				
				selected=table_editeurs.getSelectedRow();
				if(selected>=0){
					Editeur l = editeurs.get(selected);
					txt_nomEditeur.setText(l.getNom());
				}
				
			}
			
		});
		
		btn_Appliquer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtn_AjouterEditeur.isSelected()){
					if(txt_nomEditeur.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Veuillez remplir tout les champs", "Warning", JOptionPane.WARNING_MESSAGE);
					else{
						Editeur l = new Editeur(txt_nomEditeur.getText());
						stub.ajouterEditeur(l);
						PreExecution();
						ViderChamps();
						JOptionPane.showMessageDialog(null, "L'editeur a été ajouté", "Information", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else if(rdbtn_ModifierEditeur.isSelected()){
					if(selected>=0){
						if(txt_nomEditeur.getText().equals(""))
							JOptionPane.showMessageDialog(null, "Veuillez remplir tout les champs", "Warning", JOptionPane.WARNING_MESSAGE);
						else{
							Editeur l = editeurs.get(selected);
							l.setNom(txt_nomEditeur.getText());
							stub.modifierEditeur(l);
							PreExecution();
							ViderChamps();
							JOptionPane.showMessageDialog(null, "L'editeur a été modifié", "Information", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Veuillez selectionner un editeur à modifier", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
				else if(rdbtn_SupprimerEditeur.isSelected()){
					if(selected>=0){
						Editeur l = editeurs.get(selected);
						stub.supprimerEditeur(l.getID_editeur());
						PreExecution();
						ViderChamps();
						JOptionPane.showMessageDialog(null, "L'editeur a été supprimé", "Information", JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null, "Veuillez selectionner un editeur à supprimer", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		
		//Ajout au frame
		frame.getContentPane().add(lbl_titreFenetre);
		frame.getContentPane().add(rdbtn_AjouterEditeur);
		frame.getContentPane().add(rdbtn_ModifierEditeur);
		frame.getContentPane().add(rdbtn_SupprimerEditeur);
		frame.getContentPane().add(scrollPane);
		frame.getContentPane().add(txt_nomEditeur);
		frame.getContentPane().add(lbl_NomEditeur);
		frame.getContentPane().add(btn_Appliquer);
		
	}
}
