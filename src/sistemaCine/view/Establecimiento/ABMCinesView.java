package sistemaCine.view.Establecimiento;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class ABMCinesView {

	private JFrame frame;
	private JButton btnEstablecimientos;
	private JButton btnPeliculas;
	private Container lblNombreUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ABMCinesView window = new ABMCinesView();
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
	public ABMCinesView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnEstablecimientos = new JButton("Buscar por Establecimientos");
		btnEstablecimientos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEstablecimientos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnEstablecimientos.setBounds(12, 153, 408, 67);
		frame.getContentPane().add(btnEstablecimientos);
		
		btnPeliculas = new JButton("Buscar por Peliculas");
		btnPeliculas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPeliculas.setBounds(12, 73, 408, 67);
		frame.getContentPane().add(btnPeliculas);
		
		lblNombreUsuario = new JLabel("");
		lblNombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNombreUsuario.setBounds(12, 13, 408, 32);
		frame.getContentPane().add(lblNombreUsuario);
	}
}
