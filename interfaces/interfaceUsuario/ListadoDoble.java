package interfaceUsuario;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class ListadoDoble extends VentanaModelo {

	private static final long serialVersionUID = 1L;
	private JPanel listado;
	private JScrollPane scrollTabla1;
	private JScrollPane scrollTabla2;
	public JTable tabla1;
	public JTable tabla2;
	public JCheckBox checkBoxActivos;
	public JTextField txtTabla1;
	public JTextField txtTabla2;
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

		scrollTabla1 = new JScrollPane();
		contenedor.putConstraint(SpringLayout.NORTH, scrollTabla1, 50, SpringLayout.NORTH, listado);
		contenedor.putConstraint(SpringLayout.WEST, scrollTabla1, 15, SpringLayout.WEST, listado);
		contenedor.putConstraint(SpringLayout.SOUTH, scrollTabla1, -50, SpringLayout.SOUTH, listado);
		contenedor.putConstraint(SpringLayout.EAST, scrollTabla1, -489, SpringLayout.EAST, listado);
		listado.add(scrollTabla1);
		tabla1 = new JTable();
		scrollTabla1.setViewportView(tabla1);
		
		txtTabla1 = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txtTabla1, 15, SpringLayout.NORTH, listado);
		contenedor.putConstraint(SpringLayout.WEST, txtTabla1, 30, SpringLayout.WEST, scrollTabla1);
		txtTabla1.setColumns(20);
		listado.add(txtTabla1);
		
		checkBoxActivos = new JCheckBox("Activos");
		contenedor.putConstraint(SpringLayout.NORTH, checkBoxActivos, 0, SpringLayout.NORTH, txtTabla1);
		contenedor.putConstraint(SpringLayout.WEST, checkBoxActivos, 35, SpringLayout.EAST, txtTabla1);
		listado.add(checkBoxActivos);
		
		btnAgregar = new JButton("Agregar");
		contenedor.putConstraint(SpringLayout.NORTH, btnAgregar, 50, SpringLayout.NORTH, scrollTabla1);
		contenedor.putConstraint(SpringLayout.WEST, btnAgregar, 10, SpringLayout.EAST, scrollTabla1);
//		contenedor.putConstraint(SpringLayout.EAST, btnAgregar, -90, SpringLayout.WEST, btnAgregar);
		listado.add(btnAgregar);
		
		scrollTabla2 = new JScrollPane();
		contenedor.putConstraint(SpringLayout.NORTH, scrollTabla2, 0, SpringLayout.NORTH, scrollTabla1);
		contenedor.putConstraint(SpringLayout.WEST, scrollTabla2, 10, SpringLayout.EAST, btnAgregar);
		contenedor.putConstraint(SpringLayout.SOUTH, scrollTabla2, 0, SpringLayout.SOUTH, scrollTabla1);
		contenedor.putConstraint(SpringLayout.EAST, scrollTabla2, -10, SpringLayout.EAST, listado);
		listado.add(scrollTabla2);
		tabla2 = new JTable();
		scrollTabla2.setViewportView(tabla2);
		
		txtTabla2 = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txtTabla2, 0, SpringLayout.NORTH, txtTabla1);
		contenedor.putConstraint(SpringLayout.WEST, txtTabla2, 30, SpringLayout.WEST, scrollTabla2);
		txtTabla2.setColumns(20);
		listado.add(txtTabla2);
		
		btnEditar = new JButton("Editar");
		contenedor.putConstraint(SpringLayout.SOUTH, btnEditar, -10, SpringLayout.SOUTH, listado);
		contenedor.putConstraint(SpringLayout.WEST, btnEditar, 100, SpringLayout.WEST, listado);
//		contenedor.putConstraint(SpringLayout.WEST, btnEditar, -90, SpringLayout.EAST, btnEditar);
		listado.add(btnEditar);
		
		btnGuafrdar = new JButton("Guardar");
		contenedor.putConstraint(SpringLayout.SOUTH, btnGuafrdar, 0, SpringLayout.SOUTH, btnEditar);
		contenedor.putConstraint(SpringLayout.WEST, btnGuafrdar, 60, SpringLayout.EAST, btnEditar);
//		contenedor.putConstraint(SpringLayout.WEST, btnGuafrdar, -90, SpringLayout.EAST, btnGuafrdar);
		listado.add(btnGuafrdar);
		
		btnVolver = new JButton("Volver");
		contenedor.putConstraint(SpringLayout.SOUTH, btnVolver, -10, SpringLayout.SOUTH, listado);
		contenedor.putConstraint(SpringLayout.EAST, btnVolver, -10, SpringLayout.EAST, listado);
//		contenedor.putConstraint(SpringLayout.WEST, btnVolver, -90, SpringLayout.EAST, btnVolver);
		listado.add(btnVolver);
	}
}
