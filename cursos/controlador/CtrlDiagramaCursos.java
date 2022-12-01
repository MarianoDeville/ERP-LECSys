package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import interfaceUsuario.Listado;
import modelo.DtosCurso;

public class CtrlDiagramaCursos implements ActionListener {
	
	private Listado ventanaDiagramaCursos;
	private DtosCurso dtosCurso;
		
	public CtrlDiagramaCursos(Listado vista) {
		
		this.ventanaDiagramaCursos = vista;
		this.dtosCurso = new DtosCurso();
		this.ventanaDiagramaCursos.comboBox1.setVisible(true);
		this.ventanaDiagramaCursos.comboBox1.setModel(new DefaultComboBoxModel<String>(dtosCurso.getListaCriterios()));
		this.ventanaDiagramaCursos.comboBox2.setVisible(true);
		this.ventanaDiagramaCursos.lblTxt1.setVisible(true);
		this.ventanaDiagramaCursos.lblTxt1.setText("Cantidad de horas:");
		this.ventanaDiagramaCursos.txt1.setVisible(true);
		this.ventanaDiagramaCursos.comboBox1.addActionListener(this);
		this.ventanaDiagramaCursos.comboBox2.addActionListener(this);
		this.ventanaDiagramaCursos.btnVolver.addActionListener(this);
	}

	public void iniciar() {
		
		ventanaDiagramaCursos.tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		ventanaDiagramaCursos.tabla.doLayout();
		actualizar();
		ventanaDiagramaCursos.setVisible(true);
	}

	private void actualizar() {

		ventanaDiagramaCursos.comboBox2.setModel(new DefaultComboBoxModel<String>(dtosCurso.getListadoOpciones((String)ventanaDiagramaCursos.comboBox1.getSelectedItem())));
		actualizarTabla();
	}
	
	private void actualizarTabla() {
		
		ventanaDiagramaCursos.tabla.setModel(dtosCurso.getDiagramacion(
								(String)ventanaDiagramaCursos.comboBox1.getSelectedItem(), 
								ventanaDiagramaCursos.comboBox2.getSelectedIndex()));
		
		for(int i = 1 ; i < 33 ; i++) {
			
			ventanaDiagramaCursos.tabla.getColumnModel().getColumn(i).setPreferredWidth(40);
		}

		ventanaDiagramaCursos.tabla.setRowHeight(25);
		ventanaDiagramaCursos.txt1.setText(dtosCurso.getCantHoras());
	}
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == ventanaDiagramaCursos.comboBox1) {
			
			actualizar();
		}
		
		if(e.getSource() == ventanaDiagramaCursos.comboBox2) {
			
			actualizarTabla();
		}
		
		if(e.getSource() == ventanaDiagramaCursos.btnImprimir) {
			
			try {
				
				ventanaDiagramaCursos.tabla.print();
			} catch (PrinterException f) {
				
				f.printStackTrace();
			}
		}
		
		if(e.getSource() == ventanaDiagramaCursos.btnVolver) {
			
			ventanaDiagramaCursos.dispose();
		}
	}
}