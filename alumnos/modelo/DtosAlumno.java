/*****************************************************************************************************************************************************************/
//										LISTADO DE MÉTODOS
/*---------------------------------------------------------------------------------------------------------------------------------------------------------------*/
//	public int getAsistencia(String campo, int fila)
//	public int getCursoSeleccionado()
//	public boolean getEstado()
//	public DefaultTableModel getTablaAsistencia(int cursoSeleccionado)
//	public DefaultTableModel getListadoAlumnos(String campo, String valor)
//	public DefaultTableModel getTablaAlumnos(String campo, String valor, boolean estado, int pos)
//	public DefaultTableModel getTablaDias(int curso)
//	public DefaultTableModel getTablaRegistroAsistencia(int cursoSeleccionado, boolean reduciodo)
//	public DefaultTableModel getTablaExamenes(int curso)
//	public String getFechaActual(boolean formato)
//	public String getIdValorCriterio(String criterio, int valor)
//	public String getLegajo()
//	public String getNombre()
//	public String getApellido()
//	public String getFechaAño()
//	public String getFechaMes()
//	public String getFechaDia()
//	public String getDni()
//	public String getTelefono()
//	public String getDireccion()
//	public String getEmail()
//	public String getIDCurso()
//	public String getCurso()
//	public String getNombreCurso()
//	public String getIdPersona()
//	public String getCantAlumnos()
//	public String getResultadoExamen()
//	public String getIdProfesor()
//	public String getTipoExamen()
//	public String getFechaIngreso()
//	public String [] getOrdenamiento()
//	public String [] getListaCursos()
//	public String [] getCriterio() 
//	public String [] getListadoProfesores()
//	public String [] getListadoValorCriterio(String criterio)
//	public String [] getListaTipoExamen()
//	public String [][] getHorariosCursos()
//	public void setTablaAsistencia(int fila, int columna, boolean valor)
//	public void setLegajo(String legajo)
//	public void setNombre(String nombre)
//	public void setApellido(String apellido)
//	public void setFechaAño(String fechaNacimientoAño)
//	public void setFechaMes(String fechaNacimientoMes)
//	public void setFechaDia(String fechaNacimientoDia)
//	public void setDni(String dni)
//	public void setTelefono(String telefono)
//	public void setDireccion(String direccion)
//	public void setEmail(String email)
//	public void setIdCurso(String curso)
//	public void setHorariosCursos(String [][] horariosCursos)
//	public void setEstado(boolean estado)
//	public void setCurso(int curso)
//	public void setIdPersona(String idPersona)
//	public void setTipoExamen(String tipoExamen)
//	public boolean setNuevoAlumno() 
//	public boolean setActualizarAlumno()
//	public void limpiarInformacion()
//	public void recuperarInformacionAlumno(String nroLegajo, boolean estadoAlumno)
//	public boolean guardoAsistencia()
//	public boolean guardarResultados(String [][] tablaResultados)
//	public String checkInformacion(boolean checDNI)
//	private boolean isNumeric(String cadena)
/*****************************************************************************************************************************************************************/
package modelo;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.table.DefaultTableModel;
import dao.AlumnosDAO;
import dao.CursosDAO;
import dao.EmpleadosDAO;
import dao.PersonasDAO;

public class DtosAlumno {
	
	AlumnosDAO alumnosDAO;
	private static boolean estado;
	private static String legajo;
	private static String nombre;
	private static String apellido;
	private static String dni;
	private static String fechaAño;
	private static String fechaMes;
	private static String fechaDia;
	private static String telefono;
	private static String direccion;
	private static String fechaIngreso;
	private static String email;
	private static String idCurso;
	private static String idPersona;
	private static String idProfesor;
	private static String cantAlumnos;
	private static String resultadoExamen;
	private static String tipoExamen;
	private static String [] idCursos;
	private static String [] nombreCursos;
	private static String [] idProfesores;
	private static String [][] horariosCursos;
	private static Object [][] tablaAsistencia;
	
