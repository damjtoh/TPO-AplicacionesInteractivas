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
import java.awt.Color;

public class ModificarUsuarioView extends JFrame {

	private JPanel contentPane;
	private JTextField textEmail;
	private JTextField textPassword;
	private JTextField textDomicilio;

	public ModificarUsuarioView(){
		ini();
	}
	
	
	public void ini() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Email = new JLabel("Email");
		Email.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Email.setBounds(10, 81, 106, 14);
		contentPane.add(Email);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(141, 79, 133, 20);
		contentPane.add(textEmail);
		
		JLabel Password = new JLabel("Password");
		Password.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Password.setBounds(10, 113, 99, 14);
		contentPane.add(Password);
		
		textPassword = new JTextField();
		textPassword.setColumns(10);
		textPassword.setBounds(141, 110, 133, 20);
		contentPane.add(textPassword);
		
		JLabel Domicilio = new JLabel("Domicilio");
		Domicilio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Domicilio.setBounds(10, 140, 86, 14);
		contentPane.add(Domicilio);
		
		textDomicilio = new JTextField();
		textDomicilio.setColumns(10);
		textDomicilio.setBounds(141, 138, 133, 20);
		contentPane.add(textDomicilio);
	
		JLabel DatosModificados = new JLabel("Los datos han sido modificados");
		DatosModificados.setForeground(new Color(0, 204, 0));
		DatosModificados.setFont(new Font("Tahoma", Font.PLAIN, 12));
		DatosModificados.setBounds(10, 225, 288, 14);
		contentPane.add(DatosModificados);
		DatosModificados.setVisible(false);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SistemaUsuarios.ModificarUsuario(Email.getText(), textPassword.getText(), textDomicilio.getText());
				DatosModificados.setVisible(true);
			}
		});
		btnModificar.setBounds(242, 188, 90, 23);
		contentPane.add(btnModificar);
		
		JButton button_1 = new JButton("Salir");
		button_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button_1.setBounds(341, 188, 60, 23);
		contentPane.add(button_1);
		
		JLabel lblUstedPuedeModificar = new JLabel("Usted puede modificar los siguientes campos:");
		lblUstedPuedeModificar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUstedPuedeModificar.setBounds(10, 36, 333, 14);
		contentPane.add(lblUstedPuedeModificar);
		
		JLabel lblModificarUsuario = new JLabel("Modificar Usuario");
		lblModificarUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblModificarUsuario.setBounds(10, 11, 213, 14);
		contentPane.add(lblModificarUsuario);
	
		
	}
}