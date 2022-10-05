package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaceUsuario.ABML;

public class CtrlABMLUsuarios implements ActionListener {
	
	private  ABML ventanaABML;

	public CtrlABMLUsuarios(ABML vista) {
		
		this.ventanaABML = vista;
		this.ventanaABML.btnNuevo.addActionListener(this);
		this.ventanaABML.btnEditar.addActionListener(this);
		this.ventanaABML.btnImprimir.addActionListener(this);
		this.ventanaABML.btnVolver.addActionListener(this);
	}
	
	public void iniciar() {
		
		ventanaABML.chckbx1.setVisible(true);
		ventanaABML.comboBox1.setVisible(true);
		ventanaABML.txt1.setVisible(true);
		ventanaABML.setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ventanaABML.btnNuevo) {
			
			
		}
		
		if(e.getSource() == ventanaABML.btnEditar) {
			
			
		}
		
		if(e.getSource() == ventanaABML.btnImprimir) {
			
			
		}
		
		if(e.getSource() == ventanaABML.btnVolver) {
			
			ventanaABML.dispose();
		}
	}
}
