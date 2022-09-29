package modelo;

import dao.EmpleadosDAO;

public class DtosNuevoEmpleado {
	
	private static String nombre;
	private static String apellido;
	private static String dni;
	private static String fechaNacimientoAño;
	private static String fechaNacimientoMes;
	private static String fechaNacimientoDia;
	private static String telefono;
	private static String direccion;
	private static String email;
	private static String sector;
	private static String relacion;
	private static String cargo;
	private static String salario;
	
	public void limpiarInformacion() {
		
		nombre = null;
		apellido = null;
		dni = null;
		fechaNacimientoAño = null;
		fechaNacimientoDia = null;
		fechaNacimientoMes = null;
		telefono = null;
		direccion = null;
		email = null;
		sector = null;
		relacion = null;
		cargo = null;
		salario = null;
	}
	public boolean setNuevoEmpleado () {
		
		boolean bandera = false;
		EmpleadosDAO empleadoDAO = new EmpleadosDAO();
		bandera = empleadoDAO.setEmpleado();
		limpiarInformacion();
		return bandera;
	}

	public String [] getSectores() {
		
		return new String [] {"Docente","Administrativo","Recepcionista","Personal limpieza"};
	}
	
	public String [] getTipo () {
		
		return new String [] {"Monotributista","Relación de dependencia"};
	}
	
	public String checkInformacion() {
		
		String msg = "";
		
		if(nombre.length() < 3) {

			msg ="El nombre debe tener más de dos caracteres.";
		}else if(apellido.length() < 3) {
			
			msg ="El apellido debe tener más de dos caracteres.";
		}else if(dni.length() < 7 || !isNumeric(dni)) {
			
			msg ="Error en el formato del DNI (solamente números).";
		}else if(fechaNacimientoAño.length() == 0 || Integer.parseInt(fechaNacimientoAño) < 1940) {
			
			msg ="Error en el formato del año.";
		}else if(fechaNacimientoMes.length() == 0 || Integer.parseInt(fechaNacimientoMes) < 1 || Integer.parseInt(fechaNacimientoMes) > 12 ) {
			
			msg ="Error en el formato del mes.";
		}else if(fechaNacimientoDia.length() == 0 || Integer.parseInt(fechaNacimientoDia) < 1 || Integer.parseInt(fechaNacimientoDia) > 31 ) {
			
			msg ="Error en el formato del día.";
		}else if(direccion.length() == 0) {
			
			msg ="La dirección no puede estar vacía.";
		}else if(telefono.length() == 0 || !isNumeric(telefono)) {
			
			msg ="Error en el formato del teléfono (solamente números).";
		}else if(cargo.length() == 0) {
			
			msg ="El cargo no puede estar vacío.";
		}else if(salario.length() == 0 || !isNumeric(salario)) {
			
			msg ="Error en el formato del salario (solamente números).";
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
		DtosNuevoEmpleado.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		DtosNuevoEmpleado.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		DtosNuevoEmpleado.dni = dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		DtosNuevoEmpleado.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		DtosNuevoEmpleado.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		DtosNuevoEmpleado.email = email;
	}

	public String getSalario() {
		return salario;
	}

	public void setSalario(String salario) {
		DtosNuevoEmpleado.salario = salario;
	}

	public String getRelacion() {
		return relacion;
	}

	public void setRelacion(String relacion) {
		DtosNuevoEmpleado.relacion = relacion;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		DtosNuevoEmpleado.sector = sector;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		DtosNuevoEmpleado.cargo = cargo;
	}

	public String getFechaNacimientoMes() {
		return fechaNacimientoMes;
	}

	public void setFechaNacimientoMes(String fechaNacimientoMes) {
		DtosNuevoEmpleado.fechaNacimientoMes = fechaNacimientoMes;
	}

	public String getFechaNacimientoDia() {
		return fechaNacimientoDia;
	}

	public void setFechaNacimientoDia(String fechaNacimientoDia) {
		DtosNuevoEmpleado.fechaNacimientoDia = fechaNacimientoDia;
	}

	public String getFechaNacimientoAño() {
		return fechaNacimientoAño;
	}

	public void setFechaNacimientoAño(String fechaNacimientoAño) {
		DtosNuevoEmpleado.fechaNacimientoAño = fechaNacimientoAño;
	}
}
