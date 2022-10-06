package controlador;

import interfaceUsuario.Listado;
import modelo.DtosActividad;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class CtrlActividad implements ActionListener {

	private Listado ventanaActividad;
	private DtosActividad dtosActividad;
	private DefaultTableModel tablaModelo;
	private String tabla[][];
	
	public CtrlActividad(Listado vista) {
		
		this.ventanaActividad = vista;
		this.dtosActividad = new DtosActividad();
		this.ventanaActividad.comboBox1.addActionListener(this);
		this.ventanaActividad.comboBox2.addActionListener(this);
		this.ventanaActividad.btnImprimir.addActionListener(this);
		this.ventanaActividad.btnVolver.addActionListener(this);
	}
		
	public void iniciar() {
		
		ventanaActividad.lblComboBox1.setText("Mes:");
		ventanaActividad.lblComboBox1.setVisible(true);
		ventanaActividad.comboBox1.setVisible(true);
		ventanaActividad.lblComboBox2.setText("Año:");
		ventanaActividad.lblComboBox2.setVisible(true);
		ventanaActividad.comboBox2.setVisible(true);
		ventanaActividad.comboBox1.setModel(new DefaultComboBoxModel<String>(dtosActividad.getMeses()));
		ventanaActividad.comboBox2.setModel(new DefaultComboBoxModel<String>(dtosActividad.getAños()));
		actualizar();
		ventanaActividad.setVisible(true);
	}
	
	private void actualizar() {
		
		tabla = dtosActividad.getActividad(ventanaActividad.comboBox1.getSelectedIndex() + 1 + "", (String)ventanaActividad.comboBox2.getSelectedItem());
		tablaModelo = new DefaultTableModel(tabla, dtosActividad.getTituloTabla());
		ventanaActividad.tabla.setModel(tablaModelo);
		ventanaActividad.tabla.getColumnModel().getColumn(0).setMaxWidth(30);
		ventanaActividad.tabla.getColumnModel().getColumn(1).setMaxWidth(90);
		ventanaActividad.tabla.getColumnModel().getColumn(2).setMaxWidth(90);
		ventanaActividad.tabla.getColumnModel().getColumn(3).setMaxWidth(70);
		ventanaActividad.tabla.getColumnModel().getColumn(6).setMaxWidth(110);
		ventanaActividad.comboBox1.setSelectedIndex(dtosActividad.getMesActual());
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ventanaActividad.comboBox1) {
			
			actualizar();
		}
		
		if(e.getSource() == ventanaActividad.comboBox2) {
			
			actualizar();
		}
		
		if(e.getSource() == ventanaActividad.btnImprimir) {
			
			try {
				
				ventanaActividad.tabla.print();
			} catch (PrinterException f) {
				
				f.printStackTrace();
			}
		}

		if(e.getSource() == ventanaActividad.btnVolver) {
			
			ventanaActividad.dispose();
		}
	}
	
}
