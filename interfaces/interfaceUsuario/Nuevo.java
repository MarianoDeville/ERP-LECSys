package interfaceUsuario;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class Nuevo extends VentanaModelo {

	private static final long serialVersionUID = 1L;
	private JPanel nuevo;
	public JTextField txtNombre;
	public JTextField txtApellido;
	public JTextField txtDNI;
	public JTextField txtDia;
	public JTextField txtMes;
	public JTextField txtAño;
	public JTextField txtDireccion;
	public JTextField txtEmail;
	public JTextField txtTelefono;
	public JTextField txt1;
	public JTextField txt2;
	public JLabel lblcomboBox1;
	public JLabel lblcomboBox2;
	public JLabel lblTxt1;
	public JLabel lblTxt2;
	public JLabel lblMsgError;
	public JComboBox<String> comboBox1;
	public JComboBox<String> comboBox2;
	public JButton btnGuardar;
	public JButton btnVolver;
	
	public Nuevo(String nombreVentana) {
		
		super(nombreVentana);
		nuevo = new JPanel();
		setContentPane(nuevo);
		SpringLayout contenedor = new SpringLayout();
		setMinimumSize(new Dimension(400, 500));
		setBounds(10, 10, 400, 500);
		nuevo.setLayout(contenedor);
		
		JLabel lblNombre = new JLabel("Nombre:");
		contenedor.putConstraint(SpringLayout.NORTH, lblNombre, 30, SpringLayout.NORTH, nuevo);
		contenedor.putConstraint(SpringLayout.WEST, lblNombre, 15, SpringLayout.WEST, nuevo);
		contenedor.putConstraint(SpringLayout.EAST, lblNombre, 70, SpringLayout.WEST, lblNombre);
		nuevo.add(lblNombre);
		
		txtNombre = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txtNombre, -2, SpringLayout.NORTH, lblNombre);
		contenedor.putConstraint(SpringLayout.WEST, txtNombre, 5, SpringLayout.EAST, lblNombre);
		contenedor.putConstraint(SpringLayout.EAST, txtNombre, 150, SpringLayout.WEST, txtNombre);
		nuevo.add(txtNombre);
		configurarJTextField(txtNombre, 20);
		
		JLabel lblApellido = new JLabel("Apellido:");
		contenedor.putConstraint(SpringLayout.NORTH, lblApellido, 15, SpringLayout.SOUTH, lblNombre);
		contenedor.putConstraint(SpringLayout.WEST, lblApellido, 15, SpringLayout.WEST, nuevo);
		contenedor.putConstraint(SpringLayout.EAST, lblApellido, 70, SpringLayout.WEST, lblApellido);
		nuevo.add(lblApellido);
		
		txtApellido = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txtApellido, -2, SpringLayout.NORTH, lblApellido);
		contenedor.putConstraint(SpringLayout.WEST, txtApellido, 5, SpringLayout.EAST, lblApellido);
		contenedor.putConstraint(SpringLayout.EAST, txtApellido, 150, SpringLayout.WEST, txtApellido);
		nuevo.add(txtApellido);
		configurarJTextField(txtApellido, 20);
		
		JLabel lblDNI = new JLabel("DNI:");
		contenedor.putConstraint(SpringLayout.NORTH, lblDNI, 15, SpringLayout.SOUTH, lblApellido);
		contenedor.putConstraint(SpringLayout.WEST, lblDNI, 15, SpringLayout.WEST, nuevo);
		contenedor.putConstraint(SpringLayout.EAST, lblDNI, 70, SpringLayout.WEST, lblDNI);
		nuevo.add(lblDNI);
		
		txtDNI = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txtDNI, -2, SpringLayout.NORTH, lblDNI);
		contenedor.putConstraint(SpringLayout.WEST, txtDNI, 5, SpringLayout.EAST, lblDNI);
		contenedor.putConstraint(SpringLayout.EAST, txtDNI, 150, SpringLayout.WEST, txtDNI);
		nuevo.add(txtDNI);
		configurarJTextField(txtDNI, 10);
		
		JLabel lblNacimiento = new JLabel("Nacimiento:");
		contenedor.putConstraint(SpringLayout.NORTH, lblNacimiento, 15, SpringLayout.SOUTH, lblDNI);
		contenedor.putConstraint(SpringLayout.WEST, lblNacimiento, 15, SpringLayout.WEST, nuevo);
		contenedor.putConstraint(SpringLayout.EAST, lblNacimiento, 70, SpringLayout.WEST, lblNacimiento);
		nuevo.add(lblNacimiento);
		
		txtDia = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txtDia, -2, SpringLayout.NORTH, lblNacimiento);
		contenedor.putConstraint(SpringLayout.WEST, txtDia, 5, SpringLayout.EAST, lblNacimiento);
		contenedor.putConstraint(SpringLayout.EAST, txtDia, 25, SpringLayout.WEST, txtDia);
		nuevo.add(txtDia);
		configurarJTextField(txtDia, 2);
		
		JLabel separador1 = new JLabel("/");
		contenedor.putConstraint(SpringLayout.NORTH, separador1, 2, SpringLayout.NORTH, txtDia);
		contenedor.putConstraint(SpringLayout.WEST, separador1, 5, SpringLayout.EAST, txtDia);
		nuevo.add(separador1);

		txtMes = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txtMes, -2, SpringLayout.NORTH, separador1);
		contenedor.putConstraint(SpringLayout.WEST, txtMes, 5, SpringLayout.EAST, separador1);
		contenedor.putConstraint(SpringLayout.EAST, txtMes, 25, SpringLayout.WEST, txtMes);
		nuevo.add(txtMes);
		configurarJTextField(txtMes, 2);
		
		JLabel separador2 = new JLabel("/");
		contenedor.putConstraint(SpringLayout.NORTH, separador2, 2, SpringLayout.NORTH, txtMes);
		contenedor.putConstraint(SpringLayout.WEST, separador2, 5, SpringLayout.EAST, txtMes);
		nuevo.add(separador2);
		
		txtAño = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txtAño, -2, SpringLayout.NORTH, separador2);
		contenedor.putConstraint(SpringLayout.WEST, txtAño, 5, SpringLayout.EAST, separador2);
		contenedor.putConstraint(SpringLayout.EAST, txtAño, 40, SpringLayout.WEST, txtAño);
		nuevo.add(txtAño);
		configurarJTextField(txtAño, 4);
		
		JLabel formato = new JLabel("DD / MM / AAAA");
		contenedor.putConstraint(SpringLayout.NORTH, formato, 2, SpringLayout.NORTH, txtAño);
		contenedor.putConstraint(SpringLayout.WEST, formato, 5, SpringLayout.EAST, txtAño);
		nuevo.add(formato);
		
		JLabel lblDireccion = new JLabel("Dirección:");
		contenedor.putConstraint(SpringLayout.NORTH, lblDireccion, 15, SpringLayout.SOUTH, lblNacimiento);
		contenedor.putConstraint(SpringLayout.WEST, lblDireccion, 15, SpringLayout.WEST, nuevo);
		contenedor.putConstraint(SpringLayout.EAST, lblDireccion, 70, SpringLayout.WEST, lblDireccion);
		nuevo.add(lblDireccion);
		
		txtDireccion = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txtDireccion, -2, SpringLayout.NORTH, lblDireccion);
		contenedor.putConstraint(SpringLayout.WEST, txtDireccion, 5, SpringLayout.EAST, lblDireccion);
		contenedor.putConstraint(SpringLayout.EAST, txtDireccion, 285, SpringLayout.WEST, txtDireccion);
		nuevo.add(txtDireccion);
		configurarJTextField(txtDireccion, 45);
		
		JLabel lblEmail = new JLabel("E-mail:");
		contenedor.putConstraint(SpringLayout.NORTH, lblEmail, 15, SpringLayout.SOUTH, lblDireccion);
		contenedor.putConstraint(SpringLayout.WEST, lblEmail, 15, SpringLayout.WEST, nuevo);
		contenedor.putConstraint(SpringLayout.EAST, lblEmail, 70, SpringLayout.WEST, lblEmail);
		nuevo.add(lblEmail);
		
		txtEmail = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txtEmail, -2, SpringLayout.NORTH, lblEmail);
		contenedor.putConstraint(SpringLayout.WEST, txtEmail, 5, SpringLayout.EAST, lblEmail);
		contenedor.putConstraint(SpringLayout.EAST, txtEmail, 285, SpringLayout.WEST, txtEmail);
		nuevo.add(txtEmail);
		configurarJTextField(txtEmail, 40);
		
		JLabel lblTelefono = new JLabel("Teléfono:");
		contenedor.putConstraint(SpringLayout.NORTH, lblTelefono, 15, SpringLayout.SOUTH, lblEmail);
		contenedor.putConstraint(SpringLayout.WEST, lblTelefono, 15, SpringLayout.WEST, nuevo);
		contenedor.putConstraint(SpringLayout.EAST, lblTelefono, 70, SpringLayout.WEST, lblTelefono);
		nuevo.add(lblTelefono);
		
		txtTelefono = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txtTelefono, -2, SpringLayout.NORTH, lblTelefono);
		contenedor.putConstraint(SpringLayout.WEST, txtTelefono, 5, SpringLayout.EAST, lblTelefono);
		contenedor.putConstraint(SpringLayout.EAST, txtTelefono, 150, SpringLayout.WEST, txtTelefono);
		nuevo.add(txtTelefono);
		configurarJTextField(txtTelefono, 20);
		
		lblcomboBox1 = new JLabel();
		contenedor.putConstraint(SpringLayout.NORTH, lblcomboBox1, 15, SpringLayout.SOUTH, lblTelefono);
		contenedor.putConstraint(SpringLayout.WEST, lblcomboBox1, 15, SpringLayout.WEST, nuevo);
		contenedor.putConstraint(SpringLayout.EAST, lblcomboBox1, 70, SpringLayout.WEST, lblcomboBox1);
		nuevo.add(lblcomboBox1);
		
		comboBox1 = new JComboBox<String>();
		contenedor.putConstraint(SpringLayout.NORTH, comboBox1, -2, SpringLayout.NORTH, lblcomboBox1);
		contenedor.putConstraint(SpringLayout.WEST, comboBox1, 5, SpringLayout.EAST, lblcomboBox1);
		contenedor.putConstraint(SpringLayout.EAST, comboBox1, 250, SpringLayout.WEST, comboBox1);
		nuevo.add(comboBox1);

		lblcomboBox2 = new JLabel();
		contenedor.putConstraint(SpringLayout.NORTH, lblcomboBox2, 15, SpringLayout.SOUTH, lblcomboBox1);
		contenedor.putConstraint(SpringLayout.WEST, lblcomboBox2, 15, SpringLayout.WEST, nuevo);
		contenedor.putConstraint(SpringLayout.EAST, lblcomboBox2, 70, SpringLayout.WEST, lblcomboBox2);
		lblcomboBox2.setVisible(false);
		nuevo.add(lblcomboBox2);
		
		comboBox2 = new JComboBox<String>();
		contenedor.putConstraint(SpringLayout.NORTH, comboBox2, -2, SpringLayout.NORTH, lblcomboBox2);
		contenedor.putConstraint(SpringLayout.WEST, comboBox2, 5, SpringLayout.EAST, lblcomboBox2);
		contenedor.putConstraint(SpringLayout.EAST, comboBox2, 250, SpringLayout.WEST, comboBox2);
		comboBox2.setVisible(false);
		nuevo.add(comboBox2);
		
		lblTxt1 = new JLabel();
		contenedor.putConstraint(SpringLayout.NORTH, lblTxt1, 15, SpringLayout.SOUTH, lblcomboBox2);
		contenedor.putConstraint(SpringLayout.WEST, lblTxt1, 15, SpringLayout.WEST, nuevo);
		contenedor.putConstraint(SpringLayout.EAST, lblTxt1, 70, SpringLayout.WEST, lblTxt1);
		lblTxt1.setVisible(false);
		nuevo.add(lblTxt1);
		
		txt1 = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txt1, -2, SpringLayout.NORTH, lblTxt1);
		contenedor.putConstraint(SpringLayout.WEST, txt1, 5, SpringLayout.EAST, lblTxt1);
		contenedor.putConstraint(SpringLayout.EAST, txt1, 285, SpringLayout.WEST, txt1);
		txt1.setVisible(false);
		nuevo.add(txt1);
		configurarJTextField(txt1, 40);
		
		lblTxt2 = new JLabel();
		contenedor.putConstraint(SpringLayout.NORTH, lblTxt2, 15, SpringLayout.SOUTH, lblTxt1);
		contenedor.putConstraint(SpringLayout.WEST, lblTxt2, 15, SpringLayout.WEST, nuevo);
		contenedor.putConstraint(SpringLayout.EAST, lblTxt2, 70, SpringLayout.WEST, lblTxt2);
		lblTxt2.setVisible(false);
		nuevo.add(lblTxt2);
		
		txt2 = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txt2, -2, SpringLayout.NORTH, lblTxt2);
		contenedor.putConstraint(SpringLayout.WEST, txt2, 5, SpringLayout.EAST, lblTxt2);
		contenedor.putConstraint(SpringLayout.EAST, txt2, 150, SpringLayout.WEST, txt2);
		txt2.setVisible(false);
		nuevo.add(txt2);
		configurarJTextField(txt2, 20);
		
		lblMsgError = new JLabel();
		contenedor.putConstraint(SpringLayout.NORTH, lblMsgError, 15, SpringLayout.SOUTH, lblTxt2);
		contenedor.putConstraint(SpringLayout.WEST, lblMsgError, 15, SpringLayout.WEST, nuevo);
		lblMsgError.setForeground(Color.RED);
		nuevo.add(lblMsgError);
	
		btnGuardar = new JButton("Guardar");
		contenedor.putConstraint(SpringLayout.SOUTH, btnGuardar, -15, SpringLayout.SOUTH, nuevo);
		contenedor.putConstraint(SpringLayout.WEST, btnGuardar, 60, SpringLayout.WEST, nuevo);
		contenedor.putConstraint(SpringLayout.EAST, btnGuardar, 85, SpringLayout.WEST, btnGuardar);
		nuevo.add(btnGuardar);
		
		btnVolver = new JButton("Volver");
		contenedor.putConstraint(SpringLayout.NORTH, btnVolver, 0, SpringLayout.NORTH, btnGuardar);
		contenedor.putConstraint(SpringLayout.WEST, btnVolver, 60, SpringLayout.EAST, btnGuardar);
		contenedor.putConstraint(SpringLayout.EAST, btnVolver, 85, SpringLayout.WEST, btnVolver);
		nuevo.add(btnVolver);
	}
	
	private void configurarJTextField(Component nombre, int cantidadCaracteres) {
		
		((JTextField) nombre).setColumns(cantidadCaracteres);
		nombre.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				
				if(((JTextField) nombre).getText().length() >= cantidadCaracteres)
					e.consume();
			}
		});
	}
}
