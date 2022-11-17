package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaceUsuario.Listado;
import modelo.DtosAlumno;

public class CtrlGrupoFamiliar implements ActionListener {

	private Listado ventanaListado;
	private DtosAlumno dtosAlumno;
	
	public CtrlGrupoFamiliar(Listado vista) {
		
		this.ventanaListado = vista;
		this.dtosAlumno = new DtosAlumno();

		
		this.ventanaListado.btnVolver.addActionListener(this);
	}

	public void iniciar() {
		

		actualizar();
		ventanaListado.setVisible(true);
	}
	
	private void actualizar() {

		
		
	}
	
	
	public void actionPerformed(ActionEvent e) {

	
		
		if(e.getSource() == ventanaListado.btnVolver) {

			ventanaListado.dispose();
		}
	}
}
