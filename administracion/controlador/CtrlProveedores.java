package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import interfaceUsuario.ABML;
import interfaceUsuario.InterfaceBotones;
import modelo.DtosConfiguracion;

public class CtrlProveedores implements ActionListener{
	
	private InterfaceBotones ventana;
	
	public CtrlProveedores(InterfaceBotones vista) {
		
		this.ventana = vista;
		this.ventana.btn1A.addActionListener(this);
		this.ventana.btnVolver.addActionListener(this);
	}
	
	public void iniciar() {

		ventana.lbl1A.setText("ABML proveedores");
		ventana.lbl1A.setVisible(true);
		ventana.btn1A.setIcon(new ImageIcon(DtosConfiguracion.getDirectorio() + "\\Imagenes\\ABML.png"));
		ventana.btn1A.setVisible(true);
		ventana.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == ventana.btn1A) {
			
			ABML ventanaABMLProveedores = new ABML("ABML proveedores");
			CtrlABMLProveedores ctrlABMLProveedores = new CtrlABMLProveedores(ventanaABMLProveedores);
			ctrlABMLProveedores.iniciar();
		}
		
		if(e.getSource() == ventana.btnVolver) {
			
			ventana.dispose();
		}
	}
}