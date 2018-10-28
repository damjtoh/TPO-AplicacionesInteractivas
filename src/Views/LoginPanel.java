package Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Usuarios.SistemaUsuarios;

public class LoginPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public LoginPanel(MenuPrincipalView menuPrincipalView) {
		setBounds(10, 0, 414, 225);
		setLayout(null);
		setVisible(false);
		JLabel label = new JLabel("Nombre de Usuario");
		label.setBounds(22, 92, 115, 14);
		add(label);

		JTextField usuarioField = new JTextField();
		usuarioField.setBounds(154, 89, 115, 20);
		usuarioField.setColumns(10);
		add(usuarioField);

		JLabel label_1 = new JLabel("Contrase\u00F1a");
		label_1.setBounds(22, 120, 80, 14);
		add(label_1);

		JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(154, 117, 115, 20);
		passwordField.setColumns(10);
		add(passwordField);
		
		JButton button = new JButton("Iniciar Sesi\u00F3n");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = usuarioField.getText();
				String password = new String(passwordField.getPassword());
				System.out.println("intento ingresar: " + user + password);
				if (SistemaUsuarios.getInstancia().login(user, password)) {
					System.out.println("Usuario correcto!");
					//setVisible(false);
					//panel_1.setVisible(true);
					menuPrincipalView.setMenuAfterLogin();
					System.out.println(SistemaUsuarios.getInstancia().getUsuarioLogueado().toString());
					HubView nextScreen = new HubView(SistemaUsuarios.getInstancia().getUsuarioLogueado());
					nextScreen.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Usuario o contrasena incorrecta.");
				}
			}
		});
		button.setBounds(246, 155, 122, 23);
		add(button);

		JLabel lblIngreseLosSiguientes = new JLabel("Ingrese los siguientes datos:");
		lblIngreseLosSiguientes.setBounds(22, 47, 159, 14);
		add(lblIngreseLosSiguientes);

	}

}
