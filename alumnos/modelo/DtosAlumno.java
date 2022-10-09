package modelo;

import javax.swing.table.DefaultTableModel;
import dao.AlumnosDAO;
import dao.CursosDAO;
import dao.EmpleadosDAO;
import dao.PeronasDAO;

public class DtosAlumno {
	
	private static String legajo;
	private static String nombre;
	private static String apellido;
	private static String dni;
	private static String fechaNacimientoAño;
	private static String fechaNacimientoMes;
	private static String fechaNacimientoDia;
	private static String telefono;
	private static String direccion;
	private static String email;
	private static boolean estado;
	private static String curso;
	private static String idCurso;
	private static String idPersona;
	private static String [] idCursos;
	private static String [] idProfesores;
	private static String [][] horariosCursos;
	private static String cantAlumnos; 
	
	public DefaultTableModel getListadoAlumnos(String campo, String valor) {
		
		AlumnosDAO alumnos = new AlumnosDAO();
		String titulo[] = {"Legajo", "Nombre", "Apellido", "DNI", "Dirección", "Teléfono", "E-mail", "Curso"};
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
			}
		} else
			cuerpo = null;
		
		DefaultTableModel tablaModelo = new DefaultTableModel(cuerpo, titulo);
		return tablaModelo;
	}
	
	public String [] getOrdenamiento() {
		
		return new String [] {"Legajo", "Nombre", "Apellido", "DNI", "Dirección", "Curso"};
	}
	
	public DefaultTableModel getTablaAlumnos(String campo, String valor, boolean estado, int pos) {
		
		AlumnosDAO alumnos = new AlumnosDAO();
		String titulo[] = {"Legajo", "Nombre", "Apellido", "DNI", "Dirección", "Teléfono", "E-mail", "Curso", "Sel."};
		String ordenado[] = {"idAlumno", "nombre", "apellido", "dni", "dirección", "alumnos.idCurso"};
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
		String titulo[] = {"", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sábado"};
		String horas[] = dtosCurso.getListadoHorarios();
		String duracion[] = new String [] {"0:00","0:30","1:00","1:30","2:00","2:30","3:00","3:30","4:00"};
		String respuesta[][]=cursosDAO.buscarDiasCurso(idCursos[curso]);
		Object cuerpo[][]= new Object[][] {{"Hora:","","","","","",""},{"Duración:","","","","","",""}};
		
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
		fechaNacimientoAño = "";
		fechaNacimientoMes = "";
		fechaNacimientoDia = "";
		telefono = "";
		direccion = "";
		email = "";
		curso = "";
		idPersona = "";
	}
	
	public void recuperarInformacionAlumno(String nroLegajo) {
		
		AlumnosDAO alumnosDAO = new AlumnosDAO();
		String alumno[][] = alumnosDAO.getAlumnos("ID", nroLegajo, true, "");
		
		if(alumno.length > 0) {
			
			legajo = alumno[0][0];
			nombre =  alumno[0][1];
			apellido = alumno[0][2];
			dni = alumno[0][3];
			direccion = alumno[0][4];
			telefono = alumno[0][5];
			email = alumno[0][6];
			idCurso = alumno[0][10];
			estado = alumno[0][11].contentEquals("Activo")? true: false;
			String[] fecha = alumno[0][12].split("-");
			fechaNacimientoAño = fecha[0];
			fechaNacimientoMes = fecha[1];
			fechaNacimientoDia = fecha[2];
			idPersona = alumno[0][13];
		}
	}
	
	public int getCursoSeleccionado() {
		
		int i = 0;
		while(i < idCurso.length()) {
			
			if(idCurso.contentEquals(idCursos[i])) {

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
		String [] nombreCursos = new String[respuesta.length];
		
		for(int i = 0 ; i < respuesta.length; i++) {
			
			idCursos[i] = respuesta[i][5];
			nombreCursos[i] = respuesta[i][0] + " " + respuesta[i][1] + " " + respuesta[i][2];
		}
		return nombreCursos;
	}
	
	public String[] getCriterio() {
		
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
		
		if(criterio.contentEquals("Curso")) {
			
			return getListaCursos();
		}
		
		if(criterio.contentEquals("Docente")) {
		
			return getListadoProfesores();
		}
		return null;
	}
	
	public String getIdValorCriterio(String criterio, int valor) {
		
		if(criterio.contentEquals("Curso")) {
			
			return idCursos[valor];
		}
		
		if(criterio.contentEquals("Docente")) {
		
			return idProfesores[valor];
		}
		return null;
	}
	
	public String checkInformacion(boolean checDNI) {
		
		String msg = "";
		PeronasDAO personasDAO = new PeronasDAO();
		
		if(nombre.length() < 3) {

			msg ="El nombre debe tener más de dos caracteres.";
		}else if(apellido.length() < 3) {
			
			msg ="El apellido debe tener más de dos caracteres.";
		}else if(dni.length() < 7 || !isNumeric(dni)) {
			
			msg ="Error en el formato del DNI (solamente números).";
		}else if(personasDAO.getDNIDuplicado(dni) && checDNI) {
			
			msg ="El DNI ya está siendo usado.";
		}else if(fechaNacimientoAño.length() == 0 
				|| Integer.parseInt(fechaNacimientoAño) < 1920) {
			
			msg ="Error en el formato del año.";
		}else if(fechaNacimientoMes.length() == 0 
				|| Integer.parseInt(fechaNacimientoMes) < 1 
				|| Integer.parseInt(fechaNacimientoMes) > 12 ) {
			
			msg ="Error en el formato del mes.";
		}else if(fechaNacimientoDia.length() == 0 
				|| Integer.parseInt(fechaNacimientoDia) < 1 
				|| Integer.parseInt(fechaNacimientoDia) > 31 ) {
			
			msg ="Error en el formato del día.";
		}else if(direccion.length() == 0) {
			
			msg ="La dirección no puede estar vacía.";
		}else if(telefono.length() == 0 || !isNumeric(telefono)) {
			
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
	
	public String getFechaNacimientoAño() {
		
		return fechaNacimientoAño;
	}
	
	public void setFechaNacimientoAño(String fechaNacimientoAño) {
		
		DtosAlumno.fechaNacimientoAño = fechaNacimientoAño;
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
	
	public String getIDCurso() {
		
		return idCurso;
	}
	
	public void setCurso(String curso) {
		
		DtosAlumno.idCurso = curso;
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
	public String getCurso() {
		
		return curso;
	}

	public void setCurso(int curso) {
		
		DtosAlumno.curso = idCursos[curso];
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
