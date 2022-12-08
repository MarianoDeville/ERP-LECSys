package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import interfaceUsuario.ListadoDoble;
import modelo.DtosGrupoFamiliar;

public class CtrlEditarGrupoFamiliar implements ActionListener {

	private ListadoDoble ventanaEditarFamilia;
	private DtosGrupoFamiliar dtosGrupoFamiliar;
	private int elemento;
	
	public CtrlEditarGrupoFamiliar(ListadoDoble vista) {
		
		this.ventanaEditarFamilia = vista;
		this.dtosGrupoFamiliar = new DtosGrupoFamiliar();
		this.ventanaEditarFamilia.btnAgregar.addActionListener(this);
		this.ventanaEditarFamilia.btnEliminar.addActionListener(this);
		this.ventanaEditarFamilia.btnGuafrdar.addActionListener(this);
		this.ventanaEditarFamilia.btnVolver.addActionListener(this);
		this.ventanaEditarFamilia.txt1Tabla2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				actualizar();
			}
		});
		this.ventanaEditarFamilia.tabla1.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 1) {

					elemento = ventanaEditarFamilia.tabla1.getSelectedRow();
					limpiarOtros(1);
		        }
		      }
		  });
		this.ventanaEditarFamilia.tabla2.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 1) {

					elemento = ventanaEditarFamilia.tabla2.getSelectedRow();
					limpiarOtros(2);
		        }
		      }
		  });
	}

	public void iniciar() {
	
		ventanaEditarFamilia.txt1Tabla1.setText(dtosGrupoFamiliar.getNombreFamilia());
		ventanaEditarFamilia.txt2Tabla1.setText(dtosGrupoFamiliar.getDescuento());
		ventanaEditarFamilia.panelListado.remove(ventanaEditarFamilia.txt3Tabla1);
		ventanaEditarFamilia.panelListado.remove(ventanaEditarFamilia.lblTxt3Tabla1);
		ventanaEditarFamilia.checkBoxActivos.setVisible(false);
		actualizar();
		ventanaEditarFamilia.setVisible(true);
	}
	
	private void actualizar() {

		ventanaEditarFamilia.tabla1.setModel(dtosGrupoFamiliar.getTablaFamilia());
		ventanaEditarFamilia.tabla1.getColumnModel().getColumn(0).setPreferredWidth(40);
		ventanaEditarFamilia.tabla1.getColumnModel().getColumn(0).setMaxWidth(50);
		ventanaEditarFamilia.tabla1.getColumnModel().getColumn(3).setPreferredWidth(40);
		ventanaEditarFamilia.tabla1.getColumnModel().getColumn(3).setMaxWidth(50);
		ventanaEditarFamilia.tabla1.getColumnModel().getColumn(4).setPreferredWidth(20);
		ventanaEditarFamilia.tabla1.getColumnModel().getColumn(4).setMaxWidth(25);	
		
		ventanaEditarFamilia.tabla2.setModel(dtosGrupoFamiliar.getTablaAlumnos(ventanaEditarFamilia.txt1Tabla2.getText() 
																		 	  ,true));
		ventanaEditarFamilia.tabla2.getColumnModel().getColumn(0).setPreferredWidth(40);
		ventanaEditarFamilia.tabla2.getColumnModel().getColumn(0).setMaxWidth(50);
		ventanaEditarFamilia.tabla2.getColumnModel().getColumn(4).setPreferredWidth(40);
		ventanaEditarFamilia.tabla2.getColumnModel().getColumn(4).setMaxWidth(50);
	}
	
	private void limpiarOtros(int tabla) {
		
		if(tabla == 1) {
			
			for(int i = 0; i < ventanaEditarFamilia.tabla1.getRowCount(); i++) {
				
				if(i != elemento)
					ventanaEditarFamilia.tabla1.setValueAt((boolean) false, i, 3);
			}
		}
		
		if(tabla == 2) {
			
			for(int i = 0; i < ventanaEditarFamilia.tabla2.getRowCount(); i++) {
				
				if(i != elemento)
					ventanaEditarFamilia.tabla2.setValueAt((boolean) false, i, 4);
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ventanaEditarFamilia.btnAgregar) {
			
			agregarElementoSeleccionado();
			actualizar();
		}
		
		if(e.getSource() == ventanaEditarFamilia.btnEliminar) {
			
			eliminarElemento();
			actualizar();
		}
		
		if(e.getSource() == ventanaEditarFamilia.btnGuafrdar) {
			
			guardarCambios();
		}
		
		if(e.getSource() == ventanaEditarFamilia.btnVolver) {
			
			ventanaEditarFamilia.dispose();
		}
	}
	
	private void agregarElementoSeleccionado() {
		
		boolean bandera = false;
		int i = 0;
		
		while(i < ventanaEditarFamilia.tabla2.getRowCount()) {
			
			if((boolean) ventanaEditarFamilia.tabla2.getValueAt(i, 4)) {
				
				dtosGrupoFamiliar.setElementoSeleccionado(i);
				bandera = true;
				break;
			}
			i++;
		}
		
		if(!bandera) {
			
			JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento.");
			return;
		}
		
		if( dtosGrupoFamiliar.isRepetido(ventanaEditarFamilia.tabla2.getValueAt(i, 0)) ) {
			
			JOptionPane.showMessageDialog(null, "El elemento seleccionado ya fue agregado.");
			return;
		}
			
		dtosGrupoFamiliar.setAgregarElementos();
	}
	
	private void eliminarElemento() {
	
		boolean bandera = false;
		int i = 0;
		
		while(i < ventanaEditarFamilia.tabla1.getRowCount()) {
			
			if((boolean) ventanaEditarFamilia.tabla1.getValueAt(i, 3)) {
				
				dtosGrupoFamiliar.setElementoSeleccionado(i);
				bandera = true;
				break;
			}
			i++;
		}
		
		if(!bandera) {
			
			JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento.");
			return;
		}
		
		if(ventanaEditarFamilia.tabla1.getValueAt(i, 4).equals("E")) {
			
			JOptionPane.showMessageDialog(null, "El elemento seleccionado ya se encuentra marcado para eliminación.");
			return;
		}
		if(ventanaEditarFamilia.tabla1.getValueAt(i, 4).equals("A")) {
			
			JOptionPane.showMessageDialog(null, "No se puede eliminar un elemento que se acaba de guardar.");
			return;
		}
		dtosGrupoFamiliar.setEliminarElementos();
	}
	
	private void guardarCambios() {
		
		if(JOptionPane.showConfirmDialog(null, "¿Esta seguro?", "Alerta!", JOptionPane.YES_NO_OPTION) == 0) {
			
			dtosGrupoFamiliar.guardarCambios();
			
			ventanaEditarFamilia.btnGuafrdar.setEnabled(false);
			
			
			
			
System.out.println("Acá guardo los cambios en la base de datos");
		} else {
			
			
			
			
System.out.println("Vuelvo sin hacer nada.");			
		}
		
		
	}
}