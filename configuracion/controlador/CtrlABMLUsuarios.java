package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import interfaceUsuario.ABML;
import interfaceUsuario.NuevoUsuario;
import modelo.DtosAcceso;
import modelo.DtosUsuarios;

public class CtrlABMLUsuarios implements ActionListener {
	
	private ABML ventanaABMLUsuarios;
	private DtosUsuarios dtosUsuario;
	private NuevoUsuario ventanaNuevoUsuario;
	private NuevoUsuario ventanaEditarUsuario;
	private DtosAcceso acceso;

	public CtrlABMLUsuarios(ABML vista) {
		
		this.ventanaABMLUsuarios = vista;
		this.dtosUsuario = new DtosUsuarios();
		this.acceso = new DtosAcceso();
		this.ventanaABMLUsuarios.btnNuevo.addActionListener(this);
		this.ventanaABMLUsuarios.btnEditar.addActionListener(this);
		this.ventanaABMLUsuarios.btnImprimir.addActionListener(this);
		this.ventanaABMLUsuarios.btnVolver.addActionListener(this);
	}
	
	public void iniciar() {
		
		actualizar();
		ventanaABMLUsuarios.setVisible(true);
	}
	
	public void actualizar() {
		
		ventanaABMLUsuarios.tabla.setModel(dtosUsuario.getTablaUsuarios());
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ventanaABMLUsuarios.btnNuevo) {
			
			if(acceso.chkAcceso("Usuarios", "Crear")) {
				
				ventanaNuevoUsuario = new NuevoUsuario("Crear usuario del sistema.");
				CtrlNuevoUsuario ctrlNuevoUsuario = new CtrlNuevoUsuario(ventanaNuevoUsuario);
				ctrlNuevoUsuario.iniciar();
				ventanaNuevoUsuario.btnVolver.addActionListener(this);
			}
		}
		
		if(ventanaNuevoUsuario !=null) {
		
			if(e.getSource() == ventanaNuevoUsuario.btnVolver) {
				
				actualizar();
			}
		}
		
		if(e.getSource() == ventanaABMLUsuarios.btnEditar) {
			
			if(acceso.chkAcceso("Usuarios", "Editar")) {
			
				int i=0;
				String usuario = null;
				
				while(i < ventanaABMLUsuarios.tabla.getRowCount()) {
					
					if((boolean)ventanaABMLUsuarios.tabla.getValueAt(i, 3)) {
						
						usuario = (String)ventanaABMLUsuarios.tabla.getValueAt(i, 0);
						break;
					}
					i++;
				}
				
				if(usuario != null) {
					
					ventanaEditarUsuario = new NuevoUsuario("Editar usuario.");
					CtrlEditarUsuario ctrlEditarUsuario = new CtrlEditarUsuario(ventanaEditarUsuario);
					ctrlEditarUsuario.iniciar(usuario);
					ventanaEditarUsuario.btnVolver.addActionListener(this);
				} else {
					
					JOptionPane.showMessageDialog(null, "Debe seleccionar un alumno para editar.");
				}
			}
		}
		
		if(ventanaEditarUsuario != null) {
			
			if(e.getSource() == ventanaEditarUsuario.btnVolver) {
				
				actualizar();
			}
		}
		
		if(e.getSource() == ventanaABMLUsuarios.btnImprimir) {
			
			try {
				
				ventanaABMLUsuarios.tabla.print();
			} catch (Exception f) {

				f.printStackTrace();
			}
		}
		
		if(e.getSource() == ventanaABMLUsuarios.btnVolver) {
			
			ventanaABMLUsuarios.dispose();
		}
	}
}
