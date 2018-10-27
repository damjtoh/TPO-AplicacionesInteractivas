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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Usuarios.SistemaUsuarios;
import Usuarios.Usuario;

public class MenuPrincipalView extends JFrame {

	private JPanel contentPane;
	private JTextField UsuarioValidar;
	private JTextField PasswordValidar;
	private Usuario usuarioLogeado;

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

	public MenuPrincipalView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Menu Usuarios");
		menuBar.add(mnNewMenu);

		JMenuItem mntmAltausuario = new JMenuItem("Registrar");
		mntmAltausuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AltaUsuarioView a = new AltaUsuarioView();
				a.setVisible(true);
			}
		});

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 414, 225);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setVisible(false);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 0, 292, 35);
		contentPane.add(panel_1);
		panel_1.setVisible(false);
		panel_1.setLayout(null);

		JButton btnModificarUsuario = new JButton("Modificar Usuario");
		btnModificarUsuario.setBounds(0, 0, 144, 23);
		panel_1.add(btnModificarUsuario);

		JButton btnBajaUsuario = new JButton("Baja Usuario");
		btnBajaUsuario.setBounds(150, 0, 132, 23);
		panel_1.add(btnBajaUsuario);
		btnBajaUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BajaUsuarioView b = new BajaUsuarioView();
				b.setVisible(true);
			}
		});
		btnModificarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarUsuario m = new ModificarUsuario();
				m.setVisible(true);
			}
		});

		JLabel label = new JLabel("Nombre de Usuario");
		label.setBounds(22, 92, 115, 14);
		panel.add(label);

		UsuarioValidar = new JTextField();
		UsuarioValidar.setBounds(154, 89, 115, 20);
		UsuarioValidar.setColumns(10);
		panel.add(UsuarioValidar);

		JLabel label_1 = new JLabel("Contrase\u00F1a");
		label_1.setBounds(22, 120, 80, 14);
		panel.add(label_1);

		PasswordValidar = new JTextField();
		PasswordValidar.setBounds(154, 117, 115, 20);
		PasswordValidar.setColumns(10);
		panel.add(PasswordValidar);

		JMenuItem mntmIniciarSesion = new JMenuItem("Iniciar Sesi\u00F3n ");
		mntmIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
			}
		});
		mnNewMenu.add(mntmIniciarSesion);
		mnNewMenu.add(mntmAltausuario);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuBar.add(btnSalir);

		JLabel IngresoIncorrecto = new JLabel("Los datos ingresados son incorrectos");
		IngresoIncorrecto.setForeground(new Color(220, 20, 60));
		IngresoIncorrecto.setBounds(46, 200, 267, 14);
		panel.add(IngresoIncorrecto);
		IngresoIncorrecto.setVisible(false);

		JButton button = new JButton("Iniciar Sesi\u00F3n");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("intento ingresar: " + UsuarioValidar.getText() + PasswordValidar.getText());
				if (SistemaUsuarios.validarUsuario(UsuarioValidar.getText(), PasswordValidar.getText())) {
					System.out.println("Usuario correcto!");
					panel.setVisible(false);
					panel_1.setVisible(true);
					usuarioLogeado = SistemaUsuarios.buscarUsuario(UsuarioValidar.getText());
					HubView nextScreen = new HubView(usuarioLogeado);
				} else {
					JOptionPane.showMessageDialog(null, "Usuario o contrasena incorrecta.");
				}
			}
		});
		button.setBounds(246, 155, 122, 23);
		panel.add(button);

		JLabel lblIngreseLosSiguientes = new JLabel("Ingrese los siguientes datos:");
		lblIngreseLosSiguientes.setBounds(22, 47, 159, 14);
		panel.add(lblIngreseLosSiguientes);

	}
}
