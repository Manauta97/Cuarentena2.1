package interfaz;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Individuos.Ciudadano;
import Individuos.Persona;
import Individuos.Policia;
import IoDatos.IOdatos;

/*
Creacion de la interfaz registro
@autor Raúl
@version 0.1
*/

public class Registro extends JFrame {

	private JPanel contentPane;
	private JTextField txtnombre;
	private JTextField txtdni;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnaceptar;
	private JRadioButton rdbtnCiudadano;
	private JRadioButton rdbtnPolicia;
	private ArrayList<Persona> vPersonas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro frame = new Registro();
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
	public Registro() {
		vPersonas = IOdatos.cargarpersona();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 365, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("REGISTRO");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 329, 27);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(67, 122, 67, 21);
		contentPane.add(lblNewLabel_1);

		txtnombre = new JTextField();
		txtnombre.setBounds(153, 123, 171, 20);
		contentPane.add(txtnombre);
		txtnombre.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("DNI");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(67, 169, 67, 21);
		contentPane.add(lblNewLabel_1_1);

		txtdni = new JTextField();
		txtdni.setBounds(153, 170, 171, 20);
		contentPane.add(txtdni);
		txtdni.setColumns(10);

		rdbtnPolicia = new JRadioButton("Policia");
		buttonGroup.add(rdbtnPolicia);
		rdbtnPolicia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnPolicia.setBounds(72, 225, 84, 23);
		contentPane.add(rdbtnPolicia);

		rdbtnCiudadano = new JRadioButton("Ciudadano");
		buttonGroup.add(rdbtnCiudadano);
		rdbtnCiudadano.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnCiudadano.setBounds(158, 225, 109, 23);
		contentPane.add(rdbtnCiudadano);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new BtnVolverMouseListener());
		btnVolver.setBounds(67, 351, 89, 23);
		contentPane.add(btnVolver);

		JButton btnaceptar = new JButton("Aceptar Registro");
		btnaceptar.addMouseListener(new BtnNewButtonMouseListener());
		btnaceptar.setBounds(177, 351, 147, 23);
		contentPane.add(btnaceptar);
	}

	/*
	 * Al clicar el boton volver cierra la ventana registro y vuelve a la ventana
	 * login.
	 */
	private class BtnVolverMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			Login log = new Login();
			log.setVisible(true);
			dispose();
		}
	}

	/*
	 * crea un objeto de persona o policia segun lo que hemos elegido.
	 */
	private class BtnNewButtonMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			

			if (!(txtnombre.getText().equalsIgnoreCase("") || txtnombre.getText() == null)
					&& !(txtdni.getText().equalsIgnoreCase("") || txtdni.getText() == null)) {
				if (rdbtnPolicia.isSelected()) {
					Policia poli = new Policia(txtnombre.getText(), txtdni.getText(), ("P-" + txtdni.getText()));
					vPersonas.add(poli);
					IOdatos.guardarPers(vPersonas);
				} else {
					Ciudadano ciu = new Ciudadano(txtnombre.getText(), txtdni.getText(), ("C-" + txtdni.getText()));
					vPersonas.add(ciu);
					IOdatos.guardarPers(vPersonas);
				}
				Login l = new Login();
				l.setVisible(true);
				dispose();
			}

		}
	}
}
