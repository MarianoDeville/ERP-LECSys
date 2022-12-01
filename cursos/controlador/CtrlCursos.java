package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import interfaceUsuario.ABML;
import interfaceUsuario.InterfaceBotones;
import interfaceUsuario.Listado;
import modelo.DtosAcceso;
import modelo.DtosConfiguracion;

public class CtrlCursos implements ActionListener {
	
	private InterfaceBotones ventanaCursos;
	private DtosAcceso acceso;

	public CtrlCursos(InterfaceBotones vista) {
		
		this.ventanaCursos = vista;
		acceso = new DtosAcceso();
		this.ventanaCursos.btn1A.addActionListener(this);
		this.ventanaCursos.btn1B.addActionListener(this);
		this.ventanaCursos.btnVolver.addActionListener(this);
	}
	
	public void iniciar() {
		
		ventanaCursos.lbl1A.setText("ABML");
		ventanaCursos.lbl1A.setVisible(true);
		ventanaCursos.btn1A.setIcon(new ImageIcon(DtosConfiguracion.getDirectorio() + "\\Imagenes\\Actividades.png"));
		ventanaCursos.btn1A.setVisible(true);
		ventanaCursos.lbl1B.setText("Diagramación");
		ventanaCursos.lbl1B.setVisible(true);
		ventanaCursos.btn1B.setIcon(new ImageIcon(DtosConfiguracion.getDirectorio() + "\\Imagenes\\Diagramacion.png"));
		ventanaCursos.btn1B.setVisible(true);
		ventanaCursos.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == ventanaCursos.btn1A) {
			
			if(acceso.chkAcceso("Cursos", "ABML")) {
				
				ABML ventanaABMLCursos = new ABML("Gestionar los cursos");
				CtrlABMLCursos ctrlABMLCursos = new CtrlABMLCursos(ventanaABMLCursos);
				ctrlABMLCursos.iniciar();
			}
		}
		
		if(e.getSource() == ventanaCursos.btn1B) {
			
			if(acceso.chkAcceso("Cursos", "Listado")) {
				
				Listado ventanaDiagramacionCursos = new Listado("Diagramación de cursos");
				CtrlDiagramaCursos ctrlDiagramaCursos = new CtrlDiagramaCursos(ventanaDiagramacionCursos);
				ctrlDiagramaCursos.iniciar();
			}
		}
		
		if(e.getSource() == ventanaCursos.btnVolver) {
			
			ventanaCursos.dispose();
		}
	}
}
