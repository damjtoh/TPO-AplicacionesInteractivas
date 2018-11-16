package Views;

import javax.swing.JPanel;

import Usuarios.Usuario;

import javax.swing.JLabel;
import java.awt.Font;

public class BienvenidoPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public BienvenidoPanel(Usuario usuario) {
		setBounds(10, 0, 485, 286);
		setLayout(null);
		setVisible(false);
		
		JLabel lblBienvenido = new JLabel("Bienvenido "+usuario.getNombre());
		lblBienvenido.setFont(new Font("Dialog", Font.BOLD, 26));
		lblBienvenido.setBounds(47, 69, 364, 130);
		add(lblBienvenido);

	}
}
