package sistemaCine.utils;

import java.awt.HeadlessException;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public abstract class GeneralFrame extends javax.swing.JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JFrame frame;


	public void addUpdate(WindowListener windowListener){
		frame.addWindowListener(windowListener);
	}
	public void reload() {
		initialize();
	}
	protected abstract void initialize();
}
