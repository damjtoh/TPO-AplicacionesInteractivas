package sistemaCine.view.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import sistemaCine.CineDAO.EstablecimientoDAO;
import sistemaCine.clases.Establecimiento;
import sistemaCine.clases.Sala;
import sistemaCine.services.EstablecimientoService;
import sistemaCine.services.SalaServices;
import sistemaCine.utils.GeneralFrame;
import sistemaCine.utils.IntegerField;
import sistemaCine.utils.IsTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.awt.Font;

@SuppressWarnings("serial")
public class AltaModificacionEstablecimientoView extends GeneralFrame {
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
	private JButton btnCrearSala;
	private JComboBox<String> comboBoxSalas;
	private JPanel panelSalas;
	private JButton btnEditarSala;

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
	protected void initialize() {
		frame = new JFrame();
		super.frame = frame;
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				btnCancelar.doClick();
			}
		});
		frame.setBounds(100, 100, 450, 300);
//		
		frame.getContentPane().setLayout(null);
		compCuit = new IntegerField();
		compCuit.setBounds(22, 29, 131, 20);
		frame.getContentPane().add(compCuit);

		compNombre = new JTextField();
		compNombre.setBounds(22, 83, 131, 20);
		frame.getContentPane().add(compNombre);
		compNombre.setColumns(10);

		compDireccion = new JTextField();
		compDireccion.setBounds(22, 143, 131, 20);
		frame.getContentPane().add(compDireccion);
		compDireccion.setColumns(10);

		compCapacidad = new IntegerField();
		compCapacidad.setBounds(22, 199, 131, 20);
		frame.getContentPane().add(compCapacidad);
		compCapacidad.setColumns(10);

		JLabel lblCapacidad = new JLabel("Capacidad");
		lblCapacidad.setBounds(22, 176, 131, 14);
		frame.getContentPane().add(lblCapacidad);

		JLabel lblDireccion = new JLabel("Direcci\u00F3n");
		lblDireccion.setBounds(23, 116, 130, 14);
		frame.getContentPane().add(lblDireccion);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(22, 62, 131, 14);
		frame.getContentPane().add(lblNombre);

		JLabel lblCuit = new JLabel("Cuit");
		lblCuit.setBounds(22, 13, 131, 14);
		frame.getContentPane().add(lblCuit);

		btnCrear = new JButton("Crear Establecimiento");
		btnCrear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCrear.setBounds(225, 199, 195, 25);
		frame.getContentPane().add(btnCrear);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(303, 224, 117, 25);
		frame.getContentPane().add(btnCancelar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(22, 224, 117, 25);
		frame.getContentPane().add(btnEliminar);

		panelSalas = new JPanel();
		panelSalas.setBounds(215, 43, 223, 157);
		frame.getContentPane().add(panelSalas);
		panelSalas.setLayout(null);
		panelSalas.setVisible(false);
		lblSalas = new JLabel("Salas");
		lblSalas.setBounds(12, 12, 70, 15);
		panelSalas.add(lblSalas);

		btnCrearSala = new JButton("Crear Sala");
		btnCrearSala.setBounds(12, 69, 138, 25);
		panelSalas.add(btnCrearSala);

		comboBoxSalas = new JComboBox<>();
		comboBoxSalas.setBounds(12, 33, 138, 24);
		panelSalas.add(comboBoxSalas);
		
		btnEditarSala = new JButton("Editar Sala");
		btnEditarSala.setBounds(12, 96, 138, 25);
		panelSalas.add(btnEditarSala);
		comboBoxSalas.addItem(null);
		btnEliminar.setVisible(false);
		btnCancelar.addActionListener(e -> {
			close();
		});
		if (cuit == null) {
			btnCrear.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						EstablecimientoService.crearEstablecimiento(
								new Establecimiento(Integer.parseInt(compCuit.getText()), compNombre.getText(),
										compDireccion.getText(), Integer.parseInt(compCapacidad.getText())));
						btnCancelar.doClick();
					} catch (NumberFormatException | SQLException e1) {
						btnCrear.setBackground(Color.RED);
						e1.printStackTrace();
					}
				}
			});
		} else {
			try {
				setModificar();
			} catch (SQLException e1) {
				btnCrear.setBackground(Color.RED);
				compNombre.setText("Error en obtencion establecimiento");
				e1.printStackTrace();
			}
		}
	}

	public void setModificar() throws SQLException {
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
			try {
				EstablecimientoService.eliminarEstablecimiento(establecimiento);
				btnCancelar.doClick();
			} catch (SQLException e1) {
				btnEliminar.setBackground(Color.RED);
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Eliminacion Fallida", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		});
		btnCrear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					EstablecimientoService.updateEstablecimiento(new Establecimiento(Integer.parseInt(compCuit.getText()),
							compNombre.getText(), compDireccion.getText(), Integer.parseInt(compCapacidad.getText())));
				} catch (NumberFormatException | SQLException e1) {
					btnCrear.setBackground(Color.RED);
					e1.printStackTrace();
				}
			}
		});

		compCuit.setEditable(false);
		try {
			for (Sala sala : SalaServices.getSalas(cuit)) {
				comboBoxSalas.addItem(sala.getNombre());
			}
		} catch (SQLException e1) {
			comboBoxSalas.addItem("Error on get salas");
			e1.printStackTrace();
		}
		btnEditarSala.addActionListener(e -> {
			if (comboBoxSalas.getSelectedItem()!=null) {
				AltaModificacionSalaView.getInstancia(comboBoxSalas.getSelectedItem().toString(),cuit).setOldGF(this);
			}
		});
		btnCrearSala.addActionListener(e -> {
				AltaModificacionSalaView.getInstancia(null,cuit).setOldGF(this);
		});
		panelSalas.setVisible(true);
	}

	@Override
	protected void deleteInstance() {
		instancia = null;
	}

}
