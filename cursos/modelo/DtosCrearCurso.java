package modelo;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dao.CursosDAO;
import dao.EmpleadosDAO;

public class DtosCrearCurso {
	
	private boolean editable[][];
	private static int aula;
	private static String año;
	private static String nivel;
	private static String [] idProfesores;
	private static String idProfesor;
	private static String valorCuota;
	private static int[][] horarios;
		
	public DefaultTableModel getHorarios(int aula, int profesor) {
		
		CursosDAO cursoDAO = new CursosDAO();
		
		String titulo[] = new String[] {"7:00", "7:30", "8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", 
										"12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", 
										"16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30"};

		editable = cursoDAO.getCronogramaDias(profesor, aula);
		Object cronograma[][] = new Object[6][28];

		for(int i = 0 ; i < 6 ; i++) {
			
			for(int e = 0 ; e < 28 ; e++) {
				
				cronograma[i][e] = false;
			}
		}
				
		DefaultTableModel tablaModelo = new DefaultTableModel(cronograma, titulo){

			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable(int row, int column) {
				
				return editable[row][column];
			}
			
			public Class<?> getColumnClass(int row) {
	
		        return Boolean.class;
		    }
		};
		return tablaModelo;
	}
	
	public String [] getListaNivel() {
		
		return new String [] {"Kinder", "Children", "Junior", "Teens", "Adults"};
	}

	public String [] getListaTurno() {
		
		return new String [] {"Mañana", "Tarde", "Noche"};
	}
	
	public String [] getListaAulas() {
		
		return new String [] {"A1", "A2", "A3", "B1", "B2", "B3"};
	}
	
	public String [] getAño(String nivel) {
		
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
		
		if(valorCuota.contentEquals("") || !isNumeric(valorCuota))
			
			return "El valor cuota no puede estar vacío y debe ser un número.";
		
		for(int i = 0 ; i < 6 ; i++) {
			
			for(int e = 0; e < 28 ; e++) {
			
				if(horarios.length == 0) {
				
					return "Debe elegir por lo menos un día y horario para el curso.";
				}
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

	public String getAño() {
		return año;
	}

	public void setAño(String año) {
		DtosCrearCurso.año = año;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		DtosCrearCurso.nivel = nivel;
	}

	public String getValorCuota() {
		return valorCuota;
	}

	public void setValorCuota(String valorCuota) {
		DtosCrearCurso.valorCuota = valorCuota;
	}

	public String getIdProfesor() {
		return idProfesor;
	}
	
	public void setIdProfesor(int orden) {
		DtosCrearCurso.idProfesor = idProfesores[orden];
	}

	public int [][] getHorarios() {
		return horarios;
	}

	public void setHorarios(JTable horarios) {
		
		int cont = 0;
		int cursado[][];
		
		for(int i = 0 ; i < 6 ; i++) {
			
			for(int e = 0; e < 28; e++) {
			
				if((boolean)horarios.getValueAt(i, e) == true) {
				
					while((boolean)horarios.getValueAt(i, e)) {
						
						e++;
					}
					cont++;
				}
			}
		}
		cursado = new int [cont][3];
		cont = 0;
		
		for(int i = 0 ; i < 6 ; i++) {
			
			int e = 0;
			
			while(e < 28) {
				
				if((boolean)horarios.getValueAt(i, e)) {

					cursado[cont][0] = i;
					cursado[cont][1] = e;
					int cant = 0;
					
					while((boolean)horarios.getValueAt(i, e)) { 
						
						e++;
						cant++;
					}
					cursado[cont][2] = cant;
					cont++;
				}
				e++;
			}
		}
		DtosCrearCurso.horarios = cursado;
	}

	public int getAula() {
		return aula;
	}

	public void setAula(int aula) {
		DtosCrearCurso.aula = aula;
	}
}
