package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import interfaceUsuario.CrearCurso;
import modelo.DtosCurso;

public class CtrlEditarCurso implements ActionListener{
	
	private CrearCurso ventanaEditarCursos;
	private DtosCurso dtosCurso;
	private String curso;
		
	public CtrlEditarCurso(CrearCurso vista) {
		
		this.ventanaEditarCursos = vista;
		this.dtosCurso = new DtosCurso();
		this.ventanaEditarCursos.comboBoxAula.addActionListener(this);
		this.ventanaEditarCursos.comboBoxNivel.addActionListener(this);
		this.ventanaEditarCursos.comboBoxAula.addActionListener(this);
		this.ventanaEditarCursos.comboBoxProfesor.addActionListener(this);
		this.ventanaEditarCursos.btnGuardar.addActionListener(this);
		this.ventanaEditarCursos.btnBorrar.addActionListener(this);
		this.ventanaEditarCursos.btnVolver.addActionListener(this);
	}
	
	public void iniciar(String idCurso) {
		
		dtosCurso.setCurso(idCurso);
		curso = idCurso;
		dtosCurso.getInformacionCurso();
		ventanaEditarCursos.btnBorrar.setVisible(true);
		ventanaEditarCursos.comboBoxA�o.setModel(new DefaultComboBoxModel<String>(new String [] {dtosCurso.getA�o()}));
		ventanaEditarCursos.comboBoxNivel.setModel(new DefaultComboBoxModel<String>(new String [] {dtosCurso.getNivel()}));
		ventanaEditarCursos.comboBoxProfesor.setModel(new DefaultComboBoxModel<String>(dtosCurso.getListaProfesores()));
		ventanaEditarCursos.comboBoxAula.setModel(new DefaultComboBoxModel<String>(dtosCurso.getListaAulas()));
		ventanaEditarCursos.txtCuota.setText(dtosCurso.getValorCuota());
		ventanaEditarCursos.comboBoxAula.setSelectedIndex(dtosCurso.getAula());
		ventanaEditarCursos.comboBoxA�o.setSelectedItem(dtosCurso.getA�o());
		ventanaEditarCursos.comboBoxProfesor.setSelectedItem(dtosCurso.getNombreProfesor());
		ventanaEditarCursos.comboBoxNivel.setSelectedItem(dtosCurso.getNivel());
		actualizar();
		ventanaEditarCursos.setVisible(true);
	}

	private void actualizar() {

		if(ventanaEditarCursos.comboBoxAula.getSelectedIndex() == dtosCurso.getAula()) {
			
			dtosCurso.setCurso(curso);	
			ventanaEditarCursos.tablaHorarios.setModel(dtosCurso.getHorariosCurso(ventanaEditarCursos.comboBoxAula.getSelectedIndex(),
																				  ventanaEditarCursos.comboBoxProfesor.getSelectedIndex()));
		} else {
			
			dtosCurso.setCurso("0");
			ventanaEditarCursos.tablaHorarios.setModel(dtosCurso.getHorariosCurso(ventanaEditarCursos.comboBoxAula.getSelectedIndex(),
																			 ventanaEditarCursos.comboBoxProfesor.getSelectedIndex()));
		}

		for(int i = 0 ; i < 32 ; i++) {
			
			ventanaEditarCursos.tablaHorarios.getColumnModel().getColumn(i).setPreferredWidth(40);
		}

		ventanaEditarCursos.tablaHorarios.setRowHeight(25);
	}
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == ventanaEditarCursos.comboBoxAula) {
			
			actualizar();
		}	
		
		if(e.getSource() == ventanaEditarCursos.comboBoxProfesor) {
			
			actualizar();
		}
		
		if(e.getSource() == ventanaEditarCursos.btnGuardar) {
		
			dtosCurso.setA�o((String)ventanaEditarCursos.comboBoxA�o.getSelectedItem());
			dtosCurso.setNivel((String)ventanaEditarCursos.comboBoxNivel.getSelectedItem());
			dtosCurso.setIdProfesor(ventanaEditarCursos.comboBoxProfesor.getSelectedIndex());
			dtosCurso.setValorCuota(ventanaEditarCursos.txtCuota.getText());
			dtosCurso.setAula(ventanaEditarCursos.comboBoxAula.getSelectedIndex());
			dtosCurso.setHorarios(ventanaEditarCursos.tablaHorarios);
			String msgError = dtosCurso.checkInformacion(); 
			ventanaEditarCursos.lblMensageError.setForeground(Color.RED);
			ventanaEditarCursos.lblMensageError.setText(msgError);
			
			if(msgError.contentEquals("")) {
				
				if(dtosCurso.setActualizarCurso()) {
					
					ventanaEditarCursos.lblMensageError.setForeground(Color.BLUE);
					ventanaEditarCursos.lblMensageError.setText("Registro guardado con �xito.");
				} else {
					
					ventanaEditarCursos.lblMensageError.setForeground(Color.RED);
					ventanaEditarCursos.lblMensageError.setText("No se pudo guardar en la base de datos.");
				}
			}
		}
		
		if(e.getSource() == ventanaEditarCursos.btnBorrar) {
			
			dtosCurso.setEstado(0);
			
			if(dtosCurso.setActualizarCurso()) {
				
				ventanaEditarCursos.comboBoxA�o.setModel(new DefaultComboBoxModel<String>(new String [] {}));
				ventanaEditarCursos.comboBoxNivel.setModel(new DefaultComboBoxModel<String>(new String [] {}));		
				ventanaEditarCursos.comboBoxProfesor.setModel(new DefaultComboBoxModel<String>(new String [] {}));
				ventanaEditarCursos.comboBoxAula.setModel(new DefaultComboBoxModel<String>(new String [] {}));
				ventanaEditarCursos.txtCuota.setText("");
				ventanaEditarCursos.lblMensageError.setForeground(Color.BLUE);
				ventanaEditarCursos.lblMensageError.setText("El registro se borro exitosamente.");
			} else {
				
				ventanaEditarCursos.lblMensageError.setForeground(Color.RED);
				ventanaEditarCursos.lblMensageError.setText("Error al acceder a la base de datos.");
			}
		}
		
		if(e.getSource() == ventanaEditarCursos.btnVolver) {
			
			ventanaEditarCursos.dispose();
		}
	}
}
