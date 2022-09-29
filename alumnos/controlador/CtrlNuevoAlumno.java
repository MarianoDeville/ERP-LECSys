package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import interfaceUsuario.Nuevo;
import modelo.DtosNuevoAlumno;

public class CtrlNuevoAlumno implements ActionListener {
	
	private Nuevo ventanaNuenoAlumno;
	private DtosNuevoAlumno dtosNuevoAlumno;
	
	public CtrlNuevoAlumno(Nuevo vista) {
		
		this.ventanaNuenoAlumno = vista;
		this.dtosNuevoAlumno = new DtosNuevoAlumno();
		this.ventanaNuenoAlumno.btnGuardar.addActionListener(this);
		this.ventanaNuenoAlumno.btnVolver.addActionListener(this);
	}

	public void iniciar() {
		
		ventanaNuenoAlumno.lblcomboBox1.setText("Curso:");
		ventanaNuenoAlumno.comboBox1.setModel(new DefaultComboBoxModel<String>(dtosNuevoAlumno.getListaCursos()));
		ventanaNuenoAlumno.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == ventanaNuenoAlumno.btnGuardar) {
			
			String [] informaci�n = new String [9];
			informaci�n[0] = ventanaNuenoAlumno.txtNombre.getText();
			informaci�n[1] = ventanaNuenoAlumno.txtApellido.getText();
			informaci�n[2] = ventanaNuenoAlumno.txtDNI.getText();
			informaci�n[3] = ventanaNuenoAlumno.txtA�o.getText();
			informaci�n[4] = ventanaNuenoAlumno.txtMes.getText();
			informaci�n[5] = ventanaNuenoAlumno.txtDia.getText();
			informaci�n[6] = ventanaNuenoAlumno.txtDireccion.getText();
			informaci�n[7] = ventanaNuenoAlumno.txtEmail.getText();
			informaci�n[8] = ventanaNuenoAlumno.txtTelefono.getText();

			String msgError = dtosNuevoAlumno.checkInformacion(informaci�n); 
			ventanaNuenoAlumno.lblMsgError.setText(msgError);
			
			if(msgError.contentEquals(""))
				dtosNuevoAlumno.setNuevoAlumno(informaci�n);
		}		
		
		if(e.getSource() == ventanaNuenoAlumno.btnVolver) {

			ventanaNuenoAlumno.dispose();
		}
	}
}
