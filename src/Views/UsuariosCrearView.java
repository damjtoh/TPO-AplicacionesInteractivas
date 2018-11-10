package Views;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JList;

import Usuarios.Rol;

import java.awt.ComponentOrientation;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UsuariosCrearView extends JPanel {

	private JTextField txtNombreApellido;
	private JTextField txtNombreUsuario;
	private JTextField txtContrasena;
	private JTextField txtDomicilio;
	private JTextField textDNI;
	private JTextField textFechaNacimiento;


	public UsuariosCrearView() {
		setLayout(null);
		
		JLabel lblCrearUsuario = new JLabel("Crear Usuario");
		lblCrearUsuario.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblCrearUsuario.setBounds(6, 6, 111, 16);
		add(lblCrearUsuario);
		
		JLabel labelNombreApellido = new JLabel("Nombre y Apellido");
		labelNombreApellido.setBounds(6, 34, 120, 16);
		add(labelNombreApellido);
		
		txtNombreApellido = new JTextField();
		txtNombreApellido.setBounds(6, 52, 130, 26);
		add(txtNombreApellido);
		txtNombreApellido.setColumns(10);
		
		JLabel labelNombreUsuario = new JLabel("Nombre de usuario");
		labelNombreUsuario.setBounds(148, 34, 120, 16);
		add(labelNombreUsuario);
		
		txtNombreUsuario = new JTextField();
		txtNombreUsuario.setColumns(10);
		txtNombreUsuario.setBounds(148, 52, 130, 26);
		add(txtNombreUsuario);
		
		JLabel labelContrasena = new JLabel("Contraseña");
		labelContrasena.setBounds(297, 34, 120, 16);
		add(labelContrasena);
		
		txtContrasena = new JPasswordField();
		txtContrasena.setColumns(10);
		txtContrasena.setBounds(297, 52, 130, 26);
		add(txtContrasena);
		
		JLabel labelDomicilio = new JLabel("Domicilio");
		labelDomicilio.setBounds(6, 89, 120, 16);
		add(labelDomicilio);
		
		txtDomicilio = new JTextField();
		txtDomicilio.setColumns(10);
		txtDomicilio.setBounds(6, 107, 130, 26);
		add(txtDomicilio);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(148, 90, 120, 16);
		add(lblDni);
		
		textDNI = new JTextField();
		textDNI.setColumns(10);
		textDNI.setBounds(148, 108, 130, 26);
		add(textDNI);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblFechaNacimiento.setBounds(297, 89, 130, 16);
		add(lblFechaNacimiento);
		
		textFechaNacimiento = new JTextField();
		textFechaNacimiento.setColumns(10);
		textFechaNacimiento.setBounds(297, 107, 130, 26);
		add(textFechaNacimiento);
		
		JLabel lblRoles = new JLabel("Roles");
		lblRoles.setBounds(6, 142, 120, 16);
		add(lblRoles);
		
		 JList listRoles = new JList(Rol.asCombo) ;                    // creating JList object; pass the array as parameter
		 listRoles.setBounds(6, 159, 80, 100);
		 listRoles.setVisibleRowCount(5); 
			     
		 listRoles.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		 add(listRoles);
		 
		 JButton btnCrearUsuario = new JButton("Crear Usuario");
		 btnCrearUsuario.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 	}
		 });
		 btnCrearUsuario.setBounds(310, 345, 117, 29);
		 add(btnCrearUsuario);


	}
}
