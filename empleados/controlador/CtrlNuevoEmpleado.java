package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import interfaceUsuario.Nuevo;
import modelo.DtosNuevoEmpleado;

public class CtrlNuevoEmpleado implements ActionListener {

	private Nuevo ventanaNuevoEmplado;
	private DtosNuevoEmpleado dtosNuevoEmpleado;
	
	public CtrlNuevoEmpleado(Nuevo vista) {
		
		this.ventanaNuevoEmplado = vista;
		this.dtosNuevoEmpleado = new DtosNuevoEmpleado();
		this.ventanaNuevoEmplado.btnGuardar.addActionListener(this);
		this.ventanaNuevoEmplado.btnVolver.addActionListener(this);
	}
	
	public void iniciar() {
		
		ventanaNuevoEmplado.lblcomboBox1.setText("Sector:");
		ventanaNuevoEmplado.comboBox1.setModel(new DefaultComboBoxModel<String>(dtosNuevoEmpleado.getSectores()));
		ventanaNuevoEmplado.lblcomboBox2.setText("Relación:");
		ventanaNuevoEmplado.comboBox2.setModel(new DefaultComboBoxModel<String>(dtosNuevoEmpleado.getTipo()));
		ventanaNuevoEmplado.lblcomboBox2.setVisible(true);
		ventanaNuevoEmplado.comboBox2.setVisible(true);
		ventanaNuevoEmplado.lblTxt1.setText("Cargo:");
		ventanaNuevoEmplado.lblTxt1.setVisible(true);
		ventanaNuevoEmplado.txt1.setVisible(true);
		ventanaNuevoEmplado.lblTxt2.setText("Salario:");
		ventanaNuevoEmplado.lblTxt2.setVisible(true);
		ventanaNuevoEmplado.txt2.setVisible(true);
		ventanaNuevoEmplado.setVisible(true);
	}
	
	private void limpiarCampos() {
		
		ventanaNuevoEmplado.txtNombre.setText("");
		ventanaNuevoEmplado.txtApellido.setText("");
		ventanaNuevoEmplado.txtDNI.setText("");
		ventanaNuevoEmplado.txtAño.setText(""); 
		ventanaNuevoEmplado.txtMes.setText(""); 
		ventanaNuevoEmplado.txtDia.setText("");
		ventanaNuevoEmplado.txtDireccion.setText("");
		ventanaNuevoEmplado.txtEmail.setText("");
		ventanaNuevoEmplado.txtTelefono.setText("");
		ventanaNuevoEmplado.txt1.setText("");
		ventanaNuevoEmplado.txt2.setText("");
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ventanaNuevoEmplado.btnGuardar) {
			
			dtosNuevoEmpleado.setNombre(ventanaNuevoEmplado.txtNombre.getText());
			dtosNuevoEmpleado.setApellido(ventanaNuevoEmplado.txtApellido.getText());
			dtosNuevoEmpleado.setDni(ventanaNuevoEmplado.txtDNI.getText());
			dtosNuevoEmpleado.setFechaNacimientoAño(ventanaNuevoEmplado.txtAño.getText()); 
			dtosNuevoEmpleado.setFechaNacimientoMes(ventanaNuevoEmplado.txtMes.getText()); 
			dtosNuevoEmpleado.setFechaNacimientoDia(ventanaNuevoEmplado.txtDia.getText());
			dtosNuevoEmpleado.setDireccion(ventanaNuevoEmplado.txtDireccion.getText());
			dtosNuevoEmpleado.setEmail(ventanaNuevoEmplado.txtEmail.getText());
			dtosNuevoEmpleado.setTelefono(ventanaNuevoEmplado.txtTelefono.getText());
			dtosNuevoEmpleado.setSector((String) ventanaNuevoEmplado.comboBox1.getSelectedItem());
			dtosNuevoEmpleado.setRelacion((String) ventanaNuevoEmplado.comboBox2.getSelectedItem());
			dtosNuevoEmpleado.setCargo(ventanaNuevoEmplado.txt1.getText());
			dtosNuevoEmpleado.setSalario(ventanaNuevoEmplado.txt2.getText());
			String msgError = dtosNuevoEmpleado.checkInformacion(); 
			ventanaNuevoEmplado.lblMsgError.setForeground(Color.RED);
			ventanaNuevoEmplado.lblMsgError.setText(msgError);
			
			if(msgError.contentEquals("")) {
				
				if(dtosNuevoEmpleado.setNuevoEmpleado()) {
					
					ventanaNuevoEmplado.lblMsgError.setForeground(Color.BLUE);
					limpiarCampos();
					ventanaNuevoEmplado.lblMsgError.setText("Registro guardado con éxito.");
				}
			}
		}

		if(e.getSource() == ventanaNuevoEmplado.btnVolver) {
			
			ventanaNuevoEmplado.dispose();
		}
	}
}