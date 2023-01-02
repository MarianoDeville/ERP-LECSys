package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;
import controlador.CtrlLogErrores;
import modelo.DtosActividad;

public class ProveedoresDAO extends Conexion {

	public boolean setProveedorNuevo(String nombre, String dirección, String cuit, String sitFiscal, JTable contactos) {
		
		boolean bandera = true;
		int idProveedor = 0;
		DtosActividad dtosActividad = new DtosActividad();

		try {
			
			this.conectar();
			PreparedStatement stm = this.conexion.prepareStatement("INSERT INTO lecsys1.proveedores (nombre, direccion, cuit, tipo, estado)"
																 + " VALUES (?, ?, ?, ?, 1)");
			stm.setString(1, nombre);
			stm.setString(2, dirección);
			stm.setString(3, cuit);
			stm.setString(4, sitFiscal);
			stm.executeUpdate();
			ResultSet rs = stm.executeQuery("SELECT MAX(idProveedores) FROM lecsys1.proveedores");
			
			if(rs.next())
				idProveedor = rs.getInt(1);

			for(int i = 0 ; i < contactos.getRowCount() ; i++) {
				
				stm = this.conexion.prepareStatement("INSERT INTO lecsys1.contacto (nombre, teléfono, email, idProveedores, sector) "
													+ "VALUES (?, ?, ?, ?, ?)");
				stm.setString(1, (String)contactos.getValueAt(i, 0));
				stm.setString(2, (String)contactos.getValueAt(i, 1));
				stm.setString(3, (String)contactos.getValueAt(i, 2));
				stm.setInt(4, idProveedor);
				stm.setString(5, (String)contactos.getValueAt(i, 3));
				stm.executeUpdate();	
			}
		} catch (Exception e) {
			
			bandera = false;
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("ProveedoresDAO, setProveedorNuevo()");
		} finally {
			
			this.cerrar();
		}
		dtosActividad.registrarActividad("Registrar nuevo proveedor.", "Administración.");
		return bandera;
	}
	
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
