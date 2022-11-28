package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import interfaceUsuario.Cobro;
import interfaceUsuario.ReciboCobro;
import modelo.DtosCobros;

public class CtrolCobrarInscripcion implements ActionListener {
	
	private Cobro ventanaCobrarInscripcion;
	private DtosCobros dtosCobros;
	private boolean primeraVez;
		
	public CtrolCobrarInscripcion(Cobro vista) {
		
		this.ventanaCobrarInscripcion = vista;
		this.dtosCobros = new DtosCobros();
		this.ventanaCobrarInscripcion.chckbxEnviarEmail.addActionListener(this);
		this.ventanaCobrarInscripcion.btnVolver.addActionListener(this);
		this.ventanaCobrarInscripcion.btnCobrar.addActionListener(this);
		this.ventanaCobrarInscripcion.txtDescuento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				actualizar();
			}
		});
		this.ventanaCobrarInscripcion.txtDescEfectivo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				actualizar();
			}
		});
		this.ventanaCobrarInscripcion.txtInscripci�n.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				actualizar();
			}
		});
		this.ventanaCobrarInscripcion.txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				dtosCobros.setEmail(ventanaCobrarInscripcion.txtEmail.getText());
			}
		});
	}
	
	public void iniciar() {
		
		primeraVez = true; 
		ventanaCobrarInscripcion.tabla.setModel(dtosCobros.getTablaSeleccionados());
		
		if(dtosCobros.getCantidadElementosSeleccionados() > 1) {
			
			ventanaCobrarInscripcion.txtNombre.setEditable(true);
			ventanaCobrarInscripcion.lblDescGrupo.setVisible(true);
			ventanaCobrarInscripcion.txtDescuento.setVisible(true);
		} 
		ventanaCobrarInscripcion.txtNombre.setText(dtosCobros.getNombre());
		ventanaCobrarInscripcion.txtDescuento.setText(dtosCobros.getDescuentoGrupo() + "");
		ventanaCobrarInscripcion.txtEmail.setText(dtosCobros.getEmail());
		ventanaCobrarInscripcion.setVisible(true);
		
		if(dtosCobros.getEmail().length() > 2)
			ventanaCobrarInscripcion.chckbxEnviarEmail.setSelected(true);
		
		actualizar();
	}
	
	private void actualizar() {
		
		String mensaje = null;
		ventanaCobrarInscripcion.lblMsgError.setForeground(Color.RED);
		ventanaCobrarInscripcion.lblMsgError.setText("");
		ventanaCobrarInscripcion.lblEmail.setVisible(ventanaCobrarInscripcion.chckbxEnviarEmail.isSelected());
		ventanaCobrarInscripcion.txtEmail.setVisible(ventanaCobrarInscripcion.chckbxEnviarEmail.isSelected());
		
		mensaje = dtosCobros.setDescuentoGrupo(ventanaCobrarInscripcion.txtDescuento.getText());
		
		if(mensaje == null)
			mensaje = dtosCobros.setDescuentoContado(ventanaCobrarInscripcion.txtDescEfectivo.getText());
		
		if(mensaje == null)
			mensaje = dtosCobros.setInscripcion(ventanaCobrarInscripcion.txtInscripci�n.getText());
		
		if(!primeraVez)
			ventanaCobrarInscripcion.lblMsgError.setText(mensaje);
		
		ventanaCobrarInscripcion.txtTotalPagar.setText(dtosCobros.getMontoTotal() + "");
		
		if(dtosCobros.getCantidadElementosSeleccionados() == 1)
			ventanaCobrarInscripcion.txtEmail.setText(dtosCobros.getEmail());
		
		primeraVez = false;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ventanaCobrarInscripcion.chckbxEnviarEmail) {
			
			actualizar();
		}
		
		if(e.getSource() == ventanaCobrarInscripcion.btnCobrar) {
			
			dtosCobros.setNombre(ventanaCobrarInscripcion.txtNombre.getText());
			dtosCobros.setEnviarEmail(ventanaCobrarInscripcion.chckbxEnviarEmail.isSelected());
			dtosCobros.setFactura(ventanaCobrarInscripcion.txtFactura.getText());
			
			if(ventanaCobrarInscripcion.chckbxEnviarEmail.isSelected()) {
				
				dtosCobros.setEmail(ventanaCobrarInscripcion.txtEmail.getText());
			} else {
				
				dtosCobros.setEmail("");
			}
			String error = dtosCobros.validarInformaci�n();
			
			if(error.length() == 0) {

				if(dtosCobros.guardarCobroGrupo()) {
					
					ventanaCobrarInscripcion.lblMsgError.setForeground(Color.BLUE);
					ventanaCobrarInscripcion.lblMsgError.setText("Operaci�n almacenada en la base de datos.");
					ventanaCobrarInscripcion.btnCobrar.setEnabled(false);

					if(ventanaCobrarInscripcion.chckbxEnviarEmail.isSelected()) {
						
						EmailSenderService emailService = new EmailSenderService();
						emailService.mandarCorreo(dtosCobros.getEmail(), "Recibo de pago", dtosCobros.getCuerpoEmail());
					} else {
					
						ReciboCobro ventanaReciboPago = new ReciboCobro("Comprobante de pago");
						CtrolReciboCobrarInscripcion ctrolReciboInscripcion = new CtrolReciboCobrarInscripcion(ventanaReciboPago);
						ctrolReciboInscripcion.iniciar();
					}
					dtosCobros.setBorrarSeleccionados();
				} else {
					
					ventanaCobrarInscripcion.lblMsgError.setForeground(Color.RED);
					ventanaCobrarInscripcion.lblMsgError.setText("Error al intentar guardar la informaci�n en la base de datos.");
				}
			} else {
				
				ventanaCobrarInscripcion.lblMsgError.setForeground(Color.RED);
				ventanaCobrarInscripcion.lblMsgError.setText(error);
			}
		}

		if(e.getSource() == ventanaCobrarInscripcion.btnVolver) {
			
			dtosCobros.setBorrarSeleccionados();
			ventanaCobrarInscripcion.dispose();
		}
	}
}
