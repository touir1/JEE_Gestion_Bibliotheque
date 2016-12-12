import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Accueil extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7422136070563511862L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Accueil frame = new Accueil();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Accueil() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 264, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Gestion Livre");
		btnNewButton.setBounds(47, 34, 159, 23);
		btnNewButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				GestionLivre gl = new GestionLivre();
				gl.frame.setVisible(true);
			}
			
		});
		contentPane.add(btnNewButton);
		
		JButton btnGestionAuteur = new JButton("Gestion Auteur");
		btnGestionAuteur.setBounds(47, 77, 159, 23);
		btnGestionAuteur.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				GestionAuteur ga = new GestionAuteur();
				ga.frame.setVisible(true);
			}
			
		});
		contentPane.add(btnGestionAuteur);
		
		JButton btnGestionEditeur = new JButton("Gestion Editeur");
		btnGestionEditeur.setBounds(47, 122, 159, 23);
		btnGestionEditeur.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				GestionEditeur ge = new GestionEditeur();
				ge.frame.setVisible(true);
			}
			
		});
		contentPane.add(btnGestionEditeur);
		
		JButton btnGestionModeDe = new JButton("Gestion Mode de paiement");
		btnGestionModeDe.setBounds(47, 169, 159, 23);
		btnGestionModeDe.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				GestionModePaiement gmp = new GestionModePaiement();
				gmp.frame.setVisible(true);
			}
			
		});
		contentPane.add(btnGestionModeDe);
		
		JButton btnGestionPromotion = new JButton("Gestion Promotion");
		btnGestionPromotion.setBounds(47, 217, 159, 23);
		btnGestionPromotion.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				GestionPromotion gp = new GestionPromotion();
				gp.frame.setVisible(true);
			}
			
		});
		contentPane.add(btnGestionPromotion);
		
		JButton btnGestionTypeLivre = new JButton("Gestion Type Livre");
		btnGestionTypeLivre.setBounds(47, 268, 159, 23);
		btnGestionTypeLivre.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				GestionTypeLivre gtp = new GestionTypeLivre();
				gtp.frame.setVisible(true);
			}
			
		});
		contentPane.add(btnGestionTypeLivre);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setBounds(47, 315, 159, 23);
		btnQuitter.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
			
		});
		contentPane.add(btnQuitter);
	}
}
