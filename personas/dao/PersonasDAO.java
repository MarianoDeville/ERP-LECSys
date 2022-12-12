package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import controlador.CtrlLogErrores;

public class PersonasDAO extends Conexion {
	
	public boolean actualizarPersona(String infoPersona[]) {
		
		boolean bandera = true;
		
		try {
			
			this.conectar();
			PreparedStatement stm = this.conexion.prepareStatement("UPDATE lecsys1.persona SET "
																 + "nombre = ?, "
																 + "apellido = ?, "
																 + "dni = ?, "
																 + "dirección = ?, "
																 + "fechaNacimiento = ?, "
																 + "teléfono = ?, "
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
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("PersonasDAO, actualizarPersona()");
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
																 + "dirección, "
																 + "fechaNacimiento, "
																 + "teléfono, "
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
			
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("PersonasDAO, registrarPersona()");
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
			
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("PersonasDAO, getDNIDuplicado()");
		} finally {
			
			this.cerrar();
		}
		return bandera;
	}
	
	public String [][] getListadoCumpleAños() {

		String respuesta[][] = null;

		try {
			
			this.conectar();
			Statement stm = this.conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stm.executeQuery("SELECT nombre, apellido FROM lecsys1.persona "
										  + "JOIN lecsys1.alumnos ON alumnos.idPersona = persona.idPersona "
										  + "JOIN lecsys1.empleados ON empleados.idPersona = empleados.idPersona "
										  + "WHERE (DAY(fechaNacimiento) = DAY(NOW()) AND MONTH(fechaNacimiento) = MONTH(NOW()) "
										  + "AND alumnos.estado = 1 AND empleados.estado = 1) "
										  + "GROUP BY persona.idPersona");
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
			
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("PersonasDAO, listadoCumpleAños()");
		} finally {
			
			this.cerrar();
		}
		return respuesta;
	}
}