	public String guardarResultados(String [][] tablaResultados) {
		
		boolean bandera = true;
		
		for(int i = 0 ; i < tablaResultados.length ; i++) {
			
			if(!isNumeric(tablaResultados[i][1])) {
				
				if(!tablaResultados[i][1].equals("-")) {

					return "Las notas deben ser numéricas o guión.";
				}
			}
		}
		
		for(int i = 0 ; i < tablaResultados.length ; i++) {
			
			if(!tablaResultados[i][1].equals("-")) {
				
				legajo = tablaResultados[i][0];
				resultadoExamen = tablaResultados[i][1];
				idProfesor = idProfesores[getCursoSeleccionado()];
	
				if(bandera) {
					bandera = alumnosDAO.setExamen();
				} else {
					
					alumnosDAO.setExamen();
				}
			}
		}
		if(bandera) {
			
			return "Notas almacenadas correctamente.";
		}
		return "Error al intentar almacenar las notas.";
	}
	
	public String [] getListaTipoExamen() {
		
		return new String[] {"1º Escrito","2º Escrito","1º Oral", "2º Oral","Recuperatorio","Final"};
	}

	public DefaultTableModel getTablaExamenes(int cursoSeleccionado) {

		alumnosDAO = new AlumnosDAO();
		String cuerpo[][] = null;
		String titulo[] = {"Legajo", "Nombre", "Apellido", "Resultado"};
		String respuesta[][] = alumnosDAO.getAlumnos("Curso", idCursos[cursoSeleccionado], true, "");

		if(respuesta != null) {
		
			cuerpo = new String [respuesta.length][4];
			
			for(int i = 0 ; i < respuesta.length ; i++) {

				cuerpo[i][0] = respuesta[i][0];
				cuerpo[i][1] = respuesta[i][1];
				cuerpo[i][2] = respuesta[i][2];
				cuerpo[i][3] = "";
			}
		} else {
			
			cuerpo = null;
		}
		
		DefaultTableModel tablaModelo = new DefaultTableModel(cuerpo, titulo){

			private static final long serialVersionUID = 1L;
			
			boolean[] columnEditables = new boolean[] {
					false, false, false,  true, true, true
			};
			
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		return tablaModelo;
	}
	
	public DefaultTableModel getTablaRegistroAsistencia(int cursoSeleccionado) {
		
		alumnosDAO = new AlumnosDAO();
		String titulo1[] = {"Legajo", "Nombre", "Apellido"};
		String cuerpo[][] = null;
		String respuesta[][] = alumnosDAO.tablaAsistenciasAlumnos(idCursos[cursoSeleccionado], true);
		String titulo2[] = new String [respuesta.length];
		String titulo[] = new String[titulo1.length + titulo2.length];
		
		for(int i = 0 ; i < titulo.length ; i++) {
		
			if(i < 3) {
				
				titulo[i] = titulo1[i];
			} else {
				
				titulo[i] = respuesta[i - 3][2];
			}
		}
		respuesta = alumnosDAO.getAlumnos("Curso", idCursos[cursoSeleccionado], true, "");
		
		if(respuesta != null) {
			
			cuerpo = new String[respuesta.length][titulo.length];

			for(int i = 0 ; i < respuesta.length ; i++) {
				
				cuerpo[i][0] = respuesta[i][0];
				cuerpo[i][1] = respuesta[i][1];
				cuerpo[i][2] = respuesta[i][2];

			}
		} else {

			cuerpo = null;
		}
		respuesta = alumnosDAO.tablaAsistenciasAlumnos(idCursos[cursoSeleccionado], false);
		
		for( int e = 0 ; e < cuerpo.length ; e++) {
		
			int f = 3;
			
			for(int i = 0 ; i < respuesta.length  ; i++) {
				
				if(cuerpo[e][0].equals(respuesta[i][1])) {
					
					cuerpo[e][f] = respuesta[i][2];
			
					if(respuesta[i][3].equals("0")) {
						
						cuerpo[e][f] = "Falta";
					} else if(respuesta[i][3].equals("1")) {
						
						cuerpo[e][f] = "Presente";
					}else if(respuesta[i][3].equals("2")) {
						
						cuerpo[e][f] = "Tarde";
					}
					f++;
				}
			}
		}
		DefaultTableModel tablaModelo = new DefaultTableModel(cuerpo, titulo);
		return tablaModelo;
	}

	public void setCurso(int curso) {
		
		DtosAlumno.idCurso = idCursos[curso];
	}
	
	public String getCurso() {
		
		return idCurso;
	}

	public String getNombreCurso() {
	
		return (String)nombreCursos[getCursoSeleccionado()];
	}
	
	public String getFechaActual(boolean formato) {
		
		Calendar fechaSistema = new GregorianCalendar();
		String fecha = null;
		if(formato) {
			
			fecha = fechaSistema.get(Calendar.DAY_OF_MONTH) + "/" 
				  + (fechaSistema.get(Calendar.MONTH)+1) + "/" 
				  + fechaSistema.get(Calendar.YEAR);
		} else {
			
			fecha = fechaSistema.get(Calendar.YEAR) + "/" 
				  + (fechaSistema.get(Calendar.MONTH)+1) + "/" 
				  + fechaSistema.get(Calendar.DAY_OF_MONTH);
		}
		return fecha;
	}
	
	public boolean guardoAsistencia() {
		
		boolean bandera = true;
		alumnosDAO = new AlumnosDAO();
		
		for(int fila = 0 ; fila < tablaAsistencia.length ; fila++) {
			
			if(bandera) {
				
				bandera = alumnosDAO.setAsistencia(fila);
			} else {
				
				alumnosDAO.setAsistencia(fila);
			}
		}
		return bandera;
	}
	
	public void setTablaAsistencia(int fila, int columna, boolean valor) {
		
		if(fila < tablaAsistencia.length) {
			
			if(columna < tablaAsistencia[fila].length && columna > 2) {
				
				tablaAsistencia[fila][columna] = valor;
			}
		}
	}
	
	public int getAsistencia(String campo, int fila) {
		
		if(campo.equals("Legajo")) {
			
			return Integer.parseInt((String)tablaAsistencia[fila][0]);
		} else if(campo.equals("Estado")) {
			
			if((boolean)tablaAsistencia[fila][4]) {
				
				return 2;
			}
			if((boolean)tablaAsistencia[fila][3]) {
				
				return 1;
			}
		}
		return 0;
	}
	
	public DefaultTableModel getTablaAsistencia(int cursoSeleccionado) {
		
		alumnosDAO = new AlumnosDAO();
		String titulo[] = {"Legajo", "Nombre", "Apellido", "Presente", "Tarde"};
		String respuesta[][] = alumnosDAO.getAlumnos("Curso", idCursos[cursoSeleccionado], true, "");
		tablaAsistencia = null;
		cantAlumnos = "0";
		if(respuesta != null) {
			
			tablaAsistencia = new Object[respuesta.length][5];
			cantAlumnos = respuesta.length + "";
			
			for(int i = 0 ; i < respuesta.length ; i++) {
				
				tablaAsistencia[i][0] = respuesta[i][0];
				tablaAsistencia[i][1] = respuesta[i][1];
				tablaAsistencia[i][2] = respuesta[i][2];
				tablaAsistencia[i][3] = false;
				tablaAsistencia[i][4] = false;
			}
		} else
			tablaAsistencia = null;
		
		DefaultTableModel tablaModelo = new DefaultTableModel(tablaAsistencia, titulo){

			private static final long serialVersionUID = 1L;
			
			boolean[] columnEditables = new boolean[] {
					false, false, false, true, true
			};
			
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
			
			public Class<?> getColumnClass(int column) {
				
		        if(column > 2)
		        	return Boolean.class;
		        else
		        	return String.class;
		    }
		};
		return tablaModelo;
	}
	
	public DefaultTableModel getListadoAlumnos(String campo, String valor) {
		
		alumnosDAO = new AlumnosDAO();
		String titulo[] = {"Legajo", "Nombre", "Apellido", "DNI", "Dirección", "Teléfono", "E-mail", "Curso", "Sel."};
		String respuesta[][] = alumnosDAO.getAlumnos(campo, valor, true, "");
		Object cuerpo[][] = null;
		cantAlumnos = "0";
		if(respuesta != null) {
			
			cuerpo = new Object[respuesta.length][9];
			cantAlumnos = respuesta.length + "";
			
			for(int i = 0 ; i < respuesta.length ; i++) {
				
				cuerpo[i][0] = respuesta[i][0];
				cuerpo[i][1] = respuesta[i][1];
				cuerpo[i][2] = respuesta[i][2];
				cuerpo[i][3] = respuesta[i][3];
				cuerpo[i][4] = respuesta[i][4];
				cuerpo[i][5] = respuesta[i][5];
				cuerpo[i][6] = respuesta[i][6];
				cuerpo[i][7] = respuesta[i][7];
				cuerpo[i][8] = false;
			}
		} else
			cuerpo = null;
		
		DefaultTableModel tablaModelo = new DefaultTableModel(cuerpo, titulo){

			private static final long serialVersionUID = 1L;
			
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, false, true
			};
			
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
			
			public Class<?> getColumnClass(int column) {
				
		        if(column == 8)
		        	return Boolean.class;
		        else
		        	return String.class;
		    }
		};
		return tablaModelo;
	}
	
