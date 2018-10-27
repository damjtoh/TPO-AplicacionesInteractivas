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

public class BajaUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField Password;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BajaUsuario frame = new BajaUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public BajaUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Contraseña = new JLabel("Contrase\u00F1a");
		Contraseña.setBounds(34, 100, 80, 14);
		contentPane.add(Contraseña);
		
		Password = new JTextField();
		Password.setColumns(10);
		Password.setBounds(166, 97, 115, 20);
		contentPane.add(Password);
		
		JButton baja = new JButton("Eliminar");
		baja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SistemaUsuarios.BajaUsuario(Password.getText());
			}
		});
		baja.setBounds(258, 135, 122, 23);
		contentPane.add(baja);
		
		JLabel lblIngreseLaContrasea = new JLabel("Ingrese la contrase\u00F1a para confirmar:");
		lblIngreseLaContrasea.setBounds(34, 59, 225, 14);
		contentPane.add(lblIngreseLaContrasea);
	}

}
