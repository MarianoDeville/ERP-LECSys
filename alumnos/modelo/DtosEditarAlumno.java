package modelo;

import javax.swing.JTextField;

public class DtosEditarAlumno {
	
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
	private static String curso;
	private static String [] idCursos;
	
	
	
	
	public String getLegajo() {
		
		return legajo;
	}
	
	public void setLegajo(String legajo) {
		
		DtosEditarAlumno.legajo = legajo;
	}
	
	public String getNombre() {
		
		return nombre;
	}
	
	public void setNombre(String nombre) {
		
		DtosEditarAlumno.nombre = nombre;
	}
	
	public String getApellido() {
		
		return apellido;
	}
	
	public void setApellido(String apellido) {
		
		DtosEditarAlumno.apellido = apellido;
	}
	
	public String getFechaNacimientoAño() {
		
		return fechaNacimientoAño;
	}
	
	public void setFechaNacimientoAño(String fechaNacimientoAño) {
		
		DtosEditarAlumno.fechaNacimientoAño = fechaNacimientoAño;
	}
	
	public String getFechaNacimientoMes() {
		
		return fechaNacimientoMes;
	}
	
	public void setFechaNacimientoMes(String fechaNacimientoMes) {
		
		DtosEditarAlumno.fechaNacimientoMes = fechaNacimientoMes;
	}
	
	public String getFechaNacimientoDia() {
		
		return fechaNacimientoDia;
	}
	
	public void setFechaNacimientoDia(String fechaNacimientoDia) {
		
		DtosEditarAlumno.fechaNacimientoDia = fechaNacimientoDia;
	}
	
	public String getDni() {
		
		return dni;
	}
	
	public void setDni(String dni) {
		
		DtosEditarAlumno.dni = dni;
	}
	
	public String getTelefono() {
		
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		
		DtosEditarAlumno.telefono = telefono;
	}
	
	public String getDireccion() {
		
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		
		DtosEditarAlumno.direccion = direccion;
	}
	
	public String getEmail() {
		
		return email;
	}
	
	public void setEmail(String email) {
		
		DtosEditarAlumno.email = email;
	}
	
	public String getCurso() {
		
		return curso;
	}
	
	public void setCurso(String curso) {
		
		DtosEditarAlumno.curso = curso;
	}

}
