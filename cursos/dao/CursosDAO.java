package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.DtosActividad;
import modelo.DtosCrearCurso;

public class CursosDAO extends Conexion {
	
	public boolean setCurso() {
		
		boolean bandera = true;
		int idCurso = 0;
		DtosActividad dtosActividad = new DtosActividad();
		DtosCrearCurso dtosCrearCurso = new DtosCrearCurso();

		try {
			
			this.conectar();
			PreparedStatement stm = this.conexion.prepareStatement("INSERT INTO curso (año, nivel, idProfesor, estado, turno)"
																 + " VALUES (?, ?, ?, 1, ?)");
			stm.setString(1, dtosCrearCurso.getAño());
			stm.setString(2, dtosCrearCurso.getNivel());
			stm.setString(3, dtosCrearCurso.getIdProfesor());
			stm.setString(4, dtosCrearCurso.getTurno());
			stm.executeUpdate();

			ResultSet rs = stm.executeQuery("SELECT MAX(idPersona) FROM persona");
			
			if(rs.next())
				idCurso = rs.getInt(1);
			
			stm = this.conexion.prepareStatement("INSERT INTO valorCuota (idCurso, precio) VALUES (?, ?)");
			stm.setInt(1, idCurso);
			stm.setFloat(2, Float.parseFloat(dtosCrearCurso.getValorCuota()));
			stm.executeUpdate();
			
			String diasCursado[][] = dtosCrearCurso.getDiasCursado();
			
			for(int i = 0 ; i < diasCursado.length ; i++) {
				
				stm = this.conexion.prepareStatement("INSERT INTO diasCursado (día, horario, duración, idCurso) VALUES (?, ?, ?, ?)");
				stm.setString(1, diasCursado[i][0]);
				stm.setString(2, diasCursado[i][1]);
				stm.setString(3, diasCursado[i][2]);
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
