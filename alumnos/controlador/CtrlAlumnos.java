package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import interfaceUsuario.InterfaceBotones;
import interfaceUsuario.ABML;
import interfaceUsuario.Listado;
import modelo.DtosAcceso;

public class CtrlAlumnos implements ActionListener {

	private InterfaceBotones ventanaAlumnos;
	private DtosAcceso acceso;
	
	public CtrlAlumnos(InterfaceBotones vista) {
		
		this.ventanaAlumnos = vista;
		this.acceso = new DtosAcceso();
		this.ventanaAlumnos.btn1A.addActionListener(this);
		this.ventanaAlumnos.btn1B.addActionListener(this);
		this.ventanaAlumnos.btn2A.addActionListener(this);
		this.ventanaAlumnos.btn2B.addActionListener(this);
		this.ventanaAlumnos.btnVolver.addActionListener(this);
	}
	
	public void iniciar() {
		
		ventanaAlumnos.lbl1A.setText("ABML alumnos");
		ventanaAlumnos.lbl1A.setVisible(true);
		ventanaAlumnos.btn1A.setIcon(new ImageIcon("C:\\LECSys 1.0\\Imagenes\\ABML.png"));
		ventanaAlumnos.btn1A.setVisible(true);
		ventanaAlumnos.lbl1B.setText("Listado");
		ventanaAlumnos.lbl1B.setVisible(true);
		ventanaAlumnos.btn1B.setIcon(new ImageIcon("C:\\LECSys 1.0\\Imagenes\\Listado.png"));
		ventanaAlumnos.btn1B.setVisible(true);
		ventanaAlumnos.lbl2A.setText("Asistencia");
		ventanaAlumnos.lbl2A.setVisible(true);
		ventanaAlumnos.btn2A.setIcon(new ImageIcon("C:\\LECSys 1.0\\Imagenes\\Asistencia.png"));
		ventanaAlumnos.btn2A.setVisible(true);
		ventanaAlumnos.lbl2B.setText("Exámenes");
		ventanaAlumnos.lbl2B.setVisible(true);
		ventanaAlumnos.btn2B.setIcon(new ImageIcon("C:\\LECSys 1.0\\Imagenes\\Examenes.png"));
		ventanaAlumnos.btn2B.setVisible(true);
		ventanaAlumnos.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ventanaAlumnos.btn1A) {
			
			if(acceso.chkAcceso("Alumnos", "ABML")) {
			
				ABML ventanaABML = new ABML("Alta, Baja, Modificación y Listado");
				CtrlABMLAlumnos ctrlABMLPersonal = new CtrlABMLAlumnos(ventanaABML);
				ctrlABMLPersonal.iniciar();
			}
		}
		
		if(e.getSource() == ventanaAlumnos.btn1B) {
			
			if(acceso.chkAcceso("Alumnos", "Listado")) {
			
				Listado ventanaListado = new Listado("Listado");
				CtrlListado ctrlListado = new CtrlListado(ventanaListado);
				ctrlListado.iniciar();
			}
		}
		
		if(e.getSource() == ventanaAlumnos.btn2A) {
			
			if(acceso.chkAcceso("Alumnos", "Asistencia")) {
			
				Listado ventanaAsistencia = new Listado("Asistencia");
				CtrlAsistenciaAlumnos ctrlAsistenciaAlumnos = new CtrlAsistenciaAlumnos(ventanaAsistencia);
				ctrlAsistenciaAlumnos.iniciar();
			}
		}
		
		if(e.getSource() == ventanaAlumnos.btn2B) {
			
			if(acceso.chkAcceso("Alumnos", "Examenes")) {
			
				Listado ventanaExamenes = new Listado("Examenes");
				CtrlExamenes ctrlExamenes = new CtrlExamenes(ventanaExamenes);
				ctrlExamenes.iniciar();
			}
		}
		
		if(e.getSource() == ventanaAlumnos.btnVolver) {
			
			ventanaAlumnos.dispose();
		}
	}
}