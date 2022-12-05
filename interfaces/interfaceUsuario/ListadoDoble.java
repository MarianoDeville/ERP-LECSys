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
	private JPanel listado;
	private JScrollPane scrollTabla1;
	private JScrollPane scrollTabla2;
	public JTextField txt1Tabla1;
	public JTextField txt2Tabla1;
	public JTextField txt1Tabla2;
	public JTable tabla1;
	public JTable tabla2;
	public JCheckBox checkBoxActivos;
	public JButton btnAgregar;
	public JButton btnEditar;
	public JButton btnGuafrdar;
	public JButton btnVolver;

	public ListadoDoble(String nombreVentana) {
		
		super(nombreVentana);
		listado = new JPanel();
		setContentPane(listado);
		SpringLayout contenedor = new SpringLayout();
		listado.setLayout(contenedor);

		JLabel lblTxt1Tabla1 = new JLabel("Nombre familia:");
		contenedor.putConstraint(SpringLayout.NORTH, lblTxt1Tabla1, 25, SpringLayout.NORTH, listado);
		contenedor.putConstraint(SpringLayout.WEST, lblTxt1Tabla1, 15, SpringLayout.WEST, listado);
		listado.add(lblTxt1Tabla1);
		
		txt1Tabla1 = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txt1Tabla1, -3, SpringLayout.NORTH, lblTxt1Tabla1);
		contenedor.putConstraint(SpringLayout.WEST, txt1Tabla1, 5, SpringLayout.EAST, lblTxt1Tabla1);
		txt1Tabla1.setColumns(15);
		listado.add(txt1Tabla1);
		
		JLabel lblTxt2Tabla1 = new JLabel("Descuento:");
		contenedor.putConstraint(SpringLayout.NORTH, lblTxt2Tabla1, 0, SpringLayout.NORTH, lblTxt1Tabla1);
		contenedor.putConstraint(SpringLayout.WEST, lblTxt2Tabla1, 15, SpringLayout.EAST, txt1Tabla1);
		listado.add(lblTxt2Tabla1);
		
		txt2Tabla1 = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txt2Tabla1, -3, SpringLayout.NORTH, lblTxt2Tabla1);
		contenedor.putConstraint(SpringLayout.WEST, txt2Tabla1, 5, SpringLayout.EAST, lblTxt2Tabla1);
		txt2Tabla1.setColumns(5);
		listado.add(txt2Tabla1);
		
		checkBoxActivos = new JCheckBox("Activos");
		contenedor.putConstraint(SpringLayout.NORTH, checkBoxActivos, -3, SpringLayout.NORTH, lblTxt1Tabla1);
		contenedor.putConstraint(SpringLayout.WEST, checkBoxActivos, 100, SpringLayout.EAST, txt2Tabla1);
		listado.add(checkBoxActivos);
		
		JLabel lblTxt2Tabla2 = new JLabel();
		contenedor.putConstraint(SpringLayout.NORTH, lblTxt2Tabla2, 0, SpringLayout.NORTH, lblTxt2Tabla1);
		contenedor.putConstraint(SpringLayout.WEST, lblTxt2Tabla2, 15, SpringLayout.EAST, checkBoxActivos);
		listado.add(lblTxt2Tabla2);
		
		txt1Tabla2 = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txt1Tabla2, -3, SpringLayout.NORTH, lblTxt2Tabla2);
		contenedor.putConstraint(SpringLayout.WEST, txt1Tabla2, 20, SpringLayout.EAST, lblTxt2Tabla2);
		txt1Tabla2.setColumns(10);
		listado.add(txt1Tabla2);

		scrollTabla1 = new JScrollPane();
		contenedor.putConstraint(SpringLayout.NORTH, scrollTabla1, 70, SpringLayout.NORTH, listado);
		contenedor.putConstraint(SpringLayout.WEST, scrollTabla1, 15, SpringLayout.WEST, listado);
		contenedor.putConstraint(SpringLayout.SOUTH, scrollTabla1, -60, SpringLayout.SOUTH, listado);
		contenedor.putConstraint(SpringLayout.EAST, scrollTabla1, -489, SpringLayout.EAST, listado);
		listado.add(scrollTabla1);
		tabla1 = new JTable();
		scrollTabla1.setViewportView(tabla1);
		
		
		
		JLabel lblTituloTabla1 = new JLabel("Integrantes del grupo familiar:");
		lblTituloTabla1.setHorizontalAlignment(SwingConstants.CENTER);
		contenedor.putConstraint(SpringLayout.NORTH, lblTituloTabla1, 15, SpringLayout.SOUTH, lblTxt2Tabla1);
		contenedor.putConstraint(SpringLayout.WEST, lblTituloTabla1, 0, SpringLayout.WEST, scrollTabla1);
		contenedor.putConstraint(SpringLayout.EAST, lblTituloTabla1, 0, SpringLayout.EAST, scrollTabla1);
		listado.add(lblTituloTabla1);

		btnAgregar = new JButton("Agregar");
		contenedor.putConstraint(SpringLayout.NORTH, btnAgregar, 86, SpringLayout.SOUTH, txt2Tabla1);
		contenedor.putConstraint(SpringLayout.WEST, btnAgregar, 6, SpringLayout.EAST, scrollTabla1);
