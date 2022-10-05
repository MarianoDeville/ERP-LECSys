package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import interfaceUsuario.ABML;
import interfaceUsuario.Nuevo;
import modelo.DtosABMLEmpleados;

public class CtrlABMLEmpleados implements ActionListener {

	private ABML ventanaABML;
	private DtosABMLEmpleados dtosABMLEmpleados;
	private Nuevo ventanaNuevoEmpleado;
	
	public CtrlABMLEmpleados(ABML vista) {
		
		this.ventanaABML = vista;
		this.dtosABMLEmpleados = new DtosABMLEmpleados();
		this.ventanaABML.btnNuevo.addActionListener(this);
		this.ventanaABML.btnEditar.addActionListener(this);
		this.ventanaABML.btnImprimir.addActionListener(this);
		this.ventanaABML.btnVolver.addActionListener(this);
		this.ventanaABML.chckbx1.addActionListener(this);
		this.ventanaABML.comboBox1.addActionListener(this);
		this.ventanaABML.txt1.addActionListener(this);
	}
	
	public void iniciar() {
		
		ventanaABML.txt1.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
	
				actualizar();
			}
		});
		
		ventanaABML.chckbx1.setVisible(true);
		ventanaABML.comboBox1.setModel(new DefaultComboBoxModel<String>(dtosABMLEmpleados.getFiltro()));
		ventanaABML.comboBox1.setVisible(true);
		ventanaABML.txt1.setVisible(true);
		actualizar();
		ventanaABML.setVisible(true);
	}
	
	private void actualizar() {
		
		ventanaABML.tabla.setModel(dtosABMLEmpleados.getTablaEmpleados(
															(String)ventanaABML.comboBox1.getSelectedItem(), 
															ventanaABML.chckbx1.isSelected(), 
															ventanaABML.txt1.getText()));
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ventanaABML.btnNuevo) {
			
			ventanaNuevoEmpleado = new Nuevo("Cargar nuevo empleado");
			CtrlNuevoEmpleado ctrlNuevoEmpleado = new CtrlNuevoEmpleado(ventanaNuevoEmpleado);
			ctrlNuevoEmpleado.iniciar();
			ventanaNuevoEmpleado.btnVolver.addActionListener(this);
			
		}
		if(ventanaNuevoEmpleado != null) {
			
			if(e.getSource() == ventanaNuevoEmpleado.btnVolver) {
	
				actualizar();
			}
		}

		if(e.getSource() == ventanaABML.comboBox1) {
			
			actualizar();
		}
		
		if(e.getSource() == ventanaABML.chckbx1) {
			
			actualizar();
		}
		
		if(e.getSource() == ventanaABML.txt1) {
			

		}
		
		if(e.getSource() == ventanaABML.btnEditar) {
			
			
		}
		
		if(e.getSource() == ventanaABML.btnImprimir) {
			
			
		}

		if(e.getSource() == ventanaABML.btnVolver) {
			
			ventanaABML.dispose();
		}
	}
}
