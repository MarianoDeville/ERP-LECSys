package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import interfaceUsuario.ABML;
import interfaceUsuario.InterfaceBotones;

public class CtrlCursos implements ActionListener {
	
	private  InterfaceBotones ventanaCursos;

	public CtrlCursos(InterfaceBotones vista) {
		
		this.ventanaCursos = vista;
		this.ventanaCursos.btn1A.addActionListener(this);
		
		
		this.ventanaCursos.btnVolver.addActionListener(this);
	}
	
	
	public void iniciar() {
		
		ventanaCursos.lbl1A.setText("Crear curso");
		ventanaCursos.lbl1A.setVisible(true);
		ventanaCursos.btn1A.setIcon(new ImageIcon("C:\\LECSys 2.0\\Imagenes\\Actividades.png"));
		ventanaCursos.btn1A.setVisible(true);
		ventanaCursos.setVisible(true);
	}

	
	public void actionPerformed(ActionEvent e) {

		
		if(e.getSource() == ventanaCursos.btn1A) {
			
			ABML ventanaABMLCursos = new ABML("Gestionar los cursos");
			CtrlABMLCursos ctrlABMLCursos = new CtrlABMLCursos(ventanaABMLCursos);
			ctrlABMLCursos.iniciar();
		}
		if(e.getSource() == ventanaCursos.btnVolver) {
			
			ventanaCursos.dispose();
		}
	}
}
