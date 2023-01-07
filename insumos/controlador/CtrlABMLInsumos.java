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
import interfaceUsuario.NuevoSimple;
import modelo.DtosInsumos;

public class CtrlABMLInsumos implements ActionListener{
	
	private ABML ventana;
	private DtosInsumos dtosInsumos;
	private NuevoSimple ventanaNuevoInsumo;
	private int elemento;
	private boolean bandera;
	
	public CtrlABMLInsumos(ABML vista) {
		
		this.ventana = vista;
		this.dtosInsumos = new DtosInsumos();
		this.ventana.btnNuevo.addActionListener(this);
		this.ventana.btnEditar.addActionListener(this);
		this.ventana.btnImprimir.addActionListener(this);
		this.ventana.btnVolver.addActionListener(this);
		this.ventana.txt1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				actualizar();
			}
		});
		this.ventana.tabla.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 1) {

					elemento = ventana.tabla.getSelectedRow();
					bandera = true;
		        } else if(e.getClickCount() == 2) {
		        	
		        	elemento = ventana.tabla.getSelectedRow();
		        	bandera = true;
		        	elementoSeleccionado();
		        }
		        
		      }
		});
	}
	
	public void iniciar() {


		
		ventana.txt1.setVisible(true);
		
		
		actualizar();
		ventana.setVisible(true);
	}
	
	private void actualizar() {

		if(!bandera)
			elemento = 10000;
		
		bandera = false;
		ventana.tabla.setModel(dtosInsumos.getTablaInsumos(ventana.txt1.getText(), elemento));
		
	}
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == ventana.btnNuevo) {
			
			ventanaNuevoInsumo = new NuevoSimple("Cargar nuevo insumo");
			CtrlNuevoInsumo ctrlNuevoInsumo = new CtrlNuevoInsumo(ventanaNuevoInsumo);
			ctrlNuevoInsumo.iniciar();
			ventanaNuevoInsumo.btnVolver.addActionListener(this);
		}
		
		if(ventanaNuevoInsumo != null) {
			
			if(e.getSource() == ventanaNuevoInsumo.btnVolver) {
				
				actualizar();
			}
		}

		if(e.getSource() == ventana.btnEditar) {
			
			elementoSeleccionado();
		}
	
		if(e.getSource() == ventana.btnImprimir) {
			
			try {
				
				ventana.tabla.print();
			} catch (PrinterException f) {
				
				JOptionPane.showMessageDialog(null, "Error al intentar imprimir.");
				CtrlLogErrores.guardarError(f.getMessage());
			}
		}
		
		if(e.getSource() == ventana.btnVolver) {
			
			ventana.dispose();
		}
	}
	
	private boolean elementoSeleccionado() {
		
		if(!bandera) {

			JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento para editar.");
			return false;
		}

		return true;
	}
}