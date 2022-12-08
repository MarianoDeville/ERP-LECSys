package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import javax.swing.JOptionPane;
import interfaceUsuario.ABML;
import interfaceUsuario.ListadoDoble;
import modelo.DtosGrupoFamiliar;

public class CtrlGrupoFamiliar implements ActionListener {

	private ABML ventanaFamilia;
	private DtosGrupoFamiliar dtosGrupoFamiliar;
	private ListadoDoble ventanaEditarGrupo;
	private int elemento;
	
	public CtrlGrupoFamiliar(ABML vista) {
		
		this.ventanaFamilia = vista;
		this.dtosGrupoFamiliar = new DtosGrupoFamiliar();
		this.ventanaFamilia.chckbx1.addActionListener(this);
		this.ventanaFamilia.btnEditar.addActionListener(this);
		this.ventanaFamilia.btnVolver.addActionListener(this);
		this.ventanaFamilia.tabla.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 1) {

					elemento = ventanaFamilia.tabla.getSelectedRow();
					limpiarOtros();
		        }
		      }
		  });
		this.ventanaFamilia.txt1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				actualizar();
			}
		});
	}

	public void iniciar() {
	
		ventanaFamilia.panelABML.remove(ventanaFamilia.btnNuevo);
		ventanaFamilia.chckbx1.setVisible(false);
		ventanaFamilia.txt1.setVisible(true);
		actualizar();
		ventanaFamilia.setVisible(true);
	}
	
	private void limpiarOtros() {
		
		for(int i = 0; i < ventanaFamilia.tabla.getRowCount(); i++) {
			
			if(i != elemento)
				ventanaFamilia.tabla.setValueAt((boolean) false, i, 2);
		}
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

			editarElementoSeleccionado();
		}
		
		if(ventanaEditarGrupo != null) {
			
			if(e.getSource() == ventanaEditarGrupo.btnVolver) {
				
				actualizar();
			}
		}

		if(e.getSource() == ventanaFamilia.btnVolver) {

			ventanaFamilia.dispose();
		}
	}
	
	private void actualizar() {

		ventanaFamilia.tabla.setModel(dtosGrupoFamiliar.getTablaGrupoFamiliar(ventanaFamilia.chckbx1.isSelected(), 
																			  ventanaFamilia.txt1.getText()));
		ventanaFamilia.tabla.getColumnModel().getColumn(0).setPreferredWidth(200);
		ventanaFamilia.tabla.getColumnModel().getColumn(0).setMaxWidth(250);
		ventanaFamilia.tabla.getColumnModel().getColumn(2).setPreferredWidth(40);
		ventanaFamilia.tabla.getColumnModel().getColumn(2).setMaxWidth(50);
	}
	
	private void editarElementoSeleccionado() {
		
		boolean bandera = false;
		int i = 0;
		
		while(i < ventanaFamilia.tabla.getRowCount()) {
			
			if((boolean)ventanaFamilia.tabla.getValueAt(i, 2)) {
				
				dtosGrupoFamiliar.setElementoSeleccionado(i);
				bandera = true;
				break;
			}
			i++;
		}
		
		if(bandera) {
			
			dtosGrupoFamiliar.setInformacionGrupo();
			ventanaEditarGrupo = new ListadoDoble("Edicion de la información del grupo.");
			CtrlEditarGrupoFamiliar ctrlEditarGrupo = new CtrlEditarGrupoFamiliar(ventanaEditarGrupo);
			ctrlEditarGrupo.iniciar();
			ventanaEditarGrupo.btnVolver.addActionListener(this);
		} else {
			
			JOptionPane.showMessageDialog(null, "No ha seleccionado ningún elemento.");
		}
	}
}
