package sistemaCine.utils;

import java.awt.HeadlessException;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public abstract class GeneralFrame extends javax.swing.JFrame{
	/**
	 * 
	 */
	protected GeneralFrame oldGF;
	private static final long serialVersionUID = 1L;
	protected JFrame frame;
	protected abstract void deleteInstance();
	protected void close() {
		oldGF.reload();
		deleteInstance();
		this.dispose();
	}
	

	public void addUpdate(WindowListener windowListener){
		frame.addWindowListener(windowListener);
	}
	public void reload() {
		initialize();
	}
	protected abstract void initialize();
}
