package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ActividadDAO extends Conexion {
	
	public void setActividad( int idUsuario, String fecha, String hora, String accion, String modulo, String miIP) {
		
		try {
			
			this.conectar();
			PreparedStatement stm = this.conexion.prepareStatement("INSERT INTO registroActividad (idUsuarios, fecha, hora, acci�n, modulo, ip) VALUES (?, ?, ?, ?, ?, ?)");
			stm.setString(1, (idUsuario !=0? idUsuario+"":null));
			stm.setString(2, fecha);
			stm.setString(3, hora);
			stm.setString(4, accion);
			stm.setString(5, modulo);
			stm.setString(6, miIP);
			stm.executeUpdate();
		} catch (Exception e) {
			
			System.err.println(e.getMessage());
		} finally {
			
			this.cerrar();
		}
	}
	
	public String [][] getActividad(String mes, String a�o) {
		
		String respuesta[][] = null;
		String armoStatement = "SELECT * FROM registroActividad WHERE MONTH(fecha)= " + mes + " AND YEAR(fecha)= " + a�o + " ORDER BY idRegistroActividad DESC";
		
		try {
			
			this.conectar();
			Statement stm = this.conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stm.executeQuery(armoStatement);
			rs.last();	
			respuesta = new String[rs.getRow()][7];
			rs.beforeFirst();
			int i=0;

			while (rs.next()) {
				
				respuesta[i][0] = rs.getInt(1) + "";
				respuesta[i][1] = rs.getInt(2) + "";
				respuesta[i][2] = rs.getString(3);
				respuesta[i][3] = rs.getString(4);
				respuesta[i][4] = rs.getString(5);
				respuesta[i][5] = rs.getString(6);
				respuesta[i][6] = rs.getString(7);
				i++;
			}
		}catch (Exception e) {
			
			System.err.println(e.getMessage());
		} finally {
			
			this.cerrar();
		}
		return respuesta;
	}
}
