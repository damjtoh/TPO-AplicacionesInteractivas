package Views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textUsuario;
	private JTextField textContrasena;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LoginView dialog = new LoginView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LoginView() {
		setTitle("Ingresar");
		setBounds(100, 100, 450, 139);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblUsuario = new JLabel("Usuario");
			lblUsuario.setBounds(85, 11, 48, 16);
			contentPanel.add(lblUsuario);
		}
		{
			textUsuario = new JTextField();
			textUsuario.setBounds(170, 6, 130, 26);
			contentPanel.add(textUsuario);
			textUsuario.setColumns(10);
		}
		{
			JLabel lblContrasena = new JLabel("Contraseña");
			lblContrasena.setBounds(85, 47, 71, 16);
			contentPanel.add(lblContrasena);
		}
		{
			textContrasena = new JPasswordField();
			textContrasena.setColumns(10);
			textContrasena.setBounds(170, 42, 130, 26);
			contentPanel.add(textContrasena);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Ingresar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println("Intento ingresar: "+textUsuario.getText());
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
