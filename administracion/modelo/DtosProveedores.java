/******************************************************************************************************************************************/
//										LISTADO DE MÉTODOS
/*----------------------------------------------------------------------------------------------------------------------------------------*/
//	public DefaultTableModel getTablaProveedores(String filtro, boolean estado)
//	public DefaultTableModel getTablaContactos(JTable contactos, int elemento)
//	public String[] getListaCondiciones()
//	public String getSituaciónFiscal()
//	public String getMensageError()
//	public String getDirección()
//	public String getNombre()
//	public String getCuit()
//	public boolean setActualizar(JTable contactos)
//	public boolean setGuardar(JTable contactos)
//	public boolean isEstado()
//	public void setSituaciónFiscal(String situaciónFiscal)
//	public void setIdProveedor(int pos, boolean est)
//	public void setNuevoContacto(String operación)
//	public void setDirección(String dirección)
//	public void setEstado(boolean estado)
//	public void setNombre(String nombre)
//	public void setCuit(String cuit)
//	private void limpioVariables()
//	private boolean isNumérico(String valor)
//	private boolean isInfoCorrecta(JTable contactos, boolean nuevo)
/******************************************************************************************************************************************/

package modelo;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dao.ProveedoresDAO;

public class DtosProveedores {

	private ProveedoresDAO proveedoresDAO= new ProveedoresDAO();
	private static String idProveedor;
	private String tabla[][];
	private static String razónSocial;
	private static String dirección;
	private static String cuit;
	private static String situaciónFiscal;
	private static boolean estado;
	private static String tablaContactos[][];
	private String mensageError;
	private int cantidadContactos = 0;
	private boolean actualizar = true;
	
	public DefaultTableModel getTablaProveedores(String filtro, boolean estado) {
		
		limpioVariables();
		String titulo[] = new String[] {"Razón social", "CUIT", "Dirección", "Teléfonos", "E-mail"};
		String matriz[][] = null;
		tabla = proveedoresDAO.getListadoProveedores(filtro, estado);
		
		if(tabla != null) {
			
			matriz = new String[tabla.length][6];
		
			for(int i = 0; i < tabla.length;i++) {
				
				matriz[i][0] = tabla[i][0];
				matriz[i][1] = tabla[i][1];
				matriz[i][2] = tabla[i][2];
				matriz[i][3] = tabla[i][5];
				matriz[i][4] = tabla[i][6];
			}
		}
		DefaultTableModel respuesta = new DefaultTableModel(matriz,titulo){

			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable(int row, int column) {
				return false;
				
			}
		};
		return respuesta;
	}
	
