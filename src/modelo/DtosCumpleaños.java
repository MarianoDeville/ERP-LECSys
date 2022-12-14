package modelo;

import javax.swing.table.DefaultTableModel;
import dao.PersonasDAO;

public class DtosCumpleaņos {
	
	private static boolean bandera = false;
	
	public DefaultTableModel getTablaCumpleaņos() {
		
		PersonasDAO personasDAO = new PersonasDAO();
		String titulo[] = {"Nombre", "Apellido"};
		String respuesta[][] = personasDAO.getListadoCumpleAņos();
		
		if(respuesta != null) 
			bandera = true;

		DefaultTableModel tablaModelo = new DefaultTableModel(respuesta, titulo);
		return tablaModelo;
	}

	public boolean isBandera() {
		
		return bandera;
	}
}
