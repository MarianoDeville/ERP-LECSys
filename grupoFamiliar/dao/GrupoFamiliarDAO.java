package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import controlador.CtrlLogErrores;
import modelo.DtosActividad;
import modelo.DtosCobros;

public class GrupoFamiliarDAO extends Conexion {

	public boolean setActualizarDeuda(int idGrupo, int modificarDeuda) {
		
		boolean bandera = true;
		int nuevaDeuda = 0;
		DtosActividad dtosActividad = new DtosActividad();
		String where = idGrupo == 0? "WHERE estado = '1'" : "WHERE idGrupoFamiliar = '" + idGrupo + "'";
		String comandoStatement = "SELECT deuda FROM lecsys1.grupoFamiliar " + where;
		
		try {
			
			this.conectar();
			PreparedStatement stm = this.conexion.prepareStatement(comandoStatement);
			ResultSet rs = stm.executeQuery();
			
			if(rs.next()) 
				nuevaDeuda = rs.getInt(1) + modificarDeuda;

			comandoStatement = "UPDATE lecsys1.grupoFamiliar SET deuda = ? " + where;
			stm = this.conexion.prepareStatement(comandoStatement);
			stm.setInt(1, nuevaDeuda);
			stm.executeUpdate();
		} catch (Exception e) {
	
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("GrupoFamiliarDAO, setActualizarDeuda()");
			CtrlLogErrores.guardarError(comandoStatement);
			bandera = false;
		} finally {
			
			this.cerrar();
		}
		dtosActividad.registrarActividad("Actualización de la deuda del grupo familiar.", "Adminnistración");
		return bandera;
	}
	
	public boolean setEliminarIntegrante(String idGrupo) {
		
		boolean bandera = true;
		DtosActividad dtosActividad = new DtosActividad();
		String comandoStatement = "SELECT integrantes FROM lecsys1.grupoFamiliar WHERE idGrupoFamiliar = ?";
		int cant = 0;

		try {
			
			this.conectar();
			PreparedStatement stm = this.conexion.prepareStatement(comandoStatement);
			stm.setString(1, idGrupo);			
			ResultSet rs = stm.executeQuery();
			
			if(rs.next())
				cant = rs.getInt(1) - 1;

			if(cant > 0) {
				
				comandoStatement = "UPDATE lecsys1.grupoFamiliar SET integrantes = ? WHERE idGrupoFamiliar = ?";
			} else {
				
				comandoStatement = "UPDATE lecsys1.grupoFamiliar SET integrantes = ?, estado = 0 WHERE idGrupoFamiliar = ?";
			}
			stm = this.conexion.prepareStatement(comandoStatement);
			stm.setInt(1, cant);
			stm.setString(2, idGrupo);
			stm.executeUpdate();
			
		} catch (Exception e) {
	
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("GrupoFamiliarDAO, setEliminarIntegrante()");
			CtrlLogErrores.guardarError(comandoStatement);
			bandera = false;
		} finally {
			
			this.cerrar();
		}
		dtosActividad.registrarActividad("Actualización de la cantidad de integrantes del grupo familiar.", "Alumnos");
		return bandera;
	}

	public boolean setActualizarGrupo(int idFamilia, String nombre, int integrantes, int descuentoGrupo, String email, String estado) {
		
		boolean bandera = true;
		DtosActividad dtosActividad = new DtosActividad();

		try {

			this.conectar();
			PreparedStatement stm = this.conexion.prepareStatement("UPDATE lecsys1.grupoFamiliar "
																 + "SET nombreFamilia = ?, integrantes = ?, estado = ?, descuento = ?, email = ? "
																 + "WHERE idGrupoFamiliar = ?");
			stm.setString(1, nombre);
			stm.setInt(2, integrantes);
			stm.setInt(3, Integer.parseInt(estado));
			stm.setInt(4, descuentoGrupo);
			stm.setString(5, email);
			stm.setInt(6, idFamilia);
			stm.executeUpdate();
		} catch (Exception e) {
	
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("GrupoFamiliarDAO, setActualizarGrupo()");
			bandera = false;
		} finally {
			
			this.cerrar();
		}
		dtosActividad.registrarActividad("Actualización datos grupo familiar.", "Administración");
		return bandera;
	}
	
