package modelo;

import java.util.Arrays;
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

	public void setConfiguracion() {
		
		if(!isArchivoExiste())
			setArchivoConfiguraci�n();
	}
	
	public String setEmail(String email, char[] contrase�a, char[] recontrase�a) {
		
		String msg = "";
		String pass = Arrays.toString(contrase�a);
		
		if(email.length() < 6)
			return "El campo email debe estar lleno.";
		
		if(!email.contains("@") || email.contains(" "))
			return "Error en el formato del email.";
		
		String partes[] = email.split("@");
		
		try {
			
			if(partes[1].length() < 2 || !partes[1].contains("."))
				return "Error en el formato del email.";	
			
		} catch (Exception e) {
			
			return "Error en el formato del email.";	
		}	
		
		if(!pass.equals(Arrays.toString(recontrase�a)))
			return "Las contrase�as son distintas";
		
		pass = "";
		
		for(int i = 0; i < contrase�a.length; i++) {
			
			pass += contrase�a[i];
		}
		
		String valores[] = {"MAIL", email, "MPAS", pass};
		msg = modificarValores(valores);
	
		if(msg.equals("")) {
			
			emailSistema = email;
			passSistema = pass;
		}
		return msg;
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