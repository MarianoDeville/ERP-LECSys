package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;

import modelo.DtosActividad;
import modelo.DtosEmpleado;

public class EmpleadosDAO extends Conexion {
	
	public boolean setActualizarEmpleado() {
		
		boolean bandera = true;
		DtosActividad dtosActividad = new DtosActividad();
		PersonasDAO dtosPersona = new PersonasDAO();
		DtosEmpleado dtosEmpleado = new DtosEmpleado();
		String infoPersona[] = new String[] {dtosEmpleado.getNombre(), 
											 dtosEmpleado.getApellido(), 
											 dtosEmpleado.getDni(), 
											 dtosEmpleado.getDireccion(), 
											 dtosEmpleado.getFechaNacimientoAño() + "-" + dtosEmpleado.getFechaNacimientoMes() + "-" + dtosEmpleado.getFechaNacimientoDia(), 
											 dtosEmpleado.getTelefono(), 
											 dtosEmpleado.getEmail(), 
											 dtosEmpleado.getIdPersona()};
		bandera = dtosPersona.actualizarPersona(infoPersona);
		
		try {
			
			this.conectar();
			PreparedStatement stm = this.conexion.prepareStatement("UPDATE lecsys1.empleados SET sueldo = ?, estado = ?, "
																 + "sector = ?, cargo = ?, tipo = ? WHERE ( idEmpleado = ? )");
			stm.setInt(1, Integer.parseInt(dtosEmpleado.getSalario()));
			stm.setInt(2, dtosEmpleado.getEstado()?1:0);
			stm.setString(3, dtosEmpleado.getSector());
			stm.setString(4, dtosEmpleado.getCargo());
			stm.setString(5, dtosEmpleado.getRelacion());
			stm.setString(6, dtosEmpleado.getLegajo());
			stm.executeUpdate();
		} catch (Exception e) {
			
			bandera = false;
			System.err.println("EmpleadosDAO, setActualizarEmpleado()");
			System.err.println(e.getMessage());
		} finally {
			
			this.cerrar();
		}
		dtosEmpleado.limpiarInformacion();
		dtosActividad.registrarActividad("Edición de los datos de un empleado empleado", "Empleados");
		return bandera;
	}

	
	public boolean setEmpleado() {
		
		boolean bandera = true;
		DtosActividad dtosActividad = new DtosActividad();
		PersonasDAO dtosPersona = new PersonasDAO();
		DtosEmpleado dtosEmpleado = new DtosEmpleado();
		Calendar fechaSistema = new GregorianCalendar();
		String fechaActual = fechaSistema.get(Calendar.YEAR) + "/" 
				 + (fechaSistema.get(Calendar.MONTH)+1) + "/" 
				 + fechaSistema.get(Calendar.DAY_OF_MONTH);
		String infoPersona[] = new String[] {dtosEmpleado.getNombre(), dtosEmpleado.getApellido(), dtosEmpleado.getDni(),dtosEmpleado.getDireccion(), 
											dtosEmpleado.getFechaNacimientoAño()+"-"+dtosEmpleado.getFechaNacimientoMes()+"-"+dtosEmpleado.getFechaNacimientoDia(), 
											dtosEmpleado.getTelefono(), dtosEmpleado.getEmail()};
		int idPersona = dtosPersona.registrarPersona(infoPersona);
		
		try {
			
			this.conectar();
			PreparedStatement stm = this.conexion.prepareStatement("INSERT INTO empleados (idPersona, sueldo, fechaIngreso, estado, sector, cargo, tipo)"
																 + " VALUES (?, ?, ?, ?, ?, ?, ?)");
			stm.setInt(1, idPersona);
			stm.setInt(2, Integer.parseInt(dtosEmpleado.getSalario()));
			stm.setString(3, fechaActual);
			stm.setInt(4, 1);
			stm.setString(5, dtosEmpleado.getSector());
			stm.setString(6, dtosEmpleado.getCargo());
			stm.setString(7, dtosEmpleado.getRelacion());
			stm.executeUpdate();
		} catch (Exception e) {
			
			bandera = false;
			System.err.println("EmpleadosDAO, setEmpleado()");
			System.err.println(e.getMessage());
		} finally {
			
			this.cerrar();
		}
		dtosEmpleado.limpiarInformacion();
		dtosActividad.registrarActividad("Registro nuevo empleado", "Empleados");
		return bandera;
	}
	
	public String [][] getEmpleados(String tipo,boolean estado, String filtrado) {
		
		String matriz[][]=null;
		String where = null;
		
		if(tipo.equals("Todos")) {

			where = "WHERE (empleados.estado = " + (estado? "1 ":"0 ") + "AND apellido LIKE '" + filtrado + "%')";
		} else if(tipo.equals("ID")) {
			
			where = "WHERE empleados.idEmpleado = '" + filtrado + "'";
		} else {
			
			where = "WHERE (empleados.estado = " + (estado? "1":"0") + " AND sector = '" + tipo + "' AND apellido LIKE '" + filtrado + "%')";
		}
		
		String comandoStatement = "SELECT empleados.idEmpleado, persona.nombre, apellido, dni, dirección, teléfono, email, sector, "
								+ "cargo , sueldo, tipo, estado, fechaNacimiento, empleados.idPersona "
								+ "FROM lecsys1.empleados "
				 				+ "JOIN lecsys1.persona on empleados.idPersona = persona.idPersona "
				 				+ where
				 				+ "ORDER BY apellido, nombre";
		
		try {
			
			this.conectar();
			Statement stm = this.conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stm.executeQuery(comandoStatement);
			rs.last();	
			matriz = new String[rs.getRow()][14];
			rs.beforeFirst();
			int i=0;

			while (rs.next()) {
				
				matriz[i][0] = rs.getInt(1) + "";
				matriz[i][1] = rs.getString(2);
				matriz[i][2] = rs.getString(3);
				matriz[i][3] = rs.getString(4);
				matriz[i][4] = rs.getString(5);
				matriz[i][5] = rs.getString(6);
				matriz[i][6] = rs.getString(7);
				matriz[i][7] = rs.getString(8);
				matriz[i][8] = rs.getString(9);
				matriz[i][9] = rs.getInt(10) + "";
				matriz[i][10] = rs.getString(11);
				matriz[i][11] = (rs.getInt(12) == 1)? "Activo":"Inactivo";
				matriz[i][12] = rs.getString(13);
				matriz[i][13] = rs.getInt(14) + "";
				i++;
			}
		}catch (Exception e) {
			
			System.err.println("EmpleadosDAO, getEmpleados()");
			System.err.println(e.getMessage());
		} finally {
			
			this.cerrar();
		}
		return matriz;
	}

}
