package Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import SistemaVentas.Venta;
import SistemaVentas.VentasDAO;
import sistemaCine.utils.IntegerField;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ImpresionDeEntradasView {

	private JFrame frame;
	private IntegerField textField;

	public ImpresionDeEntradasView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblIngreseElId = new JLabel("Ingrese el Id de la venta");
		lblIngreseElId.setBounds(28, 46, 198, 15);
		frame.getContentPane().add(lblIngreseElId);

		textField = new IntegerField();
		textField.setBounds(28, 85, 114, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblSusEntradasSe = new JLabel("Sus entradas se estan imprimiendo");
		lblSusEntradasSe.setBounds(28, 131, 286, 15);
		frame.getContentPane().add(lblSusEntradasSe);
		lblSusEntradasSe.setVisible(false);

		JLabel lblNoSeEncontro = new JLabel("No se encontro la venta");
		lblNoSeEncontro.setBounds(28, 171, 261, 15);
		frame.getContentPane().add(lblNoSeEncontro);

		JButton btnBuscar = new JButton("Buscar e imprimir");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Venta venta = VentasDAO.getById(textField.getInt());
				if (venta != null) {
					if (venta.isEsPorPortal()) {
						venta.setEsPorPortal(false);
						VentasDAO.update(venta);
						lblSusEntradasSe.setVisible(true);
						lblNoSeEncontro.setVisible(false);
					} else {
						lblNoSeEncontro.setVisible(true);
						lblSusEntradasSe.setVisible(false);
					}
				} else {
					lblNoSeEncontro.setVisible(true);
					lblSusEntradasSe.setVisible(false);
				}
			}
		});
		btnBuscar.setBounds(197, 82, 210, 25);
		frame.getContentPane().add(btnBuscar);
		lblNoSeEncontro.setVisible(false);
	}
}
