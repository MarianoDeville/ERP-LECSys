package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import modelo.DtosActividad;

public class AlumnosDAO extends Conexion {

	public void setAlumno(String infoAlumno[]) {
		
		int idPersona = 0;
		DtosActividad dtosActividad = new DtosActividad();
		PeronasDAO dtosPersona = new PeronasDAO();
		Calendar fechaSistema = new GregorianCalendar();
		String fecha = fechaSistema.get(Calendar.YEAR) + "/" 
				 + (fechaSistema.get(Calendar.MONTH)+1) + "/" 
				 + fechaSistema.get(Calendar.DAY_OF_MONTH);
		
		idPersona = dtosPersona.registrarPersona(infoAlumno);
		
		try {
			
			this.conectar();
			PreparedStatement stm = this.conexion.prepareStatement("INSERT INTO alumnos (idCurso, idPersona, fechaIngreso, estado) VALUES (?, ?, ?, ?)");
			stm.setInt(1, Integer.parseInt(infoAlumno[7]));
			stm.setInt(2, idPersona);
			stm.setString(3, fecha);
			stm.setInt(4, 0);
			stm.executeUpdate();
		} catch (Exception e) {
			
			
			System.err.println(e.getMessage());
		} finally {
			
			this.cerrar();
		}
		dtosActividad.registrarActividad("Registro nuevo alumn.o", "Alumnos");
	}
	
	public String [][] getAlumnos(String campo, String valor, boolean estado, String orden) {
		
		String matriz[][]=null;
		String comandoStatement = "SELECT idAlumno, nombre, apellido, dni, dirección, teléfono, email, alumnos.idCurso, alumnos.estado, idGrupoFamiliar "
								+ "FROM lecsys1.alumnos "
				 				+ "JOIN lecsys1.persona on alumnos.idPersona = persona.idPersona "
				 				+ "JOIN lecsys1.curso ON curso.idCurso = alumnos.idCurso " 
						 		+ "JOIN lecsys1.empleados ON empleados.idEmpleado = curso.idProfesor " 
						 		+ "WHERE (alumnos.estado = " + (estado? "1":"0")
				 				+ ") ORDER BY " + orden;
		
		try {
			
			this.conectar();
			Statement stm = this.conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stm.executeQuery(comandoStatement);
			rs.last();	
			matriz = new String[rs.getRow()][10];
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
				matriz[i][7] = (rs.getInt(8) == 1)? "Activo":"Inactivo";
				matriz[i][8] = rs.getInt(9)+"";	
				matriz[i][9] = rs.getInt(10)+"";
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
