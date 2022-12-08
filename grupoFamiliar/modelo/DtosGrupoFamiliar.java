package modelo;

import javax.swing.table.DefaultTableModel;
import dao.AlumnosDAO;
import dao.GrupoFamiliarDAO;

public class DtosGrupoFamiliar {

	private static String listaIntegrantes[][];
	private static String listaAlumnos[][];
	private String eliminarElementos[];
	private String agregarElementos[][];
	private String listaAcciones[];
	private static String nombreFamilia;
	private static String idGrupoFamiliar;
	private static String integrantes;
	private static String estado;
	private static String email;
	private static String descuento;
	private int elementoSeleccionado;

	public boolean guardarCambios() {
		
		boolean bandera = false;
		
	// listo los cambios///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		if(eliminarElementos != null) {
		for(int i = 0; i<eliminarElementos.length;i++) {
			
			System.out.println("id "+eliminarElementos[i] + " idFamilia " + idGrupoFamiliar);
		}
		}
		if(agregarElementos != null) {
		for(int i = 0; i<agregarElementos.length;i++) {
			
			System.out.println("id " +agregarElementos[i][0] + " idFamilia " + idGrupoFamiliar + " viene de la flia " + agregarElementos[i][4]);
		}
		}
		
		return bandera;
		
	}
	
	public boolean isRepetido(Object idAlumno) {
	
		for(int i = 0; i < agregarElementos.length; i++) {
			
			if(agregarElementos[i][0].equals(idAlumno))
				return true;
		}
		return false;
	}
	
	public void setAgregarElementos() {

		String temp[][] = agregarElementos;
		String temp2[] = listaAcciones;
		listaAcciones = new String[temp2.length + 1];
		System.arraycopy(temp2, 0, listaAcciones, 0, temp2.length);
		listaAcciones[temp2.length] = "A";
		agregarElementos = new String [temp.length + 1][listaIntegrantes[0].length];
		System.arraycopy(temp, 0, agregarElementos, 0, temp.length);
		agregarElementos[temp.length][0] = listaAlumnos[elementoSeleccionado][0];
		agregarElementos[temp.length][1] = "";
		agregarElementos[temp.length][2] = listaAlumnos[elementoSeleccionado][1];
		agregarElementos[temp.length][7] = listaAlumnos[elementoSeleccionado][3];
	}
	
	public void setEliminarElementos() {

		if(eliminarElementos == null) {

			eliminarElementos = new String[] {listaIntegrantes[elementoSeleccionado][0]};
		} else if(eliminarElementos.length > 0) {

			for(int i = 0; i < eliminarElementos.length; i++) {
			
				if(eliminarElementos[i].equals(listaIntegrantes[elementoSeleccionado][0]))
					return;
			}
			String temp[] = new String[eliminarElementos.length];
			System.arraycopy(eliminarElementos, 0, temp, 0, eliminarElementos.length);
			eliminarElementos = new String[temp.length + 1];
			System.arraycopy(temp, 0, eliminarElementos, 0, temp.length);
			eliminarElementos[eliminarElementos.length-1] = listaIntegrantes[elementoSeleccionado][0];
		}
		listaAcciones[elementoSeleccionado] = "E";
	}
	
	public DefaultTableModel getTablaAlumnos(String valor, boolean estado) {
		
		AlumnosDAO alumnosDAO = new AlumnosDAO();
		String titulo[] = {"Leg.", "Apellido, nombre", "Direcci�n", "Curso", "Sel."};
		listaAlumnos = alumnosDAO.getListadoAlumnos( estado, idGrupoFamiliar, valor);
		Object cuerpo[][] = null;

		if(listaAlumnos != null) {
			
			cuerpo = new Object[listaAlumnos.length][5];
			
			for(int i = 0 ; i < listaAlumnos.length ; i++) {
				
				cuerpo[i][0] = listaAlumnos[i][0];
				cuerpo[i][1] = listaAlumnos[i][1];
				cuerpo[i][2] = listaAlumnos[i][2];
				cuerpo[i][3] = listaAlumnos[i][3];
				cuerpo[i][4] = false;
			}
		} else
			cuerpo = null;
		
		DefaultTableModel tablaModelo = new DefaultTableModel(cuerpo, titulo){

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
		return tablaModelo;
	}
	
	public DefaultTableModel getTablaFamilia() {

		AlumnosDAO alumnosDAO = new AlumnosDAO();
		String titulo[] = {"Leg.", "Apellido, nombre", "Curso", "Sel.", ""};
		listaIntegrantes = alumnosDAO.getAlumnos("GF", idGrupoFamiliar, true, "idAlumno", "");
		/////////////////////////////////////////////////////////////////////////////////////////// si no est� inicializado lo inicializo con el tama�o que debo.	
		if(agregarElementos == null) {

			agregarElementos = new String[0][0];
		}

		String temp[][];
		
		if(agregarElementos.length > 0) {
		
			temp = new String[listaIntegrantes.length + agregarElementos.length][listaIntegrantes[0].length];
			System.arraycopy(listaIntegrantes, 0, temp, 0, listaIntegrantes.length);
			System.arraycopy(agregarElementos, 0, temp, listaIntegrantes.length, agregarElementos.length);
		} else {
			
			temp = listaIntegrantes;
		}

		if(listaAcciones == null) {
			
			listaAcciones = new String[temp.length];
			
			for(int i = 0; i < listaAcciones.length; i++) {
				
				listaAcciones[i] = "";
			}
		}

		Object cuerpo[][] = new Object[temp.length][5];
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		for(int i = 0; i < cuerpo.length ; i++) {
	
			cuerpo[i][0] = temp[i][0];
			cuerpo[i][1] = temp[i][2] + ", " + temp[i][1];
			cuerpo[i][2] = temp[i][7];
			cuerpo[i][3] = false;
			cuerpo[i][4] = listaAcciones[i];
		}
	
		DefaultTableModel tablaModelo = new DefaultTableModel(cuerpo, titulo){

			private static final long serialVersionUID = 1L;
			
			boolean[] columnEditables = new boolean[] {
					false, false, false, true, false
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
		return tablaModelo;
	}
	
	public void setInformacionGrupo() {
		
		idGrupoFamiliar = listaIntegrantes[elementoSeleccionado][0];
		nombreFamilia = listaIntegrantes[elementoSeleccionado][1];
		integrantes = listaIntegrantes[elementoSeleccionado][2];
		email = listaIntegrantes[elementoSeleccionado][6];
		descuento = listaIntegrantes[elementoSeleccionado][5];
	}
	
	public DefaultTableModel getTablaGrupoFamiliar(boolean estado, String busqueda) {
		
		DtosGrupoFamiliar.estado = estado?"1":"0";
		GrupoFamiliarDAO grupoFamiliarDAO = new GrupoFamiliarDAO();
		String titulo[] = {"Nombre", "Integrantes", "Sel."};
		listaIntegrantes = grupoFamiliarDAO.getGruposFamilias("", "", estado, busqueda);
		Object cuerpo[][] = new Object[listaIntegrantes.length][3];
		
		for(int i = 0 ; i < listaIntegrantes.length ; i++) {
			
			cuerpo[i][0] = listaIntegrantes[i][1];
			cuerpo[i][1] = "";
			cuerpo[i][2] = false;
			String integrantes[][] = grupoFamiliarDAO.getIntegrantes(listaIntegrantes[i][0]);
			
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

	public void setElementoSeleccionado(int elemento) {
		
		elementoSeleccionado = elemento;
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
