package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;

import interfaceUsuario.CrearCurso;
import modelo.DtosProveedores;

public class CtrlNuevoProveedor implements ActionListener{
	
	
	private CrearCurso ventana;
	private DtosProveedores dtosProveedores;

	
	public CtrlNuevoProveedor(CrearCurso vista) {
		
		this.ventana = vista;
		this.dtosProveedores = new DtosProveedores();
		this.ventana.btnVolver.addActionListener(this);
		this.ventana.btnValidar.addActionListener(this);
		this.ventana.btnGuardar.addActionListener(this);
		
	}
	
	public void iniciar() {
		
		ventana.comboBoxNivel.setVisible(false);
		ventana.comboBoxAño.setVisible(false);
		ventana.comboBoxAula.setVisible(false);
		ventana.lblAula.setVisible(false);
		ventana.txtNombre.setVisible(true);
		ventana.txtDirección.setVisible(true);
		ventana.lblNivel.setText("Nombre:");
		ventana.lblAño.setText("Dirección:");
		ventana.lblProfesor.setText("Condición:");
		ventana.lblCuota.setText("CUIT:");
		ventana.lblHorario.setText("");
		ventana.lblLunes.setText("");
		ventana.lblMartes.setText("");
		ventana.lblMiercoles.setText("");
		ventana.lblJueves.setText("");
		ventana.lblViernes.setText("");
		ventana.lblSabado.setText("");
		ventana.btnValidar.setText("Agregar");
		
		ventana.comboBoxProfesor.setModel(new DefaultComboBoxModel<String>(dtosProveedores.getListaCondiciones()));
		
		ventana.setVisible(true);
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
	
		
		if(e.getSource() == ventana.btnValidar) {
			
			actualizar();
		}
	
		if(e.getSource() == ventana.btnVolver) {
			
			ventana.dispose();
		}
	
		if(e.getSource() == ventana.btnGuardar) {
			
			
		}
	
	}
	
	private void actualizar() {
		
	}

}
