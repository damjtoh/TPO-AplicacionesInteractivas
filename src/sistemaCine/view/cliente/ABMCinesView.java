package sistemaCine.view.cliente;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import sistemaCine.view.admin.adminCines;

public class ABMCinesView extends javax.swing.JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ABMCinesView instancia;
	private JFrame frame;
	private JButton btnEstablecimientos;
	private JButton btnPeliculas;
	private Container lblNombreUsuario;

	
	public static ABMCinesView getInstancia() {
		if (instancia == null) {
			instancia = new ABMCinesView();
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
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				instancia = null;
				frame.dispose();
			}
		});
		btnEstablecimientos = new JButton("Buscar por Establecimientos");
		btnEstablecimientos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEstablecimientos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ABMFuncionesEstablecimientosView.getInstancia().setLocationRelativeTo(null);
				ABMFuncionesEstablecimientosView.getInstancia().setVisible(true);
			}
		});
		btnEstablecimientos.setBounds(12, 137, 408, 67);
		frame.getContentPane().add(btnEstablecimientos);
		
		btnPeliculas = new JButton("Buscar por Peliculas");
		btnPeliculas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPeliculas.setBounds(12, 40, 408, 67);
		frame.getContentPane().add(btnPeliculas);
		btnPeliculas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ABMPeliculasView.getInstancia().setLocationRelativeTo(null);
				ABMPeliculasView.getInstancia().setVisible(true);				
			}
		});
		
		lblNombreUsuario = new JLabel("");
		lblNombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNombreUsuario.setBounds(12, 13, 408, 32);
		frame.getContentPane().add(lblNombreUsuario);
	}
}
