package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import interfaceUsuario.Cobro;
import interfaceUsuario.ReciboCobro;
import modelo.DtosCobros;
import modelo.DtosGrupoFamiliar;

public class CtrolCobrarInscripcion implements ActionListener {
	
	private Cobro ventanaCobrarInscripcion;
	private DtosCobros dtosCobros;
	private DtosGrupoFamiliar dtosGrupoFamiliar;
	private boolean checkInfo;
	private boolean haySeleccion;
	private int elemento;
		
	public CtrolCobrarInscripcion(Cobro vista) {
		
		this.ventanaCobrarInscripcion = vista;
		this.dtosCobros = new DtosCobros();
		this.dtosGrupoFamiliar = new DtosGrupoFamiliar();
		this.ventanaCobrarInscripcion.chckbxEnviarEmail.addActionListener(this);
		this.ventanaCobrarInscripcion.chckbxTabla2.addActionListener(this);
		this.ventanaCobrarInscripcion.btnVolver.addActionListener(this);
		this.ventanaCobrarInscripcion.btnCobrar.addActionListener(this);
		this.ventanaCobrarInscripcion.tabla2.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 1) {

					elemento = ventanaCobrarInscripcion.tabla2.getSelectedRow();
					haySeleccion = (boolean)ventanaCobrarInscripcion.tabla2.getValueAt(elemento, 2);
					actualizarDatos();
		        }
		      }
		  });
		this.ventanaCobrarInscripcion.txtDescuento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				actualizoSuma();
			}
		});
		this.ventanaCobrarInscripcion.txtDescEfectivo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				checkInfo = true;
				actualizoSuma();
			}
		});
		this.ventanaCobrarInscripcion.txtInscripci�n.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				checkInfo = true;
				actualizoSuma();
			}
		});
		this.ventanaCobrarInscripcion.txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				dtosCobros.setEmail(ventanaCobrarInscripcion.txtEmail.getText());
			}
		});		
		this.ventanaCobrarInscripcion.txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				dtosCobros.setNombre(ventanaCobrarInscripcion.txtNombre.getText());
			}
		});
		this.ventanaCobrarInscripcion.txtTabla2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				actualizarTabla();
			}
		});
	}

	public void iniciar() {
		
		checkInfo = false; 
		haySeleccion = false;

		if(dtosCobros.getEmail().length() > 2) {
			
			ventanaCobrarInscripcion.chckbxEnviarEmail.setSelected(true);
			ventanaCobrarInscripcion.lblEmail.setVisible(true);
			ventanaCobrarInscripcion.txtEmail.setVisible(true);
		}
		ventanaCobrarInscripcion.tabla1.setModel(dtosCobros.getTablaSeleccionados());
		ventanaCobrarInscripcion.tabla1.getColumnModel().getColumn(0).setPreferredWidth(45);
		ventanaCobrarInscripcion.tabla1.getColumnModel().getColumn(0).setMaxWidth(55);	
		actualizarDatos();
		actualizarTabla();
		actualizoSuma();
		ventanaCobrarInscripcion.setVisible(true);
	}
		
	private void limpiarOtros() {
		
		for(int i = 0; i < ventanaCobrarInscripcion.tabla2.getRowCount(); i++) {
			
			if(i != elemento || !ventanaCobrarInscripcion.chckbxTabla2.isSelected())
				ventanaCobrarInscripcion.tabla2.setValueAt((boolean) false, i, 2);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ventanaCobrarInscripcion.chckbxTabla2) {
			
			if(!ventanaCobrarInscripcion.chckbxTabla2.isSelected())
				haySeleccion = false;

			actualizarTabla();
			actualizarDatos();
		}
		
		if(e.getSource() == ventanaCobrarInscripcion.chckbxEnviarEmail) {
			
			ventanaCobrarInscripcion.lblEmail.setVisible(ventanaCobrarInscripcion.chckbxEnviarEmail.isSelected());
			ventanaCobrarInscripcion.txtEmail.setVisible(ventanaCobrarInscripcion.chckbxEnviarEmail.isSelected());
		}
		
		if(e.getSource() == ventanaCobrarInscripcion.btnCobrar) {
				
			registraCobro();
		}

		if(e.getSource() == ventanaCobrarInscripcion.btnVolver) {
			
			dtosCobros.setBorrarSeleccionados();
			ventanaCobrarInscripcion.dispose();
		}
	}
	
	private void actualizarDatos() {

		limpiarOtros();
		ventanaCobrarInscripcion.txtNombre.setText(dtosCobros.getNombre());
		ventanaCobrarInscripcion.txtDescuento.setText(dtosCobros.getDescuentoGrupo() + "");

		if(dtosCobros.getCantidadElementosSeleccionados() == 1) {
			
			ventanaCobrarInscripcion.txtNombre.setEditable(false);
			ventanaCobrarInscripcion.lblDescGrupo.setVisible(false);
			ventanaCobrarInscripcion.txtDescuento.setVisible(false);
			ventanaCobrarInscripcion.txtEmail.setText(dtosCobros.getEmail());
		} else {

			ventanaCobrarInscripcion.txtNombre.setEditable(true);
			ventanaCobrarInscripcion.lblDescGrupo.setVisible(true);
			ventanaCobrarInscripcion.txtDescuento.setVisible(true);
		}

		if(ventanaCobrarInscripcion.chckbxTabla2.isSelected()) {
			
			ventanaCobrarInscripcion.lblDescGrupo.setVisible(true);
			ventanaCobrarInscripcion.txtDescuento.setVisible(true);
		}
		
		if(haySeleccion) {

			dtosGrupoFamiliar.setElementoSeleccionado(elemento);
			dtosGrupoFamiliar.setInformacionGrupo();
			ventanaCobrarInscripcion.txtNombre.setText(dtosGrupoFamiliar.getNombreFamilia());
			ventanaCobrarInscripcion.txtDescuento.setText(dtosGrupoFamiliar.getDescuento());
			ventanaCobrarInscripcion.txtEmail.setText(dtosGrupoFamiliar.getEmail());
		}
	}
	
	private void actualizoSuma() {
		
		String mensaje = null;
		ventanaCobrarInscripcion.lblMsgError.setForeground(Color.RED);
		ventanaCobrarInscripcion.lblMsgError.setText("");
		mensaje = dtosCobros.setDescuentoGrupo(ventanaCobrarInscripcion.txtDescuento.getText());
		
		if(mensaje == null)
			mensaje = dtosCobros.setDescuentoContado(ventanaCobrarInscripcion.txtDescEfectivo.getText());
		
		if(mensaje == null)
			mensaje = dtosCobros.setInscripcion(ventanaCobrarInscripcion.txtInscripci�n.getText());
		
		if(checkInfo)
			ventanaCobrarInscripcion.lblMsgError.setText(mensaje);
		
		ventanaCobrarInscripcion.txtTotalPagar.setText(dtosCobros.getMontoTotal() + "");
	}

	private void actualizarTabla() {

		ventanaCobrarInscripcion.txtTabla2.setVisible(ventanaCobrarInscripcion.chckbxTabla2.isSelected());
		ventanaCobrarInscripcion.scrollTabla2.setVisible(ventanaCobrarInscripcion.chckbxTabla2.isSelected());
		ventanaCobrarInscripcion.tabla2.setModel(dtosGrupoFamiliar.getTablaGrupoFamiliar(true, 
												 ventanaCobrarInscripcion.txtTabla2.getText()));
		ventanaCobrarInscripcion.tabla2.getColumnModel().getColumn(2).setPreferredWidth(40);
		ventanaCobrarInscripcion.tabla2.getColumnModel().getColumn(2).setMaxWidth(50);
	}
	
	private void registraCobro() {
		
		boolean bandera = false;
		dtosCobros.setNombre(ventanaCobrarInscripcion.txtNombre.getText());
		dtosCobros.setEnviarEmail(ventanaCobrarInscripcion.chckbxEnviarEmail.isSelected());
		dtosCobros.setFactura(ventanaCobrarInscripcion.txtFactura.getText());
		
		if(ventanaCobrarInscripcion.chckbxEnviarEmail.isSelected())
			dtosCobros.setEmail(ventanaCobrarInscripcion.txtEmail.getText());
		else 
			dtosCobros.setEmail("");
		
		ventanaCobrarInscripcion.lblMsgError.setForeground(Color.BLUE);
		ventanaCobrarInscripcion.lblMsgError.setText("Procesando la operaci�n.");
		String error = dtosCobros.validarInformaci�n(!haySeleccion);
		
		if(error.length() == 0) {

			if(ventanaCobrarInscripcion.chckbxTabla2.isSelected()) {
				
				int i = 0;
			
				while(i < ventanaCobrarInscripcion.tabla2.getRowCount()) {
					
					if((boolean) ventanaCobrarInscripcion.tabla2.getValueAt(i, 2)) {
					
						dtosGrupoFamiliar.setElementoSeleccionado(i);
						break;
					}
					i++;
				}
				dtosGrupoFamiliar.setInformacionGrupo();
				dtosCobros.setIdFamilia(Integer.parseInt(dtosGrupoFamiliar.getIdGrupoFamiliar()));
				dtosCobros.SetIntegrantes(dtosGrupoFamiliar.getIntegrantes());	
				bandera = dtosCobros.guardarCobroGrupoExistente();
			} else {
				
				bandera = dtosCobros.guardarCobroGrupo();
			}
			
			if(bandera) {
				
				if(ventanaCobrarInscripcion.chckbxEnviarEmail.isSelected()) {
					
					EmailSenderService emailService = new EmailSenderService();
					emailService.mandarCorreo(dtosCobros.getEmail(), "Recibo de pago", dtosCobros.getCuerpoEmail());
				} else {
				
					ReciboCobro ventanaReciboPago = new ReciboCobro("Comprobante de pago");
					CtrolReciboCobrarInscripcion ctrolReciboInscripcion = new CtrolReciboCobrarInscripcion(ventanaReciboPago);
					ctrolReciboInscripcion.iniciar();
				}
				ventanaCobrarInscripcion.lblMsgError.setForeground(Color.BLUE);
				ventanaCobrarInscripcion.lblMsgError.setText("Operaci�n almacenada en la base de datos.");
				ventanaCobrarInscripcion.btnCobrar.setEnabled(false);
				dtosCobros.setBorrarSeleccionados();
			}else {
				
				ventanaCobrarInscripcion.lblMsgError.setForeground(Color.RED);
				ventanaCobrarInscripcion.lblMsgError.setText("Error al intentar guardar la informaci�n en la base de datos.");
			}
		} else {
			
			ventanaCobrarInscripcion.lblMsgError.setForeground(Color.RED);
			ventanaCobrarInscripcion.lblMsgError.setText(error);
		}
	}
}