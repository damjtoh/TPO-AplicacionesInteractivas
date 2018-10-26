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

import sistemaCine.clases.Establecimiento;
import sistemaCine.clases.Pelicula;
import sistemaCine.services.EstablecimientoService;
import sistemaCine.services.PeliculaServices;
import sistemaCine.utils.GeneralFrame;
import sistemaCine.utils.IsTest;

public class adminCinesView extends  GeneralFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static adminCinesView instancia;
	private JLabel lblEstablecimiento;
	private JComboBox<String> comboBoxEstablecimiento;
	private Map<String, Establecimiento> establecimientos;
	private JButton btnEditarEstablecimiento;
	private JButton btnEditarPelicula;
	private JComboBox<String> comboBoxPeliculas;
	private JLabel lblPelicula;
	private Map<String, Pelicula> peliculas;

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
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);

		frame.getContentPane().setLayout(null);

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				instancia = null;
				frame.dispose();
			}
		});
		lblEstablecimiento = new JLabel("Establecimiento");

		lblEstablecimiento.setBounds(12, 50, 130, 16);
		frame.getContentPane().add(lblEstablecimiento);

		comboBoxEstablecimiento = new JComboBox<>();

		comboBoxEstablecimiento.setBounds(12, 75, 130, 22);
		frame.getContentPane().add(comboBoxEstablecimiento);

		btnEditarEstablecimiento = new JButton("Crear/Editar");
		btnEditarEstablecimiento.setBounds(12, 109, 138, 25);
		frame.getContentPane().add(btnEditarEstablecimiento);

		lblPelicula = new JLabel("Pelicula");
		lblPelicula.setBounds(187, 51, 70, 15);
		frame.getContentPane().add(lblPelicula);

		comboBoxPeliculas = new JComboBox<String>();
		comboBoxPeliculas.setBounds(187, 74, 279, 24);
		frame.getContentPane().add(comboBoxPeliculas);
		comboBoxPeliculas.addItem(null);
		btnEditarPelicula = new JButton("Crear/Editar");
		btnEditarPelicula.setBounds(187, 109, 145, 25);
		frame.getContentPane().add(btnEditarPelicula);

		btnEditarPelicula.addActionListener(e -> {
			if (comboBoxPeliculas.getSelectedItem() != null) {
				AltaModificacionPeliculaView
						.getInstancia(peliculas.get(comboBoxPeliculas.getSelectedItem().toString()));
			} else {
				AltaModificacionPeliculaView.getInstancia(null);
			}
		});
		btnEditarEstablecimiento.addActionListener(e -> {
			if (comboBoxEstablecimiento.getSelectedItem() != null) {
				AltaModificacionEstablecimientoView
						.getInstancia(establecimientos.get(comboBoxEstablecimiento.getSelectedItem()).getCuit());
			} else {
				AltaModificacionEstablecimientoView.getInstancia(null);
			}
		});
		
		addItemsEstablecimientos();
		addItemsPeliculas();

	}
	private void addItemsEstablecimientos() {
		try {
			if (!IsTest.is) {
				this.establecimientos = EstablecimientoService.getAllEstablecimietosMap();
			} else {
				myTest();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		comboBoxEstablecimiento.removeAllItems();
		comboBoxEstablecimiento.addItem(null);
		for (String nombreEstablecimiento : establecimientos.keySet()) {
			comboBoxEstablecimiento.addItem(nombreEstablecimiento);
		}
		comboBoxEstablecimiento.setVisible(false);
		comboBoxEstablecimiento.setVisible(true);
	}
	private void addItemsPeliculas() {
		if (!IsTest.is) {
			this.peliculas = PeliculaServices.getAllPeliculasMap();
		} else {
			myTest();
		}
		comboBoxPeliculas.removeAllItems();
		comboBoxPeliculas.addItem(null);
		List<String> list = new ArrayList<>();
		list.addAll(peliculas.keySet());
		list.sort((a, b) -> a.compareToIgnoreCase(b));
		for (String key : list) {
			comboBoxPeliculas.addItem(key);
		}
	}

	private void myTest() {
		establecimientos = new HashMap<>();
		for (int i = 1; i < 10; i++) {
			establecimientos.put(Integer.toString(i),
					new Establecimiento(i, Integer.toString(i), Integer.toString(i), i));
		}
		peliculas = new HashMap<>();
		for (int i = 1; i < 10; i++) {
			Pelicula pelicula = new Pelicula(Integer.toString(i), Integer.toString(i), Integer.toString(i), i,
					Integer.toString(i), i % 2 == 0, i, Integer.toString(i));
			peliculas.put(pelicula.toString(), pelicula);
			pelicula = new Pelicula(Integer.toString(i), Integer.toString(i), Integer.toString(i), i,
					Integer.toString(i), i % 2 != 0, i, Integer.toString(i));
			peliculas.put(pelicula.toString(), pelicula);
		}
	}
}
