package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaceUsuario.Listado;

public class CtrlAsistenciaAlumnos implements ActionListener {

	private Listado ventanaAsistencia;
	
	public CtrlAsistenciaAlumnos(Listado vista) {
		
		this.ventanaAsistencia = vista;
		this.ventanaAsistencia.btnImprimir.addActionListener(this);
		this.ventanaAsistencia.comboBox1.addActionListener(this);
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