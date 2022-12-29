package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import controlador.CtrlLogErrores;
import modelo.DtosActividad;
import modelo.DtosEmpleado;

public class EmpleadosDAO extends Conexion {
	
	private boolean matrizDiasHorarios[][];
	private int granularidad;
	
	public int getGranularidad() {
		
		return granularidad;
	}

	public boolean setCronogramaDias(int idEmpleado, String horarios[][], int gran) {
		
		if(idEmpleado == 0)
			return false;
		
		boolean bandera = true;
		DtosActividad dtosActividad = new DtosActividad();

		try {
			
			this.conectar();
			PreparedStatement stm = this.conexion.prepareStatement("DELETE FROM lecsys1.horariosEmpleados WHERE idEmpleado = ?");
			stm.setInt(1, idEmpleado);
			stm.executeUpdate();

			for(int i = 0 ; i < horarios.length ; i++) {
				
				stm = this.conexion.prepareStatement("INSERT INTO lecsys1.horariosEmpleados (día, hora, duración, idEmpleado, granularidad) VALUES (?, ?, ?, ?, ?)");
				stm.setString(1, horarios[i][0]);
				stm.setString(2, horarios[i][1]);
				stm.setString(3, horarios[i][2]);
				stm.setInt(4, idEmpleado);
				stm.setInt(5, gran);
				stm.executeUpdate();				
			}
		} catch (Exception e) {
			
			bandera = false;
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("EmpleadosDAO, setCronogramaDias()");
		} finally {
			
			this.cerrar();
		}
		dtosActividad.registrarActividad("Registrar horarios de empleado.", "Empleados.");
		return bandera;
	}
	
	public boolean [][] getmatrizDiasHorarios(){
		
		return matrizDiasHorarios;
	}
	
	public boolean getCronogramaDias(int idEmpleado){

		if(idEmpleado == 0)
			return false;

		boolean bandera = true;
		String comandoStatement = "SELECT día, HOUR(hora), MINUTE(hora), duración, granularidad "
								+ "FROM lecsys1.horariosEmpleados "
								+ "WHERE idEmpleado = " + idEmpleado;

		try {

			this.conectar();
			Statement stm = this.conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stm.executeQuery(comandoStatement);

			if(rs.next())		
				granularidad = rs.getInt(5);
			else
				return false;
			
			rs.beforeFirst();
			int tiempo[] = new int[] {97, 65, 33, 17};
			int granulo[] = new int[] {6, 4, 2, 1};
			matrizDiasHorarios = new boolean[6][tiempo[granularidad]];
			
			for(int i = 0 ; i < 6 ; i++) {
				
				for(int e = 0 ; e < matrizDiasHorarios[0].length ; e++) {
					
					matrizDiasHorarios[i][e] = false;
				}
			}		

			while(rs.next()) {
				
				int dia = rs.getInt(1);
				int duracion = rs.getInt(4);
				int pos = (rs.getInt(2) - 7) * granulo[granularidad] + rs.getInt(3) / (60 / granulo[granularidad]);
				
				while(0 < duracion--) {
					
					matrizDiasHorarios[dia][pos++] = true;
				}
			}
		} catch (Exception e) {
			
			bandera = false;
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("EmpleadosDAO, getCronogramaDias()");
			CtrlLogErrores.guardarError(comandoStatement);
		} finally {
			
			this.cerrar();
		}
		return bandera;
	}

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
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("EmpleadosDAO, setActualizarEmpleado()");
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
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("EmpleadosDAO, setEmpleado()");
		} finally {
			
			this.cerrar();
		}
		dtosEmpleado.limpiarInformacion();
		dtosActividad.registrarActividad("Registro nuevo empleado", "Empleados");
		return bandera;
	}
	
	public String [][] getEmpleados(String tipo,boolean estado, String filtrado) {
		
		String matriz[][]=null;
		String where = " AND (apellido LIKE '" + filtrado + "%' OR nombre LIKE '" + filtrado + "%')) ";
		
		switch (tipo) {
		
			case "Todos":
				where = "WHERE (empleados.estado = " + (estado? "1 ":"0 ") + where;
				break;
				
			case "ID":
				where = "WHERE empleados.idEmpleado = '" + filtrado + "'";
				break;
			
			default:
				where = "WHERE (empleados.estado = " + (estado? "1":"0") + " AND sector = '" + tipo + "'" + where;

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
			
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("EmpleadosDAO, getEmpleados()");
			CtrlLogErrores.guardarError(comandoStatement);
		} finally {
			
			this.cerrar();
		}
		return matriz;
	}
}
