package dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import interfaceUsuario.IngresoUsuario;

public class OperadorSistema extends Conexion {

	private static String nombreUsuario;
	private static int nivelAcceso;
	private static int idUsuarioActual;
	private static boolean actualizarContraseña;
		
	public String getNombreUsuario() {
		
		return nombreUsuario;
	}
	
	public int getNivelAcceso() {
		
		return nivelAcceso;
	}
	
	public int getFichaEmpleado() {
		
		return idUsuarioActual;
	}
	
	public boolean getActualizarContraseña() {
		
		return actualizarContraseña;
	}
	
	public boolean checkUsuario(IngresoUsuario ventanaLogin) {
		
		boolean bandera = false;
System.out.println(IngresoUsuario.txtPassword.getPassword());
System.out.println(IngresoUsuario.txtUsuario.getText());
		if(nombreUsuario == null) {
			
			nombreUsuario ="";
			nivelAcceso = 100;
			idUsuarioActual = 0;
			actualizarContraseña = false;
		}
		String armoStatement = "SELECT COUNT(*) FROM lecsys1.usuarios WHERE estado = 1";

		try {

			this.conectar();
			Statement stm = this.conexion.createStatement();
			ResultSet rs = stm.executeQuery(armoStatement);

			if(rs.next()) {
				
				int cant = rs.getInt(1);
				bandera = cant==0? true:false;
				nombreUsuario = "Sin usuario";
				nivelAcceso=0;
			}
		
			if(!bandera) {
				
				String palabraClave = Arrays.toString(IngresoUsuario.txtPassword.getPassword());		
				armoStatement = "SELECT idUsuarios, nombre, nivelAcceso FROM lecsys1.usuarios "
							  + "WHERE(estado = 1 AND nombre = '" + IngresoUsuario.txtUsuario.getText() + "' AND contraseña = SHA('" + palabraClave + "'))";
				rs = stm.executeQuery(armoStatement);
	
				if(rs.next()) {
					
					idUsuarioActual = rs.getInt(1);
					nombreUsuario = rs.getString(2);
					nivelAcceso = rs.getInt(3);
					bandera = true;
				}
			}
		} catch (Exception e) {
			
			System.err.println(e.getMessage());
		}finally {
			
			this.cerrar();
		}
		return bandera;
	}
}