	public String [][] getIntegrantes(String id) {
		
		String respuesta[][] = null;
		String comandoStatement = "SELECT alumnos.idAlumno, apellido, nombre, dirección, año, nivel, precio, descuento , grupoFamiliar.email, nombreFamilia " +
								  "FROM lecsys1.grupoFamiliar " + 
								  "JOIN lecsys1.alumnos ON alumnos.idGrupoFamiliar = grupoFamiliar.idGrupoFamiliar " +
								  "JOIN lecsys1.persona ON persona.idPersona = alumnos.idpersona " + 
								  "JOIN lecsys1.curso ON alumnos.idCurso = curso.idCurso " + 
								  "JOIN lecsys1.valorCuota ON curso.idCurso = valorCuota.idCurso " + 
								  "WHERE grupoFamiliar.idGrupoFamiliar = " + id;
				  
		try {
		
			this.conectar();
			Statement stm = this.conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stm.executeQuery(comandoStatement);
			rs.last();	
			respuesta = new String[rs.getRow()][9];
			rs.beforeFirst();
			int i=0;
			
			while (rs.next()) {
				
				respuesta[i][0] = rs.getString(1);
				respuesta[i][1] = rs.getString(2);
				respuesta[i][2] = rs.getString(3);
				respuesta[i][3] = rs.getString(4);
				respuesta[i][4] = rs.getString(5) + " " + rs.getString(6);
				respuesta[i][5] = rs.getString(7);
				respuesta[i][6] = rs.getString(8);
				respuesta[i][7] = rs.getString(9);
				respuesta[i][8] = rs.getString(10);
				i++;
			}
		}catch (Exception e) {
		
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("GrupoFamiliarDAO, getIntegrantes()");
			CtrlLogErrores.guardarError(comandoStatement);
		} finally {
		
			this.cerrar();
		}
		return respuesta;
	}

	public String [][] getGruposFamilias(String campo, String valor, boolean sinDeuda, String campoBusqueda) {

		String matriz[][]=null;
		String armoWhere = null;
		
		if(campo.equals("ID")) {
			
			armoWhere = "WHERE (grupoFamiliar.idGrupoFamiliar = " + valor + " AND deuda " + (sinDeuda? "= 0":"> 0") + ") ";
		} else if(campo.equals("IDALUMNO")) {
			
			armoWhere = "WHERE (idAlumno = " + valor + " AND deuda " + (sinDeuda? "= 0":"> 0") + ") ";
		} else if(campo.equals("ESTADO")) {
			
			armoWhere = "WHERE (grupoFamiliar.estado = " + valor + " AND deuda " + (sinDeuda? "= 0":"> 0") + ") ";
		} else {
		
			armoWhere = "WHERE (grupoFamiliar.estado = 1 AND deuda " + (sinDeuda? "= 0":"> 0") + " AND nombreFamilia LIKE '%" + campoBusqueda + "%') ";
		}
		
		String comandoStatement = "SELECT grupoFamiliar.idGrupoFamiliar, nombreFamilia, integrantes, deuda, SUM(precio), descuento , grupoFamiliar.email " +
								  "FROM lecsys1.grupoFamiliar " + 
								  "JOIN lecsys1.alumnos ON alumnos.idGrupoFamiliar = grupoFamiliar.idGrupoFamiliar " + 
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
			matriz = new String[rs.getRow()][7];
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
				i++;
			}
		}catch (Exception e) {
			
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("GrupoFamiliarDAO, getGruposFamilias()");
			CtrlLogErrores.guardarError(comandoStatement);
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
			
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("GrupoFamiliarDAO, setGrupoFamiliar()");
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
			
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("GrupoFamiliarDAO, isNombreFamilia()");
			CtrlLogErrores.guardarError(comandoStatement);
		} finally {
			
			this.cerrar();
		}
		return bandera;
	}
}