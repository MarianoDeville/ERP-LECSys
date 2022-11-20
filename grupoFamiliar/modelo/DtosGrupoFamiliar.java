package modelo;

import javax.swing.table.DefaultTableModel;

public class DtosGrupoFamiliar {

	
	public DefaultTableModel getTablaGrupoFamiliar(boolean estado, String busqueda) {
		
		String titulo[] = {"Nombre", "Integrantes"};
		
		DefaultTableModel tablaModelo = new DefaultTableModel(null, titulo);
		
		return tablaModelo;
	}
}
