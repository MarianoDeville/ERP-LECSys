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
		this.ventanaCrearCursos.comboBoxAula.addActionListener(this);
		this.ventanaCrearCursos.comboBoxProfesor.addActionListener(this);
		this.ventanaCrearCursos.comboBoxTurno.addActionListener(this);
		this.ventanaCrearCursos.btnGuardar.addActionListener(this);
		this.ventanaCrearCursos.btnVolver.addActionListener(this);
	}
	
	
	public void iniciar() {
		
		ventanaCrearCursos.comboBoxTurno.setModel(new DefaultComboBoxModel<String>(dtosCrearCurso.getListaTurno()));
		ventanaCrearCursos.comboBoxNivel.setModel(new DefaultComboBoxModel<String>(dtosCrearCurso.getListaNivel()));
		ventanaCrearCursos.comboBoxProfesor.setModel(new DefaultComboBoxModel<String>(dtosCrearCurso.getProfesores()));
		ventanaCrearCursos.comboBoxAula.setModel(new DefaultComboBoxModel<String>(dtosCrearCurso.getListaAulas()));
		actualizar();
		ventanaCrearCursos.setVisible(true);
	}

	private void actualizar() {

		ventanaCrearCursos.comboBoxAño.setModel(new DefaultComboBoxModel<String>(dtosCrearCurso.getAño((String)ventanaCrearCursos.comboBoxNivel.getSelectedItem())));
		ventanaCrearCursos.tablaHorarios.setModel(dtosCrearCurso.getHorarios((String)ventanaCrearCursos.comboBoxTurno.getSelectedItem(),
																			 ventanaCrearCursos.comboBoxTurno.getSelectedIndex(),
																			 ventanaCrearCursos.comboBoxProfesor.getSelectedIndex()));

		ventanaCrearCursos.tablaHorarios.setRowHeight(25);

	}
	
	private void limpiarCampos() {
		
		ventanaCrearCursos.txtCuota.setText("");

	}
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == ventanaCrearCursos.comboBoxNivel) {
			
			actualizar();
		}		
		
		if(e.getSource() == ventanaCrearCursos.comboBoxAula) {
			
			actualizar();
		}	
		
		if(e.getSource() == ventanaCrearCursos.comboBoxProfesor) {
			
			actualizar();
		}
		
		if(e.getSource() == ventanaCrearCursos.comboBoxTurno) {
			
			actualizar();
		}
		
		if(e.getSource() == ventanaCrearCursos.btnGuardar) {
		
			dtosCrearCurso.setAño((String)ventanaCrearCursos.comboBoxAño.getSelectedItem());
			dtosCrearCurso.setNivel((String)ventanaCrearCursos.comboBoxNivel.getSelectedItem());
			dtosCrearCurso.setIdProfesor(ventanaCrearCursos.comboBoxProfesor.getSelectedIndex());
			dtosCrearCurso.setTurno((String)ventanaCrearCursos.comboBoxTurno.getSelectedItem());
			dtosCrearCurso.setValorCuota(ventanaCrearCursos.txtCuota.getText());

			
			
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
