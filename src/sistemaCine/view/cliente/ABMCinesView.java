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

import sistemaCine.utils.GeneralFrame;
import sistemaCine.view.admin.adminCinesView;

public class ABMCinesView extends GeneralFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ABMCinesView instancia;
	private JFrame frame;
	private JButton btnEstablecimientos;
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
		super.frame = frame;
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	protected void initialize() {
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
				ABMFuncionesEstablecimientosView.getInstancia();
			}
		});
		btnEstablecimientos.setBounds(12, 83, 408, 67);
		frame.getContentPane().add(btnEstablecimientos);
		
		lblNombreUsuario = new JLabel("");
		lblNombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNombreUsuario.setBounds(12, 13, 408, 32);
		frame.getContentPane().add(lblNombreUsuario);
	}
}
