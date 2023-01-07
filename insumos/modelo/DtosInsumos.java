package modelo;

import javax.swing.table.DefaultTableModel;
import dao.InsumosDAO;

public class DtosInsumos {

	private InsumosDAO insumosDAO;
	private String nombre;
	private String descripción;
	private String formato;
	private String msgError;
	
	public boolean setGuardarNuevo() {
		
		boolean bandera;
		insumosDAO = new InsumosDAO();
		bandera = insumosDAO.setInsumo(nombre, descripción, formato);
		ResetearDatos();
		return bandera;
	}
	private void ResetearDatos() {
		
		nombre = "";
		descripción = "";
		formato = "";
		msgError = "";
	}
	public boolean isCheckInfo() {
		
		msgError = "";
		if(nombre.length() < 5) {
			
			msgError = "El nombre no puede faltar ni ser demasiado corto.";
			return false;
		} else if(descripción.length() < 5) {
			
			msgError = "La descripción no puede faltar ni ser demasiado corto.";
			return false;
		} else if(formato.length() < 3) {
			
			msgError = "Por favor indique en qué formato viene el producto.";
			return false;
		}
		return true;
	}
	
	public DefaultTableModel getTablaInsumos(String filtro, int elemento) {
		
		insumosDAO = new InsumosDAO();
		String titulo[] = new String[] {"ID", "Nombre", "Descripción", "Presentacíon", "Última compra"};
		String matriz[][] = insumosDAO.getListadoInsumos(filtro);
		
		DefaultTableModel respuesta = new DefaultTableModel(matriz,titulo){

			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable(int row, int column) {
				return false;
				
			}
		};
		return respuesta;
	}

	public void setNombre(String nombre) {
		
		this.nombre = nombre;
	}

	public void setDescripción(String descripción) {
		
		this.descripción = descripción;
	}

	public void setPresentación(String presentación) {
		
		this.formato = presentación;
	}

	public String getMsgError() {
		
		return msgError;
	}
}
