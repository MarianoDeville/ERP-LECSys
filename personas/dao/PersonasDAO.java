package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PersonasDAO extends Conexion {
	
	public boolean actualizarPersona(String infoPersona[]) {
		
		boolean bandera = true;
		
		try {
			
			this.conectar();
			PreparedStatement stm = this.conexion.prepareStatement("UPDATE lecsys1.persona SET "
																 + "nombre = ?, "
																 + "apellido = ?, "
																 + "dni = ?, "
																 + "direcci�n = ?, "
																 + "fechaNacimiento = ?, "
																 + "tel�fono = ?, "
																 + "email = ? WHERE (idPersona = ?)");
			stm.setString(1, infoPersona[0]);
			stm.setString(2, infoPersona[1]);
			stm.setString(3, infoPersona[2]);
			stm.setString(4, infoPersona[3]);
			stm.setString(5, infoPersona[4]);
			stm.setString(6, infoPersona[5]);
			stm.setString(7, infoPersona[6]);
			stm.setString(8, infoPersona[7]);
			stm.executeUpdate();
		} catch (Exception e) {
			
			bandera = false;
			System.err.println("PersonasDAO, actualizarPersona()");
			System.err.println(e.getMessage());
		} finally {
			
			this.cerrar();
		}
		return bandera;
	}
	
	public int registrarPersona(String infoPersona[]) {
		
		int registro = 0;
		
		try {
			
			this.conectar();
			PreparedStatement stm = this.conexion.prepareStatement("INSERT INTO persona ("
																 + "nombre, "
																 + "apellido, "
																 + "dni, "
																 + "direcci�n, "
																 + "fechaNacimiento, "
																 + "tel�fono, "
																 + "email)"
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
			
			System.err.println("PersonasDAO, registrarPersona()");
			System.err.println(e.getMessage());
		} finally {
			
			this.cerrar();
		}
		
		return registro;
	}
	
	public boolean getDNIDuplicado (String dni) {
		
		boolean bandera = false;
		String comandoStatement = "SELECT idPersona FROM lecsys1.persona WHERE dni = '" + dni + "'";
		
		try {
			
			this.conectar();
			Statement stm = this.conexion.createStatement();
			ResultSet rs = stm.executeQuery(comandoStatement);

			if(rs.next()) {
				
				bandera = true;
				
			}
		}catch (Exception e) {
			
			System.err.println("PersonasDAO, getDNIDuplicado()");
			System.err.println(e.getMessage());
		} finally {
			
			this.cerrar();
		}
		return bandera;
	}
	
	public String [][] getListadoCumpleA�os() {

		String respuesta[][] = null;

		try {
			
			this.conectar();
			Statement stm = this.conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stm.executeQuery("SELECT nombre, apellido FROM lecsys1.persona "
										  + "WHERE DAY(fechaNacimiento)=DAY(NOW()) AND MONTH(fechaNacimiento)=MONTH(NOW())");
			rs.last();
			
			if(rs.getRow() > 0) {
				
				respuesta = new String[rs.getRow()][2];
				rs.beforeFirst();
				int i=0;
	
				while (rs.next()) {
						
					respuesta[i][0] = rs.getString(1);	
					respuesta[i][1] = rs.getString(2);	
					i++;
				}
			}
		} catch (Exception e) {
			
			System.err.println("PersonasDAO, listadoCumpleA�os()");
			System.err.println(e.getMessage());
		} finally {
			
			this.cerrar();
		}
		return respuesta;
	}
}
