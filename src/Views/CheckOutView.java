package Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import SistemaFacturacion.Combo;
import SistemaFacturacion.Descuento;
import SistemaFacturacion.Promo2x1;
import SistemaFacturacion.xPorcentajePrecioVenta;
import SistemaVentas.ConfirmarVenta;
import SistemaVentas.ITipoDePago;
import SistemaVentas.TarjetaCreditoXPortal;
import SistemaVentas.TarjetaDebitoXPortal;
import SistemaVentas.Venta;
import sistemaCine.clases.Entrada;
import sistemaCine.clases.Establecimiento;
import sistemaCine.clases.Funcion;
import javax.swing.JTextField;

public class CheckOutView extends JFrame {

	private JPanel contentPane;
	private JTextField txtEstablecimiento;

	public CheckOutView(List<Entrada> entradas, Funcion funcion, boolean esPorPortal, Establecimiento establecimiento) {
		setBounds(100, 100, 450, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextArea txtPelicula = new JTextArea();
		txtPelicula.setBounds(16, 117, 217, 22);
		contentPane.add(txtPelicula);
		txtPelicula.setText(funcion.getPelicula().getNombre());
		txtPelicula.setEditable(false);

		JLabel lblTipoDePago = new JLabel("Tipo de pago");
		lblTipoDePago.setBounds(16, 197, 109, 14);
		contentPane.add(lblTipoDePago);

		JLabel lblFechaYHora = new JLabel("Fecha y hora");
		lblFechaYHora.setBounds(16, 150, 109, 14);
		contentPane.add(lblFechaYHora);

		JComboBox<ITipoDePago> listaTiposDePago = new JComboBox();
		listaTiposDePago.setBounds(16, 222, 217, 20);
		contentPane.add(listaTiposDePago);
		listaTiposDePago.addItem(new TarjetaCreditoXPortal());
		listaTiposDePago.addItem(new TarjetaDebitoXPortal());

		JLabel lblCombo = new JLabel("Combo");
		lblCombo.setBounds(16, 286, 109, 14);
		contentPane.add(lblCombo);

		JComboBox listCombos = new JComboBox();
		listCombos.setBounds(16, 311, 217, 20);
		contentPane.add(listCombos);
		listCombos.addItem(new Combo());
		listCombos.addItem(new xPorcentajePrecioVenta());
		listCombos.addItem(new Promo2x1());
		listCombos.addItem(new Object());

		JTextArea txtNumeroTarjeta = new JTextArea();
		txtNumeroTarjeta.setText("Ingresar numero de tarjeta");
		txtNumeroTarjeta.setBounds(16, 263, 217, 22);
		contentPane.add(txtNumeroTarjeta);
		txtNumeroTarjeta.setEditable(true);
		txtNumeroTarjeta.setText(null);

		JLabel lblPelicula = new JLabel("Pelicula");
		lblPelicula.setBounds(16, 92, 46, 14);
		contentPane.add(lblPelicula);

		JTextArea txtFechaYHora = new JTextArea();
		txtFechaYHora.setBounds(16, 175, 217, 22);
		contentPane.add(txtFechaYHora);
		txtFechaYHora.setText(funcion.getFechaYHora().toString());
		txtFechaYHora.setEditable(false);

		JLabel lblAsientos = new JLabel("Asientos");
		lblAsientos.setBounds(241, 92, 46, 14);
		contentPane.add(lblAsientos);

		JTextArea txtAsientos = new JTextArea();
		txtAsientos.setBounds(243, 117, 187, 22);
		contentPane.add(txtAsientos);
		for (Entrada entrada : entradas) {
			txtAsientos.setText(entrada.getAsiento().getColumna() + entrada.getAsiento().getFila());
		}
		txtAsientos.setEditable(false);

		JLabel lblSala = new JLabel("Sala");
		lblSala.setBounds(241, 150, 46, 14);
		contentPane.add(lblSala);

		JTextArea txtSala = new JTextArea();
		txtSala.setBounds(243, 175, 187, 22);
		contentPane.add(txtSala);
		txtSala.setEditable(false);
		txtSala.setText(funcion.getSala().getNombre());

		JLabel lblImporteAPagar = new JLabel("Importe a pagar");
		lblImporteAPagar.setBounds(245, 236, 187, 14);
		contentPane.add(lblImporteAPagar);

		JTextArea txtImporte = new JTextArea();
		txtImporte.setBounds(243, 263, 187, 22);
		contentPane.add(txtImporte);
//		txtImporte.setText((Descuento)listaTiposDePago.getSelectedItem().(funcion.getValor() * entradas.size())); ///NICO

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(243, 308, 187, 23);
		contentPane.add(btnConfirmar);

		JLabel lblNumeroDeTarjeta = new JLabel("Numero de Tarjeta");
		lblNumeroDeTarjeta.setBounds(16, 245, 142, 16);
		contentPane.add(lblNumeroDeTarjeta);
		
		JLabel lblEstablecimiento = new JLabel(establecimiento.getNombre());
		lblEstablecimiento.setBounds(6, 26, 109, 16);
		contentPane.add(lblEstablecimiento);
		
		txtEstablecimiento = new JTextField();
		txtEstablecimiento.setText("Establecimiento");
		txtEstablecimiento.setBounds(6, 54, 130, 26);
		contentPane.add(txtEstablecimiento);
		txtEstablecimiento.setColumns(10);

		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfirmarVenta cVenta = new ConfirmarVenta();
				if (txtNumeroTarjeta.getText() != null) {
					Venta venta = cVenta.CrearVenta(entradas, funcion.getFechaYHora().toLocalDate(),
							(ITipoDePago)listaTiposDePago.getSelectedItem(),
							Long.parseLong(txtNumeroTarjeta.getText()), funcion.getValor() * entradas.size(),
							esPorPortal);
					if (esPorPortal) {
						JOptionPane.showMessageDialog(null, "Su Venta es:" + venta.getId());
					}
					dispose();
				}
			}
		});
	}
}
