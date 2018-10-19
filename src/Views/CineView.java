package Views;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class CineView extends JFrame {

	private JPanel contentPane;
	private JPanel usuariosCrearView = new UsuariosCrearView();
	private JPanel usuariosListarView = new UsuariosListarView();
    CardLayout cardLayout = new CardLayout();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CineView frame = new CineView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CineView() {
		setTitle("Sistema Cines");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 355);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnUsuarios = new JMenu("Usuarios");
		menuBar.add(mnUsuarios);
		
		JMenuItem mntmListar = new JMenuItem("Listar");
		mntmListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) 
			{
				System.out.println("Just clicked listar");
		        System.out.println(evt.toString());
		        cardLayout.show(contentPane, "usuariosListarView");
			}
		});
		mnUsuarios.add(mntmListar);
		
		JMenuItem mntmCrear = new JMenuItem("Crear");
		mntmCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) 
			{
				System.out.println("Just clicked crear");
		        System.out.println(evt.toString());
		        cardLayout.show(contentPane, "usuariosCrearView");
			}
		});
		mnUsuarios.add(mntmCrear);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(cardLayout);
		contentPane.add(usuariosCrearView, "usuariosCrearView");
		contentPane.add(usuariosListarView, "usuariosListarView");
	}
	
	//private getFormData() {}
	

}
