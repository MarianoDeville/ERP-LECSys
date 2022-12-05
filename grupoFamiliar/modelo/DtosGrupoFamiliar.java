package modelo;

import javax.swing.table.DefaultTableModel;
import dao.GrupoFamiliarDAO;

public class DtosGrupoFamiliar {

	private static String respuesta[][];
	private static String nombreFamilia;
	private static String idGrupoFamiliar;
	private static String integrantes;
	private static String estado;
	private static String email;
	private static String descuento;
	private static int elementoSeleccionado;
	
	public void setInformacionGrupo() {
		
		idGrupoFamiliar = respuesta[elementoSeleccionado][0];
		nombreFamilia = respuesta[elementoSeleccionado][1];
		integrantes = respuesta[elementoSeleccionado][2];
		email = respuesta[elementoSeleccionado][6];
		descuento = respuesta[elementoSeleccionado][5];
	}
	
	public DefaultTableModel getTablaGrupoFamiliar(boolean estado, String busqueda) {
		
		DtosGrupoFamiliar.estado = estado?"1":"0";
		GrupoFamiliarDAO grupoFamiliarDAO = new GrupoFamiliarDAO();
		String titulo[] = {"Nombre", "Integrantes", "Sel."};
		respuesta = grupoFamiliarDAO.getGruposFamilias("", "", estado, busqueda);
		Object cuerpo[][] = new Object[respuesta.length][3];
		
		for(int i = 0 ; i < respuesta.length ; i++) {
			
			cuerpo[i][0] = respuesta[i][1];
			cuerpo[i][1] = "";
			cuerpo[i][2] = false;
			String integrantes[][] = grupoFamiliarDAO.getIntegrantes(respuesta[i][0]);
			
			for(int e = 0; e < integrantes.length ; e++) {
			
				cuerpo[i][1] += integrantes[e][1] + " " + integrantes[e][2];
				
				if(e < integrantes.length - 1) {
					
					cuerpo[i][1] += ", ";
				}
			}
		}
		DefaultTableModel tablaModelo = new DefaultTableModel(cuerpo, titulo){

			private static final long serialVersionUID = 1L;
			
			boolean[] columnEditables = new boolean[] {
					false, false, true
			};
			
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
			
			public Class<?> getColumnClass(int column) {
				
		        if(column == 2)
		        	return Boolean.class;
		        else
		        	return String.class;
		    }
		};
		
		return tablaModelo;
	}

	public void setElementoSeleccionado(int elementoSeleccionado) {
		
		DtosGrupoFamiliar.elementoSeleccionado = elementoSeleccionado;
	}

	public String getIdGrupoFamiliar() {
		
		return idGrupoFamiliar;
	}

	public String getNombreFamilia() {
		
		return nombreFamilia;
	}

	public String getIntegrantes() {
		
		return integrantes;
	}

	public String getEstado() {
		
		return estado;
	}

	public String getDescuento() {
		
		return descuento;
	}

	public String getEmail() {
		
		return email;
	}
}
