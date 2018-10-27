package Views;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Usuarios.Administrador;
import Usuarios.AgenteComercial;
import Usuarios.Operador;
import Usuarios.Usuario;
import sistemaCine.view.admin.adminCinesView;
import sistemaCine.view.cliente.ABMFuncionesEstablecimientosView;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class HubView {

	private JFrame frame;

	public HubView(Usuario usuario) {
		initialize(usuario);
	}

	private void initialize(Usuario usuario) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnComprarEntrada = new JButton("Comprar Entrada");
		btnComprarEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ABMFuncionesEstablecimientosView.getInstancia().setLocationRelativeTo(null);
				ABMFuncionesEstablecimientosView.getInstancia().setVisible(true);
			}
		});
		btnComprarEntrada.setBounds(241, 52, 172, 179);
		frame.getContentPane().add(btnComprarEntrada);
		

		JButton btnAdministrarDescuentos = new JButton("Administrar descuentos");
		if (usuario.getRoles().contains(new AgenteComercial(usuario)))
			btnAdministrarDescuentos.setVisible(true);
		else
			btnAdministrarDescuentos.setVisible(false);
		btnAdministrarDescuentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnAdministrarDescuentos.setBounds(0, 180, 231, 51);
		frame.getContentPane().add(btnAdministrarDescuentos);

		JButton btnModificarUsuarios = new JButton("Modificar Usuarios");
		if (usuario.getRoles().contains(new Administrador(usuario)))
			btnModificarUsuarios.setVisible(true);
		else
			btnModificarUsuarios.setVisible(false);
		btnModificarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarUsuario modificarUsuarioPantalla = new ModificarUsuario();
				modificarUsuarioPantalla.setVisible(true);
			}
		});
		btnModificarUsuarios.setBounds(0, 123, 231, 46);
		frame.getContentPane().add(btnModificarUsuarios);
		
		JButton btnAgregarPeliculasY = new JButton("Agregar Peliculas y Funciones");
		if (usuario.getRoles().contains(new Operador(usuario)))
			btnAgregarPeliculasY.setVisible(true);
		else
			btnAgregarPeliculasY.setVisible(false);
		btnAgregarPeliculasY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminCinesView.getInstancia().setVisible(true);
			}
		});
		btnAgregarPeliculasY.setBounds(0, 52, 231, 60);
		frame.getContentPane().add(btnAgregarPeliculasY);
		
		JLabel lblMainHub = new JLabel("Main Hub");
		lblMainHub.setFont(new Font("Wide Latin", Font.BOLD, 17));
		lblMainHub.setBounds(131, 16, 282, 25);
		frame.getContentPane().add(lblMainHub);
	}
}
