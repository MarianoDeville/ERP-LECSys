package modelo;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dao.CursosDAO;
import dao.EmpleadosDAO;

public class DtosEmpleado {
	
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
	private static String sector;
	private static String relacion;
	private static String cargo;
	private static String salario;
	private static String estado;
	private static String idPersona;
	private String respuesta[][];
	private String horarios[][];
	private String msgError;
	
	public boolean setHorarios(int granularidad, JTable tablaOcupacion) {//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		int cant = 0;
		boolean comienzo;
		boolean bandera = false;
		
		for(int i = 0; i < tablaOcupacion.getRowCount(); i++) {
			
			comienzo = false;
			
			for(int e = 0; e < tablaOcupacion.getColumnCount(); e++) {

				if(tablaOcupacion.getValueAt(i, e).equals("O ") && !comienzo) {
				
					comienzo = true;
					cant++;
				} 
				
				if(!tablaOcupacion.getValueAt(i, e).equals("O") && !tablaOcupacion.getValueAt(i, e).equals("O "))
					comienzo = false;
			}
		}
		horarios = new String [cant][3];
		int pos = -1;
		
		for(int i = 0; i < tablaOcupacion.getRowCount(); i++) {
		
			comienzo = false;
			
			for(int e = 0; e < tablaOcupacion.getColumnCount(); e++) {

				if(tablaOcupacion.getValueAt(i, e).equals("O ") && !comienzo) {
					
					pos++;
					comienzo = true;
					horarios[pos][0] = i + "";
					horarios[pos][1] = listaHorarios(granularidad)[e];
					cant = 0;
				}
		
				if(!tablaOcupacion.getValueAt(i, e).equals("O") && !tablaOcupacion.getValueAt(i, e).equals("O "))
					comienzo = false;
				
				if(comienzo) {

					cant++;
					horarios[pos][2] = cant + "";
				}
			}
		}
		EmpleadosDAO empleadoDAO = new EmpleadosDAO();
		bandera = empleadoDAO.setCronogramaDias(conviertoString(legajo), horarios, granularidad);
		return bandera;
	}

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
					case "CE ":
						buclesVaciado++;	
						break;
						
					case "FE":
					case "FE ":
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
	
	private String [] listaHorarios(int granularidad) {
		
		String listado[] = null;
		int incremento = 0;
		
		switch(granularidad) {
		
			case 0: 
				listado = new String[97];
				incremento = 10;
				break;
				
			case 1:
				listado = new String[65];
				incremento = 15;
				break;
				
			case 2:
				listado = new String[33];
				incremento = 30;
				break;	
				
			default:
				listado = new String[17];
				incremento = 60;
		}
		int hora = 7;
		int minutos = 0;
		listado[0] = "7:00";
		
		for(int i = 1; i < listado.length; i++) {
			
			if((minutos + incremento) < 60) {
				
				minutos += incremento;
			} else {
				
				minutos = 0;
				hora++;
			}
			String min = minutos == 0? "00":minutos+"";
			listado[i] = hora + ":" + min;
		}
		return listado;
	}
	
	public String [] getGranularidad() {
		
		return new String [] {"10 min.", "15 min.", "30 min.", "1 hora"};
	}
	
	private int conviertoString(String valor) {
		
		try {
			
			return Integer.parseInt(valor);
		} catch (Exception e) {
			
			return 0;
		}
	}
	
