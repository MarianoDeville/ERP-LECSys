package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaceUsuario.Examenes;

public class CtrlAsistenciaAlumnos implements ActionListener {

	private Examenes ventanaAsistencia;
	
	public CtrlAsistenciaAlumnos(Examenes vista) {
		
		this.ventanaAsistencia = vista;
		this.ventanaAsistencia.btnImprimir.addActionListener(this);
		this.ventanaAsistencia.comboBoxCurso.addActionListener(this);
		this.ventanaAsistencia.btnVolver.addActionListener(this);
	}

	public void iniciar() {
		
		ventanaAsistencia.setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent e) {
	

		if(e.getSource() == ventanaAsistencia.btnImprimir) {
			

		}
		
		if(e.getSource() == ventanaAsistencia.btnVolver) {

			ventanaAsistencia.dispose();
		}
	}
}