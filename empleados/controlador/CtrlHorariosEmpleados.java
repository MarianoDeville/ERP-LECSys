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

	private ListadoDoble2 ventana;
	private DtosEmpleado dtosEmpleado;
	private boolean refresco = false;
	private boolean estado = false;
	private boolean puntaA;
	private boolean puntaB;
	
	public CtrlHorariosEmpleados(ListadoDoble2 vista) {
		
		this.ventana = vista;
		this.dtosEmpleado = new DtosEmpleado();
		this.ventana.btnVolver.addActionListener(this);
		this.ventana.btnGuardar.addActionListener(this);
		this.ventana.btnImprimir.addActionListener(this);
		this.ventana.btnCompletar.addActionListener(this);
		this.ventana.cmbBoxSector.addActionListener(this);
		this.ventana.cmbBoxGranularidad.addActionListener(this);
		this.ventana.tabla1.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 1) {

		    		dtosEmpleado.getInformacionEmpleado(ventana.tabla1.getSelectedRow());
		        	actualizaInfoEmpleado();
		        }
		      }
		  });
		this.ventana.tabla2.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		    	
		        if (e.getClickCount() == 2 && estado)
		        	selección(ventana.tabla2.getSelectedRow(), ventana.tabla2.getSelectedColumn());
		      }
		  });
		this.ventana.txtSuperior.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				actualizaEmpleados();
			}
		});
	}
	
	public void iniciar() {
		
		puntaA = false;
		puntaB = false;
		ventana.cmbBoxSector.setModel(new DefaultComboBoxModel<>(dtosEmpleado.getListaSectores()));
		ventana.cmbBoxGranularidad.setModel(new DefaultComboBoxModel<>(dtosEmpleado.getGranularidad()));
		ventana.cmbBoxGranularidad.setSelectedItem("30 min.");
		ventana.lblTxtMedio1.setText("Nombre:");
		ventana.lblTxtMedio2.setText("Carga horaria semanal:");
		ventana.lblTxtMedio3.setVisible(false);
		ventana.txtMedio3.setVisible(false);
		dtosEmpleado.limpiarInformacion();
		actualizaEmpleados();
		ventana.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ventana.cmbBoxSector) {
			
			actualizaEmpleados();
		}
		
		if(e.getSource() == ventana.cmbBoxGranularidad) {
		
			if(refresco)
				actualizaInfoEmpleado();
			else
				refresco = true;
		}
		
		if(e.getSource() == ventana.btnCompletar) {
			
			ventana.tabla2 = dtosEmpleado.autocompletar(ventana.tabla2);
		}
		
		if(e.getSource() == ventana.btnVolver) {
			
			ventana.dispose();
		}
		
		if(e.getSource() == ventana.btnGuardar) {
			
			
			
		}
		
		if(e.getSource() == ventana.btnImprimir) {
			
			
			
		}
	}
	
	private void actualizaEmpleados(){
		
		ventana.tabla1.setModel(dtosEmpleado.getListadoEmpleados((String)ventana.cmbBoxSector.getSelectedItem(),
																				 ventana.txtSuperior.getText()));
		ventana.tabla1.getColumnModel().getColumn(0).setPreferredWidth(40);
		ventana.tabla1.getColumnModel().getColumn(0).setMaxWidth(50);
		dtosEmpleado.limpiarInformacion();
		actualizaInfoEmpleado();
	}
	
	private void actualizaInfoEmpleado() {
		
		
		estado = ventana.cmbBoxSector.getSelectedItem().equals("Docente")?false:true;
		ventana.tabla2.setEnabled(estado);
		ventana.btnGuardar.setEnabled(estado);
		ventana.btnCompletar.setVisible(estado);
		ventana.cmbBoxGranularidad.setVisible(estado);
			
		if(dtosEmpleado.getApellido().length() > 0) {
			
			ventana.txtMedio1.setText(dtosEmpleado.getApellido() + ", " + dtosEmpleado.getNombre());	
			ventana.txtMedio2.setText(String.format("%.2f",dtosEmpleado.getCantidadHoras()));
			ventana.tabla2.setEnabled(estado);
			ventana.btnCompletar.setEnabled(estado);
			ventana.btnGuardar.setEnabled(estado);
		} else {
			
			ventana.txtMedio1.setText("");	
			ventana.txtMedio2.setText("");
			ventana.tabla2.setEnabled(false);
			ventana.btnCompletar.setEnabled(false);
			ventana.btnGuardar.setEnabled(false);
		}
		ventana.tabla2.setModel(dtosEmpleado.getHorarios(ventana.cmbBoxGranularidad.getSelectedIndex()));
		ventana.tabla2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		ventana.tabla2.doLayout();

		for(int i = 1 ; i < ventana.tabla2.getColumnCount() ; i++) {
			
			ventana.tabla2.getColumnModel().getColumn(i).setPreferredWidth(40);
		}
	}

	private void selección(int fila, int columna) {

		if(ventana.tabla2.getValueAt(fila, columna).equals(" ")) {
		
			if(!puntaA) {
	    	
				ventana.tabla2.setValueAt("C", fila, columna);
				puntaA = true;
			} else {
				
				ventana.tabla2.setValueAt("F", fila, columna);
				puntaA = false;			
			}
		} else if(ventana.tabla2.getValueAt(fila, columna).equals("O")) {
			
			if(!puntaB) {
		    	
				ventana.tabla2.setValueAt("CE", fila, columna);
				puntaB = true;
			} else {
				
				ventana.tabla2.setValueAt("FE", fila, columna);
				puntaB = false;			
			}
		} else {
			
			ventana.tabla2.setValueAt(" ", fila, columna);
		}
	}
}
