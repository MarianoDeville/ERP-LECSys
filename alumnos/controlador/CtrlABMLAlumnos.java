package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import interfaceUsuario.ABML;
import interfaceUsuario.Nuevo;
import modelo.DtosAcceso;
import modelo.DtosAlumno;

public class CtrlABMLAlumnos implements ActionListener {

	private ABML ventanaABML;
	private DtosAlumno dtosABMLAlumnos;
	private Nuevo ventanaNuevoAlumno;
	private Nuevo ventanaEditarAlumno;
	private DtosAcceso acceso;
			
	public CtrlABMLAlumnos(ABML vista) {
		
		this.ventanaABML = vista;
		this.dtosABMLAlumnos = new DtosAlumno();
		this.acceso = new DtosAcceso();
		this.ventanaABML.btnNuevo.addActionListener(this);
		this.ventanaABML.btnEditar.addActionListener(this);
		this.ventanaABML.btnImprimir.addActionListener(this);
		this.ventanaABML.btnVolver.addActionListener(this);
		this.ventanaABML.chckbx1.addActionListener(this);
		this.ventanaABML.comboBox1.addActionListener(this);
		this.ventanaABML.txt1.addActionListener(this);
	}
	
	public void iniciar() {
		
		ventanaABML.chckbx1.setVisible(true);
		ventanaABML.comboBox1.setVisible(true);
		ventanaABML.txt1.setVisible(true);
		ventanaABML.txt1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				actualizar();
			}
		});
		ventanaABML.comboBox1.setModel(new DefaultComboBoxModel<String>(dtosABMLAlumnos.getOrdenamiento()));
		actualizar();
		ventanaABML.setVisible(true);
	}
	
	private void actualizar() {
		
		ventanaABML.tabla.setModel(dtosABMLAlumnos.getTablaAlumnos("",ventanaABML.txt1.getText()
																	 ,ventanaABML.chckbx1.isSelected()
																	 , ventanaABML.comboBox1.getSelectedIndex()));
		ventanaABML.tabla.getColumnModel().getColumn(0).setPreferredWidth(40);
		ventanaABML.tabla.getColumnModel().getColumn(0).setMaxWidth(50);
		ventanaABML.tabla.getColumnModel().getColumn(3).setPreferredWidth(65);
		ventanaABML.tabla.getColumnModel().getColumn(3).setMaxWidth(70);
		ventanaABML.tabla.getColumnModel().getColumn(5).setPreferredWidth(80);
		ventanaABML.tabla.getColumnModel().getColumn(5).setMaxWidth(80);
		ventanaABML.tabla.getColumnModel().getColumn(7).setPreferredWidth(80);
		ventanaABML.tabla.getColumnModel().getColumn(7).setMaxWidth(80);
		ventanaABML.tabla.getColumnModel().getColumn(8).setPreferredWidth(30);
		ventanaABML.tabla.getColumnModel().getColumn(8).setMaxWidth(30);
		dtosABMLAlumnos.setEstado(ventanaABML.chckbx1.isSelected());
	}
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == ventanaABML.btnNuevo) {
			
			if(acceso.chkAcceso("Alumnos", "Crear")) {
				
				ventanaNuevoAlumno = new Nuevo("Nuevo alumno.");
				CtrlNuevoAlumno ctrlNuevoAlumno = new CtrlNuevoAlumno(ventanaNuevoAlumno);
				ctrlNuevoAlumno.iniciar();
				ventanaNuevoAlumno.btnVolver.addActionListener(this);
			}
		}
		
		if(ventanaNuevoAlumno != null) {
			
			if(e.getSource() == ventanaNuevoAlumno.btnVolver) {
				
				actualizar();
			}
		}
		
		if(e.getSource() == ventanaABML.btnEditar) {
			
			if(acceso.chkAcceso("Alumnos", "Editar")) {
				
				int i = 0;
				String legajo = null;
				
				while(i < ventanaABML.tabla.getRowCount()) {
					
					if((boolean)ventanaABML.tabla.getValueAt(i, 8)) {
						
						legajo = (String)ventanaABML.tabla.getValueAt(i, 0);
						break;
					}
					i++;
				}
				
				if(legajo != null) {
					ventanaEditarAlumno = new Nuevo("Edición de alumno.");
					CtrlEditarAlumno ctrolEditarAlumno = new CtrlEditarAlumno(ventanaEditarAlumno);
					ctrolEditarAlumno.iniciar(legajo);
					ventanaEditarAlumno.btnVolver.addActionListener(this);
				} else {
					
					JOptionPane.showMessageDialog(null, "Debe seleccionar un alumno para editar.");
				}
			}
		}
		
		if(ventanaEditarAlumno != null) {
			
			if(e.getSource() == ventanaEditarAlumno.btnVolver) {
				
				actualizar();
			}
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
