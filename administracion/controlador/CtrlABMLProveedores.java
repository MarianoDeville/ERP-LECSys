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
	private DtosProveedores dtosProveedores;
	private int elemento;
	
	public CtrlABMLProveedores(ABML vista) {
		
		this.ventana = vista;
		this.dtosProveedores = new DtosProveedores();
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
					limpiarOtros();
		        }
		      }
		  });
	}
	
	public void iniciar() {

		ventana.txt1.setVisible(true);
		
		actualizar();
		ventana.setVisible(true);
	}
	
	private void limpiarOtros() {
		
		for(int i = 0; i < ventana.tabla.getRowCount(); i++) {
			
			if(i != elemento)
				ventana.tabla.setValueAt((boolean) false, i, 5);
		}
	}
	
	private void actualizar() {
		
		
		ventana.tabla.setModel(dtosProveedores.getTablaProveedores(ventana.txt1.getText()));
		
	}
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == ventana.btnNuevo) {
			
			CrearCurso ventanaNuevoProveedor = new CrearCurso("Nueno proveedor");
			CtrlNuevoProveedor ctrlNuevoProveedor = new CtrlNuevoProveedor(ventanaNuevoProveedor);
			ctrlNuevoProveedor.iniciar();
		}
		
		if(e.getSource() == ventana.btnEditar) {
			
			
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
}