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

import metier.entities.ModePaiement;
import metier.sessions.IBiblioRemote;
import javax.swing.JButton;

public class GestionModePaiement {
	
	public JFrame frame;
	private JTextField txt_nomModePaiement;
	private JTable table_modePaiements;
	private JRadioButton rdbtn_AjouterModePaiement;
	private JRadioButton rdbtn_ModifierModePaiement;
	private JRadioButton rdbtn_SupprimerModePaiement;
	private JLabel lbl_titreFenetre;
	private JLabel lbl_NomModePaiement;
	private JButton btn_Appliquer;
	private DefaultTableModel table_model;
	private JScrollPane scrollPane;
	private JLabel lbl_Activer;
	private JRadioButton rdbtn_Oui;
	private JRadioButton rdbtn_Non;
	
	private IBiblioRemote stub;
	
	private int selected;
	private List<ModePaiement> modePaiements;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionModePaiement window = new GestionModePaiement();
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
	public GestionModePaiement() {
		//connection
		stub = ClientEJB.getStub();
		
		initialize();
		PreExecution();
		
	}
	
	/**
	 * to execute after the frame.show
	 */
	private void PreExecution(){
		
		modePaiements = stub.consulterModePaiements();
		
		if(table_model.getColumnCount()==0){
			table_model.addColumn("ID_ModePaiement");
			table_model.addColumn("Nom du mode de paiement");
			table_model.addColumn("Est active");
		}
		
		while(table_model.getRowCount()!=0)
			table_model.removeRow(0);
		
		for(int i=0;i<modePaiements.size();i++){
			String[] row = new String[3];
			if(modePaiements.get(i).getId().toString()==null) row[0]="NULL";
			else row[0]=modePaiements.get(i).getId().toString();
			if(modePaiements.get(i).getName()==null) row[1]="NULL";
			else row[1]=modePaiements.get(i).getName();
			if(modePaiements.get(i).isActive()) row[2]="True";
			else row[2]="False";
			table_model.addRow(row);
		}
		
	}
	
