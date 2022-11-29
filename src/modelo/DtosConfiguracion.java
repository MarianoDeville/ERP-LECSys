package modelo;

import dao.DiscoDAO;

public class DtosConfiguracion extends DiscoDAO{

	private static String emailSistema;
	private static String passSistema;
	private static String servidor;
	private static String usuarioBD;
	private static String passBD;
	private static String directorio;
	private static String msgError;
	
	public String getConfig() {
		
		return getConfiguracion();
	}
	
	public String getEmailSistema() {
		return emailSistema;
	}
	
	public void setEmailSistema(String emailSistema) {
		
		DtosConfiguracion.emailSistema = emailSistema;
	}
	
	public String getPassSistema() {
		
		return passSistema;
	}
	
	public void setPassSistema(String passSistema) {
		
		DtosConfiguracion.passSistema = passSistema;
	}
	public String getServidor() {
		
		return servidor;
	}
	
	public void setServidor(String servidor) {
		
		DtosConfiguracion.servidor = servidor;
	}
	
	public String getUsuarioBD() {
		
		return usuarioBD;
	}
	
	public void setUsuarioBD(String usuarioBD) {
		
		DtosConfiguracion.usuarioBD = usuarioBD;
	}
	
	public String getPassBD() {
		
		return passBD;
	}
	
	public void setPassBD(String passBD) {
		
		DtosConfiguracion.passBD = passBD;
	}

	public String getMsgError() {
		
		return msgError;
	}

	public String getDirectorio() {
		
		return directorio;
	}

	public void setDirectorio(String directorio) {
		
		DtosConfiguracion.directorio = directorio;
	}
}