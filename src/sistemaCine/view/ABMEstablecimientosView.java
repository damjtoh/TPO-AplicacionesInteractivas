package sistemaCine.view;

import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import sistemaCine.CineDAO.EstablecimientoDAO;
import sistemaCine.CineDAO.FuncionDAO;
import sistemaCine.CineDAO.PeliculaDAO;
import sistemaCine.cinesClases.Establecimiento;
import sistemaCine.cinesClases.Pelicula;
import sistemaCine.utils.DateUtils;

import javax.swing.JList;
import javax.swing.JButton;

public class ABMEstablecimientosView extends javax.swing.JFrame {

	private static ABMEstablecimientosView instancia;
	private JFrame frame;
	private JButton btnComprar;
	private JComboBox<String> comboBoxHorario;
	private JLabel lblHorario;
	private JPanel horarioLayout;
	private JComboBox<String> comboBoxDia;
	private JLabel lblDia;
	private JPanel funcionesLayout;
	private JComboBox<String> comboBoxPeliculas;
	private JLabel lblPeliculas;
	private Container peliculasLayout;
	private JComboBox<String> comboBoxEstablecimiento;
	private Component lblEstablecimiento;
	private List<Establecimiento> establecimientos;
	private List<Pelicula> peliculas;

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
		getContentPane().setLayout(null);

		lblEstablecimiento = new JLabel("Establecimiento");
		lblEstablecimiento.setBounds(12, 13, 109, 16);
		getContentPane().add(lblEstablecimiento);

		comboBoxEstablecimiento = new JComboBox();
		comboBoxEstablecimiento.setBounds(12, 35, 109, 22);
		getContentPane().add(comboBoxEstablecimiento);

		comboBoxEstablecimiento.addItemListener(new ItemListener() {


			@Override
			public void itemStateChanged(ItemEvent e) {
				Integer cuit = null;
				for (Establecimiento establecimiento : establecimientos) {
					if (establecimiento.getNombre().equals(e.getItem())) {
						cuit = establecimiento.getCuit();
						continue;
					}
				}
				if (cuit != null) {
					List<Integer> idsPeliculas = FuncionDAO.selectPeliculasEstablecimiento(cuit,
							(Date) new java.util.Date());
					for (Integer id : idsPeliculas) {
						Pelicula pelicula = new Pelicula(null, null, null, 0, null, null, 0, null);
						pelicula.setId(id);
						comboBoxPeliculas.addItem(pelicula.toString());
						peliculas = PeliculaDAO.buscarPeliculas(pelicula);
					}
				}

			}
		});
		peliculasLayout = new JPanel();
		peliculasLayout.setBounds(133, 13, 299, 44);
		getContentPane().add(peliculasLayout);
		peliculasLayout.setLayout(null);

		lblPeliculas = new JLabel("Pelicula");
		lblPeliculas.setBounds(0, 0, 168, 16);
		peliculasLayout.add(lblPeliculas);

		comboBoxPeliculas = new JComboBox();
		comboBoxPeliculas.setBounds(0, 22, 287, 22);
		peliculasLayout.add(comboBoxPeliculas);

		funcionesLayout = new JPanel();
		funcionesLayout.setBounds(0, 70, 142, 71);
		getContentPane().add(funcionesLayout);
		funcionesLayout.setLayout(null);

		lblDia = new JLabel("Dia");
		lblDia.setBounds(12, 14, 57, 16);
		funcionesLayout.add(lblDia);

		comboBoxDia = new JComboBox();
		comboBoxDia.setBounds(12, 34, 109, 22);
		funcionesLayout.add(comboBoxDia);

		horarioLayout = new JPanel();
		horarioLayout.setBounds(146, 70, 147, 71);
		getContentPane().add(horarioLayout);
		horarioLayout.setLayout(null);

		lblHorario = new JLabel("Horario");
		lblHorario.setBounds(12, 13, 56, 16);
		horarioLayout.add(lblHorario);

		comboBoxHorario = new JComboBox();
		comboBoxHorario.setBounds(12, 36, 123, 22);
		horarioLayout.add(comboBoxHorario);

		btnComprar = new JButton("Comprar");
		btnComprar.setBounds(166, 215, 97, 25);
		getContentPane().add(btnComprar);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
