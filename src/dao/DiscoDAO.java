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
		DtosConfiguracion configuración = new DtosConfiguracion();

		try {

			archivo = new File ("LECSys.dam");
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);
			String lineaLeida;
			
			while((lineaLeida=br.readLine())!=null) {
					
				String[] partes = lineaLeida.split("=");
				
				switch(partes[0]) {
				
				case "DLOG":
					configuración.setDirectorio(partes[1]);
					break;
					
				case "IPBD":
					configuración.setServidor(partes[1]);
					break;	
					
				case "USR":
					configuración.setUsuarioBD(partes[1]);
					break;
					
				case "UPAS":
					configuración.setPassBD(partes[1]);
					break;
				
				case "MAIL":
					configuración.setEmailSistema(partes[1]);
					break;
					
				case "MPAS":
					configuración.setPassSistema(partes[1]);
					break;
				}
			}
		} catch(Exception e){
			
			msg = "No se encuentra archivo de configuración.";
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
