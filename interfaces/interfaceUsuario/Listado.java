package interfaceUsuario;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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
	public JTextField txtcantAlumn;
	public JTable tablaAlumnos;
	public JButton btnImprimir;
	public JCheckBox chckbxActivo;
	public JButton btnVolver;
	public JComboBox<String> comboBoxCriterio;
	public JComboBox<String> comboBoxItem;
	
	public Listado(String nombreVentana) {
		
		super(nombreVentana);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		listado = new JPanel();
		setContentPane(listado);
		SpringLayout sl_contentPane = new SpringLayout();
		listado.setLayout(sl_contentPane);
		
		comboBoxCriterio = new JComboBox<String>();
		sl_contentPane.putConstraint(SpringLayout.NORTH, comboBoxCriterio, 15, SpringLayout.NORTH, listado);
		sl_contentPane.putConstraint(SpringLayout.WEST, comboBoxCriterio, 15, SpringLayout.WEST, listado);
		sl_contentPane.putConstraint(SpringLayout.EAST, comboBoxCriterio, 150, SpringLayout.WEST, comboBoxCriterio);
		listado.add(comboBoxCriterio);
		
		comboBoxItem = new JComboBox<String>();
		sl_contentPane.putConstraint(SpringLayout.NORTH, comboBoxItem, 15, SpringLayout.NORTH, listado);
		sl_contentPane.putConstraint(SpringLayout.WEST, comboBoxItem, 10, SpringLayout.EAST, comboBoxCriterio);
		sl_contentPane.putConstraint(SpringLayout.EAST, comboBoxItem, 150, SpringLayout.WEST, comboBoxItem);
		listado.add(comboBoxItem);
		
		JLabel lblCantAlum = new JLabel("Cantidad de alumnos:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblCantAlum, 15, SpringLayout.NORTH, listado);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblCantAlum, 25, SpringLayout.EAST, comboBoxItem);
		listado.add(lblCantAlum);
		
		txtcantAlumn = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtcantAlumn, 15, SpringLayout.NORTH, listado);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtcantAlumn, 5, SpringLayout.EAST, lblCantAlum);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtcantAlumn, 80, SpringLayout.WEST, txtcantAlumn);
		listado.add(txtcantAlumn);
		
		scrollTabla = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollTabla, 65, SpringLayout.NORTH, listado);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollTabla, 15, SpringLayout.WEST, listado);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollTabla, -10, SpringLayout.SOUTH, listado);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollTabla, -120, SpringLayout.EAST, listado);
		listado.add(scrollTabla);
		tablaAlumnos = new JTable();
		scrollTabla.setViewportView(tablaAlumnos);
		
		btnImprimir = new JButton("Imprimir");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnImprimir, 35, SpringLayout.NORTH, listado);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnImprimir, -10, SpringLayout.EAST, listado);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnImprimir, -90, SpringLayout.EAST, btnImprimir);
		listado.add(btnImprimir);
		
		chckbxActivo = new JCheckBox("Activo");
		chckbxActivo.setSelected(true);
		sl_contentPane.putConstraint(SpringLayout.NORTH, chckbxActivo, 25, SpringLayout.SOUTH, btnImprimir);
		sl_contentPane.putConstraint(SpringLayout.EAST, chckbxActivo, 0, SpringLayout.EAST, listado);
		sl_contentPane.putConstraint(SpringLayout.WEST, chckbxActivo, -90, SpringLayout.EAST, chckbxActivo);
		listado.add(chckbxActivo);
		
		btnVolver = new JButton("Volver");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnVolver, -10, SpringLayout.SOUTH, listado);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnVolver, -10, SpringLayout.EAST, listado);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnVolver, -85, SpringLayout.EAST, btnVolver);
		listado.add(btnVolver);
	}
}
