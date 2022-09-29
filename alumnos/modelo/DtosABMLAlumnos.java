package modelo;

import javax.swing.table.DefaultTableModel;

import dao.AlumnosDAO;

public class DtosABMLAlumnos {
	
	public String [] getOrdenamiento() {
		
		return new String [] {"Legajo", "Nombre", "Apellido", "DNI", "Dirección", "Curso"};
	}
	
	public DefaultTableModel getTablaAlumnos(String campo, String valor, boolean estado, int pos) {
		
		AlumnosDAO alumnos = new AlumnosDAO();
		String titulo[] = {"Legajo", "Nombre", "Apellido", "DNI", "Dirección", "Teléfono", "E-mail", "Curso", "Sel."};
		String ordenado[] = {"idAlumno", "nombre", "apellido", "dni", "dirección", "alumnos.idCurso"};
		String respuesta[][]=alumnos.getAlumnos(campo, valor, estado, ordenado[pos]);
		Object cuerpo[][]=null;
		
		if(respuesta != null) {
			
			cuerpo = new Object[respuesta.length][9];
			
			for(int i = 0 ; i < respuesta.length ; i++) {
				
				cuerpo[i][0] = respuesta[i][0];
				cuerpo[i][1] = respuesta[i][1];
				cuerpo[i][2] = respuesta[i][2];
				cuerpo[i][3] = respuesta[i][3];
				cuerpo[i][4] = respuesta[i][4];
				cuerpo[i][5] = respuesta[i][5];
				cuerpo[i][6] = respuesta[i][6];
				cuerpo[i][7] = respuesta[i][7];
				cuerpo[i][8] = false;
			}
		} else
			cuerpo = null;
		
		DefaultTableModel tablaModelo = new DefaultTableModel(cuerpo, titulo){

			private static final long serialVersionUID = 1L;
			
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, false, true
			};
			
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
			
			public Class<?> getColumnClass(int column) {
				
		        if(column == 8)
		        	return Boolean.class;
		        else
		        	return String.class;
		    }
		};
		return tablaModelo;
	}
}
