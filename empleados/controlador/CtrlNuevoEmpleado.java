package controlador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import interfaceUsuario.Nuevo;
import modelo.DtosEmpleado;

public class CtrlNuevoEmpleado implements ActionListener {

	private Nuevo ventanaNuevoEmplado;
	private DtosEmpleado dtosEmpleado;
	
	public CtrlNuevoEmpleado(Nuevo vista) {
		
		this.ventanaNuevoEmplado = vista;
		this.dtosEmpleado = new DtosEmpleado();
		this.ventanaNuevoEmplado.btnGuardar.addActionListener(this);
		this.ventanaNuevoEmplado.btnVolver.addActionListener(this);
	}
	
	public void iniciar() {
		
		ventanaNuevoEmplado.lblcomboBox1.setText("Sector:");
		ventanaNuevoEmplado.comboBox1.setModel(new DefaultComboBoxModel<String>(dtosEmpleado.getListaSectores()));
		ventanaNuevoEmplado.lblcomboBox2.setText("Relación:");
		ventanaNuevoEmplado.comboBox2.setModel(new DefaultComboBoxModel<String>(dtosEmpleado.getListaTipos()));
		ventanaNuevoEmplado.lblcomboBox2.setVisible(true);
		ventanaNuevoEmplado.comboBox2.setVisible(true);
		ventanaNuevoEmplado.lblTxt1.setText("Cargo:");
		ventanaNuevoEmplado.lblTxt1.setVisible(true);
		ventanaNuevoEmplado.txt1.setVisible(true);
		ventanaNuevoEmplado.lblTxt2.setText("Salario:");
		ventanaNuevoEmplado.lblTxt2.setVisible(true);
		ventanaNuevoEmplado.txt2.setVisible(true);
		ventanaNuevoEmplado.setMinimumSize(new Dimension(450, 580));
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
			
			dtosEmpleado.setNombre(ventanaNuevoEmplado.txtNombre.getText());
			dtosEmpleado.setApellido(ventanaNuevoEmplado.txtApellido.getText());
			dtosEmpleado.setDni(ventanaNuevoEmplado.txtDNI.getText());
			dtosEmpleado.setFechaNacimientoAño(ventanaNuevoEmplado.txtAño.getText()); 
			dtosEmpleado.setFechaNacimientoMes(ventanaNuevoEmplado.txtMes.getText()); 
			dtosEmpleado.setFechaNacimientoDia(ventanaNuevoEmplado.txtDia.getText());
			dtosEmpleado.setDireccion(ventanaNuevoEmplado.txtDireccion.getText());
			dtosEmpleado.setEmail(ventanaNuevoEmplado.txtEmail.getText());
			dtosEmpleado.setTelefono(ventanaNuevoEmplado.txtTelefono.getText());
			dtosEmpleado.setSector((String) ventanaNuevoEmplado.comboBox1.getSelectedItem());
			dtosEmpleado.setRelacion((String) ventanaNuevoEmplado.comboBox2.getSelectedItem());
			dtosEmpleado.setCargo(ventanaNuevoEmplado.txt1.getText());
			dtosEmpleado.setSalario(ventanaNuevoEmplado.txt2.getText());
			String msgError = dtosEmpleado.checkInformacion(); 
			ventanaNuevoEmplado.lblMsgError.setForeground(Color.RED);
			ventanaNuevoEmplado.lblMsgError.setText(msgError);
			
			if(msgError.contentEquals("")) {
				
				if(dtosEmpleado.setNuevoEmpleado()) {
					
					ventanaNuevoEmplado.lblMsgError.setForeground(Color.BLUE);
					limpiarCampos();
					ventanaNuevoEmplado.lblMsgError.setText("Registro guardado con éxito.");
				} else {
					
					ventanaNuevoEmplado.lblMsgError.setForeground(Color.RED);
					ventanaNuevoEmplado.lblMsgError.setText("No se pudo guardar la información.");
				}
			}
		}

		if(e.getSource() == ventanaNuevoEmplado.btnVolver) {
			
			ventanaNuevoEmplado.dispose();
		}
	}
}