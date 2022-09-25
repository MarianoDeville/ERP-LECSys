package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaceUsuario.InterfaceBotones;

public class CtrlCompras implements ActionListener {

	private InterfaceBotones ventanaCompras;
	
	public CtrlCompras(InterfaceBotones vista) {
		
		this.ventanaCompras = vista;
		this.ventanaCompras.btnVolver.addActionListener(this);
	}
	
	public void iniciar() {
		
		ventanaCompras.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {

		
		if(e.getSource() == ventanaCompras.btnVolver) {
			
			ventanaCompras.dispose();
		}
	}
}