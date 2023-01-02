package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaceUsuario.NuevoUsuario;
import modelo.DtosConfiguracion;

public class CtrlEditarEmail implements ActionListener {

	private NuevoUsuario ventanaEmail;
	private DtosConfiguracion dtosEmail;

	public CtrlEditarEmail(NuevoUsuario vista) {
		
		this.ventanaEmail = vista;
		this.dtosEmail = new DtosConfiguracion();
		this.ventanaEmail.btnGuardar.addActionListener(this);
		this.ventanaEmail.btnVolver.addActionListener(this);
	}
	
	public void iniciar() {
		
		ventanaEmail.btnBorrar.setVisible(false);
		ventanaEmail.comboBox1.setVisible(false);
		ventanaEmail.comboBox2.setVisible(false);
		ventanaEmail.lblcomboBox1.setVisible(false);
		ventanaEmail.lblcomboBox2.setVisible(false);
		ventanaEmail.lblNombre.setText("Email: ");
		ventanaEmail.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ventanaEmail.btnVolver) {
			
			ventanaEmail.dispose();
		}
		
		if(e.getSource() == ventanaEmail.btnGuardar) {

			String msg = dtosEmail.setEmail(ventanaEmail.txtUsuario.getText(),
											ventanaEmail.txtContraseña.getPassword(),
											ventanaEmail.txtReContraseña.getPassword());
			if(msg.equals("")) {
				
				ventanaEmail.lblMsgError.setForeground(Color.BLUE);
				ventanaEmail.lblMsgError.setText("Se guardó correctamente la configuración del email.");
			} else {
				
				ventanaEmail.lblMsgError.setForeground(Color.RED);
				ventanaEmail.lblMsgError.setText(msg); 
			}
		}
	}
}