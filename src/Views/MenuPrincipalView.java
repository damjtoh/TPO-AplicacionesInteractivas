package Views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Usuarios.SistemaUsuarios;
import Usuarios.Usuario;

public class MenuPrincipalView extends JFrame {

	private JPanel contentPane;
	JMenuBar menuBar = new JMenuBar();
	private JMenu menuInicio = new JMenu("Inicio");
	JMenu mnUsuarios = new JMenu("Usuarios");
	JPanel altaUsuarioPanel = new AltaUsuarioPanel();
	JPanel currentPanel;
	private MenuPrincipalView that = this;
	private LoginPanel loginPanel = new LoginPanel(this);
	private static MenuPrincipalView instancia;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipalView window = new MenuPrincipalView();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static MenuPrincipalView getInstancia()
	{
		if (instancia == null)
			instancia = new MenuPrincipalView();
		return instancia;
	}

	public MenuPrincipalView() {
		instancia = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 494, 459);
		setJMenuBar(menuBar);

		menuBar.add(menuInicio);
		
		JMenuItem mntmIniciarSesion = new JMenuItem("Iniciar Sesi\u00F3n ");
		mntmIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				that.setPanel(loginPanel);
			}
		});
		menuInicio.add(mntmIniciarSesion);
		

		JMenuItem mntmAltausuario = new JMenuItem("Registrar");
		mntmAltausuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				that.setPanel(that.altaUsuarioPanel);
			}
		});
		menuInicio.add(mntmAltausuario);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		altaUsuarioPanel.setVisible(false);
		contentPane.add(altaUsuarioPanel);


		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		menuBar.add(mnUsuarios);
		this.mnUsuarios.setVisible(false);
		
		menuBar.add(btnSalir);
		
		getContentPane().add(loginPanel);
		this.setPanel(loginPanel);
		System.out.println(this.getContentPane().getName());


	}
	
	public void setPanel(JPanel nextPanel) {

		if (this.currentPanel != null) {
			//this.remove(this.currentPanel);
			this.currentPanel.setVisible(false);
		}
		getContentPane().add(nextPanel);
		this.currentPanel = nextPanel;
		this.currentPanel.setVisible(true);
	}
	
	
	
	public void setMenuAfterLogin() {
		//this.menuBar.remove(this.menuInicio);
		this.menuInicio.setVisible(false);
		this.mnUsuarios.setVisible(true);
		
		this.setPanel(new BienvenidoPanel(SistemaUsuarios.getInstancia().getUsuarioLogueado()));
		
		JMenuItem mntmCrearUsuario = new JMenuItem("Crear usuario");
		mnUsuarios.add(mntmCrearUsuario);
		mntmCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				that.setPanel(new AltaUsuarioPanel());
			}
		});
		
		JMenuItem mntmModificarUsuario = new JMenuItem("Gestionar usuarios");
		mnUsuarios.add(mntmModificarUsuario);
		mntmModificarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				that.setPanel(new GestionarUsuarioPanel());
			}
		});
		
	}
}
