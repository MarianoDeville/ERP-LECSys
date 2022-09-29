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
	public JLabel lbl1;
	public JLabel lbl2;
	public JLabel lbl3;
	public JTextField txt1;
	public JTable tabla;
	public JButton btnImprimir;
	public JCheckBox chckbx1;
	public JButton btnVolver;
	public JComboBox<String> comboBox1;
	public JComboBox<String> comboBox2;
	
	public Listado(String nombreVentana) {
		
		super(nombreVentana);
		listado = new JPanel();
		setContentPane(listado);
		SpringLayout contenedor = new SpringLayout();
		listado.setLayout(contenedor);
		
		lbl1 = new JLabel();
		contenedor.putConstraint(SpringLayout.NORTH, lbl1, 15, SpringLayout.NORTH, listado);
		contenedor.putConstraint(SpringLayout.WEST, lbl1, 15, SpringLayout.WEST, listado);
		lbl1.setVisible(false);
		listado.add(lbl1);
		
		comboBox1 = new JComboBox<String>();
		contenedor.putConstraint(SpringLayout.NORTH, comboBox1, 15, SpringLayout.NORTH, listado);
		contenedor.putConstraint(SpringLayout.WEST, comboBox1, 5, SpringLayout.EAST, lbl1);
		contenedor.putConstraint(SpringLayout.EAST, comboBox1, 150, SpringLayout.WEST, comboBox1);
		comboBox1.setVisible(false);
		listado.add(comboBox1);
		
		lbl2 = new JLabel();
		contenedor.putConstraint(SpringLayout.NORTH, lbl2, 15, SpringLayout.NORTH, listado);
		contenedor.putConstraint(SpringLayout.WEST, lbl2, 25, SpringLayout.EAST, comboBox1);
		lbl2.setVisible(false);
		listado.add(lbl2);
		
		comboBox2 = new JComboBox<String>();
		contenedor.putConstraint(SpringLayout.NORTH, comboBox2, 15, SpringLayout.NORTH, listado);
		contenedor.putConstraint(SpringLayout.WEST, comboBox2, 5, SpringLayout.EAST, lbl2);
		contenedor.putConstraint(SpringLayout.EAST, comboBox2, 150, SpringLayout.WEST, comboBox2);
		comboBox2.setVisible(false);
		listado.add(comboBox2);
		
		lbl3 = new JLabel();
		contenedor.putConstraint(SpringLayout.NORTH, lbl3, 15, SpringLayout.NORTH, listado);
		contenedor.putConstraint(SpringLayout.WEST, lbl3, 25, SpringLayout.EAST, comboBox2);
		lbl3.setVisible(false);
		listado.add(lbl3);
		
		txt1 = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txt1, 15, SpringLayout.NORTH, listado);
		contenedor.putConstraint(SpringLayout.WEST, txt1, 5, SpringLayout.EAST, lbl3);
		contenedor.putConstraint(SpringLayout.EAST, txt1, 80, SpringLayout.WEST, txt1);
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
		chckbx1.setSelected(true);
		contenedor.putConstraint(SpringLayout.NORTH, chckbx1, 25, SpringLayout.SOUTH, btnImprimir);
		contenedor.putConstraint(SpringLayout.EAST, chckbx1, 0, SpringLayout.EAST, listado);
		contenedor.putConstraint(SpringLayout.WEST, chckbx1, -90, SpringLayout.EAST, chckbx1);
		chckbx1.setVisible(false);
		listado.add(chckbx1);
		
		btnVolver = new JButton("Volver");
		contenedor.putConstraint(SpringLayout.SOUTH, btnVolver, -10, SpringLayout.SOUTH, listado);
		contenedor.putConstraint(SpringLayout.EAST, btnVolver, -10, SpringLayout.EAST, listado);
		contenedor.putConstraint(SpringLayout.WEST, btnVolver, -85, SpringLayout.EAST, btnVolver);
		listado.add(btnVolver);
	}
}
