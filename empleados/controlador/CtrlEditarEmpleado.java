package controlador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import interfaceUsuario.Nuevo;
import modelo.DtosEmpleado;

public class CtrlEditarEmpleado implements ActionListener {
	
	private Nuevo ventanaEditarEmpleado;
	private DtosEmpleado dtosEditarEmpleado;

	public CtrlEditarEmpleado(Nuevo vista) {
		
		this.ventanaEditarEmpleado = vista;
		this.dtosEditarEmpleado = new DtosEmpleado();
		this.ventanaEditarEmpleado.btnGuardar.addActionListener(this);
		this.ventanaEditarEmpleado.btnImprimir.addActionListener(this);
		this.ventanaEditarEmpleado.btnVolver.addActionListener(this);
		
	}
	public void iniciar(String legajo) {

		ventanaEditarEmpleado.lblcomboBox1.setText("Sector:");
		ventanaEditarEmpleado.comboBox1.setModel(new DefaultComboBoxModel<String>(dtosEditarEmpleado.getListaSectores()));
		ventanaEditarEmpleado.lblcomboBox2.setText("Relación:");
		ventanaEditarEmpleado.comboBox2.setModel(new DefaultComboBoxModel<String>(dtosEditarEmpleado.getListaTipos()));
		ventanaEditarEmpleado.lblcomboBox2.setVisible(true);
		ventanaEditarEmpleado.comboBox2.setVisible(true);
		ventanaEditarEmpleado.lblTxt1.setText("Cargo:");
		ventanaEditarEmpleado.lblTxt1.setVisible(true);
		ventanaEditarEmpleado.txt1.setVisible(true);
		ventanaEditarEmpleado.lblTxt2.setText("Salario:");
		ventanaEditarEmpleado.lblTxt2.setVisible(true);
		ventanaEditarEmpleado.txt2.setVisible(true);
		ventanaEditarEmpleado.setVisible(true);
		ventanaEditarEmpleado.btnImprimir.setVisible(true);
		ventanaEditarEmpleado.chkbox1.setVisible(true);
		ventanaEditarEmpleado.setMinimumSize(new Dimension(450, 580));
		dtosEditarEmpleado.getInformacionEmpleado(legajo);
		ventanaEditarEmpleado.txtLegajo.setText(dtosEditarEmpleado.getLegajo());
		ventanaEditarEmpleado.txtNombre.setText(dtosEditarEmpleado.getNombre());
		ventanaEditarEmpleado.txtApellido.setText(dtosEditarEmpleado.getApellido());
		ventanaEditarEmpleado.txtDNI.setText(dtosEditarEmpleado.getDni());
		ventanaEditarEmpleado.txtDireccion.setText(dtosEditarEmpleado.getDireccion());
		ventanaEditarEmpleado.txtTelefono.setText(dtosEditarEmpleado.getTelefono());
		ventanaEditarEmpleado.txtEmail.setText(dtosEditarEmpleado.getEmail());
		ventanaEditarEmpleado.txtAño.setText(dtosEditarEmpleado.getFechaNacimientoAño());
		ventanaEditarEmpleado.txtMes.setText(dtosEditarEmpleado.getFechaNacimientoMes());
		ventanaEditarEmpleado.txtDia.setText(dtosEditarEmpleado.getFechaNacimientoDia());
		ventanaEditarEmpleado.txt1.setText(dtosEditarEmpleado.getCargo());
		ventanaEditarEmpleado.txt2.setText(dtosEditarEmpleado.getSalario());
		ventanaEditarEmpleado.chkbox1.setSelected(dtosEditarEmpleado.getEstado());
		ventanaEditarEmpleado.comboBox1.setSelectedItem(dtosEditarEmpleado.getSector());
		ventanaEditarEmpleado.comboBox2.setSelectedItem(dtosEditarEmpleado.getRelacion());
		ventanaEditarEmpleado.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ventanaEditarEmpleado.btnGuardar) {
			
			dtosEditarEmpleado.setNombre(ventanaEditarEmpleado.txtNombre.getText());
			dtosEditarEmpleado.setApellido(ventanaEditarEmpleado.txtApellido.getText());
			dtosEditarEmpleado.setDni(ventanaEditarEmpleado.txtDNI.getText());
			dtosEditarEmpleado.setDireccion(ventanaEditarEmpleado.txtDireccion.getText());
			dtosEditarEmpleado.setTelefono(ventanaEditarEmpleado.txtTelefono.getText());
			dtosEditarEmpleado.setEmail(ventanaEditarEmpleado.txtEmail.getText());
			dtosEditarEmpleado.setFechaNacimientoAño(ventanaEditarEmpleado.txtAño.getText());
			dtosEditarEmpleado.setFechaNacimientoMes(ventanaEditarEmpleado.txtMes.getText());
			dtosEditarEmpleado.setFechaNacimientoDia(ventanaEditarEmpleado.txtDia.getText());
			dtosEditarEmpleado.setCargo(ventanaEditarEmpleado.txt1.getText());
			dtosEditarEmpleado.setSalario(ventanaEditarEmpleado.txt2.getText());
			dtosEditarEmpleado.setSector((String)ventanaEditarEmpleado.comboBox1.getSelectedItem());
			dtosEditarEmpleado.setRelacion((String)ventanaEditarEmpleado.comboBox2.getSelectedItem());
			dtosEditarEmpleado.setEstado(ventanaEditarEmpleado.chkbox1.isSelected()?"Activo":"");
			String msgError = dtosEditarEmpleado.checkInformacion();
			ventanaEditarEmpleado.lblMsgError.setForeground(Color.RED);
			ventanaEditarEmpleado.lblMsgError.setText(msgError);
			
			if(msgError.equals("")) {
			
				if(dtosEditarEmpleado.setActualizarEmpleado()) {
					
					ventanaEditarEmpleado.lblMsgError.setForeground(Color.BLUE);
					ventanaEditarEmpleado.lblMsgError.setText("Registro guardado con éxito.");
				} else {
					
					ventanaEditarEmpleado.lblMsgError.setForeground(Color.RED);
					ventanaEditarEmpleado.lblMsgError.setText("No se pudo guardar la información.");
				}
			}
		}
		
