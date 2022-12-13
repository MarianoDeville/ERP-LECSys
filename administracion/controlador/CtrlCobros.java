package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import interfaceUsuario.InterfaceBotones;
import interfaceUsuario.Listado;
import modelo.DtosConfiguracion;

public class CtrlCobros implements ActionListener {
	
	private InterfaceBotones ventanaCobros;
	
	public CtrlCobros(InterfaceBotones vista) {
		
		this.ventanaCobros = vista;
		this.ventanaCobros.btn1A.addActionListener(this);
		this.ventanaCobros.btn1B.addActionListener(this);
		this.ventanaCobros.btn2A.addActionListener(this);
		this.ventanaCobros.btn2B.addActionListener(this);
		this.ventanaCobros.btnVolver.addActionListener(this);
	}
	
	public void iniciar() {
		
		ventanaCobros.lbl1A.setText("Cobrar cuota");
		ventanaCobros.lbl1A.setVisible(true);
		ventanaCobros.btn1A.setIcon(new ImageIcon(DtosConfiguracion.getDirectorio() + "\\Imagenes\\Cobrar.png"));
		ventanaCobros.btn1A.setVisible(true);
		ventanaCobros.lbl1B.setText("Listado cobros");
		ventanaCobros.lbl1B.setVisible(true);
		ventanaCobros.btn1B.setIcon(new ImageIcon(DtosConfiguracion.getDirectorio() + "\\Imagenes\\Listado cobros.png"));
		ventanaCobros.btn1B.setVisible(true);
		ventanaCobros.lbl2A.setText("Habilitar y cobrar");
		ventanaCobros.lbl2A.setVisible(true);
		ventanaCobros.btn2A.setIcon(new ImageIcon(DtosConfiguracion.getDirectorio() + "\\Imagenes\\Habilitar y cobrar.png"));
		ventanaCobros.btn2A.setVisible(true);
		ventanaCobros.lbl2B.setText("Grupo familiar");
		ventanaCobros.lbl2B.setVisible(true);
		ventanaCobros.btn2B.setIcon(new ImageIcon(DtosConfiguracion.getDirectorio() + "\\Imagenes\\Descuento.png"));
		ventanaCobros.btn2B.setVisible(true);		
		ventanaCobros.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == ventanaCobros.btn1A) {
			
			Listado ventanaCobrarCuota = new Listado("Cobro de cuota");
			CtrolCobrarCuota ctrlCobrarCuota = new CtrolCobrarCuota(ventanaCobrarCuota);
			ctrlCobrarCuota.iniciar();
		}
		
		if(e.getSource() == ventanaCobros.btn1B) {
			
			Listado ventanaListadoCobros = new Listado("Listado de cobros");
			CtrolListadoCobros ctrolListadoCobros = new CtrolListadoCobros(ventanaListadoCobros);
			ctrolListadoCobros.iniciar();
		}
		
		if(e.getSource() == ventanaCobros.btn2A) {
			
			Listado ventanaCobrarHabilitar = new Listado("Cobro y habilitación");
			CtrolCobrarHabilitar ctrolCobrarHabilitar = new CtrolCobrarHabilitar(ventanaCobrarHabilitar);
			ctrolCobrarHabilitar.iniciar();
		}
		
		if(e.getSource() == ventanaCobros.btn2B) {
			
			
		}
		
		if(e.getSource() == ventanaCobros.btnVolver) {
			
			ventanaCobros.dispose();
		}
	}
}
