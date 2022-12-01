package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import javax.swing.JOptionPane;
import interfaceUsuario.ABML;
import interfaceUsuario.CrearCurso;
import modelo.DtosAcceso;
import modelo.DtosCurso;

public class CtrlABMLCursos implements ActionListener {
	
	private ABML ventanaABMLCursos;
	private DtosCurso dtosAMBLCurso;
	private CrearCurso ventanaCrearCurso;
	private CrearCurso ventanaEditarCurso;
	private DtosAcceso acceso;

	public CtrlABMLCursos(ABML vista) {
		
		this.ventanaABMLCursos = vista;
		this.dtosAMBLCurso = new DtosCurso();
		this.acceso = new DtosAcceso();
		this.ventanaABMLCursos.btnNuevo.addActionListener(this);
		this.ventanaABMLCursos.btnEditar.addActionListener(this);
		this.ventanaABMLCursos.btnImprimir.addActionListener(this);
		this.ventanaABMLCursos.btnVolver.addActionListener(this);
	}
	
	public void iniciar() {
		
		actualizar();
		ventanaABMLCursos.setVisible(true);
	}

	private void actualizar() {
		
		ventanaABMLCursos.tabla.setModel(dtosAMBLCurso.getTablaCursos());
	}
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == ventanaABMLCursos.btnNuevo) {
			
			if(acceso.chkAcceso("Cursos", "Crear")) {
			
				ventanaCrearCurso = new CrearCurso("Crear un nuevo curso");
				CtrlNuevoCurso ctrlNuevoCurso = new CtrlNuevoCurso(ventanaCrearCurso);
				ctrlNuevoCurso.iniciar();
				ventanaCrearCurso.btnVolver.addActionListener(this);
			}
		}
		
		if(ventanaCrearCurso != null) {
			
			if(e.getSource() == ventanaCrearCurso.btnVolver) {
				
				actualizar();
			}
		}

		if(e.getSource() == ventanaABMLCursos.btnEditar) {

			if(acceso.chkAcceso("Cursos", "Editar")) {
				
				int i = 0;
				String idCurso = null;
				
				while(i < ventanaABMLCursos.tabla.getRowCount()) {
					
						if((boolean)ventanaABMLCursos.tabla.getValueAt(i, 6)) {
						
							idCurso = (String)ventanaABMLCursos.tabla.getValueAt(i, 0);
							break;
						}
					i++;
				}
				
				if(idCurso != null) {
					
					ventanaEditarCurso = new CrearCurso("Edición de curso");
					CtrlEditarCurso ctrlEditarCurso = new CtrlEditarCurso(ventanaEditarCurso);
					ctrlEditarCurso.iniciar(idCurso);
					ventanaEditarCurso.btnVolver.addActionListener(this);
				} else {
					
					JOptionPane.showMessageDialog(null, "Debe seleccionar un curso para editar.");
				}
			}
		}
		
		if(ventanaEditarCurso != null) {
			
			if(e.getSource() == ventanaEditarCurso.btnVolver) {
				
				actualizar();
			}
		}
		
		if(e.getSource() == ventanaABMLCursos.btnImprimir) {
			
			try {
				
				ventanaABMLCursos.tabla.print();
			} catch (PrinterException f) {
				
				JOptionPane.showMessageDialog(null, "Error al intentar imprimir.");
				CtrlLogErrores.guardarError(f.getMessage());
			}
		}
		
		if(e.getSource() == ventanaABMLCursos.btnVolver) {
			
			ventanaABMLCursos.dispose();
		}
	}
}
