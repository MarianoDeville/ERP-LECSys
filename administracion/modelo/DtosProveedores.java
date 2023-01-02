package modelo;

import javax.swing.table.DefaultTableModel;
import dao.ProveedoresDAO;

public class DtosProveedores {

	
	private String tabla[][];
	private static String idProveedor;
	
	
	public DefaultTableModel getTablaContactos() {
		
		String titulo[] = new String[] {"Nombre del contacto", "Teléfono", "E-mail"};
		
		
		String  matriz[][] = null;
		
		ProveedoresDAO proveedoresDAO = new ProveedoresDAO();
		
		
		DefaultTableModel respuesta = new DefaultTableModel(matriz,titulo){

			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		return respuesta;
		
		
	}
	
	public DefaultTableModel getTablaProveedores(String filtro) {
		
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
	
	public String[] getListaCondiciones() {
		
		return new String[] {"Resp. Inscripto", "Monotributista", "Resp. No Inscripto", "Exento"};
	}
	
	
	
}
