package Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import SistemaFacturacion.SistemaFacturacion;
import presentacion.DescuentoPresentacion;

public class DescuentosView extends JFrame {

	private JFrame frmDescuentos;
	private JTable table;

	public DescuentosView() {
		initialize();
	}

	private void initialize() {
		frmDescuentos = new JFrame();
		frmDescuentos.setResizable(false);
		frmDescuentos.setTitle("Descuentos");
		frmDescuentos.setBounds(100, 100, 556, 358);
		frmDescuentos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDescuentos.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 550, 225);
		frmDescuentos.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nombre", "Tipo", "Fecha Inicio", "Fecha Fin"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		LoadTable();
		
		scrollPane.setViewportView(table);
		
		JButton btnAltaDescuentox = new JButton("Alta Descuento 2x1");
		btnAltaDescuentox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombre = JOptionPane.showInputDialog("Ingrese el nombre del descuento");
				if(nombre!=null && nombre.length()>0) {
					String fechaInicioStr = JOptionPane.showInputDialog("Ingrese la fecha de inicio (dd/mm/aaaa)");
					if(fechaInicioStr!=null && fechaInicioStr.length()>0) {
						String fechaFinStr = JOptionPane.showInputDialog("Ingrese la fecha de fin (dd/mm/aaaa)");
						if(fechaFinStr!=null && fechaFinStr.length()>0) {
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");
							try{
								LocalDate fechaInicio = LocalDate.parse(fechaInicioStr,formatter);
								LocalDate fechaFin = LocalDate.parse(fechaFinStr,formatter);	
								SistemaFacturacion.GetInstancia().AltaDescuento2x1(fechaInicio, fechaFin, nombre);
								LoadTable();
							}catch(Exception e) {
								JOptionPane.showMessageDialog(null,"Por favor ingresar la fecha correctamente");
							}
						}
					}
				}
			}
		});
		btnAltaDescuentox.setBounds(29, 236, 187, 23);
		frmDescuentos.getContentPane().add(btnAltaDescuentox);
		
		JButton btnAltaDescuentoPorcentaje = new JButton("Alta Descuento Porcentaje");
		btnAltaDescuentoPorcentaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombre = JOptionPane.showInputDialog("Ingrese el nombre del descuento");
				if(nombre!=null && nombre.length()>0) {
					String fechaInicioStr = JOptionPane.showInputDialog("Ingrese la fecha de inicio (dd/mm/aaaa)");
					if(fechaInicioStr!=null && fechaInicioStr.length()>0) {
						String fechaFinStr = JOptionPane.showInputDialog("Ingrese la fecha de fin (dd/mm/aaaa)");
						if(fechaFinStr!=null && fechaFinStr.length()>0) {
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
							try {
								LocalDate fechaInicio = LocalDate.parse(fechaInicioStr,formatter);
								LocalDate fechaFin = LocalDate.parse(fechaFinStr,formatter);	
						
								float porcentaje = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el porcentaje de descuento (numero entero de 0.01 a 100)"));
								
								SistemaFacturacion.GetInstancia().AltaDescuentoPorcentaje(fechaInicio, fechaFin, porcentaje, nombre);
								LoadTable();
							}catch(Exception e) {
								JOptionPane.showMessageDialog(null,"Por favor ingresar la fecha correctamente");
							}
						}
					}
				}
			}
		});
		btnAltaDescuentoPorcentaje.setBounds(29, 264, 187, 23);
		frmDescuentos.getContentPane().add(btnAltaDescuentoPorcentaje);
		
		final DescuentosView thisClass = this;
		JButton btnAltaDescuentoCombo = new JButton("Alta Descuento Combo");
		btnAltaDescuentoCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new AltaComboView(thisClass);
			}
		});
		btnAltaDescuentoCombo.setBounds(29, 295, 187, 23);
		frmDescuentos.getContentPane().add(btnAltaDescuentoCombo);
		
		JButton btnEliminarDescuentoSeleccionado = new JButton("Eliminar Descuento Seleccionado");
		btnEliminarDescuentoSeleccionado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int columna = 0;
				int fila = table.getSelectedRow();
				
				if(fila>=0) {
					int id = Integer.parseInt(table.getModel().getValueAt(fila, columna).toString());
					
					SistemaFacturacion.GetInstancia().BajaDescuento(id);
					LoadTable();
					table.clearSelection();
				}else {
					JOptionPane.showMessageDialog(null,"Por favor primero seleccionar un descuento");
					table.clearSelection();
				}
				
			}
		});
		btnEliminarDescuentoSeleccionado.setBounds(285, 236, 239, 23);
		frmDescuentos.getContentPane().add(btnEliminarDescuentoSeleccionado);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(274, 236, -12, 82);
		frmDescuentos.getContentPane().add(separator);
		
		JButton btnModificarDescuentoSeleccionado = new JButton("Modificar Descuento Seleccionado");
		btnModificarDescuentoSeleccionado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int columna = 0;
				int fila = table.getSelectedRow();
				
				if(fila>=0) {
					int id = Integer.parseInt(table.getModel().getValueAt(fila, columna).toString());
					
					String fechaInicioStr = JOptionPane.showInputDialog("Ingrese la nueva fecha de inicio (dd/mm/aaaa)");
					if(fechaInicioStr!=null && fechaInicioStr.length()>0) {
						String fechaFinStr = JOptionPane.showInputDialog("Ingrese la nueva fecha de fin (dd/mm/aaaa)");
						if(fechaFinStr!=null && fechaFinStr.length()>0) {
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
							
							try {
								LocalDate fechaInicio = LocalDate.parse(fechaInicioStr,formatter);
								LocalDate fechaFin = LocalDate.parse(fechaFinStr,formatter);	
								
								SistemaFacturacion.GetInstancia().ModificarDescuento(id,fechaInicio, fechaFin);
								LoadTable();
							}catch(Exception e2) {
								JOptionPane.showMessageDialog(null,"Por favor ingresar la fecha correctamente");
							}
						}
						
					}
					table.clearSelection();
				}else {
					JOptionPane.showMessageDialog(null,"Por favor primero seleccionar un descuento");
					table.clearSelection();
				}
			}
		});
		btnModificarDescuentoSeleccionado.setBounds(285, 264, 239, 23);
		frmDescuentos.getContentPane().add(btnModificarDescuentoSeleccionado);
	}
	
	public void LoadTable() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		Collection<DescuentoPresentacion> descuentos = SistemaFacturacion.GetInstancia().GetDescuentosPresentacion();
		int numCols = model.getColumnCount();
		
		for(DescuentoPresentacion descuento : descuentos) {
			Object[] fila = new Object[numCols];
			fila[0] = descuento.getId();
			fila[1] = descuento.getNombre();
			fila[2] = descuento.GetTipoDescuento().toString();
			fila[3] = descuento.getFechaInicio().toString();
			fila[4] = descuento.getFechaFin().toString();
			((DefaultTableModel) table.getModel()).addRow(fila);
		}
	}
}
