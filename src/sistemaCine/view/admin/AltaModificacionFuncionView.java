package sistemaCine.view.admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import sistemaCine.clases.AsinentoFisico;
import sistemaCine.clases.AsinentoVirtual;
import sistemaCine.clases.Funcion;
import sistemaCine.clases.Pelicula;
import sistemaCine.clases.Sala;
import sistemaCine.services.EntradaService;
import sistemaCine.services.FuncionServices;
import sistemaCine.services.PeliculaServices;
import sistemaCine.services.SalaServices;
import sistemaCine.utils.FilaColumna;
import sistemaCine.utils.IntegerField;
import sistemaCine.utils.IsTest;
import javax.swing.JTextField;
import java.awt.Font;

public class AltaModificacionFuncionView extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static AltaModificacionFuncionView instancia;
	private static Funcion funcion;
	private JFrame frame;
	private JComboBox<String> comboBoxPeliculas;
	private Map<String, Pelicula> peliculas;
	private JLabel lblSala;
	private JTextField compSala;
	private JPanel asientosPane;
	private JPanel screenPanel;
	private Map<Integer, Map<Integer, AsinentoFisico>> asientos;
	private IntegerField compDia;
	private IntegerField compMes;
	private IntegerField compAnio;
	private JLabel lblDdmmyyyy;
	private JButton btnCrear;
	private JButton btnEliminar;
	private static int cuit;
	private JButton btnCancelar;

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
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 800);
		frame.getContentPane().setLayout(null);

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				instancia = null;
				frame.dispose();
			}
		});

		if (!IsTest.is) {
			this.peliculas = PeliculaServices.getAllPeliculasMap();
			funcion.setMapaDeAsientos(EntradaService.getMapaAsientosFuncion(funcion));
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

		lblDdmmyyyy = new JLabel("dd/mm/yyyy");
		lblDdmmyyyy.setBounds(502, 13, 188, 16);
		frame.getContentPane().add(lblDdmmyyyy);

		btnCrear = new JButton("Crear");
		btnCrear.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCrear.setBounds(12, 686, 127, 54);
		frame.getContentPane().add(btnCrear);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(873, 715, 97, 25);
		frame.getContentPane().add(btnCancelar);
		
		btnCancelar.addActionListener(e -> {
			instancia = null;
			frame.dispose();
		});

		if (funcion.getPelicula() != null) {
			setModificar();
		} else {
			btnCrear.addActionListener(e -> {
				FuncionServices.crearFuncion(funcion, cuit);
			});
		}
//		setMapaAsientosFuncion();

	}

	private void setModificar() {
		compDia.setText(Integer.toString(funcion.getFechaYHora().getDay()));
		compMes.setText(Integer.toString(funcion.getFechaYHora().getMonth()));
		compAnio.setText(Integer.toString(funcion.getFechaYHora().getYear()));
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(151, 715, 97, 25);
		frame.getContentPane().add(btnEliminar);
		btnEliminar.addActionListener(e -> {
			FuncionServices.eliminarFuncion(funcion);
		});
		btnCrear.addActionListener(e -> {
			FuncionServices.updateFuncion(funcion, cuit);
		});
	}

	private void setMapaAsientosFuncion() {

		asientosPane.setLayout(new GridLayout(funcion.getSala().getCantFilas(), funcion.getSala().getCantColumnas()));
		asientos = new HashMap<Integer, Map<Integer, AsinentoFisico>>();
		for (AsinentoFisico asiento : funcion.getSala().getMapaDeAsientos().values()) {
			if (!asientos.containsKey(asiento.getNroFila())) {
				asientos.put(asiento.getNroFila(), new HashMap<Integer, AsinentoFisico>());
			}
			asientos.get(asiento.getNroFila()).put(asiento.getNroColumna(), asiento);
		}
		for (int nroFila = 1; nroFila <= funcion.getSala().getCantFilas(); nroFila++) {
			for (int nroColumna = 1; nroColumna <= funcion.getSala().getCantColumnas(); nroColumna++) {
				AsinentoFisico asiento = asientos.get(nroFila).get(nroColumna);
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
		Map<FilaColumna, AsinentoVirtual> mapaDeAsientosVirtuales = new HashMap<FilaColumna, AsinentoVirtual>();
		Map<FilaColumna, AsinentoFisico> mapaDeAsientosFisicos = new HashMap<>();
		for (int nroFila = 1; nroFila < 6; nroFila++) {
			for (int nroColumna = 1; nroColumna < 5; nroColumna++) {
				AsinentoFisico asinentoFisico = new AsinentoFisico(Integer.toString(nroFila),
						Integer.toString(nroColumna), nroFila, nroColumna);
				AsinentoVirtual asinentoVirtual = new AsinentoVirtual(Integer.toString(nroColumna),
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
}
