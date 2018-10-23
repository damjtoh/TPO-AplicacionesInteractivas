package sistemaCine.view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import sistemaCine.CineDAO.EntradasDAO;
import sistemaCine.CineDAO.SalaDAO;
import sistemaCine.cinesClases.AsinentoFisico;
import sistemaCine.cinesClases.AsinentoVirtual;
import sistemaCine.cinesClases.Entrada;
import sistemaCine.cinesClases.Establecimiento;
import sistemaCine.cinesClases.Funcion;
import sistemaCine.cinesClases.Sala;
import sistemaCine.utils.FilaColumna;

import javax.swing.JScrollPane;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class SeleccionarAsientosView extends javax.swing.JFrame{

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
	private Map<Integer, Map<Integer, AsinentoFisico>> asientos;
	private Map<FilaColumna ,AsinentoFisico> asientosSeleccionados;

	public static SeleccionarAsientosView getInstance(Funcion f) {
		funcion = f;
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
			funcion = new Funcion(new Date(new java.util.Date().getTime()) , null, new Sala("The first"), 3);
			Map<FilaColumna, AsinentoVirtual> mapaDeAsientosVirtuales = new HashMap<FilaColumna, AsinentoVirtual>();
			Map<FilaColumna, AsinentoFisico> mapaDeAsientosFisicos = new HashMap<>();
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 10; j++) {
					AsinentoFisico asinentoFisico = new AsinentoFisico(Integer.toString(i), Integer.toString(j), i, j);
					AsinentoVirtual asinentoVirtual = new AsinentoVirtual(Integer.toString(j), Integer.toString(i));
					mapaDeAsientosVirtuales.put(new FilaColumna(Integer.toString(i), Integer.toString(j)), asinentoVirtual);
					mapaDeAsientosFisicos.put(new FilaColumna(Integer.toString(i), Integer.toString(j)), asinentoFisico);
					asinentoFisico.setUsable(true);
					if (i==j) {
						asinentoFisico.setUsable(false);
					}
					asinentoVirtual.setOcupado(i==j+2);
				}
			}
			funcion.getSala().setMapaDeAsientos(mapaDeAsientosFisicos);
			funcion.setMapaDeAsientos(mapaDeAsientosVirtuales);
//			SeleccionarAsientosView.getInstance(funcion).setLocationRelativeTo(null);
//			SeleccionarAsientosView.getInstance(funcion).setVisible(true);
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		asientosPane = new JPanel();
		asientosPane.setBounds(12, 116, 958, 524);
		frame.getContentPane().add(asientosPane);
		asientosPane.setLayout(new GridLayout(15, 10, 0, 0));
		btnComprar = new JButton("Comprar");
		btnComprar.setBackground(Color.GRAY);
		btnComprar.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnComprar.setBounds(394, 670, 209, 70);
		frame.getContentPane().add(btnComprar);

		screenPanel = new JPanel();
		screenPanel.setBackground(Color.DARK_GRAY);
		screenPanel.setBounds(12, 93, 958, 10);
		frame.getContentPane().add(screenPanel);

		lblSeleccioneSusAsientos = new JLabel("Seleccione sus asientos");
		lblSeleccioneSusAsientos.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccioneSusAsientos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSeleccioneSusAsientos.setBounds(290, 24, 412, 46);
		frame.getContentPane().add(lblSeleccioneSusAsientos);
		setMapaAsientos();

	}

	private void setMapaAsientos() {
//		funcion.setMapaDeAsientos(EntradasDAO.selectMapaEntradas(funcion));
//		funcion.getSala().setMapaDeAsientos(SalaDAO.selectAsientosSala(funcion.getSala(), establecimiento.getCuit()));
//		asientosPane.setLayout(new GridLayout(funcion.getSala().getCantFilas(), funcion.getSala().getCantColumnas()));
		asientos = new HashMap<Integer, Map<Integer,AsinentoFisico>>();
		for (AsinentoFisico asiento : funcion.getSala().getMapaDeAsientos().values()) {
			if (!asientos.containsKey(asiento.getNroFila())) {
				asientos.put(asiento.getNroFila(), new HashMap<Integer, AsinentoFisico>());
			}
			asientos.get(asiento.getNroFila()).put(asiento.getNroColumna(), asiento);
		}
		asientosSeleccionados = new HashMap<>();
		for (int i = 0; i <= funcion.getSala().getCantFilas(); i++) {
			for (int j = 0; j <= funcion.getSala().getCantColumnas(); j++) {
				AsinentoFisico asiento = asientos.get(i).get(j);
				JButton btnAsiento = new JButton(asientos.get(i).get(j).toString());
				if (!asiento.isUsable()||funcion.getMapaDeAsientos().get(new FilaColumna(asiento.getFila(), asiento.getColumna())).isOcupado()) {
					btnAsiento.setBackground(Color.RED);
				}else {
					btnAsiento.setBackground(Color.GREEN);
					btnAsiento.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							seleccionarAsiento(btnAsiento,asiento);							
							btnAsiento.removeActionListener(this);
						}
					});
				}
				asientosPane.add(btnAsiento);
			}
		}
	}

	private void seleccionarAsiento(JButton btnAsiento, AsinentoFisico asiento) {
		btnAsiento.setBackground(Color.BLUE);
		asientosSeleccionados.put(new FilaColumna(asiento.getFila(), asiento.getColumna()), asiento);
		btnAsiento.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				deseleccionarAsiento(btnAsiento,asiento);
				btnAsiento.removeActionListener(this);
			}
		});
		}

	private void deseleccionarAsiento(JButton btnAsiento,AsinentoFisico asiento) {
		btnAsiento.setBackground(Color.GREEN);
		asientosSeleccionados.remove(new FilaColumna(asiento.getFila(), asiento.getColumna()));
		btnAsiento.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				seleccionarAsiento(btnAsiento,asiento);							
				btnAsiento.removeActionListener(this);
			}
		});
	}
}
