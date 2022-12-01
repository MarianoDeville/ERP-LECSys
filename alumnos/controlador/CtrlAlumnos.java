package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import interfaceUsuario.InterfaceBotones;
import interfaceUsuario.ABML;
import interfaceUsuario.Listado;
import interfaceUsuario.ListadoDoble;
import modelo.DtosAcceso;
import modelo.DtosConfiguracion;

public class CtrlAlumnos implements ActionListener {

	private InterfaceBotones ventanaAlumnos;
	private DtosAcceso acceso;
	
	public CtrlAlumnos(InterfaceBotones vista) {
		
		this.ventanaAlumnos = vista;
		this.acceso = new DtosAcceso();
		this.ventanaAlumnos.btn1A.addActionListener(this);
		this.ventanaAlumnos.btn1B.addActionListener(this);
		this.ventanaAlumnos.btn1C.addActionListener(this);
		this.ventanaAlumnos.btn2A.addActionListener(this);
		this.ventanaAlumnos.btn2B.addActionListener(this);
		this.ventanaAlumnos.btn2C.addActionListener(this);
		this.ventanaAlumnos.btnVolver.addActionListener(this);
	}
	
	public void iniciar() {
		
		ventanaAlumnos.lbl1A.setText("ABML alumnos");
		ventanaAlumnos.lbl1A.setVisible(true);
		ventanaAlumnos.btn1A.setIcon(new ImageIcon(DtosConfiguracion.getDirectorio() + "\\Imagenes\\ABML.png"));
		ventanaAlumnos.btn1A.setVisible(true);
		ventanaAlumnos.lbl1B.setText("Listado");
		ventanaAlumnos.lbl1B.setVisible(true);
		ventanaAlumnos.btn1B.setIcon(new ImageIcon(DtosConfiguracion.getDirectorio() + "\\Imagenes\\Listado.png"));
		ventanaAlumnos.btn1B.setVisible(true);
		ventanaAlumnos.lbl1C.setText("Exámenes");
		ventanaAlumnos.lbl1C.setVisible(true);
		ventanaAlumnos.btn1C.setIcon(new ImageIcon(DtosConfiguracion.getDirectorio() + "\\Imagenes\\Examenes.png"));
		ventanaAlumnos.btn1C.setVisible(true);
		ventanaAlumnos.lbl2A.setText("Tomar asistencia");
		ventanaAlumnos.lbl2A.setVisible(true);
		ventanaAlumnos.btn2A.setIcon(new ImageIcon(DtosConfiguracion.getDirectorio() + "\\Imagenes\\Asistencia.png"));
		ventanaAlumnos.btn2A.setVisible(true);
		ventanaAlumnos.lbl2B.setText("Registro asistencia");
		ventanaAlumnos.lbl2B.setVisible(true);
		ventanaAlumnos.btn2B.setIcon(new ImageIcon(DtosConfiguracion.getDirectorio() + "\\Imagenes\\Registro asistencia.png"));
		ventanaAlumnos.btn2B.setVisible(true);
		ventanaAlumnos.lbl2C.setText("Grupo familiar");
		ventanaAlumnos.lbl2C.setVisible(true);
		ventanaAlumnos.btn2C.setIcon(new ImageIcon(DtosConfiguracion.getDirectorio() + "\\Imagenes\\Grupo familiar.png"));
		ventanaAlumnos.btn2C.setVisible(true);
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
		
		if(e.getSource() == ventanaAlumnos.btn1C) {
			
			if(acceso.chkAcceso("Alumnos", "Examenes")) {
			
				Listado ventanaExamenes = new Listado("Examenes");
				CtrlExamenes ctrlExamenes = new CtrlExamenes(ventanaExamenes);
				ctrlExamenes.iniciar();
			}
		}
		
		if(e.getSource() == ventanaAlumnos.btn2A) {
			
			if(acceso.chkAcceso("Alumnos", "Tomar asistencia")) {
			
				Listado ventanaAsistencia = new Listado("Tomar asistencia");
				CtrlAsistenciaAlumnos ctrlAsistenciaAlumnos = new CtrlAsistenciaAlumnos(ventanaAsistencia);
				ctrlAsistenciaAlumnos.iniciar();
			}
		}
		
		if(e.getSource() == ventanaAlumnos.btn2B) {
			
			if(acceso.chkAcceso("Alumnos", "Registro asistencia")) {
			
				Listado ventanaRegistroAsistencia = new Listado("Registro asistencia");
				CtrlRegistroAsistencia ctrlRegistroAsistencias = new CtrlRegistroAsistencia(ventanaRegistroAsistencia);
				ctrlRegistroAsistencias.iniciar();
			}
		}
		
		if(e.getSource() == ventanaAlumnos.btn2C) {
			
			if(acceso.chkAcceso("Alumnos", "Grupo familiar")) {
			
				ListadoDoble ventanaGrupoFamiliar = new ListadoDoble("Gestión de grupos familiares");
				CtrlGrupoFamiliar ctrlGrupoFamiliar = new CtrlGrupoFamiliar(ventanaGrupoFamiliar);
				ctrlGrupoFamiliar.iniciar();
			}
		}
		
		if(e.getSource() == ventanaAlumnos.btnVolver) {
			
			ventanaAlumnos.dispose();
		}
	}
}