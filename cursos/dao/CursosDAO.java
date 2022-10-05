package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import modelo.DtosActividad;
import modelo.DtosCrearCurso;

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

	public boolean [][] getCronogramaDias(int idProfesor, int aula){

		boolean [][] matrizDiasHorarios = new boolean[6][28];
		String comandoStatement = "SELECT día, horario, duración "
								+ "FROM lecsys1.diasCursado "
								+ "JOIN lecsys1.curso ON diasCursado.idCurso = curso.idCurso "
								+ "JOIN lecsys1.empleados ON curso.idProfesor = empleados.idEmpleado "
								+ "WHERE (curso.estado = 1 AND (curso.aula = "
								+ aula 
								+ " OR curso.idProfesor = "
								+ idProfesor
								+ "))";
		
		for(int i = 0 ; i < 6 ; i++) {
			
			for(int e = 0 ; e < 28 ; e++) {
				
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
	
	public String [][] getListado() {
		
		String matriz[][] = null;
		
		String comandoStatement = "SELECT curso.idCurso, año, nivel, nombre, apellido, precio FROM lecsys1.curso "
				 + "JOIN lecsys1.empleados ON curso.idProfesor = empleados.idEmpleado "
				 + "JOIN lecsys1.persona ON empleados.idPersona = persona.idPersona "
				 + "JOIN lecsys1.valorCuota on curso.idCurso = valorCuota.idCurso "
				 + "WHERE curso.estado = 1 GROUP BY curso.idCurso";

		try {
			
			this.conectar();
			Statement stm = this.conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stm.executeQuery(comandoStatement);
			rs.last();	
			matriz = new String[rs.getRow()][6];
			rs.beforeFirst();
			int i=0;

			while (rs.next()) {
				
				matriz[i][4] = rs.getInt(1) + "";
				matriz[i][0] = rs.getString(2);
				matriz[i][1] = rs.getString(3);
				matriz[i][2] = rs.getString(4) + " " + rs.getString(5);
				matriz[i][3] = rs.getString(6);
				matriz[i][5] = rs.getInt(1) + "";
				i++;
			}
			
			String dia[] = new String[] {"Lunes","Martes","Miercoles","Jueves","Sábado"};
			i = 0;		
			
			while(i < matriz.length) {		
				
				comandoStatement = "SELECT día FROM lecsys1.diasCursado WHERE idCurso = " + Integer.valueOf(matriz[i][4]);
				
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
		DtosCrearCurso dtosCrearCurso = new DtosCrearCurso();

		try {
			
			this.conectar();
			PreparedStatement stm = this.conexion.prepareStatement("INSERT INTO lecsys1.curso (año, nivel, idProfesor, estado, aula)"
																 + " VALUES (?, ?, ?, 1, ?)");
			stm.setString(1, dtosCrearCurso.getAño());
			stm.setString(2, dtosCrearCurso.getNivel());
			stm.setString(3, dtosCrearCurso.getIdProfesor());
			stm.setInt(4, dtosCrearCurso.getAula());
			stm.executeUpdate();

			ResultSet rs = stm.executeQuery("SELECT MAX(idCurso) FROM lecsys1.curso");
			
			if(rs.next())
				idCurso = rs.getInt(1);
			
			stm = this.conexion.prepareStatement("INSERT INTO lecsys1.valorCuota (idCurso, precio) VALUES (?, ?)");
			stm.setInt(1, idCurso);
			stm.setString(2, dtosCrearCurso.getValorCuota());
			stm.executeUpdate();
			
			int diasCursado[][] = dtosCrearCurso.getHorarios();
			
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
}
