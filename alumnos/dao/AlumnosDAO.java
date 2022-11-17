package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import modelo.DtosActividad;
import modelo.DtosAlumno;

public class AlumnosDAO extends Conexion {

	public boolean setExamen() {
		
		boolean bandera = true;
		DtosAlumno dtosAlumno = new DtosAlumno();
		DtosActividad dtosActividad = new DtosActividad();
		String fecha = dtosAlumno.getFechaAño() + "-"
					 + dtosAlumno.getFechaMes() + "-"
					 + dtosAlumno.getFechaDia();
		
		try {
			
			this.conectar();
			PreparedStatement stm = this.conexion.prepareStatement("INSERT INTO lecsys1.examenes "
																+ "(idAlumno, fecha, tipo, nota, idProfesor, idCurso) "
																+ "VALUES (?, ?, ?, ?, ?, ?)");
			stm.setInt(1, Integer.parseInt(dtosAlumno.getLegajo()));
			stm.setString(2, fecha);
			stm.setString(3, dtosAlumno.getTipoExamen());
			stm.setInt(4, Integer.parseInt(dtosAlumno.getResultadoExamen()));
			stm.setInt(5, Integer.parseInt(dtosAlumno.getIdProfesor()));
			stm.setInt(6, Integer.parseInt(dtosAlumno.getCurso()));
			stm.executeUpdate();
		} catch (Exception e) {
	
			System.err.println("AlumnosDAO, setExamen()");
			System.err.println(e.getMessage());
			bandera = false;
		} finally {
			
			this.cerrar();
		}
		dtosActividad.registrarActividad("Carga de exámenes.", "Alumnos");
		return bandera;
	}
	
	public boolean setActualizarAlumno() {
		
		boolean bandera = true;
		DtosAlumno dtosNuevoAlumno = new DtosAlumno();
		DtosActividad dtosActividad = new DtosActividad();
		PeronasDAO dtosPersona = new PeronasDAO();
		String infoPersona[] = new String[] {dtosNuevoAlumno.getNombre(), 
											 dtosNuevoAlumno.getApellido(), 
											 dtosNuevoAlumno.getDni(),
											 dtosNuevoAlumno.getDireccion(), 
											 dtosNuevoAlumno.getFechaAño()+"-"+dtosNuevoAlumno.getFechaMes()+"-"+dtosNuevoAlumno.getFechaDia(), 
											 dtosNuevoAlumno.getTelefono(), 
											 dtosNuevoAlumno.getEmail(), 
											 dtosNuevoAlumno.getIdPersona()};
		bandera = dtosPersona.actualizarPersona(infoPersona);
		
		try {

			this.conectar();
			PreparedStatement stm = this.conexion.prepareStatement("UPDATE lecsys1.alumnos SET idCurso = ?, estado = ? WHERE (idAlumno = ?)");
			stm.setInt(1, Integer.parseInt(dtosNuevoAlumno.getCurso()));
			stm.setInt(2, dtosNuevoAlumno.getEstado()? 1:0);
			stm.setInt(3, Integer.parseInt(dtosNuevoAlumno.getLegajo()));
			stm.executeUpdate();
		} catch (Exception e) {
	
			System.err.println("AlumnosDAO, setActualizarAlumno()");
			System.err.println(e.getMessage());
			bandera = false;
		} finally {
			
			this.cerrar();
		}
		dtosNuevoAlumno.limpiarInformacion();
		dtosActividad.registrarActividad("Actualizacion de datos de alumno.", "Alumnos");
		return bandera;
	}
		
