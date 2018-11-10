package Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import Usuarios.Administrador;
import Usuarios.AgenteComercial;
import Usuarios.Cliente;
import Usuarios.MapperUsuario;
import Usuarios.Operador;
import Usuarios.Rol;
import Usuarios.SistemaUsuarios;
import Usuarios.Usuario;
import Usuarios.Vendedor;

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
import javax.swing.JOptionPane;
import javax.swing.JList;

public class AltaUsuarioPanel extends JPanel {

	//private JPanel this;
	private JTextField txtNombre;
	private JTextField txtEmail;
	private JPasswordField txtPassword;
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
	private JCheckBox chckbxVendedor;
	private AltaUsuarioPanel that = this;

	private int cant;
	private ArrayList<Rol> roles;
	
	public AltaUsuarioPanel() {
		setBounds(10, 0, 414, 255);
		setLayout(null);
		setVisible(false);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(21, 56, 99, 14);
		this.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(152, 56, 133, 20);
		this.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmail.setBounds(21, 79, 106, 14);
		this.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(152, 79, 133, 20);
		this.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPassword.setBounds(21, 103, 99, 14);
		this.add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(152, 101, 133, 20);
		this.add(txtPassword);
		txtPassword.setColumns(10);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario");
		lblNombreDeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombreDeUsuario.setBounds(21, 125, 133, 14);
		this.add(lblNombreDeUsuario);
		
		txtNombredeusuario = new JTextField();
		txtNombredeusuario.setBounds(152, 123, 133, 20);
		this.add(txtNombredeusuario);
		txtNombredeusuario.setColumns(10);
		
		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDomicilio.setBounds(21, 149, 86, 14);
		this.add(lblDomicilio);
		
		txtDomicilio = new JTextField();
		txtDomicilio.setBounds(152, 147, 133, 20);
		this.add(txtDomicilio);
		txtDomicilio.setColumns(10);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDni.setBounds(21, 169, 99, 14);
		this.add(lblDni);
		
		txtDni = new JTextField();
		txtDni.setBounds(152, 169, 133, 20);
		this.add(txtDni);
		txtDni.setColumns(10);
		lblDia = new JLabel("dia");
		lblDia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDia.setBounds(152, 196, 46, 14);
		this.add(lblDia);
		
		textDia = new JTextField();
		textDia.setBounds(180, 196, 30, 20);
		this.add(textDia);
		textDia.setColumns(10);
		
		lblMes = new JLabel("mes");
		lblMes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMes.setBounds(220, 196, 46, 14);
		this.add(lblMes);
		
		textMes = new JTextField();
		textMes.setBounds(247, 196, 30, 20);
		this.add(textMes);
		textMes.setColumns(10);
		
		lblAo = new JLabel("a\u00F1o");
		lblAo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAo.setBounds(287, 196, 46, 14);
		this.add(lblAo);
		
		textAnio = new JTextField();
		textAnio.setBounds(313, 196, 46, 20);
		this.add(textAnio);
		textAnio.setColumns(10);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento:");
		lblFechaDeNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFechaDeNacimiento.setBounds(21, 195, 133, 14);
		this.add(lblFechaDeNacimiento);
		
		btnCrear = new JButton("Crear");
		btnCrear.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Integer id = null;
			String nombre = txtNombre.getText();
			String email = txtEmail.getText();
			String password = new String(txtPassword.getPassword());
			String nombreUsuario = txtNombredeusuario.getText();
			String domicilio = txtDomicilio.getText();
			Integer dni = Integer.parseInt(txtDni.getText());
			LocalDate fechaNacimiento = LocalDate.of(Integer.parseInt(textAnio.getText()), Integer.parseInt(textMes.getText()), Integer.parseInt(textDia.getText()));
			Usuario nuevoUsuario = new Usuario(id, nombre, email, password, nombreUsuario, domicilio, dni, fechaNacimiento);
			if (SistemaUsuarios.getInstancia().getUsuarioLogueado() == null) {
				nuevoUsuario.addRol(new Cliente(nuevoUsuario));
			} else {				
				ArrayList<Rol> roles = that.getRolesFromCheckbox(nuevoUsuario);
				nuevoUsuario.setRoles(roles);
			}
			MapperUsuario.insert(nuevoUsuario);
			JOptionPane.showMessageDialog(null, "Usuario creado con exito.");
			}
		});
		btnCrear.setBounds(264, 227, 77, 23);
		this.add(btnCrear);
		
		btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(351, 227, 60, 23);
		this.add(btnSalir);
		
		lblCompleteLosSiguientes = new JLabel("Complete los siguientes campos:");
		lblCompleteLosSiguientes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCompleteLosSiguientes.setBounds(21, 31, 281, 14);
		this.add(lblCompleteLosSiguientes);
		
		lblRegistrar = new JLabel("Registrar Usuario");
		lblRegistrar.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRegistrar.setBounds(21, 11, 264, 23);
		this.add(lblRegistrar);
		
		if (SistemaUsuarios.getInstancia().getUsuarioLogueado() != null) {
			// checkear si es admin tambien
			
			JLabel lblRol = new JLabel("Rol");
			lblRol.setBounds(295, 57, 46, 14);
			this.add(lblRol);
			
			chckbxCliente = new JCheckBox("Cliente");
			chckbxCliente.setBounds(313, 76, 97, 23);
			this.add(chckbxCliente);
			
			 
			chckbxAdministrador = new JCheckBox("Administrador");
			chckbxAdministrador.setBounds(314, 146, 97, 23);
			this.add(chckbxAdministrador);
			
			chckbxOperador = new JCheckBox("Operador");
			chckbxOperador.setBounds(313, 100, 97, 23);
			this.add(chckbxOperador);
			
			chckbxAgenteComercial = new JCheckBox("Agente Comercial");
			chckbxAgenteComercial.setBounds(313, 122, 115, 23);
			this.add(chckbxAgenteComercial);
			
			chckbxVendedor = new JCheckBox("Agente Comercial");
			chckbxVendedor.setBounds(313, 168, 115, 23);
			this.add(chckbxVendedor);
			
		} else {
			chckbxCliente = new JCheckBox("Cliente");
			this.chckbxCliente.setSelected(true);
		}
			 
		
	}
	
	private ArrayList<Rol> getRolesFromCheckbox(Usuario u) {
		ArrayList<Rol> roles = new ArrayList<Rol>();
		if (chckbxCliente.isSelected()) {
			roles.add(new Cliente(u));
		}
		if (chckbxAdministrador.isSelected()) {
			roles.add(new Administrador(u));
		}
		if (chckbxOperador.isSelected()) {
			roles.add(new Operador(u));
		}
		if (chckbxAgenteComercial.isSelected()) {
			roles.add(new AgenteComercial(u));
		}
		if (chckbxVendedor.isSelected()) {
			roles.add(new Vendedor(u));
		}
		return roles;
	}
}




