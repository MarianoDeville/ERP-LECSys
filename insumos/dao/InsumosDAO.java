package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import controlador.CtrlLogErrores;
import modelo.DtosActividad;

public class InsumosDAO extends Conexion {

	
	
	public boolean setInsumo(String nombre, String descrip, String formato) {
		
		boolean bandera = true;
		DtosActividad dtosActividad = new DtosActividad();

		try {
			
			this.conectar();
			PreparedStatement stm = this.conexion.prepareStatement("INSERT INTO lecsys1.insumos (nombre, descripción, presentación)"
																 + " VALUES (?, ?, ?)");
			stm.setString(1, nombre);
			stm.setString(2, descrip);
			stm.setString(3, formato);
			stm.executeUpdate();
		} catch (Exception e) {
			
			bandera = false;
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("InsumosDAO, setInsumo()");
		} finally {
			
			this.cerrar();
		}
		dtosActividad.registrarActividad("Registrar nuevo insumo.", "Insumos.");
		return bandera;
	}
	
	public String [][] getListadoInsumos(String filtrado) {
		
		String matriz[][]=null;
		String comandoStatement = "SELECT idInsumos, nombre, descripción, presentación "
								+ "FROM lecsys1.insumos "
				 				+ "WHERE (nombre LIKE '%" + filtrado + "%' OR descripción LIKE '%" + filtrado + "%') "
				 				+ "ORDER BY nombre";
		
		try {
			
			this.conectar();
			Statement stm = this.conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stm.executeQuery(comandoStatement);
			rs.last();	
			matriz = new String[rs.getRow()][4];
			rs.beforeFirst();
			int i=0;

			while (rs.next()) {
				
				matriz[i][0] = rs.getString(1);
				matriz[i][1] = rs.getString(2);
				matriz[i][2] = rs.getString(3);
				matriz[i][3] = rs.getString(4);
				i++;
			}
		}catch (Exception e) {
			
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("InsumosDAO, getListadoInsumos()");
			CtrlLogErrores.guardarError(comandoStatement);
		} finally {
			
			this.cerrar();
		}
		return matriz;
	}
}
