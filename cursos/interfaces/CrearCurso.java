package interfaces;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import interfaceUsuario.VentanaModelo;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

public class CrearCurso extends VentanaModelo{

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	public JScrollPane scrollTabla;
	public JTextField txtCuota;
	public JComboBox<String> comboBoxNivel;
	public JComboBox<String> comboBoxAño;
	public JComboBox<String> comboBoxProfesor;
	public JComboBox<String> comboBoxAula;
	public JLabel lblMensageError;
	public JButton btnGuardar;
	public JButton btnVolver;
	public JTable tablaHorarios;
	
	public CrearCurso(String nombreVentana) {
		
		super(nombreVentana);
		setResizable(true);
		setBounds(10, 20, 480, 330);
		panel = new JPanel();
		panel.setBorder(null);
		setContentPane(panel);
		SpringLayout contenedor = new SpringLayout();
		panel.setLayout(contenedor);

		JLabel lblNivel = new JLabel("Nivel:");
		contenedor.putConstraint(SpringLayout.NORTH, lblNivel, 15, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.WEST, lblNivel, 25, SpringLayout.WEST, panel);
		contenedor.putConstraint(SpringLayout.SOUTH, lblNivel, 35, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.EAST, lblNivel, 95, SpringLayout.WEST, panel);
		panel.add(lblNivel);
		
		comboBoxNivel = new JComboBox<String>();
		contenedor.putConstraint(SpringLayout.NORTH, comboBoxNivel, 15, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.WEST, comboBoxNivel, 95, SpringLayout.WEST, panel);
		contenedor.putConstraint(SpringLayout.SOUTH, comboBoxNivel, 35, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.EAST, comboBoxNivel, 215, SpringLayout.WEST, panel);
		panel.add(comboBoxNivel);
		
		JLabel lblAo = new JLabel("Año:");
		contenedor.putConstraint(SpringLayout.NORTH, lblAo, 40, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.WEST, lblAo, 25, SpringLayout.WEST, panel);
		contenedor.putConstraint(SpringLayout.SOUTH, lblAo, 60, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.EAST, lblAo, 95, SpringLayout.WEST, panel);
		panel.add(lblAo);	
		
		comboBoxAño = new JComboBox<String>();
		contenedor.putConstraint(SpringLayout.NORTH, comboBoxAño, 40, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.WEST, comboBoxAño, 95, SpringLayout.WEST, panel);
		contenedor.putConstraint(SpringLayout.SOUTH, comboBoxAño, 60, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.EAST, comboBoxAño, 215, SpringLayout.WEST, panel);
		panel.add(comboBoxAño);
		
		JLabel lblProfesor = new JLabel("Profesor:");
		contenedor.putConstraint(SpringLayout.NORTH, lblProfesor, 65, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.WEST, lblProfesor, 25, SpringLayout.WEST, panel);
		contenedor.putConstraint(SpringLayout.SOUTH, lblProfesor, 85, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.EAST, lblProfesor, 95, SpringLayout.WEST, panel);
		panel.add(lblProfesor);
		
		comboBoxProfesor = new JComboBox<String>();
		contenedor.putConstraint(SpringLayout.NORTH, comboBoxProfesor, 65, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.WEST, comboBoxProfesor, 95, SpringLayout.WEST, panel);
		contenedor.putConstraint(SpringLayout.SOUTH, comboBoxProfesor, 85, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.EAST, comboBoxProfesor, 295, SpringLayout.WEST, panel);
		panel.add(comboBoxProfesor);
		
		JLabel lblAula = new JLabel("Aula:");
		contenedor.putConstraint(SpringLayout.NORTH, lblAula, 115, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.WEST, lblAula, 25, SpringLayout.WEST, panel);
		contenedor.putConstraint(SpringLayout.SOUTH, lblAula, 135, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.EAST, lblAula, 95, SpringLayout.WEST, panel);
		panel.add(lblAula);
		
		comboBoxAula = new JComboBox<String>();
		contenedor.putConstraint(SpringLayout.NORTH, comboBoxAula, 115, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.WEST, comboBoxAula, 95, SpringLayout.WEST, panel);
		contenedor.putConstraint(SpringLayout.SOUTH, comboBoxAula, 135, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.EAST, comboBoxAula, 215, SpringLayout.WEST, panel);
		panel.add(comboBoxAula);
		
		JLabel lblHorario = new JLabel("Horario:");
		contenedor.putConstraint(SpringLayout.NORTH, lblHorario, 150, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.WEST, lblHorario, 25, SpringLayout.WEST, panel);
		contenedor.putConstraint(SpringLayout.SOUTH, lblHorario, 170, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.EAST, lblHorario, 95, SpringLayout.WEST, panel);
		panel.add(lblHorario);
		
		JLabel lblLunes = new JLabel("Lunes");
		contenedor.putConstraint(SpringLayout.NORTH, lblLunes, 175, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.WEST, lblLunes, 25, SpringLayout.WEST, panel);
		contenedor.putConstraint(SpringLayout.SOUTH, lblLunes, 195, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.EAST, lblLunes, 95, SpringLayout.WEST, panel);
		lblLunes.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblLunes);
		
