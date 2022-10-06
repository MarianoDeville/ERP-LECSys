package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import interfaceUsuario.NuevoUsuario;
import modelo.DtosUsuarios;

public class CtrlNuevoUsuario implements ActionListener {

	private  NuevoUsuario ventanaNuevoUsuario;
	private DtosUsuarios dtosUsuarios;

	public CtrlNuevoUsuario(NuevoUsuario vista) {
		
		this.ventanaNuevoUsuario = vista;
		this.dtosUsuarios = new DtosUsuarios();
		this.ventanaNuevoUsuario.btnGuardar.addActionListener(this);
		this.ventanaNuevoUsuario.btnVolver.addActionListener(this);
	}
	
	public void iniciar() {
		
		ventanaNuevoUsuario.comboBox1.setModel(new DefaultComboBoxModel<String>(dtosUsuarios.getListaNivelAcceso()));
		ventanaNuevoUsuario.comboBox2.setModel(new DefaultComboBoxModel<String>(dtosUsuarios.getListaEmpleados()));
		ventanaNuevoUsuario.setVisible(true);
	}
	
	private void limpiarCampos() {
		
		ventanaNuevoUsuario.txtUsuario.setText("");
		ventanaNuevoUsuario.txtContrase�a.setText("");
		ventanaNuevoUsuario.txtReContrase�a.setText("");
	}
	
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ventanaNuevoUsuario.btnGuardar) {
			
			dtosUsuarios.setUsuario(ventanaNuevoUsuario.txtUsuario.getText());
			dtosUsuarios.setContrase�a(ventanaNuevoUsuario.txtContrase�a.getPassword());
			dtosUsuarios.setReContrase�a(ventanaNuevoUsuario.txtReContrase�a.getPassword());
			dtosUsuarios.setIdEmpleado(ventanaNuevoUsuario.comboBox2.getSelectedIndex());
			dtosUsuarios.setNivelAcceso(ventanaNuevoUsuario.comboBox1.getSelectedIndex());
			String msgError = dtosUsuarios.checkInformacion(true);
			ventanaNuevoUsuario.lblMsgError.setForeground(Color.RED);
			ventanaNuevoUsuario.lblMsgError.setText(msgError);
			
			if(msgError.contentEquals("")) {

				if(dtosUsuarios.setNuevoUsuario()) {
					
					limpiarCampos();
					ventanaNuevoUsuario.lblMsgError.setForeground(Color.BLUE);
					ventanaNuevoUsuario.lblMsgError.setText("Los datos se guardaron correctamente.");
				} else {
					
					ventanaNuevoUsuario.lblMsgError.setForeground(Color.RED);
					ventanaNuevoUsuario.lblMsgError.setText("Error al intentar guardar la informaci�n.");
				}
			}
		}
		
		if(e.getSource() == ventanaNuevoUsuario.btnVolver) {
			
			ventanaNuevoUsuario.dispose();
		}
	}
}

