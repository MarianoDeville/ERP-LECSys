package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

import interfaceUsuario.ABML;
import interfaceUsuario.InterfaceBotones;
import modelo.DtosConfiguracion;

public class CtrlInsumos implements ActionListener {

	private InterfaceBotones ventana;
	
	public CtrlInsumos(InterfaceBotones vista) {
		
		this.ventana = vista;
		this.ventana.btn1A.addActionListener(this);
		this.ventana.btn1B.addActionListener(this);
		this.ventana.btn2A.addActionListener(this);
		this.ventana.btnVolver.addActionListener(this);
	}
	
	public void iniciar() {
		
		ventana.lbl1A.setText("ABML insumos");
		ventana.lbl1A.setVisible(true);
		ventana.btn1A.setIcon(new ImageIcon(DtosConfiguracion.getDirectorio() + "\\Imagenes\\ABML.png"));
		ventana.btn1A.setVisible(true);
		ventana.lbl1B.setText("Stock");
		ventana.lbl1B.setVisible(true);
		ventana.btn1B.setIcon(new ImageIcon(DtosConfiguracion.getDirectorio() + "\\Imagenes\\Insumos.png"));
		ventana.btn1B.setVisible(true);
		ventana.lbl2A.setText("Histórico");
		ventana.lbl2A.setVisible(true);
		ventana.btn2A.setIcon(new ImageIcon(DtosConfiguracion.getDirectorio() + "\\Imagenes\\Listado.png"));
		ventana.btn2A.setVisible(true);
		ventana.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == ventana.btn1A) {
			
			ABML ventanaABMLInsumos = new ABML("Insumos");
			CtrlABMLInsumos ctrlABMLInsumos = new CtrlABMLInsumos(ventanaABMLInsumos);
			ctrlABMLInsumos.iniciar();
		}
		
		if(e.getSource() == ventana.btn1B) {
			

		}

		if(e.getSource() == ventana.btn2A) {
			

		}

		if(e.getSource() == ventana.btnVolver) {
			
			ventana.dispose();
		}
	}
}
