/*****************************************************************************************************************************************************************/
//										LISTADO DE MÉTODOS
/*---------------------------------------------------------------------------------------------------------------------------------------------------------------*/
//	public int getMontoTotal()
//	public String getCalculoCobro()
//	public void setCantidadCuotasSeleccionadas(String cantidad)
//	public String setRecargoMora(String recargo)
//	public String [] getListadoConceptos()
//	public int getIntegrantes()
//	public TableModel getTablaDeudores(String busqueda, boolean pagoAdelantado)
//	public void SetIntegrantes(String integrantes)
//	public boolean guardarCobroGrupoExistente()
//	public TableModel getTablaAlumnos(boolean reinscripción, boolean todos, String busqueda)
//	public TableModel getTablaSeleccionados()
//	public String getEmail()
//	public String getNombre()
//	public String getFactura()
//	public String getHoraActual()
//	public String getCuerpoEmail()
//	public String getDescripcion()
//	public String getNumeroRecibo()
//	public String getFechaActual(String formato)
//	public String [] getIdElementosSeleccionados()
//	public int getIdFamilia()
//	public int getSumaCuotas()
//	public int getCalculoMontoTotal()
//	public int getDescuentoGrupo()
//	public int getCantidadElementos()
//	public int getCantidadElementosSeleccionados()
//	public void setIdFamilia(int id)
//	public void setEmail(String mail)
//	public void setBorrarSeleccionados()
//	public void setEnviarEmail(boolean enviar)
//	public void setNombre(String nombreFamilia)
//	public void setFactura(String numeroFactura)
//	public void setAlumnosSeleccionados(boolean seleccionados[])
//	public String setInscripcion(String inscrip)
//	public String setDescuentoGrupo(String descuento)
//	public String setDescuentoContado(String desContado)
//	public boolean guardarCobroGrupo()
//	public boolean isReinscripcion()
//	public String validarInformación()
//	public void setElementoSeleccionado(int elemento)
/*****************************************************************************************************************************************************************/
package modelo;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import dao.AdministracionDAO;
import dao.AlumnosDAO;
import dao.GrupoFamiliarDAO;

public class DtosCobros {
	
	private static String matrizSelec[][] = null;
	private static String email;
	private static String nombre;
	private static String descripcion;
	private static String factura;
	private static boolean enviarEmail;
	private static boolean reinscripción;
	private static int idFamilia;
	private static int cantElementosSel;
	private static int descuentoGrupo;
	private static int descuentoContado;
	private static int montoTotal;
	private static int sumaCuotas;
	private static int cantidadCuotas;
	private static int integrantes;
	private String tablaRespuesta[][];
	private int recargoMora;
	private int inscripcion;
	private int nroCobro;
	private int elementoSeleccionado;
	private int cantidadCuotasSeleccionadas = 1;
	
	public int getMontoTotal() {
		
		return montoTotal;
	}
	
	public boolean guardarCobroCuota() {

		GrupoFamiliarDAO grupoFamiliarDAO = new GrupoFamiliarDAO();

		if(!grupoFamiliarDAO.setActualizarDeuda(idFamilia, -cantidadCuotasSeleccionadas))
			return false;
		
		AdministracionDAO administracionDAO = new AdministracionDAO();
		
		if(!administracionDAO.setCobro())
			return false;
		
		nroCobro = administracionDAO.getUltimoRegistro();
		return true;
	}
	
	public String getCalculoCobro(String concepto) {
		
		int descuentoDeGrupo = 0;
		montoTotal = sumaCuotas * cantidadCuotasSeleccionadas;
		descripcion = "Cuota correspondiente a " + concepto + ": " + montoTotal;
		descuentoDeGrupo = montoTotal * descuentoGrupo / 100;
		montoTotal -= descuentoDeGrupo; 
		
		if(descuentoGrupo > 0)
			descripcion += ", descuento grupo familiar: " + descuentoDeGrupo;

		if(recargoMora > 0)
			descripcion += ", recargo por pago fuera de término: " + recargoMora;		
		
		if(descuentoContado > 0)
			descripcion += ", descuento pago contado: " + descuentoContado;

		montoTotal -= descuentoContado + recargoMora;
		descripcion += ", suma total: " + montoTotal;
		return montoTotal + "";
	}
	
