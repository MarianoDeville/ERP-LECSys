package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import interfaceUsuario.InterfaceBotones;
import modelo.DtosConfiguracion;

public class CtrlAdministracion implements ActionListener {

	private InterfaceBotones ventana;
	
	public CtrlAdministracion(InterfaceBotones vista) {
		
		this.ventana = vista;
		this.ventana.btn1A.addActionListener(this);
		this.ventana.btn1B.addActionListener(this);
		this.ventana.btn1C.addActionListener(this);
		this.ventana.btn2A.addActionListener(this);
		this.ventana.btn2B.addActionListener(this);
		this.ventana.btn2C.addActionListener(this);
		this.ventana.btnVolver.addActionListener(this);
	}
	
	public void iniciar() {
		
		ventana.lbl1A.setText("Cobros");
		ventana.lbl1A.setVisible(true);
		ventana.btn1A.setIcon(new ImageIcon(DtosConfiguracion.getDirectorio() + "\\Imagenes\\Cobros.png"));
		ventana.btn1A.setVisible(true);
		ventana.lbl1B.setText("Pagos");
		ventana.lbl1B.setVisible(true);
		ventana.btn1B.setIcon(new ImageIcon(DtosConfiguracion.getDirectorio() + "\\Imagenes\\Pagos.png"));
		ventana.btn1B.setVisible(true);
		ventana.lbl1C.setText("Compras");
		ventana.lbl1C.setVisible(true);
		ventana.btn1C.setIcon(new ImageIcon(DtosConfiguracion.getDirectorio() + "\\Imagenes\\Compras.png"));
		ventana.btn1C.setVisible(true);
		ventana.lbl2A.setText("EstadÍsticas");
		ventana.lbl2A.setVisible(true);
		ventana.btn2A.setIcon(new ImageIcon(DtosConfiguracion.getDirectorio() + "\\Imagenes\\Estadisticas.png"));
		ventana.btn2A.setVisible(true);
		ventana.lbl2B.setText("Proveedores");
		ventana.lbl2B.setVisible(true);
		ventana.btn2B.setIcon(new ImageIcon(DtosConfiguracion.getDirectorio() + "\\Imagenes\\Proveedores.png"));
		ventana.btn2B.setVisible(true);
		ventana.lbl2C.setText("Cerrar año");
		ventana.lbl2C.setVisible(true);
		ventana.btn2C.setIcon(new ImageIcon(DtosConfiguracion.getDirectorio() + "\\Imagenes\\Cierre.png"));
		ventana.btn2C.setVisible(true);
		ventana.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == ventana.btn1A) {
			
			InterfaceBotones ventanaCobros = new InterfaceBotones("Gestión de cobros");
			CtrlCobros ctrlCobros = new CtrlCobros(ventanaCobros);
			ctrlCobros.iniciar();
		}
		
		if(e.getSource() == ventana.btn1B) {
			
			InterfaceBotones ventanaPagos = new InterfaceBotones("Gestión de pagos");
			CtrlPagos ctrlPagos = new CtrlPagos(ventanaPagos);
			ctrlPagos.iniciar();
		}

		if(e.getSource() == ventana.btn1C) {
			
			InterfaceBotones ventanaCompras = new InterfaceBotones("Gestión de compras");
			CtrlCompras ctrlCompras = new CtrlCompras(ventanaCompras);
			ctrlCompras.iniciar();
		}
		
		if(e.getSource() == ventana.btn2A) {
			
			InterfaceBotones ventanaEstadisticas = new InterfaceBotones("Estadísticas");
			CtrlEstadisticas ctrlEstadisticas = new CtrlEstadisticas(ventanaEstadisticas);
			ctrlEstadisticas.iniciar();
		}
		
		if(e.getSource() == ventana.btn2B) {
			
			InterfaceBotones ventanaProveedores = new InterfaceBotones("Gestión de proveedores");
			CtrlProveedores ctrlProveedores = new CtrlProveedores(ventanaProveedores);
			ctrlProveedores.iniciar();
		}
		
		if(e.getSource() == ventana.btn2C) {
			
			
		}
		
		if(e.getSource() == ventana.btnVolver) {
			
			ventana.dispose();
		}
	}
}
