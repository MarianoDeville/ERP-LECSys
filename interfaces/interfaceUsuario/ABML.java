package interfaceUsuario;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class ABML extends VentanaModelo {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollTabla;
	public JTable tablaAlumnos;
	public JTextField txtFiltrar;
	public JCheckBox chckbxActivo;
	public JComboBox<String> comboBoxOrden;
	public JButton btnNuevo;
	public JButton btnEditar;
	public JButton btnBuscar;
	public JButton btnImprimir;
	public JButton btnVolver;

	public ABML(String nombreVentana) {
		
		super(nombreVentana);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		scrollTabla = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollTabla, 15, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollTabla, 15, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollTabla, -10, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollTabla, -120, SpringLayout.EAST, contentPane);
		contentPane.add(scrollTabla);
		tablaAlumnos = new JTable();
		scrollTabla.setViewportView(tablaAlumnos);
		
		btnNuevo = new JButton("Nuevo");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNuevo, 35, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnNuevo, -10, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNuevo, -90, SpringLayout.EAST, btnNuevo);
		contentPane.add(btnNuevo);
		
		btnEditar = new JButton("Editar");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnEditar, 25, SpringLayout.SOUTH, btnNuevo);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnEditar, 0, SpringLayout.EAST, btnNuevo);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnEditar, -90, SpringLayout.EAST, btnEditar);
		contentPane.add(btnEditar);
		
		btnBuscar = new JButton("Buscar");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnBuscar, 25, SpringLayout.SOUTH, btnEditar);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnBuscar, 0, SpringLayout.EAST, btnNuevo);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnBuscar, -90, SpringLayout.EAST, btnBuscar);
		contentPane.add(btnBuscar);
		
		btnImprimir = new JButton("Imprimir");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnImprimir, 25, SpringLayout.SOUTH, btnBuscar);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnImprimir, 0, SpringLayout.EAST, btnNuevo);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnImprimir, -90, SpringLayout.EAST, btnImprimir);
		contentPane.add(btnImprimir);		
			
		chckbxActivo = new JCheckBox("Activo");
		chckbxActivo.setSelected(true);
		sl_contentPane.putConstraint(SpringLayout.NORTH, chckbxActivo, 25, SpringLayout.SOUTH, btnImprimir);
		sl_contentPane.putConstraint(SpringLayout.EAST, chckbxActivo, 0, SpringLayout.EAST, btnNuevo);
		sl_contentPane.putConstraint(SpringLayout.WEST, chckbxActivo, -90, SpringLayout.EAST, chckbxActivo);
		contentPane.add(chckbxActivo);
		
		comboBoxOrden = new JComboBox<String>();
		sl_contentPane.putConstraint(SpringLayout.NORTH, comboBoxOrden, 25, SpringLayout.SOUTH, chckbxActivo);
		sl_contentPane.putConstraint(SpringLayout.EAST, comboBoxOrden, 0, SpringLayout.EAST, btnNuevo);
		sl_contentPane.putConstraint(SpringLayout.WEST, comboBoxOrden, -90, SpringLayout.EAST, comboBoxOrden);
		contentPane.add(comboBoxOrden);
		
		txtFiltrar = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtFiltrar, 25, SpringLayout.SOUTH, comboBoxOrden);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtFiltrar, 0, SpringLayout.EAST, btnNuevo);
		contentPane.add(txtFiltrar);
		txtFiltrar.setColumns(8);
		
		btnVolver = new JButton("Volver");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnVolver, -10, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnVolver, -10, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnVolver, -85, SpringLayout.EAST, btnVolver);
		contentPane.add(btnVolver);
	}
}