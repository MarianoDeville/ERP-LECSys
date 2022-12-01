package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaceUsuario.ListadoDoble;
import modelo.DtosGrupoFamiliar;

public class CtrlGrupoFamiliar implements ActionListener {

	private ListadoDoble ventanaListado;
	private DtosGrupoFamiliar dtosGrupoFamiliar;
	
	public CtrlGrupoFamiliar(ListadoDoble vista) {
		
		this.ventanaListado = vista;
		this.dtosGrupoFamiliar = new DtosGrupoFamiliar();
		this.ventanaListado.checkBoxActivos.addActionListener(this);
		this.ventanaListado.txtTabla1.addActionListener(this);
		this.ventanaListado.txtTabla2.addActionListener(this);
		this.ventanaListado.btnAgregar.addActionListener(this);
		this.ventanaListado.btnEditar.addActionListener(this);
		this.ventanaListado.btnGuafrdar.addActionListener(this);
		this.ventanaListado.btnVolver.addActionListener(this);
	}

	public void iniciar() {
	
		actualizar(0);
		ventanaListado.setVisible(true);
	}
	
	private void actualizar(int tabla) {

		ventanaListado.tabla1.setModel(dtosGrupoFamiliar.getTablaGrupoFamiliar(ventanaListado.checkBoxActivos.isSelected(), 
																			   ventanaListado.txtTabla1.getText()));
		ventanaListado.tabla2.setModel(dtosGrupoFamiliar.getTablaGrupoFamiliar(true, 
																			   ventanaListado.txtTabla2.getText()));
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ventanaListado.txtTabla1) {

			actualizar(1);
		}
		
		if(e.getSource() == ventanaListado.txtTabla2) {

			actualizar(2);
		}
		
		if(e.getSource() == ventanaListado.checkBoxActivos) {

			actualizar(1);
		}

		if(e.getSource() == ventanaListado.btnAgregar) {

			
		}
	
		if(e.getSource() == ventanaListado.btnEditar) {

			
		}
		
		if(e.getSource() == ventanaListado.btnGuafrdar) {

			
		}
		
		if(e.getSource() == ventanaListado.btnVolver) {

			ventanaListado.dispose();
		}
	}
}
