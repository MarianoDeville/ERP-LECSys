package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaceUsuario.InterfaceBotones;

public class CtrlProveedores implements ActionListener{
	
	private InterfaceBotones ventanaProveedor;
	
	public CtrlProveedores(InterfaceBotones vista) {
		
		this.ventanaProveedor = vista;
		this.ventanaProveedor.btnVolver.addActionListener(this);
	}
	
	public void iniciar() {
		
		ventanaProveedor.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {


		
		if(e.getSource() == ventanaProveedor.btnVolver) {
			
			ventanaProveedor.dispose();
		}
	}
}