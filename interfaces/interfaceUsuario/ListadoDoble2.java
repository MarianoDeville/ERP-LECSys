package interfaceUsuario;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class ListadoDoble2 extends VentanaModelo {
	
	private static final long serialVersionUID = 1L;
	private JPanel panelListado;
	private JScrollPane scrollTabla1;
	private JScrollPane scrollTabla2;
	public JTable tabla1;
	public JTable tabla2;
	public JComboBox<String> cmbBoxSector;
	public JComboBox<String> cmbBoxGranularidad;
	public JLabel lblTxtMedio1;
	public JLabel lblTxtMedio2;
	public JLabel lblTxtMedio3;
	public JTextField txtSuperior;
	public JTextField txtMedio1;
	public JTextField txtMedio2;
	public JTextField txtMedio3;
	public JButton btnVolver;
	public JButton btnImprimir;
	public JButton btnGuardar;
	public JButton btnCompletar;

	public ListadoDoble2(String nombreVentana) {
		
		super(nombreVentana);
		panelListado = new JPanel();
		setContentPane(panelListado);
		SpringLayout contenedor = new SpringLayout();
		panelListado.setLayout(contenedor);
		
		cmbBoxSector = new JComboBox<String>();
		contenedor.putConstraint(SpringLayout.NORTH, cmbBoxSector, 20, SpringLayout.NORTH, panelListado);
		contenedor.putConstraint(SpringLayout.WEST, cmbBoxSector, 15, SpringLayout.WEST, panelListado);
		panelListado.add(cmbBoxSector);

		txtSuperior = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txtSuperior, 2, SpringLayout.NORTH, cmbBoxSector);
		contenedor.putConstraint(SpringLayout.WEST, txtSuperior, 10, SpringLayout.EAST, cmbBoxSector);
		txtSuperior.setColumns(7);
		panelListado.add(txtSuperior);

		scrollTabla1 = new JScrollPane();
		contenedor.putConstraint(SpringLayout.NORTH, scrollTabla1, 15, SpringLayout.SOUTH, cmbBoxSector);
		contenedor.putConstraint(SpringLayout.WEST, scrollTabla1, 15, SpringLayout.WEST, panelListado);
		contenedor.putConstraint(SpringLayout.SOUTH, scrollTabla1, -285, SpringLayout.SOUTH, panelListado);
		contenedor.putConstraint(SpringLayout.EAST, scrollTabla1, -15, SpringLayout.EAST, panelListado);
		panelListado.add(scrollTabla1);
		tabla1 = new JTable();
		scrollTabla1.setViewportView(tabla1);
		
		lblTxtMedio1 = new JLabel();
		contenedor.putConstraint(SpringLayout.NORTH, lblTxtMedio1, 15, SpringLayout.SOUTH, scrollTabla1);
		contenedor.putConstraint(SpringLayout.WEST, lblTxtMedio1, 15, SpringLayout.WEST, panelListado);
		panelListado.add(lblTxtMedio1);
		
		txtMedio1 = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txtMedio1, -2, SpringLayout.NORTH, lblTxtMedio1);
		contenedor.putConstraint(SpringLayout.WEST, txtMedio1, 10, SpringLayout.EAST, lblTxtMedio1);
		txtMedio1.setColumns(15);
		panelListado.add(txtMedio1);
		
		lblTxtMedio2 = new JLabel();
		contenedor.putConstraint(SpringLayout.NORTH, lblTxtMedio2, 0, SpringLayout.NORTH, lblTxtMedio1);
		contenedor.putConstraint(SpringLayout.WEST, lblTxtMedio2, 20, SpringLayout.EAST, txtMedio1);
		panelListado.add(lblTxtMedio2);
		
		txtMedio2 = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txtMedio2, -2, SpringLayout.NORTH, lblTxtMedio2);
		contenedor.putConstraint(SpringLayout.WEST, txtMedio2, 10, SpringLayout.EAST, lblTxtMedio2);
		txtMedio2.setColumns(5);
		panelListado.add(txtMedio2);
		
		lblTxtMedio3 = new JLabel();
		contenedor.putConstraint(SpringLayout.NORTH, lblTxtMedio3, 0, SpringLayout.NORTH, lblTxtMedio2);
		contenedor.putConstraint(SpringLayout.WEST, lblTxtMedio3, 20, SpringLayout.EAST, txtMedio2);
		panelListado.add(lblTxtMedio3);
		
		txtMedio3 = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txtMedio3, -2, SpringLayout.NORTH, lblTxtMedio3);
		contenedor.putConstraint(SpringLayout.WEST, txtMedio3, 10, SpringLayout.EAST, lblTxtMedio3);
		txtMedio3.setColumns(15);
		panelListado.add(txtMedio3);

		cmbBoxGranularidad = new JComboBox<String>();
		contenedor.putConstraint(SpringLayout.NORTH, cmbBoxGranularidad, 15, SpringLayout.SOUTH, txtMedio3);
		contenedor.putConstraint(SpringLayout.WEST, cmbBoxGranularidad, 15, SpringLayout.WEST, panelListado);
		panelListado.add(cmbBoxGranularidad);

		btnCompletar = new JButton("Autocompletar");
		contenedor.putConstraint(SpringLayout.NORTH, btnCompletar, 0, SpringLayout.NORTH, cmbBoxGranularidad);
		contenedor.putConstraint(SpringLayout.WEST, btnCompletar, 20, SpringLayout.EAST, cmbBoxGranularidad);
		contenedor.putConstraint(SpringLayout.EAST, btnCompletar, 120, SpringLayout.WEST, btnCompletar);
		panelListado.add(btnCompletar);
		
		scrollTabla2 = new JScrollPane();
		contenedor.putConstraint(SpringLayout.NORTH, scrollTabla2, 15, SpringLayout.SOUTH, cmbBoxGranularidad);
		contenedor.putConstraint(SpringLayout.WEST, scrollTabla2, 15, SpringLayout.WEST, panelListado);
		contenedor.putConstraint(SpringLayout.SOUTH, scrollTabla2, -60, SpringLayout.SOUTH, panelListado);
		contenedor.putConstraint(SpringLayout.EAST, scrollTabla2, -10, SpringLayout.EAST, panelListado);
		panelListado.add(scrollTabla2);
		tabla2 = new JTable();
		scrollTabla2.setViewportView(tabla2);
	
		btnGuardar = new JButton("Guardar");
		contenedor.putConstraint(SpringLayout.SOUTH, btnGuardar, -15, SpringLayout.SOUTH, panelListado);
		contenedor.putConstraint(SpringLayout.WEST, btnGuardar, 20, SpringLayout.WEST, panelListado);
		contenedor.putConstraint(SpringLayout.EAST, btnGuardar, 90, SpringLayout.WEST, btnGuardar);
		panelListado.add(btnGuardar);
		
		btnImprimir = new JButton("Imprimir");
		contenedor.putConstraint(SpringLayout.SOUTH, btnImprimir, -15, SpringLayout.SOUTH, panelListado);
		contenedor.putConstraint(SpringLayout.WEST, btnImprimir, 60, SpringLayout.EAST, btnGuardar);
		contenedor.putConstraint(SpringLayout.EAST, btnImprimir, 90, SpringLayout.WEST, btnImprimir);
		panelListado.add(btnImprimir);
		
		btnVolver = new JButton("Volver");
		contenedor.putConstraint(SpringLayout.SOUTH, btnVolver, -15, SpringLayout.SOUTH, panelListado);
		contenedor.putConstraint(SpringLayout.EAST, btnVolver, -20, SpringLayout.EAST, panelListado);
		contenedor.putConstraint(SpringLayout.WEST, btnVolver, -90, SpringLayout.EAST, btnVolver);
		panelListado.add(btnVolver);
	}
}