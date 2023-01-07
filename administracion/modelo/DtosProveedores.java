/******************************************************************************************************************************************/
//										LISTADO DE M�TODOS
/*----------------------------------------------------------------------------------------------------------------------------------------*/
//	public DefaultTableModel getTablaProveedores(String filtro, boolean estado)
//	public DefaultTableModel getTablaContactos(JTable contactos, int elemento)
//	public String[] getListaCondiciones()
//	public String getSituaci�nFiscal()
//	public String getMensageError()
//	public String getDirecci�n()
//	public String getNombre()
//	public String getCuit()
//	public boolean setActualizar(JTable contactos)
//	public boolean setGuardar(JTable contactos)
//	public boolean isEstado()
//	public void setSituaci�nFiscal(String situaci�nFiscal)
//	public void setIdProveedor(int pos, boolean est)
//	public void setNuevoContacto(String operaci�n)
//	public void setDirecci�n(String direcci�n)
//	public void setEstado(boolean estado)
//	public void setNombre(String nombre)
//	public void setCuit(String cuit)
//	private void limpioVariables()
//	private boolean isNum�rico(String valor)
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
	private static String raz�nSocial;
	private static String direcci�n;
	private static String cuit;
	private static String situaci�nFiscal;
	private static boolean estado;
	private static String tablaContactos[][];
	private String mensageError;
	private int cantidadContactos = 0;
	private boolean actualizar = true;
	
	public DefaultTableModel getTablaProveedores(String filtro, boolean estado) {
		
		limpioVariables();
		String titulo[] = new String[] {"Raz�n social", "CUIT", "Direcci�n", "Tel�fonos", "E-mail"};
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
		
		String titulo[] = new String[] {"Nombre del contacto", "Sector", "Tel�fono", "E-mail"};
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
	
	public String getSituaci�nFiscal() {
		
		return situaci�nFiscal;
	}

	public String getMensageError() {
		
		return mensageError;
	}

	public String getDirecci�n() {
		
		return direcci�n;
	}

	public String getNombre() {
		
		return raz�nSocial;
	}
	
	public String getCuit() {
		
		return cuit;
	}
	
	public boolean setActualizar(JTable contactos) {
		
		if(!isInfoCorrecta(contactos, false))
			return false;

		if(proveedoresDAO.setActualizarProveedor(idProveedor,raz�nSocial, direcci�n, cuit, situaci�nFiscal, estado, contactos)) {
			
			mensageError = "La informaci�n se guard� correctamente.";
			return true;
		} 
		mensageError = "Error al intentar guardar la informaci�n.";
		return false;
	}
	
	public boolean setGuardar(JTable contactos) {
		
		if(!isInfoCorrecta(contactos, true))
			return false;

		if(proveedoresDAO.setProveedorNuevo(raz�nSocial, direcci�n, cuit, situaci�nFiscal, contactos)) {
			
			mensageError = "La informaci�n se guard� correctamente.";
			return true;
		} 
		mensageError = "Error al intentar guardar la informaci�n.";
		return false;
	}
	
	public boolean isEstado() {
		
		return estado;
	}
	
	public void setSituaci�nFiscal(String situaci�nFiscal) {
		
		DtosProveedores.situaci�nFiscal = situaci�nFiscal;
	}
	
	public void setIdProveedor(int pos, boolean est) {

		raz�nSocial = tabla[pos][0];
		cuit = tabla[pos][1];
		direcci�n = tabla[pos][2];
		situaci�nFiscal = tabla[pos][3];
		idProveedor = tabla[pos][4];		
		tablaContactos = proveedoresDAO.getListadoContactos(idProveedor);
		estado = est;
	}
	
	public void setNuevoContacto(String operaci�n) {
		
		if(operaci�n.equals("+")) {
			
			cantidadContactos++;	
		} else if(operaci�n.equals("-") && cantidadContactos > 0) {
			
			cantidadContactos--;
			return;
		}
		
		if(cantidadContactos == 0) {
			
			mensageError = "No existen elementos para borrar.";
			return;
		}
		mensageError = "";
	}
	
	public void setDirecci�n(String direcci�n) {
		
		DtosProveedores.direcci�n = direcci�n;
	}
	
	public void setEstado(boolean estado) {
		
		DtosProveedores.estado = estado;
	}

	public void setNombre(String nombre) {
		
		DtosProveedores.raz�nSocial = nombre;
	}
	
	public void setCuit(String cuit) {
		
		DtosProveedores.cuit = cuit;
	}
	
	private void limpioVariables() {
		
		tablaContactos = null;
		tabla = null;
		raz�nSocial = "";
		direcci�n = "";
		cuit = "";
		situaci�nFiscal = "";
		mensageError = "";
	}
	
	private boolean isNum�rico(String valor) {
		
		try {
			
			Long.parseLong(valor);
			return true;
		} catch (Exception e) {

			return false;
		}
	}

	private boolean isInfoCorrecta(JTable contactos, boolean nuevo) {

		if(raz�nSocial.length() < 4) {
			
			mensageError = "El nombre o raz�n social debe ser m�s largo.";
			return false;
		} else if(direcci�n.length() < 6) {
			
			mensageError = "La direcci�n ingresada no es v�lida.";
			return false;
		} else if(!isNum�rico(cuit) || cuit.length() != 11) {

			mensageError = "El CUIT debe ser num�rico, sin guiones o espacios y contener 11 d�gitos.";
			return false;
		}else if(proveedoresDAO.isCUITExistente(cuit) && nuevo) {

			mensageError = "El n�mero de cuit ya se encuentra cargado en la base de datos.";
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
					
					mensageError = "Nombre de contacto no v�lido.";
					return false;
				}	

				if(contactos.getValueAt(i, 1).toString().length() < 3) {
					
					mensageError = "Sector no v�lido.";
					return false;
				}	

				if(contactos.getValueAt(i, 2).toString().length() < 3 && 
					contactos.getValueAt(i, 3).toString().length() < 3) {
					
					mensageError = "Debe cargar por lo menos uno de los m�todos de contacto.";
					return false;
				}	
			}
		}
		return true;
	}
}
