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
		if (oldGF!=null) {
			oldGF.reload();
		}
		deleteInstance();
		frame.dispose();
	}
	
	public void setOldGF(GeneralFrame oldGF) {
		this.oldGF = oldGF;
	}
	public void addUpdate(WindowListener windowListener){
		frame.addWindowListener(windowListener);
	}
	public void reload() {
		frame.dispose();
		initialize();
		frame.setVisible(false);
		frame.setVisible(true);
	}
	protected abstract void initialize();
}
