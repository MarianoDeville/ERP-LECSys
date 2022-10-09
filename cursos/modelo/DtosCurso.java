package modelo;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dao.CursosDAO;
import dao.EmpleadosDAO;

public class DtosCurso {
	
	private boolean editable[][];
	private static int aula;
	private static String año;
	private static String nivel;
	private static String [] idProfesores;
	private static String idProfesor;
	private static String valorCuota;
	private static String nombreProfesor;
	private static int[][] horarios;
	private static String idCurso;
	private static String [] idCursos;
	private static int estado;
	private static String cantHoras;
	
	public void getInformacionCurso() {
		
		CursosDAO cursosDAO = new CursosDAO();
		String respuesta[][] = cursosDAO.getListado(idCurso);
		
		if(respuesta.length > 0) {
			
			año = respuesta[0][0];
			nivel = respuesta[0][1];
			nombreProfesor = respuesta[0][2];
			valorCuota = respuesta[0][3];
			idProfesor = respuesta[0][7];
			aula = Integer.parseInt(respuesta[0][8]);
			estado = 1;
		}
	}
	
	public DefaultTableModel getTablaCursos() {
		
		CursosDAO cursosDAO = new CursosDAO();
		String titulo[] = {"", "Curso", "Profesor", "Aula", "Cuota", "Días", "Sel."};
		String aulas [] = getListaAulas();
		String respuesta[][]= cursosDAO.getListado("");
		Object cuerpo[][]=null;
		
		if(respuesta != null) {
			
			cuerpo = new Object[respuesta.length][7];
			
			for(int i = 0 ; i < respuesta.length ; i++) {
				
				cuerpo[i][0] = respuesta[i][6];
				cuerpo[i][1] = respuesta[i][1] + " " + respuesta[i][0];
				cuerpo[i][2] = respuesta[i][2];
				cuerpo[i][3] = aulas[Integer.parseInt(respuesta[i][8])];
				cuerpo[i][4] = respuesta[i][3];
				cuerpo[i][5] = respuesta[i][4];
				cuerpo[i][6] = false;
			}
		} else
			
			cuerpo = null;
		
		DefaultTableModel tablaModelo = new DefaultTableModel(cuerpo, titulo){

			private static final long serialVersionUID = 1L;
			
			boolean[] columnEditables = new boolean[] {
					
					false, false, false, false, false, false, true
			};
			
			public boolean isCellEditable(int row, int column) {
				
				return columnEditables[column];
			}
			
			public Class<?> getColumnClass(int column) {
				
		        if(column == 6)
		        	
		        	return Boolean.class;
		        else
		        	
		        	return String.class;
		    }
		};
		return tablaModelo;
	}

	public DefaultTableModel getDiagramacion (String criterio, int valor) {
		
		CursosDAO cursoDAO = new CursosDAO();
		int sumaHoras=0;
		String titulo[] = new String[] {"", "7:00", "7:30", "8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", 
										"13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", 
										"18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30"};
		String dia[] = new String[] {"Lunes","Martes","Miércoles","Jueves","Viernes","Sábado"};

		if(criterio.contentEquals("Profesor")) {
			
			editable = cursoDAO.getCronogramaDias(0, Integer.parseInt(idProfesores[valor]), 100);
		} else if(criterio.contentEquals("Curso")) {
			
			editable = cursoDAO.getCronogramaDias(Integer.parseInt(idCursos[valor]), 0, 100);
		}else {
			
			editable = cursoDAO.getCronogramaDias(0, 0, valor);
		}

		Object cronograma[][] = new Object[6][33];

		for(int i = 0 ; i < 6 ; i++) {
			
			for(int e = 0 ; e < 32 ; e++) {
				
				if(e == 0) {
					
					cronograma[i][e] = dia[i];
				} else {
					
					cronograma[i][e] = editable[i][e]? " ":"   O ";
				}
				if(cronograma[i][e].equals("   O ")) {
					
					sumaHoras++;
				}
			}
		}
		cantHoras = (float)sumaHoras / 2 + "";
		DefaultTableModel tablaModelo = new DefaultTableModel(cronograma, titulo);
		return tablaModelo;	
	}
	
	public String [] getListadoHorarios() {
		
		return new String[] {"7:00", "7:30", "8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", 
							 "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", 
							 "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30"};
	}
	
