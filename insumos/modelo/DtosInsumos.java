package modelo;

import javax.swing.table.DefaultTableModel;
import dao.InsumosDAO;

public class DtosInsumos {

	private InsumosDAO insumosDAO;
	private String nombre;
	private String descripci�n;
	private String formato;
	private String msgError;
	
	public boolean setGuardarNuevo() {
		
		boolean bandera;
		insumosDAO = new InsumosDAO();
		bandera = insumosDAO.setInsumo(nombre, descripci�n, formato);
		ResetearDatos();
		return bandera;
	}
	private void ResetearDatos() {
		
		nombre = "";
		descripci�n = "";
		formato = "";
		msgError = "";
	}
	public boolean isCheckInfo() {
		
		msgError = "";
		if(nombre.length() < 5) {
			
			msgError = "El nombre no puede faltar ni ser demasiado corto.";
			return false;
		} else if(descripci�n.length() < 5) {
			
			msgError = "La descripci�n no puede faltar ni ser demasiado corto.";
			return false;
		} else if(formato.length() < 3) {
			
			msgError = "Por favor indique en qu� formato viene el producto.";
			return false;
		}
		return true;
	}
	
	public DefaultTableModel getTablaInsumos(String filtro, int elemento) {
		
		insumosDAO = new InsumosDAO();
		String titulo[] = new String[] {"ID", "Nombre", "Descripci�n", "Presentac�on", "�ltima compra"};
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

	public void setDescripci�n(String descripci�n) {
		
		this.descripci�n = descripci�n;
	}

	public void setPresentaci�n(String presentaci�n) {
		
		this.formato = presentaci�n;
	}

	public String getMsgError() {
		
		return msgError;
	}
}
