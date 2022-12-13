package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import interfaceUsuario.Cobro;
import interfaceUsuario.Listado;
import modelo.DtosAcceso;
import modelo.DtosCobros;

public class CtrolCobrarHabilitar implements ActionListener {

	private Listado ventanaCobrarHabilitar;
	private Cobro cobrarInscripción;
	private DtosCobros dtosCobros;
	private DtosAcceso dtosAcceso;
	
	public CtrolCobrarHabilitar(Listado vista) {
		
		this.ventanaCobrarHabilitar = vista;
		this.dtosAcceso = new DtosAcceso();
		this.dtosCobros = new DtosCobros();
		this.ventanaCobrarHabilitar.chckbx1.addActionListener(this);
		this.ventanaCobrarHabilitar.chckbx2.addActionListener(this);
		this.ventanaCobrarHabilitar.txt3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				actualizar();
			}
		});
		this.ventanaCobrarHabilitar.btn1A.addActionListener(this);
		this.ventanaCobrarHabilitar.btnImprimir.addActionListener(this);
		this.ventanaCobrarHabilitar.btnVolver.addActionListener(this);
	}
	
	public void iniciar() {
		
		ventanaCobrarHabilitar.btn1A.setText("Cobrar");
		ventanaCobrarHabilitar.btn1A.setVisible(true);
		ventanaCobrarHabilitar.chckbx1.setVisible(true);
		ventanaCobrarHabilitar.chckbx1.setEnabled(true);
		ventanaCobrarHabilitar.chckbx1.setSelected(false);
		ventanaCobrarHabilitar.chckbx1.setText("Reinscripción");
		ventanaCobrarHabilitar.chckbx2.setVisible(true);
		ventanaCobrarHabilitar.chckbx2.setEnabled(true);
		ventanaCobrarHabilitar.chckbx2.setSelected(false);
		ventanaCobrarHabilitar.chckbx2.setText("Todos");		
		ventanaCobrarHabilitar.txt3.setVisible(true);
		ventanaCobrarHabilitar.txt3.setEnabled(true);
		ventanaCobrarHabilitar.txt3.setEditable(true);
		actualizar();
		ventanaCobrarHabilitar.setVisible(true);
	}
	
	private boolean[] itemsSeleccionados() {
		
		boolean listaSeleccionados[] = new boolean[dtosCobros.getCantidadElementos()];
		
		for(int i = 0; i < dtosCobros.getCantidadElementos(); i++) {

			listaSeleccionados[i] = (boolean)ventanaCobrarHabilitar.tabla.getValueAt(i, 4);
		}
		return listaSeleccionados;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ventanaCobrarHabilitar.btn1A) {

			if(dtosAcceso.chkAcceso("Administrativo", "Cobrar y habilitar")) {
				
				dtosCobros.setAlumnosSeleccionados(itemsSeleccionados());
				cobrarInscripción = new Cobro("Cobrar inscripción y habilitar");
				CtrolCobrarInscripcion ctrlCobrarInscripción = new CtrolCobrarInscripcion(cobrarInscripción);
				cobrarInscripción.btnVolver.addActionListener(this);
				cobrarInscripción.btnCobrar.addActionListener(this);
				ctrlCobrarInscripción.iniciar();
			}
		}
		
		if(cobrarInscripción != null) {
			
			if(e.getSource() == cobrarInscripción.btnVolver) {
				
				ventanaCobrarHabilitar.txt3.setText("");
				ventanaCobrarHabilitar.chckbx1.setSelected(false);
				ventanaCobrarHabilitar.chckbx2.setSelected(false);
				actualizar();
			}
			
			if(e.getSource() == cobrarInscripción.btnCobrar) {
				
				ventanaCobrarHabilitar.txt3.setText("");
				ventanaCobrarHabilitar.chckbx1.setSelected(false);
				ventanaCobrarHabilitar.chckbx2.setSelected(false);
				actualizar();
			}
		}

		if(e.getSource() == ventanaCobrarHabilitar.chckbx1) {
			
			actualizar();
		}
		
		if(e.getSource() == ventanaCobrarHabilitar.chckbx2) {
			
			actualizar();
		}

		if(e.getSource() == ventanaCobrarHabilitar.btnImprimir) {
			
			try {
				
				ventanaCobrarHabilitar.tabla.print();
			} catch (PrinterException f) {
				
				f.printStackTrace();
			}
		}
		
		if(e.getSource() == ventanaCobrarHabilitar.btnVolver) {
			
			ventanaCobrarHabilitar.dispose();
		}
	}
	
	private void actualizar() {
		
		ventanaCobrarHabilitar.tabla.setModel(dtosCobros.getTablaAlumnos(ventanaCobrarHabilitar.chckbx1.isSelected(), 
																		 ventanaCobrarHabilitar.chckbx2.isSelected(), 
																		 ventanaCobrarHabilitar.txt3.getText()));
		ventanaCobrarHabilitar.tabla.getColumnModel().getColumn(0).setPreferredWidth(45);
		ventanaCobrarHabilitar.tabla.getColumnModel().getColumn(0).setMaxWidth(55);	
		ventanaCobrarHabilitar.tabla.getColumnModel().getColumn(4).setPreferredWidth(45);
		ventanaCobrarHabilitar.tabla.getColumnModel().getColumn(4).setMaxWidth(55);	
	}
}
