package interfaceUsuario;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ListadoDoble extends VentanaModelo {

	private static final long serialVersionUID = 1L;
	public JPanel panelListado;
	private JScrollPane scrollTabla1;
	private JScrollPane scrollTabla2;
	public JLabel lblTxt1Tabla1;
	public JLabel lblTxt2Tabla1;
	public JLabel lblTxt3Tabla1;
	public JLabel lblTxt2Tabla2;
	public JLabel lblTituloTabla1;
	public JLabel lblTituloTabla2;
	public JTextField txt1Tabla1;
	public JTextField txt2Tabla1;
	public JTextField txt3Tabla1;
	public JTextField txt1Tabla2;
	public JTable tabla1;
	public JTable tabla2;
	public JCheckBox checkBoxActivos;
	public JButton btnAgregar;
	public JButton btnEliminar;
	public JButton btnGuafrdar;
	public JButton btnVolver;

	public ListadoDoble(String nombreVentana) {
		
		super(nombreVentana);
		panelListado = new JPanel();
		setContentPane(panelListado);
		SpringLayout contenedor = new SpringLayout();
		panelListado.setLayout(contenedor);
		
		scrollTabla1 = new JScrollPane();
		contenedor.putConstraint(SpringLayout.NORTH, scrollTabla1, 100, SpringLayout.NORTH, panelListado);
		contenedor.putConstraint(SpringLayout.WEST, scrollTabla1, 15, SpringLayout.WEST, panelListado);
		contenedor.putConstraint(SpringLayout.SOUTH, scrollTabla1, -60, SpringLayout.SOUTH, panelListado);
		contenedor.putConstraint(SpringLayout.EAST, scrollTabla1, 300, SpringLayout.WEST, scrollTabla1);
		panelListado.add(scrollTabla1);
		tabla1 = new JTable();
		scrollTabla1.setViewportView(tabla1);
		
		lblTituloTabla1 = new JLabel("Integrantes del grupo familiar:");
		lblTituloTabla1.setHorizontalAlignment(SwingConstants.CENTER);
		contenedor.putConstraint(SpringLayout.SOUTH, lblTituloTabla1, -5, SpringLayout.NORTH, scrollTabla1);
		contenedor.putConstraint(SpringLayout.WEST, lblTituloTabla1, 0, SpringLayout.WEST, scrollTabla1);
		contenedor.putConstraint(SpringLayout.EAST, lblTituloTabla1, 0, SpringLayout.EAST, scrollTabla1);
		panelListado.add(lblTituloTabla1);
		
		btnAgregar = new JButton("Agregar");
		contenedor.putConstraint(SpringLayout.NORTH, btnAgregar, 50, SpringLayout.NORTH, scrollTabla1);
		contenedor.putConstraint(SpringLayout.WEST, btnAgregar, 5, SpringLayout.EAST, scrollTabla1);
		contenedor.putConstraint(SpringLayout.EAST, btnAgregar, 90, SpringLayout.WEST, btnAgregar);
		panelListado.add(btnAgregar);
		
		scrollTabla2 = new JScrollPane();
		contenedor.putConstraint(SpringLayout.NORTH, scrollTabla2, 60, SpringLayout.NORTH, panelListado);
		contenedor.putConstraint(SpringLayout.WEST, scrollTabla2, 5, SpringLayout.EAST, btnAgregar);
		contenedor.putConstraint(SpringLayout.SOUTH, scrollTabla2, -60, SpringLayout.SOUTH, panelListado);
		contenedor.putConstraint(SpringLayout.EAST, scrollTabla2, -10, SpringLayout.EAST, panelListado);
		panelListado.add(scrollTabla2);
		tabla2 = new JTable();
		scrollTabla2.setViewportView(tabla2);
		
		lblTxt1Tabla1 = new JLabel("Nombre de familia:");
		contenedor.putConstraint(SpringLayout.NORTH, lblTxt1Tabla1, 25, SpringLayout.NORTH, panelListado);
		contenedor.putConstraint(SpringLayout.WEST, lblTxt1Tabla1, 15, SpringLayout.WEST, panelListado);
		panelListado.add(lblTxt1Tabla1);
		
		txt1Tabla1 = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txt1Tabla1, -3, SpringLayout.NORTH, lblTxt1Tabla1);
		contenedor.putConstraint(SpringLayout.WEST, txt1Tabla1, 5, SpringLayout.EAST, lblTxt1Tabla1);
		txt1Tabla1.setColumns(17);
		panelListado.add(txt1Tabla1);
		
		lblTxt2Tabla1 = new JLabel("Descuento:");
		contenedor.putConstraint(SpringLayout.NORTH, lblTxt2Tabla1, 15, SpringLayout.SOUTH, lblTxt1Tabla1);
		contenedor.putConstraint(SpringLayout.WEST, lblTxt2Tabla1, 15, SpringLayout.WEST, panelListado);
		panelListado.add(lblTxt2Tabla1);
		
		txt2Tabla1 = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txt2Tabla1, -3, SpringLayout.NORTH, lblTxt2Tabla1);
		contenedor.putConstraint(SpringLayout.WEST, txt2Tabla1, 5, SpringLayout.EAST, lblTxt2Tabla1);
		txt2Tabla1.setColumns(3);
		panelListado.add(txt2Tabla1);

		lblTxt3Tabla1 = new JLabel("Deuda:");
		contenedor.putConstraint(SpringLayout.NORTH, lblTxt3Tabla1, 0, SpringLayout.NORTH, lblTxt2Tabla1);
		contenedor.putConstraint(SpringLayout.WEST, lblTxt3Tabla1, 10, SpringLayout.EAST, txt2Tabla1);
		panelListado.add(lblTxt3Tabla1);
		
		txt3Tabla1 = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txt3Tabla1, -3, SpringLayout.NORTH, lblTxt3Tabla1);
		contenedor.putConstraint(SpringLayout.WEST, txt3Tabla1, 5, SpringLayout.EAST, lblTxt3Tabla1);
		txt3Tabla1.setColumns(6);
		panelListado.add(txt3Tabla1);
	
		lblTxt2Tabla2 = new JLabel();
		contenedor.putConstraint(SpringLayout.NORTH, lblTxt2Tabla2, 25, SpringLayout.NORTH, panelListado);
		contenedor.putConstraint(SpringLayout.WEST, lblTxt2Tabla2, 0, SpringLayout.WEST, scrollTabla2);
		panelListado.add(lblTxt2Tabla2);
		
		txt1Tabla2 = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txt1Tabla2, -3, SpringLayout.NORTH, lblTxt2Tabla2);
		contenedor.putConstraint(SpringLayout.WEST, txt1Tabla2, 20, SpringLayout.EAST, lblTxt2Tabla2);
		txt1Tabla2.setColumns(10);
		panelListado.add(txt1Tabla2);
		
		checkBoxActivos = new JCheckBox();
		contenedor.putConstraint(SpringLayout.NORTH, checkBoxActivos, -3, SpringLayout.NORTH, lblTxt1Tabla1);
		contenedor.putConstraint(SpringLayout.WEST, checkBoxActivos, 20, SpringLayout.EAST, txt1Tabla2);
		panelListado.add(checkBoxActivos);

		lblTituloTabla2 = new JLabel("Listado de alumnos:");
		lblTituloTabla2.setHorizontalAlignment(SwingConstants.CENTER);
		contenedor.putConstraint(SpringLayout.NORTH, lblTituloTabla2, 15, SpringLayout.SOUTH, lblTxt2Tabla1);
		contenedor.putConstraint(SpringLayout.WEST, lblTituloTabla2, 0, SpringLayout.WEST, scrollTabla2);
		contenedor.putConstraint(SpringLayout.EAST, lblTituloTabla2, 0, SpringLayout.EAST, scrollTabla2);
		panelListado.add(lblTituloTabla2);

		btnEliminar = new JButton("Eliminar");
		contenedor.putConstraint(SpringLayout.NORTH, btnEliminar, 15, SpringLayout.SOUTH, scrollTabla1);
		contenedor.putConstraint(SpringLayout.WEST, btnEliminar, 60, SpringLayout.WEST, panelListado);
		contenedor.putConstraint(SpringLayout.EAST, btnEliminar, 90, SpringLayout.WEST, btnEliminar);
		panelListado.add(btnEliminar);
		
		btnGuafrdar = new JButton("Guardar");
		contenedor.putConstraint(SpringLayout.NORTH, btnGuafrdar, 0, SpringLayout.NORTH, btnEliminar);
		contenedor.putConstraint(SpringLayout.WEST, btnGuafrdar, 60, SpringLayout.EAST, btnEliminar);
		contenedor.putConstraint(SpringLayout.EAST, btnGuafrdar, 90, SpringLayout.WEST, btnGuafrdar);
		panelListado.add(btnGuafrdar);
		
		btnVolver = new JButton("Volver");
		contenedor.putConstraint(SpringLayout.NORTH, btnVolver, 15, SpringLayout.SOUTH, scrollTabla1);
		contenedor.putConstraint(SpringLayout.EAST, btnVolver, -10, SpringLayout.EAST, panelListado);
		contenedor.putConstraint(SpringLayout.WEST, btnVolver, -90, SpringLayout.EAST, btnVolver);
		panelListado.add(btnVolver);
	}
}
