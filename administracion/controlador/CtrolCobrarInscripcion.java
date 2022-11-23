package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaceUsuario.Cobro;
import modelo.DtosCobros;

public class CtrolCobrarInscripcion implements ActionListener {
	
	private Cobro ventanaCobrarInscripcion;
	private DtosCobros dtosCobros;
	
	public CtrolCobrarInscripcion(Cobro vista) {
		
		this.ventanaCobrarInscripcion = vista;
		this.dtosCobros = new DtosCobros();
		this.ventanaCobrarInscripcion.chckbxEnviarEmail.addActionListener(this);
		this.ventanaCobrarInscripcion.btnVolver.addActionListener(this);
		this.ventanaCobrarInscripcion.btnCobrar.addActionListener(this);
	}
	
	public void iniciar() {
		
		ventanaCobrarInscripcion.tabla.setModel(dtosCobros.getTablaSeleccionados());
		ventanaCobrarInscripcion.setVisible(true);
		actualizar();
	}
	
	private void actualizar() {

		ventanaCobrarInscripcion.lblEmail.setVisible(ventanaCobrarInscripcion.chckbxEnviarEmail.isSelected());
		ventanaCobrarInscripcion.txtEmail.setVisible(ventanaCobrarInscripcion.chckbxEnviarEmail.isSelected());
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ventanaCobrarInscripcion.chckbxEnviarEmail) {
			
			actualizar();
		}
		
		if(e.getSource() == ventanaCobrarInscripcion.btnCobrar) {
			
			// acá debo imprimir el comprobante o enviarlo por email, según corresponda
			// después borro los datos de la selecciona 
			
			
			
		}

		if(e.getSource() == ventanaCobrarInscripcion.btnVolver) {
			
			dtosCobros.setBorrarSeleccionados();
			ventanaCobrarInscripcion.dispose();
		}
	}
}
