package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import modelo.DtosConfiguracion;

public class DiscoDAO {
	
	public String getConfiguracion() {
		
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		String msg = null;
		DtosConfiguracion configuraci�n = new DtosConfiguracion();

		try {

			archivo = new File ("LECSys.dam");
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);
			String lineaLeida;
			
			while((lineaLeida=br.readLine())!=null) {
					
				String[] partes = lineaLeida.split("=");
				
				switch(partes[0]) {
				
				case "DLOG":
					configuraci�n.setDirectorio(partes[1]);
					break;
					
				case "IPBD":
					configuraci�n.setServidor(partes[1]);
					break;	
					
				case "USR":
					configuraci�n.setUsuarioBD(partes[1]);
					break;
					
				case "UPAS":
					configuraci�n.setPassBD(partes[1]);
					break;
				
				case "MAIL":
					configuraci�n.setEmailSistema(partes[1]);
					break;
					
				case "MPAS":
					configuraci�n.setPassSistema(partes[1]);
					break;
				}
			}
		} catch(Exception e){
			
			msg = "No se encuentra archivo de configuraci�n.";
			e.printStackTrace();
		} finally {

			try {
				
				if( null != fr )   
					fr.close();     
           
			} catch (Exception e2) {
				
				System.err.println(e2.getMessage());
			}
		}
		return msg;
	}
}
