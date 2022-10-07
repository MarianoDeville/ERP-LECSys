package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import interfaceUsuario.CrearCurso;
import modelo.DtosCurso;

public class CtrlNuenoCurso implements ActionListener {
	
	private  CrearCurso ventanaCrearCursos;
	private DtosCurso dtosCurso;
		
	public CtrlNuenoCurso(CrearCurso vista) {
		
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
		ventanaCrearCursos.comboBoxProfesor.setModel(new DefaultComboBoxModel<String>(dtosCurso.getProfesores()));
		ventanaCrearCursos.comboBoxAula.setModel(new DefaultComboBoxModel<String>(dtosCurso.getListaAulas()));
		actualizar();
		ventanaCrearCursos.setVisible(true);
	}

	private void actualizar() {

		ventanaCrearCursos.comboBoxA�o.setModel(new DefaultComboBoxModel<String>(dtosCurso.getListaA�os((String)ventanaCrearCursos.comboBoxNivel.getSelectedItem())));
		ventanaCrearCursos.tablaHorarios.setModel(dtosCurso.getHorarios(ventanaCrearCursos.comboBoxAula.getSelectedIndex(),
																			 ventanaCrearCursos.comboBoxProfesor.getSelectedIndex()));

		for(int i = 0 ; i < 28 ; i++) {
			
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
			
			actualizar();
		}		
		
		if(e.getSource() == ventanaCrearCursos.comboBoxAula) {
			
			actualizar();
		}	
		
		if(e.getSource() == ventanaCrearCursos.comboBoxProfesor) {
			
			actualizar();
		}
		
		if(e.getSource() == ventanaCrearCursos.btnGuardar) {
		
			dtosCurso.setA�o((String)ventanaCrearCursos.comboBoxA�o.getSelectedItem());
			dtosCurso.setNivel((String)ventanaCrearCursos.comboBoxNivel.getSelectedItem());
			dtosCurso.setIdProfesor(ventanaCrearCursos.comboBoxProfesor.getSelectedIndex());
			dtosCurso.setValorCuota(ventanaCrearCursos.txtCuota.getText());
			dtosCurso.setAula(ventanaCrearCursos.comboBoxAula.getSelectedIndex());
			dtosCurso.setHorarios(ventanaCrearCursos.tablaHorarios);
			String msgError = dtosCurso.checkInformacion(); 
			ventanaCrearCursos.lblMensageError.setForeground(Color.RED);
			ventanaCrearCursos.lblMensageError.setText(msgError);
			
			if(msgError.contentEquals("")) {
				
				if(dtosCurso.setNuevoCurso()) {
					
					ventanaCrearCursos.lblMensageError.setForeground(Color.BLUE);
					limpiarCampos();
					ventanaCrearCursos.lblMensageError.setText("Registro guardado con �xito.");
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
