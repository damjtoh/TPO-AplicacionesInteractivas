package Usuarios;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModificarUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField textEmail;
	private JTextField textPassword;
	private JTextField textDomicilio;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarUsuario frame = new ModificarUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public ModificarUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Email = new JLabel("Email");
		Email.setBounds(10, 76, 106, 14);
		contentPane.add(Email);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(141, 76, 133, 20);
		contentPane.add(textEmail);
		
		JLabel Password = new JLabel("Password");
		Password.setBounds(10, 110, 99, 14);
		contentPane.add(Password);
		
		textPassword = new JTextField();
		textPassword.setColumns(10);
		textPassword.setBounds(141, 107, 133, 20);
		contentPane.add(textPassword);
		
		JLabel Domicilio = new JLabel("Domicilio");
		Domicilio.setBounds(10, 137, 86, 14);
		contentPane.add(Domicilio);
		
		textDomicilio = new JTextField();
		textDomicilio.setColumns(10);
		textDomicilio.setBounds(141, 135, 133, 20);
		contentPane.add(textDomicilio);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModificar.setBounds(252, 227, 77, 23);
		contentPane.add(btnModificar);
		
		JButton button_1 = new JButton("Salir");
		button_1.setBounds(339, 227, 60, 23);
		contentPane.add(button_1);
		
		JLabel lblUstedPuedeModificar = new JLabel("Usted puede modificar los siguientes campos:");
		lblUstedPuedeModificar.setBounds(10, 25, 333, 14);
		contentPane.add(lblUstedPuedeModificar);
	}

}
