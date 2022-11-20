package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaceUsuario.Cumpleaños;
import modelo.DtosCumpleaños;

public class CtrlCumpleaños implements ActionListener {

	private Cumpleaños ventanaCumpleaños;
	private DtosCumpleaños dtosCumplaños;
	
	public CtrlCumpleaños(Cumpleaños vista) {
		
		this.ventanaCumpleaños = vista;
		this.dtosCumplaños = new DtosCumpleaños();
		this.ventanaCumpleaños.btnCerrar.addActionListener(this);
	}
	
	public void iniciar() {
		
		ventanaCumpleaños.tablaCumpleaños.setModel(dtosCumplaños.getTablaCumpleaños());
		
		if(dtosCumplaños.isBandera()) {
			
			ventanaCumpleaños.setVisible(true);
		} else {
			
			ventanaCumpleaños.dispose();
		}
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ventanaCumpleaños.btnCerrar) {
			
			ventanaCumpleaños.dispose();
		}
	}
}
