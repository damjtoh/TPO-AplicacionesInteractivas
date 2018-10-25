package sistemaCine.view.cliente;

import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sistemaCine.CineDAO.PeliculaDAO;
import sistemaCine.clases.Establecimiento;
import sistemaCine.clases.Funcion;
import sistemaCine.clases.Pelicula;
import sistemaCine.services.EstablecimientoService;
import sistemaCine.services.FuncionServices;

public class ABMFuncionesEstablecimientosView extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ABMFuncionesEstablecimientosView instancia;
	private JFrame frame;
	private JButton btnSeleccionar;
	private JComboBox<String> comboBoxHorario;
	private JLabel lblHorario;
	private JPanel horarioLayout;
	private JComboBox<String> comboBoxDia;
	private JLabel lblDia;
	private JPanel diaLayout;
	private JComboBox<String> comboBoxPeliculas;
	private JLabel lblPeliculas;
	private Container peliculasLayout;
	private JComboBox<String> comboBoxEstablecimiento;
	private JLabel lblEstablecimiento;
	private Map<String, Establecimiento> establecimientos = new HashMap<>();
	private Map<String, Pelicula> peliculas;
	private Map<String, Map<String, Funcion>> funciones;
	private JPanel buttonLayout;
	private JLabel lblPrecio;

	/**
	 * Launch the application.
	 */
	public static ABMFuncionesEstablecimientosView getInstancia() {
		if (instancia == null) {
			instancia = new ABMFuncionesEstablecimientosView();
		}
		return instancia;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ABMFuncionesEstablecimientosView window = new ABMFuncionesEstablecimientosView();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ABMFuncionesEstablecimientosView() {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				frame.dispose();
			}
		});
		lblEstablecimiento = new JLabel("Establecimiento");
		lblEstablecimiento.setBounds(12, 13, 130, 16);
		frame.getContentPane().add(lblEstablecimiento);

		comboBoxEstablecimiento = new JComboBox<>();
		comboBoxEstablecimiento.setBounds(12, 35, 130, 22);
		frame.getContentPane().add(comboBoxEstablecimiento);
		comboBoxEstablecimiento.addItem(null);
		this.establecimientos = EstablecimientoService.getAllEstablecimietosMap();
		for (String nombreEstablecimiento : establecimientos.keySet()) {
			comboBoxEstablecimiento.addItem(nombreEstablecimiento);
		}
		comboBoxEstablecimiento.addItemListener(e -> {

			if (comboBoxEstablecimiento.getSelectedItem() != null) {
				comboBoxPeliculas.removeAllItems();
				Integer cuit = null;
				cuit = establecimientos.get(comboBoxEstablecimiento.getSelectedItem()).getCuit();

				if (cuit != null) {
					List<Integer> idsPeliculas = FuncionServices.getPeliculasEstablecimientoIDS(cuit,
							(Date) new java.util.Date());
					List<Pelicula> peliculasList = new ArrayList<>();
					peliculasList.addAll(PeliculaDAO.getPeliculas(idsPeliculas));
//					for (Integer id : idsPeliculas) {
//						Pelicula pelicula = new Pelicula(null, null, null, 0, null, null, 0, null);
//						pelicula.setId(id);
//						peliculasList.addAll(PeliculaDAO.buscarPeliculas(pelicula));
//					}
					for (Pelicula pelicula : peliculasList) {
						peliculas.put(pelicula.toString(), pelicula);
						comboBoxPeliculas.addItem(pelicula.toString());
					}
					peliculasLayout.setVisible(true);
				}
			} else {
				peliculasLayout.setVisible(false);
			}

			diaLayout.setVisible(false);
			horarioLayout.setVisible(false);
			buttonLayout.setVisible(false);

		});
		peliculasLayout = new JPanel();
		peliculasLayout.setBounds(158, 13, 274, 44);
		frame.getContentPane().add(peliculasLayout);
		peliculasLayout.setLayout(null);

		lblPeliculas = new JLabel("Pelicula");
		lblPeliculas.setBounds(26, 0, 142, 16);
		peliculasLayout.add(lblPeliculas);

		comboBoxPeliculas = new JComboBox<>();
		comboBoxPeliculas.setBounds(23, 22, 251, 22);
		peliculasLayout.add(comboBoxPeliculas);
		comboBoxPeliculas.addItem(null);
		comboBoxPeliculas.addActionListener(e -> {
			if (comboBoxPeliculas.getSelectedItem() != null) {
				comboBoxDia.removeAllItems();
				funciones = FuncionServices.getFuncionesMap(peliculas.get(comboBoxPeliculas.getSelectedItem()),
						(Date) new java.util.Date(),
						establecimientos.get(comboBoxEstablecimiento.getSelectedItem()).getCuit());
				for (String dia : funciones.keySet()) {
					comboBoxDia.addItem(dia);
				}
				diaLayout.setVisible(true);
			} else {
				diaLayout.setVisible(false);

			}
			horarioLayout.setVisible(false);
			buttonLayout.setVisible(false);

		});

		diaLayout = new JPanel();
		diaLayout.setBounds(0, 70, 142, 71);
		frame.getContentPane().add(diaLayout);
		diaLayout.setLayout(null);

		lblDia = new JLabel("Dia");
		lblDia.setBounds(12, 14, 57, 16);
		diaLayout.add(lblDia);

		comboBoxDia = new JComboBox<>();
		comboBoxDia.setBounds(12, 34, 109, 22);
		diaLayout.add(comboBoxDia);
		comboBoxDia.addItem(null);

		comboBoxDia.addActionListener(e -> {
			comboBoxHorario.removeAllItems();
			if (comboBoxDia.getSelectedItem() != null) {
				for (String hora : funciones.get(comboBoxDia.getSelectedItem()).keySet()) {
					comboBoxHorario.addItem(hora);
				}
				comboBoxHorario.setVisible(true);
			} else {
				comboBoxHorario.setVisible(false);
			}
			buttonLayout.setVisible(false);
		});
		horarioLayout = new JPanel();
		horarioLayout.setBounds(146, 70, 286, 71);
		frame.getContentPane().add(horarioLayout);
		horarioLayout.setLayout(null);

		lblHorario = new JLabel("Horario");
		lblHorario.setBounds(12, 13, 56, 16);
		horarioLayout.add(lblHorario);

		comboBoxHorario = new JComboBox<>();
		comboBoxHorario.setBounds(12, 36, 123, 22);
		horarioLayout.add(comboBoxHorario);
		comboBoxHorario.addItem(null);
		comboBoxHorario.addActionListener(e -> {
			if (comboBoxHorario.getSelectedItem() != null) {
				lblPrecio.setText("$"
						+ funciones.get(comboBoxDia.getSelectedItem()).get(comboBoxHorario.getSelectedItem()).getValor()
						+ " c/u");
				buttonLayout.setVisible(true);
			} else {
				buttonLayout.setVisible(false);
			}
		});

		buttonLayout = new JPanel();
		buttonLayout.setBounds(117, 168, 254, 85);
		frame.getContentPane().add(buttonLayout);
		buttonLayout.setLayout(null);

		btnSeleccionar = new JButton("Seleccionar asientos");
		btnSeleccionar.setBounds(12, 47, 195, 25);
		buttonLayout.add(btnSeleccionar);
		lblPrecio = new JLabel("");
		lblPrecio.setBounds(38, 13, 101, 15);
		buttonLayout.add(lblPrecio);
		buttonLayout.setVisible(false);
		peliculasLayout.setVisible(false);
		diaLayout.setVisible(false);
		horarioLayout.setVisible(false);
		btnSeleccionar.addActionListener(e -> {
			SeleccionarAsientosView.getInstance(
					funciones.get(comboBoxDia.getSelectedItem()).get(comboBoxHorario.getSelectedItem()),
					establecimientos.get(comboBoxEstablecimiento.getSelectedItem()));
		});
	}
}
