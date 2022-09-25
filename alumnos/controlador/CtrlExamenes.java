package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaceUsuario.Examenes;

public class CtrlExamenes implements ActionListener {

	private Examenes ventanaExamenes;
	
	public CtrlExamenes(Examenes vista) {
		
		this.ventanaExamenes = vista;
		this.ventanaExamenes.comboBoxCurso.addActionListener(this);
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
