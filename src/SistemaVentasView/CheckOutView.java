package SistemaVentasView;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import SistemaVentas.ConfirmarVenta;
import SistemaVentas.TarjetaCreditoXPortal;
import SistemaVentas.TarjetaDebitoXPortal;
import sistemaCine.clases.Entrada;
import sistemaCine.clases.Funcion;
import sistemaCine.clases.Pelicula;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class CheckOutView extends JFrame {

	private JPanel contentPane;

	public CheckOutView(List<Entrada> entradas, Funcion funcion) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txtPelicula = new JTextArea();
		txtPelicula.setBounds(10, 36, 217, 22);
		contentPane.add(txtPelicula);
		txtPelicula.setText(funcion.getPelicula().getNombre());
		txtPelicula.setEditable(false);
		
		JLabel lblTipoDePago = new JLabel("Tipo de pago");
		lblTipoDePago.setBounds(10, 116, 109, 14);
		contentPane.add(lblTipoDePago);
		
		JLabel lblFechaYHora = new JLabel("Fecha y hora");
		lblFechaYHora.setBounds(10, 69, 109, 14);
		contentPane.add(lblFechaYHora);
		
		JComboBox listaTiposDePago = new JComboBox();
		listaTiposDePago.setBounds(10, 141, 217, 20);
		contentPane.add(listaTiposDePago);
		listaTiposDePago.addItem(new TarjetaCreditoXPortal());
		listaTiposDePago.addItem(new TarjetaDebitoXPortal());
		
		
		JLabel lblCombo = new JLabel("Combo");
		lblCombo.setBounds(10, 205, 109, 14);
		contentPane.add(lblCombo);
		
		JComboBox listCombos = new JComboBox();
		listCombos.setBounds(10, 230, 217, 20);
		contentPane.add(listCombos);
		
		
		JTextArea txtNumeroTarjeta = new JTextArea();
		txtNumeroTarjeta.setText("Ingresar numero de tarjeta");
		txtNumeroTarjeta.setBounds(10, 172, 217, 22);
		contentPane.add(txtNumeroTarjeta);
		txtNumeroTarjeta.setEditable(true);
		txtNumeroTarjeta.setText(null);
		
		JLabel lblPelicula = new JLabel("Pelicula");
		lblPelicula.setBounds(10, 11, 46, 14);
		contentPane.add(lblPelicula);
		
		JTextArea txtFechaYHora = new JTextArea();
		txtFechaYHora.setBounds(10, 94, 217, 22);
		contentPane.add(txtFechaYHora);
		txtFechaYHora.setText(funcion.getFechaYHora().toString());
		txtFechaYHora.setEditable(false);
		
		
		JLabel lblAsientos = new JLabel("Asientos");
		lblAsientos.setBounds(235, 11, 46, 14);
		contentPane.add(lblAsientos);
		
		JTextArea txtAsientos = new JTextArea();
		txtAsientos.setBounds(237, 36, 187, 22);
		contentPane.add(txtAsientos);
		for (Entrada entrada : entradas) {
			txtAsientos.setText(entrada.getAsiento().getColumna()+entrada.getAsiento().getFila());
		}
		txtAsientos.setEditable(false);
		
		JLabel lblSala = new JLabel("Sala");
		lblSala.setBounds(235, 69, 46, 14);
		contentPane.add(lblSala);
		
		JTextArea txtSala = new JTextArea();
		txtSala.setBounds(237, 94, 187, 22);
		contentPane.add(txtSala);
		txtSala.setEditable(false);
		txtSala.setText(funcion.getSala().getNombre());
		
		JLabel lblImporteAPagar = new JLabel("Importe a pagar");
		lblImporteAPagar.setBounds(237, 144, 187, 14);
		contentPane.add(lblImporteAPagar);
		
		JTextArea txtImporte = new JTextArea();
		txtImporte.setBounds(237, 172, 187, 22);
		contentPane.add(txtImporte);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(237, 227, 187, 23);
		contentPane.add(btnConfirmar);
		
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfirmarVenta cVenta = new ConfirmarVenta();
				if (txtNumeroTarjeta.getText() != null)
				cVenta.CrearVenta(entradas, funcion.getFechaYHora().toString(), listCombos.getSelectedItem(), txtNumeroTarjeta.getText(), importe)
			}
		});
	}
}
