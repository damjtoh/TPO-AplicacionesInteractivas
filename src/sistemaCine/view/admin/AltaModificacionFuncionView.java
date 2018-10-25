package sistemaCine.view.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;

import sistemaCine.clases.Funcion;
import sistemaCine.clases.Sala;

public class AltaModificacionFuncionView extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static AltaModificacionFuncionView instancia;
	private static Funcion funcion;
	private JFrame frame;
	
	
	
	public static AltaModificacionFuncionView getInstancia(Funcion f) {
		funcion= f;
		if (instancia == null) {
			instancia = new AltaModificacionFuncionView();
		}
		return instancia;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaModificacionFuncionView window = new AltaModificacionFuncionView();
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
	public AltaModificacionFuncionView() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}

}