	public DefaultTableModel getHorariosCurso(int aula, int profesor) {
		
		CursosDAO cursoDAO = new CursosDAO();
		String titulo[] = getListadoHorarios();
		editable = cursoDAO.getCronogramaDias(0, Integer.parseInt(idProfesores[profesor]), aula);
		boolean [][] horariosActuales = null;
		if(!idCurso.contentEquals("0")) {
			
			horariosActuales = cursoDAO.getCronogramaDias(Integer.parseInt(idCurso), profesor, aula);
		}
		Object cronograma[][] = new Object[6][32];

		for(int i = 0 ; i < 6 ; i++) {
			
			for(int e = 0 ; e < 32 ; e++) {
				
				if(!idCurso.contentEquals("0")) {
					
					cronograma[i][e] = !horariosActuales[i][e];
					
					if(!horariosActuales[i][e]) {
						
						editable[i][e] = true;
					}
				} else {
					
					cronograma[i][e] = false;
				}
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

	public void setHorarios(JTable horarios) {
		
		int cont = 0;
		int cursado[][];

		for(int i = 0 ; i < 6 ; i++) {
			
			for(int e = 0 ; e < 32 ; e++) {
			
				if((boolean)horarios.getValueAt(i, e) == true) {
				
					while((boolean)horarios.getValueAt(i, e) && e < 31) {
						
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
			
			while(e < 32) {
				
				if((boolean)horarios.getValueAt(i, e)) {

					cursado[cont][0] = i;
					cursado[cont][1] = e;
					int cant = 0;
					
					while((boolean)horarios.getValueAt(i, e) && e < 31) { 
						
						e++;
						cant++;
					}
					cursado[cont][2] = cant;
					cont++;
				}
				e++;
			}
		}
		DtosCurso.horarios = cursado;
	}

	public String checkInformacion() {
		
		if(valorCuota.contentEquals("") || !isNumeric(valorCuota))
			
			return "El valor cuota no puede estar vacío y debe ser un número.";
		
		for(int i = 0 ; i < 6 ; i++) {
			
			for(int e = 0; e < 32 ; e++) {
			
				if(horarios.length == 0) {
				
					return "Debe elegir por lo menos un día y horario para el curso.";
				}
			}
		}
		return "";
	}
	
	public String [] getListaNivel() {
		
		return new String [] {"Kinder", "Children", "Junior", "Teens", "Adults"};
	}

	public String [] getListaCriterios() {
		
		return new String [] {"Aula", "Curso", "Profesor"};
	}
	
	public String [] getListadoOpciones(String valor) {
		
		if(valor.contentEquals("Aula")) {
			
			return getListaAulas();
		} else if(valor.contentEquals("Profesor")) {
			
			return getListaProfesores();
		} else if(valor.contentEquals("Curso")) {
			
			return getListaCursos();
		}
		return null;
	}
	
	public String [] getListaCursos() {
		
		CursosDAO cursosDAO = new CursosDAO();
		String [][] respuesta = cursosDAO.getListado("");
		idCursos = new String[respuesta.length];
		String [] nombreCursos = new String[respuesta.length];
		
		for(int i = 0 ; i < respuesta.length; i++) {
			
			idCursos[i] = respuesta[i][5];
			nombreCursos[i] = respuesta[i][0] + " " + respuesta[i][1] + " " + respuesta[i][2];
		}
		return nombreCursos;
	}

	public String [] getListaAulas() {
		
		return new String [] {"A1", "A2", "A3", "B1", "B2", "B3"};
	}
	
	public String [] getListaAños(String nivel) {
		
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
	
	public String [] getListaProfesores() {
		
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
	
	public boolean setActualizarCurso() {
		
		CursosDAO cursoDAO = new CursosDAO();
		return cursoDAO.setActualizarCurso();
	}

	public String getAño() {
		
		return año;
	}

	public void setAño(String año) {
		
		DtosCurso.año = año;
	}

	public String getNivel() {
		
		return nivel;
	}

	public void setNivel(String nivel) {
		
		DtosCurso.nivel = nivel;
	}

	public String getValorCuota() {
		
		return valorCuota;
	}

	public void setValorCuota(String valorCuota) {
		
		DtosCurso.valorCuota = valorCuota;
	}

	public String getIdProfesor() {
		
		return idProfesor;
	}
	
	public void setIdProfesor(int orden) {
		
		DtosCurso.idProfesor = idProfesores[orden];
	}

	public int [][] getHorarios() {
		
		return horarios;
	}

	public int getAula() {
		
		return aula;
	}

	public void setAula(int aula) {
		
		DtosCurso.aula = aula;
	}

	public String getNombreProfesor() {
		
		return nombreProfesor;
	}

	public String getCurso() {
		
		return idCurso;
	}

	public void setCurso(String curso) {
		
		DtosCurso.idCurso = curso;
	}

	public int getEstado() {
		
		return estado;
	}

	public void setEstado(int estado) {
		
		DtosCurso.estado = estado;
	}

	public String getCantHoras() {
		return cantHoras;
	}
}
