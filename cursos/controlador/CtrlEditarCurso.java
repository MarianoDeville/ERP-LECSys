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

public class CtrlEditarCurso implements ActionListener{
	
	private CrearCurso ventana;
	private DtosCurso dtosCurso;
		
	public CtrlEditarCurso(CrearCurso vista) {
		
		this.ventana = vista;
		this.dtosCurso = new DtosCurso();
		this.ventana.comboBoxAula.addActionListener(this);
		this.ventana.comboBoxNivel.addActionListener(this);
		this.ventana.comboBoxAula.addActionListener(this);
		this.ventana.comboBoxProfesor.addActionListener(this);
		this.ventana.btnGuardar.addActionListener(this);
		this.ventana.btnBorrar.addActionListener(this);
		this.ventana.btnVolver.addActionListener(this);
		this.ventana.btnValidar.addActionListener(this);
		this.ventana.tablaHorarios.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				if (e.getClickCount() == 2)
					selección(ventana.tablaHorarios.getSelectedRow(), ventana.tablaHorarios.getSelectedColumn());
			}        
		});
	}
	
	public void iniciar(String idCurso) {
		
		dtosCurso.setCurso(idCurso);
		dtosCurso.getInformacionCurso();
		ventana.btnBorrar.setVisible(true);
		ventana.btnValidar.setEnabled(false);
		ventana.comboBoxAño.setModel(new DefaultComboBoxModel<String>(new String [] {dtosCurso.getAño()}));
		ventana.comboBoxNivel.setModel(new DefaultComboBoxModel<String>(new String [] {dtosCurso.getNivel()}));
		ventana.comboBoxProfesor.setModel(new DefaultComboBoxModel<String>(dtosCurso.getListaProfesores()));
		ventana.comboBoxAula.setModel(new DefaultComboBoxModel<String>(dtosCurso.getListaAulas()));
		ventana.txtCuota.setText(dtosCurso.getValorCuota());
		ventana.comboBoxAula.setSelectedIndex(dtosCurso.getAula());
		ventana.comboBoxAño.setSelectedItem(dtosCurso.getAño());
		ventana.comboBoxProfesor.setSelectedItem(dtosCurso.getNombreProfesor());
		ventana.comboBoxNivel.setSelectedItem(dtosCurso.getNivel());
		actualizar();
		ventana.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == ventana.comboBoxAula) {
			
			actualizar();
		}	
		
		if(e.getSource() == ventana.comboBoxProfesor) {
			
			actualizar();
		}
		
		if(e.getSource() == ventana.btnGuardar) {
		
			guardar();
		}
		
		if(e.getSource() == ventana.btnBorrar) {
			
			borrar();
		}
		
		if(e.getSource() == ventana.btnValidar) {
			
			if(!dtosCurso.autocompletar(ventana.tablaHorarios)) {
				
				ventana.lblMensageError.setForeground(Color.RED);
				ventana.lblMensageError.setText(dtosCurso.getMsgError());
			}
		}
		
		if(e.getSource() == ventana.btnVolver) {
			
			ventana.dispose();
		}
	}

	private void actualizar() {

		ventana.tablaHorarios.setModel(dtosCurso.getHorariosCurso(ventana.comboBoxAula.getSelectedIndex(),
																  ventana.comboBoxProfesor.getSelectedIndex()));
		DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
		centrado.setHorizontalAlignment(JLabel.CENTER);
		
		for(int i = 0 ; i < ventana.tablaHorarios.getColumnCount() ; i++) {
			
			ventana.tablaHorarios.getColumnModel().getColumn(i).setPreferredWidth(40);
			ventana.tablaHorarios.getColumnModel().getColumn(i).setCellRenderer(centrado);
		}

		ventana.tablaHorarios.setRowHeight(25);
	}
	
	private void borrar() {
		
		dtosCurso.setEstado(0);
		
		if(JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar el curso?", "Alerta!", JOptionPane.YES_NO_OPTION) == 0) {
			
			if(dtosCurso.setActualizarCurso()) {
				
				ventana.comboBoxAño.setModel(new DefaultComboBoxModel<String>(new String [] {}));
				ventana.comboBoxNivel.setModel(new DefaultComboBoxModel<String>(new String [] {}));		
				ventana.comboBoxProfesor.setModel(new DefaultComboBoxModel<String>(new String [] {}));
				ventana.comboBoxAula.setModel(new DefaultComboBoxModel<String>(new String [] {}));
				ventana.txtCuota.setText("");
				ventana.lblMensageError.setForeground(Color.BLUE);
				ventana.lblMensageError.setText("El registro se borro exitosamente.");
			} else {
				
				ventana.lblMensageError.setForeground(Color.RED);
				ventana.lblMensageError.setText("Error al acceder a la base de datos.");
			}
		}
	}
	
	
	private void selección(int fila, int columna) {
		
		ventana.btnValidar.setEnabled(true);
		ventana.lblMensageError.setText("");
		boolean comienzo;
		boolean comienzoEliminar;
		int cont = 0;
		int contE = 0;
		
		for(int i = 0; i < ventana.tablaHorarios.getColumnCount();i++) {
			
			if(ventana.tablaHorarios.getValueAt(fila, i).equals("C") || 
					ventana.tablaHorarios.getValueAt(fila, i).equals("C "))
				cont++;
			
			if(ventana.tablaHorarios.getValueAt(fila, i).equals("F") || 
					ventana.tablaHorarios.getValueAt(fila, i).equals("F "))
				cont--;
			
			if(ventana.tablaHorarios.getValueAt(fila, i).equals("CE") || 
					ventana.tablaHorarios.getValueAt(fila, i).equals("CE "))
				contE++;
			
			if(ventana.tablaHorarios.getValueAt(fila, i).equals("FE") || 
					ventana.tablaHorarios.getValueAt(fila, i).equals("FE "))
				contE--;
		}
		comienzo = cont == 0? true:false;
		comienzoEliminar = contE == 0? true:false;

		switch((String)ventana.tablaHorarios.getValueAt(fila, columna)) {
		
			case " ":
				ventana.tablaHorarios.setValueAt(comienzo?"C":"F", fila, columna);
				break;
	
			case "X ":
				ventana.tablaHorarios.setValueAt(comienzo?"C ":"F ", fila, columna);
				break;
				
			case "O":
				ventana.tablaHorarios.setValueAt(comienzoEliminar?"CE":"FE", fila, columna);
				break;
			
			case "O ":
				ventana.tablaHorarios.setValueAt(comienzoEliminar?"CE ":"FE ", fila, columna);
				break;
				
			case "C":
			case "F":
				ventana.tablaHorarios.setValueAt(" ", fila, columna);
				break;
				
			case "CE":
			case "FE":
				ventana.tablaHorarios.setValueAt("O", fila, columna);
				break;
			
			case "C ":
			case "F ":
				ventana.tablaHorarios.setValueAt("X ", fila, columna);
				break;
				
			case "CE ":
			case "FE ":
				ventana.tablaHorarios.setValueAt("O ", fila, columna);
				break;
		}
	}
	
	private void guardar() {
		
		dtosCurso.setAño((String)ventana.comboBoxAño.getSelectedItem());
		dtosCurso.setNivel((String)ventana.comboBoxNivel.getSelectedItem());
		dtosCurso.setIdProfesor(ventana.comboBoxProfesor.getSelectedIndex());
		dtosCurso.setValorCuota(ventana.txtCuota.getText());
		dtosCurso.setAula(ventana.comboBoxAula.getSelectedIndex());
		dtosCurso.setHorarios(ventana.tablaHorarios);

		if(!dtosCurso.isCheckInfo()) {
		
			ventana.lblMensageError.setForeground(Color.RED);
			ventana.lblMensageError.setText(dtosCurso.getMsgError());
			return;
		}		

		if(dtosCurso.setActualizarCurso()) {
			
			ventana.lblMensageError.setForeground(Color.BLUE);
			ventana.lblMensageError.setText("Registro guardado con éxito.");
			ventana.btnBorrar.setEnabled(false);
			ventana.btnGuardar.setEnabled(false);
			ventana.btnValidar.setEnabled(false);
		} else {
			
			ventana.lblMensageError.setForeground(Color.RED);
			ventana.lblMensageError.setText("No se pudo guardar en la base de datos.");
		}
	}
}
