package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import javax.swing.DefaultComboBoxModel;
import interfaceUsuario.ABML;
import interfaceUsuario.Nuevo;
import modelo.DtosABMLAlumnos;

public class CtrlABMLAlumnos implements ActionListener {

	private ABML ventanaABML;
	private DtosABMLAlumnos dtosABMLAlumnos;
			
	public CtrlABMLAlumnos(ABML vista) {
		
		this.ventanaABML = vista;
		this.dtosABMLAlumnos = new DtosABMLAlumnos();
		this.ventanaABML.btnNuevo.addActionListener(this);
		this.ventanaABML.btnBuscar.addActionListener(this);
		this.ventanaABML.btnEditar.addActionListener(this);
		this.ventanaABML.btnImprimir.addActionListener(this);
		this.ventanaABML.btnVolver.addActionListener(this);
		this.ventanaABML.chckbx1.addActionListener(this);
		this.ventanaABML.comboBox1.addActionListener(this);
		this.ventanaABML.txt1.addActionListener(this);
	}
	
	public void iniciar() {
		
		ventanaABML.btnBuscar.setVisible(true);
		ventanaABML.chckbx1.setVisible(true);
		ventanaABML.comboBox1.setVisible(true);
		ventanaABML.txt1.setVisible(true);
		ventanaABML.comboBox1.setModel(new DefaultComboBoxModel<String>(dtosABMLAlumnos.getOrdenamiento()));
		actualizar();
		ventanaABML.setVisible(true);
	}
	
	private void actualizar() {
		
		ventanaABML.tabla.setModel(dtosABMLAlumnos.getTablaAlumnos("","",true, ventanaABML.comboBox1.getSelectedIndex()));
	}
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == ventanaABML.btnNuevo) {
			
			Nuevo ventanaNuevoAlumno = new Nuevo("Nuevo alumno");
			CtrlNuevoAlumno ctrlNuevoAlumno = new CtrlNuevoAlumno(ventanaNuevoAlumno);
			ctrlNuevoAlumno.iniciar();
		}
		
		if(e.getSource() == ventanaABML.btnBuscar) {
			

		}
		
		if(e.getSource() == ventanaABML.btnEditar) {
			

		}
		
		if(e.getSource() == ventanaABML.btnImprimir) {
			
			try {
				
				ventanaABML.tabla.print();
			} catch (PrinterException f) {
				
				f.printStackTrace();
			}
		}
		
		if(e.getSource() == ventanaABML.chckbx1) {
			
			actualizar();
		}
		
		if(e.getSource() == ventanaABML.comboBox1) {
			
			actualizar();
		}
		
		if(e.getSource() == ventanaABML.txt1) {
			
			actualizar();
		}
		
		if(e.getSource() == ventanaABML.btnVolver) {
			
			ventanaABML.dispose();
		}
	}
}
