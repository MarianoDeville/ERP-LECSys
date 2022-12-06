package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import interfaceUsuario.Listado;
import modelo.DtosAlumno;

public class CtrlRegistroAsistencia implements ActionListener {

	private Listado ventanaRegistroAsistencia;
	private DtosAlumno dtosAlumno;
		
	public CtrlRegistroAsistencia(Listado vista) {
		
		this.ventanaRegistroAsistencia = vista;
		this.dtosAlumno = new DtosAlumno();
		this.ventanaRegistroAsistencia.comboBox1.addActionListener(this);
		this.ventanaRegistroAsistencia.comboBox2.addActionListener(this);
		this.ventanaRegistroAsistencia.btnImprimir.addActionListener(this);
		this.ventanaRegistroAsistencia.btnVolver.addActionListener(this);
	}
	
	public void iniciar() {
		
		ventanaRegistroAsistencia.comboBox1.setVisible(true);
		ventanaRegistroAsistencia.comboBox1.setModel(new DefaultComboBoxModel<String>(dtosAlumno.getListaCursos()));
		ventanaRegistroAsistencia.comboBox2.setVisible(true);
		ventanaRegistroAsistencia.comboBox2.setModel(new DefaultComboBoxModel<String>(dtosAlumno.getListaMeses()));
		ventanaRegistroAsistencia.setVisible(true);
		actualizar();
	}
	
	private void actualizar() {
		
		ventanaRegistroAsistencia.tabla.setModel(dtosAlumno.getTablaRegistroAsistencia(ventanaRegistroAsistencia.comboBox1.getSelectedIndex(), 
																						ventanaRegistroAsistencia.comboBox2.getSelectedIndex()));
		ventanaRegistroAsistencia.tabla.getColumnModel().getColumn(0).setPreferredWidth(45);
		ventanaRegistroAsistencia.tabla.getColumnModel().getColumn(0).setMaxWidth(55);
		ventanaRegistroAsistencia.tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		ventanaRegistroAsistencia.tabla.doLayout();
		
		if(!dtosAlumno.getMsg().equals(""))
			JOptionPane.showMessageDialog(null, dtosAlumno.getMsg());
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ventanaRegistroAsistencia.comboBox1) {
			
			actualizar();
		}
		
		if(e.getSource() == ventanaRegistroAsistencia.comboBox2) {
			
			actualizar();
		}
		
		if(e.getSource() == ventanaRegistroAsistencia.btnImprimir) {
			
			try {
				
				ventanaRegistroAsistencia.tabla.print();
			} catch (Exception f) {
				
				JOptionPane.showMessageDialog(null, "Error al intentar imprimir.");
				CtrlLogErrores.guardarError(f.getMessage());
			}
		}
		
		if(e.getSource() == ventanaRegistroAsistencia.btnVolver) {
			
			ventanaRegistroAsistencia.dispose();
		}
	}
}