package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import controlador.CtrlLogErrores;

public class EstadisticasDAO extends Conexion {

	public boolean isNuevoMes() {

		int ultimoMesCargado = 0;

		try {
			
			this.conectar();
			Statement stm = this.conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stm.executeQuery("SELECT mes FROM lecsys1.estadísticas WHERE (año = YEAR(NOW()) AND mes = MONTH(NOW()))");
					
			if (rs.next())
				ultimoMesCargado = rs.getInt(1);

			if(ultimoMesCargado > 0) {
				
				this.cerrar();
				return false;
			}
			int estudiantesActivos = 0;
			int empleados = 0;
			int sueldos = 0;
			int ingresosMes = 0;
			int compras = 0;
			int servicios = 0;
			int faltasEmpleados = 0;
			int faltasAlumnos = 0;

			rs = stm.executeQuery("SELECT COUNT(*) FROM lecsys1.alumnos  WHERE estado = 1");
			
			if (rs.next())
				estudiantesActivos = rs.getInt(1);

			rs = stm.executeQuery("SELECT COUNT(*) FROM lecsys1.empleados  WHERE (estado = 1 AND cargo = 'Profesor')");
			
			if (rs.next())
				empleados = rs.getInt(1);

			rs = stm.executeQuery("SELECT SUM(monto) FROM lecsys1.cobros  WHERE MONTH(fecha) = MONTH(NOW())");
			
			if (rs.next())
				ingresosMes = rs.getInt(1);
			
			rs = stm.executeQuery("SELECT SUM(monto) FROM lecsys1.pagos  WHERE MONTH(fecha) = MONTH(NOW())");
			
			if (rs.next())
				sueldos = rs.getInt(1);
	
			PreparedStatement pstm  = this.conexion.prepareStatement("INSERT INTO lecsys1.estadísticas (mes, año, estudiantesActivos, empleados, "
																   + "ingresosMes, sueldos, compras, servicios, faltasEmpleados, faltasAlumnos) "
																   + "VALUES (MONTH(NOW()), YEAR(NOW()), ?, ?, ?, ?, ?, ?, ?, ?)");
			pstm.setInt(1, estudiantesActivos);
			pstm.setInt(2, empleados);
			pstm.setInt(3, ingresosMes);
			pstm.setInt(4, sueldos);
			pstm.setInt(5, compras);
			pstm.setInt(6, servicios);
			pstm.setInt(7, faltasEmpleados);
			pstm.setInt(8, faltasAlumnos);
			pstm.executeUpdate();
			
			int ultimoID = 0;
			rs = stm.executeQuery("SELECT idEstadísticas FROM lecsys1.estadísticas ORDER BY idEstadísticas DESC LIMIT 1");
			
			if (rs.next())
				ultimoID = rs.getInt(1);
			
			if(ultimoID > 0) {
				
				pstm  = this.conexion.prepareStatement("UPDATE lecsys1.cobros SET idEstadisticas = ? WHERE idEstadisticas IS NULL");
				pstm.setInt(1, ultimoID);	
				pstm.executeUpdate();
			}
		} catch (Exception e) {
		
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("EstadisticasDAO, isNuevoMes()");
		} finally {
			
			this.cerrar();
		}
		return true;
	}

}
