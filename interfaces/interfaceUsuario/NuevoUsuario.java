package interfaceUsuario;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class NuevoUsuario extends VentanaModelo{

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	public JTextField txtUsuario;
	public JPasswordField txtContraseña;
	public JPasswordField txtReContraseña;
	public JComboBox<String> comboBox1;
	public JComboBox<String> comboBox2;
	public JLabel lblMsgError;
	public JButton btnGuardar;
	public JButton btnBorrar;
	public JButton btnVolver;

	public NuevoUsuario(String nombreVentana) {
		
		super(nombreVentana);
		panel = new JPanel();
		setContentPane(panel);
		SpringLayout contenedor = new SpringLayout();
		setMinimumSize(new Dimension(460, 300));
		setBounds(10, 10, 460, 300);
		panel.setLayout(contenedor);
		
		JLabel lblNombre = new JLabel("Usuario:");
		contenedor.putConstraint(SpringLayout.NORTH, lblNombre, 30, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.WEST, lblNombre, 15, SpringLayout.WEST, panel);
		contenedor.putConstraint(SpringLayout.EAST, lblNombre, 115, SpringLayout.WEST, lblNombre);
		panel.add(lblNombre);
		
		txtUsuario = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txtUsuario, -2, SpringLayout.NORTH, lblNombre);
		contenedor.putConstraint(SpringLayout.WEST, txtUsuario, 5, SpringLayout.EAST, lblNombre);
		contenedor.putConstraint(SpringLayout.EAST, txtUsuario, 150, SpringLayout.WEST, txtUsuario);
		panel.add(txtUsuario);
		configurarJTextField(txtUsuario, 20);
		
		JLabel lblContraseña = new JLabel("Contraseña:");
		contenedor.putConstraint(SpringLayout.NORTH, lblContraseña, 15, SpringLayout.SOUTH, lblNombre);
		contenedor.putConstraint(SpringLayout.WEST, lblContraseña, 15, SpringLayout.WEST, panel);
		contenedor.putConstraint(SpringLayout.EAST, lblContraseña, 115, SpringLayout.WEST, lblContraseña);
		panel.add(lblContraseña);
		
		txtContraseña = new JPasswordField();
		contenedor.putConstraint(SpringLayout.NORTH, txtContraseña, -2, SpringLayout.NORTH, lblContraseña);
		contenedor.putConstraint(SpringLayout.WEST, txtContraseña, 5, SpringLayout.EAST, lblContraseña);
		contenedor.putConstraint(SpringLayout.EAST, txtContraseña, 150, SpringLayout.WEST, txtContraseña);
		panel.add(txtContraseña);
		configurarJTextField(txtContraseña, 20);
		
		JLabel lblReContraseña = new JLabel("Repetir contraseña:");
		contenedor.putConstraint(SpringLayout.NORTH, lblReContraseña, 15, SpringLayout.SOUTH, txtContraseña);
		contenedor.putConstraint(SpringLayout.WEST, lblReContraseña, 15, SpringLayout.WEST, panel);
		contenedor.putConstraint(SpringLayout.EAST, lblReContraseña, 115, SpringLayout.WEST, lblReContraseña);
		panel.add(lblReContraseña);
		
		txtReContraseña = new JPasswordField();
		contenedor.putConstraint(SpringLayout.NORTH, txtReContraseña, -2, SpringLayout.NORTH, lblReContraseña);
		contenedor.putConstraint(SpringLayout.WEST, txtReContraseña, 5, SpringLayout.EAST, lblReContraseña);
		contenedor.putConstraint(SpringLayout.EAST, txtReContraseña, 150, SpringLayout.WEST, txtReContraseña);
		panel.add(txtReContraseña);
		configurarJTextField(txtReContraseña, 20);
		
		JLabel lblcomboBox1 = new JLabel("Nivel de acceso:");
		contenedor.putConstraint(SpringLayout.NORTH, lblcomboBox1, 15, SpringLayout.SOUTH, lblReContraseña);
		contenedor.putConstraint(SpringLayout.WEST, lblcomboBox1, 15, SpringLayout.WEST, panel);
		contenedor.putConstraint(SpringLayout.EAST, lblcomboBox1, 115, SpringLayout.WEST, lblcomboBox1);
		panel.add(lblcomboBox1);
		
		comboBox1 = new JComboBox<String>();
		contenedor.putConstraint(SpringLayout.NORTH, comboBox1, -2, SpringLayout.NORTH, lblcomboBox1);
		contenedor.putConstraint(SpringLayout.WEST, comboBox1, 5, SpringLayout.EAST, lblcomboBox1);
		contenedor.putConstraint(SpringLayout.EAST, comboBox1, 250, SpringLayout.WEST, comboBox1);
		panel.add(comboBox1);

		JLabel lblcomboBox2 = new JLabel("Empleado:");
		contenedor.putConstraint(SpringLayout.NORTH, lblcomboBox2, 15, SpringLayout.SOUTH, lblcomboBox1);
		contenedor.putConstraint(SpringLayout.WEST, lblcomboBox2, 15, SpringLayout.WEST, panel);
		contenedor.putConstraint(SpringLayout.EAST, lblcomboBox2, 115, SpringLayout.WEST, lblcomboBox2);
		panel.add(lblcomboBox2);
		
		comboBox2 = new JComboBox<String>();
		contenedor.putConstraint(SpringLayout.NORTH, comboBox2, -2, SpringLayout.NORTH, lblcomboBox2);
		contenedor.putConstraint(SpringLayout.WEST, comboBox2, 5, SpringLayout.EAST, lblcomboBox2);
		contenedor.putConstraint(SpringLayout.EAST, comboBox2, 250, SpringLayout.WEST, comboBox2);
		panel.add(comboBox2);
		
		lblMsgError = new JLabel();
		contenedor.putConstraint(SpringLayout.NORTH, lblMsgError, 15, SpringLayout.SOUTH, lblcomboBox2);
		contenedor.putConstraint(SpringLayout.WEST, lblMsgError, 15, SpringLayout.WEST, panel);
		lblMsgError.setForeground(Color.RED);
		panel.add(lblMsgError);
		
		btnGuardar = new JButton("Guardar");
		contenedor.putConstraint(SpringLayout.SOUTH, btnGuardar, -15, SpringLayout.SOUTH, panel);
		contenedor.putConstraint(SpringLayout.WEST, btnGuardar, 40, SpringLayout.WEST, panel);
		contenedor.putConstraint(SpringLayout.EAST, btnGuardar, 85, SpringLayout.WEST, btnGuardar);
		panel.add(btnGuardar);
		
		btnBorrar = new JButton("Borrar");
		contenedor.putConstraint(SpringLayout.NORTH, btnBorrar, 0, SpringLayout.NORTH, btnGuardar);
		contenedor.putConstraint(SpringLayout.WEST, btnBorrar, 60, SpringLayout.EAST, btnGuardar);
		contenedor.putConstraint(SpringLayout.EAST, btnBorrar, 85, SpringLayout.WEST, btnBorrar);
		btnBorrar.setVisible(false);
		panel.add(btnBorrar);

		btnVolver = new JButton("Volver");
		contenedor.putConstraint(SpringLayout.NORTH, btnVolver, 0, SpringLayout.NORTH, btnBorrar);
		contenedor.putConstraint(SpringLayout.WEST, btnVolver, 60, SpringLayout.EAST, btnBorrar);
		contenedor.putConstraint(SpringLayout.EAST, btnVolver, 85, SpringLayout.WEST, btnVolver);
		panel.add(btnVolver);
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
