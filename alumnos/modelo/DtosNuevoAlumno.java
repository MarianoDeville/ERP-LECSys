package modelo;

import dao.AlumnosDAO;
import dao.CursosDAO;
import dao.PeronasDAO;

public class DtosNuevoAlumno {
	
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
	private String [] idCursos;

	public boolean setNuevoAlumno() {
		
		AlumnosDAO alumnosDAO = new AlumnosDAO();
		return alumnosDAO.setAlumno();
	}

	public void limpiarInformacion() {
		
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
	}
	public String [] getListaCursos() {
		
		CursosDAO cursosDAO = new CursosDAO();
		String [][] respuesta = cursosDAO.getListado();
		idCursos = new String[respuesta.length];
		String [] nombreCursos = new String[respuesta.length];
		
		for(int i = 0 ; i < respuesta.length; i++) {
			
			idCursos[i] = respuesta[i][5];
			nombreCursos[i] = respuesta[i][0] + " " + respuesta[i][1] + " " + respuesta[i][2];
		}
		return nombreCursos;
	}
	
	public String checkInformacion() {
		
		String msg = "";
		PeronasDAO personasDAO = new PeronasDAO();
		
		if(nombre.length() < 3) {

			msg ="El nombre debe tener m�s de dos caracteres.";
		}else if(apellido.length() < 3) {
			
			msg ="El apellido debe tener m�s de dos caracteres.";
		}else if(dni.length() < 7 || !isNumeric(dni)) {
			
			msg ="Error en el formato del DNI (solamente n�meros).";
		}else if(personasDAO.getDNIDuplicado(dni)) {
			
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
	
	private static boolean isNumeric(String cadena) {
		
		try {
			
			Double.parseDouble(cadena);
			return true;
		} catch (NumberFormatException e){
			
			return false;
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		DtosNuevoAlumno.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		DtosNuevoAlumno.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		DtosNuevoAlumno.dni = dni;
	}

	public String getFechaNacimientoA�o() {
		return fechaNacimientoA�o;
	}

	public void setFechaNacimientoA�o(String fechaNacimientoA�o) {
		DtosNuevoAlumno.fechaNacimientoA�o = fechaNacimientoA�o;
	}

	public String getFechaNacimientoMes() {
		return fechaNacimientoMes;
	}

	public void setFechaNacimientoMes(String fechaNacimientoMes) {
		DtosNuevoAlumno.fechaNacimientoMes = fechaNacimientoMes;
	}

	public String getFechaNacimientoDia() {
		return fechaNacimientoDia;
	}

	public void setFechaNacimientoDia(String fechaNacimientoDia) {
		DtosNuevoAlumno.fechaNacimientoDia = fechaNacimientoDia;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		DtosNuevoAlumno.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		DtosNuevoAlumno.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		DtosNuevoAlumno.email = email;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(int curso) {
		DtosNuevoAlumno.curso = idCursos[curso];
	}
}
