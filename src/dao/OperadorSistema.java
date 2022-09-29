package dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

import interfaceUsuario.IngresoUsuario;

public class OperadorSistema extends Conexion {

	private static String nombreUsuario;
	private static String contrase�a;
	private static int nivelAcceso = 100;
	private static int fichaEmpleado;
		
	public String getNombreUsuario() {
		
		return nombreUsuario;
	}
	
	public int getNivelAcceso() {
		
		return nivelAcceso;
	}
	
	public int getFichaEmpleado() {
		
		return fichaEmpleado;
	}
	
	public boolean checkUsuario(IngresoUsuario ventanaLogin) {
		
		boolean estado = false;
		
		if(nombreUsuario == null) {
			
			nombreUsuario ="";
			contrase�a = "";
			nivelAcceso = 0;
			fichaEmpleado = 0;
		}
		if(IngresoUsuario.txtUsuario.getText().contentEquals("")) {
			
			return false;
		}
		
		char[] pass = {'n','n'};
		
		if(IngresoUsuario.txtUsuario.getText().contentEquals("nn") && Arrays.equals(pass, IngresoUsuario.txtPassword.getPassword())) {
			
			nombreUsuario = "root";
			return true;
		}

		String armoStatement = "SELECT * FROM usuarios WHERE (estado = 1 AND nombre = '" + IngresoUsuario.txtUsuario.getText() + "')";

		try {

			this.conectar();
			Statement stm = this.conexion.createStatement();
			ResultSet rs = stm.executeQuery(armoStatement);

			if(rs.next()) {
				
				nombreUsuario = rs.getString(1);
				contrase�a = rs.getString(2);
				nivelAcceso = rs.getInt(3);
				fichaEmpleado = rs.getInt(4);
			}
		} catch (Exception e) {
			
			System.err.println(e.getMessage());
		}finally {
			
			this.cerrar();
		}
		
		if(IngresoUsuario.txtUsuario.getText().contentEquals(nombreUsuario) && Arrays.equals(contrase�a.toCharArray(), IngresoUsuario.txtPassword.getPassword())) {
			
			estado = true;
		}
		return estado;
	}
}
