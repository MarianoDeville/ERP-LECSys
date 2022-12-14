package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import interfaceUsuario.ABML;
import interfaceUsuario.Nuevo;
import modelo.DtosAcceso;
import modelo.DtosEmpleado;

public class CtrlABMLEmpleados implements ActionListener {

	private ABML ventanaABML;
	private DtosEmpleado dtosABMLEmpleados;
	private Nuevo ventanaNuevoEmpleado;
	private Nuevo ventanaEditarEmpleado;
	private DtosAcceso acceso;
	private int elemento;
	
	public CtrlABMLEmpleados(ABML vista) {
		
		this.ventanaABML = vista;
		this.acceso = new DtosAcceso();
		this.dtosABMLEmpleados = new DtosEmpleado();
		this.ventanaABML.btnNuevo.addActionListener(this);
		this.ventanaABML.btnEditar.addActionListener(this);
		this.ventanaABML.btnImprimir.addActionListener(this);
		this.ventanaABML.btnVolver.addActionListener(this);
		this.ventanaABML.chckbx1.addActionListener(this);
		this.ventanaABML.comboBox1.addActionListener(this);
		this.ventanaABML.txt1.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
	
				actualizar();
			}
		});
		this.ventanaABML.tabla.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 1) {

					elemento = ventanaABML.tabla.getSelectedRow();
					limpiarOtros();
		        }
		      }
		  });
	}
	
	public void iniciar() {
		
		ventanaABML.chckbx1.setVisible(true);
		ventanaABML.comboBox1.setModel(new DefaultComboBoxModel<String>(dtosABMLEmpleados.getFiltro()));
		ventanaABML.comboBox1.setVisible(true);
		ventanaABML.txt1.setVisible(true);
		actualizar();
		ventanaABML.setVisible(true);
	}
	
	private void actualizar() {
		
		ventanaABML.tabla.setModel(dtosABMLEmpleados.getTablaEmpleados((String)ventanaABML.comboBox1.getSelectedItem(), 
																		ventanaABML.chckbx1.isSelected(), 
																		ventanaABML.txt1.getText()));
		ventanaABML.tabla.getColumnModel().getColumn(0).setPreferredWidth(40);
		ventanaABML.tabla.getColumnModel().getColumn(0).setMaxWidth(50);
		ventanaABML.tabla.getColumnModel().getColumn(10).setPreferredWidth(40);
		ventanaABML.tabla.getColumnModel().getColumn(10).setMaxWidth(50);
	}
	
	private void limpiarOtros() {
		
		for(int i = 0; i < ventanaABML.tabla.getRowCount(); i++) {
			
			if(i != elemento)
				ventanaABML.tabla.setValueAt((boolean) false, i, 10);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ventanaABML.comboBox1) {
			
			actualizar();
		}
		
		if(e.getSource() == ventanaABML.chckbx1) {
			
			actualizar();
		}
		
		if(e.getSource() == ventanaABML.btnNuevo) {
			
			if(acceso.chkAcceso("Empleados", "Crear")) {
				
				ventanaNuevoEmpleado = new Nuevo("Cargar nuevo empleado");
				CtrlNuevoEmpleado ctrlNuevoEmpleado = new CtrlNuevoEmpleado(ventanaNuevoEmpleado);
				ctrlNuevoEmpleado.iniciar();
				ventanaNuevoEmpleado.btnVolver.addActionListener(this);
			}
		}
		
		if(ventanaNuevoEmpleado != null) {
			
			if(e.getSource() == ventanaNuevoEmpleado.btnVolver) {
	
				actualizar();
			}
		}
	
		if(e.getSource() == ventanaABML.btnEditar) {
			
			if(acceso.chkAcceso("Empleados", "Editar")) {

				int i = 0;
				String legajo = null;
				
				while(i < ventanaABML.tabla.getRowCount()) {
					
					if((boolean)ventanaABML.tabla.getValueAt(i, 10)) {
						
						legajo = (String)ventanaABML.tabla.getValueAt(i, 0);
						break;
					}
					i++;
				}
				if(legajo != null) {

					dtosABMLEmpleados.getInformacionEmpleado(i);
					ventanaEditarEmpleado = new Nuevo("Editar datos empleado");
					CtrlEditarEmpleado ctrlEditarEmpleado = new CtrlEditarEmpleado(ventanaEditarEmpleado);
					ctrlEditarEmpleado.iniciar(legajo);
					ventanaEditarEmpleado.btnVolver.addActionListener(this);
				} else {
					
					JOptionPane.showMessageDialog(null, "Debe seleccionar un alumno para editar.");
				}
			}
		}
		
		if(ventanaEditarEmpleado != null) {

			if(e.getSource() == ventanaEditarEmpleado.btnVolver) {
	
				actualizar();
			}
		}
		
		if(e.getSource() == ventanaABML.btnImprimir) {
			
			try {
				
				ventanaABML.tabla.print();
			} catch (PrinterException f) {
				
				JOptionPane.showMessageDialog(null, "Error al intentar imprimir.");
				CtrlLogErrores.guardarError(f.getMessage());
			}
		}

		if(e.getSource() == ventanaABML.btnVolver) {
			
			ventanaABML.dispose();
		}
	}
}
