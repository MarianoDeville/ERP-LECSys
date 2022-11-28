/*****************************************************************************************************************************************************************/
//										LISTADO DE MÉTODOS
/*---------------------------------------------------------------------------------------------------------------------------------------------------------------*/
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
//	public int getMontoTotal()
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
	
	private String tablaRespuesta[][];
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
	private static int inscripcion;
	private static int nroCobro;
	
	public TableModel getTablaAlumnos(boolean reinscripción, boolean todos, String busqueda) {
		
		String titulo[] = null;
		DtosCobros.reinscripción = reinscripción;
		
		if(reinscripción) {
			
			titulo = new String[] {"Id", "Nombre", "Cant. Integrantes",  "Email" , "Sel"};
			GrupoFamiliarDAO grupoFamiliarDAO = new GrupoFamiliarDAO();
			tablaRespuesta = grupoFamiliarDAO.getGruposFamilias("ESTADO", "0", true, busqueda);
		} else {
		
			titulo = new String[] {"Legajo","Apellido","Nombre", "Dirección", "Sel"};
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
		
		String titulo[] = {"Legajo", "Apellido", "Nombre", "Drirección", "Curso", "Valor cuota"};
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
	
	public int getMontoTotal() {

		descripcion = "Inscripción: " + inscripcion + ", primer cuota: " + sumaCuotas;
		
		if(descuentoContado > 0)
			descripcion += ", descuento pago contado: " + descuentoContado;
		
		montoTotal = sumaCuotas + inscripcion;
		montoTotal -= montoTotal * descuentoGrupo /100; 
		
		if(descuentoGrupo > 0)
			descripcion += ", descuento grupo familiar: " + (montoTotal * descuentoGrupo /100);
		
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
			if(!grupoFamiliarDAO.setActualizarGrupo(idFamilia, nombre, cantElementosSel, descuentoGrupo, email))
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

	public String validarInformación() {
		
		GrupoFamiliarDAO grupoFamiliaDAO = new GrupoFamiliarDAO();
		
		if(grupoFamiliaDAO.isNombreFamilia(nombre) && !reinscripción)
			return "El nombre de familia ya está en uso.";
		
		if(nombre.length() < 5)
			return "El nombre de familia es demasiado corto.";
		
		if(enviarEmail) {
			
			if(email.length() < 3)
				return "Debe llenar el campo email.";
			
			if(!email.contains("@") || !email.contains(".") || email.contains(" "))
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
}