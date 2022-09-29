package interfaces;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import interfaceUsuario.VentanaModelo;

public class CrearCurso extends VentanaModelo{

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	public JTextField txtLunesHorario;
	public JTextField txtLunesDuracion;
	public JTextField txtMartesHorario;
	public JTextField txtMartesDuracion;
	public JTextField txtMiercolesHorario;
	public JTextField txtMiercolesDuracion;
	public JTextField txtJuevesHorario;
	public JTextField txtJuevesDuracion;
	public JTextField txtViernesHorario;
	public JTextField txtViernesDuracion;
	public JTextField txtSabadoHorario;
	public JTextField txtSabadoDuracion;
	public JTextField txtCuota;
	public JComboBox<String> comboBoxNivel;
	public JComboBox<String> comboBoxAño;
	public JComboBox<String> comboBoxProfesor;
	public JComboBox<String> comboBoxTurno;
	public JLabel lblMensageError;
	public JButton btnGuardar;
	public JButton btnVolver;
	
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
		
		JLabel lblHorario = new JLabel("Horario:");
		lblHorario.setBounds(25, 175, 60, 20);
		panel.add(lblHorario);
		
		JLabel lblDuration = new JLabel("Duración:");
		lblDuration.setBounds(25, 195, 60, 20);
		panel.add(lblDuration);
		
		JLabel lblLunes = new JLabel("Lunes");
		lblLunes.setBounds(85, 155, 60, 20);
		lblLunes.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblLunes);
		
		txtLunesHorario = new JTextField();
		txtLunesHorario.setBounds(85, 175, 60, 20);
		txtLunesHorario.setHorizontalAlignment(SwingConstants.CENTER);
		txtLunesHorario.setColumns(10);
		panel.add(txtLunesHorario);
		
		txtLunesDuracion = new JTextField();
		txtLunesDuracion.setBounds(85, 195, 60, 20);
		txtLunesDuracion.setHorizontalAlignment(SwingConstants.CENTER);
		txtLunesDuracion.setColumns(10);
		panel.add(txtLunesDuracion);
		
		JLabel lblMartes = new JLabel("Martes");
		lblMartes.setBounds(145, 155, 60, 20);
		lblMartes.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblMartes);
		
		txtMartesHorario = new JTextField();
		txtMartesHorario.setBounds(145, 175, 60, 20);
		txtMartesHorario.setHorizontalAlignment(SwingConstants.CENTER);
		txtMartesHorario.setColumns(10);
		panel.add(txtMartesHorario);
		
		txtMartesDuracion = new JTextField();
		txtMartesDuracion.setBounds(145, 195, 60, 20);
		txtMartesDuracion.setHorizontalAlignment(SwingConstants.CENTER);
		txtMartesDuracion.setColumns(10);
		panel.add(txtMartesDuracion);
		
		JLabel lblMiercoles = new JLabel("Miércoles");
		lblMiercoles.setBounds(205, 155, 60, 20);
		lblMiercoles.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblMiercoles);
		
		txtMiercolesHorario = new JTextField();
		txtMiercolesHorario.setBounds(205, 175, 60, 20);
		txtMiercolesHorario.setHorizontalAlignment(SwingConstants.CENTER);
		txtMiercolesHorario.setColumns(10);
		panel.add(txtMiercolesHorario);
		
		txtMiercolesDuracion = new JTextField();
		txtMiercolesDuracion.setBounds(205, 195, 60, 20);
		txtMiercolesDuracion.setHorizontalAlignment(SwingConstants.CENTER);
		txtMiercolesDuracion.setColumns(10);
		panel.add(txtMiercolesDuracion);
		
		JLabel lblJueves = new JLabel("Jueves");
		lblJueves.setBounds(265, 155, 60, 20);
		lblJueves.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblJueves);
		
		txtJuevesHorario = new JTextField();
		txtJuevesHorario.setBounds(266, 175, 60, 20);
		txtJuevesHorario.setHorizontalAlignment(SwingConstants.CENTER);
		txtJuevesHorario.setColumns(10);
		panel.add(txtJuevesHorario);
		
		txtJuevesDuracion = new JTextField();
		txtJuevesDuracion.setBounds(265, 195, 60, 20);
		txtJuevesDuracion.setHorizontalAlignment(SwingConstants.CENTER);
		txtJuevesDuracion.setColumns(10);
		panel.add(txtJuevesDuracion);
		
		JLabel lblViernes = new JLabel("Viernes");
		lblViernes.setBounds(325, 155, 60, 20);
		lblViernes.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblViernes);
		
		txtViernesHorario = new JTextField();
		txtViernesHorario.setBounds(325, 175, 60, 20);
		txtViernesHorario.setHorizontalAlignment(SwingConstants.CENTER);
		txtViernesHorario.setColumns(10);
		panel.add(txtViernesHorario);
		
		txtViernesDuracion = new JTextField();
		txtViernesDuracion.setBounds(325, 195, 60, 20);
		txtViernesDuracion.setHorizontalAlignment(SwingConstants.CENTER);
		txtViernesDuracion.setColumns(10);
		panel.add(txtViernesDuracion);
		
		JLabel lblSabado = new JLabel("Sábado");
		lblSabado.setBounds(385, 155, 60, 20);
		lblSabado.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblSabado);
		
		txtSabadoHorario = new JTextField();
		txtSabadoHorario.setBounds(385, 175, 60, 20);
		txtSabadoHorario.setHorizontalAlignment(SwingConstants.CENTER);
		txtSabadoHorario.setColumns(10);
		panel.add(txtSabadoHorario);
		
		txtSabadoDuracion = new JTextField();
		txtSabadoDuracion.setBounds(385, 195, 60, 20);
		txtSabadoDuracion.setHorizontalAlignment(SwingConstants.CENTER);
		txtSabadoDuracion.setColumns(10);
		panel.add(txtSabadoDuracion);
		
		lblMensageError = new JLabel("");
		lblMensageError.setBounds(25, 225, 420, 20);
		lblMensageError.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblMensageError);
		
		JLabel lblCuota = new JLabel("Valor cuota:");
		lblCuota.setBounds(25, 115, 70, 20);
		panel.add(lblCuota);
		
		txtCuota = new JTextField();
		txtCuota.setBounds(95, 115, 120, 20);
		panel.add(txtCuota);
		txtCuota.setColumns(10);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(236, 260, 90, 23);
		panel.add(btnGuardar);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(356, 260, 90, 23);
		panel.add(btnVolver);
	}
}