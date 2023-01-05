package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import controlador.CtrlLogErrores;
import modelo.DtosActividad;

public class CursosDAO extends Conexion {
	
	private boolean matrizDiasHorarios[][];
	
	public boolean [][] getmatrizDiasHorarios(){
		
		return matrizDiasHorarios;
	}
	
	public boolean getCronogramaDias(String idCurso, String idProfesor, int aula){

		boolean bandera = true;
		String where = null;
		matrizDiasHorarios = new boolean[6][33];
		
		if(idProfesor.equals("0")) {
			
			where = "WHERE (curso.estado = 1 AND curso.idCurso = " + idCurso + ")";
		} else {
			
			where = "WHERE (curso.estado = 1 "
					+ "AND (curso.aula = " + aula 
					+ " OR (curso.idProfesor = " + idProfesor 
					+ " AND curso.idCurso != " + idCurso + ")))";
		}

		String comandoStatement = "SELECT día, HOUR(hora), MINUTE(hora), duración "
								+ "FROM lecsys1.diasCursado "
								+ "JOIN lecsys1.curso ON diasCursado.idCurso = curso.idCurso "
								+ "JOIN lecsys1.empleados ON curso.idProfesor = empleados.idEmpleado "
								+ where;
		
		for(int i = 0 ; i < 6 ; i++) {
			
			for(int e = 0 ; e < 32 ; e++) {
				
				matrizDiasHorarios[i][e] = false;
			}
		}

		try {
			
			this.conectar();
			Statement stm = this.conexion.createStatement();
			ResultSet rs = stm.executeQuery(comandoStatement);
			
			while(rs.next()) {
				
				int dia = rs.getInt(1);
				int duracion = rs.getInt(4);
				int pos = (rs.getInt(2) - 7) * 2 + rs.getInt(3) / 30;
				
				while(0 < duracion--) {
					
					matrizDiasHorarios[dia][pos++] = true;
				}
			}
		} catch (Exception e) {
			
			bandera = false;
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("CursosDAO, getCronogramaDias()");
			CtrlLogErrores.guardarError(comandoStatement);
		} finally {
			
			this.cerrar();
		}
		return bandera;
	}
	
	public String [][] buscarDiasCurso(String idCurso) {
		
		String matriz[][] = null;
		String comandoStatement = "SELECT día, hora, duración FROM lecsys1.diasCursado WHERE idCurso = " + idCurso;
		
		try {
		
			this.conectar();
			Statement stm = this.conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stm.executeQuery(comandoStatement);
			rs.last();	
			matriz = new String[rs.getRow()][3];
			rs.beforeFirst();
			int i=0; 
			
			while(rs.next()) {
			
				matriz[i][0] = rs.getInt(1) + "";
				matriz[i][1] = rs.getInt(2) + "";
				matriz[i][2] = rs.getInt(3) + "";
				i++;
			}
		} catch (Exception e) {
		
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("CursosDAO, buscarDiasCurso()");
		} finally {
		
			this.cerrar();
		}
		return matriz;
	}
	
	public String [][] getListado(String idCurso) {
		
		String matriz[][] = null;
		String where = null;
		
		if(idCurso.equals(""))
			where = "WHERE curso.estado = 1 ";
		else
			where = "WHERE ( curso.estado = 1 AND curso.idCurso = " + idCurso + ")";

		String comandoStatement = "SELECT curso.idCurso, año, nivel, nombre, apellido, precio, curso.idProfesor, aula "
								+ "FROM lecsys1.curso "
								+ "JOIN lecsys1.empleados ON curso.idProfesor = empleados.idEmpleado "
								+ "JOIN lecsys1.persona ON empleados.idPersona = persona.idPersona "
								+ "JOIN lecsys1.valorCuota on curso.idCurso = valorCuota.idCurso "
								+ where
								+ "GROUP BY curso.idCurso";

		try {
			
			this.conectar();
			Statement stm = this.conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stm.executeQuery(comandoStatement);
			rs.last();	
			matriz = new String[rs.getRow()][9];
			rs.beforeFirst();
			int i=0;

			while (rs.next()) {
				
				matriz[i][0] = rs.getString(2);							// año
				matriz[i][1] = rs.getString(3);							// nivel
				matriz[i][2] = rs.getString(4) + " " + rs.getString(5);	// nombre y apellido del profesor
				matriz[i][3] = rs.getString(6);							// valor cuota
				matriz[i][5] = rs.getInt(1) + "";						// idcurso
				matriz[i][6] = rs.getInt(1) + "";						// idcurso
				matriz[i][7] = rs.getInt(7) + "";						// idProfesor
				matriz[i][8] = rs.getInt(8) + "";						// aula
				i++;
			}
			
			String dia[] = new String[] {"Lunes", "Martes", "Miercoles", "Jueves", "viernes", "Sábado"};
			i = 0;		
			
			while(i < matriz.length) {		
				
				comandoStatement = "SELECT día FROM lecsys1.diasCursado WHERE idCurso = " + matriz[i][6];
				rs = stm.executeQuery(comandoStatement);
				boolean bandera = true;
	
				while (rs.next()) {
					
					if(bandera) {
						
						matriz[i][4] = dia[rs.getInt(1)];
						bandera = false;
					} else {
						
						matriz[i][4] += ", " + dia[rs.getInt(1)];
					}
				}
				i++;
			}			
		} catch (Exception e) {
			
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("CursosDAO, getListado()");
			CtrlLogErrores.guardarError(comandoStatement);
		} finally {
			
			this.cerrar();
		}
		return matriz;
	}
	
