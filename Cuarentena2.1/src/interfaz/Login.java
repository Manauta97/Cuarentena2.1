package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Individuos.Ciudadano;
import Individuos.Persona;
import IoDatos.IOdatos;

/*
Creacion de la interfaz Login
@autor Raúl
@version 0.1
*/
public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtdni;
	private ArrayList<Persona> vPersona;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		vPersona = IOdatos.cargarpersona();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 349, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 313, 33);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.addMouseListener(new BtnNewButtonMouseListener());
		btnNewButton.setBounds(45, 350, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Registrarse");
		btnNewButton_1.addMouseListener(new BtnNewButton_1MouseListener());
		btnNewButton_1.setBounds(172, 350, 107, 23);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(45, 170, 89, 23);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("DNI");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(45, 219, 89, 23);
		contentPane.add(lblNewLabel_2);

		textField = new JTextField();
		textField.setBounds(144, 172, 135, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		txtdni = new JTextField();
		txtdni.setBounds(144, 221, 135, 20);
		contentPane.add(txtdni);
		txtdni.setColumns(10);
	}

	/*
	 * El boton registrar te manda a la ventana de registrar y cierra la pestaña
	 * Login
	 */
	private class BtnNewButton_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			Registro regs = new Registro();
			regs.setVisible(true);
			dispose();
		}
	}

	private class BtnNewButtonMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			
			

			for (int i = 0; i < vPersona.size(); i++) {
				if (vPersona.get(i).getDni().contentEquals(txtdni.getText())) {

					if (vPersona.get(i) instanceof Ciudadano) {
						InterfazSalida itf = new InterfazSalida(txtdni.getText());
						itf.setVisible(true);
						dispose();
					} else {
						InterfazPolicia poli = new InterfazPolicia();
						poli.setVisible(true);
						dispose();
					}
				}
			}

		}
	}
}