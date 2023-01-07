package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaceUsuario.NuevoSimple;
import modelo.DtosInsumos;

public class CtrlNuevoInsumo implements ActionListener {
	
	private NuevoSimple ventana;
	private DtosInsumos dtosInsumos;
	
	public CtrlNuevoInsumo(NuevoSimple vista) {
		
		this.ventana = vista;
		this.dtosInsumos = new DtosInsumos();
		this.ventana.btnGuardar.addActionListener(this);
		this.ventana.btnVolver.addActionListener(this);
	}

	public void iniciar() {

		ventana.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ventana.btnGuardar) {
			
			guardar();
		}		
		
		if(e.getSource() == ventana.btnVolver) {

			ventana.dispose();
		}
	}
	
	private void guardar() {
		
		dtosInsumos.setNombre(ventana.txtNombre.getText());
		dtosInsumos.setDescripción(ventana.txtDescripción.getText());
		dtosInsumos.setPresentación(ventana.txtFormato.getText());
		
		if(!dtosInsumos.isCheckInfo()) {
			
			ventana.lblMsgError.setForeground(Color.RED);
			ventana.lblMsgError.setText(dtosInsumos.getMsgError());
			return;
		}
		
		if(!dtosInsumos.setGuardarNuevo()) {
			
			ventana.lblMsgError.setForeground(Color.RED);
			ventana.lblMsgError.setText("Error al intentar guardar la información en la base de datos.");
			return;
		}
		ventana.lblMsgError.setForeground(Color.BLUE);
		ventana.lblMsgError.setText("El producto se guardó corractamente.");
		ventana.btnGuardar.setEnabled(false);
	}
}
