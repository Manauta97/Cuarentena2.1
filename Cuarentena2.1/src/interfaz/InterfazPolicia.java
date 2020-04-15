package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import IoDatos.IOdatos;
import motivos.Salidas;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.event.ChangeListener;

import Individuos.Persona;

import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class InterfazPolicia extends JFrame {

	private JPanel contentPane;
	private JLabel lblpolicia;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_hora;
	private JTextField textField_dni;
	private JTextField textField_hora;
	private JTextField textField_minutos;
	private JComboBox comboBox_personas;
	private JLabel lblNewLabel_personas;
	private JLabel lblNewLabel_dia;
	private JLabel lblNewLabel_info;
	private JTextArea textArea_infoCiudadano;
	private JButton btn_atras;
	private JButton btn_multar;
	private JToggleButton tglbtn_mostrartodo;
	private JButton btn_revisar;
	private DefaultComboBoxModel cbList;
	private JTextField textField_dia;
	private ArrayList<Salidas> vSalidas;
	private ArrayList<Persona> vPersonas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazPolicia frame = new InterfazPolicia();
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
	public InterfazPolicia() {
		vSalidas = IOdatos.cargarSalida();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 461, 713);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblpolicia = new JLabel("Policia");
		lblpolicia.setHorizontalAlignment(SwingConstants.CENTER);
		lblpolicia.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblpolicia.setBounds(146, 34, 118, 45);
		contentPane.add(lblpolicia);

		lblNewLabel = new JLabel("dni a comprobar");
		lblNewLabel.setBounds(60, 116, 107, 14);
		contentPane.add(lblNewLabel);

		lblNewLabel_hora = new JLabel("hora a comprobar");
		lblNewLabel_hora.setBounds(60, 157, 107, 14);
		contentPane.add(lblNewLabel_hora);

		textField_dni = new JTextField();
		textField_dni.addKeyListener(new TextField_dniKeyListener());
		textField_dni.setBounds(223, 113, 167, 20);
		contentPane.add(textField_dni);
		textField_dni.setColumns(10);

		textField_hora = new JTextField();
		textField_hora.addKeyListener(new TextField_horaKeyListener());
		textField_hora.setBounds(223, 154, 86, 20);
		contentPane.add(textField_hora);
		textField_hora.setColumns(10);

		textField_minutos = new JTextField();
		textField_minutos.addKeyListener(new TextField_minutosKeyListener());
		textField_minutos.setBounds(319, 154, 86, 20);
		contentPane.add(textField_minutos);
		textField_minutos.setColumns(10);

		comboBox_personas = new JComboBox();
		comboBox_personas.addMouseListener(new ComboBox_personasMouseListener());
		cbList = new DefaultComboBoxModel<>();
		comboBox_personas.setModel(cbList);
		comboBox_personas.setBounds(60, 267, 107, 22);
		contentPane.add(comboBox_personas);

		lblNewLabel_personas = new JLabel("personas en el momento seleccionado");
		lblNewLabel_personas.setBounds(189, 271, 193, 14);
		contentPane.add(lblNewLabel_personas);

		lblNewLabel_dia = new JLabel("dia");
		lblNewLabel_dia.setBounds(60, 206, 107, 14);
		contentPane.add(lblNewLabel_dia);

		lblNewLabel_info = new JLabel("informacion del ciudadano");
		lblNewLabel_info.setBounds(60, 349, 167, 14);
		contentPane.add(lblNewLabel_info);

		textArea_infoCiudadano = new JTextArea();
		textArea_infoCiudadano.setEditable(false);
		textArea_infoCiudadano.setBounds(43, 374, 362, 178);
		contentPane.add(textArea_infoCiudadano);

		btn_atras = new JButton("atras");
		btn_atras.addMouseListener(new Btn_atrasMouseListener());
		btn_atras.setBounds(52, 617, 89, 23);
		contentPane.add(btn_atras);

		btn_multar = new JButton("multar");
		btn_multar.addMouseListener(new Btn_multarMouseListener());
		btn_multar.setBounds(305, 617, 89, 23);
		contentPane.add(btn_multar);

		tglbtn_mostrartodo = new JToggleButton("mostrar todos");
		tglbtn_mostrartodo.addChangeListener(new Tglbtn_mostrartodoChangeListener());
		tglbtn_mostrartodo.setBounds(223, 288, 121, 23);
		contentPane.add(tglbtn_mostrartodo);

		btn_revisar = new JButton("revisar");
		btn_revisar.addMouseListener(new Btn_revisarMouseListener());
		btn_revisar.setBounds(175, 617, 89, 23);
		contentPane.add(btn_revisar);

		textField_dia = new JTextField();
		textField_dia.addKeyListener(new TextField_diaKeyListener());
		textField_dia.setBounds(223, 203, 183, 20);
		contentPane.add(textField_dia);
		textField_dia.setColumns(10);

		iniciarModelo();
	}

	private void iniciarModelo() {
		cbList.removeAllElements();
		for (Salidas s : vSalidas) {
			cbList.addElement(s);
		}

	}

	private void actualizarModelo(boolean todos) {
		cbList.removeAllElements();
		int accion = 0;
		String dni = "";
		String hora = "";
		String minutos = "";
		String dia = "";
		accion = 0;
		boolean b1 = false;
		boolean b2 = false;

		dni = textField_dni.getText();
		hora = textField_hora.getText();
		minutos = textField_minutos.getText();
		dia = textField_dia.getText();

		String patdni = "dddddddd[A-Z]";
		String pat2dni = "[A-Z]ddddddd[A-Z]";

		if (Pattern.matches(patdni, dni) || Pattern.matches(pat2dni, dni)) {
			accion += 1;
		}
		/////////////////////////////////////////////
		String pathor = "dd";

		int comprobar = 0;
		try {
			comprobar = Integer.parseInt(hora);
		} catch (Exception e2) {
			// JOptionPane.showMessageDialog(null, "introduce un numero", "error", 0);
		}

		if (Pattern.matches(pathor, hora) && (comprobar >= 0 && comprobar <= 23)) {
			b1 = true;
		}

		///////////////////////////
		String patmin = "dd";

		String minuto = textField_hora.getText();
		comprobar = 0;
		try {
			comprobar = Integer.parseInt(minuto);
		} catch (Exception e2) {
			// JOptionPane.showMessageDialog(null, "introduce un numero", "error", 0);
		}

		if (Pattern.matches(patmin, minuto) && (comprobar >= 0 && comprobar <= 23)) {
			b2 = true;
		}
		if (b1 && b2) {
			accion += 5;
		}
		////////////////////////////////
		String pat = "dd";

		comprobar = 0;
		try {
			comprobar = Integer.parseInt(dia);
		} catch (Exception e2) {
			// JOptionPane.showMessageDialog(null, "introduce un numero", "error", 0);
		}

		if (Pattern.matches(pat, dia) && (comprobar >= 0 && comprobar <= 23)) {
			accion += 10;
		}
		/////////////////////////////////

		try {

			if (todos) {
				switch (accion) {
				case 0:
					cbList.removeAllElements();
					for (Salidas s : vSalidas) {
						cbList.addElement(s);
					}

					break;
				case 1:
					for (Salidas s : vSalidas) {
						if (s.getdni().equalsIgnoreCase(dni)) {
							cbList.addElement(s);
						}
					} //
					break;
				case 5:
					for (Salidas s : vSalidas) {
						if (s.getHora().equalsIgnoreCase(hora) && s.getMinutos().equalsIgnoreCase(hora)) {
							cbList.addElement(s);
						}
					}
					break;
				case 10:
					for (Salidas s : vSalidas) {
						if (s.getFecha().equalsIgnoreCase(dia)) {
							cbList.addElement(s);
						}
					}
					break;
				case 11:
					for (Salidas s : vSalidas) {
						if ((s.getdni().equalsIgnoreCase(dni)) && (s.getFecha().equalsIgnoreCase(dia))) {
							cbList.addElement(s);
						}
					}
					break;
				case 15:
					for (Salidas s : vSalidas) {
						if ((s.getHora().equalsIgnoreCase(hora) && s.getMinutos().equalsIgnoreCase(hora))
								&& (s.getFecha().equalsIgnoreCase(dia))) {
							cbList.addElement(s);
						}
					}
					break;
				case 6:
					for (Salidas s : vSalidas) {
						if ((s.getdni().equalsIgnoreCase(dni))
								&& (s.getHora().equalsIgnoreCase(hora) && s.getMinutos().equalsIgnoreCase(hora))) {
							cbList.addElement(s);
						}
					}
					break;
				}
			} else {
				switch (accion) {
				case 0:
					cbList.removeAllElements();
					for (Salidas s : vSalidas) {
						cbList.addElement(s);
					}
					break;
				case 1:
					for (Salidas s : vSalidas) {
						if (s.getdni().equalsIgnoreCase(dni) && (s.isRevisado() != true)) {
							cbList.addElement(s.getdni());
						}
					} //
					break;
				case 5:
					for (Salidas s : vSalidas) {
						if (s.getHora().equalsIgnoreCase(hora) && s.getMinutos().equalsIgnoreCase(hora)
								&& (s.isRevisado() != true)) {
							cbList.addElement(s);
						}
					}
					break;
				case 10:
					for (Salidas s : vSalidas) {
						if (s.getFecha().equalsIgnoreCase(dia) && (s.isRevisado() != true)) {
							cbList.addElement(s);
						}
					}
					break;
				case 11:
					for (Salidas s : vSalidas) {
						if ((s.getdni().equalsIgnoreCase(dni)) && (s.getFecha().equalsIgnoreCase(dia))
								&& (s.isRevisado() != true)) {
							cbList.addElement(s);
						}
					}
					break;
				case 15:
					for (Salidas s : vSalidas) {
						if ((s.getHora().equalsIgnoreCase(hora) && s.getMinutos().equalsIgnoreCase(hora))
								&& (s.getFecha().equalsIgnoreCase(dia)) && (s.isRevisado() != true)) {
							cbList.addElement(s);
						}
					}
					break;
				case 6:
					for (Salidas s : vSalidas) {
						if ((s.getdni().equalsIgnoreCase(dni))
								&& (s.getHora().equalsIgnoreCase(hora) && s.getMinutos().equalsIgnoreCase(hora))
								&& (s.isRevisado() != true)) {
							cbList.addElement(s);
						}
					}
					break;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("todo se fue a la verga");
		}

	}

	private class TextField_dniKeyListener extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {

			actualizarModelo(true);
		}
	}

	private class TextField_horaKeyListener extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {

			actualizarModelo(true);
		}
	}

	private class TextField_minutosKeyListener extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {

			actualizarModelo(true);
		}
	}

