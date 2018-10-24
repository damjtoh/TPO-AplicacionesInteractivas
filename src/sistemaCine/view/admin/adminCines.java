package sistemaCine.view.admin;

import java.awt.EventQueue;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
import sistemaCine.utils.IsTest;

public class adminCines {

	private JFrame frame;
	private JLabel lblEstablecimiento;
	private JComboBox<String> comboBoxEstablecimiento;
	private Map<String, Establecimiento> establecimientos;
	private JButton btnEditarEstablecimiento;
	private JButton btnEditarPelicula;
	private JComboBox<String> comboBoxPeliculas;
	private JLabel lblPelicula;
	private Map<String,Pelicula> peliculas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminCines window = new adminCines();
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
	public adminCines() {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

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
						.getInstancia(establecimientos.get(comboBoxEstablecimiento.getSelectedItem()).getCuit())
						.setVisible(true);
			} else {
				AltaModificacionPeliculaView.getInstancia(null);
			}
		});
		btnEditarEstablecimiento.addActionListener(e -> {
			if (comboBoxEstablecimiento.getSelectedItem() != null) {
				AltaModificacionEstablecimientoView
						.getInstancia(establecimientos.get(comboBoxEstablecimiento.getSelectedItem()).getCuit())
						.setVisible(true);
			} else {
				AltaModificacionEstablecimientoView.getInstancia(null);
			}
		});
		comboBoxEstablecimiento.addItem(null);
		if (!IsTest.is) {
			this.establecimientos = EstablecimientoService.getAllEstablecimietosMap();
			this.peliculas = PeliculaServices.getAllPeliculasMap();
		} else {
			establecimientos = new HashMap<>();
			for (int i = 1; i < 10; i++) {
				establecimientos.put(Integer.toString(i),
						new Establecimiento(i, Integer.toString(i), Integer.toString(i), i));
			}
			peliculas = new HashMap<>();
			for (int i = 1; i < 10; i++) {
				Pelicula pelicula = new Pelicula(Integer.toString(i), Integer.toString(i), Integer.toString(i), i, Integer.toString(i), i%2==0, i, Integer.toString(i));
				peliculas.put(pelicula.toString(),pelicula);
				pelicula = new Pelicula(Integer.toString(i), Integer.toString(i), Integer.toString(i), i, Integer.toString(i), i%2!=0, i, Integer.toString(i));
				peliculas.put(pelicula.toString(),pelicula);
			}
		}
		List<String> list= new ArrayList<>();
		list.addAll(peliculas.keySet());
		list.sort((a,b) -> a.compareToIgnoreCase(b));
		for (String nombreEstablecimiento : establecimientos.keySet()) {
			comboBoxEstablecimiento.addItem(nombreEstablecimiento);
		}
		for (String key : list) {
			comboBoxPeliculas.addItem(key);
		}

	}
}
