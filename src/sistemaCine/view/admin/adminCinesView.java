package sistemaCine.view.admin;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import sistemaCine.clases.Establecimiento;
import sistemaCine.clases.Pelicula;
import sistemaCine.clases.SistemaCines;
import sistemaCine.services.EstablecimientoService;
import sistemaCine.services.PeliculaServices;
import sistemaCine.utils.GeneralFrame;
import sistemaCine.utils.IsTest;

public class adminCinesView extends GeneralFrame {

	private static final long serialVersionUID = 1L;
	private static adminCinesView instancia;
	private SistemaCines sistema;
	private JFrame frame;
	private JLabel lblEstablecimiento;
	private JComboBox<String> comboBoxEstablecimiento;
	private JButton btnCrearEstablecimiento;
	private JButton btnCrearPelicula;
	private JComboBox<String> comboBoxPeliculas;
	private JLabel lblPelicula;
	private JButton btnEditarEstablecimiento;
	private JButton btnEditarPelicula;

	public static adminCinesView getInstancia() {
		if (instancia == null) {
			instancia = new adminCinesView();
		}
		return instancia;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminCinesView window = new adminCinesView();
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
	public adminCinesView() {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	protected void initialize() {
		frame = new JFrame();
		super.frame = frame;
		frame.setBounds(100, 100, 600, 400);

		frame.getContentPane().setLayout(null);

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				close();
			}
		});
		lblEstablecimiento = new JLabel("Establecimiento");

		lblEstablecimiento.setBounds(12, 50, 130, 16);
		frame.getContentPane().add(lblEstablecimiento);

		comboBoxEstablecimiento = new JComboBox<>();

		comboBoxEstablecimiento.setBounds(12, 75, 130, 22);
		frame.getContentPane().add(comboBoxEstablecimiento);

		btnCrearEstablecimiento = new JButton("Crear");
		btnCrearEstablecimiento.setHorizontalAlignment(SwingConstants.LEFT);
		btnCrearEstablecimiento.setBounds(12, 109, 70, 25);
		frame.getContentPane().add(btnCrearEstablecimiento);

		lblPelicula = new JLabel("Pelicula");
		lblPelicula.setBounds(187, 51, 70, 15);
		frame.getContentPane().add(lblPelicula);

		comboBoxPeliculas = new JComboBox<String>();
		comboBoxPeliculas.setBounds(187, 74, 279, 24);
		frame.getContentPane().add(comboBoxPeliculas);
		comboBoxPeliculas.addItem(null);
		btnCrearPelicula = new JButton("Crear");
		btnCrearPelicula.setBounds(187, 109, 70, 25);
		frame.getContentPane().add(btnCrearPelicula);

		btnEditarEstablecimiento = new JButton("Editar");
		btnEditarEstablecimiento.setBounds(83, 110, 70, 25);
		frame.getContentPane().add(btnEditarEstablecimiento);

		btnEditarPelicula = new JButton("Editar");
		btnEditarPelicula.setBounds(262, 109, 70, 25);
		frame.getContentPane().add(btnEditarPelicula);

		btnEditarPelicula.addActionListener(e -> {
			if (comboBoxPeliculas.getSelectedItem() != null) {
				AltaModificacionPeliculaView
						.getInstancia(sistema.peliculas.get(comboBoxPeliculas.getSelectedItem().toString())).setOldGF(this);
			}
		});
		btnCrearPelicula.addActionListener(e ->

		AltaModificacionPeliculaView.getInstancia(null).setOldGF(this)

		);
		btnCrearEstablecimiento.addActionListener(e ->

		AltaModificacionEstablecimientoView.getInstancia(null).setOldGF(this));
		btnEditarEstablecimiento.addActionListener(e -> {
			if (comboBoxEstablecimiento.getSelectedItem() != null) {
				AltaModificacionEstablecimientoView
						.getInstancia(sistema.establecimientos.get(comboBoxEstablecimiento.getSelectedItem()).getCuit())
						.setOldGF(this);
			}
		});
		comboBoxEstablecimiento.addItem(null);
		if (!IsTest.is) {
			try {
				this.sistema.establecimientos = EstablecimientoService.getAllEstablecimietosMap();
				this.sistema.peliculas = PeliculaServices.getAllPeliculasMap();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else {
			myTest();
		}
		List<String> list = new ArrayList<>();
		list.addAll(sistema.peliculas.keySet());
		list.sort((a, b) -> a.compareToIgnoreCase(b));
		for (String nombreEstablecimiento : sistema.establecimientos.keySet()) {
			comboBoxEstablecimiento.addItem(nombreEstablecimiento);
		}
		for (String key : list) {
			comboBoxPeliculas.addItem(key);
		}

	}

	private void myTest() {
		sistema.establecimientos = new HashMap<>();
		for (int i = 1; i < 10; i++) {
			sistema.establecimientos.put(Integer.toString(i),
					new Establecimiento(i, Integer.toString(i), Integer.toString(i), i));
		}
		sistema.peliculas = new HashMap<>();
		for (int i = 1; i < 10; i++) {
			Pelicula pelicula = new Pelicula(Integer.toString(i), Integer.toString(i), Integer.toString(i), i,
					Integer.toString(i), i % 2 == 0, i, Integer.toString(i));
			sistema.peliculas.put(pelicula.toString(), pelicula);
			pelicula = new Pelicula(Integer.toString(i), Integer.toString(i), Integer.toString(i), i,
					Integer.toString(i), i % 2 != 0, i, Integer.toString(i));
			sistema.peliculas.put(pelicula.toString(), pelicula);
		}
	}

	@Override
	protected void deleteInstance() {
		instancia = null;
	}

}