	public boolean setAlumno() {
		
		int idPersona = 0;
		boolean bandera = true;
		DtosAlumno dtosNuevoAlumno = new DtosAlumno();
		DtosActividad dtosActividad = new DtosActividad();
		PeronasDAO dtosPersona = new PeronasDAO();
		Calendar fechaSistema = new GregorianCalendar();
		String fecha = fechaSistema.get(Calendar.YEAR) + "/" 
					 + (fechaSistema.get(Calendar.MONTH)+1) + "/" 
					 + fechaSistema.get(Calendar.DAY_OF_MONTH);
		String infoPersona[] = new String[] {dtosNuevoAlumno.getNombre(), 
											 dtosNuevoAlumno.getApellido(), 
											 dtosNuevoAlumno.getDni(),
											 dtosNuevoAlumno.getDireccion(), 
											 dtosNuevoAlumno.getFechaAño()+"-"+dtosNuevoAlumno.getFechaMes()+"-"+dtosNuevoAlumno.getFechaDia(), 
											 dtosNuevoAlumno.getTelefono(), 
											 dtosNuevoAlumno.getEmail()};
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
	
			System.err.println("AlumnosDAO, setAlumno()");
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
		String armoWhere = null;
		
		if(campo.equals("ID")) {
			
			armoWhere = "WHERE (alumnos.estado = " + (estado? "1 ":"0 ") 
					  + " AND idAlumno = " + valor + ")";
		} else if(campo.equals("Docente")) {
		
			armoWhere = "WHERE (alumnos.estado = " + (estado? "1 ":"0 ") 
					  + " AND idProfesor = " + valor + ")";
		} else if(campo.equals("Curso")) {
			
			armoWhere = "WHERE (alumnos.estado = " + (estado? "1 ":"0 ") 
					  + " AND alumnos.idCurso = " + valor + ")";
		} else {
			
			armoWhere = "WHERE (alumnos.estado = " + (estado? "1 ":"0 ")
					  + "AND apellido LIKE '" + valor
					  + "%') ORDER BY " + orden;
		}
		
		String comandoStatement = "SELECT idAlumno, nombre, apellido, dni, dirección, teléfono, email, nivel, año"
								+ ", idGrupoFamiliar, alumnos.estado, alumnos.idCurso, alumnos.estado, fechaNacimiento , alumnos.idPersona, date_format(fechaIngreso, '%d/%m/%Y') "
								+ "FROM lecsys1.alumnos "
				 				+ "JOIN lecsys1.persona on alumnos.idPersona = persona.idPersona "
				 				+ "JOIN lecsys1.curso ON curso.idCurso = alumnos.idCurso " 
						 		+ armoWhere;
	
		try {
			
			this.conectar();
			Statement stm = this.conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stm.executeQuery(comandoStatement);
			rs.last();	
			matriz = new String[rs.getRow()][15];
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
				matriz[i][8] = rs.getInt(10) + "";
				matriz[i][9] = rs.getInt(11) + "";
				matriz[i][10] = rs.getInt(12) + "";
				matriz[i][11] = (rs.getInt(13) == 1)? "Activo": "Inactivo";
				matriz[i][12] = rs.getString(14);
				matriz[i][13] = rs.getString(15);
				matriz[i][14] = rs.getString(16);
				i++;
			}
		}catch (Exception e) {
			
			System.err.println("AlumnosDAO, getAlumnos()");
			System.err.println(e.getMessage());
		} finally {
			
			this.cerrar();
		}
		return matriz;
	}
	public boolean setAsistencia(int fila) {
		
		boolean bandera = true;
		DtosAlumno dtosAlumno = new DtosAlumno();
		DtosActividad dtosActividad = new DtosActividad();

		try {
			
			this.conectar();
			PreparedStatement stm = this.conexion.prepareStatement("INSERT INTO lecsys1.faltas (idAlumnos, fecha, estado, idCurso) VALUES (?, ?, ?, ?)");
			stm.setInt(1, dtosAlumno.getAsistencia("Legajo",fila));
			stm.setString(2, dtosAlumno.getFechaActual(false));
			stm.setInt(3, dtosAlumno.getAsistencia("Estado",fila));
			stm.setInt(4, Integer.parseInt(dtosAlumno.getCurso()));
			stm.executeUpdate();
		} catch (Exception e) {
	
			System.err.println("AsistenciaDAO, setAsistencia()");
			System.err.println(e.getMessage());
			bandera = false;
		} finally {
			
			this.cerrar();
		}
		dtosActividad.registrarActividad("Registro de asistencia.", "Alumnos");
		return bandera;
	}

	public String [][] tablaAsistenciasAlumnos(String idCurso, boolean reducido) {
		
		String matriz[][]=null;
		String comandoStatement = "SELECT * FROM lecsys1.faltas WHERE idCurso = " + idCurso; 
		
		if(reducido) {
			
			comandoStatement += " GROUP BY fecha";
		}
		
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
				matriz[i][1] = rs.getInt(2) + "";
				matriz[i][2] = rs.getString(3);
				matriz[i][3] = rs.getInt(4) + "";
				matriz[i][4] = rs.getInt(5) + "";
				i++;
			}
		}catch (Exception e) {
			
			System.err.println("AsistenciaDAO, tablaAsistenciasAlumnos()");
			System.err.println(e.getMessage());
		} finally {
			
			this.cerrar();
		}
		return matriz;
	}
}
