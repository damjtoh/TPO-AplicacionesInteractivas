package Views;

import javax.swing.JPanel;
import javax.swing.JPasswordField;

import Usuarios.MapperUsuario;
import Usuarios.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ModificarUsuarioPanel extends JPanel {

	private JTextField textEmail;
	private JPasswordField textPassword;
	private JTextField textDomicilio;
	private JTextField usuarioField;
	private Usuario usuario;

	public ModificarUsuarioPanel(Usuario usuario){
		this.usuario = usuario;
		System.out.println("Voy a modificar a: "+usuario.toString());
		ini();
	}
	
	
	public void ini() {
		setBounds(10, 0, 485, 286);
		setLayout(null);
		setVisible(false);
		
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(10, 54, 66, 15);
		add(lblUsuario);
		
		usuarioField = new JTextField(this.usuario.getNombreUsuario());
		usuarioField.setEnabled(false);
		usuarioField.setEditable(false);
		usuarioField.setBounds(141, 48, 124, 19);
		add(usuarioField);
		usuarioField.setColumns(10);
		
		JLabel Email = new JLabel("Email");
		Email.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Email.setBounds(10, 81, 106, 14);
		add(Email);
		
		textEmail = new JTextField(this.usuario.getEmail());
		textEmail.setColumns(10);
		textEmail.setBounds(141, 79, 133, 20);
		add(textEmail);
		
		JLabel Password = new JLabel("Password");
		Password.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Password.setBounds(10, 113, 99, 14);
		add(Password);
		
		textPassword = new JPasswordField(this.usuario.getPassword());
		textPassword.setColumns(10);
		textPassword.setBounds(141, 110, 133, 20);
		add(textPassword);
		
		JLabel Domicilio = new JLabel("Domicilio");
		Domicilio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Domicilio.setBounds(10, 140, 86, 14);
		add(Domicilio);
		
		textDomicilio = new JTextField(this.usuario.getDomicilio());
		textDomicilio.setColumns(10);
		textDomicilio.setBounds(141, 138, 133, 20);
		add(textDomicilio);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuario.setDomicilio(textDomicilio.getText());
				usuario.setEmail(textEmail.getText());
				usuario.setPassword(new String(textPassword.getPassword()));
				MapperUsuario.update(usuario);
				JOptionPane.showMessageDialog(null, "Usuario modificado con exito.");
				MenuPrincipalView.getInstancia().setPanel(new GestionarUsuarioPanel());
			}
		});
		btnModificar.setBounds(210, 190, 116, 23);
		add(btnModificar);
		
		JLabel lblUstedPuedeModificar = new JLabel("Usted puede modificar los siguientes campos:");
		lblUstedPuedeModificar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUstedPuedeModificar.setBounds(10, 36, 333, 14);
		add(lblUstedPuedeModificar);
		
		JLabel lblModificarUsuario = new JLabel("Modificar Usuario");
		lblModificarUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblModificarUsuario.setBounds(10, 11, 213, 14);
		add(lblModificarUsuario);
	
		
	}
}