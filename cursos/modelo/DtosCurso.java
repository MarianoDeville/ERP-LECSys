/************************************************************************************************************************************************/
//										LISTADO DE M?TODOS
/*----------------------------------------------------------------------------------------------------------------------------------------------*/
//	public String getMsgError()
//	public boolean autocompletar(JTable tablaOcupacion)
//	public DefaultTableModel getHorariosCurso(int aula, int profesor)
//	public void getInformacionCurso()
//	public DefaultTableModel getTablaCursos()
//	public DefaultTableModel getDiagramacion (String criterio, int valor)
//	public void setHorarios(JTable tablaHorarios)
//	public boolean isCheckInfo()
//	public String [] getListadoOpciones(String valor)
//	public String [] getListaCursos()
//	public String [] getListadoHorarios()
//	public String [] getListaNivel()
//	public String [] getListaCriterios()
//	public String [] getListaAulas()
//	public String [] getListaA?os(String nivel)
//	public String [] getListaProfesores()
//	private static boolean isNumeric(String cadena)
//	public boolean setNuevoCurso()
//	public boolean setActualizarCurso()
//	public String getA?o()
//	public void setA?o(String a?o)
//	public String getNivel()
//	public void setNivel(String nivel)
//	public String getValorCuota()
//	public void setValorCuota(String valorCuota)
//	public void setIdProfesor(int orden)
//	public int getAula()
//	public void setAula(int aula)
//	public String getNombreProfesor()
//	public void setCurso(String curso)
//	public void setEstado(int estado)
//	public String getCantHoras()
/************************************************************************************************************************************************/

package modelo;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dao.CursosDAO;
import dao.EmpleadosDAO;

public class DtosCurso {
	
	private boolean ocupado[][];
	private String horarios[][];
	private String idProfesores[];
	private String idCursos[];
	private int aula;
	private int estado;
	private String a?o;
	private String nivel;
	private String idProfesor;
	private String valorCuota;
	private String nombreProfesor;
	private String idCurso;
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
				
				switch ((String)tablaOcupacion.getValueAt(i, e)) {
				
					case "C":
					case "C ":
						buclesLlenado++;	
						break;

					case "F":
					case "F ":
						tablaOcupacion.setValueAt("O ", i, e);
						buclesLlenado--;	
						break;
					
					case "CE":
						buclesVaciado++;	
						break;
						
					case "FE":
						tablaOcupacion.setValueAt(" ", i, e);
						buclesVaciado--;	
						break;	
				}

				if(buclesLlenado > 0 && tablaOcupacion.getValueAt(i, e).equals("X")) {
				
					msgError = "No es posible reservar en el rango seleccionado.";
					return false;
				}
				
