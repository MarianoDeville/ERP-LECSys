package interfaceUsuario;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class Principal extends VentanaModelo{

	private static final long serialVersionUID = 1L;
	private JPanel principal;
	public JButton btnAdmin;
	public JButton btnAlumnos;
	public JButton btnPersonal;
	public JButton btnConfig;
	public JButton btnRelogin;
	public JButton btnSalir;
	
	public Principal(String nombreVentana) {
		
		super(nombreVentana);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		principal = new JPanel();
		setContentPane(principal);
		SpringLayout sl_contentPane = new SpringLayout();
		principal.setLayout(sl_contentPane);
		
		JLabel lblAdministracion = new JLabel("Administración");
		lblAdministracion.setHorizontalAlignment(SwingConstants.CENTER);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblAdministracion, 100, SpringLayout.NORTH, principal);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblAdministracion, 75, SpringLayout.WEST, principal);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblAdministracion, 104, SpringLayout.WEST, lblAdministracion);
		principal.add(lblAdministracion);
		
		btnAdmin = new JButton("");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnAdmin, 15, SpringLayout.NORTH, lblAdministracion);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnAdmin, 0, SpringLayout.WEST, lblAdministracion);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnAdmin, 94, SpringLayout.NORTH, btnAdmin);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnAdmin, 104, SpringLayout.WEST, btnAdmin);
		btnAdmin.setIcon(new ImageIcon("C:\\LECSys 2.0\\Imagenes\\Admin.png"));
		principal.add(btnAdmin);
		
		JLabel lblAlumnos = new JLabel("Alumnos");
		lblAlumnos.setHorizontalAlignment(SwingConstants.CENTER);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblAlumnos, 100, SpringLayout.NORTH, principal);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblAlumnos, 75, SpringLayout.EAST, lblAdministracion);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblAlumnos, 104, SpringLayout.WEST, lblAlumnos);
		principal.add(lblAlumnos);
		
		btnAlumnos = new JButton("");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnAlumnos, 15, SpringLayout.NORTH, lblAlumnos);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnAlumnos, 0, SpringLayout.WEST, lblAlumnos);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnAlumnos, 94, SpringLayout.NORTH, btnAlumnos);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnAlumnos, 104, SpringLayout.WEST, btnAlumnos);
		btnAlumnos.setIcon(new ImageIcon("C:\\LECSys 2.0\\Imagenes\\Alumno.png"));
		principal.add(btnAlumnos);
		
		JLabel lblPersonal = new JLabel("Personal");
		lblPersonal.setHorizontalAlignment(SwingConstants.CENTER);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblPersonal, 100, SpringLayout.NORTH, principal);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblPersonal, 75, SpringLayout.EAST, lblAlumnos);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblPersonal, 104, SpringLayout.WEST, lblPersonal);
		principal.add(lblPersonal);
		
		btnPersonal = new JButton("");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnPersonal, 15, SpringLayout.NORTH, lblPersonal);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnPersonal, 0, SpringLayout.WEST, lblPersonal);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnPersonal, 94, SpringLayout.NORTH, btnPersonal);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnPersonal, 104, SpringLayout.WEST, btnPersonal);
		btnPersonal.setIcon(new ImageIcon("C:\\LECSys 2.0\\Imagenes\\Personal.png"));
		principal.add(btnPersonal);
		
		JLabel lblConfig = new JLabel("Configuración");
		lblConfig.setHorizontalAlignment(SwingConstants.CENTER);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblConfig, -100, SpringLayout.SOUTH, principal);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblConfig, 50, SpringLayout.WEST, principal);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblConfig, 84, SpringLayout.WEST, lblConfig);
		principal.add(lblConfig);
		
		btnConfig = new JButton("");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnConfig, 15, SpringLayout.NORTH, lblConfig);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnConfig, 4, SpringLayout.WEST, lblConfig);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnConfig, 69, SpringLayout.NORTH, btnConfig);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnConfig, 76, SpringLayout.WEST, btnConfig);
		btnConfig.setIcon(new ImageIcon("C:\\LECSys 2.0\\Imagenes\\Configuracion1.png"));
		principal.add(btnConfig);
		
		JLabel lblRelogin = new JLabel("Usuario");
		lblRelogin.setHorizontalAlignment(SwingConstants.CENTER);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblRelogin, 0, SpringLayout.SOUTH, lblConfig);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblRelogin, 50, SpringLayout.EAST, lblConfig);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblRelogin, 76, SpringLayout.WEST, lblRelogin);
		principal.add(lblRelogin);
		
		btnRelogin = new JButton("");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnRelogin, 15, SpringLayout.NORTH, lblRelogin);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnRelogin, 0, SpringLayout.WEST, lblRelogin);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnRelogin, 69, SpringLayout.NORTH, btnRelogin);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnRelogin, 76, SpringLayout.WEST, btnRelogin);
		btnRelogin.setIcon(new ImageIcon("C:\\LECSys 2.0\\Imagenes\\Relogin.png"));
		principal.add(btnRelogin);
		
		JLabel lblSalir = new JLabel("Salir");
		lblSalir.setHorizontalAlignment(SwingConstants.CENTER);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblSalir, 0, SpringLayout.SOUTH, lblRelogin);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblSalir, -60, SpringLayout.EAST, principal);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblSalir, -76, SpringLayout.EAST, lblSalir);
		principal.add(lblSalir);
		
		btnSalir = new JButton("");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnSalir, 15, SpringLayout.NORTH, lblSalir);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnSalir, 0, SpringLayout.WEST, lblSalir);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnSalir, 69, SpringLayout.NORTH, btnSalir);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnSalir, 76, SpringLayout.WEST, btnSalir);
		btnSalir.setIcon(new ImageIcon("C:\\LECSys 2.0\\Imagenes\\Salir.png"));
		principal.add(btnSalir);
	}
}