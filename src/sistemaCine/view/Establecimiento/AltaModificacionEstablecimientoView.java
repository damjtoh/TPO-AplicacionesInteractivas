package sistemaCine.view.Establecimiento;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import sistemaCine.CineDAO.EstablecimientoDAO;
import sistemaCine.cinesClases.Establecimiento;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Properties;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class AltaModificacionEstablecimientoView extends javax.swing.JFrame{
	static AltaModificacionEstablecimientoView instancia;
	private JFrame frame;
	private JTextField compNombre;
	private JTextField compDireccion;
	private JTextField compCapacidad;
	private JTextField compCuit;
	private JButton btnCrear;

	/**
	 * Launch the application.
	 */
	public static AltaModificacionEstablecimientoView getInstancia(Integer cuit) {
		if (instancia == null) {
			instancia = new AltaModificacionEstablecimientoView(cuit);
		}
		return instancia;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaModificacionEstablecimientoView window = new AltaModificacionEstablecimientoView(null);
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
	public AltaModificacionEstablecimientoView(Integer cuit) {
		initialize(cuit);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer cuit) {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				frame.dispose();
			}
		});
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		compCuit = new JTextField("");
		compCuit.setBounds(22, 43, 131, 20);
		frame.getContentPane().add(compCuit);
		compCuit.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				compCuit.setText(compCuit.getText().replaceAll("[^0-9]", ""));
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		compNombre = new JTextField();
		compNombre.setBounds(22, 99, 131, 20);
		frame.getContentPane().add(compNombre);
		compNombre.setColumns(10);

		compDireccion = new JTextField();
		compDireccion.setBounds(22, 155, 131, 20);
		frame.getContentPane().add(compDireccion);
		compDireccion.setColumns(10);

		compCapacidad = new JTextField();
		compCapacidad.setBounds(22, 211, 131, 20);
		frame.getContentPane().add(compCapacidad);
		compCapacidad.setColumns(10);

		JLabel lblCapacidad = new JLabel("Capacidad");
		lblCapacidad.setBounds(22, 186, 131, 14);
		frame.getContentPane().add(lblCapacidad);

		JLabel lblDireccion = new JLabel("Direcci\u00F3n");
		lblDireccion.setBounds(23, 130, 130, 14);
		frame.getContentPane().add(lblDireccion);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(22, 74, 131, 14);
		frame.getContentPane().add(lblNombre);

		JLabel lblCuit = new JLabel("Cuit");
		lblCuit.setBounds(22, 18, 131, 14);
		frame.getContentPane().add(lblCuit);

		btnCrear = new JButton("Crear");
		btnCrear.setBounds(225, 182, 118, 23);
		frame.getContentPane().add(btnCrear);
		if (cuit == null) {
			btnCrear.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					EstablecimientoDAO.insert(new Establecimiento(Integer.parseInt(compCuit.getText()),
							compNombre.getText(), compDireccion.getText(), Integer.parseInt(compCapacidad.getText())));
					;
				}
			});
		}else {
			setModificar(cuit);
		}
		compCapacidad.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				compCapacidad.setText(compCapacidad.getText().replaceAll("[^0-9]", ""));
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	public void setModificar(int cuit) {
		Establecimiento establecimiento = EstablecimientoDAO.selectEstablecimieto(cuit);
		compCuit.setText(Integer.toString(establecimiento.getCuit()));
		compNombre.setText(establecimiento.getNombre());
		compDireccion.setText(establecimiento.getDomicilio());
		compCapacidad.setText(Integer.toString(establecimiento.getCapacidadTotal()));
		btnCrear.setText("Modificar");
		btnCrear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EstablecimientoDAO.update(new Establecimiento(Integer.parseInt(compCuit.getText()),
						compNombre.getText(), compDireccion.getText(), Integer.parseInt(compCapacidad.getText())));
				;
			}
		});
		compCuit.setEditable(false);
	}
}
