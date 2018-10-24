package sistemaCine.view.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import sistemaCine.CineDAO.EstablecimientoDAO;
import sistemaCine.clases.Establecimiento;
import sistemaCine.clases.Sala;
import sistemaCine.services.EstablecimientoService;
import sistemaCine.services.SalaServices;
import sistemaCine.utils.IntegerField;
import sistemaCine.utils.IsTest;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class AltaModificacionEstablecimientoView extends javax.swing.JFrame {
	static AltaModificacionEstablecimientoView instancia;
	private JFrame frame;
	private JTextField compNombre;
	private JTextField compDireccion;
	private JTextField compCapacidad;
	private JTextField compCuit;
	private JButton btnCrear;
	private JButton btnEliminar;
	private JButton btnCancelar;
	private static Integer cuit = null;
	private JLabel lblSalas;
	private JButton btnEditarSala;
	private JComboBox<String> comboBoxSalas;
	private JPanel panelSalas;

	/**
	 * Launch the application.
	 */
	public static AltaModificacionEstablecimientoView getInstancia(Integer c) {
		cuit = c;
		if (instancia == null) {
			instancia = new AltaModificacionEstablecimientoView();
		}
		return instancia;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaModificacionEstablecimientoView window = new AltaModificacionEstablecimientoView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AltaModificacionEstablecimientoView() {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				frame.dispose();
			}
		});
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		compCuit = new IntegerField();
		compCuit.setBounds(22, 43, 131, 20);
		frame.getContentPane().add(compCuit);

		compNombre = new JTextField();
		compNombre.setBounds(22, 99, 131, 20);
		frame.getContentPane().add(compNombre);
		compNombre.setColumns(10);

		compDireccion = new JTextField();
		compDireccion.setBounds(22, 155, 131, 20);
		frame.getContentPane().add(compDireccion);
		compDireccion.setColumns(10);

		compCapacidad = new IntegerField();
		compCapacidad.setBounds(22, 211, 131, 20);
		frame.getContentPane().add(compCapacidad);
		compCapacidad.setColumns(10);

		JLabel lblCapacidad = new JLabel("Capacidad");
		lblCapacidad.setBounds(22, 186, 131, 14);
		frame.getContentPane().add(lblCapacidad);

		JLabel lblDireccion = new JLabel("Direcci\u00F3n");
		lblDireccion.setBounds(23, 130, 130, 14);
		frame.getContentPane().add(lblDireccion);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(22, 74, 131, 14);
		frame.getContentPane().add(lblNombre);

		JLabel lblCuit = new JLabel("Cuit");
		lblCuit.setBounds(22, 18, 131, 14);
		frame.getContentPane().add(lblCuit);

		btnCrear = new JButton("Crear");
		btnCrear.setBounds(22, 264, 118, 23);
		frame.getContentPane().add(btnCrear);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(321, 263, 117, 25);
		frame.getContentPane().add(btnCancelar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(152, 263, 117, 25);
		frame.getContentPane().add(btnEliminar);

		panelSalas = new JPanel();
		panelSalas.setBounds(215, 43, 223, 157);
		frame.getContentPane().add(panelSalas);
		panelSalas.setLayout(null);
		panelSalas.setVisible(false);
		lblSalas = new JLabel("Salas");
		lblSalas.setBounds(12, 12, 70, 15);
		panelSalas.add(lblSalas);

		btnEditarSala = new JButton("Crear/Editar");
		btnEditarSala.setBounds(12, 69, 138, 25);
		panelSalas.add(btnEditarSala);

		comboBoxSalas = new JComboBox<String>();
		comboBoxSalas.setBounds(12, 33, 138, 24);
		panelSalas.add(comboBoxSalas);
		comboBoxSalas.addItem(null);
		btnEliminar.setVisible(false);
		btnCancelar.addActionListener(e -> {
			this.frame.dispose();
		});
		if (cuit == null) {
			btnCrear.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					EstablecimientoService.crearEstablecimiento(
							new Establecimiento(Integer.parseInt(compCuit.getText()), compNombre.getText(),
									compDireccion.getText(), Integer.parseInt(compCapacidad.getText())));
					;
				}
			});
		} else {
			setModificar();
		}
	}

	public void setModificar() {
		Establecimiento establecimiento;
		if (!IsTest.is) {
			establecimiento = EstablecimientoService.getEstablecimieto(cuit);
		} else {
			establecimiento = new Establecimiento(cuit, Integer.toString(cuit), Integer.toString(cuit), cuit);
		}
		compCuit.setText(Integer.toString(establecimiento.getCuit()));
		compNombre.setText(establecimiento.getNombre());
		compDireccion.setText(establecimiento.getDomicilio());
		compCapacidad.setText(Integer.toString(establecimiento.getCapacidadTotal()));
		btnCrear.setText("Modificar");
		btnEliminar.setVisible(true);
		btnEliminar.addActionListener(e -> {
			EstablecimientoService.eliminarEstablecimiento(establecimiento);
		});
		btnCrear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EstablecimientoService.updateEstablecimiento(new Establecimiento(Integer.parseInt(compCuit.getText()),
						compNombre.getText(), compDireccion.getText(), Integer.parseInt(compCapacidad.getText())));
				;
			}
		});

		compCuit.setEditable(false);
		panelSalas.setVisible(false);
		for (Sala sala : SalaServices.getSalas(cuit)) {
			comboBoxSalas.addItem(sala.getNombre());
		}
		btnEditarSala.addActionListener(e -> {
			AltaModificacionSalaView.getInstancia(comboBoxSalas.getSelectedItem()).setVisible(true);
		});

	}
}