	public DefaultTableModel getHorarios(int granularidad) {//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		CursosDAO cursoDAO = new CursosDAO();
		EmpleadosDAO empleadosDAO = new EmpleadosDAO();
		boolean ocupado[][];
		
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		if(sector.equals("Docente")) {
			
			cursoDAO.getCronogramaDias(0, conviertoString(legajo), 100);
			ocupado = cursoDAO.getmatrizDiasHorarios();
		} else {
			
			empleadosDAO.getCronogramaDias(conviertoString(legajo));
			ocupado = empleadosDAO.getmatrizDiasHorarios();
			granularidad = empleadosDAO.getGranularidad();
		}
		
		String titulo[] = listaHorarios(granularidad);
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		String cronograma[][] = new String[6][titulo.length];

		for(int i = 0 ;i < 6 ;i++) {
			
			for(int e = 0 ; e< titulo.length ;e++) {

				if(ocupado != null) {
					
					if(ocupado[i][e]) {
						
						cronograma[i][e] = "O";
						
						if(e > 0 && e < titulo.length - 1) { 
						
							if(!ocupado[i][e-1] || !ocupado[i][e+1])
								cronograma[i][e] = "O ";
						}
					} else {
						
						cronograma[i][e] = " ";
					}
				} else {
					
					cronograma[i][e] = " ";
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
	}//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public DefaultTableModel getListadoEmpleados(String tipo, String filtro) {
		
		EmpleadosDAO empleados = new EmpleadosDAO();
		String titulo[] = {"Leg.", "Apellido, nombre", "Sector", "Cargo"};
		respuesta =empleados.getEmpleados(tipo, true, filtro);
		String cuerpo[][]=null;
		
		if(respuesta != null) {
			
			cuerpo = new String[respuesta.length][4];
			
			for(int i = 0 ; i < respuesta.length ; i++) {
				
				cuerpo[i][0] = respuesta[i][0];
				cuerpo[i][1] = respuesta[i][2] + ", " + respuesta[i][1];
				cuerpo[i][2] = respuesta[i][7];
				cuerpo[i][3] = respuesta[i][8];
			}
		} else
			cuerpo = null;
		
		DefaultTableModel tablaModelo = new DefaultTableModel(cuerpo, titulo){

			private static final long serialVersionUID = 1L;
			
			boolean[] columnEditables = new boolean[] {
					false, false, false, false
			};
			
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		return tablaModelo;
	}
	
	public void getInformacionEmpleado(int pos) {
		
		if(respuesta.length > 0) {
		
			legajo = respuesta[pos][0];
			nombre =  respuesta[pos][1];
			apellido = respuesta[pos][2];
			dni = respuesta[pos][3];
			direccion = respuesta[pos][4];
			telefono = respuesta[pos][5];
			email = respuesta[pos][6];
			sector = respuesta[pos][7];
			cargo = respuesta[pos][8];
			salario = respuesta[pos][9];
			relacion = respuesta[pos][10];
			estado =  respuesta[pos][11];
			String[] fecha = respuesta[pos][12].split("-");
			fechaNacimientoAño = fecha[0];
			fechaNacimientoMes = fecha[1];
			fechaNacimientoDia = fecha[2];
			idPersona = respuesta[pos][13];
		}
	}

	public void limpiarInformacion() {
		
		legajo = "";
		nombre = "";
		apellido = "";
		dni = "";
		fechaNacimientoAño = "";
		fechaNacimientoDia = "";
		fechaNacimientoMes = "";
		telefono = "";
		direccion = "";
		email = "";
		sector = "";
		relacion = "";
		cargo = "";
		salario = "";
	}
	
	public boolean setNuevoEmpleado() {
		
		boolean bandera = false;
		EmpleadosDAO empleadoDAO = new EmpleadosDAO();
		bandera = empleadoDAO.setEmpleado();
		limpiarInformacion();
		return bandera;
	}
	
	public boolean setActualizarEmpleado() {
		
		boolean bandera = false;
		EmpleadosDAO empleadoDAO = new EmpleadosDAO();
		bandera = empleadoDAO.setActualizarEmpleado();
		limpiarInformacion();
		return bandera;
	}

	public String [] getFiltro() {
		
		return new String [] {"Todos","Docente","Administrativo","Recepcionista","Personal limpieza"};
	}
	
	public DefaultTableModel getTablaEmpleados(String tipo, boolean estado, String filtro) {
		
		EmpleadosDAO empleados = new EmpleadosDAO();
		String titulo[] = {"Leg.", "Nombre", "Apellido", "DNI", "Dirección", "Teléfono", "E-mail", "Sector", "Cargo", "Tipo", "Sel."};
		respuesta = empleados.getEmpleados(tipo, estado, filtro);
		Object cuerpo[][]=null;
		
		if(respuesta != null) {
			
			cuerpo = new Object[respuesta.length][11];
			
			for(int i = 0 ; i < respuesta.length ; i++) {
				
				cuerpo[i][0] = respuesta[i][0];
				cuerpo[i][1] = respuesta[i][1];
				cuerpo[i][2] = respuesta[i][2];
				cuerpo[i][3] = respuesta[i][3];
				cuerpo[i][4] = respuesta[i][4];
				cuerpo[i][5] = respuesta[i][5];
				cuerpo[i][6] = respuesta[i][6];
				cuerpo[i][7] = respuesta[i][7];
				cuerpo[i][8] = respuesta[i][8];
				cuerpo[i][9] = respuesta[i][10];
				cuerpo[i][10] = false;
			}
		} else
			cuerpo = null;
		
		DefaultTableModel tablaModelo = new DefaultTableModel(cuerpo, titulo){

			private static final long serialVersionUID = 1L;
			
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, false, false, false, true
			};
			
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
			
			public Class<?> getColumnClass(int column) {
				
		        if(column == 10)
		        	return Boolean.class;
		        else
		        	return String.class;
		    }
		};
		return tablaModelo;
	}
	
	public String [] getListaSectores() {
		
		return new String [] {"Docente","Administrativo","Recepcionista","Personal limpieza"};
	}
	
	public String [] getListaTipos () {
		
		return new String [] {"Relación de dependencia","Monotributista"};
	}
	
	public String checkInformacion() {
		
		if(nombre.length() < 3) {

			return "El nombre debe tener más de dos caracteres.";
		}else if(apellido.length() < 3) {
			
			return "El apellido debe tener más de dos caracteres.";
		}else if(dni.length() < 7 || !isNumeric(dni)) {
			
			return "Error en el formato del DNI (solamente números).";
		}else if(fechaNacimientoAño.length() == 0 || Integer.parseInt(fechaNacimientoAño) < 1940) {
			
			return "Error en el formato del año.";
		}else if(fechaNacimientoMes.length() == 0 || Integer.parseInt(fechaNacimientoMes) < 1 || Integer.parseInt(fechaNacimientoMes) > 12 ) {
			
			return "Error en el formato del mes.";
		}else if(fechaNacimientoDia.length() == 0 || Integer.parseInt(fechaNacimientoDia) < 1 || Integer.parseInt(fechaNacimientoDia) > 31 ) {
			
			return "Error en el formato del día.";
		}else if(direccion.length() == 0) {
			
			return "La dirección no puede estar vacía.";
		}else if(telefono.length() == 0 || !isNumeric(telefono)) {
			
			return "Error en el formato del teléfono (solamente números).";
		}else if(cargo.length() == 0) {
			
			return "El cargo no puede estar vacío.";
		}else if(salario.length() == 0 || !isNumeric(salario)) {
			
			return "Error en el formato del salario (solamente números).";
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

	public String getNombre() {
		
		return nombre;
	}

	public void setNombre(String nombre) {
		
		DtosEmpleado.nombre = nombre;
	}

	public String getApellido() {
		
		return apellido;
	}

	public void setApellido(String apellido) {
		
		DtosEmpleado.apellido = apellido;
	}

	public String getDni() {
		
		return dni;
	}

	public void setDni(String dni) {
		
		DtosEmpleado.dni = dni;
	}

	public String getTelefono() {
		
		return telefono;
	}

	public void setTelefono(String telefono) {
		
		DtosEmpleado.telefono = telefono;
	}

	public String getDireccion() {
		
		return direccion;
	}

	public void setDireccion(String direccion) {
		
		DtosEmpleado.direccion = direccion;
	}

	public String getEmail() {
		
		return email;
	}

	public void setEmail(String email) {
		
		DtosEmpleado.email = email;
	}

	public String getSalario() {
		
		return salario;
	}

	public void setSalario(String salario) {
		
		DtosEmpleado.salario = salario;
	}

	public String getRelacion() {
		
		return relacion;
	}

	public void setRelacion(String relacion) {
		
		DtosEmpleado.relacion = relacion;
	}

	public String getSector() {
		
		return sector;
	}

	public void setSector(String sector) {
		
		DtosEmpleado.sector = sector;
	}

	public String getCargo() {
		
		return cargo;
	}

	public void setCargo(String cargo) {
		
		DtosEmpleado.cargo = cargo;
	}

	public String getFechaNacimientoMes() {
		
		return fechaNacimientoMes;
	}

	public void setFechaNacimientoMes(String fechaNacimientoMes) {
		
		DtosEmpleado.fechaNacimientoMes = fechaNacimientoMes;
	}

	public String getFechaNacimientoDia() {
		
		return fechaNacimientoDia;
	}

	public void setFechaNacimientoDia(String fechaNacimientoDia) {
		
		DtosEmpleado.fechaNacimientoDia = fechaNacimientoDia;
	}

	public String getFechaNacimientoAño() {
		
		return fechaNacimientoAño;
	}

	public void setFechaNacimientoAño(String fechaNacimientoAño) {
		
		DtosEmpleado.fechaNacimientoAño = fechaNacimientoAño;
	}

	public String getLegajo() {
		
		return legajo;
	}

	public boolean getEstado() {
		
		return estado.equals("Activo")? true:false;
	}

	public void setEstado(String estado) {
		
		DtosEmpleado.estado = estado;
	}

	public String getIdPersona() {
		
		return idPersona;
	}

	public String getCantidadHoras(JTable tablaOcupacion, int granularidad) {
		
		int cant = 0;
		int tiempo[] = new int [] {6, 4, 2, 1};
		boolean bandera = false;
		
		for(int i = 0; i < tablaOcupacion.getRowCount(); i++) {

			for(int e = 0; e < tablaOcupacion.getColumnCount(); e++) {
				
				if(tablaOcupacion.getValueAt(i, e).equals("O"))
					cant++;
					
				if(tablaOcupacion.getValueAt(i, e).equals("O ") ) {
					
					bandera = !bandera;
					
					if(bandera)
						cant++;
				}
			}
		}

		int resto = cant % tiempo[granularidad];
		String cantidadHoras = (cant / tiempo[granularidad]) + ":";
		cantidadHoras += resto > 0 ?  resto * 60 / tiempo[granularidad]:"00";
		return cantidadHoras;
	}
}
