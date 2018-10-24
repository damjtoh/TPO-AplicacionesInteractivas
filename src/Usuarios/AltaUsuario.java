package Usuarios;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class AltaUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtEmail;
	private JTextField txtPassword;
	private JTextField txtNombredeusuario;
	private JTextField txtDomicilio;
	private JTextField txtDni;
	private JButton btnCrear;
	private JButton btnSalir;
	private JLabel lblCompleteLosSiguientes;
	private JLabel lblDia;
	private JTextField textDia;
	private JLabel lblMes;
	private JTextField textMes;
	private JLabel lblAo;
	private JTextField textAnio;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaUsuario frame = new AltaUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public AltaUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(22, 48, 99, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(153, 48, 133, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(22, 71, 106, 14);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(153, 71, 133, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(22, 96, 99, 14);
		contentPane.add(lblPassword);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(153, 93, 133, 20);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario");
		lblNombreDeUsuario.setBounds(22, 117, 133, 14);
		contentPane.add(lblNombreDeUsuario);
		
		txtNombredeusuario = new JTextField();
		txtNombredeusuario.setBounds(153, 115, 133, 20);
		contentPane.add(txtNombredeusuario);
		txtNombredeusuario.setColumns(10);
		
		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setBounds(22, 141, 86, 14);
		contentPane.add(lblDomicilio);
		
		txtDomicilio = new JTextField();
		txtDomicilio.setBounds(153, 139, 133, 20);
		contentPane.add(txtDomicilio);
		txtDomicilio.setColumns(10);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(22, 161, 99, 14);
		contentPane.add(lblDni);
		
		txtDni = new JTextField();
		txtDni.setBounds(153, 161, 133, 20);
		contentPane.add(txtDni);
		txtDni.setColumns(10);
		lblDia = new JLabel("dia");
		lblDia.setBounds(153, 192, 46, 14);
		contentPane.add(lblDia);
		
		textDia = new JTextField();
		textDia.setBounds(181, 192, 30, 20);
		contentPane.add(textDia);
		textDia.setColumns(10);
		
		lblMes = new JLabel("mes");
		lblMes.setBounds(221, 192, 46, 14);
		contentPane.add(lblMes);
		
		textMes = new JTextField();
		textMes.setBounds(248, 192, 30, 20);
		contentPane.add(textMes);
		textMes.setColumns(10);
		
		lblAo = new JLabel("a\u00F1o");
		lblAo.setBounds(288, 192, 46, 14);
		contentPane.add(lblAo);
		
		textAnio = new JTextField();
		textAnio.setBounds(314, 192, 46, 20);
		contentPane.add(textAnio);
		textAnio.setColumns(10);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento:");
		lblFechaDeNacimiento.setBounds(22, 187, 133, 14);
		contentPane.add(lblFechaDeNacimiento);
		
		btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			SistemaUsuarios.AltaUsuario(txtNombre.getText(), txtEmail.getText(), txtPassword.getText(), 
						txtNombredeusuario.getText(), txtDomicilio.getText(), Integer.parseInt(txtDni.getText()), 
						LocalDate.of(Integer.parseInt(textDia.getText()), Integer.parseInt(textMes.getText()), Integer.parseInt(textAnio.getText()))); 
			}
		});
		btnCrear.setBounds(264, 227, 77, 23);
		contentPane.add(btnCrear);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(351, 227, 60, 23);
		contentPane.add(btnSalir);
		
		lblCompleteLosSiguientes = new JLabel("Complete los siguientes campos:");
		lblCompleteLosSiguientes.setBounds(22, 11, 281, 14);
		contentPane.add(lblCompleteLosSiguientes);
		
		
	}

}




