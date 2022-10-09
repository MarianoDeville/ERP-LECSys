/*****************************************************************************************************************************************************************/
//										LISTADO DE M�TODOS
/*---------------------------------------------------------------------------------------------------------------------------------------------------------------*/
//	public int getAsistencia(String campo, int fila)
//	public int getCursoSeleccionado()
//	public boolean getEstado()
//	public DefaultTableModel getTablaAsistencia(int cursoSeleccionado)
//	public DefaultTableModel getListadoAlumnos(String campo, String valor)
//	public DefaultTableModel getTablaAlumnos(String campo, String valor, boolean estado, int pos)
//	public DefaultTableModel getTablaDias(int curso)
//	public String getFechaActual(boolean formato)
//	public String getIdValorCriterio(String criterio, int valor)
//	public String getLegajo()
//	public String getNombre()
//	public String getApellido()
//	public String getFechaNacimientoA�o()
//	public String getFechaNacimientoMes()
//	public String getFechaNacimientoDia()
//	public String getDni()
//	public String getTelefono()
//	public String getDireccion()
//	public String getEmail()
//	public String getIDCurso()
//	public String getCurso()
//	public String getNombreCurso()
//	public String getIdPersona()
//	public String getCantAlumnos()
//	public String [] getOrdenamiento()
//	public String [] getListaCursos()
//	public String [] getCriterio() 
//	public String [] getListadoProfesores()
//	public String [] getListadoValorCriterio(String criterio)
//	public String [][] getHorariosCursos()
//	public void setTablaAsistencia(int fila, int columna, boolean valor)
//	public void setLegajo(String legajo)
//	public void setNombre(String nombre)
//	public void setApellido(String apellido)
//	public void setFechaNacimientoA�o(String fechaNacimientoA�o)
//	public void setFechaNacimientoMes(String fechaNacimientoMes)
//	public void setFechaNacimientoDia(String fechaNacimientoDia)
//	public void setDni(String dni)
//	public void setTelefono(String telefono)
//	public void setDireccion(String direccion)
//	public void setEmail(String email)
//	public void setIdCurso(String curso)
//	public void setHorariosCursos(String [][] horariosCursos)
//	public void setEstado(boolean estado)
//	public void setCurso(int curso)
//	public void setIdPersona(String idPersona)
//	public boolean setNuevoAlumno() 
//	public boolean setActualizarAlumno()
//	public void limpiarInformacion()
//	public void recuperarInformacionAlumno(String nroLegajo, boolean estadoAlumno)
//	public boolean guardoAsistencia()	
//	public String checkInformacion(boolean checDNI)
//	private boolean isNumeric(String cadena)
/*****************************************************************************************************************************************************************/
package modelo;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.table.DefaultTableModel;
import dao.AlumnosDAO;
import dao.AsistenciaDAO;
import dao.CursosDAO;
import dao.EmpleadosDAO;
import dao.PeronasDAO;

public class DtosAlumno {
	
	private static boolean estado;
	private static String legajo;
	private static String nombre;
	private static String apellido;
	private static String dni;
	private static String fechaNacimientoA�o;
	private static String fechaNacimientoMes;
	private static String fechaNacimientoDia;
	private static String telefono;
	private static String direccion;
	private static String email;
	private static String curso;
	private static String idCurso;
	private static String idPersona;
	private static String cantAlumnos;
	private static String [] idCursos;
	private static String [] nombreCursos;
	private static String [] idProfesores;
	private static String [][] horariosCursos;
	private static Object [][] tablaAsistencia;


	public void setCurso(int curso) {
		
		DtosAlumno.curso = idCursos[curso];
	}
	
