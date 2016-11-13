import java.awt.EventQueue;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.swing.JFrame;

import metier.sessions.IBiblioRemote;

public class GestionAuteur {

	private JFrame frame;
	private IBiblioRemote stub;

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
		ClientEJB.initialisation();
		stub = ClientEJB.getStub();
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//initialisation + instantiation
		frame = new JFrame();
		
		//parametres frame
		frame.setTitle("Gestion Livre");
		frame.setSize(800, 500);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLocale(java.util.Locale.getDefault());
		frame.getContentPane().setLayout(null);
		
		//placements
		frame.setBounds(100, 100, 450, 300);
		
		//configuration
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
