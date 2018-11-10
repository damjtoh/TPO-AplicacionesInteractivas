package sistemaCine.view.cliente;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Views.CheckOutView;
import sistemaCine.clases.AsientoFisico;
import sistemaCine.clases.AsientoVirtual;
import sistemaCine.clases.Entrada;
import sistemaCine.clases.Establecimiento;
import sistemaCine.clases.Funcion;
import sistemaCine.clases.Sala;
import sistemaCine.services.EntradaService;
import sistemaCine.services.SalaServices;
import sistemaCine.utils.FilaColumna;
import sistemaCine.utils.GeneralFrame;
import sistemaCine.utils.IsTest;

public class SeleccionarAsientosView extends GeneralFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static SeleccionarAsientosView instancia;
	private JFrame frame;
	private JPanel screenPanel;
	private JButton btnComprar;
	private JPanel asientosPane;
	private JLabel lblSeleccioneSusAsientos;
	private static Funcion funcion;
	private Map<Integer, Map<Integer, AsientoFisico>> asientos;
	private Map<FilaColumna, AsientoFisico> asientosSeleccionados;
	private static Establecimiento establecimiento;
	private static boolean esPorPortal;

	public static SeleccionarAsientosView getInstance(Funcion f, Establecimiento e, boolean esPorPortal2) {
		esPorPortal = esPorPortal2;
		funcion = f;
		establecimiento = e;
		if (instancia == null) {
			instancia = new SeleccionarAsientosView();
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
					SeleccionarAsientosView window = new SeleccionarAsientosView();
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
	public SeleccionarAsientosView() {
		super.frame = frame;
		if (IsTest.is) {
			testMy();
		}
		initialize();
		this.frame.setVisible(true);
	}

	private void testMy() {
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
//		SeleccionarAsientosView.getInstance(funcion).setLocationRelativeTo(null);
//		SeleccionarAsientosView.getInstance(funcion).setVisible(true);

	}

	@Override
	protected void deleteInstance() {
		instancia = null;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	protected void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 800);

		frame.getContentPane().setLayout(null);

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				close();
			}
		});

		asientosPane = new JPanel();
		asientosPane.setBounds(12, 116, 958, 524);
		frame.getContentPane().add(asientosPane);
		btnComprar = new JButton("Comprar");
		btnComprar.setBackground(Color.GRAY);
		btnComprar.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnComprar.setBounds(394, 670, 209, 70);
		frame.getContentPane().add(btnComprar);
		btnComprar.addActionListener(e -> {
			if (!asientosSeleccionados.isEmpty()) {
				// TODO
				List<Entrada> entradas = new ArrayList<>();
				for (AsientoFisico asiento : asientosSeleccionados.values()) {
					entradas.add(new Entrada(new AsientoVirtual(asiento.getColumna(), asiento.getFila()), funcion));
				}
				new CheckOutView(entradas, funcion,esPorPortal).setVisible(true);;
			}
		});

		screenPanel = new JPanel();
		screenPanel.setBackground(Color.DARK_GRAY);
		screenPanel.setBounds(12, 93, 958, 10);
		frame.getContentPane().add(screenPanel);

		lblSeleccioneSusAsientos = new JLabel("Seleccione sus asientos");
		lblSeleccioneSusAsientos.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccioneSusAsientos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSeleccioneSusAsientos.setBounds(290, 24, 412, 46);
		frame.getContentPane().add(lblSeleccioneSusAsientos);
		if (!IsTest.is) {
			try {
				funcion.setMapaDeAsientos(EntradaService.getMapaAsientosFuncion(funcion));
				funcion.getSala()
						.setMapaDeAsientos(SalaServices.getAsientosSala(funcion.getSala(), establecimiento.getCuit()));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		setMapaAsientos();

	}

	private void setMapaAsientos() {

		asientosPane.setLayout(new GridLayout(funcion.getSala().getCantFilas(), funcion.getSala().getCantColumnas()));
		asientos = new HashMap<Integer, Map<Integer, AsientoFisico>>();
		for (AsientoFisico asiento : funcion.getSala().getMapaDeAsientos().values()) {
			if (!asientos.containsKey(asiento.getNroFila())) {
				asientos.put(asiento.getNroFila(), new HashMap<Integer, AsientoFisico>());
			}
			asientos.get(asiento.getNroFila()).put(asiento.getNroColumna(), asiento);
		}
		asientosSeleccionados = new HashMap<>();
		for (int nroFila = 1; nroFila <= funcion.getSala().getCantFilas(); nroFila++) {
			for (int nroColumna = 1; nroColumna <= funcion.getSala().getCantColumnas(); nroColumna++) {
				AsientoFisico asiento = asientos.get(nroFila).get(nroColumna);
				JButton btnAsiento = new JButton(asientos.get(nroFila).get(nroColumna).toString());
				if (!asiento.isUsable() || funcion.getMapaDeAsientos()
						.get(new FilaColumna(asiento.getFila(), asiento.getColumna())).isOcupado()) {
					btnAsiento.setBackground(Color.RED);
				} else {
					btnAsiento.setBackground(Color.GREEN);
					btnAsiento.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							seleccionarAsiento(btnAsiento, asiento);
							btnAsiento.removeActionListener(this);
						}
					});
				}
				asientosPane.add(btnAsiento);
			}
		}
	}

	private void seleccionarAsiento(JButton btnAsiento, AsientoFisico asiento) {
		btnAsiento.setBackground(Color.BLUE);
		asientosSeleccionados.put(new FilaColumna(asiento.getFila(), asiento.getColumna()), asiento);
		btnAsiento.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				deseleccionarAsiento(btnAsiento, asiento);
				btnAsiento.removeActionListener(this);
			}
		});
	}

	private void deseleccionarAsiento(JButton btnAsiento, AsientoFisico asiento) {
		btnAsiento.setBackground(Color.GREEN);
		asientosSeleccionados.remove(new FilaColumna(asiento.getFila(), asiento.getColumna()));
		btnAsiento.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				seleccionarAsiento(btnAsiento, asiento);
				btnAsiento.removeActionListener(this);
			}
		});
	}
}
