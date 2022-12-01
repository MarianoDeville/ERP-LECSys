package modelo;

import javax.swing.table.DefaultTableModel;
import dao.PersonasDAO;

public class DtosCumplea�os {
	
	private static boolean bandera = false;
	
	public DefaultTableModel getTablaCumplea�os() {
		
		PersonasDAO personasDAO = new PersonasDAO();
		String titulo[] = {"Nombre", "Apellido"};
		String respuesta[][] = personasDAO.getListadoCumpleA�os();
		
		if(respuesta != null) 
			bandera = true;

		DefaultTableModel tablaModelo = new DefaultTableModel(respuesta, titulo);
		return tablaModelo;
	}

	public boolean isBandera() {
		
		return bandera;
	}
}
