package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import interfaceUsuario.Listado;
import modelo.DtosCobros;

public class CtrlListadoCobros implements ActionListener {

	private Listado ventanaListadoCobros;
	private DtosCobros dtosCobros;
	private boolean bandera;
	
	public CtrlListadoCobros(Listado vista) {
		
		this.ventanaListadoCobros = vista;
		this.dtosCobros = new DtosCobros();
		this.ventanaListadoCobros.btnImprimir.addActionListener(this);
		this.ventanaListadoCobros.btnVolver.addActionListener(this);
		this.ventanaListadoCobros.btn1A.addActionListener(this);
		this.ventanaListadoCobros.comboBox1.addActionListener(this);
		this.ventanaListadoCobros.comboBox2.addActionListener(this);
	}
	
	public void iniciar() {
		
		bandera = false;
		ventanaListadoCobros.lblComboBox1.setVisible(true);
		ventanaListadoCobros.lblComboBox1.setText("Año:");
		ventanaListadoCobros.comboBox1.setVisible(true);
		ventanaListadoCobros.comboBox1.setModel(new DefaultComboBoxModel<String>(dtosCobros.getAños()));
		ventanaListadoCobros.lblComboBox2.setVisible(true);
		ventanaListadoCobros.lblComboBox2.setText("Mes:");
		ventanaListadoCobros.comboBox2.setVisible(true);
		ventanaListadoCobros.comboBox2.setModel(new DefaultComboBoxModel<String>(dtosCobros.getMeses()));
		ventanaListadoCobros.comboBox2.setSelectedIndex(dtosCobros.getMesActual());
		ventanaListadoCobros.lblTxt1.setVisible(true);
		ventanaListadoCobros.lblTxt1.setText("Suma:");
		ventanaListadoCobros.txt1.setVisible(true);
		ventanaListadoCobros.txt1.setColumns(7);
		ventanaListadoCobros.lblTxt2.setVisible(true);
		ventanaListadoCobros.lblTxt2.setText("Cant. operaciones:");
		ventanaListadoCobros.txt2.setVisible(true);
		ventanaListadoCobros.txt2.setColumns(4);
		ventanaListadoCobros.btn1A.setVisible(true);
		ventanaListadoCobros.btn1A.setText("Guardar");
		actualizar();
		ventanaListadoCobros.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ventanaListadoCobros.comboBox1) {
			
			actualizar();
		}
		
		if(e.getSource() == ventanaListadoCobros.comboBox2) {
			
			actualizar();
		}

		if(e.getSource() == ventanaListadoCobros.btn1A) {
			
			guardarCambio();
		}
		
		if(e.getSource() == ventanaListadoCobros.btnImprimir) {
			
			try {
				
				ventanaListadoCobros.tabla.print();
			} catch (PrinterException f) {
				
				JOptionPane.showMessageDialog(null, "Error al intentar imprimir.");
				CtrlLogErrores.guardarError(f.getMessage());
			}
		}

		if(e.getSource() == ventanaListadoCobros.btnVolver) {
			
			dtosCobros.setBorrarSeleccionados();
			ventanaListadoCobros.dispose();
		}
	}

	private void actualizar() {
		
		if(!bandera) {
			
			bandera = true;
			return;
		}
		ventanaListadoCobros.tabla.setModel(dtosCobros.getTablaCobros(ventanaListadoCobros.comboBox2.getSelectedIndex(),
																	  ventanaListadoCobros.comboBox1.getSelectedItem()));
		ventanaListadoCobros.tabla.getColumnModel().getColumn(0).setMaxWidth(90);
		ventanaListadoCobros.tabla.getColumnModel().getColumn(0).setPreferredWidth(70);
		
		ventanaListadoCobros.tabla.getColumnModel().getColumn(1).setMaxWidth(250);
		ventanaListadoCobros.tabla.getColumnModel().getColumn(1).setPreferredWidth(200);
		
		
		ventanaListadoCobros.tabla.getColumnModel().getColumn(3).setMaxWidth(70);
		ventanaListadoCobros.tabla.getColumnModel().getColumn(3).setPreferredWidth(60);
		ventanaListadoCobros.tabla.getColumnModel().getColumn(4).setMaxWidth(60);
		ventanaListadoCobros.tabla.getColumnModel().getColumn(4).setPreferredWidth(40);
		ventanaListadoCobros.tabla.getColumnModel().getColumn(5).setMaxWidth(100);
		ventanaListadoCobros.tabla.getColumnModel().getColumn(5).setPreferredWidth(95);
		ventanaListadoCobros.txt2.setText(dtosCobros.getCantidadElementosSeleccionados() + "");
		ventanaListadoCobros.txt1.setText(dtosCobros.getMontoTotal() + "");
	}
	
	private void guardarCambio() {
		
		String listaFacturas[] = new String[ventanaListadoCobros.tabla.getRowCount()];
		
		for(int i = 0; i < listaFacturas.length; i++) {
			
			listaFacturas[i] = (String) ventanaListadoCobros.tabla.getValueAt(i, 5);
		}
		
		if(!dtosCobros.setActualizarFacturas(listaFacturas))
			JOptionPane.showMessageDialog(null, "No se pudo actualizar la información.");
	}
}
