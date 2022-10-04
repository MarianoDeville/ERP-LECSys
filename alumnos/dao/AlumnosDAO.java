package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import modelo.DtosActividad;
import modelo.DtosNuevoAlumno;

public class AlumnosDAO extends Conexion {

	public boolean setAlumno() {
		
		int idPersona = 0;
		boolean bandera = true;
		DtosNuevoAlumno dtosNuevoAlumno = new DtosNuevoAlumno();
		DtosActividad dtosActividad = new DtosActividad();
		PeronasDAO dtosPersona = new PeronasDAO();
		Calendar fechaSistema = new GregorianCalendar();
		String fecha = fechaSistema.get(Calendar.YEAR) + "/" 
				 + (fechaSistema.get(Calendar.MONTH)+1) + "/" 
				 + fechaSistema.get(Calendar.DAY_OF_MONTH);
		String infoPersona[] = new String[] {dtosNuevoAlumno.getNombre(), dtosNuevoAlumno.getApellido(), dtosNuevoAlumno.getDni(),dtosNuevoAlumno.getDireccion(), 
											 dtosNuevoAlumno.getFechaNacimientoAño()+"-"+dtosNuevoAlumno.getFechaNacimientoMes()+"-"+dtosNuevoAlumno.getFechaNacimientoDia(), 
											 dtosNuevoAlumno.getTelefono(), dtosNuevoAlumno.getEmail()};
		idPersona = dtosPersona.registrarPersona(infoPersona);
		
		try {
			
			this.conectar();
			PreparedStatement stm = this.conexion.prepareStatement("INSERT INTO alumnos (idCurso, idPersona, fechaIngreso, estado) VALUES (?, ?, ?, ?)");
			stm.setInt(1, Integer.parseInt(dtosNuevoAlumno.getCurso()));
			stm.setInt(2, idPersona);
			stm.setString(3, fecha);
			stm.setInt(4, 0);
			stm.executeUpdate();
		} catch (Exception e) {
	
			System.err.println(e.getMessage());
			bandera = false;
		} finally {
			
			this.cerrar();
		}
		dtosNuevoAlumno.limpiarInformacion();
		dtosActividad.registrarActividad("Registro nuevo alumno.", "Alumnos");
		return bandera;
	}
	
	public String [][] getAlumnos(String campo, String valor, boolean estado, String orden) {
	
		String matriz[][]=null;
		String comandoStatement = "SELECT idAlumno, nombre, apellido, dni, dirección, teléfono, email, nivel, año, idGrupoFamiliar "
								+ "FROM lecsys1.alumnos "
				 				+ "JOIN lecsys1.persona on alumnos.idPersona = persona.idPersona "
				 				+ "JOIN lecsys1.curso ON curso.idCurso = alumnos.idCurso " 
						 		+ "WHERE (alumnos.estado = " + (estado? "1 ":"0 ")
						 		+ "AND apellido LIKE '" + valor
				 				+ "%') ORDER BY " + orden;
		
		try {
			
			this.conectar();
			Statement stm = this.conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stm.executeQuery(comandoStatement);
			rs.last();	
			matriz = new String[rs.getRow()][9];
			rs.beforeFirst();
			int i=0;

			while (rs.next()) {
					
				matriz[i][0] = rs.getInt(1)+"";	
				matriz[i][1] = rs.getString(2);	
				matriz[i][2] = rs.getString(3);	
				matriz[i][3] = rs.getString(4);	
				matriz[i][4] = rs.getString(5);	
				matriz[i][5] = rs.getString(6);	
				matriz[i][6] = rs.getString(7);	
				matriz[i][7] = rs.getString(8) + " " + rs.getString(9);
				matriz[i][8] = rs.getInt(10)+"";
				i++;
			}
		}catch (Exception e) {
			
			System.err.println(e.getMessage());
		} finally {
			
			this.cerrar();
		}
	
		return matriz;
	}
	
}
