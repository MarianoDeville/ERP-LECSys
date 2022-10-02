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

public class CrearCurso extends VentanaModelo{

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	public JTextField txtCuota;
	public JComboBox<String> comboBoxNivel;
	public JComboBox<String> comboBoxAño;
	public JComboBox<String> comboBoxProfesor;
	public JComboBox<String> comboBoxTurno;
	public JComboBox<String> comboBoxAula;
	public JLabel lblMensageError;
	public JButton btnGuardar;
	public JButton btnVolver;
	public JTable tablaHorarios;
	
	public CrearCurso(String nombreVentana) {
		
		super(nombreVentana);
		setResizable(false);
		setBounds(10, 20, 480, 330);
		panel = new JPanel();
		panel.setBorder(null);
		setContentPane(panel);
		panel.setLayout(null);

		JLabel lblNivel = new JLabel("Nivel:");
		lblNivel.setBounds(25, 15, 70, 20);
		panel.add(lblNivel);
		
		comboBoxNivel = new JComboBox<String>();
		comboBoxNivel.setBounds(95, 15, 120, 20);
		panel.add(comboBoxNivel);
		
		JLabel lblAo = new JLabel("Año:");
		lblAo.setBounds(25, 40, 70, 20);
		panel.add(lblAo);	
		
		comboBoxAño = new JComboBox<String>();
		comboBoxAño.setBounds(95, 40, 120, 20);
		panel.add(comboBoxAño);
		
		JLabel lblProfesor = new JLabel("Profesor:");
		lblProfesor.setBounds(25, 65, 70, 20);
		panel.add(lblProfesor);
		
		comboBoxProfesor = new JComboBox<String>();
		comboBoxProfesor.setBounds(95, 65, 200, 20);
		panel.add(comboBoxProfesor);
		
		JLabel lblTurno = new JLabel("Turno:");
		lblTurno.setBounds(25, 90, 70, 20);
		panel.add(lblTurno);
		
		comboBoxTurno = new JComboBox<String>();
		comboBoxTurno.setBounds(95, 90, 120, 20);
		panel.add(comboBoxTurno);
		
		JLabel lblAula = new JLabel("Aula:");
		lblAula.setBounds(25, 140, 70, 20);
		panel.add(lblAula);
		
		comboBoxAula = new JComboBox<String>();
		comboBoxAula.setBounds(95, 140, 120, 20);
		panel.add(comboBoxAula);
		
		JLabel lblHorario = new JLabel("Horario:");
		lblHorario.setBounds(25, 175, 70, 20);
		panel.add(lblHorario);
		
		JLabel lblLunes = new JLabel("Lunes");
		lblLunes.setBounds(25, 200, 70, 20);
		lblLunes.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblLunes);
		
		JLabel lblMartes = new JLabel("Martes");
		lblMartes.setBounds(25, 225, 70, 20);
		lblMartes.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblMartes);
		
		JLabel lblMiercoles = new JLabel("Miércoles");
		lblMiercoles.setBounds(25, 250, 70, 20);
		lblMiercoles.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblMiercoles);
		
		JLabel lblJueves = new JLabel("Jueves");
		lblJueves.setBounds(25, 275, 70, 20);
		lblJueves.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblJueves);
		
		JLabel lblViernes = new JLabel("Viernes");
		lblViernes.setBounds(25, 300, 70, 20);
		lblViernes.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblViernes);
		
		JLabel lblSabado = new JLabel("Sábado");
		lblSabado.setBounds(25, 325, 70, 20);
		lblSabado.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblSabado);
		
		lblMensageError = new JLabel("");
		lblMensageError.setBounds(25, 360, 516, 20);
		lblMensageError.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblMensageError);
		
		JLabel lblCuota = new JLabel("Valor cuota:");
		lblCuota.setBounds(25, 115, 70, 20);
		panel.add(lblCuota);
		
		txtCuota = new JTextField();
		txtCuota.setBounds(95, 115, 120, 20);
		panel.add(txtCuota);
		txtCuota.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(95, 175, 450, 175);
		panel.add(scrollPane);
		
		tablaHorarios = new JTable();
		tablaHorarios.setCellSelectionEnabled(true);
		scrollPane.setViewportView(tablaHorarios);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(236, 407, 90, 23);
		panel.add(btnGuardar);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(355, 407, 90, 23);
		panel.add(btnVolver);
		
	}
}