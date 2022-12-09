package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import interfaceUsuario.InformeAlumno;
import interfaceUsuario.Listado;
import modelo.DtosAlumno;

public class CtrlListado implements ActionListener {

	private Listado ventanaListado;
	private DtosAlumno dtosAlumno;
	
	public CtrlListado(Listado vista) {
		
		this.ventanaListado = vista;
		this.dtosAlumno = new DtosAlumno();
		this.ventanaListado.comboBox1.addActionListener(this);
		this.ventanaListado.comboBox2.addActionListener(this);
		this.ventanaListado.btnImprimir.addActionListener(this);
		this.ventanaListado.btn1A.addActionListener(this);
		this.ventanaListado.btnVolver.addActionListener(this);
	}

	public void iniciar() {
		
		ventanaListado.btn1A.setVisible(true);
		ventanaListado.btn1A.setText("Informe");
		ventanaListado.comboBox1.setVisible(true);
		ventanaListado.comboBox2.setVisible(true);
		ventanaListado.lblTxt1.setVisible(true);
		ventanaListado.lblTxt1.setText("Cantidad de alumnos:");
		ventanaListado.txt1.setVisible(true);
		ventanaListado.txt1.setEditable(false);
		ventanaListado.comboBox1.setModel(new DefaultComboBoxModel<String>(dtosAlumno.getCriterio()));
		ventanaListado.comboBox2.setModel(new DefaultComboBoxModel<String>(dtosAlumno.getListadoValorCriterio((String)ventanaListado.comboBox1.getSelectedItem())));
		actualizar();
		ventanaListado.setVisible(true);
	}
	
	private void actualizar() {
		
		ventanaListado.tabla.setModel(dtosAlumno.getListadoAlumnos((String)ventanaListado.comboBox1.getSelectedItem() 
																,dtosAlumno.getIdValorCriterio((String)ventanaListado.comboBox1.getSelectedItem()
																,ventanaListado.comboBox2.getSelectedIndex())));
		ventanaListado.tabla.getColumnModel().getColumn(0).setPreferredWidth(40);
		ventanaListado.tabla.getColumnModel().getColumn(0).setMaxWidth(50);
		ventanaListado.tabla.getColumnModel().getColumn(8).setPreferredWidth(40);
		ventanaListado.tabla.getColumnModel().getColumn(8).setMaxWidth(50);
		ventanaListado.txt1.setText(dtosAlumno.getCantAlumnos());
	}
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == ventanaListado.comboBox1) {

			ventanaListado.comboBox2.setModel(new DefaultComboBoxModel<String>(dtosAlumno.getListadoValorCriterio((String)ventanaListado.comboBox1.getSelectedItem())));
			actualizar();
		}
		
		if(e.getSource() == ventanaListado.comboBox2) {

			actualizar();
		}
		
		if(e.getSource() == ventanaListado.btn1A) {

			int i = 0;
			String legajo = null;
			
			while(i < ventanaListado.tabla.getRowCount()) {
				
				if((boolean)ventanaListado.tabla.getValueAt(i, 8)) {
					
					legajo = (String)ventanaListado.tabla.getValueAt(i, 0);
					dtosAlumno.recuperarInformacionAlumno(legajo, true);
					break;
				}
				i++;
			}
			
			if(legajo != null) {
				
				InformeAlumno ventanaInforme = new InformeAlumno("Informe académico");
				CtrlInformeAlumno ctrlInforme = new CtrlInformeAlumno(ventanaInforme);
				ctrlInforme.iniciar();
			} else {
				
				JOptionPane.showMessageDialog(null, "Debe seleccionar un alumno.");
			}
		}
		
		if(e.getSource() == ventanaListado.btnImprimir) {

			try {
				
				ventanaListado.tabla.print();
			} catch (Exception f) {
				
				JOptionPane.showMessageDialog(null, "Error al intentar imprimir.");
				CtrlLogErrores.guardarError(f.getMessage());
			}
		}		
		
		if(e.getSource() == ventanaListado.btnVolver) {

			ventanaListado.dispose();
		}
	}
}
