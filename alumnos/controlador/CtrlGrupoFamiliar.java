package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;

import interfaceUsuario.ABML;
import modelo.DtosGrupoFamiliar;

public class CtrlGrupoFamiliar implements ActionListener {

	private ABML ventanaFamilia;
	private DtosGrupoFamiliar dtosGrupoFamiliar;
	
	public CtrlGrupoFamiliar(ABML vista) {
		
		this.ventanaFamilia = vista;
		this.dtosGrupoFamiliar = new DtosGrupoFamiliar();
		this.ventanaFamilia.chckbx1.addActionListener(this);
		this.ventanaFamilia.btnEditar.addActionListener(this);
		this.ventanaFamilia.btnVolver.addActionListener(this);
		this.ventanaFamilia.txt1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				actualizar();
			}
		});
	}

	public void iniciar() {
	
		ventanaFamilia.panel.remove(ventanaFamilia.btnNuevo);
		ventanaFamilia.chckbx1.setVisible(true);
		ventanaFamilia.txt1.setVisible(true);
		ventanaFamilia.setVisible(true);
		actualizar();
	}
	
	private void actualizar() {

		ventanaFamilia.tabla.setModel(dtosGrupoFamiliar.getTablaGrupoFamiliar(ventanaFamilia.chckbx1.isSelected(), 
																			  ventanaFamilia.txt1.getText()));
		ventanaFamilia.tabla.getColumnModel().getColumn(0).setPreferredWidth(200);
		ventanaFamilia.tabla.getColumnModel().getColumn(0).setMaxWidth(250);
		ventanaFamilia.tabla.getColumnModel().getColumn(2).setPreferredWidth(40);
		ventanaFamilia.tabla.getColumnModel().getColumn(2).setMaxWidth(50);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ventanaFamilia.chckbx1) {
			
			actualizar();
		}
	
		if(e.getSource() == ventanaFamilia.btnImprimir) {
			
			try {
				
				ventanaFamilia.tabla.print();
			} catch (PrinterException f) {
				
				f.printStackTrace();
			}
		}
		
		if(e.getSource() == ventanaFamilia.btnEditar) {

			int i = 0;
			
			while(i < ventanaFamilia.tabla.getRowCount()) {
				
				if((boolean)ventanaFamilia.tabla.getValueAt(i, 2)) {
					
					dtosGrupoFamiliar.setElementoSeleccionado(i);
					break;
				}
				i++;
			}
			
			dtosGrupoFamiliar.setInformacionGrupo();
			
			
			
		}

		if(e.getSource() == ventanaFamilia.btnVolver) {

			ventanaFamilia.dispose();
		}
	}
}
