package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import modelo.DtosActividad;
import modelo.DtosUsuarios;

public class UsuariosDAO extends Conexion {
	
	public boolean setUsuario() {

		boolean bandera = true;
		DtosUsuarios dtosUsuario = new DtosUsuarios();
		DtosActividad dtosActividad = new DtosActividad();
		
		try {
			
			this.conectar();
			PreparedStatement stm = this.conexion.prepareStatement("INSERT INTO lecsys1.usuarios (nombre, contraseña, nivelAcceso, estado, idEmpleado) VALUES (?, ?, ?, ?, ?)");
			stm.setString(1, dtosUsuario.getUsuario());
			stm.setString(2, dtosUsuario.getContraseña());
			stm.setInt(3, Integer.parseInt(dtosUsuario.getNivelAcceso()));
			stm.setInt(4, 1);
			stm.setInt(5, Integer.parseInt(dtosUsuario.getIdEmpleado()));
			stm.executeUpdate();
		} catch (Exception e) {
	
			System.err.println(e.getMessage());
			bandera = false;
		} finally {
			
			this.cerrar();
		}
		dtosUsuario.limpiarInformacion();
		dtosActividad.registrarActividad("Registro nuevo usuario del sistema.", "Configuración");
		return bandera;
	}
	
	public String [][] getUsuarios(String usuario) {
		
		String matriz[][] = null;
		String where = null;
		
		if(usuario.contentEquals("")) {
			
			where = "WHERE usuarios.estado = 1";
		} else {
	
			where = "WHERE (usuarios.estado = 1 AND usuarios.nombre = '" + usuario + "')";
		}
		
		String comandoStatement = "SELECT usuarios.idUsuarios, usuarios.nombre, persona.nombre, persona.apellido, nivelAcceso, usuarios.idEmpleado "
								+ "FROM lecsys1.usuarios "
								+ "JOIN lecsys1.empleados on usuarios.idEmpleado = empleados.idEmpleado "
								+ "JOIN lecsys1.persona ON empleados.idPersona = persona.idPersona "
								+ where;
		
		try {
			
			this.conectar();
			Statement stm = this.conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stm.executeQuery(comandoStatement);
			rs.last();	
			matriz = new String[rs.getRow()][5];
			rs.beforeFirst();
			int i=0;

			while (rs.next()) {

				matriz[i][0] = rs.getInt(1) + "";	
				matriz[i][1] = rs.getString(2);	
				matriz[i][2] = rs.getString(3) + " " + rs.getString(4);	
				matriz[i][3] = rs.getInt(5) + "";
				matriz[i][4] = rs.getInt(6) + "";
				i++;
			}
		}catch (Exception e) {
			
			System.err.println(e.getMessage());
		} finally {
			
			this.cerrar();
		}
		return matriz;
	}
	
	public boolean setActualizarUsuario() {
		
		boolean bandera = true;
		DtosUsuarios dtosUsuarios = new DtosUsuarios();
		DtosActividad dtosActividad = new DtosActividad();
		
		try {
			
			this.conectar();
			PreparedStatement stm = this.conexion.prepareStatement("UPDATE lecsys1.usuarios SET contraseña = ?, estado = ?, nivelAcceso = ? WHERE ( nombre = ?)");
			stm.setString(1, dtosUsuarios.getContraseña());
			stm.setInt(2, Integer.parseInt(dtosUsuarios.getEstado()));
			stm.setInt(3, Integer.parseInt(dtosUsuarios.getNivelAcceso()));
			stm.setString(4, dtosUsuarios.getUsuario());
			stm.executeUpdate();
			
		} catch (Exception e) {

			System.err.println(e.getMessage());
			bandera = false;
		} finally {
			
			this.cerrar();
		}
		dtosActividad.registrarActividad("Actualizacion de datos de usuarios.", "Configuración");
		return bandera;
	}
}
