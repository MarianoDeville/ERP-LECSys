package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		this.ventanaEditarFamilia.btnEditar.addActionListener(this);
		this.ventanaEditarFamilia.btnGuafrdar.addActionListener(this);
		this.ventanaEditarFamilia.btnVolver.addActionListener(this);
		this.ventanaEditarFamilia.checkBoxActivos.addActionListener(this);
		this.ventanaEditarFamilia.txt1Tabla2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				actualizar();
			}
		});
		this.ventanaEditarFamilia.tabla2.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 1) {

					elemento = ventanaEditarFamilia.tabla2.getSelectedRow();
					limpiarOtros();
		        }
		      }
		  });
	}

	public void iniciar() {
	
		ventanaEditarFamilia.tabla1.setModel(dtosGrupoFamiliar.getTablaFamilia());
		ventanaEditarFamilia.txt1Tabla1.setText(dtosGrupoFamiliar.getNombreFamilia());
		ventanaEditarFamilia.txt2Tabla1.setText(dtosGrupoFamiliar.getDescuento());
		ventanaEditarFamilia.tabla1.getColumnModel().getColumn(0).setPreferredWidth(40);
		ventanaEditarFamilia.tabla1.getColumnModel().getColumn(0).setMaxWidth(50);
		ventanaEditarFamilia.tabla1.getColumnModel().getColumn(3).setPreferredWidth(40);
		ventanaEditarFamilia.tabla1.getColumnModel().getColumn(3).setMaxWidth(50);
		ventanaEditarFamilia.checkBoxActivos.setSelected(true);

		

		actualizar();
		ventanaEditarFamilia.setVisible(true);
	}
	
	private void actualizar() {

		ventanaEditarFamilia.tabla2.setModel(dtosGrupoFamiliar.getTablaAlumnos(ventanaEditarFamilia.txt1Tabla2.getText() 
																		 	  ,ventanaEditarFamilia.checkBoxActivos.isSelected()));
		ventanaEditarFamilia.tabla2.getColumnModel().getColumn(0).setPreferredWidth(40);
		ventanaEditarFamilia.tabla2.getColumnModel().getColumn(0).setMaxWidth(50);
		ventanaEditarFamilia.tabla2.getColumnModel().getColumn(4).setPreferredWidth(40);
		ventanaEditarFamilia.tabla2.getColumnModel().getColumn(4).setMaxWidth(50);
		
	}
	
	private void limpiarOtros() {
		
		for(int i = 0; i < ventanaEditarFamilia.tabla2.getRowCount(); i++) {
			
			if(i != elemento)
				ventanaEditarFamilia.tabla2.setValueAt((boolean) false, i, 4);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ventanaEditarFamilia.checkBoxActivos) {
			
			actualizar();
		}
		
		if(e.getSource() == ventanaEditarFamilia.btnAgregar) {
			
			
		}
		
		if(e.getSource() == ventanaEditarFamilia.btnEditar) {
			
			
		}
		
		if(e.getSource() == ventanaEditarFamilia.btnGuafrdar) {
			
			
		}
		
		if(e.getSource() == ventanaEditarFamilia.btnVolver) {
			
			ventanaEditarFamilia.dispose();
		}

	}
}