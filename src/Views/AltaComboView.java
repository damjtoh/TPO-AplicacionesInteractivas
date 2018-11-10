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
import java.util.ArrayList;
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
import presentacion.ComboPresentacion;
import presentacion.DescuentoPresentacion;
import presentacion.Promo2x1Presentacion;
import presentacion.xPorcentajePrecioVentaPresentacion;

public class AltaComboView extends JFrame {

	private JFrame frmDescuentos;
	private JTable table;
	private Collection<DescuentoPresentacion> descuentosPresentacion;
	private DescuentosView descuentosView;
	
	public AltaComboView() {
		//this.descuentosView = DescuentosDAO.getAll();
		initialize();
		frmDescuentos.setVisible(true);
	}


	private void initialize() {
		frmDescuentos = new JFrame();
		frmDescuentos.setResizable(false);
		frmDescuentos.setTitle("Combo");
		frmDescuentos.setBounds(100, 100, 556, 358);
		frmDescuentos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
				"Nombre", "Tipo"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		descuentosPresentacion = new ArrayList<DescuentoPresentacion>();
		
		LoadTable();
		
		scrollPane.setViewportView(table);
		
		JButton btnAltaDescuentox = new JButton("Agregar Descuento 2x1");
		btnAltaDescuentox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombre = JOptionPane.showInputDialog("Ingrese el nombre del descuento");
				if(nombre!=null && nombre.length()>0) {
					DescuentoPresentacion descuentoPresentacion = new Promo2x1Presentacion(null,null,nombre);
					descuentosPresentacion.add(descuentoPresentacion);
					LoadTable();
				}
			}
		});
		btnAltaDescuentox.setBounds(29, 236, 187, 23);
		frmDescuentos.getContentPane().add(btnAltaDescuentox);
		
		JButton btnAltaDescuentoPorcentaje = new JButton("Agregar Descuento Porcentaje");
		btnAltaDescuentoPorcentaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombre = JOptionPane.showInputDialog("Ingrese el nombre del descuento");
				if(nombre!=null && nombre.length()>0) {
					float porcentaje = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el porcentaje de descuento (numero entero de 0.01 a 100)"));

					DescuentoPresentacion descuentoPresentacion = new xPorcentajePrecioVentaPresentacion(null,null,porcentaje,nombre);
					descuentosPresentacion.add(descuentoPresentacion);
					LoadTable();
				}
				
			}
		});
		btnAltaDescuentoPorcentaje.setBounds(29, 264, 187, 23);
		frmDescuentos.getContentPane().add(btnAltaDescuentoPorcentaje);
		
		JButton btnEliminarDescuentoSeleccionado = new JButton("Dar Alta al Combo");
		btnEliminarDescuentoSeleccionado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombre = JOptionPane.showInputDialog("Ingrese el nombre del descuento");
				if(nombre!=null && nombre.length()>0) {
					String fechaInicioStr = JOptionPane.showInputDialog("Ingrese la fecha de inicio (dd/mm/aaaa)");
					if(fechaInicioStr!=null && fechaInicioStr.length()>0) {
						String fechaFinStr = JOptionPane.showInputDialog("Ingrese la fecha de fin (dd/mm/aaaa)");
						if(fechaFinStr!=null && fechaFinStr.length()>0) {
							//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
							DateFormat formatter;
							
							try {
								formatter = new SimpleDateFormat("dd/MM/yyyy");
								Date fechaInicio = (Date) formatter.parse(fechaInicioStr);
								Date fechaFin = (Date) formatter.parse(fechaFinStr);	
								
								if(!descuentosPresentacion.isEmpty()) {
									for(DescuentoPresentacion descuento : descuentosPresentacion) {
										descuento.setFechaInicio(fechaInicio);
										descuento.setFechaFin(fechaFin);
									}
									
									DescuentoPresentacion descuentoPresentacion = new ComboPresentacion(fechaInicio,fechaFin,descuentosPresentacion,nombre);
									
									
									SistemaFacturacion.GetInstancia().AltaDescuentoCombo(fechaInicio, fechaFin, descuentosPresentacion, nombre);
									
									descuentosView.LoadTable();
									frmDescuentos.dispose();
								}else {
									JOptionPane.showMessageDialog(null,"Por favor ingrese al menos un descuento");
								}
							}catch(Exception e) {
								JOptionPane.showMessageDialog(null,"Por favor ingresar la fecha correctamente");
							}
						}
					}
				}
			}
		});
		btnEliminarDescuentoSeleccionado.setBounds(285, 236, 239, 23);
		frmDescuentos.getContentPane().add(btnEliminarDescuentoSeleccionado);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(274, 236, -12, 82);
		frmDescuentos.getContentPane().add(separator);
	}
	
	public void LoadTable() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		int numCols = model.getColumnCount();
		
		for(DescuentoPresentacion descuento : descuentosPresentacion) {
			Object[] fila = new Object[numCols];
			fila[0] = descuento.getNombre();
			fila[1] = descuento.GetTipoDescuento().toString();
			((DefaultTableModel) table.getModel()).addRow(fila);
		}
	}

}
