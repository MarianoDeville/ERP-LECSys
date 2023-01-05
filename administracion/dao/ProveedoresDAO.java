package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;
import controlador.CtrlLogErrores;
import modelo.DtosActividad;

public class ProveedoresDAO extends Conexion {
	
	public boolean setActualizarProveedor(String id, String nombre, String dir, String cuit, String sF, boolean est, JTable contactos) {
		
		boolean bandera = true;
		DtosActividad dtosActividad = new DtosActividad();

		try {
			
			this.conectar();
			PreparedStatement stm = this.conexion.prepareStatement("UPDATE lecsys1.proveedores "
														+ "SET nombre = ?, direccion = ?, cuit = ?, tipo = ?, estado = ? "
														+ "WHERE idProveedores = ?");
			stm.setString(1, nombre);
			stm.setString(2, dir);
			stm.setString(3, cuit);
			stm.setString(4, sF);
			stm.setString(5, est?"1":"0");
			stm.setString(6, id);
			stm.executeUpdate();
			
			if(est) {		
				
				stm = this.conexion.prepareStatement("DELETE FROM lecsys1.contacto WHERE idProveedores = ?");
				stm.setString(1, id);
				stm.executeUpdate();
				
				for(int i = 0 ; i < contactos.getRowCount() ; i++) {
					
					stm = this.conexion.prepareStatement("INSERT INTO lecsys1.contacto (nombre, teléfono, email, idProveedores, sector) "
														+ "VALUES (?, ?, ?, ?, ?)");
					stm.setString(1, (String)contactos.getValueAt(i, 0));
					stm.setString(2, (String)contactos.getValueAt(i, 2));
					stm.setString(3, (String)contactos.getValueAt(i, 3));
					stm.setString(4, id);
					stm.setString(5, (String)contactos.getValueAt(i, 1));
					stm.executeUpdate();				
				}
			}
		} catch (Exception e) {
			
			bandera = false;
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("ProveedoresDAO, setActualizarProveedor()");
		} finally {
			
			this.cerrar();
		}
		dtosActividad.registrarActividad("Actualizar información del proveedor.", "Administración.");
		return bandera;
	}
	
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
				stm.setString(2, (String)contactos.getValueAt(i, 2));
				stm.setString(3, (String)contactos.getValueAt(i, 3));
				stm.setInt(4, idProveedor);
				stm.setString(5, (String)contactos.getValueAt(i, 1));
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
	
	public String [][] getListadoProveedores(String filtrado, boolean estado) {
		
		String matriz[][]=null;
		String comandoStatement = "SELECT nombre, cuit, direccion, tipo, idProveedores "
								+ "FROM lecsys1.proveedores "
				 				+ "WHERE (estado = " + (estado?"1":"0") +  " AND (nombre LIKE '%" + filtrado + "%' OR cuit LIKE'" + filtrado + "%')) "
				 				+ "ORDER BY nombre";
		
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
				i++;
			}
			
			i = 0;
			while(i < matriz.length) {		

				comandoStatement = "SELECT teléfono, email, nombre FROM lecsys1.contacto WHERE idProveedores = " + matriz[i][4];
				rs = stm.executeQuery(comandoStatement);
				boolean banderaT = true;
				boolean banderaE = true;
				String telefono;
				String email;
				String nombre;
				
				while (rs.next()) {
					
					telefono = rs.getString(1);
					email = rs.getString(2);
					nombre = rs.getString(3);
					
					if(telefono.length() > 1) {
						
						if(banderaT) {
							
							matriz[i][5] = nombre + ": " + telefono;
							banderaT = false;
						} else {
							
							matriz[i][5] += ", " + telefono;
						}						
					}
					
					if(email.length() > 1) {
						
						if(banderaE) {
							
							matriz[i][6] = nombre + ": " + email;
							banderaE = false;
						} else {
							
							matriz[i][6] += ", " + email;
						}	
					}
				}
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
	
	public String [][] getListadoContactos(String idProveedor) {
		
		String matriz[][]=null;
		String comandoStatement = "SELECT nombre, sector, teléfono, email FROM lecsys1.contacto WHERE idProveedores = " + idProveedor;
		
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
			CtrlLogErrores.guardarError("ProveedoresDAO, getListadoContactos()");
			CtrlLogErrores.guardarError(comandoStatement);
		} finally {
			
			this.cerrar();
		}
		return matriz;
	}
	
	public boolean isCUITExistente(String cuit) {
		
		boolean bandera = false;
		
		try {
			
			this.conectar();
			Statement stm = this.conexion.createStatement();
			ResultSet rs = stm.executeQuery("SELECT idProveedores FROM lecsys1.proveedores WHERE cuit = " + cuit);

			if(rs.next())
				bandera = true;
			
		}catch (Exception e) {
			
			CtrlLogErrores.guardarError(e.getMessage());
			CtrlLogErrores.guardarError("ProveedoresDAO, isCUITExistente()");
		} finally {
			
			this.cerrar();
		}
		
		return bandera;
	}
}
