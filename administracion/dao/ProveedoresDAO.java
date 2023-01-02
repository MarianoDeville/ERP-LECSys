package dao;

import java.sql.ResultSet;
import java.sql.Statement;
import controlador.CtrlLogErrores;

public class ProveedoresDAO extends Conexion {

	
	
	public String [][] getListadoProveedores(String filtrado) {
		
		String matriz[][]=null;
		String comandoStatement = "SELECT nombre, cuit, direccion, tipo, idProveedores "
								+ "FROM lecsys1.proveedores "
				 				+ "WHERE (estado = 1 AND nombre LIKE '%" + filtrado + "%') "
				 				+ "ORDER BY nombre";
		
		try {
			
			this.conectar();
			Statement stm = this.conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stm.executeQuery(comandoStatement);
			rs.last();	
			matriz = new String[rs.getRow()][6];
			rs.beforeFirst();
			int i=0;

			while (rs.next()) {
				
				matriz[i][0] = rs.getString(1);
				matriz[i][1] = rs.getString(2);
				matriz[i][2] = rs.getString(3);

				i++;
			}
		}catch (Exception e) {
			
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("ProveedoresDAO, getListadoProveedores()");
			CtrlLogErrores.guardarError(comandoStatement);
		} finally {
			
			this.cerrar();
		}
		return matriz;
	}
}
