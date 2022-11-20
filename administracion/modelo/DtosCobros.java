package modelo;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class DtosCobros {
	
	private String tablaAlumnos[][];
	
	public TableModel getTablaAlumnos(boolean reinscripción, boolean todos, String busqueda) {
		
		String titulo[] = {"Legajo","Nombre","Apellido",""};
		
		
		DefaultTableModel tablaModelo = new DefaultTableModel(null, titulo);
		
		
		
		return tablaModelo;
	}

}
