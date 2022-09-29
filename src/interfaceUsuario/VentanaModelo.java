package interfaceUsuario;

import javax.swing.JFrame;

import dao.OperadorSistema;

import java.awt.Dimension;
import java.awt.Toolkit;

public abstract class VentanaModelo extends JFrame {

	private static final long serialVersionUID = 1L;

	public VentanaModelo(String nombreVentana) {
		
		OperadorSistema identificaci�n = new OperadorSistema();
		setTitle(nombreVentana + " - " + identificaci�n.getNombreUsuario());
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\LECSys 2.0\\Imagenes\\LEC.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(10, 10, 800, 600);
		setMinimumSize(new Dimension(640, 480));
	}
}
