package Views;

import javax.swing.JPanel;
import javax.swing.JList;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Usuarios.MapperUsuario;
import Usuarios.Usuario;
import javax.swing.JScrollPane;

public class ListarUsuariosPanel extends JPanel {
	private JTable table;
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private Vector<Vector> tableData = new Vector<Vector>();

	/**
	 * Create the panel.
	 */
	public ListarUsuariosPanel() {
		setLayout(null);
		
		JLabel lblSeleccionarUsuario = new JLabel("Seleccionar usuario");
		lblSeleccionarUsuario.setBounds(12, 5, 197, 15);
		add(lblSeleccionarUsuario);
		
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("ID");
		columnNames.add("Nombre usuario");
		columnNames.add("Nombre");
		columnNames.add("DNI");
		
		this.usuarios = MapperUsuario.list();
		
		for (Usuario usuario: this.usuarios) {
			Vector row = new Vector();
			row.add(usuario.getId());
			row.add(usuario.getNombreUsuario());
			row.add(usuario.getNombre());
			row.add(usuario.getDni());
			tableData.add(row);
		}
		
		System.out.println(tableData.toString());
		System.out.println(columnNames.toString());
		
		table = new JTable(tableData, columnNames);
		table.setDefaultEditor(Object.class, null);
		table.setBounds(12, 32, 426, 256);
		add(table);
		table.setVisible(true);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 26, 426, 262);
		add(scrollPane);
		

	}
	
	public void removeAt(int index) {
		((DefaultTableModel)this.table.getModel()).removeRow(index);
	}
	
	public int getSelectedIndex() {
		return this.table.getSelectedRow();
	}
	
	public Usuario getSelectedUsuario() {
		Integer rowIndex = this.table.getSelectedRow();
		if (rowIndex == null) {
			return null;
		}
		Usuario usuarioSelected = this.usuarios.get(rowIndex);
		return usuarioSelected;
	}
}