		JLabel lblMartes = new JLabel("Martes");
		contenedor.putConstraint(SpringLayout.NORTH, lblMartes, 200, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.WEST, lblMartes, 25, SpringLayout.WEST, panel);
		contenedor.putConstraint(SpringLayout.SOUTH, lblMartes, 220, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.EAST, lblMartes, 95, SpringLayout.WEST, panel);
		lblMartes.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblMartes);
		
		JLabel lblMiercoles = new JLabel("Miércoles");
		contenedor.putConstraint(SpringLayout.NORTH, lblMiercoles, 225, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.WEST, lblMiercoles, 25, SpringLayout.WEST, panel);
		contenedor.putConstraint(SpringLayout.SOUTH, lblMiercoles, 245, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.EAST, lblMiercoles, 95, SpringLayout.WEST, panel);
		lblMiercoles.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblMiercoles);
		
		JLabel lblJueves = new JLabel("Jueves");
		contenedor.putConstraint(SpringLayout.NORTH, lblJueves, 250, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.WEST, lblJueves, 25, SpringLayout.WEST, panel);
		contenedor.putConstraint(SpringLayout.SOUTH, lblJueves, 270, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.EAST, lblJueves, 95, SpringLayout.WEST, panel);
		lblJueves.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblJueves);
		
		JLabel lblViernes = new JLabel("Viernes");
		contenedor.putConstraint(SpringLayout.NORTH, lblViernes, 275, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.WEST, lblViernes, 25, SpringLayout.WEST, panel);
		contenedor.putConstraint(SpringLayout.SOUTH, lblViernes, 295, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.EAST, lblViernes, 95, SpringLayout.WEST, panel);
		lblViernes.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblViernes);
		
		JLabel lblSabado = new JLabel("Sábado");
		contenedor.putConstraint(SpringLayout.NORTH, lblSabado, 300, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.WEST, lblSabado, 25, SpringLayout.WEST, panel);
		contenedor.putConstraint(SpringLayout.SOUTH, lblSabado, 320, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.EAST, lblSabado, 95, SpringLayout.WEST, panel);
		lblSabado.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblSabado);
		
		lblMensageError = new JLabel("");
		contenedor.putConstraint(SpringLayout.NORTH, lblMensageError, 360, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.WEST, lblMensageError, 25, SpringLayout.WEST, panel);
		contenedor.putConstraint(SpringLayout.SOUTH, lblMensageError, 380, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.EAST, lblMensageError, 541, SpringLayout.WEST, panel);
		lblMensageError.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblMensageError);
		
		JLabel lblCuota = new JLabel("Valor cuota:");
		contenedor.putConstraint(SpringLayout.NORTH, lblCuota, 90, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.WEST, lblCuota, 25, SpringLayout.WEST, panel);
		contenedor.putConstraint(SpringLayout.SOUTH, lblCuota, 110, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.EAST, lblCuota, 95, SpringLayout.WEST, panel);
		panel.add(lblCuota);
		
		txtCuota = new JTextField();
		contenedor.putConstraint(SpringLayout.NORTH, txtCuota, 90, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.WEST, txtCuota, 95, SpringLayout.WEST, panel);
		contenedor.putConstraint(SpringLayout.EAST, txtCuota, 215, SpringLayout.WEST, panel);
		panel.add(txtCuota);
		txtCuota.setColumns(10);

		scrollTabla = new JScrollPane();
		contenedor.putConstraint(SpringLayout.NORTH, scrollTabla, 150, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.WEST, scrollTabla, 95, SpringLayout.WEST, panel);
		contenedor.putConstraint(SpringLayout.SOUTH, scrollTabla, 340, SpringLayout.NORTH, panel);
		contenedor.putConstraint(SpringLayout.EAST, scrollTabla, -25, SpringLayout.EAST, panel);
		panel.add(scrollTabla);
		tablaHorarios = new JTable();
		tablaHorarios.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tablaHorarios.doLayout();
		scrollTabla.setViewportView(tablaHorarios); 
		
		btnGuardar = new JButton("Guardar");
		contenedor.putConstraint(SpringLayout.SOUTH, btnGuardar, -15, SpringLayout.SOUTH, panel);
		contenedor.putConstraint(SpringLayout.WEST, btnGuardar, 60, SpringLayout.WEST, panel);
		contenedor.putConstraint(SpringLayout.EAST, btnGuardar, 85, SpringLayout.WEST, btnGuardar);
		panel.add(btnGuardar);
		
		btnVolver = new JButton("Volver");
		contenedor.putConstraint(SpringLayout.NORTH, btnVolver, 0, SpringLayout.NORTH, btnGuardar);
		contenedor.putConstraint(SpringLayout.WEST, btnVolver, 60, SpringLayout.EAST, btnGuardar);
		contenedor.putConstraint(SpringLayout.EAST, btnVolver, 85, SpringLayout.WEST, btnVolver);
		panel.add(btnVolver);
		
	}
}