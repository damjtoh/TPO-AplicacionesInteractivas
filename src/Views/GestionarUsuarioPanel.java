package Views;

import javax.swing.JPanel;

import Usuarios.MapperUsuario;
import Usuarios.Usuario;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestionarUsuarioPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ListarUsuariosPanel listadoPanel;
	private GestionarUsuarioPanel that = this;

	/**
	 * Create the panel.
	 */
	public GestionarUsuarioPanel() {
		setBounds(10, 0, 485, 412);
		setLayout(null);
		setVisible(false);
		
		this.listadoPanel = new ListarUsuariosPanel();
		this.listadoPanel.setBounds(12, 12, 468, 352);
		add(this.listadoPanel);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(209, 314, 114, 25);
		listadoPanel.add(btnEliminar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(327, 314, 114, 25);
		listadoPanel.add(btnModificar);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Usuario usuarioSelected = listadoPanel.getSelectedUsuario();
				System.out.println("Usuario seleccionado: "+usuarioSelected.toString());
				MenuPrincipalView.getInstancia().setPanel(new ModificarUsuarioPanel(usuarioSelected));
			}
		});
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Usuario usuarioSelected = listadoPanel.getSelectedUsuario();
				int dialogResult = JOptionPane.showConfirmDialog (null, "Estas seguro que deseas borrar el usuario: "+usuarioSelected.getNombreUsuario()+"?","Eliminar usuario",JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION){
					MapperUsuario.delete(usuarioSelected);
					System.out.println(that.listadoPanel.getSelectedIndex());
					that.listadoPanel.removeAt(that.listadoPanel.getSelectedIndex());
					JOptionPane.showMessageDialog(null, "Usuario eliminado con exito.");
				}
			}
		});

	}
}
