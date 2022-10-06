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
	public JPasswordField txtContrase�a;
	public JPasswordField txtReContrase�a;
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
		
		JLabel lblContrase�a = new JLabel("Contrase�a:");
		contenedor.putConstraint(SpringLayout.NORTH, lblContrase�a, 15, SpringLayout.SOUTH, lblNombre);
		contenedor.putConstraint(SpringLayout.WEST, lblContrase�a, 15, SpringLayout.WEST, panel);
		contenedor.putConstraint(SpringLayout.EAST, lblContrase�a, 115, SpringLayout.WEST, lblContrase�a);
		panel.add(lblContrase�a);
		
		txtContrase�a = new JPasswordField();
		contenedor.putConstraint(SpringLayout.NORTH, txtContrase�a, -2, SpringLayout.NORTH, lblContrase�a);
		contenedor.putConstraint(SpringLayout.WEST, txtContrase�a, 5, SpringLayout.EAST, lblContrase�a);
		contenedor.putConstraint(SpringLayout.EAST, txtContrase�a, 150, SpringLayout.WEST, txtContrase�a);
		panel.add(txtContrase�a);
		configurarJTextField(txtContrase�a, 20);
		
		JLabel lblReContrase�a = new JLabel("Repetir contrase�a:");
		contenedor.putConstraint(SpringLayout.NORTH, lblReContrase�a, 15, SpringLayout.SOUTH, txtContrase�a);
		contenedor.putConstraint(SpringLayout.WEST, lblReContrase�a, 15, SpringLayout.WEST, panel);
		contenedor.putConstraint(SpringLayout.EAST, lblReContrase�a, 115, SpringLayout.WEST, lblReContrase�a);
		panel.add(lblReContrase�a);
		
		txtReContrase�a = new JPasswordField();
		contenedor.putConstraint(SpringLayout.NORTH, txtReContrase�a, -2, SpringLayout.NORTH, lblReContrase�a);
		contenedor.putConstraint(SpringLayout.WEST, txtReContrase�a, 5, SpringLayout.EAST, lblReContrase�a);
		contenedor.putConstraint(SpringLayout.EAST, txtReContrase�a, 150, SpringLayout.WEST, txtReContrase�a);
		panel.add(txtReContrase�a);
		configurarJTextField(txtReContrase�a, 20);
		
		JLabel lblcomboBox1 = new JLabel("Nivel de acceso:");
		contenedor.putConstraint(SpringLayout.NORTH, lblcomboBox1, 15, SpringLayout.SOUTH, lblReContrase�a);
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
