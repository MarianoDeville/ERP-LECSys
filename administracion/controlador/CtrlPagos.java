package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import interfaceUsuario.InterfaceBotones;
import modelo.DtosConfiguracion;

public class CtrlPagos implements ActionListener {

	private InterfaceBotones ventanaPagos;
	
	public CtrlPagos(InterfaceBotones vista) {
		
		this.ventanaPagos = vista;
		this.ventanaPagos.btn1A.addActionListener(this);
		this.ventanaPagos.btn1B.addActionListener(this);
		this.ventanaPagos.btn2A.addActionListener(this);
		this.ventanaPagos.btn2B.addActionListener(this);
		this.ventanaPagos.btnVolver.addActionListener(this);
	}
	
	public void iniciar() {
		
		ventanaPagos.lbl1A.setText("Pagar");
		ventanaPagos.lbl1A.setVisible(true);
		ventanaPagos.btn1A.setIcon(new ImageIcon(DtosConfiguracion.getDirectorio() + "\\Imagenes\\Pagos.png"));
		ventanaPagos.btn1A.setVisible(true);
		ventanaPagos.lbl1B.setText("Proveedores");
		ventanaPagos.lbl1B.setVisible(true);
		ventanaPagos.btn1B.setIcon(new ImageIcon(DtosConfiguracion.getDirectorio() + "\\Imagenes\\Proveedores.png"));
		ventanaPagos.btn1B.setVisible(true);
		ventanaPagos.lbl2A.setText("Empleados");
		ventanaPagos.lbl2A.setVisible(true);
		ventanaPagos.btn2A.setIcon(new ImageIcon(DtosConfiguracion.getDirectorio() + "\\Imagenes\\Empleados.png"));
		ventanaPagos.btn2A.setVisible(true);
		ventanaPagos.lbl2B.setText("Listado pagos");
		ventanaPagos.lbl2B.setVisible(true);
		ventanaPagos.btn2B.setIcon(new ImageIcon(DtosConfiguracion.getDirectorio() + "\\Imagenes\\Listado cobros.png"));
		ventanaPagos.btn2B.setVisible(true);
		ventanaPagos.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == ventanaPagos.btn1A) {

		}
		
		if(e.getSource() == ventanaPagos.btn1B) {
			
			
		}
		
		if(e.getSource() == ventanaPagos.btn2A) {
			
			
		}
		
		if(e.getSource() == ventanaPagos.btn2B) {
			
			
		}
		
		if(e.getSource() == ventanaPagos.btnVolver) {
			
			ventanaPagos.dispose();
		}
	}
}
