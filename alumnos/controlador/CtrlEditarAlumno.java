package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
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
		ventanaEditarAlumno.lblTxt1.setVisible(true);
		ventanaEditarAlumno.txt1.setVisible(true);
		ventanaEditarAlumno.txt1.setEditable(false);
		dtosEditarAlumno.recuperarInformacionAlumno(legajo, dtosEditarAlumno.getEstado());
		ventanaEditarAlumno.txtLegajo.setText(dtosEditarAlumno.getLegajo());
		ventanaEditarAlumno.txtNombre.setText(dtosEditarAlumno.getNombre());
		ventanaEditarAlumno.txtApellido.setText(dtosEditarAlumno.getApellido());
		ventanaEditarAlumno.txtDNI.setText(dtosEditarAlumno.getDni());
		ventanaEditarAlumno.txtDireccion.setText(dtosEditarAlumno.getDireccion());
		ventanaEditarAlumno.txtTelefono.setText(dtosEditarAlumno.getTelefono());
		ventanaEditarAlumno.txtEmail.setText(dtosEditarAlumno.getEmail());
		ventanaEditarAlumno.txtAño.setText(dtosEditarAlumno.getFechaAño());
		ventanaEditarAlumno.txtMes.setText(dtosEditarAlumno.getFechaMes());
		ventanaEditarAlumno.txtDia.setText(dtosEditarAlumno.getFechaDia());
		ventanaEditarAlumno.comboBox1.setModel(new DefaultComboBoxModel<String>(dtosEditarAlumno.getListaCursos()));
		ventanaEditarAlumno.comboBox1.setSelectedIndex(dtosEditarAlumno.getCursoSeleccionado());
		ventanaEditarAlumno.lblTxt1.setText("Ingreso:");
		ventanaEditarAlumno.txt1.setText(dtosEditarAlumno.getFechaIngreso());
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
			
			dtosEditarAlumno.setNombre(ventanaEditarAlumno.txtNombre.getText());
			dtosEditarAlumno.setApellido(ventanaEditarAlumno.txtApellido.getText());
			dtosEditarAlumno.setDni(ventanaEditarAlumno.txtDNI.getText());
			dtosEditarAlumno.setDireccion(ventanaEditarAlumno.txtDireccion.getText());
			dtosEditarAlumno.setTelefono(ventanaEditarAlumno.txtTelefono.getText());
			dtosEditarAlumno.setEmail(ventanaEditarAlumno.txtEmail.getText());
			dtosEditarAlumno.setFechaAño(ventanaEditarAlumno.txtAño.getText());
			dtosEditarAlumno.setFechaMes(ventanaEditarAlumno.txtMes.getText());
			dtosEditarAlumno.setFechaDia(ventanaEditarAlumno.txtDia.getText());
			dtosEditarAlumno.setEstado(ventanaEditarAlumno.chkbox1.isSelected());
			dtosEditarAlumno.setCurso(ventanaEditarAlumno.comboBox1.getSelectedIndex());
			String msgError = dtosEditarAlumno.checkInformacion(false); 
			ventanaEditarAlumno.lblMsgError.setForeground(Color.RED);
			ventanaEditarAlumno.lblMsgError.setText(msgError);

			if(msgError.equals("")) {
				
				if(dtosEditarAlumno.setActualizarAlumno()) {
					
				ventanaEditarAlumno.lblMsgError.setForeground(Color.BLUE);
				ventanaEditarAlumno.lblMsgError.setText("Los datos se guardaron correctamente.");
				} else {
				
				ventanaEditarAlumno.lblMsgError.setForeground(Color.RED);
				ventanaEditarAlumno.lblMsgError.setText("Error al intentar guardar la información.");
				}
			}
		}
		
		if(e.getSource() == ventanaEditarAlumno.btnImprimir) {
			
			Color colorPanel = ventanaEditarAlumno.panel.getBackground();
			Color colorChckbox = ventanaEditarAlumno.chkbox1.getBackground();
			Color colorComboBox = ventanaEditarAlumno.comboBox1.getBackground();
			ventanaEditarAlumno.panel.setBackground(Color.WHITE);
			ventanaEditarAlumno.chkbox1.setBackground(Color.WHITE);
			ventanaEditarAlumno.comboBox1.setBackground(Color.WHITE);
			ventanaEditarAlumno.btnGuardar.setVisible(false);
			ventanaEditarAlumno.btnImprimir.setVisible(false);
			ventanaEditarAlumno.btnVolver.setVisible(false);
			PrinterJob imprimir = PrinterJob.getPrinterJob();
			PageFormat preformat = imprimir.defaultPage();
			PageFormat postformat = imprimir.pageDialog(preformat);
			imprimir.setPrintable(new Printer(ventanaEditarAlumno.panel), postformat);
			
			if (imprimir.printDialog()) {
				
				try {
					
						imprimir.print();
				} catch (PrinterException f) {
					
						JOptionPane.showMessageDialog(null, "Error al intentar imprimir.");
						System.err.println(f.getMessage());
				}
			}
			
			ventanaEditarAlumno.panel.setBackground(colorPanel);
			ventanaEditarAlumno.chkbox1.setBackground(colorChckbox);
			ventanaEditarAlumno.comboBox1.setBackground(colorComboBox);
			ventanaEditarAlumno.btnGuardar.setVisible(true);
			ventanaEditarAlumno.btnImprimir.setVisible(true);
			ventanaEditarAlumno.btnVolver.setVisible(true);
		}
		
		if(e.getSource() == ventanaEditarAlumno.btnVolver) {
			
			dtosEditarAlumno.limpiarInformacion();
			ventanaEditarAlumno.dispose();
		}
	}
}
