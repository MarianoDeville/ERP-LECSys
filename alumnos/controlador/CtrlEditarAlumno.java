package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import interfaceUsuario.Nuevo;
import modelo.DtosAlumno;

public class CtrlEditarAlumno implements ActionListener {
	
	private Nuevo ventanaEditarAlumno;
	private DtosAlumno dtosEditarAlumno;

	public CtrlEditarAlumno(Nuevo vista) {
		
		this.ventanaEditarAlumno = vista;
		this.dtosEditarAlumno = new DtosAlumno();
		this.ventanaEditarAlumno.comboBox1.addActionListener(this);
		this.ventanaEditarAlumno.btnGuardar.addActionListener(this);
		this.ventanaEditarAlumno.btnImprimir.addActionListener(this);
		this.ventanaEditarAlumno.btnVolver.addActionListener(this);
		
	}
	public void iniciar(String legajo) {
		
		ventanaEditarAlumno.btnImprimir.setVisible(true);
		ventanaEditarAlumno.scrollTabla.setVisible(true);
		ventanaEditarAlumno.chkbox1.setVisible(true);
		dtosEditarAlumno.recuperarInformacionAlumno(legajo);
		ventanaEditarAlumno.txtLegajo.setText(dtosEditarAlumno.getLegajo());
		ventanaEditarAlumno.txtNombre.setText(dtosEditarAlumno.getNombre());
		ventanaEditarAlumno.txtApellido.setText(dtosEditarAlumno.getApellido());
		ventanaEditarAlumno.txtDNI.setText(dtosEditarAlumno.getDni());
		ventanaEditarAlumno.txtDireccion.setText(dtosEditarAlumno.getDireccion());
		ventanaEditarAlumno.txtTelefono.setText(dtosEditarAlumno.getTelefono());
		ventanaEditarAlumno.txtEmail.setText(dtosEditarAlumno.getEmail());
		ventanaEditarAlumno.txtAño.setText(dtosEditarAlumno.getFechaNacimientoAño());
		ventanaEditarAlumno.txtMes.setText(dtosEditarAlumno.getFechaNacimientoMes());
		ventanaEditarAlumno.txtDia.setText(dtosEditarAlumno.getFechaNacimientoDia());
		ventanaEditarAlumno.comboBox1.setModel(new DefaultComboBoxModel<String>(dtosEditarAlumno.getListaCursos()));
		ventanaEditarAlumno.comboBox1.setSelectedIndex(dtosEditarAlumno.getCursoSeleccionado());
		ventanaEditarAlumno.chkbox1.setSelected(dtosEditarAlumno.getEstado());
		actualizar();
		ventanaEditarAlumno.setVisible(true);
	}
	
	private void actualizar() {
		
		ventanaEditarAlumno.tabla.setModel(dtosEditarAlumno.getTablaDias(ventanaEditarAlumno.comboBox1.getSelectedIndex()));
	}

	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == ventanaEditarAlumno.comboBox1) {
			
			actualizar();
		}
		
		if(e.getSource() == ventanaEditarAlumno.btnGuardar) {
			
			
		}
		
		if(e.getSource() == ventanaEditarAlumno.btnImprimir) {
			
			
		}
		
		if(e.getSource() == ventanaEditarAlumno.btnVolver) {
			
			ventanaEditarAlumno.dispose();
		}
	}
	
}
