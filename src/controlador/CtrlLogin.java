package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dao.OperadorSistema;
import interfaceUsuario.IngresoUsuario;
import interfaceUsuario.Principal;

public class CtrlLogin implements ActionListener {
	
	private IngresoUsuario ventanaLogin;
	private OperadorSistema comprobarLogin;
	
	public CtrlLogin(IngresoUsuario vista) {
		
		this.ventanaLogin = vista;
		this.ventanaLogin.btnCancelar.addActionListener(this);
		this.ventanaLogin.btnOk.addActionListener(this);
	}

	public void iniciar() {
		
		ventanaLogin.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == ventanaLogin.btnCancelar) {
			
			System.exit(0);
		}
		
		if(e.getSource() == ventanaLogin.btnOk) {
			
			if(IngresoUsuario.txtUsuario.getText().contentEquals("")) {
				
				ventanaLogin.txtError.setText("Ninguno de los campos debe estar vacío.");
			} else {
				
				comprobarLogin = new OperadorSistema();
				if(!comprobarLogin.checkUsuario(ventanaLogin)) {
					
					IngresoUsuario.txtPassword.setText("");
					ventanaLogin.txtError.setText("Usuario o contraseña incorrectos.");
				} else {
	
					ventanaLogin.dispose();
					Principal ventanaPrincipal = new Principal("Sistema de gestión - LECSys");
					CtrlPrincipal ctrlPrincipal = new CtrlPrincipal(ventanaPrincipal);
					ctrlPrincipal.iniciar();
				}
			}
		}
	}
}
