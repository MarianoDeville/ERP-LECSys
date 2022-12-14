package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import interfaceUsuario.Listado;
import modelo.DtosAlumno;

public class CtrlExamenes implements ActionListener {

	private Listado ventanaExamenes;
	private DtosAlumno dtosAlumno;
	
	public CtrlExamenes(Listado vista) {
		
		this.ventanaExamenes = vista;
		this.dtosAlumno = new DtosAlumno();
		this.ventanaExamenes.comboBox1.addActionListener(this);
		this.ventanaExamenes.comboBox2.addActionListener(this);
		this.ventanaExamenes.btnImprimir.addActionListener(this);
		this.ventanaExamenes.btn1B.addActionListener(this);
		this.ventanaExamenes.btnVolver.addActionListener(this);
	}
	
	public void iniciar() {
		
		ventanaExamenes.btn1B.setVisible(true);
		ventanaExamenes.btn1B.setText("Guardar");
		ventanaExamenes.lblComboBox1.setVisible(true);
		ventanaExamenes.lblComboBox1.setText("Curso:");
		ventanaExamenes.comboBox1.setVisible(true);
		ventanaExamenes.comboBox1.setModel(new DefaultComboBoxModel<String>(dtosAlumno.getListaCursos()));
		ventanaExamenes.lblComboBox2.setVisible(true);
		ventanaExamenes.lblComboBox2.setText("Examen");
		ventanaExamenes.comboBox2.setVisible(true);
		ventanaExamenes.comboBox2.setModel(new DefaultComboBoxModel<String>(dtosAlumno.getListaTipoExamen()));
		ventanaExamenes.lblTxt1.setVisible(true);
		ventanaExamenes.lblTxt1.setText("Fecha");
		ventanaExamenes.txt1.setVisible(true);
		ventanaExamenes.txt1.setEditable(true);
		ventanaExamenes.lblTxt1Der.setVisible(true);
		ventanaExamenes.lblTxt1Der.setText("DD-MM-AAAA");
		actualizar();
		ventanaExamenes.setVisible(true);
	}
	
	private void actualizar() {
		
		ventanaExamenes.tabla.setModel(dtosAlumno.getTablaExamenes(ventanaExamenes.comboBox1.getSelectedIndex()));
		ventanaExamenes.tabla.getColumnModel().getColumn(0).setPreferredWidth(40);
		ventanaExamenes.tabla.getColumnModel().getColumn(0).setMaxWidth(50);
		ventanaExamenes.btn1B.setEnabled(true);
	}
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == ventanaExamenes.comboBox1) {
			
			actualizar();
		}
		
		if(e.getSource() == ventanaExamenes.comboBox2) {
			
			ventanaExamenes.btn1B.setEnabled(true);
		}
		
		if(e.getSource() == ventanaExamenes.btn1B) {
			
			int cantAlumnos = ventanaExamenes.tabla.getRowCount();
			String notas [][] = new String [cantAlumnos][3];
			dtosAlumno.setCurso(ventanaExamenes.comboBox1.getSelectedIndex());
			dtosAlumno.setTipoExamen((String)ventanaExamenes.comboBox2.getSelectedItem());
			String fechaCargada = ventanaExamenes.txt1.getText();
			fechaCargada = fechaCargada.replaceAll("/","-");

			if(fechaCargada.length() == 10 && fechaCargada.contains("-")) {

				String[] fecha = fechaCargada.split("-");
				dtosAlumno.setFechaDia(fecha[0]);
				dtosAlumno.setFechaMes(fecha[1]);
				dtosAlumno.setFechaA?o(fecha[2]);
				
				for(int i = 0 ; i < cantAlumnos ; i++) {
					
					notas[i][0] = (String)ventanaExamenes.tabla.getValueAt(i, 0);
					notas[i][1] = (String)ventanaExamenes.tabla.getValueAt(i, 3);
				}
				
				dtosAlumno.guardarResultados(notas);
				ventanaExamenes.btn1B.setEnabled(false);
				JOptionPane.showMessageDialog(null,dtosAlumno.getMsg());
			} else {
				
				JOptionPane.showMessageDialog(null, "El formato de la fecha es incorrecto. Ej. 25-10-2020");
			}
		}
		
		if(e.getSource() == ventanaExamenes.btnImprimir) {
			
			try {
				
				ventanaExamenes.tabla.print();
			} catch (PrinterException f) {
				
				f.printStackTrace();
			}
		}
		
		if(e.getSource() == ventanaExamenes.btnVolver) {
			
			ventanaExamenes.dispose();
		}
	}
}
