package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import interfaceUsuario.Cobro;
import interfaceUsuario.ReciboCobro;
import modelo.DtosCobros;

public class CtrlRealizarCobro implements ActionListener {
	
	private Cobro ventanaCobrarCuota;
	private DtosCobros dtosCobros;
	private int calculoDescuentoGrupo;
	
	public CtrlRealizarCobro(Cobro vista) {
		
		this.ventanaCobrarCuota = vista;
		this.dtosCobros = new DtosCobros();
		this.ventanaCobrarCuota.btnVolver.addActionListener(this);
		this.ventanaCobrarCuota.btnCobrar.addActionListener(this);
		this.ventanaCobrarCuota.btnCentral.addActionListener(this);
		this.ventanaCobrarCuota.txt3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				actualizar();
			}
		});
		this.ventanaCobrarCuota.txt4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				actualizar();
			}
		});
		this.ventanaCobrarCuota.comboBox.addActionListener(this);
		this.ventanaCobrarCuota.chckbxEnviarEmail.addActionListener(this);
	}

	public void iniciar() {

		ventanaCobrarCuota.chckbxTabla2.setVisible(false);
		ventanaCobrarCuota.txtTabla2.setVisible(false);
		ventanaCobrarCuota.scrollTabla2.setVisible(false);
		ventanaCobrarCuota.lbl1.setVisible(true);
		ventanaCobrarCuota.lbl1.setText("Descuento grupo:");
		ventanaCobrarCuota.lbl2.setText("Valor cuota:");		
		ventanaCobrarCuota.lbl3.setText("Recargo mora:");
		ventanaCobrarCuota.lbl4.setText("Descuento pago efectivo:");
		ventanaCobrarCuota.lbl5.setVisible(true);
		ventanaCobrarCuota.lbl5.setText("Monto a pagar:");
		ventanaCobrarCuota.lblComboBox.setVisible(true);
		ventanaCobrarCuota.lblComboBox.setText("Concepto:");
		ventanaCobrarCuota.txt1.setVisible(true);
		ventanaCobrarCuota.txt1.setEditable(false);
		ventanaCobrarCuota.txt2.setEditable(false);
		ventanaCobrarCuota.txt3.setEditable(false);
		ventanaCobrarCuota.txt4.setEditable(false);
		ventanaCobrarCuota.txt5.setVisible(true);
		ventanaCobrarCuota.txt5.setEditable(false);
		ventanaCobrarCuota.txtNombre.setText(dtosCobros.getNombre());		
		ventanaCobrarCuota.btnCobrar.setEnabled(false);
		ventanaCobrarCuota.btnCentral.setVisible(true);
		ventanaCobrarCuota.btnCentral.setText("Borrar");
		ventanaCobrarCuota.btnCentral.setEnabled(false);
		ventanaCobrarCuota.comboBox.setVisible(true);
		ventanaCobrarCuota.comboBox.setModel(new DefaultComboBoxModel<String>(dtosCobros.getListadoConceptos()));
		ventanaCobrarCuota.tabla1.setModel(dtosCobros.getTablaSeleccionados());
		ventanaCobrarCuota.tabla1.getColumnModel().getColumn(0).setPreferredWidth(45);
		ventanaCobrarCuota.tabla1.getColumnModel().getColumn(0).setMaxWidth(55);
		
		if(dtosCobros.getEmail().length() > 2) {
			
			ventanaCobrarCuota.chckbxEnviarEmail.setSelected(true);
			ventanaCobrarCuota.lblEmail.setVisible(true);
			ventanaCobrarCuota.txtEmail.setVisible(true);
			ventanaCobrarCuota.txtEmail.setText(dtosCobros.getEmail());
		}
		calculoDescuentoGrupo = dtosCobros.getDescuentoGrupo() * dtosCobros.getSumaCuotas() /100;
		ventanaCobrarCuota.txt1.setText(calculoDescuentoGrupo + "");
		ventanaCobrarCuota.txt2.setText(dtosCobros.getSumaCuotas() + "");
		ventanaCobrarCuota.setVisible(true);
	}
		
	private void actualizar() {
		
		String mensaje = null;
		
		if(ventanaCobrarCuota.comboBox.getSelectedIndex() == 0) {
			
			ventanaCobrarCuota.txt3.setEditable(false);
			ventanaCobrarCuota.txt4.setEditable(false);
			ventanaCobrarCuota.txt5.setText("0");
			ventanaCobrarCuota.btnCobrar.setEnabled(false);
			ventanaCobrarCuota.btnCentral.setEnabled(false);
			return;
		}
		ventanaCobrarCuota.txt3.setEditable(true);
		ventanaCobrarCuota.txt4.setEditable(true);
		ventanaCobrarCuota.btnCobrar.setEnabled(true);
		ventanaCobrarCuota.btnCentral.setEnabled(true);
		ventanaCobrarCuota.lblMsgError.setForeground(Color.RED);
		ventanaCobrarCuota.lblMsgError.setText("");
		mensaje = dtosCobros.setRecargoMora(ventanaCobrarCuota.txt3.getText());
		
		if(mensaje == null)
			mensaje = dtosCobros.setDescuentoContado(ventanaCobrarCuota.txt4.getText());
		
		dtosCobros.setCantidadCuotasSeleccionadas(ventanaCobrarCuota.comboBox.getSelectedIndex());
		ventanaCobrarCuota.lblMsgError.setText(mensaje);
		ventanaCobrarCuota.txt5.setText(dtosCobros.getCalculoCobro((String)ventanaCobrarCuota.comboBox.getSelectedItem()));
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ventanaCobrarCuota.comboBox) {
			
			actualizar();
		}
		
		if(e.getSource() == ventanaCobrarCuota.chckbxEnviarEmail) {
			
			ventanaCobrarCuota.chckbxEnviarEmail.setSelected(ventanaCobrarCuota.chckbxEnviarEmail.isSelected());
			ventanaCobrarCuota.lblEmail.setVisible(ventanaCobrarCuota.chckbxEnviarEmail.isSelected());
			ventanaCobrarCuota.txtEmail.setVisible(ventanaCobrarCuota.chckbxEnviarEmail.isSelected());
			ventanaCobrarCuota.txtEmail.setText(dtosCobros.getEmail());
		}
		
		if(e.getSource() == ventanaCobrarCuota.btnCobrar) {
			
			registraCobro();
		}
		
		if(e.getSource() == ventanaCobrarCuota.btnCentral) {
			
			eliminarDeuda();	
		}

		if(e.getSource() == ventanaCobrarCuota.btnVolver) {
			
			dtosCobros.setBorrarSeleccionados();
			ventanaCobrarCuota.dispose();
		}
	}

	private void registraCobro() {

		dtosCobros.setFactura(ventanaCobrarCuota.txtFactura.getText());
		ventanaCobrarCuota.lblMsgError.setForeground(Color.BLUE);
		ventanaCobrarCuota.lblMsgError.setText("Procesando la operación.");
		dtosCobros.setEnviarEmail(ventanaCobrarCuota.chckbxEnviarEmail.isSelected());

		if(ventanaCobrarCuota.chckbxEnviarEmail.isSelected())
			dtosCobros.setEmail(ventanaCobrarCuota.txtEmail.getText());
		else 
			dtosCobros.setEmail("");

		String error = dtosCobros.validarInformación(false, false);
		
		if(error.length() > 0) {
			
			ventanaCobrarCuota.lblMsgError.setForeground(Color.RED);
			ventanaCobrarCuota.lblMsgError.setText(error);
			return;
		}
		
		if(dtosCobros.guardarCobroCuota()) {
	
			if(ventanaCobrarCuota.chckbxEnviarEmail.isSelected()) {
				
				EmailSenderService emailService = new EmailSenderService();
				if(emailService.mandarCorreo(dtosCobros.getEmail(), "Recibo de pago", dtosCobros.getCuerpoEmail())) {
					
					ventanaCobrarCuota.lblMsgError.setForeground(Color.RED);
					ventanaCobrarCuota.lblMsgError.setText("Error en el emvío del email.");		
				}
			} else {
			
				ReciboCobro ventanaReciboPago = new ReciboCobro("Comprobante de pago");
				CtrlReciboCobrarInscripcion ctrolReciboInscripcion = new CtrlReciboCobrarInscripcion(ventanaReciboPago);
				ctrolReciboInscripcion.iniciar();
			}
			ventanaCobrarCuota.lblMsgError.setForeground(Color.BLUE);
			ventanaCobrarCuota.lblMsgError.setText("Operación almacenada en la base de datos.");
			ventanaCobrarCuota.btnCobrar.setEnabled(false);
			ventanaCobrarCuota.btnCentral.setEnabled(false);
			ventanaCobrarCuota.comboBox.setEnabled(false);
			dtosCobros.setBorrarSeleccionados();
			return;
		}
		ventanaCobrarCuota.lblMsgError.setForeground(Color.RED);
		ventanaCobrarCuota.lblMsgError.setText("Error al intentar guardar la información en la base de datos.");
	}
	
	private void eliminarDeuda() {

		if(JOptionPane.showConfirmDialog(null, "¿Esta seguro de borrar la deuda seleccionada?", "Alerta!", JOptionPane.YES_NO_OPTION) == 0) {
		
			if(dtosCobros.setBorrarDeuda()) {
				
				ventanaCobrarCuota.lblMsgError.setForeground(Color.BLUE);
				ventanaCobrarCuota.lblMsgError.setText("Operación almacenada en la base de datos.");
				ventanaCobrarCuota.btnCobrar.setEnabled(false);
				ventanaCobrarCuota.btnCentral.setEnabled(false);
				ventanaCobrarCuota.comboBox.setEnabled(false);
				dtosCobros.setBorrarSeleccionados();
				return;
			} 
			ventanaCobrarCuota.lblMsgError.setForeground(Color.RED);
			ventanaCobrarCuota.lblMsgError.setText("Error al intentar guardar la información en la base de datos.");
		}
	}
}
