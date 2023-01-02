package modelo;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dao.ProveedoresDAO;

public class DtosProveedores {

	private static String idProveedor;
	private String tabla[][];
	private String nombre;
	private String dirección;
	private String cuit;
	private String situaciónFiscal;
	private String mensageError;
	private int cantidadContactos = 0;
	
	private boolean isInfoCorrecta(JTable contactos) {

		if(nombre.length() < 4) {
			
			mensageError = "El nombre o razón social debe ser más largo.";
			return false;
		} else if(dirección.length() < 6) {
			
			mensageError = "La dirección ingresada no es válida.";
			return false;
		} else if(!isNumérico(cuit) || cuit.length() != 11) {

			mensageError = "El CUIT debe ser numérico, sin guiones o espacios y contener 11 dígitos.";
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
	
	public boolean setGuardar(JTable contactos) {
		
		ProveedoresDAO proveedoresDAO = new ProveedoresDAO();
		
		if(!isInfoCorrecta(contactos))
			return false;

		if(proveedoresDAO.setProveedorNuevo(nombre, dirección, cuit, situaciónFiscal, contactos)) {
			
			mensageError = "La información se guardó correctamente.";
			return true;
		} 
		mensageError = "Error al intentar guardar la información.";
		return false;
	}
	
	public void setNuevoContacto(String operación) {
		
		if(operación.equals("+")) {
			
			cantidadContactos++;	
		} else if(operación.equals("-") && cantidadContactos > 0) {
			
			cantidadContactos--;
		}
	}
	
	public DefaultTableModel getTablaContactos(JTable contactos) {
		
		String titulo[] = new String[] {"Nombre del contacto", "Sector", "Teléfono", "E-mail"};
		String  matriz[][] = new String [cantidadContactos][4];
		int tamaño = (contactos.getRowCount() < matriz.length)?contactos.getRowCount():matriz.length; 
		
		if(contactos.getRowCount() > 0) {
			
			for(int i = 0; i < tamaño; i++) {
				
				matriz[i][0] = (String)contactos.getValueAt(i, 0);
				matriz[i][1] = (String)contactos.getValueAt(i, 1);
				matriz[i][2] = (String)contactos.getValueAt(i, 2);
				matriz[i][3] = (String)contactos.getValueAt(i, 3);
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
	
	public DefaultTableModel getTablaProveedores(String filtro) {
		
		limpioVariables();
		String titulo[] = new String[] {"Razón social", "CUIT", "Dirección", "Teléfonos", "E-mail", "Sel."};
		Object matriz[][] = null;
		ProveedoresDAO proveedoresDAO = new ProveedoresDAO();
		tabla = proveedoresDAO.getListadoProveedores(filtro);
		
		if(tabla != null) {
			
			matriz = new Object[tabla.length][6];
		
			for(int i = 0; i < tabla.length;i++) {
				
				matriz[i][0] = tabla[i][0];
				matriz[i][1] = tabla[i][1];
				matriz[i][2] = tabla[i][2];
				matriz[i][3] = tabla[i][3];
				matriz[i][4] = tabla[i][4];
				matriz[i][5] = false;
			}
		}
		DefaultTableModel respuesta = new DefaultTableModel(matriz,titulo){

			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, true
			};
			
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
			
			public Class<?> getColumnClass(int column) {
				
		        if(column == 5)
		        	return Boolean.class;
		        else
		        	return String.class;
		    }
		};
		return respuesta;
	}
	
	private void limpioVariables() {
		
		tabla = null;
		nombre = "";
		dirección = "";
		cuit = "";
		situaciónFiscal = "";
		mensageError = "";
	}
	public String[] getListaCondiciones() {
		
		return new String[] {"Resp. Inscripto", "Monotributista", "Resp. No Inscripto", "Exento"};
	}

	public void setNombre(String nombre) {
		
		this.nombre = nombre;
	}

	public void setDirección(String dirección) {
		
		this.dirección = dirección;
	}

	public void setSituaciónFiscal(String situaciónFiscal) {
		
		this.situaciónFiscal = situaciónFiscal;
	}

	public void setCuit(String cuit) {
		
		this.cuit = cuit;
	}

	public String getMensageError() {
		
		return mensageError;
	}

	public void setIdProveedor(String idProveedor) {
		
		DtosProveedores.idProveedor = idProveedor;
	}
	
	private boolean isNumérico(String valor) {
		
		try {
			
			Long.parseLong(valor);
			return true;
		} catch (Exception e) {

			return false;
		}
	}
}
