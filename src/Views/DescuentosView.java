package Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import SistemaFacturacion.Descuento;
import SistemaFacturacion.FacturacionMapper;
import SistemaFacturacion.SistemaFacturacion;
import presentacion.DescuentoPresentacion;
import sistemaCine.view.admin.adminCinesView;

import javax.swing.JComboBox;
import javax.swing.JLabel;

public class DescuentosView extends JFrame {

	/**
	 * 
	 */
	private static DescuentosView instancia;
	private static final long serialVersionUID = 1L;
	private JFrame frmDescuentos;
	private JTable table;
	JPanel panel = new JPanel();
	DescuentosView that = this;

	public DescuentosView() {
		initialize();
		this.frmDescuentos.setVisible(true);
	}
	
	public static DescuentosView getInstancia() {
		if (instancia == null) {
			instancia = new DescuentosView();
		}
		return instancia;
	}

	private void initialize() {
		frmDescuentos = new JFrame();
		frmDescuentos.setResizable(false);
		frmDescuentos.setTitle("Descuentos");
		frmDescuentos.setBounds(100, 100, 556, 402);
//		frmDescuentos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDescuentos.getContentPane().setLayout(null);
		
		panel.setBounds(0, 0, 550, 225);
		frmDescuentos.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nombre", "Tipo", "Fecha Inicio", "Fecha Fin", "CUIT", "Activo"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
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
				Alta2x1View alta2x1View = new Alta2x1View();
				alta2x1View.setVisible(true);
//				String nombre = JOptionPane.showInputDialog("Ingrese el nombre del descuento");
//				if(nombre!=null && nombre.length()>0) {
//					String fechaInicioStr = JOptionPane.showInputDialog("Ingrese la fecha de inicio (dd/mm/aaaa)");
//					if(fechaInicioStr!=null && fechaInicioStr.length()>0) {
//						String fechaFinStr = JOptionPane.showInputDialog("Ingrese la fecha de fin (dd/mm/aaaa)");
//						if(fechaFinStr!=null && fechaFinStr.length()>0) {
//							
//							DateFormat formatter;
//							try{
//								formatter = new SimpleDateFormat("dd/MM/yyyy");
//								Date fechainicio = (Date) formatter.parse(fechaInicioStr);
//								Date fechaFin = (Date) formatter.parse(fechaFinStr);
//								SistemaFacturacion.GetInstancia().AltaDescuento2x1(fechainicio, fechaFin, nombre);
//								LoadTable();
//							}catch(Exception e) {
//								JOptionPane.showMessageDialog(null,"Por favor ingresar la fecha correctamente");
//							}
//						}
//					}
//				}
			}
		});
		btnAltaDescuentox.setBounds(29, 267, 187, 23);
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
							DateFormat formatter;
							formatter = new SimpleDateFormat("dd/MM/yyyy");
							try {
								
								//Parsear para BASE DE DATOS
								//			Date fecha = result.getDate(7);
								//          LocalDate fechaNacimiento = fecha.toLocalDate();
								// USAR AMBOS JUNTOS
								Date fechaInicio = (Date) formatter.parse(fechaInicioStr);
								Date fechaFin = (Date) formatter.parse(fechaFinStr);
								
						
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
		btnAltaDescuentoPorcentaje.setBounds(29, 302, 187, 23);
		frmDescuentos.getContentPane().add(btnAltaDescuentoPorcentaje);
		
		final DescuentosView thisClass = this;
		JButton btnAltaDescuentoCombo = new JButton("Alta Descuento Combo");
		btnAltaDescuentoCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new AltaComboView();
			}
		});
		btnAltaDescuentoCombo.setBounds(29, 337, 187, 23);
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
		btnEliminarDescuentoSeleccionado.setBounds(285, 267, 239, 23);
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
							DateFormat formatter;
							
							try {
								formatter = new SimpleDateFormat("dd/MM/yyyy");
								Date fechaInicio = (Date) formatter.parse(fechaInicioStr);
								Date fechaFin = (Date) formatter.parse(fechaFinStr);
								
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
		btnModificarDescuentoSeleccionado.setBounds(285, 302, 239, 23);
		frmDescuentos.getContentPane().add(btnModificarDescuentoSeleccionado);
		
		JButton btnDesactivarDescuentoSeleccionado = new JButton("Activar/Desactivar Descuento");
		btnDesactivarDescuentoSeleccionado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int columna = 0;
				int fila = table.getSelectedRow();
				
				if(fila>=0) {
					int id = Integer.parseInt(table.getModel().getValueAt(fila, columna).toString());
					Descuento descuento = SistemaFacturacion.GetInstancia().BuscarDescuento(id);
					if (descuento.getActivo() == 1) {
						FacturacionMapper.desactivateDescuento(id);
						descuento.setActivo(0);
						JOptionPane.showMessageDialog(null,"Descuento desactivado con éxito");
					} else {
						FacturacionMapper.activateDescuento(id);
						descuento.setActivo(1);
						JOptionPane.showMessageDialog(null,"Descuento activado con éxito");
					}
					
					that.LoadTable();
					
				} else {
					JOptionPane.showMessageDialog(null,"Por favor primero seleccionar un descuento");
				}
			}
		});
		btnDesactivarDescuentoSeleccionado.setBounds(285, 334, 239, 23);
		frmDescuentos.getContentPane().add(btnDesactivarDescuentoSeleccionado);
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
			fila[5] = descuento.getEstablecimientoCuit().toString();
			fila[6] = Integer.toString(descuento.getActivo());
			((DefaultTableModel) table.getModel()).addRow(fila);
		}
	}
}
