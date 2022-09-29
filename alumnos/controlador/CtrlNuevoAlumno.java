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
			
			String [] información = new String [9];
			información[0] = ventanaNuenoAlumno.txtNombre.getText();
			información[1] = ventanaNuenoAlumno.txtApellido.getText();
			información[2] = ventanaNuenoAlumno.txtDNI.getText();
			información[3] = ventanaNuenoAlumno.txtAño.getText();
			información[4] = ventanaNuenoAlumno.txtMes.getText();
			información[5] = ventanaNuenoAlumno.txtDia.getText();
			información[6] = ventanaNuenoAlumno.txtDireccion.getText();
			información[7] = ventanaNuenoAlumno.txtEmail.getText();
			información[8] = ventanaNuenoAlumno.txtTelefono.getText();

			String msgError = dtosNuevoAlumno.checkInformacion(información); 
			ventanaNuenoAlumno.lblMsgError.setText(msgError);
			
			if(msgError.contentEquals(""))
				dtosNuevoAlumno.setNuevoAlumno(información);
		}		
		
		if(e.getSource() == ventanaNuenoAlumno.btnVolver) {

			ventanaNuenoAlumno.dispose();
		}
	}
}
