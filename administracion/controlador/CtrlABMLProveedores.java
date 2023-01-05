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
import interfaceUsuario.CrearCurso;
import modelo.DtosProveedores;

public class CtrlABMLProveedores implements ActionListener{
	
	private ABML ventana;
	private CrearCurso ventanaNuevoProveedor;
	private CrearCurso ventanaEditarProveedor;
	private DtosProveedores dtosProveedores;
	private int elemento;
	private boolean bandera;
	
	public CtrlABMLProveedores(ABML vista) {
		
		this.ventana = vista;
		this.dtosProveedores = new DtosProveedores();
		this.ventana.btnNuevo.addActionListener(this);
		this.ventana.btnEditar.addActionListener(this);
		this.ventana.btnImprimir.addActionListener(this);
		this.ventana.btnVolver.addActionListener(this);
		this.ventana.chckbx1.addActionListener(this);
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
		ventana.chckbx1.setVisible(true);
		actualizar();
		ventana.setVisible(true);
	}
	
	private void actualizar() {

		if(!bandera)
			elemento = 10000;
		
		bandera = false;
		ventana.tabla.setModel(dtosProveedores.getTablaProveedores(ventana.txt1.getText(), 
																	ventana.chckbx1.isSelected(), 
																	elemento));
	}
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == ventana.chckbx1) {
			
			actualizar();
		}
		
		if(e.getSource() == ventana.btnNuevo) {
			
			ventanaNuevoProveedor = new CrearCurso("Nueno proveedor");
			CtrlNuevoProveedor ctrlNuevoProveedor = new CtrlNuevoProveedor(ventanaNuevoProveedor);
			ctrlNuevoProveedor.iniciar();
			ventanaNuevoProveedor.btnVolver.addActionListener(this);
		}
		
		if(ventanaNuevoProveedor != null) {
			
			if(e.getSource() == ventanaNuevoProveedor.btnVolver) {
				
				actualizar();
			}
		}
		
		if(e.getSource() == ventana.btnEditar) {
			
			elementoSeleccionado();
		}
		
		if(ventanaEditarProveedor != null) {
			
			if(e.getSource() == ventanaEditarProveedor.btnVolver) {
				
				actualizar();
			}
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
		dtosProveedores.setIdProveedor(elemento, ventana.chckbx1.isSelected());
		ventanaEditarProveedor = new CrearCurso("Editar proveedor");
		CtrlEditarProveedor ctrlEditarProveedor = new CtrlEditarProveedor(ventanaEditarProveedor);
		ctrlEditarProveedor.iniciar();
		ventanaEditarProveedor.btnVolver.addActionListener(this);
		return true;
	}
}