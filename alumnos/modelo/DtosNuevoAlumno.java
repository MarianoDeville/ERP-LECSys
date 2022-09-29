package modelo;

public class DtosNuevoAlumno {

	public void setNuevoAlumno(String [] informacion) {
		
		
		
		
		
	}

	public String [] getListaCursos() {
		
		return new String [] {"uno","dos","tres","cuatro"};
	}
	
	public String checkInformacion(String [] informacion) {
		
		String msg = "";
		
		if(informacion[0].length() < 3) {

			msg ="El nombre debe tener más de dos caracteres.";
		}else if(informacion[1].length() < 3) {
			
			msg ="El apellido debe tener más de dos caracteres.";
		}else if(informacion[2].length() < 7 
				|| !isNumeric(informacion[2])) {
			
			msg ="Error en el formato del DNI (solamente números).";
		}else if(informacion[3].length() == 0 
				|| Integer.parseInt(informacion[3]) < 1920) {
			
			msg ="Error en el formato del año.";
		}else if(informacion[4].length() == 0 
				|| Integer.parseInt(informacion[4]) < 1 
				|| Integer.parseInt(informacion[4]) > 12 ) {
			
			msg ="Error en el formato del mes.";
		}else if(informacion[5].length() == 0 
				|| Integer.parseInt(informacion[5]) < 1 
				|| Integer.parseInt(informacion[5]) > 31 ) {
			
			msg ="Error en el formato del día.";
		}else if(informacion[6].length() == 0) {
			
			msg ="La dirección no puede estar vacía.";
		}else if(informacion[8].length() == 0 || !isNumeric(informacion[8])) {
			
			msg ="Error en el formato del teléfono (solamente números).";
		}
		
		return msg;
	}
	
	private static boolean isNumeric(String cadena) {
		
		try {
			
			Double.parseDouble(cadena);
			return true;
		} catch (NumberFormatException e){
			
			return false;
		}
	}
}
