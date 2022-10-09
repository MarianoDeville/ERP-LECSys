package dao;

import java.sql.PreparedStatement;
import modelo.DtosActividad;
import modelo.DtosAlumno;

public class AsistenciaDAO extends Conexion {
	
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

}
