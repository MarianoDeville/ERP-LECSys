package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import dao.OperadorSistema;
import interfaceUsuario.CambioContrase�a;
import interfaceUsuario.Cumplea�os;
import interfaceUsuario.IngresoUsuario;
import interfaceUsuario.InterfaceBotones;
import interfaceUsuario.Principal;
import modelo.DtosAcceso;
import modelo.DtosActividad;

public class CtrlPrincipal implements ActionListener {
	
	private Principal ventanaPrincipal;
	private DtosAcceso controlAcceso;
	private DtosActividad actividad;
	private IngresoUsuario ventanaLogin;
	private OperadorSistema acceso;

	public CtrlPrincipal(Principal vista) {
		
		this.ventanaPrincipal = vista;
		this.controlAcceso = new DtosAcceso();
		this.actividad = new DtosActividad();
		this.ventanaPrincipal.btnAdmin.addActionListener(this);
		this.ventanaPrincipal.btnAlumnos.addActionListener(this);
		this.ventanaPrincipal.btnPersonal.addActionListener(this);
		this.ventanaPrincipal.btnCursos.addActionListener(this);
		this.ventanaPrincipal.btnConfig.addActionListener(this);
		this.ventanaPrincipal.btnRelogin.addActionListener(this);
		this.ventanaPrincipal.btnSalir.addActionListener(this);
	}
	
	public void iniciar() {
		
		JOptionPane.showMessageDialog(null, "Welcome to LECSys.\nVer.1.00\nRev. 09122022.1758");
		actividad.registrarActividad("Inicio del sistema", "Principal");
		ventanaPrincipal.setVisible(true);
		acceso = new OperadorSistema();
		
		if(acceso.getActualizarContrase�a()) {
			
			CambioContrase�a ventanaCambioPass = new CambioContrase�a();
			CtrlCambioPassword ctrlCambioPass = new CtrlCambioPassword(ventanaCambioPass);
			ctrlCambioPass.iniciar();
		}
		Cumplea�os ventanaCumplea�os = new Cumplea�os("Recordatorio de cumplea�os");
		CtrlCumplea�os ctrlCumplea�os = new CtrlCumplea�os(ventanaCumplea�os);
		ctrlCumplea�os.iniciar();
	}

	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == ventanaPrincipal.btnAdmin) {
		
			if(controlAcceso.chkAcceso("Administrativo", "Entrar")) {

				InterfaceBotones ventanaAdministracion = new InterfaceBotones("Gesti�n administrativa");
				CtrlAdministracion ctrlAdmin = new CtrlAdministracion(ventanaAdministracion);
				ctrlAdmin.iniciar();
			} else {
				
				actividad.registrarActividad("Nivel de acceso insuficiente", "Gesti�n administrativa");
			}
		}
		
		if(e.getSource() == ventanaPrincipal.btnAlumnos) {

			if(controlAcceso.chkAcceso("Alumnos", "Entrar")) {
				
				InterfaceBotones ventanaAlumnos = new InterfaceBotones("Gesti�n de los alumnos");
				CtrlAlumnos ctrlAlumn = new CtrlAlumnos(ventanaAlumnos);
				ctrlAlumn.iniciar();
			} else {
				
				actividad.registrarActividad("Nivel de acceso insuficiente", "Gesti�n de los alumnos");
			}
		}
		
		if(e.getSource() == ventanaPrincipal.btnPersonal) {

			if(controlAcceso.chkAcceso("Personal", "Entrar")) {
				
				InterfaceBotones ventanaPersonal = new InterfaceBotones("Gesti�n del personal");
				CtrlEmpleados ctrlAdmin = new CtrlEmpleados(ventanaPersonal);
				ctrlAdmin.iniciar();
			} else {
				
				actividad.registrarActividad("Nivel de acceso insuficiente", "Gesti�n del personal");
			}
		}
		
		if(e.getSource() == ventanaPrincipal.btnCursos) {

			if(controlAcceso.chkAcceso("Cursos", "Entrar")) {
				
				InterfaceBotones ventanaCursos = new InterfaceBotones("Gesti�n de los cursos");
				CtrlCursos ctrlCursos = new CtrlCursos(ventanaCursos);
				ctrlCursos.iniciar();
			} else {
				
				actividad.registrarActividad("Nivel de acceso insuficiente", "Gesti�n del personal");
			}
		}
		
		if(e.getSource() == ventanaPrincipal.btnConfig) {	
			
			if(controlAcceso.chkAcceso("Configuracion", "Entrar")) {

				InterfaceBotones ventanaConfiguracion = new InterfaceBotones("Configuraci�n del sistema");
				CtrlConfiguracion ctrlConfig = new CtrlConfiguracion(ventanaConfiguracion);
				ctrlConfig.iniciar();
			} else {
				
				actividad.registrarActividad("Nivel de acceso insuficiente", "Configuraci�n del sistema");
			}
			
		}
	
		if(e.getSource() == ventanaPrincipal.btnRelogin) {
			
			ventanaLogin = new IngresoUsuario();
			CtrlReLogin ctrlIngreso = new CtrlReLogin(ventanaLogin);
			ctrlIngreso.iniciar();
			ventanaPrincipal.setTitle("Sistema de gesti�n - LECSys - " + acceso.getNombreUsuario());
		}
		
		if(e.getSource() == ventanaPrincipal.btnSalir) {
			
			actividad.registrarActividad("Cierre del sistema", "Principal" );
			System.exit(0);
		}
	}
}
