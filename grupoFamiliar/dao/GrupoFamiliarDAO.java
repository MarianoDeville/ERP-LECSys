package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import modelo.DtosActividad;
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
		
		boolean bandera = true;
		DtosCobros dtosNuevoGrupoFamilar = new DtosCobros();
		DtosActividad dtosActividad = new DtosActividad();
		int id = 0;
		
		try {
			
			this.conectar();
			PreparedStatement stm = this.conexion.prepareStatement("INSERT INTO lecsys1.grupoFamiliar (nombreFamilia, integrantes, deuda, estado, descuento, email) "
																 + "VALUES (?, ?, 0, 1, ?, ?)");
			stm.setString(1, dtosNuevoGrupoFamilar.getNombre());
			stm.setInt(2, dtosNuevoGrupoFamilar.getCantidadElementosSeleccionados());
			stm.setInt(3, dtosNuevoGrupoFamilar.getDescuentoGrupo());
			stm.setString(4, dtosNuevoGrupoFamilar.getEmail());
			stm.executeUpdate();
			String listaIdAlumnos[] = dtosNuevoGrupoFamilar.getIdElementosSeleccionados();
			ResultSet rs = stm.executeQuery("SELECT MAX(idGrupoFamiliar) FROM lecsys1.grupoFamiliar");
			
			if(rs.next())
				id = rs.getInt(1);
			
			dtosNuevoGrupoFamilar.setIdFamilia(id);
			
			for(int i = 0 ; i < listaIdAlumnos.length ; i++) {
				
				stm = this.conexion.prepareStatement("UPDATE lecsys1.alumnos SET idGrupoFamiliar = ?, estado = 1 WHERE (idAlumno = ?)");
				stm.setInt(1, id);
				stm.setInt(2, Integer.parseInt(listaIdAlumnos[i]));
				stm.executeUpdate();
			}
			
		} catch (Exception e) {
	
			System.err.println("GrupoFamiliarDAO, setGrupoFamiliar()");
			System.err.println(e.getMessage());
			bandera = false;
		} finally {
			
			this.cerrar();
		}
		dtosActividad.registrarActividad("Registro nuevo grupo familiar.", "Administración");
		return bandera;
	}

	public boolean isNombreFamilia(String nombre) {
		
		boolean bandera = false;
		String comandoStatement = "SELECT idGrupoFamiliar FROM lecsys1.grupoFamiliar WHERE nombreFamilia = '" + nombre + "'";
		
		try {
			
			this.conectar();
			Statement stm = this.conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stm.executeQuery(comandoStatement);

			if(rs.next())
				bandera = true;
				
		}catch (Exception e) {
			
			System.err.println("GrupoFamiliarDAO, isNombreFamilia()");
			System.err.println(e.getMessage());
			System.out.println(comandoStatement);
		} finally {
			
			this.cerrar();
		}
		return bandera;
		
	}
	
}