	private void ViderChamps(){
		txt_nomModePaiement.setText("");
		rdbtn_Oui.setSelected(false);
		rdbtn_Non.setSelected(true);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		
		//Instantiation + initialization
		frame = new JFrame();
		lbl_titreFenetre = new JLabel("Gestion des modes de paiements");
		lbl_NomModePaiement = new JLabel("Nom du mode de paiement :");
		txt_nomModePaiement = new JTextField();
		rdbtn_AjouterModePaiement = new JRadioButton("Ajouter ModePaiement");
		rdbtn_ModifierModePaiement = new JRadioButton("Modifier ModePaiement");
		rdbtn_SupprimerModePaiement = new JRadioButton("Supprimer ModePaiement");
		table_model = new DefaultTableModel(){
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		table_modePaiements = new JTable(table_model);
		btn_Appliquer = new JButton("Appliquer");
		scrollPane = new JScrollPane();
		selected = -1;
		lbl_Activer = new JLabel("Activer :");
		rdbtn_Non = new JRadioButton("Non");
		rdbtn_Oui = new JRadioButton("Oui");
		
		//parametres frame
		frame.setTitle("Gestion ModePaiement");
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
		lbl_NomModePaiement.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		table_modePaiements.setRowSelectionAllowed(false);
		table_modePaiements.setEnabled(false);
		scrollPane.setSize(668, 188);
		scrollPane.setLocation(65, 247);
		scrollPane.setViewportView(table_modePaiements);
		rdbtn_AjouterModePaiement.setSelected(true);
		rdbtn_Oui.setSelected(false);
		rdbtn_Non.setSelected(true);
		lbl_Activer.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		//emplacements
		table_modePaiements.setBounds(156, 242, 557, 160);
		rdbtn_AjouterModePaiement.setBounds(604, 96, 141, 23);
		rdbtn_ModifierModePaiement.setBounds(604, 122, 141, 23);
		rdbtn_SupprimerModePaiement.setBounds(604, 148, 153, 23);
		txt_nomModePaiement.setBounds(200, 93, 204, 20);
		lbl_NomModePaiement.setBounds(10, 92, 180, 19);
		lbl_titreFenetre.setBounds(171, 11, 419, 47);
		btn_Appliquer.setBounds(604, 193, 89, 23);
		lbl_Activer.setBounds(129, 126, 61, 19);
		rdbtn_Oui.setBounds(200, 126, 89, 23);
		rdbtn_Non.setBounds(315, 126, 89, 23);
		
		//listeners
		rdbtn_AjouterModePaiement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtn_AjouterModePaiement.setSelected(true);
				rdbtn_ModifierModePaiement.setSelected(false);
				rdbtn_SupprimerModePaiement.setSelected(false);
				
				table_modePaiements.setRowSelectionAllowed(false);
				table_modePaiements.setEnabled(false);
				table_modePaiements.getSelectionModel().clearSelection();
				selected = -1;
				ViderChamps();
			}
		});
		
		rdbtn_ModifierModePaiement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtn_AjouterModePaiement.setSelected(false);
				rdbtn_ModifierModePaiement.setSelected(true);
				rdbtn_SupprimerModePaiement.setSelected(false);
				
				table_modePaiements.setRowSelectionAllowed(true);
				table_modePaiements.setEnabled(true);
				table_modePaiements.getSelectionModel().clearSelection();
				selected = -1;
				ViderChamps();
			}
		});
		
		rdbtn_SupprimerModePaiement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtn_AjouterModePaiement.setSelected(false);
				rdbtn_ModifierModePaiement.setSelected(false);
				rdbtn_SupprimerModePaiement.setSelected(true);
				
				table_modePaiements.setRowSelectionAllowed(true);
				table_modePaiements.setEnabled(true);
				table_modePaiements.getSelectionModel().clearSelection();
				selected = -1;
				ViderChamps();
			}
		});
		
		rdbtn_Oui.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				rdbtn_Oui.setSelected(true);
				rdbtn_Non.setSelected(false);
			}
		});
		
		rdbtn_Non.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				rdbtn_Oui.setSelected(false);
				rdbtn_Non.setSelected(true);
			}
		});
		ListSelectionModel mod = table_modePaiements.getSelectionModel();
		mod.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				
				selected=table_modePaiements.getSelectedRow();
				if(selected>=0){
					ModePaiement l = modePaiements.get(selected);
					txt_nomModePaiement.setText(l.getName());
					rdbtn_Oui.setSelected(l.isActive());
					rdbtn_Non.setSelected(!l.isActive());
				}
				
			}
			
		});
		
		btn_Appliquer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtn_AjouterModePaiement.isSelected()){
					if(txt_nomModePaiement.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Veuillez remplir tout les champs", "Warning", JOptionPane.WARNING_MESSAGE);
					else{
						ModePaiement l = new ModePaiement(txt_nomModePaiement.getText());
						l.setActive(rdbtn_Oui.isSelected());
						stub.ajouterModePaiement(l);
						PreExecution();
						ViderChamps();
						JOptionPane.showMessageDialog(null, "Le mode de paiement a été ajouté", "Information", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else if(rdbtn_ModifierModePaiement.isSelected()){
					if(selected>=0){
						if(txt_nomModePaiement.getText().equals(""))
							JOptionPane.showMessageDialog(null, "Veuillez remplir tout les champs", "Warning", JOptionPane.WARNING_MESSAGE);
						else{
							ModePaiement l = modePaiements.get(selected);
							l.setName(txt_nomModePaiement.getText());
							l.setActive(rdbtn_Oui.isSelected());
							stub.modifierModePaiement(l);
							PreExecution();
							ViderChamps();
							JOptionPane.showMessageDialog(null, "Le mode de paiement a été modifié", "Information", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Veuillez selectionner un editeur à modifier", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
				else if(rdbtn_SupprimerModePaiement.isSelected()){
					if(selected>=0){
						ModePaiement l = modePaiements.get(selected);
						stub.supprimerModePaiement(l.getId());
						PreExecution();
						ViderChamps();
						JOptionPane.showMessageDialog(null, "Le mode de paiement a été supprimé", "Information", JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null, "Veuillez selectionner un mode de paiement à supprimer", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		
		//Ajout au frame
		frame.getContentPane().add(lbl_titreFenetre);
		frame.getContentPane().add(rdbtn_AjouterModePaiement);
		frame.getContentPane().add(rdbtn_ModifierModePaiement);
		frame.getContentPane().add(rdbtn_SupprimerModePaiement);
		frame.getContentPane().add(scrollPane);
		frame.getContentPane().add(txt_nomModePaiement);
		frame.getContentPane().add(lbl_NomModePaiement);
		frame.getContentPane().add(btn_Appliquer);
		frame.getContentPane().add(rdbtn_Non);
		frame.getContentPane().add(rdbtn_Oui);
		frame.getContentPane().add(lbl_Activer);
	}
}