	public boolean isExamenCargado(String idCurso, String examen) {

		boolean bandera = false;
		String comandoStatement = "SELECT curso.idCurso FROM lecsys1.curso "
								+ "JOIN lecsys1.examenes ON curso.idCurso = examenes.idCurso "
								+ "WHERE (estado = 1 AND curso.idCurso = " + idCurso + " AND tipo = '" + examen + "' AND YEAR(fecha)=YEAR(NOW())) "
								+ "GROUP BY curso.idCurso";

		try {
			
			this.conectar();
			Statement stm = this.conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stm.executeQuery(comandoStatement);

			if(rs.next())
				bandera =true;
		} catch (Exception e) {
			
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("CursosDAO, isExamenCargado()");
			CtrlLogErrores.guardarError(comandoStatement);
		} finally {
			
			this.cerrar();
		}
		return bandera;
	}
	
	public boolean setCurso(String año, String nivel, String idProfesor, int aula, String valorCuota, String horarios[][]) {
		
		boolean bandera = true;
		int idCurso = 0;
		DtosActividad dtosActividad = new DtosActividad();

		try {
			
			this.conectar();
			PreparedStatement stm = this.conexion.prepareStatement("INSERT INTO lecsys1.curso (año, nivel, idProfesor, estado, aula)"
																 + " VALUES (?, ?, ?, 1, ?)");
			stm.setString(1, año);
			stm.setString(2, nivel);
			stm.setString(3, idProfesor);
			stm.setInt(4, aula);
			stm.executeUpdate();
			ResultSet rs = stm.executeQuery("SELECT MAX(idCurso) FROM lecsys1.curso");
			
			if(rs.next())
				idCurso = rs.getInt(1);
			
			stm = this.conexion.prepareStatement("INSERT INTO lecsys1.valorCuota (idCurso, precio) VALUES (?, ?)");
			stm.setInt(1, idCurso);
			stm.setString(2, valorCuota);
			stm.executeUpdate();
			
			for(int i = 0 ; i < horarios.length ; i++) {
				
				stm = this.conexion.prepareStatement("INSERT INTO lecsys1.diasCursado (día, hora, duración, idCurso) VALUES (?, ?, ?, ?)");
				stm.setString(1, horarios[i][0]);
				stm.setString(2, horarios[i][1]);
				stm.setString(3, horarios[i][2]);
				stm.setInt(4, idCurso);
				stm.executeUpdate();				
			}
		} catch (Exception e) {
			
			bandera = false;
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("CursosDAO, setCurso()");
		} finally {
			
			this.cerrar();
		}
		dtosActividad.registrarActividad("Registrar nuevo curso.", "Cursos.");
		return bandera;
	}
	
	public boolean setActualizarCurso(String idCurso, String idProfesor, int aula, String valorCuota, int estado, String horarios[][]) {
		
		boolean bandera = true;
		DtosActividad dtosActividad = new DtosActividad();

		try {
			
			this.conectar();
			PreparedStatement stm = this.conexion.prepareStatement("UPDATE lecsys1.curso SET idProfesor = ?, estado = ?, aula = ? WHERE idCurso = ?");
			stm.setString(1, idProfesor);
			stm.setInt(2, estado);
			stm.setInt(3, aula);
			stm.setString(4, idCurso);
			stm.executeUpdate();
			stm = this.conexion.prepareStatement("UPDATE lecsys1.valorCuota SET precio = ? WHERE idCurso = ?");
			stm.setString(1, valorCuota);
			stm.setString(2, idCurso);
			stm.executeUpdate();
			stm = this.conexion.prepareStatement("DELETE FROM lecsys1.diasCursado WHERE idCurso = ?");
			stm.setString(1, idCurso);
			stm.executeUpdate();
			
			if(estado == 1) {
				
				for(int i = 0 ; i < horarios.length ; i++) {
					
					stm = this.conexion.prepareStatement("INSERT INTO lecsys1.diasCursado (día, hora, duración, idCurso) VALUES (?, ?, ?, ?)");
					stm.setString(1, horarios[i][0]);
					stm.setString(2, horarios[i][1]);
					stm.setString(3, horarios[i][2]);
					stm.setString(4, idCurso);
					stm.executeUpdate();				
				}
			}
		} catch (Exception e) {
			
			bandera = false;
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("CursosDAO, setActualizarCurso()");
		} finally {
			
			this.cerrar();
		}
		dtosActividad.registrarActividad("Editar un curso.", "Cursos.");
		return bandera;
	}
	
	public String getValorCuota(String idCurso) {
		
		String respuesta = null;
		String comandoStatement = "SELECT precio FROM lecsys1.curso "
								+ "JOIN lecsys1.valorCuota ON valorCuota.idCurso = curso.idCurso "
								+ "WHERE curso.idCurso = " + idCurso;
		
		try {
			
			this.conectar();
			Statement stm = this.conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stm.executeQuery(comandoStatement);

			if(rs.next()) {
				
				respuesta = rs.getString(1);
			}
		} catch (Exception e) {
			
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("CursosDAO, getValorCuota()");
			CtrlLogErrores.guardarError(comandoStatement);
		} finally {
			
			this.cerrar();
		}
		return respuesta;
	}
}
