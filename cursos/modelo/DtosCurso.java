package modelo;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dao.CursosDAO;
import dao.EmpleadosDAO;

public class DtosCurso {
	
	private boolean ocupado[][];
	private  int aula;
	private int estado;
	private String año;
	private String nivel;
	private String [] idProfesores;
	private String idProfesor;
	private String valorCuota;
	private String nombreProfesor;
	private String[][] horarios;
	private String idCurso;
	private String [] idCursos;
	private String cantHoras;
	private String msgError;
	
	public String getMsgError() {
		
		return msgError;
	}
	
	public boolean autocompletar(JTable tablaOcupacion) {

		msgError = null;
		
		for(int i = 0; i < tablaOcupacion.getRowCount(); i++) {

			int buclesLlenado = 0;
			int buclesVaciado = 0;
			
			for(int e = 0; e < tablaOcupacion.getColumnCount(); e++) {
				
				if(tablaOcupacion.getValueAt(i, e).equals("C") || tablaOcupacion.getValueAt(i, e).equals("C ")) {
					
					buclesLlenado++;
				} else if(tablaOcupacion.getValueAt(i, e).equals("F") || tablaOcupacion.getValueAt(i, e).equals("F ")) {

					tablaOcupacion.setValueAt("O", i, e);
					buclesLlenado--;
				} else if(tablaOcupacion.getValueAt(i, e).equals("CE")) {
					
					buclesVaciado++;
				} else if(tablaOcupacion.getValueAt(i, e).equals("FE")) {
				
					tablaOcupacion.setValueAt(" ", i, e);
					buclesVaciado--;
				}

				if(buclesLlenado > 0 && tablaOcupacion.getValueAt(i, e).equals("X")) {
				
					msgError = "No es posible reservar en el rango seleccionado.";
					return false;
				}
				
				if(buclesLlenado > 0) {
					
					tablaOcupacion.setValueAt("O", i, e);
				}				
	
				if(buclesVaciado > 0) {
					
					tablaOcupacion.setValueAt(" ", i, e);
				}
			}
		}
		return msgError==null;
	}

