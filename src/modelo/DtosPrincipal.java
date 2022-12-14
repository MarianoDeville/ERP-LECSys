package modelo;

import dao.EstadisticasDAO;
import dao.GrupoFamiliarDAO;

public class DtosPrincipal {
	
	public void inicializar() {

		
		EstadisticasDAO estadisticasDAO = new EstadisticasDAO();
		GrupoFamiliarDAO grupoFamiliarDAO = new GrupoFamiliarDAO();
		if(estadisticasDAO.isNuevoMes())
			grupoFamiliarDAO.setActualizarDeuda(0, 1);
	}

}
