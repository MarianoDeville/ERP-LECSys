package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import dao.OperadorSistema;
import interfaceUsuario.InterfaceBotones;
import interfaceUsuario.Principal;
import modelo.DtosActividad;

public class CtrlPrincipal implements ActionListener {
	
	private Principal ventanaPrincipal;
	private OperadorSistema identificacion;
	private DtosActividad actividad;

	public CtrlPrincipal(Principal vista) {
		
		this.ventanaPrincipal = vista;
		this.identificacion = new OperadorSistema();
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
		
		actividad.registrarActividad("Inicio del sistema", "Principal");
		ventanaPrincipal.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == ventanaPrincipal.btnAdmin) {
		
			if(identificacion.getNivelAcceso() < 60) {

				InterfaceBotones ventanaAdministracion = new InterfaceBotones("Gesti�n administrativa");
				CtrlAdministracion ctrlAdmin = new CtrlAdministracion(ventanaAdministracion);
				ctrlAdmin.iniciar();
			} else {
				
				JOptionPane.showMessageDialog(null, "No tiene nivel de acceso suficiente.");
				actividad.registrarActividad("Nivel de acceso insuficiente", "Gesti�n administrativa");
			}
		}
		
		if(e.getSource() == ventanaPrincipal.btnAlumnos) {

			if(identificacion.getNivelAcceso() < 60) {
				
				InterfaceBotones ventanaAlumnos = new InterfaceBotones("Gesti�n de los alumnos");
				CtrlAlumnos ctrlAlumn = new CtrlAlumnos(ventanaAlumnos);
				ctrlAlumn.iniciar();
			} else {
				
				JOptionPane.showMessageDialog(null, "No tiene nivel de acceso suficiente.");
				actividad.registrarActividad("Nivel de acceso insuficiente", "Gesti�n de los alumnos");
			}
		}
		
		if(e.getSource() == ventanaPrincipal.btnPersonal) {

			if(identificacion.getNivelAcceso() < 60) {
				
				InterfaceBotones ventanaPersonal = new InterfaceBotones("Gesti�n del personal");
				CtrlEmpleados ctrlAdmin = new CtrlEmpleados(ventanaPersonal);
				ctrlAdmin.iniciar();
			} else {
				
				JOptionPane.showMessageDialog(null, "No tiene nivel de acceso suficiente.");
				actividad.registrarActividad("Nivel de acceso insuficiente", "Gesti�n del personal");
			}
		}
		
		if(e.getSource() == ventanaPrincipal.btnCursos) {

			if(identificacion.getNivelAcceso() < 60) {
				
				InterfaceBotones ventanaCursos = new InterfaceBotones("Gesti�n de los cursos");
				CtrlCursos ctrlCursos = new CtrlCursos(ventanaCursos);
				ctrlCursos.iniciar();
			} else {
				
				JOptionPane.showMessageDialog(null, "No tiene nivel de acceso suficiente.");
				actividad.registrarActividad("Nivel de acceso insuficiente", "Gesti�n del personal");
			}
		}
		
		if(e.getSource() == ventanaPrincipal.btnConfig) {	
			
			if(identificacion.getNivelAcceso() < 11) {

				InterfaceBotones ventanaConfiguracion = new InterfaceBotones("Configuraci�n del sistema");
				CtrlConfiguracion ctrlConfig = new CtrlConfiguracion(ventanaConfiguracion);
				ctrlConfig.iniciar();
			} else {
				
				JOptionPane.showMessageDialog(null, "No tiene nivel de acceso suficiente.");
				actividad.registrarActividad("Nivel de acceso insuficiente", "Configuraci�n del sistema");
			}
			
		}
	
		if(e.getSource() == ventanaPrincipal.btnRelogin) {
			
			
		}
		
		if(e.getSource() == ventanaPrincipal.btnSalir) {
			
			actividad.registrarActividad("Cierre del sistema", "Principal");
			System.exit(0);
		}
		
	}
}
