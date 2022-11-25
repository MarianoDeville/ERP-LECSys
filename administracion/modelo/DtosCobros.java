package modelo;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dao.AdministracionDAO;
import dao.AlumnosDAO;
import dao.GrupoFamiliarDAO;

public class DtosCobros {
	
	private String tablaRespuesta[][];
	private static String seleccionados[][] = null;
	private static String email;
	private static String nombre;
	private static String descripcion;
	private static String factura;
	private boolean enviarEmail;
	private static int idFamilia;
	private static int cantElementosSel;
	private static int descuentoGrupo;
	private static int descuentoContado;
	private static int montoTotal;
	private static int sumaCuotas;
	private static int inscripcion;
		
	public void setFactura(String numeroFactura) {
		
		factura = numeroFactura;
	}
	
	public String getFactura() {
		
		return factura;
	}
	
	public String getDescripcion() {
		
		return descripcion;
	}
	
	public int getIdFamilia() {
		
		return idFamilia;
	}
	
	public void setIdFamilia(int id) {
		
		idFamilia = id;
	}
	
	public void setEnviarEmail(boolean enviar) {
		
		enviarEmail = enviar;
	}
	
	public String validarInformación() {
		
		GrupoFamiliarDAO grupoFamiliaDAO = new GrupoFamiliarDAO();
		
		if(grupoFamiliaDAO.isNombreFamilia(nombre))
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
	
	public String [] getIdElementosSeleccionados() {
		
		String id[] = new String[cantElementosSel];
		
		for(int i = 0 ; i < cantElementosSel ; i++) {
			
			id[i] = seleccionados[i][0];
		}
		return id;
	}
	
	public boolean guardarCobroGrupo() {
		
		GrupoFamiliarDAO grupoFamiliarDAO = new GrupoFamiliarDAO();
		AdministracionDAO administracionDAO = new AdministracionDAO();
		
		if(!grupoFamiliarDAO.setGrupoFamiliar())
			return false;

		if(!administracionDAO.setCobro())
			return false;

		return true;
	}
	
	public int getDescuentoGrupo() {
		
		return descuentoGrupo;
	}
	public int getCantidadElementosSeleccionados() {
		
		return cantElementosSel;
	}
	
	public void setEmail(String mail) {
		
		email = mail;
	}
	
	public String getEmail() {
		
		return email;
	}
	
	public String getNombre() {
		
		return nombre;
	}
	
	public void setNombre(String nombreFamilia) {
		
		nombre =nombreFamilia;
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
	
	public TableModel getTablaSeleccionados() {
		
		String titulo[] = {"Legajo", "Apellido", "Nombre", "Drirección", "Curso", "Valor cuota"};
		DefaultTableModel tabla = new DefaultTableModel(seleccionados,titulo);
		return tabla;
	}
	
	public void setAlumnosSeleccionados(String cuerpo[][]) {
		
		seleccionados = cuerpo;
		cantElementosSel = seleccionados.length;
		sumaCuotas = 0;
		
		for(int i = 0 ; i < cantElementosSel ; i++) {
			
			sumaCuotas += Integer.parseInt(seleccionados[i][5]);
		}
		if(cantElementosSel == 1)
			nombre = seleccionados[0][1] + ", " + seleccionados[0][2];
		
		for(int i = 0 ; i < tablaRespuesta.length ; i++) {
		
			if(seleccionados[0][0].equals(tablaRespuesta[i][0])) {
				
				email = tablaRespuesta[i][6];
				break;
			}
		}
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
		seleccionados = null;
	}
	
	public int getCantidadElementos() {
		
		return tablaRespuesta.length;
	}
	
	public String getElementoMatriz(int fila, int columna) {
		
		return tablaRespuesta[fila][columna];
	}
	
	public TableModel getTablaAlumnos(boolean reinscripción, boolean todos, String busqueda) {
		
		String titulo[] = null;
		
		if(reinscripción) {
			
			titulo = new String[] {"Nombre", "Integrantes",  "Descuento" , "Sel"};
			GrupoFamiliarDAO grupoFamiliarDAO = new GrupoFamiliarDAO();
			tablaRespuesta = grupoFamiliarDAO.getGruposFamilias(false, busqueda);
		} else {
		
			titulo = new String[] {"Legajo","Apellido","Nombre", "Dirección", "Sel"};
			AlumnosDAO alumnosDAO = new AlumnosDAO();
			tablaRespuesta = alumnosDAO.getAlumnos("GF", "", false, "apellido", busqueda);
		}
		Object cuerpo[][] = new Object[tablaRespuesta.length][5];
		
		for(int i = 0 ; i < tablaRespuesta.length ; i++) {

			cuerpo[i][0] = (reinscripción)?tablaRespuesta[i][1]:tablaRespuesta[i][0];
			cuerpo[i][1] = (reinscripción)?tablaRespuesta[i][3]:tablaRespuesta[i][2];
			cuerpo[i][2] = (reinscripción)?tablaRespuesta[i][5]:tablaRespuesta[i][1];
			cuerpo[i][3] = (reinscripción)?todos:tablaRespuesta[i][4];
			
			if(!reinscripción) {
				
				cuerpo[i][4] = todos;
			}
		}
		DefaultTableModel tablaModelo;
		
		if(reinscripción) {
			
			tablaModelo = new DefaultTableModel(cuerpo, titulo){
	
				private static final long serialVersionUID = 1L;
				
				boolean[] columnEditables = new boolean[] {
					false, false, false, true
				};
				
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
				
				public Class<?> getColumnClass(int column) {
					
			        if(column == 3)
			        	return Boolean.class;
			        else
			        	return String.class;
			    }
			};
		} else {
			
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
		} 	
		return tablaModelo;
	}
	
	
}