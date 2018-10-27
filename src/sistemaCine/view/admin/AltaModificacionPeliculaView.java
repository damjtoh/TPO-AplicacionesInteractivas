package sistemaCine.view.admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import sistemaCine.clases.Pelicula;
import sistemaCine.services.PeliculaServices;
import sistemaCine.utils.GeneralFrame;
import sistemaCine.utils.IntegerField;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Font;

public class AltaModificacionPeliculaView extends GeneralFrame {

	private JFrame frame;

	private static Pelicula pelicula;
	private static AltaModificacionPeliculaView instancia;
	private JTextField compNombre;
	private JTextField compDirector;
	private JTextField compGenero;
	private IntegerField compDuracion;
	private JTextField compIdioma;
	private IntegerField compCalificacion;
	private JTextField compObservacion;

	private JButton btnCrear;

	private JCheckBox chckbxSubtitulada;
	private JButton btnCancelar;

	public static AltaModificacionPeliculaView getInstancia(Pelicula p) {
		pelicula = p;
		if (instancia == null) {
			instancia = new AltaModificacionPeliculaView();
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
					AltaModificacionPeliculaView window = new AltaModificacionPeliculaView();
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
	public AltaModificacionPeliculaView() {
		super.frame = frame;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	protected void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 470, 300);
		

		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				btnCancelar.doClick();
			}
		});
		JLabel lblNombre = new JLabel("Titulo");
		lblNombre.setBounds(12, 13, 56, 16);
		frame.getContentPane().add(lblNombre);

		compNombre = new JTextField();
		compNombre.setBounds(12, 42, 116, 22);
		frame.getContentPane().add(compNombre);
		compNombre.setColumns(10);

		JLabel lblDirector = new JLabel("Director");
		lblDirector.setBounds(12, 77, 56, 16);
		frame.getContentPane().add(lblDirector);

		compDirector = new JTextField();
		compDirector.setBounds(12, 106, 116, 22);
		frame.getContentPane().add(compDirector);
		compDirector.setColumns(10);

		JLabel lblGenero = new JLabel("Genero");
		lblGenero.setBounds(12, 141, 56, 16);
		frame.getContentPane().add(lblGenero);

		compGenero = new JTextField();
		compGenero.setBounds(12, 170, 116, 22);
		frame.getContentPane().add(compGenero);
		compGenero.setColumns(10);

		JLabel lblDuracion = new JLabel("Duraci\u00F3n");
		lblDuracion.setBounds(157, 13, 56, 16);
		frame.getContentPane().add(lblDuracion);

		compDuracion = new IntegerField();
		compDuracion.setBounds(157, 42, 116, 22);
		frame.getContentPane().add(compDuracion);
		compDuracion.setColumns(10);

		JLabel lblIdioma = new JLabel("Idioma");
		lblIdioma.setBounds(157, 77, 56, 16);
		frame.getContentPane().add(lblIdioma);

		compIdioma = new JTextField();
		compIdioma.setBounds(157, 106, 116, 22);
		frame.getContentPane().add(compIdioma);
		compIdioma.setColumns(10);

		chckbxSubtitulada = new JCheckBox("Subtitulada");
		chckbxSubtitulada.setBounds(281, 105, 113, 25);
		frame.getContentPane().add(chckbxSubtitulada);

		JLabel lblCalificacion = new JLabel("Calificaci\u00F3n");
		lblCalificacion.setBounds(157, 141, 86, 16);
		frame.getContentPane().add(lblCalificacion);

		compCalificacion = new IntegerField();
		compCalificacion.setBounds(157, 170, 116, 22);
		frame.getContentPane().add(compCalificacion);
		compCalificacion.setColumns(10);

		JLabel lblObservacion = new JLabel("Observaci\u00F3n");
		lblObservacion.setBounds(12, 197, 92, 16);
		frame.getContentPane().add(lblObservacion);

		compObservacion = new JTextField();
		compObservacion.setBounds(12, 218, 116, 22);
		frame.getContentPane().add(compObservacion);
		compObservacion.setColumns(10);

		btnCrear = new JButton("Crear");
		btnCrear.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		btnCrear.setBounds(157, 205, 129, 37);
		frame.getContentPane().add(btnCrear);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(323, 217, 97, 25);
		frame.getContentPane().add(btnCancelar);
		btnCancelar.addActionListener(e -> {
			instancia=null;
			frame.dispose();
		});
		if (pelicula != null) {
			setEditarPelicula();

		} else {
			btnCrear.addActionListener(e -> {
				try {
					PeliculaServices.crearPelicula(new Pelicula(compNombre.getText(), compDirector.getText(),
							compGenero.getText(), compDuracion.getInt(), compIdioma.getText(),
							chckbxSubtitulada.isSelected(), compCalificacion.getInt(), compObservacion.getText()));
					frame.dispose();
				} catch (Exception ex) {
					btnCrear.setBackground(Color.RED);
					ex.printStackTrace();
				}
			});
		}

	}

	private void setEditarPelicula() {
		btnCrear.setText("Modificar");
		btnCrear.addActionListener(e -> {
			Pelicula p= new Pelicula(compNombre.getText(), compDirector.getText(),
					compGenero.getText(), compDuracion.getInt(), compIdioma.getText(),
					chckbxSubtitulada.isSelected(), compCalificacion.getInt(), compObservacion.getText()); 
			p.setId(pelicula.getId());
			PeliculaServices.updatePelicula(p);
		});
		compNombre.setText(pelicula.getNombre());
		compDirector.setText(pelicula.getDirector());
		compDuracion.setText(Integer.toString(pelicula.getDuracion()));
		compCalificacion.setText(Double.toString(pelicula.getClaificacion()));
		compObservacion.setText(pelicula.getObservaciones());
		compIdioma.setText(pelicula.getIdioma());
		chckbxSubtitulada.setSelected(pelicula.isSubtitulos());
		compGenero.setText(pelicula.getGenero());
	}
}
