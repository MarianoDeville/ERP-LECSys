package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaceUsuario.InformeAlumno;
import modelo.DtosAlumno;

public class CtrlInformeAlumno implements ActionListener {

	private InformeAlumno ventanaInforme;
	private DtosAlumno dtosAlumno;
	
	public CtrlInformeAlumno(InformeAlumno vista) {
		
		this.ventanaInforme = vista;
		this.dtosAlumno = new DtosAlumno();
		this.ventanaInforme.btnImprimir.addActionListener(this);
		this.ventanaInforme.btnVolver.addActionListener(this);
	}

	public void iniciar() {
		
		ventanaInforme.txtLegajo.setEditable(false);
		ventanaInforme.txtLegajo.setText(dtosAlumno.getLegajo());
		ventanaInforme.txtNombre.setEditable(false);
		ventanaInforme.txtNombre.setText(dtosAlumno.getNombre());
		ventanaInforme.txtApellido.setEditable(false);
		ventanaInforme.txtApellido.setText(dtosAlumno.getApellido());
		ventanaInforme.txtCurso.setEditable(false);
		ventanaInforme.txtCurso.setText(dtosAlumno.getNombreCurso());
		
		ventanaInforme.txtPresentismo.setEditable(false);
		ventanaInforme.txtFaltas.setEditable(false);
		ventanaInforme.txtTotal.setEditable(false);
		ventanaInforme.txtEscrito1.setEditable(false);
		ventanaInforme.txtEscrito2.setEditable(false);
		ventanaInforme.txtFinalEscrito.setEditable(false);
		ventanaInforme.txtComportamiento1.setEditable(false);
		ventanaInforme.txtComportamiento2.setEditable(false);
		ventanaInforme.txtFinalComportamiento.setEditable(false);
		ventanaInforme.txtOral1.setEditable(false);
		ventanaInforme.txtOral2.setEditable(false);
		ventanaInforme.txtFinalOral.setEditable(false);
		
		
		
		ventanaInforme.setVisible(true);
	}
	

	
	public void actionPerformed(ActionEvent e) {

		
		if(e.getSource() == ventanaInforme.btnImprimir) {


		}		
		
		if(e.getSource() == ventanaInforme.btnVolver) {

			ventanaInforme.dispose();
		}
	}
}
