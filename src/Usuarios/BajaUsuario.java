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
	private JTextField NombreU;
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
		
		JLabel NombreUsuario = new JLabel("Nombre de Usuario");
		NombreUsuario.setBounds(34, 104, 115, 14);
		contentPane.add(NombreUsuario);
		
		NombreU = new JTextField();
		NombreU.setColumns(10);
		NombreU.setBounds(166, 101, 115, 20);
		contentPane.add(NombreU);
		
		JLabel Contraseña = new JLabel("Contrase\u00F1a");
		Contraseña.setBounds(34, 132, 80, 14);
		contentPane.add(Contraseña);
		
		Password = new JTextField();
		Password.setColumns(10);
		Password.setBounds(166, 129, 115, 20);
		contentPane.add(Password);
		
		JButton baja = new JButton("Eliminar");
		baja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SistemaUsuarios.BajaUsuario(NombreU.getText(), Password.getText());
			}
		});
		baja.setBounds(258, 167, 122, 23);
		contentPane.add(baja);
		
		JLabel label_2 = new JLabel("Ingrese los siguientes datos:");
		label_2.setBounds(34, 59, 225, 14);
		contentPane.add(label_2);
	}

}