		if(e.getSource() == ventanaEditarEmpleado.btnImprimir) {
			
			Color colorPanel = ventanaEditarEmpleado.panel.getBackground();
			Color colorChckbox = ventanaEditarEmpleado.chkbox1.getBackground();
			Color colorComboBox = ventanaEditarEmpleado.comboBox1.getBackground();
			ventanaEditarEmpleado.panel.setBackground(Color.WHITE);
			ventanaEditarEmpleado.chkbox1.setBackground(Color.WHITE);
			ventanaEditarEmpleado.comboBox1.setBackground(Color.WHITE);
			ventanaEditarEmpleado.btnGuardar.setVisible(false);
			ventanaEditarEmpleado.btnImprimir.setVisible(false);
			ventanaEditarEmpleado.btnVolver.setVisible(false);
			PrinterJob imprimir = PrinterJob.getPrinterJob();
			PageFormat preformat = imprimir.defaultPage();
			PageFormat postformat = imprimir.pageDialog(preformat);
			imprimir.setPrintable(new Printer(ventanaEditarEmpleado.panel), postformat);
			
			if (imprimir.printDialog()) {
				
				try {
					
						imprimir.print();
				} catch (PrinterException f) {
					
						JOptionPane.showMessageDialog(null, "Error al intentar imprimir.");
						System.err.println(f.getMessage());
				}
			}
			ventanaEditarEmpleado.panel.setBackground(colorPanel);
			ventanaEditarEmpleado.chkbox1.setBackground(colorChckbox);
			ventanaEditarEmpleado.comboBox1.setBackground(colorComboBox);
			ventanaEditarEmpleado.btnGuardar.setVisible(true);
			ventanaEditarEmpleado.btnImprimir.setVisible(true);
			ventanaEditarEmpleado.btnVolver.setVisible(true);
		}
		
		if(e.getSource() == ventanaEditarEmpleado.btnVolver) {
			
			ventanaEditarEmpleado.dispose();
		}
	}
	
}
