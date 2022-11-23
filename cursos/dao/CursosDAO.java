package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import modelo.DtosActividad;
import modelo.DtosCurso;

public class CursosDAO extends Conexion {
	
	public String [][] buscarDiasCurso(String idCurso) {
		
		String matriz[][] = null;
		String comandoStatement = "SELECT día, horario, duración FROM lecsys1.diasCursado WHERE idCurso = " + idCurso;
		
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
		
			System.err.println(e.getMessage());
		} finally {
		
			this.cerrar();
		}
		return matriz;
	}

	public boolean [][] getCronogramaDias(int idCurso, int idProfesor, int aula){

		boolean [][] matrizDiasHorarios = new boolean[6][32];
		String where = null;
		
		if(idCurso == 0) {
			
			where = "WHERE (curso.estado = 1 AND (curso.aula = "
					+ aula 
					+ " OR curso.idProfesor = "
					+ idProfesor
					+ "))";
		} else {
			
			where = "WHERE (curso.estado = 1 AND curso.idCurso = "
					+ idCurso 
					+ ")";
		}
		
		String comandoStatement = "SELECT día, horario, duración "
								+ "FROM lecsys1.diasCursado "
								+ "JOIN lecsys1.curso ON diasCursado.idCurso = curso.idCurso "
								+ "JOIN lecsys1.empleados ON curso.idProfesor = empleados.idEmpleado "
								+ where;
		
		for(int i = 0 ; i < 6 ; i++) {
			
			for(int e = 0 ; e < 32 ; e++) {
				
				matrizDiasHorarios[i][e] = true;
			}
		}

		try {
			
			this.conectar();
			Statement stm = this.conexion.createStatement();
			ResultSet rs = stm.executeQuery(comandoStatement);
			
			while(rs.next()) {
				
				int dia = rs.getInt(1);
				int hora = rs.getInt(2);
				int duracion = rs.getInt(3);
				int f = 0;
				
				while(f < duracion) {
					
					matrizDiasHorarios[dia][hora + f] = false;
					f++;
				}
			}
			
		} catch (Exception e) {
			
			System.err.println(e.getMessage());
		} finally {
			
			this.cerrar();
		}
		return matrizDiasHorarios;
	}
	
	public String [][] getListado(String idCurso) {
		
		String matriz[][] = null;
		String where = null;
		
		if(idCurso.equals("")) {
		
			where = "WHERE curso.estado = 1 ";
		} else {
			
			where = "WHERE ( curso.estado = 1 AND curso.idCurso = " + idCurso + ")";
		}
		
		String comandoStatement = "SELECT curso.idCurso, año, nivel, nombre, apellido, precio, curso.idProfesor, aula FROM lecsys1.curso "
								 + "JOIN lecsys1.empleados ON curso.idProfesor = empleados.idEmpleado "
								 + "JOIN lecsys1.persona ON empleados.idPersona = persona.idPersona "
								 + "JOIN lecsys1.valorCuota on curso.idCurso = valorCuota.idCurso "
								 + where;
						//		 + "GROUP BY curso.idCurso";

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
			
			String dia[] = new String[] {"Lunes","Martes","Miercoles","Jueves","Sábado"};
			i = 0;		
			
			while(i < matriz.length) {		
				
				comandoStatement = "SELECT día FROM lecsys1.diasCursado WHERE idCurso = " + Integer.valueOf(matriz[i][6]);
				
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
		}catch (Exception e) {
			
			System.out.println(comandoStatement);
			System.err.println(e.getMessage());
		} finally {
			
			this.cerrar();
		}
		return matriz;
	}
	
	public boolean setCurso() {
		
		boolean bandera = true;
		int idCurso = 0;
		DtosActividad dtosActividad = new DtosActividad();
		DtosCurso dtosCurso = new DtosCurso();

		try {
			
			this.conectar();
			PreparedStatement stm = this.conexion.prepareStatement("INSERT INTO lecsys1.curso (año, nivel, idProfesor, estado, aula)"
																 + " VALUES (?, ?, ?, 1, ?)");
			stm.setString(1, dtosCurso.getAño());
			stm.setString(2, dtosCurso.getNivel());
			stm.setString(3, dtosCurso.getIdProfesor());
			stm.setInt(4, dtosCurso.getAula());
			stm.executeUpdate();

			ResultSet rs = stm.executeQuery("SELECT MAX(idCurso) FROM lecsys1.curso");
			
			if(rs.next())
				idCurso = rs.getInt(1);
			
			stm = this.conexion.prepareStatement("INSERT INTO lecsys1.valorCuota (idCurso, precio) VALUES (?, ?)");
			stm.setInt(1, idCurso);
			stm.setString(2, dtosCurso.getValorCuota());
			stm.executeUpdate();
			
			int diasCursado[][] = dtosCurso.getHorarios();
			
			for(int i = 0 ; i < diasCursado.length ; i++) {
				
				stm = this.conexion.prepareStatement("INSERT INTO lecsys1.diasCursado (día, horario, duración, idCurso) VALUES (?, ?, ?, ?)");
				stm.setInt(1, diasCursado[i][0]);
				stm.setInt(2, diasCursado[i][1]);
				stm.setInt(3, diasCursado[i][2]);
				stm.setInt(4, idCurso);
				stm.executeUpdate();				
			}

		} catch (Exception e) {
			
			bandera = false;
			System.err.println(e.getMessage());
		} finally {
			
			this.cerrar();
		}
		dtosActividad.registrarActividad("Registrar nuevo curso.", "Cursos.");
		return bandera;
	}
	
	public boolean setActualizarCurso() {
		
		boolean bandera = true;
		DtosActividad dtosActividad = new DtosActividad();
		DtosCurso dtosCurso = new DtosCurso();

		try {
			
			this.conectar();
			PreparedStatement stm = this.conexion.prepareStatement("UPDATE lecsys1.curso SET idProfesor = ?, estado = ?, aula = ? WHERE ( idCurso = ? )");
			stm.setInt(1, Integer.parseInt(dtosCurso.getIdProfesor()));
			stm.setInt(2, dtosCurso.getEstado());
			stm.setInt(3, dtosCurso.getAula());
			stm.setInt(4, Integer.parseInt(dtosCurso.getCurso()));
			stm.executeUpdate();
			
			stm = this.conexion.prepareStatement("UPDATE lecsys1.valorCuota SET precio = ? WHERE ( idCurso = ? )");
			stm.setInt(1, Integer.parseInt(dtosCurso.getValorCuota()));
			stm.setInt(2, Integer.parseInt(dtosCurso.getCurso()));
			stm.executeUpdate();
	
			stm = this.conexion.prepareStatement("DELETE FROM lecsys1.diasCursado WHERE ( idCurso = ? )");
			stm.setInt(1, Integer.parseInt(dtosCurso.getCurso()));
			stm.executeUpdate();
			
			if(dtosCurso.getEstado() == 1) {
				
				int diasCursado[][] = dtosCurso.getHorarios();
				
				for(int i = 0 ; i < diasCursado.length ; i++) {
					
					stm = this.conexion.prepareStatement("INSERT INTO lecsys1.diasCursado (día, horario, duración, idCurso) VALUES (?, ?, ?, ?)");
					stm.setInt(1, diasCursado[i][0]);
					stm.setInt(2, diasCursado[i][1]);
					stm.setInt(3, diasCursado[i][2]);
					stm.setInt(4, Integer.parseInt(dtosCurso.getCurso()));
					stm.executeUpdate();				
				}
			}

		} catch (Exception e) {
			
			bandera = false;
			System.err.println(e.getMessage());
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
			
		}catch (Exception e) {
			
			System.out.println(comandoStatement);
			System.err.println(e.getMessage());
		} finally {
			
			this.cerrar();
		}
		return respuesta;
	}
	
	
}
