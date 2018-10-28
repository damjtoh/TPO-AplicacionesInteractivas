package Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Usuarios.Rol;
import Usuarios.SistemaUsuarios;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JList;

public class AltaUsuarioView extends JFrame {

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
	private JLabel lblRegistrar;
	private JCheckBox chckbxCliente;
	private JCheckBox chckbxAdministrador;
	private JCheckBox chckbxOperador;
	private JCheckBox chckbxAgenteComercial;

	private int cant;
	private ArrayList<Rol> roles;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaUsuarioView frame = new AltaUsuarioView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public AltaUsuarioView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(21, 56, 99, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(152, 56, 133, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmail.setBounds(21, 79, 106, 14);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(152, 79, 133, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPassword.setBounds(21, 103, 99, 14);
		contentPane.add(lblPassword);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(152, 101, 133, 20);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario");
		lblNombreDeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombreDeUsuario.setBounds(21, 125, 133, 14);
		contentPane.add(lblNombreDeUsuario);
		
		txtNombredeusuario = new JTextField();
		txtNombredeusuario.setBounds(152, 123, 133, 20);
		contentPane.add(txtNombredeusuario);
		txtNombredeusuario.setColumns(10);
		
		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDomicilio.setBounds(21, 149, 86, 14);
		contentPane.add(lblDomicilio);
		
		txtDomicilio = new JTextField();
		txtDomicilio.setBounds(152, 147, 133, 20);
		contentPane.add(txtDomicilio);
		txtDomicilio.setColumns(10);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDni.setBounds(21, 169, 99, 14);
		contentPane.add(lblDni);
		
		txtDni = new JTextField();
		txtDni.setBounds(152, 169, 133, 20);
		contentPane.add(txtDni);
		txtDni.setColumns(10);
		lblDia = new JLabel("dia");
		lblDia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDia.setBounds(152, 196, 46, 14);
		contentPane.add(lblDia);
		
		textDia = new JTextField();
		textDia.setBounds(180, 196, 30, 20);
		contentPane.add(textDia);
		textDia.setColumns(10);
		
		lblMes = new JLabel("mes");
		lblMes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMes.setBounds(220, 196, 46, 14);
		contentPane.add(lblMes);
		
		textMes = new JTextField();
		textMes.setBounds(247, 196, 30, 20);
		contentPane.add(textMes);
		textMes.setColumns(10);
		
		lblAo = new JLabel("a\u00F1o");
		lblAo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAo.setBounds(287, 196, 46, 14);
		contentPane.add(lblAo);
		
		textAnio = new JTextField();
		textAnio.setBounds(313, 196, 46, 20);
		contentPane.add(textAnio);
		textAnio.setColumns(10);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento:");
		lblFechaDeNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFechaDeNacimiento.setBounds(21, 195, 133, 14);
		contentPane.add(lblFechaDeNacimiento);
		
		btnCrear = new JButton("Crear");
		btnCrear.setFont(new Font("Tahoma", Font.BOLD, 12));
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
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(351, 227, 60, 23);
		contentPane.add(btnSalir);
		
		lblCompleteLosSiguientes = new JLabel("Complete los siguientes campos:");
		lblCompleteLosSiguientes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCompleteLosSiguientes.setBounds(21, 31, 281, 14);
		contentPane.add(lblCompleteLosSiguientes);
		
		lblRegistrar = new JLabel("Registrar Usuario");
		lblRegistrar.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRegistrar.setBounds(21, 11, 264, 23);
		contentPane.add(lblRegistrar);
		
		JLabel lblRol = new JLabel("Rol");
		lblRol.setBounds(295, 57, 46, 14);
		contentPane.add(lblRol);
		
		chckbxCliente = new JCheckBox("Cliente");
		chckbxCliente.setBounds(313, 76, 97, 23);
		contentPane.add(chckbxCliente);
		
		 
		chckbxAdministrador = new JCheckBox("Administrador");
		chckbxAdministrador.setBounds(314, 146, 97, 23);
		contentPane.add(chckbxAdministrador);
		
		chckbxOperador = new JCheckBox("Operador");
		chckbxOperador.setBounds(313, 100, 97, 23);
		contentPane.add(chckbxOperador);
		
		chckbxAgenteComercial = new JCheckBox("Agente Comercial");
		chckbxAgenteComercial.setBounds(313, 122, 115, 23);
		contentPane.add(chckbxAgenteComercial);
			 
		
	}
}




