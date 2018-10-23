package sistemaCine.utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class IntegerField extends JTextField {

	public IntegerField(String string) {
		super(string);
		IntegerField field = this;
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				field.setText(field.getText().replaceAll("[^0-9]", ""));
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				field.setText(field.getText().replaceAll("[^0-9]", ""));
			}
		});
	}
	public IntegerField() {
		super();
		IntegerField field = this;
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				field.setText(field.getText().replaceAll("[^0-9]", ""));
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				field.setText(field.getText().replaceAll("[^0-9]", ""));
			}
		});
	}
}
