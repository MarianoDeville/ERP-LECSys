package modelo;

import javax.swing.table.DefaultTableModel;
import dao.EmpleadosDAO;

public class DtosABMLEmpleados {

	
	public String [] getFiltro() {
		
		return new String [] {"Todos","Docente","Administrativo","Recepcionista","Personal limpieza"};
	}
	
	public DefaultTableModel getTablaEmpleados(String tipo, boolean estado, String filtro) {
		
		EmpleadosDAO empleados = new EmpleadosDAO();
		String titulo[] = {"Legajo", "Nombre", "Apellido", "DNI", "Dirección", "Teléfono", "E-mail", "Sector", "Cargo", "Tipo,", "Sel."};
		String respuesta[][]=empleados.getEmpleados(tipo, estado, filtro);
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
}
