package modelo;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import dao.AlumnosDAO;
import dao.GrupoFamiliarDAO;

public class DtosCobros {
	
	private String tablaRespuesta[][];
	private static String seleccionados[][] = null;
	
	
	
	public TableModel getTablaSeleccionados() {
		
		String titulo[] = {"Legajo", "Apellido", "Nombre", "Drirecci�n", "Curso", "Valor cuota"};
		DefaultTableModel tabla = new DefaultTableModel(seleccionados,titulo);
		return tabla;
	}
	
	public void setAlumnosSeleccionados(String cuerpo[][]) {
		
		seleccionados = cuerpo;
	}
	
	public void setBorrarSeleccionados() {
		
		seleccionados = null;
	}
	
	public int getCantidadElementos() {
		
		return tablaRespuesta.length;
	}
	
	public String getElementoMatriz(int fila, int columna) {
		
		return tablaRespuesta[fila][columna];
	}
	
	public TableModel getTablaAlumnos(boolean reinscripci�n, boolean todos, String busqueda) {
		
		String titulo[] = null;
		
		if(reinscripci�n) {
			
			titulo = new String[] {"Nombre", "Integrantes",  "Descuento" , "Sel"};
			GrupoFamiliarDAO grupoFamiliarDAO = new GrupoFamiliarDAO();
			tablaRespuesta = grupoFamiliarDAO.getGruposFamilias(false, busqueda);
		} else {
		
			titulo = new String[] {"Legajo","Apellido","Nombre", "Direcci�n", "Sel"};
			AlumnosDAO alumnosDAO = new AlumnosDAO();
			tablaRespuesta = alumnosDAO.getAlumnos("GF", "", false, "apellido", busqueda);
		}
				
		Object cuerpo[][] = new Object[tablaRespuesta.length][5];
		
		for(int i = 0 ; i < tablaRespuesta.length ; i++) {

			cuerpo[i][0] = (reinscripci�n)?tablaRespuesta[i][1]:tablaRespuesta[i][0];
			cuerpo[i][1] = (reinscripci�n)?tablaRespuesta[i][3]:tablaRespuesta[i][2];
			cuerpo[i][2] = (reinscripci�n)?tablaRespuesta[i][5]:tablaRespuesta[i][1];
			cuerpo[i][3] = (reinscripci�n)?todos:tablaRespuesta[i][4];
			
			if(!reinscripci�n) {
				
				cuerpo[i][4] = todos;
			}
		}
		DefaultTableModel tablaModelo;
		
		if(reinscripci�n) {
			
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