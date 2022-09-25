package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaceUsuario.InterfaceBotones;

public class CtrlEstadisticas implements ActionListener {

	private InterfaceBotones ventanaEstadisticas;
	
	public CtrlEstadisticas(InterfaceBotones vista) {
		
		this.ventanaEstadisticas = vista;
		this.ventanaEstadisticas.btnVolver.addActionListener(this);
	}
	
	public void iniciar() {
		
		ventanaEstadisticas.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {

		
		if(e.getSource() == ventanaEstadisticas.btnVolver) {
			
			ventanaEstadisticas.dispose();
		}
	}
}