	public void setCantidadCuotasSeleccionadas(int cantidad) {
		
		cantidadCuotasSeleccionadas = cantidad;
	}

	public String setRecargoMora(String recargo) {
		
		String mensaje = null;
		
		try {
			
			if(recargo.length() > 0)
				recargoMora = Integer.parseInt(recargo);
		} catch (Exception e) {
			
			mensaje = "El valor debe ser numérico.";
		}
		return mensaje;
	}
	
	public String [] getListadoConceptos() {
	
		Calendar fechaSistema = new GregorianCalendar();
		int mesActual = fechaSistema.get(Calendar.MONTH);
		String meses[] = new String[] {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
		String listaCuotasDeuda[] = new String[cantidadCuotas == 0? 2 : cantidadCuotas + 1];
		listaCuotasDeuda[0] = "Seleccione uno";

		if(cantidadCuotas > 0) {

			if(mesActual + 1 >= cantidadCuotas) {

				listaCuotasDeuda[1] = meses[mesActual-cantidadCuotas+1];
				
				for(int aux = 2; aux < listaCuotasDeuda.length; aux++) {
					
					for(int i = 0; i < aux; i++) {
					
						if(i == 0)
							listaCuotasDeuda[aux] = meses[mesActual - cantidadCuotas+1];
						else
							listaCuotasDeuda[aux] += ", " + meses[mesActual + 1 + i - cantidadCuotas];
					}
				}
			} else {
			
				for(int aux = 1; aux < listaCuotasDeuda.length; aux++) {
					
					String temp = (aux == 1)? " mes.":" meses."; 	
					listaCuotasDeuda[aux] =aux + temp;
				}
			}
		} else {
			
			listaCuotasDeuda[1] = meses[mesActual == 11? 0 : mesActual + 1];	
		}
		return listaCuotasDeuda;
	}

	public int getIntegrantes() {
		
		return integrantes;
	}
	
	public void setInfoCobro() {

		idFamilia = Integer.parseInt(tablaRespuesta[elementoSeleccionado][0]);
		nombre = tablaRespuesta[elementoSeleccionado][1];
		cantidadCuotas = Integer.parseInt(tablaRespuesta[elementoSeleccionado][3]);
		sumaCuotas = Integer.parseInt(tablaRespuesta[elementoSeleccionado][4]);
		descuentoGrupo = Integer.parseInt(tablaRespuesta[elementoSeleccionado][5]);		
		GrupoFamiliarDAO grupoFamiliarDAO = new GrupoFamiliarDAO();
		matrizSelec = grupoFamiliarDAO.getIntegrantes(idFamilia + "");	
		integrantes = matrizSelec.length;
		email = matrizSelec[0][7];
	}

	public TableModel getTablaDeudores(String busqueda, boolean pagoAdelantado) {
		
		GrupoFamiliarDAO grupoFamiliarDAO = new GrupoFamiliarDAO();
		String titulo[] = new String[] {"Nombre", "Integrantes",  "Cuotas" ,"Valor cuota", "Desc.", "Total" , "Sel"};
		tablaRespuesta = grupoFamiliarDAO.getGruposFamilias("", "", pagoAdelantado, busqueda);
		Object cuerpo[][] = new Object[tablaRespuesta.length][8];
		
		for(int i = 0; i < cuerpo.length; i ++) {
			
			int calculo = Integer.parseInt(tablaRespuesta[i][3]) * Integer.parseInt(tablaRespuesta[i][4]);
			calculo -= calculo * Integer.parseInt(tablaRespuesta[i][5]) /100;			
			cuerpo[i][0] = tablaRespuesta[i][1];
			cuerpo[i][1] = tablaRespuesta[i][2];
			cuerpo[i][2] = tablaRespuesta[i][3];
			cuerpo[i][3] = tablaRespuesta[i][4];
			cuerpo[i][4] = tablaRespuesta[i][5];
			cuerpo[i][5] = calculo;			
			cuerpo[i][6] = false;
		}
		DefaultTableModel tablaModelo;
		tablaModelo = new DefaultTableModel(cuerpo, titulo){

			private static final long serialVersionUID = 1L;
			
			boolean[] columnEditables = new boolean[] {
				false, false, false,false, false, false, true
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
	
	public void SetIntegrantes(String integrantes) {
		
		DtosCobros.integrantes = Integer.parseInt(integrantes);
	}
	
	public boolean guardarCobroGrupoExistente() {
		
		GrupoFamiliarDAO grupoFamiliarDAO = new GrupoFamiliarDAO();

		if(!grupoFamiliarDAO.setActualizarGrupo(idFamilia, nombre, cantElementosSel + integrantes, descuentoGrupo, email, "1"))
			return false;
		
		String idAlumnos[] = new String[matrizSelec.length];
		AlumnosDAO alumnosDAO = new AlumnosDAO();
		
		for(int i = 0; i < matrizSelec.length; i++) {
			
			idAlumnos[i] = matrizSelec[i][0];
		}

		if(!alumnosDAO.setActualizarIdFamila(idFamilia+"", idAlumnos, "1"))
			return false;
		
		AdministracionDAO administracionDAO = new AdministracionDAO();
		
		if(!administracionDAO.setCobro())
			return false;

		nroCobro = administracionDAO.getUltimoRegistro();
		return true;
	}
	
	public TableModel getTablaAlumnos(boolean reinscripción, boolean todos, String busqueda) {
		
		String titulo[] = null;
		DtosCobros.reinscripción = reinscripción;
	
		if(reinscripción) {
			
			titulo = new String[] {"Id", "Nombre", "Cant. Integrantes",  "Email" , "Sel"};
			GrupoFamiliarDAO grupoFamiliarDAO = new GrupoFamiliarDAO();
			tablaRespuesta = grupoFamiliarDAO.getGruposFamilias("ESTADO", "0", true, busqueda);
		} else {
		
			titulo = new String[] {"Leg.","Apellido","Nombre", "Dirección", "Sel"};
			AlumnosDAO alumnosDAO = new AlumnosDAO();
			tablaRespuesta = alumnosDAO.getAlumnos("GF", "", false, "apellido", busqueda);
		}
		Object cuerpo[][] = new Object[tablaRespuesta.length][5];
		
		for(int i = 0 ; i < tablaRespuesta.length ; i++) {

			cuerpo[i][0] = (reinscripción)?tablaRespuesta[i][0]:tablaRespuesta[i][0];
			cuerpo[i][1] = (reinscripción)?tablaRespuesta[i][1]:tablaRespuesta[i][2];
			cuerpo[i][2] = (reinscripción)?tablaRespuesta[i][2]:tablaRespuesta[i][1];
			cuerpo[i][3] = (reinscripción)?tablaRespuesta[i][6]:tablaRespuesta[i][4];
			cuerpo[i][4] = todos;

		}
		DefaultTableModel tablaModelo;
		tablaModelo = new DefaultTableModel(cuerpo, titulo){

			private static final long serialVersionUID = 1L;
			
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, true
			};
			
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
			
			public Class<?> getColumnClass(int column) {
				
		        if(column == 4)
		        	return Boolean.class;
		        else
		        	return String.class;
		    }
		};
		return tablaModelo;
	}
	
	public TableModel getTablaSeleccionados() {
		
		String titulo[] = {"Leg.", "Apellido", "Nombre", "Drirección", "Curso", "Valor cuota"};
		DefaultTableModel tabla = new DefaultTableModel(matrizSelec,titulo);
		return tabla;
	}
	
	public String getEmail() {
		
		return email;
	}
	
	public String getNombre() {
		
		return nombre;
	}
	
	public String getFactura() {
		
		return factura;
	}
	
	public String getHoraActual() {
		
		Calendar fechaSistema = new GregorianCalendar();
	    String hora = (fechaSistema.get(Calendar.AM_PM)==0? fechaSistema.get(Calendar.HOUR):fechaSistema.get(Calendar.HOUR)+12) + ":" 
					+ (fechaSistema.get(Calendar.MINUTE)<10? "0" + fechaSistema.get(Calendar.MINUTE):fechaSistema.get(Calendar.MINUTE)) + ":" 
					+ (fechaSistema.get(Calendar.SECOND)<10? "0" + fechaSistema.get(Calendar.SECOND):fechaSistema.get(Calendar.SECOND));		
		return hora;
	}
	
	public String getCuerpoEmail() {
		
		String temp = "Por la presente se deja constancia del pago realizado el día " + getFechaActual("") 
					+ ", y el detalle del mismo:\n\n" + descripcion.replaceAll(", ", "\n");
		temp += "\nTotal: " + montoTotal;
		return temp;
	}
	
	public String getDescripcion() {
		
		return descripcion;
	}
	
	public String getNumeroRecibo() {
		
		String numero = String.format("%012d" , nroCobro);
		StringBuilder sb = new StringBuilder(numero);
		sb.insert(4, '-');
		return sb.toString();
	}
	
	public String getFechaActual(String formato) {
		
		Calendar fechaSistema = new GregorianCalendar();
		String fecha = null;
		if(formato.equals("A")) {
		
			fecha = fechaSistema.get(Calendar.YEAR) + "/" 
				  + (fechaSistema.get(Calendar.MONTH)+1) + "/" 
				  + fechaSistema.get(Calendar.DAY_OF_MONTH);
		} else {
			
			fecha = fechaSistema.get(Calendar.DAY_OF_MONTH) + "/"
				  + (fechaSistema.get(Calendar.MONTH)+1) + "/"
				  + fechaSistema.get(Calendar.YEAR);
		}
		return fecha;
	}
	
	public String [] getIdElementosSeleccionados() {
		
		String id[] = new String[cantElementosSel];
		
		for(int i = 0 ; i < cantElementosSel ; i++) {
			
			id[i] = matrizSelec[i][0];
		}
		return id;
	}
	
	public int getIdFamilia() {
		
		return idFamilia;
	}
	
	public int getSumaCuotas() {

		return sumaCuotas;
	}
	
	public int getCalculoMontoTotal() {

		int descuentoDeGrupo = 0;
		descripcion = "Inscripción: " + inscripcion + ", primer cuota: " + sumaCuotas;
		
		if(descuentoContado > 0)
			descripcion += ", descuento pago contado: " + descuentoContado;
		
		montoTotal = sumaCuotas + inscripcion;
		descuentoDeGrupo = montoTotal * descuentoGrupo / 100;
		montoTotal -= descuentoDeGrupo; 
		
		if(descuentoGrupo > 0)
			descripcion += ", descuento grupo familiar: " + descuentoDeGrupo;
		
		montoTotal -= descuentoContado;
		return montoTotal;
	}
	
	public int getDescuentoGrupo() {
		
		return descuentoGrupo;
	}
	
	public int getCantidadElementos() {
		
		return tablaRespuesta.length;
	}
	
	public int getCantidadElementosSeleccionados() {
		
		return cantElementosSel;
	}
	
	public void setIdFamilia(int id) {
		
		idFamilia = id;
	}
	
	public void setEmail(String mail) {
		
		email = mail;
	}
	
	public void setBorrarSeleccionados() {

		email = "";
		nombre = "";
		descripcion = "";
		factura = "";
		idFamilia = 0;
		cantElementosSel = 0;
		descuentoGrupo = 0;
		descuentoContado = 0;
		montoTotal = 0;
		sumaCuotas = 0;
		inscripcion = 0;		
		matrizSelec = null;
	}
	
	public void setEnviarEmail(boolean enviar) {
		
		enviarEmail = enviar;
	}
	
	public void setNombre(String nombreFamilia) {
		
		nombre =nombreFamilia;
	}
	
	public void setFactura(String numeroFactura) {
		
		factura = numeroFactura;
	}
	
	public void setAlumnosSeleccionados(boolean seleccionados[]) {
		
		cantElementosSel = 0;
		
		for(int i = 0 ; i < seleccionados.length ; i++) {
			
			if(seleccionados[i])
				cantElementosSel++;
		}

		if(reinscripción) {

			idFamilia = 0;
			
			for(int i = 0 ; i < tablaRespuesta.length ; i++) {
				
				if(seleccionados[i]) {
				
					idFamilia = Integer.parseInt(tablaRespuesta[i][0]);
					break;
				}
			}
			
			if(idFamilia != 0) {
			
				GrupoFamiliarDAO grupoFamiliarDAO = new GrupoFamiliarDAO();
				matrizSelec = grupoFamiliarDAO.getIntegrantes(idFamilia + "");
				descuentoGrupo = Integer.parseInt(matrizSelec[0][6]);
				email = matrizSelec[0][7];
				sumaCuotas = 0;
				nombre = matrizSelec[0][8];
				cantElementosSel = matrizSelec.length;
				
				for(int i = 0 ; i < cantElementosSel ; i++) {
					
					sumaCuotas += Integer.parseInt(matrizSelec[i][5]);
				}
			}
		} else {
			
			matrizSelec = new String[cantElementosSel][6];
			int e = 0;
			sumaCuotas = 0;
			email = "";
	
			for(int i = 0 ; i < tablaRespuesta.length ; i++) {
					
				if(seleccionados[i]) {
				
					matrizSelec[e][0] = tablaRespuesta[i][0];
					matrizSelec[e][1] = tablaRespuesta[i][2];
					matrizSelec[e][2] = tablaRespuesta[i][1];
					matrizSelec[e][3] = tablaRespuesta[i][4];
					matrizSelec[e][4] = tablaRespuesta[i][7];
					matrizSelec[e][5] = tablaRespuesta[i][16];
					sumaCuotas += Integer.parseInt(matrizSelec[e][5]);
					e++;
					
					if(cantElementosSel == 1) {
						
						nombre = matrizSelec[0][1] + ", " + matrizSelec[0][2];
						email = tablaRespuesta[i][6];
					}
				}
			}
		}
	}

	public String setInscripcion(String inscrip) {

		String mensage = null;
		inscripcion = 0;
		
		if(inscrip.length() > 0) {
			
			try {
				
				inscripcion = Integer.parseInt(inscrip) * cantElementosSel;
			} catch (Exception e) {

				mensage = "El valor de la inscrioción debe ser numérico.";
			}
		} else
			mensage = "Inscripción debe tener valor.";
		return mensage;
	}
	
	public String setDescuentoGrupo(String descuento) {
		
		String mensage = null;
		descuentoGrupo = 0;
		
		if(descuento.length() > 0) {
			
			try {
				
				descuentoGrupo = Integer.parseInt(descuento);
			} catch (Exception e) {

				mensage = "El valor del descuento por grupo familiar debe ser numérico.";
			}
		}
		return mensage;
	}
	
	public String setDescuentoContado(String desContado) {

		String mensage = null;
		descuentoContado = 0;
		
		if(desContado.length() > 0) {
			
			try {
				
				descuentoContado = Integer.parseInt(desContado);
			} catch (Exception e) {

				mensage = "El valor del descuento por pago en efectivo debe ser numérico.";
			}
		}
		return mensage;
	}
 
	public boolean guardarCobroGrupo() {
		
		GrupoFamiliarDAO grupoFamiliarDAO = new GrupoFamiliarDAO();
		AdministracionDAO administracionDAO = new AdministracionDAO();
		
		if(reinscripción) {
			if(!grupoFamiliarDAO.setActualizarGrupo(idFamilia, nombre, cantElementosSel, descuentoGrupo, email, "1"))
				return false;
			
		}else {
		
			if(!grupoFamiliarDAO.setGrupoFamiliar())
				return false;
		}
		
		if(!administracionDAO.setCobro())
			return false;

		nroCobro = administracionDAO.getUltimoRegistro();
		return true;
	}

	public boolean isReinscripcion() {
		
		return reinscripción;
	}

	public String validarInformación(boolean validarNombre) {
		
		GrupoFamiliarDAO grupoFamiliaDAO = new GrupoFamiliarDAO();
	
		if(grupoFamiliaDAO.isNombreFamilia(nombre) && !reinscripción && validarNombre)
			return "El nombre de familia ya está en uso.";
		
		if(nombre.length() < 5)
			return "El nombre de familia es demasiado corto.";
		
		if(enviarEmail) {
			
			if(email.length() < 6)
				return "Debe llenar el campo email.";
			
			if(!email.contains("@") || email.contains(" "))
				return "Error en el formato del email.";
			
			String partes[] = email.split("@");
			
			try {
				
				if(partes[1].length() < 3 || !partes[1].contains("."))
					return "Error en el formato del email.";	
				
			} catch (Exception e) {
				
				return "Error en el formato del email.";	
			}
		}
		
		if(inscripcion < 1)
			return "La inscripción debe tener un valor.";
		
		return "";
	}

	public void setElementoSeleccionado(int elemento) {
		
		elementoSeleccionado = elemento;
	}
}