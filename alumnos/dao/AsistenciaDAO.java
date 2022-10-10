package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
