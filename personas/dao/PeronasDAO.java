package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PeronasDAO extends Conexion {
	
	public int registrarPersona(String infoPersona[]) {
		
		int registro = 0;
		
		try {
			
			this.conectar();
			PreparedStatement stm = this.conexion.prepareStatement("INSERT INTO persona (nombre, apellido, dni, direcci�n, fechaNacimiento, tel�fono, email)"
																 + " VALUES (?, ?, ?, ?, ?, ?, ?)");
			stm.setString(1, infoPersona[0]);
			stm.setString(2, infoPersona[1]);
			stm.setString(3, infoPersona[2]);
			stm.setString(4, infoPersona[3]);
			stm.setString(5, infoPersona[4]);
			stm.setString(6, infoPersona[5]);
			stm.setString(7, infoPersona[6]);
			stm.executeUpdate();

			ResultSet rs = stm.executeQuery("SELECT MAX(idPersona) FROM persona");
			
			if(rs.next())
				registro = rs.getInt(1);
			
		} catch (Exception e) {
			
			System.err.println(e.getMessage());
		} finally {
			
			this.cerrar();
		}
		
		return registro;
	}
	
	

}
