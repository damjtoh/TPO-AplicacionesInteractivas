package sistemaCine.view.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

public class adminCines {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminCines window = new adminCines();
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
	public adminCines() {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnBuscarEstablecimiento = new JButton("Buscar Establecimiento");
		btnBuscarEstablecimiento.setBounds(12, 62, 117, 25);
		frame.getContentPane().add(btnBuscarEstablecimiento);
	}

}
