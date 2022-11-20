package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaceUsuario.Cumplea�os;
import modelo.DtosCumplea�os;

public class CtrlCumplea�os implements ActionListener {

	private Cumplea�os ventanaCumplea�os;
	private DtosCumplea�os dtosCumpla�os;
	
	public CtrlCumplea�os(Cumplea�os vista) {
		
		this.ventanaCumplea�os = vista;
		this.dtosCumpla�os = new DtosCumplea�os();
		this.ventanaCumplea�os.btnCerrar.addActionListener(this);
	}
	
	public void iniciar() {
		
		ventanaCumplea�os.tablaCumplea�os.setModel(dtosCumpla�os.getTablaCumplea�os());
		
		if(dtosCumpla�os.isBandera()) {
			
			ventanaCumplea�os.setVisible(true);
		} else {
			
			ventanaCumplea�os.dispose();
		}
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ventanaCumplea�os.btnCerrar) {
			
			ventanaCumplea�os.dispose();
		}
	}
}
