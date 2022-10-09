package modelo;

import java.util.Arrays;

import javax.swing.table.DefaultTableModel;
import dao.EmpleadosDAO;
import dao.UsuariosDAO;

public class DtosUsuarios {
	
	private static String usuario;
	private static String nombre;
	private static String contrase�a;
	private static String reContrase�a;
	private static String estado;
	private static String [] idEmpleados;
	private static String idEmpleado;
	private static String nivelAcceso;
	private static String [] nivelesAcceso;
	
	public void recuperarInformacionUsuario() {
		
		UsuariosDAO usuarioDAO = new UsuariosDAO();
		String respuesta[][] = usuarioDAO.getUsuarios(usuario);
		
		if(respuesta.length > 0) {
			
			nombre = respuesta[0][2];
			nivelAcceso = (Integer.parseInt(respuesta[0][3]) / 10) + "";
			idEmpleado = respuesta[0][4];
			estado = "1";
		}
	}
	
	public DefaultTableModel getTablaUsuarios() {
		
		nivelesAcceso = new String [] {"Acceso total","Administraci�n","Recepci�n","Docente"};
		String titulo[] = {"Usuario", "Pertenece a", "Nivel acceso", "Sel."};
		UsuariosDAO usuariosDAO = new UsuariosDAO();
		String respuesta[][] = usuariosDAO.getUsuarios("");
		Object cuerpo[][]=null;

		if(respuesta != null) {
			
			cuerpo = new Object[respuesta.length][9];
			
			for(int i = 0 ; i < respuesta.length ; i++) {
				
				cuerpo[i][0] = respuesta[i][1];
				cuerpo[i][1] = respuesta[i][2];
				cuerpo[i][2] = nivelesAcceso[Integer.parseInt(respuesta[i][3])/10];
				cuerpo[i][3] = false;
			}
		} else
			cuerpo = null;
		
		DefaultTableModel tablaModelo = new DefaultTableModel(cuerpo, titulo){

			private static final long serialVersionUID = 1L;
			
			boolean[] columnEditables = new boolean[] {
					false, false, false, true
			};
			
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
			
			public Class<?> getColumnClass(int column) {
				
		        if(column == 3)
		        	return Boolean.class;
		        else
		        	return String.class;
		    }
		};
		return tablaModelo;
	}
	public boolean setNuevoUsuario() {
		
		UsuariosDAO usuariosDAO = new UsuariosDAO();
		return usuariosDAO.setUsuario();
	}
	
	public boolean setActualizarUsuario() { 
		
		UsuariosDAO usuariosDAO = new UsuariosDAO();
		return usuariosDAO.setActualizarUsuario();
	} 
	
	public void limpiarInformacion() {
		
		usuario = "";
		contrase�a = "";
		reContrase�a = "";
		idEmpleado = "";
		nivelAcceso = "";
	}
	
	public String checkInformacion(boolean usuarioRepetido) {
		
		if(usuario.length() < 4) {

		return "El nombre debe tener m�s de tres caracteres.";
		}else if(contrase�a.length() < 5) {
			
			return "La contrase�a debe tener m�s de cuatro caracteres.";
		} else if(!contrase�a.equals(reContrase�a)) {
			
			return "Las contrase�as no coinciden.";
		}
		
		UsuariosDAO usuarioDAO = new UsuariosDAO();
		String [][] respuesta = usuarioDAO.getUsuarios(usuario);
		if(respuesta.length > 0 && usuarioRepetido) {
		
			return "Nombre de usuario en uso.";
		}
		return "";
	}
	
	public String [] getListaNivelAcceso() {
		
		return nivelesAcceso;
	}
	
	public String [] getListaEmpleados() {
		
		EmpleadosDAO empleadosDAO = new EmpleadosDAO();
		String matriz[][] = empleadosDAO.getEmpleados("Todos", true, "");
		String respuesta[] = new String[matriz.length];
		idEmpleados = new String[matriz.length];
		
		for(int i = 0 ; i < matriz.length ; i++) {
			
			idEmpleados[i] = matriz[i][0];
			respuesta[i] = matriz[i][1] + " " + matriz[i][2]; 
		}
		return respuesta;
	}
	
	public void setIdEmpleado(int indexIdEmpleados) {

		DtosUsuarios.idEmpleado = idEmpleados[indexIdEmpleados];
	}
	
	public void setNivelAcceso(int indexNivelAcceso) {
		
		DtosUsuarios.nivelAcceso = (indexNivelAcceso * 10) + "";
	}
	
	public String getUsuario() {
		
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		
		DtosUsuarios.usuario = usuario;
	}
	
	public String getContrase�a() {
		
		return contrase�a;
	}
	
	public void setContrase�a(char[] contrase�a) {
		
		DtosUsuarios.contrase�a = Arrays.toString(contrase�a);
	}
	
	public String [] getIdEmpleados() {
		
		return idEmpleados;
	}
	
	public void setIdEmpleados(String [] idEmpleados) {
		
		DtosUsuarios.idEmpleados = idEmpleados;
	}
	
	public String getReContrase�a() {
		
		return reContrase�a;
	}
	
	public void setReContrase�a(char[] reContrase�a) {

		DtosUsuarios.reContrase�a = Arrays.toString(reContrase�a);
	}
	
	public String getIdEmpleado() {
		
		return idEmpleado;
	}
	
	public String getNivelAcceso() {
		
		return nivelAcceso;
	}

	public String getNombre() {
		return nombre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		DtosUsuarios.estado = estado;
	}
}
