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

import Usuarios.Rol;
import Usuarios.SistemaUsuarios;
import Usuarios.Usuario;
import sistemaCine.view.admin.adminCinesView;
import sistemaCine.view.cliente.ABMFuncionesEstablecimientosView;

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
		
		JButton btnImprimirEntradas = new JButton("Imprimir Entradas");
		btnImprimirEntradas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ImpresionDeEntradasView impresionDeEntradasView = new ImpresionDeEntradasView();
			}
		});
		btnImprimirEntradas.setBounds(165, 324, 146, 25);
		contentPane.add(btnImprimirEntradas);
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
		Usuario usuarioLogueado = SistemaUsuarios.getInstancia().getUsuarioLogueado();
		this.setPanel(new BienvenidoPanel(usuarioLogueado));
		
		
		if (usuarioLogueado.tieneRol(Rol.ADMINISTRADOR_ID)) {			
			this.mnUsuarios.setVisible(true);
			
			
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
		
		if (usuarioLogueado.tieneRol(Rol.CLIENTE_ID)) {
			
			JMenu menuEntradas = new JMenu("Entradas");
			JMenuItem subMenuComprarEntradas = new JMenuItem("Comprar entradas");
			menuEntradas.add(subMenuComprarEntradas);
			subMenuComprarEntradas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ABMFuncionesEstablecimientosView.getInstancia(usuarioLogueado.getRolesIds().contains(1));
				}
			});
			this.menuBar.add(menuEntradas);
		}
		
		if (usuarioLogueado.tieneRol(Rol.VENDEDOR_ID)) {
			
			JMenu menuVentas = new JMenu("Ventas");
			JMenuItem subMenuVenderEntrada = new JMenuItem("Nueva venta");
			menuVentas.add(subMenuVenderEntrada);
			subMenuVenderEntrada.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ABMFuncionesEstablecimientosView.getInstancia(false);
				}
			});
			this.menuBar.add(menuVentas);
		}
		
		if (usuarioLogueado.tieneRol(Rol.OPERADOR_ID)) {
			
			JMenu menuPeliculas = new JMenu("Peliculas");
			JMenuItem subMenuPeliculasFunciones = new JMenuItem("Agregar Peliculas y Funciones");
			menuPeliculas.add(subMenuPeliculasFunciones);
			subMenuPeliculasFunciones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					adminCinesView.getInstancia();
				}
			});
			this.menuBar.add(menuPeliculas);
		}
		
		if (usuarioLogueado.tieneRol(Rol.AGENTE_COMERCIAL_ID)) {
			
			JMenu menuDescuentos = new JMenu("Descuentos");
			JMenuItem subMenuAdministrarDescuentos = new JMenuItem("Administrar descuentos");
			menuDescuentos.add(subMenuAdministrarDescuentos);
			subMenuAdministrarDescuentos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFrame descuentosView = new DescuentosView();
					descuentosView.setLocationRelativeTo(null);
//					descuentosView.setVisible(true);
				}
			});
			this.menuBar.add(menuDescuentos);
		}
		
	}
}
