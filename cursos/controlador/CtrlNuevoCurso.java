package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import interfaceUsuario.CrearCurso;
import modelo.DtosCurso;

public class CtrlNuevoCurso implements ActionListener {
	
	private CrearCurso ventanaCrearCursos;
	private DtosCurso dtosCurso;
		
	public CtrlNuevoCurso(CrearCurso vista) {
		
		this.ventanaCrearCursos = vista;
		this.dtosCurso = new DtosCurso();
		this.ventanaCrearCursos.comboBoxNivel.addActionListener(this);
		this.ventanaCrearCursos.comboBoxAula.addActionListener(this);
		this.ventanaCrearCursos.comboBoxProfesor.addActionListener(this);
		this.ventanaCrearCursos.btnGuardar.addActionListener(this);
		this.ventanaCrearCursos.btnVolver.addActionListener(this);
	}
	
	public void iniciar() {
		
		ventanaCrearCursos.comboBoxNivel.setModel(new DefaultComboBoxModel<String>(dtosCurso.getListaNivel()));
		ventanaCrearCursos.comboBoxProfesor.setModel(new DefaultComboBoxModel<String>(dtosCurso.getListaProfesores()));
		ventanaCrearCursos.comboBoxAula.setModel(new DefaultComboBoxModel<String>(dtosCurso.getListaAulas()));
		ventanaCrearCursos.comboBoxAño.setModel(new DefaultComboBoxModel<String>(dtosCurso.getListaAños((String)ventanaCrearCursos.comboBoxNivel.getSelectedItem())));
		actualizar();
		ventanaCrearCursos.setVisible(true);
	}

	private void actualizar() {

		dtosCurso.setCurso("0");
		ventanaCrearCursos.tablaHorarios.setModel(dtosCurso.getHorariosCurso(ventanaCrearCursos.comboBoxAula.getSelectedIndex(),
																			 ventanaCrearCursos.comboBoxProfesor.getSelectedIndex()));

		for(int i = 0 ; i < 32 ; i++) {
			
			ventanaCrearCursos.tablaHorarios.getColumnModel().getColumn(i).setPreferredWidth(40);
		}
		ventanaCrearCursos.tablaHorarios.setRowHeight(25);
	}
	
	private void limpiarCampos() {
		
		ventanaCrearCursos.txtCuota.setText("");
		actualizar();
	}
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == ventanaCrearCursos.comboBoxNivel) {
			
			ventanaCrearCursos.comboBoxAño.setModel(new DefaultComboBoxModel<String>(dtosCurso.getListaAños((String)ventanaCrearCursos.comboBoxNivel.getSelectedItem())));
			actualizar();
		}		
		
		if(e.getSource() == ventanaCrearCursos.comboBoxAula) {
			
			actualizar();
		}	
		
		if(e.getSource() == ventanaCrearCursos.comboBoxProfesor) {
			
			actualizar();
		}
		
		if(e.getSource() == ventanaCrearCursos.btnGuardar) {
		
			dtosCurso.setAño((String)ventanaCrearCursos.comboBoxAño.getSelectedItem());
			dtosCurso.setNivel((String)ventanaCrearCursos.comboBoxNivel.getSelectedItem());
			dtosCurso.setIdProfesor(ventanaCrearCursos.comboBoxProfesor.getSelectedIndex());
			dtosCurso.setValorCuota(ventanaCrearCursos.txtCuota.getText());
			dtosCurso.setAula(ventanaCrearCursos.comboBoxAula.getSelectedIndex());
			dtosCurso.setHorarios(ventanaCrearCursos.tablaHorarios);
			String msgError = dtosCurso.checkInformacion(); 
			ventanaCrearCursos.lblMensageError.setForeground(Color.RED);
			ventanaCrearCursos.lblMensageError.setText(msgError);
			
			if(msgError.equals("")) {
				
				if(dtosCurso.setNuevoCurso()) {
					
					ventanaCrearCursos.lblMensageError.setForeground(Color.BLUE);
					limpiarCampos();
					ventanaCrearCursos.lblMensageError.setText("Registro guardado con éxito.");
				} else {
					
					ventanaCrearCursos.lblMensageError.setForeground(Color.RED);
					ventanaCrearCursos.lblMensageError.setText("No se pudo guardar en la base de datos.");
				}
			}
		}
		
		if(e.getSource() == ventanaCrearCursos.btnVolver) {
			
			ventanaCrearCursos.dispose();
		}
	}
}
