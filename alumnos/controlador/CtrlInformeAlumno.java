package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JOptionPane;
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
		ventanaInforme.txtProfesor.setEditable(false);
		ventanaInforme.txtProfesor.setText(dtosAlumno.getNombreProfesor());
		dtosAlumno.cargarAsistencia();
		ventanaInforme.txtPresentismo.setEditable(false);
		ventanaInforme.txtPresentismo.setText(dtosAlumno.getPresente());
		ventanaInforme.txtFaltas.setEditable(false);
		ventanaInforme.txtFaltas.setText(dtosAlumno.getAusente());
		ventanaInforme.txtTarde.setEditable(false);
		ventanaInforme.txtTarde.setText(dtosAlumno.getTarde());
		dtosAlumno.cargarNotas();
		ventanaInforme.txtEscrito1.setEditable(false);
		ventanaInforme.txtEscrito1.setText(dtosAlumno.getEscrito1());
		ventanaInforme.txtEscrito2.setEditable(false);
		ventanaInforme.txtEscrito2.setText(dtosAlumno.getEscrito2());
		ventanaInforme.txtFinalEscrito.setEditable(false);
		ventanaInforme.txtFinalEscrito.setText(dtosAlumno.getFinalEscrito());
		ventanaInforme.txtComportamiento1.setEditable(false);
		ventanaInforme.txtComportamiento1.setText(dtosAlumno.getComportamiento1());
		ventanaInforme.txtComportamiento2.setEditable(false);
		ventanaInforme.txtComportamiento2.setText(dtosAlumno.getComportamiento2());
		ventanaInforme.txtFinalComportamiento.setEditable(false);
		ventanaInforme.txtFinalComportamiento.setText(dtosAlumno.getFinalComportamiento());
		ventanaInforme.txtOral1.setEditable(false);
		ventanaInforme.txtOral1.setText(dtosAlumno.getOral1());
		ventanaInforme.txtOral2.setEditable(false);
		ventanaInforme.txtOral2.setText(dtosAlumno.getOral2());
		ventanaInforme.txtFinalOral.setEditable(false);
		ventanaInforme.txtFinalOral.setText(dtosAlumno.getFinalOral());
		ventanaInforme.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ventanaInforme.btnImprimir) {

			Color colorPanel = ventanaInforme.panel.getBackground();
			ventanaInforme.panel.setBackground(Color.WHITE);
			ventanaInforme.btnImprimir.setVisible(false);
			ventanaInforme.btnVolver.setVisible(false);
			PrinterJob imprimir = PrinterJob.getPrinterJob();
			PageFormat preformat = imprimir.defaultPage();
			PageFormat postformat = imprimir.pageDialog(preformat);
			imprimir.setPrintable(new Printer(ventanaInforme.panel), postformat);
			
			if (imprimir.printDialog()) {
				
				try {
					
					imprimir.print();
				} catch (PrinterException f) {
					
					JOptionPane.showMessageDialog(null, "Error al intentar imprimir.");
					CtrlLogErrores.guardarError(f.getMessage());
				}
			}
			
			ventanaInforme.panel.setBackground(colorPanel);
			ventanaInforme.btnImprimir.setVisible(true);
			ventanaInforme.btnVolver.setVisible(true);
		}		
		
		if(e.getSource() == ventanaInforme.btnVolver) {

			ventanaInforme.dispose();
		}
	}
}
