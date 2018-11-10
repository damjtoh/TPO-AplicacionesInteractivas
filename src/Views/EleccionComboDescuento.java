package Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EleccionComboDescuento {

	private JFrame frame;

	public EleccionComboDescuento() {
		initialize();
	}


	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnEditarCombos = new JButton("Editar Combos");
		btnEditarCombos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaComboView nextScreen = new AltaComboView();
				nextScreen.setVisible(true);
			}
		});
		btnEditarCombos.setBounds(10, 81, 179, 85);
		frame.getContentPane().add(btnEditarCombos);
		
		JButton btnEditarDescuentos = new JButton("Editar Descuentos");
		btnEditarDescuentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DescuentosView nextScreen = new DescuentosView();
				nextScreen.setVisible(true);
			}
		});
		btnEditarDescuentos.setBounds(261, 81, 163, 85);
		frame.getContentPane().add(btnEditarDescuentos);		
	}

}
