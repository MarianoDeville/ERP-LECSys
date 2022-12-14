package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import controlador.CtrlLogErrores;
import modelo.DtosActividad;
import modelo.DtosAlumno;

public class AlumnosDAO extends Conexion {
	
	private int faltas;
	private int presente;
	private int tarde;
	
	public String getFaltas() {
		
		return faltas + "";
	}
	
	public String getPresente() {
		
		return presente + "";
	}
	
	public String getTarde() {
		
		return tarde + "";
	}
	
	public void getInfoAsistencia(String idAlumno) {
		
		String comando = "SELECT COUNT(*) FROM lecsys1.faltas WHERE (idAlumnos = " + idAlumno + " AND YEAR(fecha) = YEAR(NOW())";
		try {
			
			this.conectar();
			Statement stm = this.conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stm.executeQuery(comando + " AND estado = 0)");
			
			if (rs.next()) 
				faltas = rs.getInt(1);	

			rs = stm.executeQuery(comando + " AND estado = 1)");
			
			if (rs.next()) 
				presente = rs.getInt(1);
			
			rs = stm.executeQuery(comando + " AND estado = 2)");
			
			if (rs.next()) 
				tarde = rs.getInt(1);
			
		}catch (Exception e) {
			
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("AlumnosDAO, getInfoAsistencia()");
		} finally {
			
			this.cerrar();
		}
	}

	public String [][] getExamen(String idAlumno) {
		
		String matriz[][]=null;

		try {
			
			this.conectar();
			Statement stm = this.conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stm.executeQuery("SELECT fecha, tipo, nota FROM lecsys1.examenes WHERE idAlumno = " + idAlumno);
			rs.last();	
			matriz = new String[rs.getRow()][3];
			rs.beforeFirst();
			int i=0;

			while (rs.next()) {
					
				matriz[i][0] = rs.getString(1);	
				matriz[i][1] = rs.getString(2);	
				matriz[i][2] = rs.getString(3);	
				i++;
			}
		}catch (Exception e) {
			
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("AlumnosDAO, getExamen()");
		} finally {
			
			this.cerrar();
		}
		return matriz;	
	}

	public boolean setActualizarIdFamila(String idFamilia, String idAlumnos[], String estado) {
		
		boolean bandera = true;
		DtosActividad dtosActividad = new DtosActividad();
		
		try {

			this.conectar();
			PreparedStatement stm = this.conexion.prepareStatement("UPDATE lecsys1.alumnos "
																 + "SET estado = ?, idGrupoFamiliar = ? "
																 + "WHERE idAlumno = ?");
			stm.setString(1, estado);

			if(isN?mero(idFamilia))
				stm.setInt(2, Integer.parseInt(idFamilia));
			
			else
				stm.setString(2, idFamilia);
			
			for(int i = 0; i < idAlumnos.length; i++) {
				
				stm.setString(3, idAlumnos[i]);
				stm.executeUpdate();
			}
		} catch (Exception e) {
	
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("AlumnosDAO, setActualizarIdFamila()");
			bandera = false;
		} finally {
			
			this.cerrar();
		}
		dtosActividad.registrarActividad("Actualizaci?n del grupo familiar de un grupo de alumnos.", "Administraci?n");
		return bandera;
	}
	
