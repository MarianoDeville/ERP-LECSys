package dao;

import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import modelo.DtosActividad;
import modelo.DtosCobros;

public class AdministracionDAO extends Conexion {

	public boolean setCobro() {
		
		boolean bandera = true;
		Calendar fechaSistema = new GregorianCalendar();
		String fecha = fechaSistema.get(Calendar.YEAR) + "/" 
					 + (fechaSistema.get(Calendar.MONTH)+1) + "/" 
					 + fechaSistema.get(Calendar.DAY_OF_MONTH);
	    String hora = (fechaSistema.get(Calendar.AM_PM)==0? fechaSistema.get(Calendar.HOUR):fechaSistema.get(Calendar.HOUR)+12) + ":" 
					+ (fechaSistema.get(Calendar.MINUTE)<10? "0" + fechaSistema.get(Calendar.MINUTE):fechaSistema.get(Calendar.MINUTE)) + ":" 
					+ (fechaSistema.get(Calendar.SECOND)<10? "0" + fechaSistema.get(Calendar.SECOND):fechaSistema.get(Calendar.SECOND));
		DtosCobros dtosNuevoCobro = new DtosCobros();
		DtosActividad dtosActividad = new DtosActividad();
		
		try {
			
			this.conectar();
			PreparedStatement stm = this.conexion.prepareStatement("INSERT INTO lecsys1.cobros (idGrupoFamiliar, nombre, concepto, fecha, hora, monto, factura) "
																 + "VALUES (?, ?, ?, ?, ?, ?, ?)");
			stm.setInt(1, dtosNuevoCobro.getIdFamilia());
			stm.setString(2, dtosNuevoCobro.getNombre());
			stm.setString(3, dtosNuevoCobro.getDescripcion());
			stm.setString(4, fecha);
			stm.setString(5, hora);
			stm.setInt(6, dtosNuevoCobro.getMontoTotal());
			stm.setString(7, dtosNuevoCobro.getFactura());
			stm.executeUpdate();
		} catch (Exception e) {
	
			System.err.println("AdministracionDAO, setCobro()");
			System.err.println(e.getMessage());
			bandera = false;
		} finally {
			
			this.cerrar();
		}
		dtosActividad.registrarActividad("Registro cobro de inscripción.", "Administración");
		return bandera;
	}
	
	
}