	public DefaultTableModel getTablaContactos(JTable contactos, int elemento) {
		
		String titulo[] = new String[] {"Nombre del contacto", "Sector", "Teléfono", "E-mail"};
		String  matriz[][];
		
		if(actualizar && tablaContactos != null) {
			
			cantidadContactos = tablaContactos.length;
			matriz = tablaContactos;
			actualizar = false;
		} else {
		
			matriz = new String [cantidadContactos][4];
		}
		
		if(contactos.getRowCount() > 0) {
			
			int e = 0;
			for(int i = 0; i < contactos.getRowCount(); i++) {
				
				if(i != elemento) {
					
					matriz[e][0] = (String)contactos.getValueAt(i, 0);
					matriz[e][1] = (String)contactos.getValueAt(i, 1);
					matriz[e][2] = (String)contactos.getValueAt(i, 2);
					matriz[e][3] = (String)contactos.getValueAt(i, 3);
					e++;
				}
			}
		}
		DefaultTableModel respuesta = new DefaultTableModel(matriz,titulo){

			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return true;
			}
		};
		return respuesta;
	}
	
	public String[] getListaCondiciones() {
		
		return new String[] {"Resp. Inscripto", "Monotributista", "Resp. No Inscripto", "Exento"};
	}
	
	public String getSituaciónFiscal() {
		
		return situaciónFiscal;
	}

	public String getMensageError() {
		
		return mensageError;
	}

	public String getDirección() {
		
		return dirección;
	}

	public String getNombre() {
		
		return razónSocial;
	}
	
	public String getCuit() {
		
		return cuit;
	}
	
	public boolean setActualizar(JTable contactos) {
		
		if(!isInfoCorrecta(contactos, false))
			return false;

		if(proveedoresDAO.setActualizarProveedor(idProveedor,razónSocial, dirección, cuit, situaciónFiscal, estado, contactos)) {
			
			mensageError = "La información se guardó correctamente.";
			return true;
		} 
		mensageError = "Error al intentar guardar la información.";
		return false;
	}
	
	public boolean setGuardar(JTable contactos) {
		
		if(!isInfoCorrecta(contactos, true))
			return false;

		if(proveedoresDAO.setProveedorNuevo(razónSocial, dirección, cuit, situaciónFiscal, contactos)) {
			
			mensageError = "La información se guardó correctamente.";
			return true;
		} 
		mensageError = "Error al intentar guardar la información.";
		return false;
	}
	
	public boolean isEstado() {
		
		return estado;
	}
	
	public void setSituaciónFiscal(String situaciónFiscal) {
		
		DtosProveedores.situaciónFiscal = situaciónFiscal;
	}
	
	public void setIdProveedor(int pos, boolean est) {

		razónSocial = tabla[pos][0];
		cuit = tabla[pos][1];
		dirección = tabla[pos][2];
		situaciónFiscal = tabla[pos][3];
		idProveedor = tabla[pos][4];		
		tablaContactos = proveedoresDAO.getListadoContactos(idProveedor);
		estado = est;
	}
	
	public void setNuevoContacto(String operación) {
		
		if(operación.equals("+")) {
			
			cantidadContactos++;	
		} else if(operación.equals("-") && cantidadContactos > 0) {
			
			cantidadContactos--;
			return;
		}
		
		if(cantidadContactos == 0) {
			
			mensageError = "No existen elementos para borrar.";
			return;
		}
		mensageError = "";
	}
	
	public void setDirección(String dirección) {
		
		DtosProveedores.dirección = dirección;
	}
	
	public void setEstado(boolean estado) {
		
		DtosProveedores.estado = estado;
	}

	public void setNombre(String nombre) {
		
		DtosProveedores.razónSocial = nombre;
	}
	
	public void setCuit(String cuit) {
		
		DtosProveedores.cuit = cuit;
	}
	
	private void limpioVariables() {
		
		tablaContactos = null;
		tabla = null;
		razónSocial = "";
		dirección = "";
		cuit = "";
		situaciónFiscal = "";
		mensageError = "";
	}
	
	private boolean isNumérico(String valor) {
		
		try {
			
			Long.parseLong(valor);
			return true;
		} catch (Exception e) {

			return false;
		}
	}

	private boolean isInfoCorrecta(JTable contactos, boolean nuevo) {

		if(razónSocial.length() < 4) {
			
			mensageError = "El nombre o razón social debe ser más largo.";
			return false;
		} else if(dirección.length() < 6) {
			
			mensageError = "La dirección ingresada no es válida.";
			return false;
		} else if(!isNumérico(cuit) || cuit.length() != 11) {

			mensageError = "El CUIT debe ser numérico, sin guiones o espacios y contener 11 dígitos.";
			return false;
		}else if(proveedoresDAO.isCUITExistente(cuit) && nuevo) {

			mensageError = "El número de cuit ya se encuentra cargado en la base de datos.";
			return false;
		}
		
		if(contactos.getRowCount() > 0) {
			
			for(int i = 0; i < contactos.getRowCount(); i++) {
				
				if(contactos.getValueAt(i, 0) == null)
					contactos.setValueAt("", i, 0);

				if(contactos.getValueAt(i, 1) == null)
					contactos.setValueAt("", i, 1);
				
				if(contactos.getValueAt(i, 2) == null)
					contactos.setValueAt("", i, 2);

				if(contactos.getValueAt(i, 3) == null)
					contactos.setValueAt("", i, 3);
				
				if(contactos.getValueAt(i, 0).toString().length() < 3) {
					
					mensageError = "Nombre de contacto no válido.";
					return false;
				}	

				if(contactos.getValueAt(i, 1).toString().length() < 3) {
					
					mensageError = "Sector no válido.";
					return false;
				}	

				if(contactos.getValueAt(i, 2).toString().length() < 3 && 
					contactos.getValueAt(i, 3).toString().length() < 3) {
					
					mensageError = "Debe cargar por lo menos uno de los métodos de contacto.";
					return false;
				}	
			}
		}
		return true;
	}
}
