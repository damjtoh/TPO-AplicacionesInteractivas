package sistemaCine.view.cliente;

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

import Usuarios.Usuario;
import sistemaCine.CineDAO.PeliculaDAO;
import sistemaCine.clases.Establecimiento;
import sistemaCine.clases.Funcion;
import sistemaCine.clases.Pelicula;
import sistemaCine.services.EstablecimientoService;
import sistemaCine.services.FuncionServices;
import sistemaCine.services.PeliculaServices;

public class ABMPeliculasView extends javax.swing.JFrame {

	private static ABMPeliculasView instancia;
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
	private JPanel establecimientosLayout;

	/**
	 * Launch the application.
	 */

	public static ABMPeliculasView getInstancia() {
		if (instancia == null) {
			instancia = new ABMPeliculasView();
		}
		return instancia;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ABMPeliculasView window = new ABMPeliculasView();
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
	public ABMPeliculasView() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 515, 321);
		

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				instancia = null;
				frame.dispose();
			}
		});
		frame.getContentPane().setLayout(null);
		
		peliculasLayout = new JPanel();
		peliculasLayout.setBounds(0, 13, 274, 44);
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
			comboBoxEstablecimiento.removeAllItems();
			if (cuit != null) {
				this.establecimientos = EstablecimientoService.getAllEstablecimietosMap();
				for (String nombreEstablecimiento : establecimientos.keySet()) {
					comboBoxEstablecimiento.addItem(nombreEstablecimiento);
				}
				establecimientosLayout.setVisible(true);
			}
		} else {
			establecimientosLayout.setVisible(false);
		}
		diaLayout.setVisible(false);
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
		lblPrecio.setBounds(42, 13, 121, 31);
		buttonLayout.add(lblPrecio);
		buttonLayout.setVisible(false);
		
		establecimientosLayout = new JPanel();
		establecimientosLayout.setBounds(286, 0, 151, 57);
		frame.getContentPane().add(establecimientosLayout);
		establecimientosLayout.setLayout(null);
		lblEstablecimiento = new JLabel("Establecimiento");
		lblEstablecimiento.setBounds(13, 8, 89, 16);
		establecimientosLayout.add(lblEstablecimiento);
		
				comboBoxEstablecimiento = new JComboBox<>();
				comboBoxEstablecimiento.setBounds(13, 35, 116, 22);
				establecimientosLayout.add(comboBoxEstablecimiento);
				comboBoxEstablecimiento.addItem(null);
				comboBoxEstablecimiento.addItemListener(e -> {
					if (comboBoxEstablecimiento.getSelectedItem() != null) {
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
//					establecimientosLayout.setVisible(false);
					horarioLayout.setVisible(false);
					buttonLayout.setVisible(false);

				});
		establecimientosLayout.setVisible(false);
		diaLayout.setVisible(false);
		horarioLayout.setVisible(false);
		btnSeleccionar.addActionListener(e -> {
			SeleccionarAsientosView.getInstance(
					funciones.get(comboBoxDia.getSelectedItem()).get(comboBoxHorario.getSelectedItem()),
					establecimientos.get(comboBoxEstablecimiento.getSelectedItem()));
		});
		
	}
}
