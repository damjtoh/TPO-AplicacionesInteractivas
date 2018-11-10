package sistemaCine.view.admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sistemaCine.clases.AsientoFisico;
import sistemaCine.clases.AsientoVirtual;
import sistemaCine.clases.Funcion;
import sistemaCine.clases.Pelicula;
import sistemaCine.clases.Sala;
import sistemaCine.services.EntradaService;
import sistemaCine.services.FuncionServices;
import sistemaCine.services.PeliculaServices;
import sistemaCine.services.SalaServices;
import sistemaCine.utils.DateUtils;
import sistemaCine.utils.FilaColumna;
import sistemaCine.utils.GeneralFrame;
import sistemaCine.utils.IntegerField;
import sistemaCine.utils.IsTest;
import javax.swing.JTextField;
import java.awt.Font;

public class AltaModificacionFuncionView extends GeneralFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static AltaModificacionFuncionView instancia;
	private JFrame frame;

	private static Funcion funcion;
	private JComboBox<String> comboBoxPeliculas;
	private Map<String, Pelicula> peliculas;
	private JLabel lblSala;
	private JTextField compSala;
	private JPanel asientosPane;
	private JPanel screenPanel;
	private Map<Integer, Map<Integer, AsientoFisico>> asientos;
	private IntegerField compDia;
	private IntegerField compMes;
	private IntegerField compAnio;
	private JLabel lblFecha;
	private JButton btnCrear;
	private JButton btnEliminar;
	private static int cuit;
	private JButton btnCancelar;
	private IntegerField compValor;
	private IntegerField compHora;
	private IntegerField compMin;

	public static AltaModificacionFuncionView getInstancia(Funcion f, int c) {
		funcion = f;
		cuit = c;
		if (instancia == null) {
			instancia = new AltaModificacionFuncionView();
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
					AltaModificacionFuncionView window = new AltaModificacionFuncionView();
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
	public AltaModificacionFuncionView() {
		
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("deprecation")
	protected void initialize() {
		frame = new JFrame();
		super.frame = frame;
		frame.setBounds(100, 100, 1000, 800);
		frame.getContentPane().setLayout(null);

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				close();
			}
		});

		if (!IsTest.is) {
			try {
				this.peliculas = PeliculaServices.getAllPeliculasMap();
				funcion.setMapaDeAsientos(EntradaService.getMapaAsientosFuncion(funcion));
			} catch (SQLException e1) {

				e1.printStackTrace();
			}

		} else {
			myTest();
		}

		JLabel lblPelicula = new JLabel("Pelicula");
		lblPelicula.setBounds(12, 13, 78, 16);
		frame.getContentPane().add(lblPelicula);

		comboBoxPeliculas = new JComboBox<>();
		comboBoxPeliculas.setBounds(12, 39, 252, 22);
		frame.getContentPane().add(comboBoxPeliculas);

		lblSala = new JLabel("Sala");
		lblSala.setBounds(298, 13, 56, 16);
		frame.getContentPane().add(lblSala);

		screenPanel = new JPanel();
		screenPanel.setBackground(Color.DARK_GRAY);
		screenPanel.setBounds(12, 74, 958, 10);
		frame.getContentPane().add(screenPanel);

		compSala = new JTextField();
		compSala.setBounds(298, 39, 145, 22);
		frame.getContentPane().add(compSala);
		compSala.setColumns(10);
		comboBoxPeliculas.addItem(null);

		List<String> list = new ArrayList<>();
		list.addAll(peliculas.keySet());
		list.sort((a, b) -> a.compareToIgnoreCase(b));
		for (String key : list)
			comboBoxPeliculas.addItem(key);
		compSala.setText(funcion.getSala().getNombre());
		compSala.setEditable(false);

		asientosPane = new JPanel();
		asientosPane.setBounds(12, 116, 958, 524);
		frame.getContentPane().add(asientosPane);
		asientosPane.setBackground(Color.DARK_GRAY);

		compDia = new IntegerField(1, 31);
		compDia.setBounds(502, 39, 47, 22);
		frame.getContentPane().add(compDia);
		compDia.setColumns(10);

		compMes = new IntegerField(1, 12);
		compMes.setText("");
		compMes.setBounds(560, 39, 47, 22);
		frame.getContentPane().add(compMes);
		compMes.setColumns(10);

		compAnio = new IntegerField(0, 2100);
		compAnio.setBounds(619, 39, 71, 22);
		frame.getContentPane().add(compAnio);
		compAnio.setColumns(10);

		lblFecha = new JLabel("Fecha: dd/mm/yyyy");
		lblFecha.setBounds(502, 13, 188, 16);
		frame.getContentPane().add(lblFecha);

		btnCrear = new JButton("Crear");
		btnCrear.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCrear.setBounds(12, 686, 127, 54);
		frame.getContentPane().add(btnCrear);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(873, 715, 97, 25);
		frame.getContentPane().add(btnCancelar);

		compValor = new IntegerField();
		compValor.setBounds(854, 39, 116, 22);
		frame.getContentPane().add(compValor);
		compValor.setColumns(10);

		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(854, 13, 56, 16);
		frame.getContentPane().add(lblPrecio);

		compHora = new IntegerField(0, 23);
		compHora.setBounds(720, 39, 38, 22);
		frame.getContentPane().add(compHora);
		compHora.setColumns(10);

		compMin = new IntegerField(0, 59);
		compMin.setBounds(770, 39, 36, 22);
		frame.getContentPane().add(compMin);
		compMin.setColumns(10);

		JLabel lblHoraHhmm = new JLabel("Hora: hh:mm");
		lblHoraHhmm.setBounds(720, 13, 86, 16);
		frame.getContentPane().add(lblHoraHhmm);

		btnCancelar.addActionListener(e -> {
			close();
		});

		if (funcion.getPelicula() != null) {
			setModificar();
		} else {
			funcion.generateMapaAsientos();
			btnCrear.addActionListener(e -> {
				try {
					funcion.setPelicula(peliculas.get(comboBoxPeliculas.getSelectedItem().toString()));
					funcion.setFechaYHora(DateUtils.getDateConHora(compAnio.getInt(), compMes.getInt(),
							compDia.getInt(), compHora.getInt(), compMin.getInt()));
					funcion.setValor(compValor.getInt());
					FuncionServices.crearFuncion(funcion, cuit);
					btnCancelar.doClick();
				} catch (SQLException | IllegalArgumentException e1) {
					btnCrear.setBackground(Color.RED);
					e1.printStackTrace();
				}

			});
		}
		setMapaAsientosFuncion();

	}

	private void setModificar() {
		compDia.setText(Integer.toString(DateUtils.get(funcion.getFechaYHora(), Calendar.DAY_OF_MONTH)));
		compMes.setText(Integer.toString(DateUtils.get(funcion.getFechaYHora(), Calendar.MONTH)));
		compAnio.setText(Integer.toString(DateUtils.get(funcion.getFechaYHora(), Calendar.YEAR)));
		compHora.setText(Integer.toString(DateUtils.get(funcion.getFechaYHora(), Calendar.HOUR_OF_DAY)));
		compMin.setText(Integer.toString(DateUtils.get(funcion.getFechaYHora(), Calendar.MINUTE)));
		comboBoxPeliculas.setSelectedItem(funcion.getPelicula().toString());
		compValor.setText(Double.toString(funcion.getValor()));
		btnCrear.setText("Editar");
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(151, 715, 97, 25);
		frame.getContentPane().add(btnEliminar);
		btnEliminar.addActionListener(e -> {
			try {
				FuncionServices.eliminarFuncion(funcion);
				btnCancelar.doClick();
			} catch (SQLException e1) {
				btnEliminar.setBackground(Color.RED);
				e1.printStackTrace();
			}
		});
		btnCrear.addActionListener(e -> {
			try {
				funcion.setPelicula(peliculas.get(comboBoxPeliculas.getSelectedItem().toString()));
				funcion.setFechaYHora(DateUtils.getDateConHora(compAnio.getInt(), compMes.getInt() ,
						compDia.getInt(), compHora.getInt(), compMin.getInt()));
				funcion.setValor(compValor.getInt());
				FuncionServices.updateFuncion(funcion, cuit);
				btnCancelar.doClick();
			} catch (SQLException e1) {
				btnCrear.setBackground(Color.RED);
				e1.printStackTrace();
			}

		});
	}

	private void setMapaAsientosFuncion() {

		asientosPane.setLayout(new GridLayout(funcion.getSala().getCantFilas(), funcion.getSala().getCantColumnas()));
		asientos = new HashMap<Integer, Map<Integer, AsientoFisico>>();
		for (AsientoFisico asiento : funcion.getSala().getMapaDeAsientos().values()) {
			if (!asientos.containsKey(asiento.getNroFila())) {
				asientos.put(asiento.getNroFila(), new HashMap<Integer, AsientoFisico>());
			}
			asientos.get(asiento.getNroFila()).put(asiento.getNroColumna(), asiento);
		}
		for (int nroFila = 1; nroFila <= funcion.getSala().getCantFilas(); nroFila++) {
			for (int nroColumna = 1; nroColumna <= funcion.getSala().getCantColumnas(); nroColumna++) {
				AsientoFisico asiento = asientos.get(nroFila).get(nroColumna);
				JButton btnAsiento = new JButton(asientos.get(nroFila).get(nroColumna).toString());
				if (!asiento.isUsable()) {
					btnAsiento.setBackground(Color.RED);
				} else {
					if (funcion.getPelicula() != null) {
						if (funcion.getMapaDeAsientos().get(new FilaColumna(asiento.getFila(), asiento.getColumna()))
								.isOcupado()) {
							btnAsiento.setBackground(Color.RED);
						} else {
							btnAsiento.setBackground(Color.GREEN);
						}
					} else {
						btnAsiento.setBackground(Color.GREEN);
					}

				}
				asientosPane.add(btnAsiento);
			}
		}
	}

	private void myTest() {

		peliculas = new HashMap<>();
		for (int i = 1; i < 10; i++) {
			Pelicula pelicula = new Pelicula(Integer.toString(i), Integer.toString(i), Integer.toString(i), i,
					Integer.toString(i), i % 2 == 0, i, Integer.toString(i));
			peliculas.put(pelicula.toString(), pelicula);
			pelicula = new Pelicula(Integer.toString(i), Integer.toString(i), Integer.toString(i), i,
					Integer.toString(i), i % 2 != 0, i, Integer.toString(i));
			peliculas.put(pelicula.toString(), pelicula);
		}
		funcion = new Funcion(new Date(new java.util.Date().getTime()), null, new Sala("The first"), 3);
		Map<FilaColumna, AsientoVirtual> mapaDeAsientosVirtuales = new HashMap<FilaColumna, AsientoVirtual>();
		Map<FilaColumna, AsientoFisico> mapaDeAsientosFisicos = new HashMap<>();
		for (int nroFila = 1; nroFila < 6; nroFila++) {
			for (int nroColumna = 1; nroColumna < 5; nroColumna++) {
				AsientoFisico asinentoFisico = new AsientoFisico(Integer.toString(nroFila),
						Integer.toString(nroColumna), nroFila, nroColumna);
				AsientoVirtual asinentoVirtual = new AsientoVirtual(Integer.toString(nroColumna),
						Integer.toString(nroFila));
				mapaDeAsientosVirtuales.put(new FilaColumna(Integer.toString(nroFila), Integer.toString(nroColumna)),
						asinentoVirtual);
				mapaDeAsientosFisicos.put(new FilaColumna(Integer.toString(nroFila), Integer.toString(nroColumna)),
						asinentoFisico);
				asinentoFisico.setUsable(true);
				if (nroFila == nroColumna) {
					asinentoFisico.setUsable(false);
				}
				asinentoVirtual.setOcupado(nroFila == nroColumna + 2);
			}
		}
		funcion.getSala().setMapaDeAsientos(mapaDeAsientosFisicos);
		funcion.setMapaDeAsientos(mapaDeAsientosVirtuales);
	}

	@Override
	protected void deleteInstance() {
		instancia = null;
	}

}
