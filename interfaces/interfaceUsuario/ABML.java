package interfaceUsuario;

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
	public JPanel panel;
	private JScrollPane scrollTabla;
	public JTable tabla;
	public JTextField txt1;
	public JCheckBox chckbx1;
	public JComboBox<String> comboBox1;
	public JButton btnNuevo;
	public JButton btnEditar;
	public JButton btnImprimir;
	public JButton btnVolver;

	public ABML(String nombreVentana) {
		
		super(nombreVentana);
		panel = new JPanel();
		setContentPane(panel);
		SpringLayout contenedor = new SpringLayout();
		panel.setLayout(contenedor);
		
		scrollTabla = new JScrollPane();
		contenedor.putConstraint(SpringLayout.NORTH, scrollTabla, 15, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.WEST, scrollTabla, 15, SpringLayout.WEST, panel);
		contenedor.putConstraint(SpringLayout.SOUTH, scrollTabla, -10, SpringLayout.SOUTH, panel);
		contenedor.putConstraint(SpringLayout.EAST, scrollTabla, -120, SpringLayout.EAST, panel);
		panel.add(scrollTabla);
		tabla = new JTable();
		scrollTabla.setViewportView(tabla);
		
		btnNuevo = new JButton("Nuevo");
		contenedor.putConstraint(SpringLayout.NORTH, btnNuevo, 15, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.EAST, btnNuevo, -10, SpringLayout.EAST, panel);
		contenedor.putConstraint(SpringLayout.WEST, btnNuevo, -105, SpringLayout.EAST, btnNuevo);
		panel.add(btnNuevo);
		
		btnEditar = new JButton("Editar");
		contenedor.putConstraint(SpringLayout.NORTH, btnEditar, 25, SpringLayout.SOUTH, btnNuevo);
		contenedor.putConstraint(SpringLayout.EAST, btnEditar, -10, SpringLayout.EAST, panel);
		contenedor.putConstraint(SpringLayout.WEST, btnEditar, -105, SpringLayout.EAST, btnEditar);
		panel.add(btnEditar);
		
		chckbx1 = new JCheckBox("Activo");
		chckbx1.setSelected(true);
		contenedor.putConstraint(SpringLayout.NORTH, chckbx1, 25, SpringLayout.SOUTH, btnEditar);
		contenedor.putConstraint(SpringLayout.EAST, chckbx1, -10, SpringLayout.EAST, panel);
		contenedor.putConstraint(SpringLayout.WEST, chckbx1, -105, SpringLayout.EAST, chckbx1);
		chckbx1.setVisible(false);
		panel.add(chckbx1);
		
		txt1 = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txt1, 25, SpringLayout.SOUTH, chckbx1);
		contenedor.putConstraint(SpringLayout.EAST, txt1, -10, SpringLayout.EAST, panel);
		contenedor.putConstraint(SpringLayout.WEST, txt1, -105, SpringLayout.EAST, txt1);
		txt1.setVisible(false);
		panel.add(txt1);
		
		comboBox1 = new JComboBox<String>();
		contenedor.putConstraint(SpringLayout.NORTH, comboBox1, 25, SpringLayout.SOUTH, txt1);
		contenedor.putConstraint(SpringLayout.EAST, comboBox1, -10, SpringLayout.EAST, panel);
		contenedor.putConstraint(SpringLayout.WEST, comboBox1, -105, SpringLayout.EAST, comboBox1);
		comboBox1.setVisible(false);
		panel.add(comboBox1);
			
		btnVolver = new JButton("Volver");
		contenedor.putConstraint(SpringLayout.SOUTH, btnVolver, -10, SpringLayout.SOUTH, panel);
		contenedor.putConstraint(SpringLayout.EAST, btnVolver, -10, SpringLayout.EAST, panel);
		contenedor.putConstraint(SpringLayout.WEST, btnVolver, -105, SpringLayout.EAST, btnVolver);
		panel.add(btnVolver);
		
		btnImprimir = new JButton("Imprimir");
		contenedor.putConstraint(SpringLayout.SOUTH, btnImprimir, -25, SpringLayout.NORTH, btnVolver);
		contenedor.putConstraint(SpringLayout.EAST, btnImprimir, -10, SpringLayout.EAST, panel);
		contenedor.putConstraint(SpringLayout.WEST, btnImprimir, -105, SpringLayout.EAST, btnImprimir);
		panel.add(btnImprimir);
	}
}