package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dao.OperadorSistema;
import interfaceUsuario.IngresoUsuario;
import interfaceUsuario.Principal;

public class CtrlReLogin implements ActionListener {
	
	private IngresoUsuario ventanaLogin;
	private OperadorSistema comprobarLogin;
	
	public CtrlReLogin(IngresoUsuario vista) {
		
		this.ventanaLogin = vista;
		this.ventanaLogin.btnCancelar.addActionListener(this);
		this.ventanaLogin.btnOk.addActionListener(this);
	}

	public void iniciar() {
		
		ventanaLogin.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == ventanaLogin.btnCancelar) {
			
			ventanaLogin.dispose();
		}
		
		if(e.getSource() == ventanaLogin.btnOk) {
			
			if(IngresoUsuario.txtUsuario.getText().equals("")) {
				
				ventanaLogin.txtError.setText("El campo nombre se usuario no puede estar vacío.");
			} else {
				
				comprobarLogin = new OperadorSistema();
				
				if(!comprobarLogin.checkUsuario(ventanaLogin)) {
					
					IngresoUsuario.txtPassword.setText("");
					ventanaLogin.txtError.setText("Usuario o contraseña incorrectos.");
				} else {
	
					ventanaLogin.dispose();
				}
			}
		}
	}
}