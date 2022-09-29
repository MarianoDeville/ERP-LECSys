package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import interfaces.CrearCurso;
import modelo.DtosCrearCurso;

public class CtrlNuenoCurso implements ActionListener {
	
	private  CrearCurso ventanaCrearCursos;
	private DtosCrearCurso dtosCrearCurso;
	
	public CtrlNuenoCurso(CrearCurso vista) {
		
		this.ventanaCrearCursos = vista;
		this.dtosCrearCurso = new DtosCrearCurso();
		this.ventanaCrearCursos.comboBoxNivel.addActionListener(this);
		this.ventanaCrearCursos.btnGuardar.addActionListener(this);
		this.ventanaCrearCursos.btnVolver.addActionListener(this);
	}
	
	
	public void iniciar() {
		
		ventanaCrearCursos.comboBoxTurno.setModel(new DefaultComboBoxModel<String>(dtosCrearCurso.getListaTurno()));
		ventanaCrearCursos.comboBoxNivel.setModel(new DefaultComboBoxModel<String>(dtosCrearCurso.getListaNivel()));
		ventanaCrearCursos.comboBoxProfesor.setModel(new DefaultComboBoxModel<String>(dtosCrearCurso.getProfesores()));
		actualizar();
		ventanaCrearCursos.setVisible(true);
	}

	private void actualizar() {

		ventanaCrearCursos.comboBoxAño.setModel(new DefaultComboBoxModel<String>(dtosCrearCurso.getAño((String)ventanaCrearCursos.comboBoxNivel.getSelectedItem())));
	}
	
	private void limpiarCampos() {
		
		ventanaCrearCursos.txtCuota.setText("");
		ventanaCrearCursos.txtLunesDuracion.setText("");
		ventanaCrearCursos.txtLunesHorario.setText("");
		ventanaCrearCursos.txtMartesDuracion.setText("");
		ventanaCrearCursos.txtMartesHorario.setText("");
		ventanaCrearCursos.txtMiercolesDuracion.setText("");
		ventanaCrearCursos.txtMiercolesHorario.setText("");
		ventanaCrearCursos.txtJuevesDuracion.setText("");
		ventanaCrearCursos.txtJuevesHorario.setText("");
		ventanaCrearCursos.txtViernesDuracion.setText("");
		ventanaCrearCursos.txtViernesHorario.setText("");
		ventanaCrearCursos.txtSabadoDuracion.setText("");
		ventanaCrearCursos.txtSabadoHorario.setText("");
	}
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == ventanaCrearCursos.comboBoxNivel) {
			
			actualizar();
		}		
		
		if(e.getSource() == ventanaCrearCursos.btnGuardar) {
		
			dtosCrearCurso.setAño((String)ventanaCrearCursos.comboBoxAño.getSelectedItem());
			dtosCrearCurso.setNivel((String)ventanaCrearCursos.comboBoxNivel.getSelectedItem());
			dtosCrearCurso.setIdProfesor(ventanaCrearCursos.comboBoxProfesor.getSelectedIndex());
			dtosCrearCurso.setTurno((String)ventanaCrearCursos.comboBoxTurno.getSelectedItem());
			dtosCrearCurso.setValorCuota(ventanaCrearCursos.txtCuota.getText());
			dtosCrearCurso.setDiasCursado(0, ventanaCrearCursos.txtLunesHorario.getText(),
											 ventanaCrearCursos.txtLunesDuracion.getText());
			dtosCrearCurso.setDiasCursado(1, ventanaCrearCursos.txtMartesHorario.getText(),
											 ventanaCrearCursos.txtMartesDuracion.getText());
			dtosCrearCurso.setDiasCursado(2, ventanaCrearCursos.txtMiercolesHorario.getText(),
											 ventanaCrearCursos.txtMiercolesDuracion.getText());
			dtosCrearCurso.setDiasCursado(3, ventanaCrearCursos.txtJuevesHorario.getText(),
											 ventanaCrearCursos.txtJuevesDuracion.getText());
			dtosCrearCurso.setDiasCursado(4, ventanaCrearCursos.txtViernesHorario.getText(),
											 ventanaCrearCursos.txtViernesDuracion.getText());
			dtosCrearCurso.setDiasCursado(5, ventanaCrearCursos.txtSabadoHorario.getText(),
											 ventanaCrearCursos.txtSabadoDuracion.getText());
			String msgError = dtosCrearCurso.checkInformacion(); 
			ventanaCrearCursos.lblMensageError.setForeground(Color.RED);
			ventanaCrearCursos.lblMensageError.setText(msgError);
			
			if(msgError.contentEquals("")) {
				
				if(dtosCrearCurso.setNuevoCurso()) {
					
					ventanaCrearCursos.lblMensageError.setForeground(Color.BLUE);
					limpiarCampos();
					ventanaCrearCursos.lblMensageError.setText("Registro guardado con éxito.");
				}
			}
		}
		
		if(e.getSource() == ventanaCrearCursos.btnVolver) {
			
			ventanaCrearCursos.dispose();
		}
	}
}
