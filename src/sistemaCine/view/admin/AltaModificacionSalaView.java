package sistemaCine.view.admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sistemaCine.clases.AsinentoFisico;
import sistemaCine.clases.Funcion;
import sistemaCine.clases.Sala;
import sistemaCine.services.FuncionServices;
import sistemaCine.services.SalaServices;
import sistemaCine.utils.FilaColumna;
import sistemaCine.utils.IntegerField;
import sistemaCine.utils.IsTest;
import javax.swing.JComboBox;

public class AltaModificacionSalaView extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static AltaModificacionSalaView instancia;
	private static Sala sala;
	private JFrame frame;
	private JPanel asientosPane;
	private HashMap<Integer, Map<Integer, AsinentoFisico>> asientos;
	private JButton btnCrear;
	private static Integer cuit;
	private JButton btnCancelar;
	private JButton btnEliminar;
	private JTextField compNombreSala;
	private IntegerField compFilas;
	private IntegerField compColumnas;
	private JPanel panelScreen;
	private JButton btnNuevosAsientos;
	private static String oldSalaName;
	private JButton btnCreareditar;
	private JComboBox<String> comboBoxFunciones;
	private JPanel panelFunciones;
	private Map<String, Funcion> funcionesMap;

	public static AltaModificacionSalaView getInstancia(String nombreSala, Integer cuit) {
		oldSalaName = nombreSala;
		sala = new Sala(nombreSala);
		AltaModificacionSalaView.cuit = cuit;
		if (instancia == null) {
			instancia = new AltaModificacionSalaView();
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
					AltaModificacionSalaView window = new AltaModificacionSalaView();
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
	public AltaModificacionSalaView() {
		if (IsTest.is) {
			testMy();
		}
		initialize();
		this.frame.setVisible(true);
	}

	private void testMy() {
		sala = new Sala(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				btnCancelar.doClick();
			}
		});
		asientosPane = new JPanel();
		asientosPane.setBackground(Color.LIGHT_GRAY);
		asientosPane.setBounds(22, 116, 952, 524);
		frame.getContentPane().add(asientosPane);
		asientosPane.setLayout(new GridLayout(1, 0, 0, 0));
		asientosPane.setVisible(false);
		btnCrear = new JButton("Crear");
		btnCrear.setFont(new Font("Dialog", Font.BOLD, 20));
		btnCrear.setBounds(12, 688, 173, 61);
		frame.getContentPane().add(btnCrear);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(871, 763, 117, 25);
		frame.getContentPane().add(btnCancelar);
		btnCancelar.addActionListener(e -> {
			this.dispose();
			frame.dispose();
		});

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(12, 761, 173, 25);
		frame.getContentPane().add(btnEliminar);

		compNombreSala = new JTextField();
		compNombreSala.setBounds(74, 42, 193, 40);
		frame.getContentPane().add(compNombreSala);
		compNombreSala.setColumns(10);

		panelScreen = new JPanel();
		panelScreen.setBackground(Color.DARK_GRAY);
		panelScreen.setBounds(12, 94, 976, 10);
		frame.getContentPane().add(panelScreen);

		compFilas = new IntegerField();
		compFilas.setBounds(689, 42, 114, 19);
		frame.getContentPane().add(compFilas);
		compFilas.setColumns(10);

		compColumnas = new IntegerField();
		compColumnas.setBounds(689, 63, 114, 19);
		frame.getContentPane().add(compColumnas);
		compColumnas.setColumns(10);

		JLabel lblFilas = new JLabel("Filas:");
		lblFilas.setBounds(601, 42, 70, 15);
		frame.getContentPane().add(lblFilas);

		JLabel lblColumnas = new JLabel("Columnas:");
		lblColumnas.setBounds(601, 67, 82, 15);
		frame.getContentPane().add(lblColumnas);

		btnNuevosAsientos = new JButton("Crear asientos");
		btnNuevosAsientos.setBounds(822, 42, 140, 40);
		frame.getContentPane().add(btnNuevosAsientos);
		
		panelFunciones = new JPanel();
		panelFunciones.setBounds(197, 713, 290, 87);
		frame.getContentPane().add(panelFunciones);
		panelFunciones.setLayout(null);
		panelFunciones.setVisible(false);
		btnCreareditar = new JButton("Crear/Editar");
		btnCreareditar.setBounds(0, 50, 169, 25);
		panelFunciones.add(btnCreareditar);
		
		btnCreareditar.addActionListener(e -> {
			if (comboBoxFunciones.getSelectedItem()!=null) {
				AltaModificacionFuncionView.getInststancia(funcionesMap.get(comboBoxFunciones.getSelectedItem().toString()));
		}else {
			AltaModificacionFuncionView.getInststancia(null);

		}
		});
		comboBoxFunciones = new JComboBox<String>();
		comboBoxFunciones.setBounds(0, 0, 173, 24);
		panelFunciones.add(comboBoxFunciones);
		if (sala.getNombre() != null) {
			btnCrear.setText("Editar");
			sala.setMapaDeAsientos(SalaServices.getAsientosSala(sala, cuit));
			setMapaAsientos();
			compNombreSala.setText(sala.getNombre());
			btnCrear.addActionListener(e -> {
				try {
					SalaServices.crearSala(sala, cuit);
					btnCancelar.doClick();
				} catch (Exception e1) {
					btnCrear.setBackground(Color.RED);
					e1.printStackTrace();
				}
			});
			List<Funcion> funciones = FuncionServices.getFuncionesSala(new Sala(oldSalaName), cuit);
			funcionesMap = new HashMap<>();
			for (Funcion funcion : funciones) {
				funcionesMap.put(funcion.toString(),funcion);
				comboBoxFunciones.addItem(funcion.toString());
			}
		} else {
			btnCrear.addActionListener(e -> {
				try {
					SalaServices.updateSala(sala, cuit, oldSalaName);
					btnCancelar.doClick();
				} catch (Exception e1) {
					btnCrear.setBackground(Color.RED);
					e1.printStackTrace();
				}
			});
		}
		btnNuevosAsientos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!compColumnas.getText().isEmpty() && !compFilas.getText().isEmpty()) {
					setMapaAsientosDefault();
					setMapaAsientos();
				}
			}

		});
		btnEliminar.addActionListener(e -> {
			SalaServices.eliminarSala(new Sala(oldSalaName), cuit);
		});

	}

	private void setMapaAsientosDefault() {
		Map<FilaColumna, AsinentoFisico> mapaDeAsientosFisicos = new HashMap<>();
		for (int nroFila = 1; nroFila <= compFilas.getInt(); nroFila++) {
			for (int nroColumna = 1; nroColumna <= compColumnas.getInt(); nroColumna++) {
				AsinentoFisico asinentoFisico = new AsinentoFisico(Integer.toString(nroFila),
						Integer.toString(nroColumna), nroFila, nroColumna);
				mapaDeAsientosFisicos.put(new FilaColumna(Integer.toString(nroFila), Integer.toString(nroColumna)),
						asinentoFisico);
			}
		}
		sala.setMapaDeAsientos(mapaDeAsientosFisicos);
	}

	private void setMapaAsientos() {
		asientosPane.removeAll();
		asientosPane.setLayout(new GridLayout(sala.getCantFilas() + 1, sala.getCantColumnas() + 1));
		asientos = new HashMap<Integer, Map<Integer, AsinentoFisico>>();
		for (AsinentoFisico asiento : sala.getMapaDeAsientos().values()) {
			if (!asientos.containsKey(asiento.getNroFila())) {
				asientos.put(asiento.getNroFila(), new HashMap<Integer, AsinentoFisico>());
			}
			asientos.get(asiento.getNroFila()).put(asiento.getNroColumna(), asiento);
		}
		List<JTextField> columnaCero = new ArrayList<>();
		List<JTextField> filaCero = new ArrayList<>();
		for (int nroFila = 0; nroFila <= sala.getCantFilas(); nroFila++) {
			columnaCero.add(new JTextField());
			asientosPane.add(columnaCero.get(nroFila));
			for (int nroColumna = 0; nroColumna <= sala.getCantColumnas(); nroColumna++) {
				if (nroFila != 0 && nroColumna != 0) {
					AsinentoFisico asiento = asientos.get(nroFila).get(nroColumna);
					JButton btnAsiento = new JButton(asientos.get(nroFila).get(nroColumna).toString());
					final int nc = nroColumna;
					final int nf = nroFila;
					btnAsiento.setBackground((asiento.isUsable()) ? Color.GREEN : Color.RED);
					btnAsiento.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							asiento.setUsable(!asiento.isUsable());
							btnAsiento.setBackground((asiento.isUsable()) ? Color.GREEN : Color.RED);
						}
					});
					asientosPane.add(btnAsiento);
					filaCero.get(nroColumna).addKeyListener(new KeyListener() {
						
						@Override
						public void keyTyped(KeyEvent e) {
											
						}
						
						@Override
						public void keyReleased(KeyEvent e) {
							if (!filaCero.get(nc).getText().isEmpty()) {
								asientos.get(nf).get(nc).setColumna(filaCero.get(nc).getText());
								btnAsiento.setText(asientos.get(nf).get(nc).toString());

							}								
						}
						
						@Override
						public void keyPressed(KeyEvent e) {
							
						}
					});
					columnaCero.get(nroFila).addKeyListener(new KeyListener() {
						
						@Override
						public void keyTyped(KeyEvent e) {
					
						}
						
						@Override
						public void keyReleased(KeyEvent e) {
							if (!columnaCero.get(nf).getText().isEmpty()) {
								asientos.get(nf).get(nc).setFila(columnaCero.get(nf).getText());
								btnAsiento.setText(asientos.get(nf).get(nc).toString());
							}									
						}
						
						@Override
						public void keyPressed(KeyEvent e) {
							
						}
					});
				} else {
					if (nroFila == 0 && nroColumna != 0) {
						filaCero.add(new JTextField());
						asientosPane.add(filaCero.get(nroColumna));
					} else {
						filaCero.add(new JTextField());
					}
				}
			}
		}
		asientosPane.setVisible(false);
		asientosPane.setVisible(true);
	}
}