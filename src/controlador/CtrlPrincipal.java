package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import interfaceUsuario.InterfaceBotones;
import interfaceUsuario.Principal;
import objetoAccesoDatos.OperadorSistema;

public class CtrlPrincipal implements ActionListener {
	
	private Principal ventanaPrincipal;
	private OperadorSistema identificacion;

	public CtrlPrincipal(Principal vista) {
		
		this.ventanaPrincipal = vista;
		this.identificacion = new OperadorSistema();
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
		
			if(identificacion.getNivelAcceso() < 60) {

				InterfaceBotones ventanaAdministracion = new InterfaceBotones("Gestión administrativa");
				CtrlAdministracion ctrlAdmin = new CtrlAdministracion(ventanaAdministracion);
				ctrlAdmin.iniciar();
			} else {
				
				JOptionPane.showMessageDialog(null, "No tiene nivel de acceso suficiente.");
			}
		}
		
		if(e.getSource() == ventanaPrincipal.btnAlumnos) {

			if(identificacion.getNivelAcceso() < 60) {
				
				InterfaceBotones ventanaAlumnos = new InterfaceBotones("Gestión de los alumnos");
				CtrlAlumnos ctrlAlumn = new CtrlAlumnos(ventanaAlumnos);
				ctrlAlumn.iniciar();
			} else {
				
				JOptionPane.showMessageDialog(null, "No tiene nivel de acceso suficiente.");
			}
		}
		
		if(e.getSource() == ventanaPrincipal.btnPersonal) {

			if(identificacion.getNivelAcceso() < 60) {
				
				InterfaceBotones ventanaPersonal = new InterfaceBotones("Gestión del personal");
				CtrlPersonal ctrlAdmin = new CtrlPersonal(ventanaPersonal);
				ctrlAdmin.iniciar();
			} else {
				
				JOptionPane.showMessageDialog(null, "No tiene nivel de acceso suficiente.");
			}
		}
		
		if(e.getSource() == ventanaPrincipal.btnConfig) {	
			
			if(identificacion.getNivelAcceso() < 11) {

				InterfaceBotones ventanaConfiguracion = new InterfaceBotones("Configuración del sistema");
				CtrlConfiguracion ctrlConfig = new CtrlConfiguracion(ventanaConfiguracion);
				ctrlConfig.iniciar();
			} else {
				
				JOptionPane.showMessageDialog(null, "No tiene nivel de acceso suficiente.");
			}
			
		}
	
		if(e.getSource() == ventanaPrincipal.btnRelogin) {
			
			
		}
		
		if(e.getSource() == ventanaPrincipal.btnSalir) {
			
			System.exit(0);
		}
		
	}
}
