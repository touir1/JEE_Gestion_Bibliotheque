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

import metier.entities.TypeLivre;
import metier.sessions.IBiblioRemote;
import javax.swing.JButton;

public class GestionTypeLivre {
	
	public JFrame frame;
	private JTextField txt_nomTypeLivre;
	private JTable table_typeLivres;
	private JRadioButton rdbtn_AjouterTypeLivre;
	private JRadioButton rdbtn_ModifierTypeLivre;
	private JRadioButton rdbtn_SupprimerTypeLivre;
	private JLabel lbl_titreFenetre;
	private JLabel lbl_NomTypeLivre;
	private JButton btn_Appliquer;
	private DefaultTableModel table_model;
	private JScrollPane scrollPane;
	
	private IBiblioRemote stub;
	
	private int selected;
	private List<TypeLivre> typeLivres;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionTypeLivre window = new GestionTypeLivre();
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
	public GestionTypeLivre() {
		//connection
		stub = ClientEJB.getStub();
		
		initialize();
		PreExecution();
		
	}
	
	/**
	 * to execute after the frame.show
	 */
	private void PreExecution(){
		
		typeLivres = stub.consulterTypeLivres();
		
		if(table_model.getColumnCount()==0){
			table_model.addColumn("ID_TypeLivre");
			table_model.addColumn("Nom du type livre");
		}
		
		while(table_model.getRowCount()!=0)
			table_model.removeRow(0);
		
		for(int i=0;i<typeLivres.size();i++){
			String[] row = new String[2];
			if(typeLivres.get(i).getId().toString()==null) row[0]="NULL";
			else row[0]=typeLivres.get(i).getId().toString();
			if(typeLivres.get(i).getNom()==null) row[1]="NULL";
			else row[1]=typeLivres.get(i).getNom();
			table_model.addRow(row);
		}
		
	}
	
	private void ViderChamps(){
		txt_nomTypeLivre.setText("");
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		
		//Instantiation + initialization
		frame = new JFrame();
		lbl_titreFenetre = new JLabel("Gestion des type de livres");
		lbl_NomTypeLivre = new JLabel("Nom du type livre :");
		txt_nomTypeLivre = new JTextField();
		rdbtn_AjouterTypeLivre = new JRadioButton("Ajouter TypeLivre");
		rdbtn_ModifierTypeLivre = new JRadioButton("Modifier TypeLivre");
		rdbtn_SupprimerTypeLivre = new JRadioButton("Supprimer TypeLivre");
		table_model = new DefaultTableModel(){
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		table_typeLivres = new JTable(table_model);
		btn_Appliquer = new JButton("Appliquer");
		scrollPane = new JScrollPane();
		selected = -1;
		
		//parametres frame
		frame.setTitle("Gestion TypeLivre");
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
		lbl_NomTypeLivre.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		table_typeLivres.setRowSelectionAllowed(false);
		table_typeLivres.setEnabled(false);
		scrollPane.setSize(668, 188);
		scrollPane.setLocation(65, 247);
		scrollPane.setViewportView(table_typeLivres);
		rdbtn_AjouterTypeLivre.setSelected(true);
		
		//emplacements
		table_typeLivres.setBounds(156, 242, 557, 160);
		rdbtn_AjouterTypeLivre.setBounds(604, 96, 141, 23);
		rdbtn_ModifierTypeLivre.setBounds(604, 122, 141, 23);
		rdbtn_SupprimerTypeLivre.setBounds(604, 148, 141, 23);
		txt_nomTypeLivre.setBounds(200, 93, 204, 20);
		lbl_NomTypeLivre.setBounds(65, 92, 125, 19);
		lbl_titreFenetre.setBounds(171, 11, 419, 47);
		btn_Appliquer.setBounds(604, 193, 89, 23);
		
		//listeners
		rdbtn_AjouterTypeLivre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtn_AjouterTypeLivre.setSelected(true);
				rdbtn_ModifierTypeLivre.setSelected(false);
				rdbtn_SupprimerTypeLivre.setSelected(false);
				
				table_typeLivres.setRowSelectionAllowed(false);
				table_typeLivres.setEnabled(false);
				table_typeLivres.getSelectionModel().clearSelection();
				selected = -1;
				ViderChamps();
			}
		});
		
		rdbtn_ModifierTypeLivre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtn_AjouterTypeLivre.setSelected(false);
				rdbtn_ModifierTypeLivre.setSelected(true);
				rdbtn_SupprimerTypeLivre.setSelected(false);
				
				table_typeLivres.setRowSelectionAllowed(true);
				table_typeLivres.setEnabled(true);
				table_typeLivres.getSelectionModel().clearSelection();
				selected = -1;
				ViderChamps();
			}
		});
		
		rdbtn_SupprimerTypeLivre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtn_AjouterTypeLivre.setSelected(false);
				rdbtn_ModifierTypeLivre.setSelected(false);
				rdbtn_SupprimerTypeLivre.setSelected(true);
				
				table_typeLivres.setRowSelectionAllowed(true);
				table_typeLivres.setEnabled(true);
				table_typeLivres.getSelectionModel().clearSelection();
				selected = -1;
				ViderChamps();
			}
		});
		
		ListSelectionModel mod = table_typeLivres.getSelectionModel();
		mod.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				
				selected=table_typeLivres.getSelectedRow();
				if(selected>=0){
					TypeLivre l = typeLivres.get(selected);
					txt_nomTypeLivre.setText(l.getNom());
				}
				
			}
			
		});
		
		btn_Appliquer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtn_AjouterTypeLivre.isSelected()){
					if(txt_nomTypeLivre.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Veuillez remplir tout les champs", "Warning", JOptionPane.WARNING_MESSAGE);
					else{
						TypeLivre l = new TypeLivre(txt_nomTypeLivre.getText());
						stub.addTypeLivre(l);
						PreExecution();
						ViderChamps();
						JOptionPane.showMessageDialog(null, "Le type livre a été ajouté", "Information", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else if(rdbtn_ModifierTypeLivre.isSelected()){
					if(selected>=0){
						if(txt_nomTypeLivre.getText().equals(""))
							JOptionPane.showMessageDialog(null, "Veuillez remplir tout les champs", "Warning", JOptionPane.WARNING_MESSAGE);
						else{
							TypeLivre l = typeLivres.get(selected);
							l.setNom(txt_nomTypeLivre.getText());
							stub.updateTypeLivre(l);
							PreExecution();
							ViderChamps();
							JOptionPane.showMessageDialog(null, "Le type livre a été modifié", "Information", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Veuillez selectionner un type livre à modifier", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
				else if(rdbtn_SupprimerTypeLivre.isSelected()){
					if(selected>=0){
						TypeLivre l = typeLivres.get(selected);
						stub.supprimerTypeLivre(l.getId());
						PreExecution();
						ViderChamps();
						JOptionPane.showMessageDialog(null, "L'type livre a été supprimé", "Information", JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null, "Veuillez selectionner un type livre à supprimer", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		
		//Ajout au frame
		frame.getContentPane().add(lbl_titreFenetre);
		frame.getContentPane().add(rdbtn_AjouterTypeLivre);
		frame.getContentPane().add(rdbtn_ModifierTypeLivre);
		frame.getContentPane().add(rdbtn_SupprimerTypeLivre);
		frame.getContentPane().add(scrollPane);
		frame.getContentPane().add(txt_nomTypeLivre);
		frame.getContentPane().add(lbl_NomTypeLivre);
		frame.getContentPane().add(btn_Appliquer);
		
	}
}
