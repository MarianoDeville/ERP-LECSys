package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaceUsuario.ABML;

public class CtrlABMLPersonal implements ActionListener {

	private ABML ventanaABML;
	
	public CtrlABMLPersonal(ABML vista) {
		
		this.ventanaABML = vista;
		this.ventanaABML.btnNuevo.addActionListener(this);
		this.ventanaABML.btnBuscar.addActionListener(this);
		this.ventanaABML.btnEditar.addActionListener(this);
		this.ventanaABML.btnImprimir.addActionListener(this);
		this.ventanaABML.btnVolver.addActionListener(this);
		this.ventanaABML.chckbxActivo.addActionListener(this);
		this.ventanaABML.comboBoxOrden.addActionListener(this);
		this.ventanaABML.txtFiltrar.addActionListener(this);
	}
	
	public void iniciar() {
		
		ventanaABML.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		
		

		if(e.getSource() == ventanaABML.btnVolver) {
			
			ventanaABML.dispose();
		}
	}
}
