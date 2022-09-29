package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaceUsuario.ABML;
import interfaces.CrearCurso;

public class CtrlABMLCursos implements ActionListener {
	
	private ABML ventanaABMLCursos;

	public CtrlABMLCursos(ABML vista) {
		
		this.ventanaABMLCursos = vista;
		this.ventanaABMLCursos.btnNuevo.addActionListener(this);
		this.ventanaABMLCursos.btnEditar.addActionListener(this);
		this.ventanaABMLCursos.btnImprimir.addActionListener(this);
		this.ventanaABMLCursos.btnVolver.addActionListener(this);
	}
	
	
	public void iniciar() {
		

		ventanaABMLCursos.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == ventanaABMLCursos.btnNuevo) {
			
			CrearCurso ventanaCrearCurso = new CrearCurso("Crear un nuevo curso");
			CtrlNuenoCurso ctrlNuenoCurso = new CtrlNuenoCurso(ventanaCrearCurso);
			ctrlNuenoCurso.iniciar();
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