	public String [] getOrdenamiento() {
		
		return new String [] {"Legajo", "Nombre", "Apellido", "DNI", "Dirección", "Curso"};
	}
	
	public DefaultTableModel getTablaAlumnos(String campo, String valor, boolean estado, int pos) {
		
		alumnosDAO = new AlumnosDAO();
		String titulo[] = {"Legajo", "Nombre", "Apellido", "DNI", "Dirección", "Teléfono", "E-mail", "Curso", "Sel."};
		String ordenado[] = {"idAlumno", "nombre", "apellido", "dni", "dirección", "alumnos.idCurso"};
		String respuesta[][] = alumnosDAO.getAlumnos(campo, valor, estado, ordenado[pos]);
		Object cuerpo[][] = null;
		cantAlumnos = "0";
		if(respuesta != null) {
			
			cuerpo = new Object[respuesta.length][9];
			cantAlumnos = respuesta.length + "";
			
			for(int i = 0 ; i < respuesta.length ; i++) {
				
				cuerpo[i][0] = respuesta[i][0];
				cuerpo[i][1] = respuesta[i][1];
				cuerpo[i][2] = respuesta[i][2];
				cuerpo[i][3] = respuesta[i][3];
				cuerpo[i][4] = respuesta[i][4];
				cuerpo[i][5] = respuesta[i][5];
				cuerpo[i][6] = respuesta[i][6];
				cuerpo[i][7] = respuesta[i][7];
				cuerpo[i][8] = false;
			}
		} else
			cuerpo = null;
		
		DefaultTableModel tablaModelo = new DefaultTableModel(cuerpo, titulo){

			private static final long serialVersionUID = 1L;
			
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, false, true
			};
			
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
			
			public Class<?> getColumnClass(int column) {
				
		        if(column == 8)
		        	return Boolean.class;
		        else
		        	return String.class;
		    }
		};
		return tablaModelo;
	}

	public DefaultTableModel getTablaDias(int curso) {
		
		CursosDAO cursosDAO = new CursosDAO();
		DtosCurso dtosCurso = new DtosCurso();
		String titulo[] = {"", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sábado"};
		String horas[] = dtosCurso.getListadoHorarios();
		String duracion[] = new String [] {"0:00","0:30","1:00","1:30","2:00","2:30","3:00","3:30","4:00"};
		String respuesta[][] = cursosDAO.buscarDiasCurso(idCursos[curso]);
		Object cuerpo[][] = new Object[][] {{"Hora:","","","","","",""},{"Duración:","","","","","",""}};
		
		if(respuesta != null) {
			
			for(int i = 0 ; i < respuesta.length ; i++) {

				cuerpo[0][Integer.parseInt(respuesta[i][0])+1] = horas[Integer.parseInt(respuesta[i][1])];
				cuerpo[1][Integer.parseInt(respuesta[i][0])+1] = duracion[Integer.parseInt(respuesta[i][2])];
			}
		} else
			cuerpo = null;
		
		DefaultTableModel tablaModelo = new DefaultTableModel(cuerpo, titulo){

			private static final long serialVersionUID = 1L;
			
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, false, true
			};
			
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
			
			public Class<?> getColumnClass(int column) {
				
		        if(column == 8)
		        	return Boolean.class;
		        else
		        	return String.class;
		    }
		};
		return tablaModelo;
	}
	
	public boolean setNuevoAlumno() {
		
		alumnosDAO = new AlumnosDAO();
		return alumnosDAO.setAlumno();
	}
	
	public boolean setActualizarAlumno() { 
		
		alumnosDAO = new AlumnosDAO();
		return alumnosDAO.setActualizarAlumno();
	} 

	public void limpiarInformacion() {
		
		legajo = "";
		nombre = "";
		apellido = "";
		dni = "";
		fechaAño = "";
		fechaMes = "";
		fechaDia = "";
		telefono = "";
		direccion = "";
		email = "";
		idCurso = "";
		idPersona = "";
		fechaIngreso = "";
	}
	
	public void recuperarInformacionAlumno(String nroLegajo, boolean estadoAlumno) {
		
		alumnosDAO = new AlumnosDAO();
		String alumno[][] = alumnosDAO.getAlumnos("ID", nroLegajo, estadoAlumno, "");
		
		if(alumno.length > 0) {
			
			legajo = alumno[0][0];
			nombre =  alumno[0][1];
			apellido = alumno[0][2];
			dni = alumno[0][3];
			direccion = alumno[0][4];
			telefono = alumno[0][5];
			email = alumno[0][6];
			idCurso = alumno[0][10];
			estado = estadoAlumno;
			String[] fecha = alumno[0][12].split("-");
			fechaAño = fecha[0];
			fechaMes = fecha[1];
			fechaDia = fecha[2];
			idPersona = alumno[0][13];
			fechaIngreso = alumno[0][14];
		}
	}
	
	public int getCursoSeleccionado() {
		
		int i = 0;
		while(i < idCursos.length) {
	
			if(idCurso.equals(idCursos[i])) {

				break;
			}
			i++;
		}
		return i;
	}
	
	public String [] getListaCursos() {
		
		CursosDAO cursosDAO = new CursosDAO();
		String respuesta[][] = cursosDAO.getListado("");
		idCursos = new String[respuesta.length];
		nombreCursos = new String[respuesta.length];
		idProfesores = new String[respuesta.length];

		for(int i = 0 ; i < respuesta.length; i++) {
			
			idCursos[i] = respuesta[i][5];
			nombreCursos[i] = respuesta[i][0] + " " + respuesta[i][1] + " " + respuesta[i][2];
			idProfesores[i] = respuesta[i][7];
		}
		return nombreCursos;
	}
	
	public String [] getCriterio() {
		
		return new String [] {"Curso", "Docente"};
	}
		
	public String [] getListadoProfesores() {
		
		EmpleadosDAO empleadosDAO = new EmpleadosDAO();
		String matriz[][] = empleadosDAO.getEmpleados("Docente", true, "");
		String respuesta[] = new String[matriz.length];
		idProfesores = new String[matriz.length];
		for(int i = 0 ; i < matriz.length ; i++) {
			
			idProfesores[i] = matriz[i][0];
			respuesta[i] = matriz[i][1] + " " + matriz[i][2];
		}
		return respuesta;
	}

	public String [] getListadoValorCriterio(String criterio) {
		
		if(criterio.equals("Curso")) {
			
			return getListaCursos();
		}
		
		if(criterio.equals("Docente")) {
		
			return getListadoProfesores();
		}
		return null;
	}
	
	public String getIdValorCriterio(String criterio, int valor) {
		
		if(criterio.equals("Curso")) {
			
			return idCursos[valor];
		}
		
		if(criterio.equals("Docente")) {
		
			return idProfesores[valor];
		}
		return null;
	}
	
	public String checkInformacion(boolean checDNI) {
		
		String msg = "";
		PersonasDAO personasDAO = new PersonasDAO();
		
		if(nombre.length() < 3) {

			msg ="El nombre debe tener más de dos caracteres.";
		}else if(apellido.length() < 3) {
			
			msg ="El apellido debe tener más de dos caracteres.";
		}else if(dni.length() < 7 || !isNumeric(dni)) {
			
			msg ="Error en el formato del DNI (solamente números).";
		}else if(personasDAO.getDNIDuplicado(dni) && checDNI) {
			
			msg ="El DNI ya está siendo usado.";
		}else if(fechaAño.length() == 0 
				|| Integer.parseInt(fechaAño) < 1920) {
			
			msg ="Error en el formato del año.";
		}else if(fechaMes.length() == 0 
				|| Integer.parseInt(fechaMes) < 1 
				|| Integer.parseInt(fechaMes) > 12 ) {
			
			msg ="Error en el formato del mes.";
		}else if(fechaDia.length() == 0 
				|| Integer.parseInt(fechaDia) < 1 
				|| Integer.parseInt(fechaDia) > 31 ) {
			
			msg ="Error en el formato del día.";
		}else if(direccion.length() == 0) {
			
			msg ="La dirección no puede estar vacía.";
		}else if(telefono.length() == 0 || !isNumeric(telefono)) {
			
			msg ="Error en el formato del teléfono (solamente números).";
		}
		return msg;
	}
	
	private boolean isNumeric(String cadena) {
		
		try {
			
			Double.parseDouble(cadena);
			return true;
		} catch (NumberFormatException e){
			
			return false;
		}
	}
	
	public String getLegajo() {
		
		return legajo;
	}
	
	public void setLegajo(String legajo) {
		
		DtosAlumno.legajo = legajo;
	}
	
	public String getNombre() {
		
		return nombre;
	}
	
	public void setNombre(String nombre) {
		
		DtosAlumno.nombre = nombre;
	}
	
	public String getApellido() {
		
		return apellido;
	}
	
	public void setApellido(String apellido) {
		
		DtosAlumno.apellido = apellido;
	}
	
	public String getFechaAño() {
		
		return fechaAño;
	}
	
	public void setFechaAño(String fechaNacimientoAño) {
		
		DtosAlumno.fechaAño = fechaNacimientoAño;
	}
	
	public String getFechaMes() {
		
		return fechaMes;
	}
	
	public void setFechaMes(String fechaNacimientoMes) {
		
		DtosAlumno.fechaMes = fechaNacimientoMes;
	}
	
	public String getFechaDia() {
		
		return fechaDia;
	}
	
	public void setFechaDia(String fechaNacimientoDia) {
		
		DtosAlumno.fechaDia = fechaNacimientoDia;
	}
	
	public String getDni() {
		
		return dni;
	}
	
	public void setDni(String dni) {
		
		DtosAlumno.dni = dni;
	}
	
	public String getTelefono() {
		
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		
		DtosAlumno.telefono = telefono;
	}
	
	public String getDireccion() {
		
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		
		DtosAlumno.direccion = direccion;
	}
	
	public String getEmail() {
		
		return email;
	}
	
	public void setEmail(String email) {
		
		DtosAlumno.email = email;
	}

	public String [][] getHorariosCursos() {
		return horariosCursos;
	}

	public void setHorariosCursos(String [][] horariosCursos) {
		
		DtosAlumno.horariosCursos = horariosCursos;
	}

	public boolean getEstado() {
		
		return estado;
	}

	public void setEstado(boolean estado) {
		
		DtosAlumno.estado = estado;
	}

	public String getIdPersona() {
		
		return idPersona;
	}

	public void setIdPersona(String idPersona) {
		
		DtosAlumno.idPersona = idPersona;
	}

	public String getCantAlumnos() {
		
		return cantAlumnos;
	}

	public String getResultadoExamen() {
		
		return resultadoExamen;
	}

	public String getIdProfesor() {
		
		return idProfesor;
	}

	public String getTipoExamen() {
		
		return tipoExamen;
	}

	public void setTipoExamen(String tipoExamen) {
		
		DtosAlumno.tipoExamen = tipoExamen;
	}

	public String getFechaIngreso() {
		
		return fechaIngreso;
	}
}
