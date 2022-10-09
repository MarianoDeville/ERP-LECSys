package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

import interfaceUsuario.ABML;
import interfaceUsuario.CambioContrase�a;
import interfaceUsuario.InterfaceBotones;
import interfaceUsuario.Listado;
import modelo.DtosAcceso;

public class CtrlConfiguracion implements ActionListener {

	private InterfaceBotones ventanaConfiguracion;
	private DtosAcceso acceso;

	public CtrlConfiguracion(InterfaceBotones vista) {
		
		this.ventanaConfiguracion = vista;
		this.acceso = new DtosAcceso();
		this.ventanaConfiguracion.btn1A.addActionListener(this);
		this.ventanaConfiguracion.btn1B.addActionListener(this);
		this.ventanaConfiguracion.btn1C.addActionListener(this);
		this.ventanaConfiguracion.btnVolver.addActionListener(this);
	}
	
	public void iniciar() {
		
		ventanaConfiguracion.lbl1A.setText("Actividad");
		ventanaConfiguracion.lbl1A.setVisible(true);
		ventanaConfiguracion.btn1A.setIcon(new ImageIcon("C:\\LECSys 1.0\\Imagenes\\Actividades.png"));
		ventanaConfiguracion.btn1A.setVisible(true);
		ventanaConfiguracion.lbl1B.setText("ABML usuarios");
		ventanaConfiguracion.lbl1B.setVisible(true);
		ventanaConfiguracion.btn1B.setIcon(new ImageIcon("C:\\LECSys 1.0\\Imagenes\\ABML.png"));
		ventanaConfiguracion.btn1B.setVisible(true);
		
		ventanaConfiguracion.lbl1C.setText("Cambiar contrase�a");
		ventanaConfiguracion.lbl1C.setVisible(true);
		ventanaConfiguracion.btn1C.setIcon(new ImageIcon("C:\\LECSys 1.0\\Imagenes\\Cambio contrase�a.png"));
		ventanaConfiguracion.btn1C.setVisible(true);
		
		ventanaConfiguracion.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ventanaConfiguracion.btn1A) {
			
			if(acceso.chkAcceso("Configuraci�n", "Listar actividad")) {
			
				Listado ventanaActividad = new Listado("Listado de actividad");
				CtrlActividad ctrlActividad = new CtrlActividad(ventanaActividad);
				ctrlActividad.iniciar();
			}
		}
		
		if(e.getSource() == ventanaConfiguracion.btn1B) {
			
			if(acceso.chkAcceso("Configuraci�n", "ABML")) {
				
				ABML ventanaUsuarios = new ABML("Alta Baja Modificaci�n de usuarios");
				CtrlABMLUsuarios ctrlABMLUsuarios = new CtrlABMLUsuarios(ventanaUsuarios);
				ctrlABMLUsuarios.iniciar();
			}
		}
		
		if(e.getSource() == ventanaConfiguracion.btn1C) {
			
			if(acceso.chkAcceso("Configuraci�n", "Cambiar contrase�a")) {
			
				CambioContrase�a ventanaCambioPass = new CambioContrase�a();
				CtrlCambioPassword ctrlCambioPass = new CtrlCambioPassword(ventanaCambioPass);
				ctrlCambioPass.iniciar();
			}
		}

		if(e.getSource() == ventanaConfiguracion.btnVolver) {
			
			ventanaConfiguracion.dispose();
		}
	}
}