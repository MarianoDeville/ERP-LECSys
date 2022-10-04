package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaceUsuario.Nuevo;
import modelo.DtosEditarAlumno;

public class CtrlEditarAlumno implements ActionListener {
	
	private Nuevo ventanaEditarAlumno;
	private DtosEditarAlumno dtosEditarAlumno;

	public CtrlEditarAlumno(Nuevo vista) {
		
		this.ventanaEditarAlumno = vista;
		this.dtosEditarAlumno = new DtosEditarAlumno();
		this.ventanaEditarAlumno.btnGuardar.addActionListener(this);
		this.ventanaEditarAlumno.btnImprimir.addActionListener(this);
		this.ventanaEditarAlumno.btnVolver.addActionListener(this);
		
	}
	public void iniciar(String legajo) {
		
		ventanaEditarAlumno.btnImprimir.setVisible(true);
		ventanaEditarAlumno.scrollTabla.setVisible(true);
		actualizar();
		ventanaEditarAlumno.setVisible(true);
	}
	
	private void actualizar() {
		

	}
	
	
	
	public void actionPerformed(ActionEvent e) {


		if(e.getSource() == ventanaEditarAlumno.btnGuardar) {
			
			
		}
		
		if(e.getSource() == ventanaEditarAlumno.btnImprimir) {
			
			
		}
		
		if(e.getSource() == ventanaEditarAlumno.btnVolver) {
			
			ventanaEditarAlumno.dispose();
		}
	}
	
}
