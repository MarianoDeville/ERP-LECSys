package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import interfaceUsuario.NuevoUsuario;
import modelo.DtosUsuarios;

public class CtrlEditarUsuario implements ActionListener {

	private NuevoUsuario ventanaEditarUsuario;
	private DtosUsuarios dtosUsuario;
	
	public CtrlEditarUsuario(NuevoUsuario vista) {
		
		this.ventanaEditarUsuario = vista;
		this.dtosUsuario = new DtosUsuarios();
		this.ventanaEditarUsuario.btnGuardar.addActionListener(this);
		this.ventanaEditarUsuario.btnBorrar.addActionListener(this);
		this.ventanaEditarUsuario.btnVolver.addActionListener(this);
	}
	
	public void iniciar(String usuario) {
		
		ventanaEditarUsuario.btnBorrar.setVisible(true);
		ventanaEditarUsuario.txtUsuario.setEditable(false);
		dtosUsuario.setUsuario(usuario);
		dtosUsuario.recuperarInformacionUsuario();
		ventanaEditarUsuario.txtUsuario.setText(dtosUsuario.getUsuario());
		ventanaEditarUsuario.comboBox1.setModel(new DefaultComboBoxModel<String>(dtosUsuario.getListaNivelAcceso()));
		ventanaEditarUsuario.comboBox1.setSelectedIndex(Integer.parseInt(dtosUsuario.getNivelAcceso()));
		ventanaEditarUsuario.comboBox2.setModel(new DefaultComboBoxModel<String>(new String [] {dtosUsuario.getNombre()}));
		ventanaEditarUsuario.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ventanaEditarUsuario.btnGuardar) {
			
			dtosUsuario.setContraseña(ventanaEditarUsuario.txtContraseña.getPassword());
			dtosUsuario.setReContraseña(ventanaEditarUsuario.txtReContraseña.getPassword());
			dtosUsuario.setNivelAcceso(ventanaEditarUsuario.comboBox1.getSelectedIndex());
			String msgError = dtosUsuario.checkInformacion(false);
			ventanaEditarUsuario.lblMsgError.setForeground(Color.RED);
			ventanaEditarUsuario.lblMsgError.setText(msgError);
			
			if(msgError.equals("")) {
				
				if(dtosUsuario.setActualizarUsuario()) {
					
					ventanaEditarUsuario.lblMsgError.setForeground(Color.BLUE);
					ventanaEditarUsuario.lblMsgError.setText("Los datos se actualizaron correctamente.");
				} else {
					
					ventanaEditarUsuario.lblMsgError.setForeground(Color.RED);
					ventanaEditarUsuario.lblMsgError.setText("Error al intentar guardar la información.");
				}				
			}
		}
		
		if(e.getSource() == ventanaEditarUsuario.btnBorrar) {
			
			dtosUsuario.setContraseña(null);
			dtosUsuario.setReContraseña(null);
			dtosUsuario.setNivelAcceso(ventanaEditarUsuario.comboBox1.getSelectedIndex());
			dtosUsuario.setEstado("0");
			
			if(dtosUsuario.setActualizarUsuario()) {
				
				ventanaEditarUsuario.lblMsgError.setForeground(Color.BLUE);
				ventanaEditarUsuario.lblMsgError.setText("Usuario eliminado.");
			} else {
				
				ventanaEditarUsuario.lblMsgError.setForeground(Color.RED);
				ventanaEditarUsuario.lblMsgError.setText("Error al intentar guardar la información.");
			}				
		}

		if(e.getSource() == ventanaEditarUsuario.btnVolver) {
			
			ventanaEditarUsuario.dispose();
		}
		
	}

}
