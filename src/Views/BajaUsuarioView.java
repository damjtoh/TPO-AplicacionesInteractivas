package Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Usuarios.SistemaUsuarios;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class BajaUsuarioView extends JFrame {

	private JPanel contentPane;
	private JTextField Password;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BajaUsuarioView frame = new BajaUsuarioView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public BajaUsuarioView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel NombreUsuario = new JLabel("Nombre de Usuario");
		NombreUsuario.setBounds(34, 104, 115, 14);
		contentPane.add(NombreUsuario);
		
		NombreU = new JTextField();
		NombreU.setColumns(10);
		NombreU.setBounds(166, 101, 115, 20);
		contentPane.add(NombreU);
		
		JLabel Contrasena = new JLabel("Contrase\u00F1a");
		Contrasena.setBounds(34, 132, 80, 14);
		contentPane.add(Contrasena);
		
		Password = new JTextField();
		Password.setColumns(10);
		Password.setBounds(166, 97, 115, 20);
		contentPane.add(Password);
		
		JButton baja = new JButton("Eliminar");
		baja.setFont(new Font("Tahoma", Font.BOLD, 12));
		baja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SistemaUsuarios.BajaUsuario(Password.getText());
			}
		});
		baja.setBounds(265, 138, 122, 23);
		contentPane.add(baja);
		
		JLabel lblIngreseLaContrasea = new JLabel("Ingrese la contrase\u00F1a para confirmar:");
		lblIngreseLaContrasea.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIngreseLaContrasea.setBounds(34, 59, 225, 14);
		contentPane.add(lblIngreseLaContrasea);
		
		JLabel lblBajaUsuario = new JLabel("Baja Usuario");
		lblBajaUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblBajaUsuario.setBounds(34, 11, 178, 23);
		contentPane.add(lblBajaUsuario);
	}
}
