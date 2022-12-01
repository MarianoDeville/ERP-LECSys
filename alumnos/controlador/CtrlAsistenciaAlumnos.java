package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import interfaceUsuario.Listado;
import modelo.DtosAlumno;

public class CtrlAsistenciaAlumnos implements ActionListener {

	private Listado ventanaAsistencia;
	private DtosAlumno dtosAlumno;
	
	public CtrlAsistenciaAlumnos(Listado vista) {
		
		this.ventanaAsistencia = vista;
		this.dtosAlumno = new DtosAlumno();
		this.ventanaAsistencia.comboBox1.addActionListener(this);
		this.ventanaAsistencia.btnImprimir.addActionListener(this);
		this.ventanaAsistencia.btn1B.addActionListener(this);
		this.ventanaAsistencia.btnVolver.addActionListener(this);
	}

	public void iniciar() {
		
		ventanaAsistencia.btn1B.setVisible(true);
		ventanaAsistencia.btn1B.setText("Guardar");
		ventanaAsistencia.lblComboBox1.setVisible(true);
		ventanaAsistencia.lblComboBox1.setText("Curso");
		ventanaAsistencia.comboBox1.setVisible(true);
		ventanaAsistencia.comboBox1.setModel(new DefaultComboBoxModel<String>(dtosAlumno.getListaCursos()));
		ventanaAsistencia.lblTxt1.setVisible(true);
		ventanaAsistencia.lblTxt1.setText("Fecha");
		ventanaAsistencia.txt1.setVisible(true);
		ventanaAsistencia.txt1.setText(dtosAlumno.getFechaActual(true));
		actualizar();
		ventanaAsistencia.setVisible(true);
	}
	
	private void actualizar() {
		
		ventanaAsistencia.tabla.setModel(dtosAlumno.getTablaAsistencia(ventanaAsistencia.comboBox1.getSelectedIndex()));
		ventanaAsistencia.tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
		ventanaAsistencia.tabla.getColumnModel().getColumn(0).setMaxWidth(60);
		ventanaAsistencia.tabla.getColumnModel().getColumn(3).setPreferredWidth(65);
		ventanaAsistencia.tabla.getColumnModel().getColumn(3).setMaxWidth(70);
		ventanaAsistencia.tabla.getColumnModel().getColumn(4).setPreferredWidth(80);
		ventanaAsistencia.tabla.getColumnModel().getColumn(4).setMaxWidth(80);
	}
	
	public void actionPerformed(ActionEvent e) {
	
		if(e.getSource() == ventanaAsistencia.comboBox1) {
			
			actualizar();
		}
		
		if(e.getSource() == ventanaAsistencia.btn1B) {
			
			for(int i = 0 ; i < ventanaAsistencia.tabla.getRowCount() ; i++) {
				
				dtosAlumno.setTablaAsistencia(i, 3, (boolean)ventanaAsistencia.tabla.getValueAt(i, 3));
				dtosAlumno.setTablaAsistencia(i, 4, (boolean)ventanaAsistencia.tabla.getValueAt(i, 4));
			}
			
			dtosAlumno.setCurso(ventanaAsistencia.comboBox1.getSelectedIndex());
			
			if(dtosAlumno.guardoAsistencia()) {
				
				JOptionPane.showMessageDialog(null, "Se actualizó la información en la base de datos.");
			} else {

				JOptionPane.showMessageDialog(null, "Error al intentar guardar la información.");
			}
		}
		
		if(e.getSource() == ventanaAsistencia.btnImprimir) {
			
			try {
				
				ventanaAsistencia.tabla.print();
			} catch (PrinterException f) {
				
				JOptionPane.showMessageDialog(null, "Error al intentar imprimir.");
				CtrlLogErrores.guardarError(f.getMessage());
			}
		}
		
		if(e.getSource() == ventanaAsistencia.btnVolver) {

			ventanaAsistencia.dispose();
		}
	}
}