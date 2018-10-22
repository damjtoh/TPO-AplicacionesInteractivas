package sistemaCine.view;

import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

import sistemaCine.CineDAO.EstablecimientoDAO;
import sistemaCine.CineDAO.FuncionDAO;
import sistemaCine.CineDAO.PeliculaDAO;
import sistemaCine.cinesClases.Establecimiento;
import sistemaCine.cinesClases.Funcion;
import sistemaCine.cinesClases.Pelicula;
import sistemaCine.utils.DateUtils;

public class ABMEstablecimientosView extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ABMEstablecimientosView instancia;
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
	private Component lblEstablecimiento;
	private Map<String, Establecimiento> establecimientos = new HashMap<String, Establecimiento>();
	private Map<String, Pelicula> peliculas;
	private Map<String, Map<String, Funcion>> funciones;
	private JPanel buttonLayout;
	private JLabel lblPrecio;

	/**
	 * Launch the application.
	 */
	public static ABMEstablecimientosView getInstancia() {
		if (instancia == null) {
			instancia = new ABMEstablecimientosView();
		}
		return instancia;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ABMEstablecimientosView window = new ABMEstablecimientosView();
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
	public ABMEstablecimientosView() {
		initialize();
		comboBoxEstablecimiento.addItem("lol");
		comboBoxEstablecimiento.addItem("lolsds");
		comboBoxEstablecimiento.addItem("loldsf");
		comboBoxEstablecimiento.addItem("lodttttl");
		comboBoxEstablecimiento.addItem("gg");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().setLayout(null);

		lblEstablecimiento = new JLabel("Establecimiento");
		lblEstablecimiento.setBounds(12, 13, 130, 16);
		frame.getContentPane().add(lblEstablecimiento);

		comboBoxEstablecimiento = new JComboBox<String>();
		comboBoxEstablecimiento.setBounds(12, 35, 130, 22);
		frame.getContentPane().add(comboBoxEstablecimiento);
//		this.establecimientos = EstablecimientoDAO.selectAllEstablecimietos();
		for (String nombreEstablecimiento : establecimientos.keySet()) {
			comboBoxEstablecimiento.addItem(nombreEstablecimiento);
		}
		comboBoxEstablecimiento.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (comboBoxEstablecimiento.getSelectedItem() != null) {
					comboBoxPeliculas.removeAllItems();
					Integer cuit = null;
					cuit = establecimientos.get(comboBoxEstablecimiento.getSelectedItem()).getCuit();

					if (cuit != null) {
						List<Integer> idsPeliculas = FuncionDAO.selectPeliculasEstablecimiento(cuit,
								(Date) new java.util.Date());
						List<Pelicula> peliculasList = new ArrayList<>();
						for (Integer id : idsPeliculas) {
							Pelicula pelicula = new Pelicula(null, null, null, 0, null, null, 0, null);
							pelicula.setId(id);
							peliculasList.addAll(PeliculaDAO.buscarPeliculas(pelicula));
						}
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
			}
		});
		peliculasLayout = new JPanel();
		peliculasLayout.setBounds(158, 13, 274, 44);
		frame.getContentPane().add(peliculasLayout);
		peliculasLayout.setLayout(null);

		lblPeliculas = new JLabel("Pelicula");
		lblPeliculas.setBounds(26, 0, 142, 16);
		peliculasLayout.add(lblPeliculas);

		comboBoxPeliculas = new JComboBox<String>();
		comboBoxPeliculas.setBounds(23, 22, 251, 22);
		peliculasLayout.add(comboBoxPeliculas);
		comboBoxPeliculas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboBoxPeliculas.getSelectedItem() != null) {
					comboBoxDia.removeAllItems();
					List<Funcion> funcionesObtenidas = FuncionDAO.selectFunciones(
							peliculas.get(comboBoxPeliculas.getSelectedItem()), (Date) new java.util.Date(),
							establecimientos.get(comboBoxEstablecimiento.getSelectedItem()).getCuit());
					for (Funcion funcion : funcionesObtenidas) {
						if (!funciones.containsKey(DateUtils.getDateSinHora(funcion.getFechaYHora()).toString())) {
							funciones.put(DateUtils.getDateSinHora(funcion.getFechaYHora()).toString(),
									new HashMap<String, Funcion>());
						}
						funciones.get(DateUtils.getDateSinHora(funcion.getFechaYHora()).toString())
								.put(funcion.getFechaYHora().toString(), funcion);
					}
					for (String dia : funciones.keySet()) {
						comboBoxDia.addItem(dia);
					}
					diaLayout.setVisible(true);
				} else {
					diaLayout.setVisible(false);

				}
				horarioLayout.setVisible(false);
				buttonLayout.setVisible(false);
			}
		});

		diaLayout = new JPanel();
		diaLayout.setBounds(0, 70, 142, 71);
		frame.getContentPane().add(diaLayout);
		diaLayout.setLayout(null);

		lblDia = new JLabel("Dia");
		lblDia.setBounds(12, 14, 57, 16);
		diaLayout.add(lblDia);

		comboBoxDia = new JComboBox<String>();
		comboBoxDia.setBounds(12, 34, 109, 22);
		diaLayout.add(comboBoxDia);

		horarioLayout = new JPanel();
		horarioLayout.setBounds(146, 70, 286, 71);
		frame.getContentPane().add(horarioLayout);
		horarioLayout.setLayout(null);

		lblHorario = new JLabel("Horario");
		lblHorario.setBounds(12, 13, 56, 16);
		horarioLayout.add(lblHorario);

		comboBoxHorario = new JComboBox<String>();
		comboBoxHorario.setBounds(12, 36, 123, 22);
		horarioLayout.add(comboBoxHorario);
		
		comboBoxHorario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboBoxHorario.getSelectedItem()!=null) {
//				lblPrecio.setText(funciones.get(key));
				} else {

				}
			}
		});
		
		buttonLayout = new JPanel();
		buttonLayout.setBounds(123, 187, 182, 85);
		frame.getContentPane().add(buttonLayout);
		buttonLayout.setLayout(null);

		btnSeleccionar = new JButton("Comprar");
		btnSeleccionar.setBounds(40, 48, 94, 25);
		buttonLayout.add(btnSeleccionar);

		lblPrecio = new JLabel("");
		lblPrecio.setBounds(62, 21, 63, 15);
		buttonLayout.add(lblPrecio);
		btnSeleccionar.setVisible(false);
		peliculasLayout.setVisible(false);
		diaLayout.setVisible(false);
		horarioLayout.setVisible(false);
	}
}
