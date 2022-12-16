package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import interfaceUsuario.ListadoDoble2;
import modelo.DtosEmpleado;

public class CtrlHorariosEmpleados implements ActionListener {

	private ListadoDoble2 ventanaHorarios;
	private DtosEmpleado dtosEmpleado;
	boolean refresco = false;
	
	public CtrlHorariosEmpleados(ListadoDoble2 vista) {
		
		this.ventanaHorarios = vista;
		this.dtosEmpleado = new DtosEmpleado();
		this.ventanaHorarios.btnVolver.addActionListener(this);
		this.ventanaHorarios.btnGuardar.addActionListener(this);
		this.ventanaHorarios.btnImprimir.addActionListener(this);
		this.ventanaHorarios.btnCompletar.addActionListener(this);
		this.ventanaHorarios.cmbBoxSector.addActionListener(this);
		this.ventanaHorarios.cmbBoxGranularidad.addActionListener(this);
		this.ventanaHorarios.tabla1.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 1) {

		    		dtosEmpleado.getInformacionEmpleado(ventanaHorarios.tabla1.getSelectedRow());
		        	actualizaInfoEmpleado();
		        }
		      }
		  });
		this.ventanaHorarios.txtSuperior.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				actualizaEmpleados();
			}
		});
	}
	
	public void iniciar() {
		
		ventanaHorarios.cmbBoxSector.setModel(new DefaultComboBoxModel<>(dtosEmpleado.getListaSectores()));
		ventanaHorarios.cmbBoxGranularidad.setModel(new DefaultComboBoxModel<>(dtosEmpleado.getGranularidad()));
		ventanaHorarios.cmbBoxGranularidad.setSelectedItem("30 min.");
		ventanaHorarios.lblTxtMedio1.setText("Nombre:");
		ventanaHorarios.lblTxtMedio2.setText("Carga horaria semanal:");
		ventanaHorarios.lblTxtMedio3.setVisible(false);
		ventanaHorarios.txtMedio3.setVisible(false);
		dtosEmpleado.limpiarInformacion();
		actualizaEmpleados();
		ventanaHorarios.setVisible(true);
	}
	
	private void actualizaEmpleados(){
		
		ventanaHorarios.tabla1.setModel(dtosEmpleado.getListadoEmpleados((String)ventanaHorarios.cmbBoxSector.getSelectedItem(),
																				 ventanaHorarios.txtSuperior.getText()));
		ventanaHorarios.tabla1.getColumnModel().getColumn(0).setPreferredWidth(40);
		ventanaHorarios.tabla1.getColumnModel().getColumn(0).setMaxWidth(50);
		dtosEmpleado.limpiarInformacion();
		actualizaInfoEmpleado();
	}
	
	private void actualizaInfoEmpleado() {
		
		String nombre = dtosEmpleado.getApellido().length() > 0? dtosEmpleado.getApellido() + ", " + dtosEmpleado.getNombre():"";
		ventanaHorarios.txtMedio1.setText(nombre);
		ventanaHorarios.txtMedio2.setText(String.format("%.2f",dtosEmpleado.getCantidadHoras()));
		ventanaHorarios.tabla2.setModel(dtosEmpleado.getHorarios(ventanaHorarios.cmbBoxGranularidad.getSelectedIndex()));
		ventanaHorarios.tabla2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		ventanaHorarios.tabla2.doLayout();
		
		if(ventanaHorarios.cmbBoxSector.getSelectedItem().equals("Docente")) {
			
			ventanaHorarios.tabla2.setEnabled(false);
			ventanaHorarios.btnCompletar.setVisible(false);
			ventanaHorarios.cmbBoxGranularidad.setVisible(false);
			ventanaHorarios.cmbBoxGranularidad.setSelectedItem("30 min.");
		} else {
	
			ventanaHorarios.tabla2.setEnabled(true);
			ventanaHorarios.btnCompletar.setVisible(true);
			ventanaHorarios.cmbBoxGranularidad.setVisible(true);
		}

		for(int i = 1 ; i < ventanaHorarios.tabla2.getColumnCount() ; i++) {
			
			ventanaHorarios.tabla2.getColumnModel().getColumn(i).setPreferredWidth(40);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ventanaHorarios.cmbBoxSector) {
			
			actualizaEmpleados();
		}
		
		if(e.getSource() == ventanaHorarios.cmbBoxGranularidad) {
		
			if(refresco)
				actualizaInfoEmpleado();
			else
				refresco = true;
		}
		
		if(e.getSource() == ventanaHorarios.btnCompletar) {
			
			
			
			
		}
		
		if(e.getSource() == ventanaHorarios.btnVolver) {
			
			ventanaHorarios.dispose();
		}
		
		if(e.getSource() == ventanaHorarios.btnGuardar) {
			
			
			
		}
		
		if(e.getSource() == ventanaHorarios.btnImprimir) {
			
			
			
		}
	}
}
