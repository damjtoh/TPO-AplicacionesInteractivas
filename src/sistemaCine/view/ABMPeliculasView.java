package sistemaCine.view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Usuarios.Usuario;

public class ABMPeliculasView extends javax.swing.JFrame{

	private static ABMPeliculasView instancia;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	
	public static ABMPeliculasView getInstancia() {
		if (instancia == null) {
			instancia = new ABMPeliculasView();
		}
		return instancia;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ABMPeliculasView window = new ABMPeliculasView();
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
	public ABMPeliculasView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}



}