//		contenedor.putConstraint(SpringLayout.EAST, btnAgregar, 90, SpringLayout.WEST, btnAgregar);
		listado.add(btnAgregar);
		
		scrollTabla2 = new JScrollPane();
		contenedor.putConstraint(SpringLayout.NORTH, scrollTabla2, 70, SpringLayout.NORTH, listado);
		contenedor.putConstraint(SpringLayout.WEST, scrollTabla2, 14, SpringLayout.EAST, btnAgregar);
		contenedor.putConstraint(SpringLayout.SOUTH, scrollTabla2, -60, SpringLayout.SOUTH, listado);
		contenedor.putConstraint(SpringLayout.EAST, scrollTabla2, -10, SpringLayout.EAST, listado);
		listado.add(scrollTabla2);
		tabla2 = new JTable();
		scrollTabla2.setViewportView(tabla2);

		JLabel lblTituloTabla2 = new JLabel("Listado de alumnos:");
		lblTituloTabla2.setHorizontalAlignment(SwingConstants.CENTER);
		contenedor.putConstraint(SpringLayout.NORTH, lblTituloTabla2, 15, SpringLayout.SOUTH, lblTxt2Tabla1);
		contenedor.putConstraint(SpringLayout.WEST, lblTituloTabla2, 0, SpringLayout.WEST, scrollTabla2);
		contenedor.putConstraint(SpringLayout.EAST, lblTituloTabla2, 0, SpringLayout.EAST, scrollTabla2);
		listado.add(lblTituloTabla2);

		btnEditar = new JButton("Editar");
		contenedor.putConstraint(SpringLayout.EAST, btnEditar, 0, SpringLayout.EAST, lblTxt1Tabla1);
//		contenedor.putConstraint(SpringLayout.EAST, btnEditar, 90, SpringLayout.WEST, btnEditar);
		listado.add(btnEditar);
		
		btnGuafrdar = new JButton("Guardar");
		contenedor.putConstraint(SpringLayout.NORTH, btnEditar, 0, SpringLayout.NORTH, btnGuafrdar);
		contenedor.putConstraint(SpringLayout.EAST, btnGuafrdar, 0, SpringLayout.EAST, lblTxt2Tabla1);
//		contenedor.putConstraint(SpringLayout.EAST, btnGuafrdar, 90, SpringLayout.WEST, btnGuafrdar);
		listado.add(btnGuafrdar);
		
		btnVolver = new JButton("Volver");
		contenedor.putConstraint(SpringLayout.NORTH, btnGuafrdar, 0, SpringLayout.NORTH, btnVolver);
		contenedor.putConstraint(SpringLayout.SOUTH, btnVolver, -10, SpringLayout.SOUTH, listado);
		contenedor.putConstraint(SpringLayout.EAST, btnVolver, -10, SpringLayout.EAST, listado);
//		contenedor.putConstraint(SpringLayout.WEST, btnVolver, -90, SpringLayout.EAST, btnVolver);
		listado.add(btnVolver);
	}
}
