package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import interfaceUsuario.CrearCurso;
import modelo.DtosCurso;

public class CtrlNuevoCurso implements ActionListener {
	
	private CrearCurso ventana;
	private DtosCurso dtosCurso;
			
	public CtrlNuevoCurso(CrearCurso vista) {
		
		this.ventana = vista;
		this.dtosCurso = new DtosCurso();
		this.ventana.comboBoxNivel.addActionListener(this);
		this.ventana.comboBoxAula.addActionListener(this);
		this.ventana.comboBoxProfesor.addActionListener(this);
		this.ventana.btnGuardar.addActionListener(this);
		this.ventana.btnValidar.addActionListener(this);
		this.ventana.btnVolver.addActionListener(this);
		this.ventana.tabla.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				if (e.getClickCount() == 2)
					selección(ventana.tabla.getSelectedRow(), ventana.tabla.getSelectedColumn());
			}        
		});
	}
	
	public void iniciar() {
		
		ventana.btnValidar.setEnabled(false);
		ventana.btnGuardar.setEnabled(false);
		ventana.lblDescripción.setForeground(Color.GRAY);
		ventana.lblDescripción.setText("X: No disponible. - O: Asignado al curso actual. - C: Comienzo. - F: Fin.");
		ventana.comboBoxNivel.setModel(new DefaultComboBoxModel<String>(dtosCurso.getListaNivel()));
		String listaProfesores[] = dtosCurso.getListaProfesores();

		if(listaProfesores.length == 0) {
			
			ventana.dispose();
			JOptionPane.showMessageDialog(null, "Primero debe cargar un profesor.");
			return;
		}
		ventana.comboBoxProfesor.setModel(new DefaultComboBoxModel<String>(listaProfesores));
		ventana.comboBoxAula.setModel(new DefaultComboBoxModel<String>(dtosCurso.getListaAulas()));
		ventana.comboBoxAño.setModel(new DefaultComboBoxModel<String>(dtosCurso.getListaAños((String)ventana.comboBoxNivel.getSelectedItem())));
		actualizar();
		ventana.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == ventana.comboBoxNivel) {
			
			ventana.comboBoxAño.setModel(new DefaultComboBoxModel<String>(dtosCurso.getListaAños((String)ventana.comboBoxNivel.getSelectedItem())));
			actualizar();
		}		
		
		if(e.getSource() == ventana.comboBoxAula) {
			
			actualizar();
		}	
		
		if(e.getSource() == ventana.comboBoxProfesor) {
			
			actualizar();
		}
		
		if(e.getSource() == ventana.btnValidar) {
			
			if(!dtosCurso.autocompletar(ventana.tabla)) {
				
				ventana.lblMensageError.setForeground(Color.RED);
				ventana.lblMensageError.setText(dtosCurso.getMsgError());
			}
		}
		
		if(e.getSource() == ventana.btnGuardar) {
		
			guardar();
		}
		
		if(e.getSource() == ventana.btnVolver) {
			
			ventana.dispose();
		}
	}
	
	private void actualizar() {

		ventana.lblMensageError.setText("");
		dtosCurso.setCurso("0");
		ventana.tabla.setModel(dtosCurso.getHorariosCurso(ventana.comboBoxAula.getSelectedIndex(),
																  ventana.comboBoxProfesor.getSelectedIndex()));
		DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
		centrado.setHorizontalAlignment(JLabel.CENTER);
		
		for(int i = 0 ; i < ventana.tabla.getColumnCount() ; i++) {
			
			ventana.tabla.getColumnModel().getColumn(i).setPreferredWidth(40);
			ventana.tabla.getColumnModel().getColumn(i).setCellRenderer(centrado);
		}
		ventana.tabla.setRowHeight(25);
	}
	
	private void selección(int fila, int columna) {

		ventana.btnValidar.setEnabled(true);
		ventana.btnGuardar.setEnabled(true);
		ventana.lblMensageError.setText("");
		boolean comienzo;
		int cont = 0;
		
		for(int i = 0; i < ventana.tabla.getColumnCount();i++) {
			
			if(ventana.tabla.getValueAt(fila, i).equals("C") || 
					ventana.tabla.getValueAt(fila, i).equals("C "))
				cont++;
			
			if(ventana.tabla.getValueAt(fila, i).equals("F") || 
					ventana.tabla.getValueAt(fila, i).equals("F "))
				cont--;
		}

		comienzo = cont == 0? true:false;
		
		if(ventana.tabla.getValueAt(fila, columna).equals(" ")) {
		
			if(comienzo) 
				ventana.tabla.setValueAt("C", fila, columna);
			else 
				ventana.tabla.setValueAt("F", fila, columna);

		} else if(ventana.tabla.getValueAt(fila, columna).equals("X ")){
		
			if(comienzo)
				ventana.tabla.setValueAt("C ", fila, columna);
			else
				ventana.tabla.setValueAt("F ", fila, columna);

		} else if(ventana.tabla.getValueAt(fila, columna).equals("C") || 
				ventana.tabla.getValueAt(fila, columna).equals("F")) {
			
			ventana.tabla.setValueAt(" ", fila, columna);
		} else if(ventana.tabla.getValueAt(fila, columna).equals("C ") || 
				ventana.tabla.getValueAt(fila, columna).equals("F ")) {
			
			ventana.tabla.setValueAt("X ", fila, columna);
		}
	}
	
	private void guardar() {
		
		dtosCurso.setAño((String)ventana.comboBoxAño.getSelectedItem());
		dtosCurso.setNivel((String)ventana.comboBoxNivel.getSelectedItem());
		dtosCurso.setIdProfesor(ventana.comboBoxProfesor.getSelectedIndex());
		dtosCurso.setValorCuota(ventana.txtCuota.getText());
		dtosCurso.setAula(ventana.comboBoxAula.getSelectedIndex());
		dtosCurso.setHorarios(ventana.tabla);
		
		if(!dtosCurso.isCheckInfo()) {
		
			ventana.lblMensageError.setForeground(Color.RED);
			ventana.lblMensageError.setText(dtosCurso.getMsgError());
			return;
		}
		
		if(dtosCurso.setNuevoCurso()) {
			
			ventana.lblMensageError.setForeground(Color.BLUE);
			ventana.txtCuota.setText("");
			actualizar();
			ventana.lblMensageError.setText("Registro guardado con éxito.");
			ventana.btnGuardar.setEnabled(false);
			ventana.btnValidar.setEnabled(false);
		} else {
			
			ventana.lblMensageError.setForeground(Color.RED);
			ventana.lblMensageError.setText("No se pudo guardar en la base de datos.");
		}
	}
}