	public DefaultTableModel getHorariosCurso(int aula, int profesor) {
		
		CursosDAO cursoDAO = new CursosDAO();
		String titulo[] = getListadoHorarios();
		cursoDAO.getCronogramaDias(0, Integer.parseInt(idProfesores[profesor]), aula);
		ocupado = cursoDAO.getmatrizDiasHorarios();
		String cronograma[][] = new String[6][titulo.length];

		for(int i = 0 ; i < 6 ; i++) {
			
			for(int e = 0 ; e < titulo.length ; e++) {
				
				if(ocupado[i][e]) {
					
					cronograma[i][e] = "X";
					
					if(e > 0 && e < titulo.length - 1) { 
						
						if(!ocupado[i][e-1] || !ocupado[i][e+1])
							cronograma[i][e] = "X ";
					}

				} else {
					
					cronograma[i][e] = " ";
				}
			}
		}

		if(this.aula == aula) {
		
			cursoDAO.getCronogramaDias(Integer.parseInt(idCurso), 0, 0);
			ocupado = cursoDAO.getmatrizDiasHorarios();
	
			for(int i = 0 ; i < 6 ; i++) {
				
				for(int e = 0 ; e < titulo.length ; e++) {
					
					if(ocupado[i][e]) {
						
						cronograma[i][e] = "O";
						
						if(e > 0 && e < titulo.length - 1) { 
							
							if(!ocupado[i][e-1] || !ocupado[i][e+1])
								cronograma[i][e] = "O ";
						}
	
					}
				}
			}
		}
		DefaultTableModel tablaModelo = new DefaultTableModel(cronograma, titulo){
			
			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		return tablaModelo;
	}

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
		int sumaHoras = 0;
		String titulo[] = new String[] {"", "7:00", "7:30", "8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", 
										"13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", 
										"18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00"};
		String dia[] = new String[] {"Lunes","Martes","Miércoles","Jueves","Viernes","Sábado"};

		if(criterio.equals("Profesor")) {
			
			cursoDAO.getCronogramaDias(0, Integer.parseInt(idProfesores[valor]), 100);
		
		} else if(criterio.equals("Curso")) {
			
			cursoDAO.getCronogramaDias(Integer.parseInt(idCursos[valor]), 0, 100);
		}else {
			
			cursoDAO.getCronogramaDias(0, 0, valor);
		}
		ocupado = cursoDAO.getmatrizDiasHorarios();
		Object cronograma[][] = new Object[6][33];

		for(int i = 0 ; i < 6 ; i++) {
			
			for(int e = 0 ; e < 33 ; e++) {
				
				if(e == 0) {
					
					cronograma[i][e] = dia[i];
				} else {
					
					cronograma[i][e] = ocupado[i][e-1]? " ":"   O ";
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
							 "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00"};
	}

	public void setHorarios(JTable tablaHorarios) {

		int cant = 0;
		boolean comienzo;
		
		for(int i = 0; i < tablaHorarios.getRowCount(); i++) {
			
			comienzo = false;
			
			for(int e = 0; e < tablaHorarios.getColumnCount(); e++) {
			
				if(tablaHorarios.getValueAt(i, e).equals("O") && !comienzo) {
				
					comienzo = true;
					cant++;
				} 
				
				if(!tablaHorarios.getValueAt(i, e).equals("O"))
					comienzo = false;
			}
		}
		horarios = new String [cant][3];
		int pos = -1;
		
		for(int i = 0; i < tablaHorarios.getRowCount(); i++) {
		
			comienzo = false;
			
			for(int e = 0; e < tablaHorarios.getColumnCount(); e++) {

				if(tablaHorarios.getValueAt(i, e).equals("O") && !comienzo) {
					
					pos++;
					comienzo = true;
					String horas[] = getListadoHorarios();
					horarios[pos][0] = i + "";
					horarios[pos][1] = horas[e];
					cant = 0;
				}
				
				if(!tablaHorarios.getValueAt(i, e).equals("O"))
					comienzo = false;
				
				if(comienzo) {
			
					cant++;
					horarios[pos][2] = cant + "";
				}
			}
		}
	}
	
	public boolean isCheckInfo() {
		
		if(valorCuota.equals("") || !isNumeric(valorCuota)) {
			
			msgError = "El valor cuota no puede estar vacío y debe ser un número.";
			return false;
		}
		
		if(horarios.length == 0) {
		
			msgError = "Debe elegir por lo menos un día y horario para el curso.";
			return false;
		}
		return true;
	}
	
	public String [] getListaNivel() {
		
		return new String [] {"Kinder", "Children", "Junior", "Teens", "Adults"};
	}

	public String [] getListaCriterios() {
		
		return new String [] {"Aula", "Curso", "Profesor"};
	}
	
	public String [] getListadoOpciones(String valor) {
		
		if(valor.equals("Aula")) {
			
			return getListaAulas();
		} else if(valor.equals("Profesor")) {
			
			return getListaProfesores();
		} else if(valor.equals("Curso")) {
			
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
		
		if(nivel.equals("Kinder")) {
			
			return new String [] {" ", "PRE"};
		} else if(nivel.equals("Children")) {
			
			return new String [] {"First", "Second", "Third"};
		}else if(nivel.equals("Junior") || nivel.equals("Adults")) {
			
			return new String [] {"First", "Second", "Third", "Fourth", "Fifth", "Sixth"};
		}else if(nivel.equals("Teens")) {
			
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
		return cursoDAO.setCurso(año, nivel, idProfesor, aula, valorCuota, horarios);
	}
	
	public boolean setActualizarCurso() {
		
		CursosDAO cursoDAO = new CursosDAO();
		return cursoDAO.setActualizarCurso(idCurso, idProfesor, aula, valorCuota, estado, horarios);
	}

	public String getAño() {
		
		return año;
	}

	public void setAño(String año) {
		
		this.año = año;
	}

	public String getNivel() {
		
		return nivel;
	}

	public void setNivel(String nivel) {
		
		this.nivel = nivel;
	}

	public String getValorCuota() {
		
		return valorCuota;
	}

	public void setValorCuota(String valorCuota) {
		
		this.valorCuota = valorCuota;
	}

	public String getIdProfesor() {
		
		return idProfesor;
	}
	
	public void setIdProfesor(int orden) {
		
		this.idProfesor = idProfesores[orden];
	}

	public int getAula() {
		
		return aula;
	}

	public void setAula(int aula) {
		
		this.aula = aula;
	}

	public String getNombreProfesor() {
		
		return nombreProfesor;
	}

	public String getCurso() {
		
		return idCurso;
	}

	public void setCurso(String curso) {
		
		this.idCurso = curso;
	}

	public int getEstado() {
		
		return estado;
	}

	public void setEstado(int estado) {
		
		this.estado = estado;
	}

	public String getCantHoras() {
		return cantHoras;
	}
}
