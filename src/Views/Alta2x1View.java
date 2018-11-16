package Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import SistemaFacturacion.SistemaFacturacion;
import sistemaCine.clases.Establecimiento;
import sistemaCine.services.EstablecimientoService;
import sistemaCine.services.PeliculaServices;
import sistemaCine.view.admin.AltaModificacionEstablecimientoView;

import javax.swing.JComboBox;

public class Alta2x1View extends JFrame {

	private JPanel contentPane;
	
	private JTextField txtNombre;
	private JTextField txtFechaInicio;
	private JTextField txtFechaFin;
	Alta2x1View that = this;
	private Map<String, Establecimiento> establecimientos = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Alta2x1View frame = new Alta2x1View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Alta2x1View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
getContentPane().setLayout(null);
		contentPane.setLayout(null);
		
		
		JLabel lblCrearDescuentox = new JLabel("Crear descuento 2x1");
		lblCrearDescuentox.setBounds(20, 6, 137, 16);
		getContentPane().add(lblCrearDescuentox);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(21, 106, 61, 16);
		getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(112, 101, 130, 26);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblFechaInicio = new JLabel("Fecha inicio");
		lblFechaInicio.setBounds(20, 144, 80, 16);
		getContentPane().add(lblFechaInicio);
		
		txtFechaInicio = new JTextField();
		txtFechaInicio.setBounds(112, 139, 130, 26);
		getContentPane().add(txtFechaInicio);
		txtFechaInicio.setColumns(10);
		
		JLabel lblFechaFin = new JLabel("Fecha fin");
		lblFechaFin.setBounds(20, 185, 80, 16);
		getContentPane().add(lblFechaFin);
		
		txtFechaFin = new JTextField();
		txtFechaFin.setBounds(112, 180, 130, 26);
		getContentPane().add(txtFechaFin);
		txtFechaFin.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				that.dispose();
			}
		});
		btnCancelar.setBounds(75, 230, 117, 29);
		getContentPane().add(btnCancelar);
		
		JComboBox establecimientosComboBox = new JComboBox();
		establecimientosComboBox.setBounds(131, 52, 153, 24);
		contentPane.add(establecimientosComboBox);
		
		JLabel lblEstablecimiento = new JLabel("Establecimiento");
		lblEstablecimiento.setBounds(20, 55, 100, 16);
		contentPane.add(lblEstablecimiento);
		
		// Establecimientos
		try {
			establecimientos = EstablecimientoService.getAllEstablecimietosMap();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (String nombreEstablecimiento : establecimientos.keySet()) {
			establecimientosComboBox.addItem(nombreEstablecimiento);
		}
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Guardar
				String nombre = txtNombre.getText();
				String fechaInicioStr = txtFechaInicio.getText();
				String fechaFinStr = txtFechaFin.getText();
				Integer cuit = establecimientos.get(establecimientosComboBox.getSelectedItem()).getCuit();
				if(nombre!=null && nombre.length()>0 && fechaInicioStr!=null && fechaInicioStr.length()>0 && fechaFinStr!=null && fechaFinStr.length()>0) {
					DateFormat formatter;
					try{
						formatter = new SimpleDateFormat("dd/MM/yyyy");
						Date fechainicio = new Date(formatter.parse(fechaInicioStr).getTime());
						Date fechaFin = new Date(formatter.parse(fechaFinStr).getTime());
						SistemaFacturacion.GetInstancia().AltaDescuento2x1(fechainicio, fechaFin, nombre, cuit);
						DescuentosView.getInstancia().LoadTable();
						JOptionPane.showMessageDialog(null,"Éxito al crear descuento.");
						that.dispose();
					}catch(Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null,"Por favor ingresar la fecha correctamente");
					}
				} else {
					JOptionPane.showMessageDialog(null,"Por favor ingresar la fecha correctamente");
				}
			}
		});
		btnGuardar.setBounds(206, 230, 117, 29);
		getContentPane().add(btnGuardar);
	}
}
