package interfaceUsuario;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class Listado extends VentanaModelo {

	private static final long serialVersionUID = 1L;
	private JPanel listado;
	private JScrollPane scrollTabla;
	public JLabel lblComboBox1;
	public JLabel lblComboBox2;
	public JLabel lblTxt1;
	public JTextField txt1;
	public JTable tabla;
	public JButton btnImprimir;
	public JCheckBox chckbx1;
	public JButton btn1A;
	public JButton btn1B;
	public JButton btnVolver;
	public JComboBox<String> comboBox1;
	public JComboBox<String> comboBox2;
	
	public Listado(String nombreVentana) {
		
		super(nombreVentana);
		listado = new JPanel();
		setContentPane(listado);
		SpringLayout contenedor = new SpringLayout();
		listado.setLayout(contenedor);
		
		lblComboBox1 = new JLabel();
		contenedor.putConstraint(SpringLayout.NORTH, lblComboBox1, 15, SpringLayout.NORTH, listado);
		contenedor.putConstraint(SpringLayout.WEST, lblComboBox1, 15, SpringLayout.WEST, listado);
		lblComboBox1.setVisible(false);
		listado.add(lblComboBox1);
		
		comboBox1 = new JComboBox<String>();
		contenedor.putConstraint(SpringLayout.NORTH, comboBox1, -2, SpringLayout.NORTH, lblComboBox1);
		contenedor.putConstraint(SpringLayout.WEST, comboBox1, 5, SpringLayout.EAST, lblComboBox1);
		contenedor.putConstraint(SpringLayout.EAST, comboBox1, 150, SpringLayout.WEST, comboBox1);
		comboBox1.setVisible(false);
		listado.add(comboBox1);
		
		lblComboBox2 = new JLabel();
		contenedor.putConstraint(SpringLayout.NORTH, lblComboBox2, 15, SpringLayout.NORTH, listado);
		contenedor.putConstraint(SpringLayout.WEST, lblComboBox2, 25, SpringLayout.EAST, comboBox1);
		lblComboBox2.setVisible(false);
		listado.add(lblComboBox2);
		
		comboBox2 = new JComboBox<String>();
		contenedor.putConstraint(SpringLayout.NORTH, comboBox2, -2, SpringLayout.NORTH, lblComboBox2);
		contenedor.putConstraint(SpringLayout.WEST, comboBox2, 5, SpringLayout.EAST, lblComboBox2);
		contenedor.putConstraint(SpringLayout.EAST, comboBox2, 150, SpringLayout.WEST, comboBox2);
		comboBox2.setVisible(false);
		listado.add(comboBox2);
		
		lblTxt1 = new JLabel();
		contenedor.putConstraint(SpringLayout.NORTH, lblTxt1, 15, SpringLayout.NORTH, listado);
		contenedor.putConstraint(SpringLayout.WEST, lblTxt1, 25, SpringLayout.EAST, comboBox2);
		lblTxt1.setVisible(false);
		listado.add(lblTxt1);
		
		txt1 = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txt1, 15, SpringLayout.NORTH, listado);
		contenedor.putConstraint(SpringLayout.WEST, txt1, 5, SpringLayout.EAST, lblTxt1);
		contenedor.putConstraint(SpringLayout.EAST, txt1, 80, SpringLayout.WEST, txt1);
		txt1.setEditable(false);
		txt1.setVisible(false);
		listado.add(txt1);
		
		scrollTabla = new JScrollPane();
		contenedor.putConstraint(SpringLayout.NORTH, scrollTabla, 50, SpringLayout.NORTH, listado);
		contenedor.putConstraint(SpringLayout.WEST, scrollTabla, 15, SpringLayout.WEST, listado);
		contenedor.putConstraint(SpringLayout.SOUTH, scrollTabla, -10, SpringLayout.SOUTH, listado);
		contenedor.putConstraint(SpringLayout.EAST, scrollTabla, -120, SpringLayout.EAST, listado);
		listado.add(scrollTabla);
		tabla = new JTable();
		scrollTabla.setViewportView(tabla);
		
		
		btnImprimir = new JButton("Imprimir");
		contenedor.putConstraint(SpringLayout.NORTH, btnImprimir, 50, SpringLayout.NORTH, listado);
		contenedor.putConstraint(SpringLayout.EAST, btnImprimir, -10, SpringLayout.EAST, listado);
		contenedor.putConstraint(SpringLayout.WEST, btnImprimir, -90, SpringLayout.EAST, btnImprimir);
		listado.add(btnImprimir);
		
		chckbx1 = new JCheckBox();
		contenedor.putConstraint(SpringLayout.NORTH, chckbx1, 25, SpringLayout.SOUTH, btnImprimir);
		contenedor.putConstraint(SpringLayout.EAST, chckbx1, 0, SpringLayout.EAST, btnImprimir);
		contenedor.putConstraint(SpringLayout.WEST, chckbx1, -90, SpringLayout.EAST, chckbx1);
		chckbx1.setSelected(true);
		chckbx1.setVisible(false);
		listado.add(chckbx1);
		
		btn1A = new JButton("");
		contenedor.putConstraint(SpringLayout.NORTH, btn1A, 25, SpringLayout.SOUTH, btnImprimir);
		contenedor.putConstraint(SpringLayout.EAST, btn1A, 0, SpringLayout.EAST, btnImprimir);
		contenedor.putConstraint(SpringLayout.WEST, btn1A, -90, SpringLayout.EAST, btn1A);
		btn1A.setVisible(false);
		listado.add(btn1A);
		
		btnVolver = new JButton("Volver");
		contenedor.putConstraint(SpringLayout.SOUTH, btnVolver, -10, SpringLayout.SOUTH, listado);
		contenedor.putConstraint(SpringLayout.EAST, btnVolver, -10, SpringLayout.EAST, listado);
		contenedor.putConstraint(SpringLayout.WEST, btnVolver, -90, SpringLayout.EAST, btnVolver);
		listado.add(btnVolver);
		
		btn1B = new JButton("");
		contenedor.putConstraint(SpringLayout.SOUTH, btn1B, -25, SpringLayout.NORTH, btnVolver);
		contenedor.putConstraint(SpringLayout.EAST, btn1B, 0, SpringLayout.EAST, btnVolver);
		contenedor.putConstraint(SpringLayout.WEST, btn1B, -90, SpringLayout.EAST, btn1B);
		btn1B.setVisible(false);
		listado.add(btn1B);	
	}
}
