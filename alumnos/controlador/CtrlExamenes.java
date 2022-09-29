package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaceUsuario.Listado;

public class CtrlExamenes implements ActionListener {

	private Listado ventanaExamenes;
	
	public CtrlExamenes(Listado vista) {
		
		this.ventanaExamenes = vista;
		this.ventanaExamenes.comboBox1.addActionListener(this);
		this.ventanaExamenes.btnImprimir.addActionListener(this);
		this.ventanaExamenes.btnVolver.addActionListener(this);
	}
	
	public void iniciar() {
		
		ventanaExamenes.setVisible(true);
	}
	

	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == ventanaExamenes.btnImprimir) {
			

		}
		
		if(e.getSource() == ventanaExamenes.btnVolver) {
			
			ventanaExamenes.dispose();
		}
	}

}
