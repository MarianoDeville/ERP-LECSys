package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import modelo.DtosActividad;
import modelo.DtosCambioPassword;

public class CambioPasswordDAO extends Conexion {
	
	private DtosCambioPassword dtosCambioPass;
	
	public boolean checkContrase�a() {
		
		boolean bandera = false;
		dtosCambioPass = new DtosCambioPassword();
		String armoStatement = "SELECT idUsuarios FROM lecsys1.usuarios "
							 + "WHERE(nombre = BINARY'" + dtosCambioPass.getNombreUsuario() + "' "
							 + "AND contrase�a = SHA('" + dtosCambioPass.getContrase�aOld() + "'))";
		
		try {

			this.conectar();
			Statement stm = this.conexion.createStatement();
			ResultSet rs = stm.executeQuery(armoStatement);
			rs = stm.executeQuery(armoStatement);
	
			if(rs.next()) {
					
				bandera = true;
			}
		} catch (Exception e) {
			
			System.err.println(e.getMessage());
		}finally {
			
			this.cerrar();
		}
		return bandera;
	}
	
	public boolean guardarNuevaContrase�a() {
		
		boolean bandera = true;
		dtosCambioPass  = new DtosCambioPassword();
		DtosActividad dtosActividad = new DtosActividad();
		
		try {
			
			this.conectar();
			PreparedStatement stm = this.conexion.prepareStatement("UPDATE lecsys1.usuarios SET contrase�a = SHA(?) WHERE nombre = ?");
			stm.setString(1, dtosCambioPass.getContrase�aNew());
			stm.setString(2, dtosCambioPass.getNombreUsuario());
			stm.executeUpdate();
			
		} catch (Exception e) {

			System.err.println(e.getMessage());
			bandera = false;
		} finally {
			
			this.cerrar();
		}
		dtosActividad.registrarActividad("Cambio peri�dico contrase�a.", "Principal");
		return bandera;
	}
}
