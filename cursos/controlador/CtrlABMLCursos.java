package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaceUsuario.ABML;
import interfaces.CrearCurso;
import modelo.DtosAMBLCurso;

public class CtrlABMLCursos implements ActionListener {
	
	private ABML ventanaABMLCursos;
	private DtosAMBLCurso dtosAMBLCurso;
	private CrearCurso ventanaCrearCurso;

	public CtrlABMLCursos(ABML vista) {
		
		this.ventanaABMLCursos = vista;
		this.dtosAMBLCurso = new DtosAMBLCurso();
		this.ventanaABMLCursos.btnNuevo.addActionListener(this);
		this.ventanaABMLCursos.btnEditar.addActionListener(this);
		this.ventanaABMLCursos.btnImprimir.addActionListener(this);
		this.ventanaABMLCursos.btnVolver.addActionListener(this);
	}
	
	public void iniciar() {
		
		actualizar();
		ventanaABMLCursos.setVisible(true);
	}

	private void actualizar() {
		
		ventanaABMLCursos.tabla.setModel(dtosAMBLCurso.getTablaCursos());
	}
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == ventanaABMLCursos.btnNuevo) {
			
			ventanaCrearCurso = new CrearCurso("Crear un nuevo curso");
			CtrlNuenoCurso ctrlNuenoCurso = new CtrlNuenoCurso(ventanaCrearCurso);
			ctrlNuenoCurso.iniciar();
			ventanaCrearCurso.btnVolver.addActionListener(this);
		}
		
		if(ventanaCrearCurso != null) {
			
			if(e.getSource() == ventanaCrearCurso.btnVolver) {
				
				actualizar();
			}
		}

		if(e.getSource() == ventanaABMLCursos.btnEditar) {
			
			
		}
		
		if(e.getSource() == ventanaABMLCursos.btnImprimir) {
			
			
		}
		
		if(e.getSource() == ventanaABMLCursos.btnVolver) {
			
			ventanaABMLCursos.dispose();
		}
	}

}