	public boolean setExamen() {
		
		boolean bandera = true;
		DtosAlumno dtosAlumno = new DtosAlumno();
		DtosActividad dtosActividad = new DtosActividad();
		String fecha = dtosAlumno.getFechaA?o() + "-"
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
	
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("AlumnosDAO, setExamen()");
			bandera = false;
		} finally {
			
			this.cerrar();
		}
		dtosActividad.registrarActividad("Carga de ex?menes.", "Alumnos");
		return bandera;
	}
	
	public boolean setActualizarAlumno() {
		
		boolean bandera = true;
		DtosAlumno dtosNuevoAlumno = new DtosAlumno();
		DtosActividad dtosActividad = new DtosActividad();
		PersonasDAO dtosPersona = new PersonasDAO();
		String infoPersona[] = new String[] {dtosNuevoAlumno.getNombre(), 
											 dtosNuevoAlumno.getApellido(), 
											 dtosNuevoAlumno.getDni(),
											 dtosNuevoAlumno.getDireccion(), 
											 dtosNuevoAlumno.getFechaA?o()+"-"+dtosNuevoAlumno.getFechaMes()+"-"+dtosNuevoAlumno.getFechaDia(), 
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
	
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("AlumnosDAO, setActualizarAlumno()");
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
		PersonasDAO dtosPersona = new PersonasDAO();
		String infoPersona[] = new String[] {dtosNuevoAlumno.getNombre(), 
											 dtosNuevoAlumno.getApellido(), 
											 dtosNuevoAlumno.getDni(),
											 dtosNuevoAlumno.getDireccion(), 
											 dtosNuevoAlumno.getFechaA?o()+"-"+dtosNuevoAlumno.getFechaMes()+"-"+dtosNuevoAlumno.getFechaDia(), 
											 dtosNuevoAlumno.getTelefono(), 
											 dtosNuevoAlumno.getEmail()};
		idPersona = dtosPersona.registrarPersona(infoPersona);
		
		try {
			
			this.conectar();
			PreparedStatement stm = this.conexion.prepareStatement("INSERT INTO alumnos (idCurso, idPersona, fechaIngreso, estado) VALUES (?, ?, DATE(NOW()), ?)");
			stm.setInt(1, Integer.parseInt(dtosNuevoAlumno.getCurso()));
			stm.setInt(2, idPersona);
			stm.setInt(3, 0);
			stm.executeUpdate();
		} catch (Exception e) {
	
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("AlumnosDAO, setAlumno()");
			bandera = false;
		} finally {
			
			this.cerrar();
		}
		dtosNuevoAlumno.limpiarInformacion();
		dtosActividad.registrarActividad("Registro nuevo alumno.", "Alumnos");
		return bandera;
	}
	
	public String [][] getAlumnos(String campo, String valor, boolean estado, String orden, String busqueda) {
	
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
		} else if(campo.equals("GF")) {
			
			armoWhere = "WHERE (alumnos.estado = " + (estado? "1 ":"0 ") 
					  + " AND idGrupoFamiliar " + (valor.length()==0? "IS NULL":"= " + valor) 
					  + " AND (apellido LIKE '" + busqueda
					  + "%' OR nombre LIKE '" + busqueda
					  + "%')) ORDER BY " + orden;
		} else {
			
			armoWhere = "WHERE (alumnos.estado = " + (estado? "1 ":"0 ")
					  + "AND (apellido LIKE '" + busqueda
					  + "%' OR nombre LIKE '" + busqueda
					  + "%')) ORDER BY " + orden ;
		}
		
		String comandoStatement = "SELECT idAlumno, nombre, apellido, dni, direcci?n, tel?fono, email, nivel, a?o, "
								+ "idGrupoFamiliar, alumnos.estado, alumnos.idCurso, alumnos.estado, fechaNacimiento , "
								+ "alumnos.idPersona, date_format(fechaIngreso, '%d/%m/%Y'), a?o, nivel, precio "
								+ "FROM lecsys1.alumnos "
				 				+ "JOIN lecsys1.persona on alumnos.idPersona = persona.idPersona "
				 				+ "JOIN lecsys1.curso ON curso.idCurso = alumnos.idCurso " 
				 				+ "JOIN lecsys1.valorCuota ON curso.idCurso = valorCuota.idCurso "
						 		+ armoWhere;

		try {
			
			this.conectar();
			Statement stm = this.conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stm.executeQuery(comandoStatement);
			rs.last();	
			matriz = new String[rs.getRow()][17];
			rs.beforeFirst();
			int i=0;

			while (rs.next()) {
					
				matriz[i][0] = rs.getString(1);	
				matriz[i][1] = rs.getString(2);	
				matriz[i][2] = rs.getString(3);	
				matriz[i][3] = rs.getString(4);	
				matriz[i][4] = rs.getString(5);	
				matriz[i][5] = rs.getString(6);	
				matriz[i][6] = rs.getString(7);	
				matriz[i][7] = rs.getString(8) + " " + rs.getString(9);
				matriz[i][8] = rs.getString(10);
				matriz[i][9] = rs.getString(11);
				matriz[i][10] = rs.getString(12);
				matriz[i][11] = (rs.getInt(13) == 1)? "Activo": "Inactivo";
				matriz[i][12] = rs.getString(14);
				matriz[i][13] = rs.getString(15);
				matriz[i][14] = rs.getString(16);
				matriz[i][15] = rs.getString(17) + " " + rs.getString(18);
				matriz[i][16] = rs.getString(19);
				i++;
			}
		}catch (Exception e) {
			
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("AlumnosDAO, getAlumnos()");
			CtrlLogErrores.guardarError(comandoStatement);
		} finally {
			
			this.cerrar();
		}
		return matriz;
	}

	public String [][] getListadoAlumnos( boolean estado, String fuera, String busqueda) {
		
		String matriz[][]=null;
		String comandoStatement = "SELECT idAlumno, nombre, apellido, direcci?n, nivel, a?o, idGrupoFamiliar "
								+ "FROM lecsys1.alumnos "
				 				+ "JOIN lecsys1.persona on alumnos.idPersona = persona.idPersona "
				 				+ "JOIN lecsys1.curso ON curso.idCurso = alumnos.idCurso " 
							 	+ "WHERE (alumnos.estado = " + (estado? "1 ":"0 ") 
							 	+ "AND idGrupoFamiliar != " + fuera
								+ " AND (apellido LIKE '" + busqueda
								+ "%' OR nombre LIKE '" + busqueda
								+ "%')) ORDER BY idAlumno";

		try {
			
			this.conectar();
			Statement stm = this.conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stm.executeQuery(comandoStatement);
			rs.last();	
			matriz = new String[rs.getRow()][5];
			rs.beforeFirst();
			int i=0;

			while (rs.next()) {
					
				matriz[i][0] = rs.getString(1);	
				matriz[i][1] = rs.getString(3) + ", " + rs.getString(2);	
				matriz[i][2] = rs.getString(4);	
				matriz[i][3] = rs.getString(5) + " " + rs.getString(6);	
				matriz[i][4] = rs.getString(7);	
				i++;
			}
		}catch (Exception e) {
			
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("AlumnosDAO, getListadoAlumnos()");
			CtrlLogErrores.guardarError(comandoStatement);
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
	
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("AlumnosDAO, setAsistencia()");
			bandera = false;
		} finally {
			
			this.cerrar();
		}
		dtosActividad.registrarActividad("Registro de asistencia.", "Alumnos");
		return bandera;
	}

	public boolean isAsistenciaTomada(String idCurso, boolean reducido) {
		
		boolean bandera = false;
		String comandoStatement = "SELECT idCurso FROM lecsys1.faltas WHERE (idCurso = " + idCurso + " AND DATE(fecha) = CURDATE())"; 
		
		try {
			
			this.conectar();
			Statement stm = this.conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stm.executeQuery(comandoStatement);

			if(rs.next())
				bandera =true;

		}catch (Exception e) {
			
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("AlumnosDAO, isAsistenciaTomada()");
			CtrlLogErrores.guardarError(comandoStatement);
		} finally {
			
			this.cerrar();
		}
		return bandera;
	}

	public String [][] tablaAsistenciasAlumnos(String idCurso, boolean reducido, int mes) {
		
		String matriz[][]=null;
		String comandoStatement = "SELECT idFaltas, idAlumnos, DATE_FORMAT(fecha, '%d/%m/%Y'), estado, idCurso FROM lecsys1.faltas WHERE "; 
		
		if(mes == 0)
			comandoStatement += "idCurso = " + idCurso;
		else
			comandoStatement += "(idCurso = " + idCurso + " AND MONTH(fecha)= " + mes + ") ";
	
		if(reducido)
			comandoStatement += " GROUP BY fecha";

		comandoStatement += " ORDER BY fecha DESC";
		
		try {
			
			this.conectar();
			Statement stm = this.conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stm.executeQuery(comandoStatement);
			rs.last();	
			matriz = new String[rs.getRow()][5];
			rs.beforeFirst();
			int i=0;

			while (rs.next()) {
					
				matriz[i][0] = rs.getString(1);	
				matriz[i][1] = rs.getString(2);
				matriz[i][2] = rs.getString(3);
				matriz[i][3] = rs.getString(4);
				matriz[i][4] = rs.getString(5);
				i++;
			}
		}catch (Exception e) {
			
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("AlumnosDAO, tablaAsistenciasAlumnos()");
			CtrlLogErrores.guardarError(comandoStatement);
		} finally {
			
			this.cerrar();
		}
		return matriz;
	}
	
	private boolean isN?mero(String n?mero) {
		
		try {
			
			@SuppressWarnings("unused")
			int a = Integer.parseInt(n?mero);
		} catch (Exception e) {
			
			return false;
		}
		return true;
	}
}
