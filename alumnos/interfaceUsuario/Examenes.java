package interfaceUsuario;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;

public class Examenes extends VentanaModelo {

	private static final long serialVersionUID = 1L;
	private JPanel asistencia;
	private JScrollPane scrollTabla;
	public JTable tablaAsistencia;
	public JButton btnImprimir;
	public JButton btnVolver;
	public JComboBox<String> comboBoxCurso;
	
	public Examenes(String nombreVentana) {
		
		super(nombreVentana);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		asistencia = new JPanel();
		setContentPane(asistencia);
		SpringLayout sl_contentPane = new SpringLayout();
		asistencia.setLayout(sl_contentPane);
		
		JLabel lblCurso = new JLabel("Curso:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblCurso, 15, SpringLayout.NORTH, asistencia);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblCurso, 15, SpringLayout.WEST, asistencia);
		asistencia.add(lblCurso);
		
		comboBoxCurso = new JComboBox<String>();
		sl_contentPane.putConstraint(SpringLayout.NORTH, comboBoxCurso, 15, SpringLayout.NORTH, asistencia);
		sl_contentPane.putConstraint(SpringLayout.WEST, comboBoxCurso, 5, SpringLayout.EAST, lblCurso);
		sl_contentPane.putConstraint(SpringLayout.EAST, comboBoxCurso, 150, SpringLayout.WEST, comboBoxCurso);
		asistencia.add(comboBoxCurso);
		
		scrollTabla = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollTabla, 65, SpringLayout.NORTH, asistencia);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollTabla, 15, SpringLayout.WEST, asistencia);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollTabla, -10, SpringLayout.SOUTH, asistencia);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollTabla, -120, SpringLayout.EAST, asistencia);
		asistencia.add(scrollTabla);
		tablaAsistencia = new JTable();
		scrollTabla.setViewportView(tablaAsistencia);
		
		btnImprimir = new JButton("Imprimir");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnImprimir, 65, SpringLayout.NORTH, asistencia);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnImprimir, -10, SpringLayout.EAST, asistencia);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnImprimir, -90, SpringLayout.EAST, btnImprimir);
		asistencia.add(btnImprimir);
		
		btnVolver = new JButton("Volver");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnVolver, -10, SpringLayout.SOUTH, asistencia);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnVolver, -10, SpringLayout.EAST, asistencia);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnVolver, -85, SpringLayout.EAST, btnVolver);
		asistencia.add(btnVolver);
	}
}
