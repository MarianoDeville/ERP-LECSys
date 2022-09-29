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
	public JButton btnCursos;
	public JButton btnConfig;
	public JButton btnRelogin;
	public JButton btnSalir;
	
	public Principal(String nombreVentana) {
		
		super(nombreVentana);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		principal = new JPanel();
		setContentPane(principal);
		SpringLayout contenedor = new SpringLayout();
		principal.setLayout(contenedor);
		
		JLabel lblAdministracion = new JLabel("Administración");
		lblAdministracion.setHorizontalAlignment(SwingConstants.CENTER);
		contenedor.putConstraint(SpringLayout.NORTH, lblAdministracion, 100, SpringLayout.NORTH, principal);
		contenedor.putConstraint(SpringLayout.WEST, lblAdministracion, 75, SpringLayout.WEST, principal);
		contenedor.putConstraint(SpringLayout.EAST, lblAdministracion, 104, SpringLayout.WEST, lblAdministracion);
		principal.add(lblAdministracion);
		
		btnAdmin = new JButton("");
		contenedor.putConstraint(SpringLayout.NORTH, btnAdmin, 15, SpringLayout.NORTH, lblAdministracion);
		contenedor.putConstraint(SpringLayout.WEST, btnAdmin, 0, SpringLayout.WEST, lblAdministracion);
		contenedor.putConstraint(SpringLayout.SOUTH, btnAdmin, 94, SpringLayout.NORTH, btnAdmin);
		contenedor.putConstraint(SpringLayout.EAST, btnAdmin, 104, SpringLayout.WEST, btnAdmin);
		btnAdmin.setIcon(new ImageIcon("C:\\LECSys 2.0\\Imagenes\\Admin.png"));
		principal.add(btnAdmin);
		
		JLabel lblAlumnos = new JLabel("Alumnos");
		lblAlumnos.setHorizontalAlignment(SwingConstants.CENTER);
		contenedor.putConstraint(SpringLayout.NORTH, lblAlumnos, 100, SpringLayout.NORTH, principal);
		contenedor.putConstraint(SpringLayout.WEST, lblAlumnos, 75, SpringLayout.EAST, lblAdministracion);
		contenedor.putConstraint(SpringLayout.EAST, lblAlumnos, 104, SpringLayout.WEST, lblAlumnos);
		principal.add(lblAlumnos);
		
		btnAlumnos = new JButton("");
		contenedor.putConstraint(SpringLayout.NORTH, btnAlumnos, 15, SpringLayout.NORTH, lblAlumnos);
		contenedor.putConstraint(SpringLayout.WEST, btnAlumnos, 0, SpringLayout.WEST, lblAlumnos);
		contenedor.putConstraint(SpringLayout.SOUTH, btnAlumnos, 94, SpringLayout.NORTH, btnAlumnos);
		contenedor.putConstraint(SpringLayout.EAST, btnAlumnos, 104, SpringLayout.WEST, btnAlumnos);
		btnAlumnos.setIcon(new ImageIcon("C:\\LECSys 2.0\\Imagenes\\Alumno.png"));
		principal.add(btnAlumnos);
		
		JLabel lblPersonal = new JLabel("Personal");
		lblPersonal.setHorizontalAlignment(SwingConstants.CENTER);
		contenedor.putConstraint(SpringLayout.NORTH, lblPersonal, 100, SpringLayout.NORTH, principal);
		contenedor.putConstraint(SpringLayout.WEST, lblPersonal, 75, SpringLayout.EAST, lblAlumnos);
		contenedor.putConstraint(SpringLayout.EAST, lblPersonal, 104, SpringLayout.WEST, lblPersonal);
		principal.add(lblPersonal);
		
		btnPersonal = new JButton("");
		contenedor.putConstraint(SpringLayout.NORTH, btnPersonal, 15, SpringLayout.NORTH, lblPersonal);
		contenedor.putConstraint(SpringLayout.WEST, btnPersonal, 0, SpringLayout.WEST, lblPersonal);
		contenedor.putConstraint(SpringLayout.SOUTH, btnPersonal, 94, SpringLayout.NORTH, btnPersonal);
		contenedor.putConstraint(SpringLayout.EAST, btnPersonal, 104, SpringLayout.WEST, btnPersonal);
		btnPersonal.setIcon(new ImageIcon("C:\\LECSys 2.0\\Imagenes\\Personal.png"));
		principal.add(btnPersonal);
		
		
		JLabel lblCursos = new JLabel("Cursos");
		lblCursos.setHorizontalAlignment(SwingConstants.CENTER);
		contenedor.putConstraint(SpringLayout.NORTH, lblCursos, 50, SpringLayout.SOUTH, btnAdmin);
		contenedor.putConstraint(SpringLayout.WEST, lblCursos, 75, SpringLayout.WEST, principal);
		contenedor.putConstraint(SpringLayout.EAST, lblCursos, 104, SpringLayout.WEST, lblCursos);
		principal.add(lblCursos);
		
		btnCursos = new JButton("");
		contenedor.putConstraint(SpringLayout.NORTH, btnCursos, 15, SpringLayout.NORTH, lblCursos);
		contenedor.putConstraint(SpringLayout.WEST, btnCursos, 0, SpringLayout.WEST, lblCursos);
		contenedor.putConstraint(SpringLayout.SOUTH, btnCursos, 94, SpringLayout.NORTH, btnCursos);
		contenedor.putConstraint(SpringLayout.EAST, btnCursos, 104, SpringLayout.WEST, btnCursos);
		btnCursos.setIcon(new ImageIcon("C:\\LECSys 2.0\\Imagenes\\Curso.png"));
		principal.add(btnCursos);
		
		JLabel lblConfig = new JLabel("Configuración");
		lblConfig.setHorizontalAlignment(SwingConstants.CENTER);
		contenedor.putConstraint(SpringLayout.SOUTH, lblConfig, -100, SpringLayout.SOUTH, principal);
		contenedor.putConstraint(SpringLayout.WEST, lblConfig, 50, SpringLayout.WEST, principal);
		contenedor.putConstraint(SpringLayout.EAST, lblConfig, 84, SpringLayout.WEST, lblConfig);
		principal.add(lblConfig);
		
		btnConfig = new JButton("");
		contenedor.putConstraint(SpringLayout.NORTH, btnConfig, 15, SpringLayout.NORTH, lblConfig);
		contenedor.putConstraint(SpringLayout.WEST, btnConfig, 4, SpringLayout.WEST, lblConfig);
		contenedor.putConstraint(SpringLayout.SOUTH, btnConfig, 69, SpringLayout.NORTH, btnConfig);
		contenedor.putConstraint(SpringLayout.EAST, btnConfig, 76, SpringLayout.WEST, btnConfig);
		btnConfig.setIcon(new ImageIcon("C:\\LECSys 2.0\\Imagenes\\Configuracion1.png"));
		principal.add(btnConfig);
		
		JLabel lblRelogin = new JLabel("Usuario");
		lblRelogin.setHorizontalAlignment(SwingConstants.CENTER);
		contenedor.putConstraint(SpringLayout.SOUTH, lblRelogin, 0, SpringLayout.SOUTH, lblConfig);
		contenedor.putConstraint(SpringLayout.WEST, lblRelogin, 50, SpringLayout.EAST, lblConfig);
		contenedor.putConstraint(SpringLayout.EAST, lblRelogin, 76, SpringLayout.WEST, lblRelogin);
		principal.add(lblRelogin);
		
		btnRelogin = new JButton("");
		contenedor.putConstraint(SpringLayout.NORTH, btnRelogin, 15, SpringLayout.NORTH, lblRelogin);
		contenedor.putConstraint(SpringLayout.WEST, btnRelogin, 0, SpringLayout.WEST, lblRelogin);
		contenedor.putConstraint(SpringLayout.SOUTH, btnRelogin, 69, SpringLayout.NORTH, btnRelogin);
		contenedor.putConstraint(SpringLayout.EAST, btnRelogin, 76, SpringLayout.WEST, btnRelogin);
		btnRelogin.setIcon(new ImageIcon("C:\\LECSys 2.0\\Imagenes\\Relogin.png"));
		principal.add(btnRelogin);
		
		JLabel lblSalir = new JLabel("Salir");
		lblSalir.setHorizontalAlignment(SwingConstants.CENTER);
		contenedor.putConstraint(SpringLayout.SOUTH, lblSalir, 0, SpringLayout.SOUTH, lblRelogin);
		contenedor.putConstraint(SpringLayout.EAST, lblSalir, -60, SpringLayout.EAST, principal);
		contenedor.putConstraint(SpringLayout.WEST, lblSalir, -76, SpringLayout.EAST, lblSalir);
		principal.add(lblSalir);
		
		btnSalir = new JButton("");
		contenedor.putConstraint(SpringLayout.NORTH, btnSalir, 15, SpringLayout.NORTH, lblSalir);
		contenedor.putConstraint(SpringLayout.WEST, btnSalir, 0, SpringLayout.WEST, lblSalir);
		contenedor.putConstraint(SpringLayout.SOUTH, btnSalir, 69, SpringLayout.NORTH, btnSalir);
		contenedor.putConstraint(SpringLayout.EAST, btnSalir, 76, SpringLayout.WEST, btnSalir);
		btnSalir.setIcon(new ImageIcon("C:\\LECSys 2.0\\Imagenes\\Salir.png"));
		principal.add(btnSalir);
	}
}