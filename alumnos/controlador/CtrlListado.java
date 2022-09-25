package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaceUsuario.Listado;

public class CtrlListado implements ActionListener {

	private Listado ventanaListado;
	
	public CtrlListado(Listado vista) {
		
		this.ventanaListado = vista;
		this.ventanaListado.btnImprimir.addActionListener(this);
		this.ventanaListado.chckbxActivo.addActionListener(this);
		this.ventanaListado.comboBoxCriterio.addActionListener(this);
		this.ventanaListado.comboBoxItem.addActionListener(this);
		this.ventanaListado.txtcantAlumn.addActionListener(this);
		this.ventanaListado.btnVolver.addActionListener(this);
	}

	public void iniciar() {
		
		ventanaListado.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {


		
		
		if(e.getSource() == ventanaListado.btnVolver) {

			ventanaListado.dispose();
		}
	}
}
