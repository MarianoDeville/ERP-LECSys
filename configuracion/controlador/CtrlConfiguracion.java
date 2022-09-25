package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import interfaceUsuario.InterfaceBotones;
import interfaceUsuario.Listado;

public class CtrlConfiguracion implements ActionListener {

	private InterfaceBotones ventanaConfiguracion;

	public CtrlConfiguracion(InterfaceBotones vista) {
		
		this.ventanaConfiguracion = vista;
		this.ventanaConfiguracion.btn1A.addActionListener(this);
		this.ventanaConfiguracion.btnVolver.addActionListener(this);
	}
	
	public void iniciar() {
		
		ventanaConfiguracion.lbl1A.setText("Actividad");
		ventanaConfiguracion.lbl1A.setVisible(true);
		ventanaConfiguracion.btn1A.setIcon(new ImageIcon("C:\\LECSys 2.0\\Imagenes\\Actividades.png"));
		ventanaConfiguracion.btn1A.setVisible(true);
		ventanaConfiguracion.lbl1B.setText("ABML usuarios");
		ventanaConfiguracion.lbl1B.setVisible(true);
		ventanaConfiguracion.btn1B.setIcon(new ImageIcon("C:\\LECSys 2.0\\Imagenes\\ABML.png"));
		ventanaConfiguracion.btn1B.setVisible(true);
		ventanaConfiguracion.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ventanaConfiguracion.btn1A) {
			
			Listado ventanaActividad = new Listado("Listado de actividad");
			CtrlActividad ctrlActividad = new CtrlActividad(ventanaActividad);
			ctrlActividad.iniciar();
		}
		
		if(e.getSource() == ventanaConfiguracion.btn1B) {
			
			
		}

		if(e.getSource() == ventanaConfiguracion.btnVolver) {
			
			ventanaConfiguracion.dispose();
		}
	}
}