	public String getCurso() {
		
		return curso;
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
		AsistenciaDAO asistenciaDAO = new AsistenciaDAO();
		
		for(int fila = 0 ; fila < tablaAsistencia.length ; fila++) {
			
			if(bandera) {
				
				bandera = asistenciaDAO.setAsistencia(fila);
			} else {
				
				asistenciaDAO.setAsistencia(fila);
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
		
		AlumnosDAO alumnos = new AlumnosDAO();
		String titulo[] = {"Legajo", "Nombre", "Apellido", "Presente", "Tarde"};
		String respuesta[][]=alumnos.getAlumnos("Curso", idCursos[cursoSeleccionado], true, "");
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
		
		AlumnosDAO alumnos = new AlumnosDAO();
		String titulo[] = {"Legajo", "Nombre", "Apellido", "DNI", "Direcci�n", "Tel�fono", "E-mail", "Curso", "Sel."};
		String respuesta[][]=alumnos.getAlumnos(campo, valor, true, "");
		Object cuerpo[][]=null;
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
		
		return new String [] {"Legajo", "Nombre", "Apellido", "DNI", "Direcci�n", "Curso"};
	}
	
	public DefaultTableModel getTablaAlumnos(String campo, String valor, boolean estado, int pos) {
		
		AlumnosDAO alumnos = new AlumnosDAO();
		String titulo[] = {"Legajo", "Nombre", "Apellido", "DNI", "Direcci�n", "Tel�fono", "E-mail", "Curso", "Sel."};
		String ordenado[] = {"idAlumno", "nombre", "apellido", "dni", "direcci�n", "alumnos.idCurso"};
		String respuesta[][]=alumnos.getAlumnos(campo, valor, estado, ordenado[pos]);
		Object cuerpo[][]=null;
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
		String titulo[] = {"", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "S�bado"};
		String horas[] = dtosCurso.getListadoHorarios();
		String duracion[] = new String [] {"0:00","0:30","1:00","1:30","2:00","2:30","3:00","3:30","4:00"};
		String respuesta[][]=cursosDAO.buscarDiasCurso(idCursos[curso]);
		Object cuerpo[][]= new Object[][] {{"Hora:","","","","","",""},{"Duraci�n:","","","","","",""}};
		
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
		
		AlumnosDAO alumnosDAO = new AlumnosDAO();
		return alumnosDAO.setAlumno();
	}
	
	public boolean setActualizarAlumno() { 
		
		AlumnosDAO alumnosDAO = new AlumnosDAO();
		return alumnosDAO.setActualizarAlumno();
	} 

	public void limpiarInformacion() {
		
		legajo = "";
		nombre = "";
		apellido = "";
		dni = "";
		fechaNacimientoA�o = "";
		fechaNacimientoMes = "";
		fechaNacimientoDia = "";
		telefono = "";
		direccion = "";
		email = "";
		curso = "";
		idPersona = "";
	}
	
	public void recuperarInformacionAlumno(String nroLegajo, boolean estadoAlumno) {
		
		AlumnosDAO alumnosDAO = new AlumnosDAO();
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
			fechaNacimientoA�o = fecha[0];
			fechaNacimientoMes = fecha[1];
			fechaNacimientoDia = fecha[2];
			idPersona = alumno[0][13];
		}
	}
	
	public int getCursoSeleccionado() {
		
		int i = 0;
		while(i < idCurso.length()) {
			
			if(idCurso.equals(idCursos[i])) {

				break;
			}
			i++;
		}
		return i;
	}
	
	public String [] getListaCursos() {
		
		CursosDAO cursosDAO = new CursosDAO();
		String [][] respuesta = cursosDAO.getListado("");
		idCursos = new String[respuesta.length];
		nombreCursos = new String[respuesta.length];
		
		for(int i = 0 ; i < respuesta.length; i++) {
			
			idCursos[i] = respuesta[i][5];
			nombreCursos[i] = respuesta[i][0] + " " + respuesta[i][1] + " " + respuesta[i][2];
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
		PeronasDAO personasDAO = new PeronasDAO();
		
		if(nombre.length() < 3) {

			msg ="El nombre debe tener m�s de dos caracteres.";
		}else if(apellido.length() < 3) {
			
			msg ="El apellido debe tener m�s de dos caracteres.";
		}else if(dni.length() < 7 || !isNumeric(dni)) {
			
			msg ="Error en el formato del DNI (solamente n�meros).";
		}else if(personasDAO.getDNIDuplicado(dni) && checDNI) {
			
			msg ="El DNI ya est� siendo usado.";
		}else if(fechaNacimientoA�o.length() == 0 
				|| Integer.parseInt(fechaNacimientoA�o) < 1920) {
			
			msg ="Error en el formato del a�o.";
		}else if(fechaNacimientoMes.length() == 0 
				|| Integer.parseInt(fechaNacimientoMes) < 1 
				|| Integer.parseInt(fechaNacimientoMes) > 12 ) {
			
			msg ="Error en el formato del mes.";
		}else if(fechaNacimientoDia.length() == 0 
				|| Integer.parseInt(fechaNacimientoDia) < 1 
				|| Integer.parseInt(fechaNacimientoDia) > 31 ) {
			
			msg ="Error en el formato del d�a.";
		}else if(direccion.length() == 0) {
			
			msg ="La direcci�n no puede estar vac�a.";
		}else if(telefono.length() == 0 || !isNumeric(telefono)) {
			
			msg ="Error en el formato del tel�fono (solamente n�meros).";
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
	
	public String getFechaNacimientoA�o() {
		
		return fechaNacimientoA�o;
	}
	
	public void setFechaNacimientoA�o(String fechaNacimientoA�o) {
		
		DtosAlumno.fechaNacimientoA�o = fechaNacimientoA�o;
	}
	
	public String getFechaNacimientoMes() {
		
		return fechaNacimientoMes;
	}
	
	public void setFechaNacimientoMes(String fechaNacimientoMes) {
		
		DtosAlumno.fechaNacimientoMes = fechaNacimientoMes;
	}
	
	public String getFechaNacimientoDia() {
		
		return fechaNacimientoDia;
	}
	
	public void setFechaNacimientoDia(String fechaNacimientoDia) {
		
		DtosAlumno.fechaNacimientoDia = fechaNacimientoDia;
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
}