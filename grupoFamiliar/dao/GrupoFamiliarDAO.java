package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;

import modelo.DtosActividad;
import modelo.DtosAlumno;
import modelo.DtosCobros;

public class GrupoFamiliarDAO extends Conexion {

	public String [][] getGruposFamilias(boolean sinDeuda, String campoBusqueda) {

		String matriz[][]=null;
		String armoWhere = "WHERE(grupoFamiliar.estado = 1 && deuda > 0 && nombreFamilia LIKE '" + campoBusqueda + "%') ";
		
		if(sinDeuda)
			
			armoWhere = "WHERE(grupoFamiliar.estado = 1 && deuda = 0 && nombreFamilia LIKE '" + campoBusqueda + "%') ";
		
		String comandoStatement = "SELECT grupoFamiliar.idGrupoFamiliar, grupoFamiliar.nombreFamilia, integrantes, deuda, SUM(precio), descuento " +
									"FROM lecsys1.alumnos " + 
									"JOIN lecsys1.grupoFamiliar ON alumnos.idGrupoFamiliar = grupoFamiliar.idGrupoFamiliar " + 
									"JOIN lecsys1.curso ON alumnos.idCurso = curso.idCurso " + 
									"JOIN lecsys1.valorCuota ON curso.idCurso = valorCuota.idCurso " + 
									armoWhere +
									"GROUP BY grupoFamiliar.idGrupoFamiliar " +
									"ORDER BY grupoFamiliar.nombreFamilia";
		
		try {
			
			this.conectar();
			Statement stm = this.conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stm.executeQuery(comandoStatement);
			rs.last();	
			matriz = new String[rs.getRow()][6];
			rs.beforeFirst();
			int i=0;

			while (rs.next()) {
					
				matriz[i][0] =rs.getInt(1) + "";
				matriz[i][1] = rs.getString(2);
				matriz[i][2] = rs.getInt(3) + "";
				matriz[i][3] = rs.getInt(4) + "";
				matriz[i][4] = rs.getInt(5) + "";
				matriz[i][5] = rs.getInt(6) + "";
				i++;
			}
		}catch (Exception e) {
			
			System.err.println("GrupoFamiliarDAO, getGruposFamilias()");
			System.err.println(e.getMessage());
			System.out.println(comandoStatement);
		} finally {
			
			this.cerrar();
		}
		return matriz;
	}
	
	public boolean setGrupoFamiliar() {
		
		boolean bandera = false;
		DtosCobros dtosNuevoGrupoFamilar = new DtosCobros();
		DtosActividad dtosActividad = new DtosActividad();
		String infoPersona[] = null;
					
		try {
			
			this.conectar();
			PreparedStatement stm = this.conexion.prepareStatement("INSERT INTO lecsys.grupoFamiliar (nombreFamilia, integrantes, deuda, estado, descuento) VALUES (?, ?, ?, ?)");
/*			stm.setInt(1, );
			stm.setInt(2, idPersona);
			stm.setString(3, fecha);
			stm.setInt(4, 0);
			*/
			stm.executeUpdate();
		} catch (Exception e) {
	
			System.err.println("AlumnosDAO, setAlumno()");
			System.err.println(e.getMessage());
			bandera = false;
		} finally {
			
			this.cerrar();
		}
//		dtosNuevoGrupoFamilar.limpiarInformacion();
		dtosActividad.registrarActividad("Registro nuevo grupo familiar.", "Administración");
		return bandera;
	}

	
	
}
