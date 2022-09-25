package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import interfaceUsuario.InterfaceBotones;
import interfaceUsuario.Principal;

public class CtrlPrincipal implements ActionListener {
	
	private Principal ventanaPrincipal;

	public CtrlPrincipal(Principal vista) {
		
		this.ventanaPrincipal = vista;
		this.ventanaPrincipal.btnAdmin.addActionListener(this);
		this.ventanaPrincipal.btnAlumnos.addActionListener(this);
		this.ventanaPrincipal.btnPersonal.addActionListener(this);
		this.ventanaPrincipal.btnConfig.addActionListener(this);
		this.ventanaPrincipal.btnRelogin.addActionListener(this);
		this.ventanaPrincipal.btnSalir.addActionListener(this);
	}
	
	public void iniciar() {
		
		ventanaPrincipal.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == ventanaPrincipal.btnAdmin) {
			///////////////////////////  antes de abrir debo controlar el nivel de permiso que tiene.
			InterfaceBotones ventanaAdministracion = new InterfaceBotones("Gestión administrativa");
			CtrlAdministracion ctrlAdmin = new CtrlAdministracion(ventanaAdministracion);
			ctrlAdmin.iniciar();
		}
		
		if(e.getSource() == ventanaPrincipal.btnAlumnos) {
			///////////////////////////  antes de abrir debo controlar el nivel de permiso que tiene.
			InterfaceBotones ventanaAlumnos = new InterfaceBotones("Gestión de los alumnos");
			CtrlAlumnos ctrlAlumn = new CtrlAlumnos(ventanaAlumnos);
			ctrlAlumn.iniciar();
		}
		
		if(e.getSource() == ventanaPrincipal.btnPersonal) {
			///////////////////////////  antes de abrir debo controlar el nivel de permiso que tiene.
			InterfaceBotones ventanaPersonal = new InterfaceBotones("Gestión del personal");
			CtrlPersonal ctrlAdmin = new CtrlPersonal(ventanaPersonal);
			ctrlAdmin.iniciar();
		}
		
		if(e.getSource() == ventanaPrincipal.btnConfig) {
			///////////////////////////  antes de abrir debo controlar el nivel de permiso que tiene.
			InterfaceBotones ventanaConfiguracion = new InterfaceBotones("Configuración del sistema");
			CtrlConfiguracion ctrlConfig = new CtrlConfiguracion(ventanaConfiguracion);
			ctrlConfig.iniciar();
		}
		
		if(e.getSource() == ventanaPrincipal.btnAdmin) {
			///////////////////////////  antes de abrir debo controlar el nivel de permiso que tiene.

		}
		
		if(e.getSource() == ventanaPrincipal.btnSalir) {
			
			System.exit(0);
		}
		
	}
}
