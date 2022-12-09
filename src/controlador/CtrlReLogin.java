package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import dao.OperadorSistema;
import interfaceUsuario.IngresoUsuario;

public class CtrlReLogin implements ActionListener {
	
	private IngresoUsuario ventanaLogin;
	private OperadorSistema comprobarLogin;
	
	public CtrlReLogin(IngresoUsuario vista) {
		
		this.ventanaLogin = vista;
		this.ventanaLogin.btnCancelar.addActionListener(this);
		this.ventanaLogin.btnOk.addActionListener(this);
		this.ventanaLogin.txtUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				if (e.getKeyCode() >= 32)
					ventanaLogin.txtError.setText("");
			}
		});
		this.ventanaLogin.txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				if (e.getKeyCode() >= 32)
					ventanaLogin.txtError.setText("");
			}
		});
	}

	public void iniciar() {
		
		ventanaLogin.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == ventanaLogin.btnCancelar) {
			
			ventanaLogin.dispose();
		}
		
		if(e.getSource() == ventanaLogin.btnOk) {
			
			if(ventanaLogin.txtUsuario.getText().equals("")) {
				
				ventanaLogin.txtError.setText("El campo nombre se usuario no puede estar vacío.");
			} else {
				
				comprobarLogin = new OperadorSistema();
				comprobarLogin.setNombre(ventanaLogin.txtUsuario.getText());
				comprobarLogin.setPass(ventanaLogin.txtPassword);
				
				if(!comprobarLogin.checkUsuario(ventanaLogin)) {
					
					ventanaLogin.txtPassword.setText("");
					ventanaLogin.txtError.setText("Usuario o contraseña incorrectos.");
				} else {
	
					ventanaLogin.dispose();
				}
			}
		}
	}
}