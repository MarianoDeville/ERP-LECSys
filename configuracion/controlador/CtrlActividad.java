package controlador;

import interfaceUsuario.Listado;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrlActividad implements ActionListener {

	private Listado ventanaActividad;
	
	public CtrlActividad(Listado vista) {
		
		this.ventanaActividad = vista;
		this.ventanaActividad.btnVolver.addActionListener(this);
	}
	
	
	public void iniciar() {
		
		ventanaActividad.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		


		if(e.getSource() == ventanaActividad.btnVolver) {
			
			ventanaActividad.dispose();
		}
	}
}