//	private class ComboBox_personasMouseListener extends MouseAdapter {
//		@Override
//		public void mouseClicked(MouseEvent e) {
//			String s = (String) cbList.getSelectedItem();
//			for (Salidas sal : vSalidas) {
//				if (sal.getdni().equalsIgnoreCase(s)) {
//					textArea_infoCiudadano.setText(sal.toString());
//					break;
//				}
//			}
//
//		}
//	}

	private class Btn_atrasMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			Login l = new Login();
			l.setVisible(true);
			dispose();
		}
	}

	private class Btn_revisarMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			Salidas sal = (Salidas) cbList.getSelectedItem();

			sal.setRevisado(true);

		}

	}

	private class Btn_multarMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			Salidas sal = (Salidas) cbList.getSelectedItem();

			sal.setRevisado(true);
			sal.setMultado(true);

		}
	}

	private class TextField_diaKeyListener extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {

			actualizarModelo(true);
		}
	}

	private class Tglbtn_mostrartodoChangeListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			if (!tglbtn_mostrartodo.isSelected()) {
				actualizarModelo(false);
			}

		}
	}

//	private class ComboBox_personasActionListener implements ActionListener {
//		@Override
//		public void actionPerformed(ActionEvent arg0) {
//			Salidas sal = (Salidas) cbList.getSelectedItem();
//
//			textArea_infoCiudadano.setText(sal.toString());
//
//		}
//
//	}
	private class ComboBox_personasMouseListener extends MouseAdapter {
		@Override
		public void mouseEntered(MouseEvent arg0) {
			Salidas sal = (Salidas) cbList.getSelectedItem();

			textArea_infoCiudadano.setText(sal.toString());
		}
	}
}
