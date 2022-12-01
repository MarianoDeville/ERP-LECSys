package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import controlador.CtrlLogErrores;
import modelo.DtosActividad;
import modelo.DtosCobros;

public class AdministracionDAO extends Conexion {

	public boolean setCobro() {
		
		boolean bandera = true;
		DtosCobros dtosNuevoCobro = new DtosCobros();
		DtosActividad dtosActividad = new DtosActividad();
		
		try {
			
			this.conectar();
			PreparedStatement stm = this.conexion.prepareStatement("INSERT INTO lecsys1.cobros (idGrupoFamiliar, nombre, concepto, fecha, hora, monto, factura) "
																 + "VALUES (?, ?, ?, ?, ?, ?, ?)");
			stm.setInt(1, dtosNuevoCobro.getIdFamilia());
			stm.setString(2, dtosNuevoCobro.getNombre());
			stm.setString(3, dtosNuevoCobro.getDescripcion());
			stm.setString(4, dtosNuevoCobro.getFechaActual("A"));
			stm.setString(5, dtosNuevoCobro.getHoraActual());
			stm.setInt(6, dtosNuevoCobro.getMontoTotal());
			stm.setString(7, dtosNuevoCobro.getFactura());
			stm.executeUpdate();
		} catch (Exception e) {
	
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("AdministracionDAO, setCobro()");
			bandera = false;
		} finally {
			
			this.cerrar();
		}
		dtosActividad.registrarActividad("Registro cobro de inscripción.", "Administración");
		return bandera;
	}
	
	public int getUltimoRegistro() {
		
		int valor = 0;
		
		try {
			
			this.conectar();
			Statement stm = this.conexion.createStatement();
			ResultSet rs = stm.executeQuery("SELECT MAX(idCobros) FROM lecsys1.cobros");

			if(rs.next())
				valor = rs.getInt(1);

		}catch (Exception e) {
			
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("AdministracionDAO, getUltimoRegistro()");
		} finally {
			
			this.cerrar();
		}
		return valor;
	}

}
