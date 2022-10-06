package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import interfaceUsuario.InterfaceBotones;
import modelo.DtosAcceso;
import interfaceUsuario.ABML;

public class CtrlEmpleados implements ActionListener {

	private InterfaceBotones ventanaPersonal;
	private DtosAcceso acceso;
	
	public CtrlEmpleados(InterfaceBotones vista) {
		
		this.ventanaPersonal = vista;
		this.acceso = new DtosAcceso();
		this.ventanaPersonal.btn1A.addActionListener(this);
		this.ventanaPersonal.btn1B.addActionListener(this);
		this.ventanaPersonal.btn2A.addActionListener(this);
		this.ventanaPersonal.btnVolver.addActionListener(this);
	}
	
	public void iniciar() {
		
		ventanaPersonal.lbl1A.setText("ABML");
		ventanaPersonal.lbl1A.setVisible(true);
		ventanaPersonal.btn1A.setIcon(new ImageIcon("C:\\LECSys 1.0\\Imagenes\\ABML.png"));
		ventanaPersonal.btn1A.setVisible(true);
		ventanaPersonal.lbl1B.setText("Asistencia");
		ventanaPersonal.lbl1B.setVisible(true);
		ventanaPersonal.btn1B.setIcon(new ImageIcon("C:\\LECSys 1.0\\Imagenes\\Asistencia.png"));
		ventanaPersonal.btn1B.setVisible(true);
		ventanaPersonal.lbl2A.setText("Horarios");
		ventanaPersonal.lbl2A.setVisible(true);
		ventanaPersonal.btn2A.setIcon(new ImageIcon("C:\\LECSys 1.0\\Imagenes\\Horarios.png"));
		ventanaPersonal.btn2A.setVisible(true);
		ventanaPersonal.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ventanaPersonal.btn1A) {
			
			if(acceso.chkAcceso("Empleados", "ABML")) {
			
				ABML ventanaABML = new ABML("ABML del personal");
				CtrlABMLEmpleados ctrlABMLEmpleados = new CtrlABMLEmpleados(ventanaABML);
				ctrlABMLEmpleados.iniciar();
			}
		}
		
		if(e.getSource() == ventanaPersonal.btn1B) {
			
			if(acceso.chkAcceso("Empleados", "Asistencia")) {

				
				
			}
		}
		
		if(e.getSource() == ventanaPersonal.btn2A) {
			
			if(acceso.chkAcceso("Empleados", "Horarios")) {

				
				
			}
		}
		
		if(e.getSource() == ventanaPersonal.btnVolver) {
			
			ventanaPersonal.dispose();
		}
		
	}

}