				if(buclesLlenado > 0) {
					if(e == 0)
						tablaOcupacion.setValueAt("O ", i, e);
					else
						tablaOcupacion.setValueAt(tablaOcupacion.getValueAt(i, e-1).equals(" ")?"O ":"O", i, e);
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
		cursoDAO.getCronogramaDias(idCurso, idProfesores[profesor], aula);
		ocupado = cursoDAO.getmatrizDiasHorarios();
		String cronograma[][] = new String[6][getListadoHorarios().length];

		for(int i = 0 ; i < 6 ; i++) {
			
			for(int e = 0 ; e < getListadoHorarios().length ; e++) {
				
				if(ocupado[i][e]) {
					
					cronograma[i][e] = "X";
					
					if(e > 0 && e < getListadoHorarios().length - 1) { 
						
						if(!ocupado[i][e-1] || !ocupado[i][e+1])
							cronograma[i][e] = "X ";
					}

				} else {
					
					cronograma[i][e] = " ";
				}
			}
		}

		if(this.aula == aula && !idCurso.equals("0")) {
		
			cursoDAO.getCronogramaDias(idCurso, "0", 0);
			ocupado = cursoDAO.getmatrizDiasHorarios();
	
			for(int i = 0 ; i < 6 ; i++) {
				
				for(int e = 0 ; e < getListadoHorarios().length ; e++) {
					
					if(ocupado[i][e]) {
						
						cronograma[i][e] = "O";
						
						if(e > 0 && e < getListadoHorarios().length - 1) { 
							
							if(!ocupado[i][e-1] || !ocupado[i][e+1])
								cronograma[i][e] = "O ";
						}
					}
				}
			}
		}
		DefaultTableModel tablaModelo = new DefaultTableModel(cronograma, getListadoHorarios()){
			
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
			
			a?o = respuesta[0][0];
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
		String titulo[] = {"", "Curso", "Profesor", "Aula", "Cuota", "D?as", "Sel."};
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

		if(criterio.equals("Profesor")) {
			
			cursoDAO.getCronogramaDias("0", idProfesores[valor], 100);
		} else if(criterio.equals("Curso")) {
			
			cursoDAO.getCronogramaDias(idCursos[valor], "0", 100);
		}else {
			
			cursoDAO.getCronogramaDias("0", "0", valor);
		}
		ocupado = cursoDAO.getmatrizDiasHorarios();
		String cronograma[][] = new String[6][getListadoHorarios().length];

		for(int i = 0 ; i < 6 ; i++) {
			
			for(int e = 0 ; e < 33 ; e++) {
				
				cronograma[i][e] = ocupado[i][e]? "X":" ";

				if(cronograma[i][e].equals("X")) {
					
					sumaHoras++;
				}
			}
		}
		cantHoras = (float)((sumaHoras - 1) / 2) + "";
		DefaultTableModel tablaModelo = new DefaultTableModel(cronograma, getListadoHorarios());
		return tablaModelo;	
	}
	
	public void setHorarios(JTable tablaHorarios) {

		int cant = 0;
		boolean comienzo;

		for(int i = 0; i < tablaHorarios.getRowCount(); i++) {
			
			comienzo = false;
			
			for(int e = 0; e < tablaHorarios.getColumnCount(); e++) {

				if(tablaHorarios.getValueAt(i, e).equals("O ") && !comienzo) {
				
					comienzo = true;
					cant++;
				} 
				
				if(!tablaHorarios.getValueAt(i, e).equals("O") && !tablaHorarios.getValueAt(i, e).equals("O "))
					comienzo = false;
			}
		}
		horarios = new String [cant][3];
		int pos = -1;
		
		for(int i = 0; i < tablaHorarios.getRowCount(); i++) {
		
			comienzo = false;
			
			for(int e = 0; e < tablaHorarios.getColumnCount(); e++) {

				if(tablaHorarios.getValueAt(i, e).equals("O ") && !comienzo) {
					
					pos++;
					comienzo = true;
					horarios[pos][0] = i + "";
					horarios[pos][1] = getListadoHorarios()[e];
					cant = 0;
				}
		
				if(!tablaHorarios.getValueAt(i, e).equals("O") && !tablaHorarios.getValueAt(i, e).equals("O "))
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
			
			msgError = "El valor cuota no puede estar vac?o y debe ser un n?mero.";
			return false;
		}
		
		if(horarios.length == 0) {
		
			msgError = "Debe elegir por lo menos un d?a y horario para el curso.";
			return false;
		}
		return true;
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
	
	public String [] getListadoHorarios() {

		String listado[] = new String[33];
		boolean par = true;
		int hora = 7;
		
		for(int i = 0; i < listado.length; i++) {
			
			if(par) {
				
				listado[i] = hora + ":00";
				par = false;
			} else {
				
				listado[i] = hora + ":30";
				par = true;
				hora++;
			}
		}
		return listado;
	}
	
	public String [] getListaNivel() {
		
		return new String [] {"Kinder", "Children", "Junior", "Teens", "Adults"};
	}

	public String [] getListaCriterios() {
		
		return new String [] {"Aula", "Curso", "Profesor"};
	}

	public String [] getListaAulas() {
		
		return new String [] {"A1", "A2", "A3", "B1", "B2", "B3"};
	}
	
	public String [] getListaA?os(String nivel) {
		
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
		return cursoDAO.setCurso(a?o, nivel, idProfesor, aula, valorCuota, horarios);
	}
	
	public boolean setActualizarCurso() {
		
		CursosDAO cursoDAO = new CursosDAO();
		return cursoDAO.setActualizarCurso(idCurso, idProfesor, aula, valorCuota, estado, horarios);
	}

	public String getA?o() {
		
		return a?o;
	}

	public void setA?o(String a?o) {
		
		this.a?o = a?o;
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

	public void setCurso(String curso) {
		
		this.idCurso = curso;
	}

	public void setEstado(int estado) {
		
		this.estado = estado;
	}

	public String getCantHoras() {
		return cantHoras;
	}
}
