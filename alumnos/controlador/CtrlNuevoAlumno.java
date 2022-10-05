package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import interfaceUsuario.Nuevo;
import modelo.DtosAlumno;

public class CtrlNuevoAlumno implements ActionListener {
	
	private Nuevo ventanaNuenoAlumno;
	private DtosAlumno dtosNuevoAlumno;
	
	public CtrlNuevoAlumno(Nuevo vista) {
		
		this.ventanaNuenoAlumno = vista;
		this.dtosNuevoAlumno = new DtosAlumno();
		this.ventanaNuenoAlumno.comboBox1.addActionListener(this);
		this.ventanaNuenoAlumno.btnGuardar.addActionListener(this);
		this.ventanaNuenoAlumno.btnVolver.addActionListener(this);
	}

	public void iniciar() {
		
		ventanaNuenoAlumno.lblcomboBox1.setText("Curso:");
		ventanaNuenoAlumno.comboBox1.setModel(new DefaultComboBoxModel<String>(dtosNuevoAlumno.getListaCursos()));
		ventanaNuenoAlumno.scrollTabla.setVisible(true);
		actualizar();
		ventanaNuenoAlumno.setVisible(true);
	}
	
	private void actualizar() {
		
		ventanaNuenoAlumno.tabla.setModel(dtosNuevoAlumno.getTablaDias(ventanaNuenoAlumno.comboBox1.getSelectedIndex()));
	}
	
	private void limpiarCampos() {
		
		ventanaNuenoAlumno.txtNombre.setText("");
		ventanaNuenoAlumno.txtApellido.setText("");
		ventanaNuenoAlumno.txtDNI.setText("");
		ventanaNuenoAlumno.txtAño.setText("");
		ventanaNuenoAlumno.txtMes.setText("");
		ventanaNuenoAlumno.txtDia.setText("");
		ventanaNuenoAlumno.txtDireccion.setText("");
		ventanaNuenoAlumno.txtEmail.setText("");
		ventanaNuenoAlumno.txtTelefono.setText("");
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ventanaNuenoAlumno.comboBox1) {
			
			actualizar();
		}

		if(e.getSource() == ventanaNuenoAlumno.btnGuardar) {
			
			dtosNuevoAlumno.setNombre(ventanaNuenoAlumno.txtNombre.getText());
			dtosNuevoAlumno.setApellido(ventanaNuenoAlumno.txtApellido.getText());
			dtosNuevoAlumno.setDni(ventanaNuenoAlumno.txtDNI.getText());
			dtosNuevoAlumno.setFechaNacimientoAño(ventanaNuenoAlumno.txtAño.getText());
			dtosNuevoAlumno.setFechaNacimientoMes(ventanaNuenoAlumno.txtMes.getText());
			dtosNuevoAlumno.setFechaNacimientoDia(ventanaNuenoAlumno.txtDia.getText());
			dtosNuevoAlumno.setDireccion(ventanaNuenoAlumno.txtDireccion.getText());
			dtosNuevoAlumno.setEmail(ventanaNuenoAlumno.txtEmail.getText());
			dtosNuevoAlumno.setTelefono(ventanaNuenoAlumno.txtTelefono.getText());
			dtosNuevoAlumno.setCurso(ventanaNuenoAlumno.comboBox1.getSelectedIndex());
			String msgError = dtosNuevoAlumno.checkInformacion(true); 
			ventanaNuenoAlumno.lblMsgError.setForeground(Color.RED);
			ventanaNuenoAlumno.lblMsgError.setText(msgError);
			
			if(msgError.contentEquals("")) {
				
				if(dtosNuevoAlumno.setNuevoAlumno()) {
					
					limpiarCampos();
					ventanaNuenoAlumno.lblMsgError.setForeground(Color.BLUE);
					ventanaNuenoAlumno.lblMsgError.setText("Los datos se guardaron correctamente.");
				} else {
					
					ventanaNuenoAlumno.lblMsgError.setForeground(Color.RED);
					ventanaNuenoAlumno.lblMsgError.setText("Error al intentar guardar la información.");
				}
			}
		}		
		
		if(e.getSource() == ventanaNuenoAlumno.btnVolver) {

			ventanaNuenoAlumno.dispose();
		}
	}
}
