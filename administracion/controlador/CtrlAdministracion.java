package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import interfaceUsuario.InterfaceBotones;

public class CtrlAdministracion implements ActionListener {

	private InterfaceBotones ventanaAdministracion;
	
	public CtrlAdministracion(InterfaceBotones vista) {
		
		this.ventanaAdministracion = vista;
		this.ventanaAdministracion.btn1A.addActionListener(this);
		this.ventanaAdministracion.btn1B.addActionListener(this);
		this.ventanaAdministracion.btn1C.addActionListener(this);
		this.ventanaAdministracion.btn2A.addActionListener(this);
		this.ventanaAdministracion.btn2B.addActionListener(this);
		this.ventanaAdministracion.btn2C.addActionListener(this);
		this.ventanaAdministracion.btnVolver.addActionListener(this);
	}
	
	public void iniciar() {
		
		ventanaAdministracion.lbl1A.setText("Cobros");
		ventanaAdministracion.lbl1A.setVisible(true);
		ventanaAdministracion.btn1A.setIcon(new ImageIcon("C:\\LECSys 1.0\\Imagenes\\Cobros.png"));
		ventanaAdministracion.btn1A.setVisible(true);
		ventanaAdministracion.lbl1B.setText("Pagos");
		ventanaAdministracion.lbl1B.setVisible(true);
		ventanaAdministracion.btn1B.setIcon(new ImageIcon("C:\\LECSys 1.0\\Imagenes\\Pagos.png"));
		ventanaAdministracion.btn1B.setVisible(true);
		ventanaAdministracion.lbl1C.setText("Compras");
		ventanaAdministracion.lbl1C.setVisible(true);
		ventanaAdministracion.btn1C.setIcon(new ImageIcon("C:\\LECSys 1.0\\Imagenes\\Compras.png"));
		ventanaAdministracion.btn1C.setVisible(true);
		ventanaAdministracion.lbl2A.setText("EstadÍsticas");
		ventanaAdministracion.lbl2A.setVisible(true);
		ventanaAdministracion.btn2A.setIcon(new ImageIcon("C:\\LECSys 1.0\\Imagenes\\Estadisticas.png"));
		ventanaAdministracion.btn2A.setVisible(true);
		ventanaAdministracion.lbl2B.setText("Proveedores");
		ventanaAdministracion.lbl2B.setVisible(true);
		ventanaAdministracion.btn2B.setIcon(new ImageIcon("C:\\LECSys 1.0\\Imagenes\\Proveedores.png"));
		ventanaAdministracion.btn2B.setVisible(true);
		ventanaAdministracion.lbl2C.setText("Cerrar año");
		ventanaAdministracion.lbl2C.setVisible(true);
		ventanaAdministracion.btn2C.setIcon(new ImageIcon("C:\\LECSys 1.0\\Imagenes\\Cierre.png"));
		ventanaAdministracion.btn2C.setVisible(true);
		ventanaAdministracion.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == ventanaAdministracion.btn1A) {
			
			InterfaceBotones ventanaCobros = new InterfaceBotones("Gestión de cobros");
			CtrlCobros ctrlCobros = new CtrlCobros(ventanaCobros);
			ctrlCobros.iniciar();
		}
		
		if(e.getSource() == ventanaAdministracion.btn1B) {
			
			InterfaceBotones ventanaPagos = new InterfaceBotones("Gestión de pagos");
			CtrlPagos ctrlPagos = new CtrlPagos(ventanaPagos);
			ctrlPagos.iniciar();
		}

		if(e.getSource() == ventanaAdministracion.btn1C) {
			
			InterfaceBotones ventanaCompras = new InterfaceBotones("Gestión de compras");
			CtrlCompras ctrlCompras = new CtrlCompras(ventanaCompras);
			ctrlCompras.iniciar();
		}
		
		if(e.getSource() == ventanaAdministracion.btn2A) {
			
			InterfaceBotones ventanaEstadisticas = new InterfaceBotones("Estadísticas");
			CtrlEstadisticas ctrlEstadisticas = new CtrlEstadisticas(ventanaEstadisticas);
			ctrlEstadisticas.iniciar();
		}
		
		if(e.getSource() == ventanaAdministracion.btn2B) {
			
			InterfaceBotones ventanaProveedores = new InterfaceBotones("Gestión de proveedores");
			CtrlProveedores ctrlProveedores = new CtrlProveedores(ventanaProveedores);
			ctrlProveedores.iniciar();
		}
		
		if(e.getSource() == ventanaAdministracion.btn2C) {
			
			
		}
		
		if(e.getSource() == ventanaAdministracion.btnVolver) {
			
			ventanaAdministracion.dispose();
		}
	}
}
