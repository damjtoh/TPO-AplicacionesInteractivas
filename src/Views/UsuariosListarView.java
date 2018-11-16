package Views;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class UsuariosListarView extends JPanel {

	public UsuariosListarView() {
		setLayout(null);
		
		JLabel lblListarUsuarios = new JLabel("Listar usuarios");
		lblListarUsuarios.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblListarUsuarios.setBounds(6, 6, 127, 16);
		add(lblListarUsuarios);
		
		JList list = new JList();
		list.setBounds(16, 34, 416, 229);
		add(list);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(340, 265, 92, 29);
		add(btnBorrar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(241, 265, 92, 29);
		add(btnEditar);

	}

}
