package modelo;

import dao.CursosDAO;
import dao.EmpleadosDAO;

public class DtosCrearCurso {
	
	private static String a�o;
	private static String nivel;
	private static String [] idProfesores;
	private static String idProfesor;
	private static String turno;
	private static String valorCuota;
	private static String [][] matrizDiasCursado = new String [6][2];
	private static String [][] diasCursado;
	
	public String [] getListaNivel() {
		
		return new String [] {"Kinder", "Children", "Junior", "Teens", "Adults"};
	}

	public String [] getListaTurno() {
		
		return new String [] {"Ma�ana", "Tarde", "Noche"};
	}
	
	public String [] getA�o(String nivel) {
		
		if(nivel.contentEquals("Kinder")) {
			
			return new String [] {" ", "PRE"};
		} else if(nivel.contentEquals("Children")) {
			
			return new String [] {"First", "Second", "Third"};
		}else if(nivel.contentEquals("Junior") || nivel.contentEquals("Adults")) {
			
			return new String [] {"First", "Second", "Third", "Fourth", "Fifth", "Sixth"};
		}else if(nivel.contentEquals("Teens")) {
			
			return new String [] {"I", "II", "III"};
		}
		return null;
	}
	
	public String [] getProfesores() {
		
		EmpleadosDAO empleadosDAO = new EmpleadosDAO();
		String matriz[][] = empleadosDAO.getEmpleados("Docente", true, "");
		String respuesta[] = new String[matriz.length];
		idProfesores = new String [matriz.length];
		
		for(int i = 0 ; i < matriz.length ; i++) {
			
			respuesta[i] = matriz[i][1] + " " + matriz[i][2];
			idProfesores[i] = matriz[i][0];
		}
		
		return respuesta;
	}

	public String checkInformacion() {
		
		int cont=0;
		String dias[] = new String [] {"Lunes","Martes","Miercoles","Jueves","Viernes","S�bado"};
		for(int i = 0 ; i < 6 ; i++) {
			
			if(!matrizDiasCursado[i][0].contentEquals(""))
				cont++;
		}
		
		if(valorCuota.contentEquals("") || !isNumeric(valorCuota))
			return "El valor cuota no puede estar vac�o y debe ser un n�mero.";
		
		if(cont==0)
			return "Debe elegir por lo menos un d�a";

		diasCursado = new String [cont][3];
		cont = 0;
		for(int i = 0 ; i < 6 ; i++) {
			
			if(!matrizDiasCursado[i][0].contentEquals("")) {
				
				if(matrizDiasCursado[i][1].contentEquals(""))
					return "Los d�as seleccionados deben tener duraci�n de la clase.";

				diasCursado[cont][0]=dias[i];
				diasCursado[cont][1]=matrizDiasCursado[i][0];
				diasCursado[cont][2]=matrizDiasCursado[i][1];
				cont++;
			}
		}		
		return "";
	}
	
	private static boolean isNumeric(String cadena) {
		
		try {
			
			Double.parseDouble(cadena);
			return true;
		} catch (NumberFormatException e){
			
			return false;
		}
	}

	public boolean setNuevoCurso() {
		
		CursosDAO cursoDAO = new CursosDAO();
		return cursoDAO.setCurso();
	}

	public String getA�o() {
		return a�o;
	}

	public void setA�o(String a�o) {
		DtosCrearCurso.a�o = a�o;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		DtosCrearCurso.nivel = nivel;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		DtosCrearCurso.turno = turno;
	}

	public String getValorCuota() {
		return valorCuota;
	}

	public void setValorCuota(String valorCuota) {
		DtosCrearCurso.valorCuota = valorCuota;
	}

	public String [][] getDiasCursado() {
		return diasCursado;
	}

	public void setDiasCursado(int dia, String horario, String duracion) {
		DtosCrearCurso.matrizDiasCursado[dia][0]=horario;
		DtosCrearCurso.matrizDiasCursado[dia][1]=duracion;
	}

	public String getIdProfesor() {
		return idProfesor;
	}
	
	public void setIdProfesor(int orden) {
		DtosCrearCurso.idProfesor = idProfesores[orden];
	}
}
