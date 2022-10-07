package modelo;

import javax.swing.JOptionPane;

import dao.OperadorSistema;

public class DtosAcceso {
	
	public boolean chkAcceso(String modulo, String accion) {
		
		boolean bandera = false;
		OperadorSistema identificacion = new OperadorSistema();
		String modulos[] = new String[] {"Administrativo","Alumnos","Personal","Cursos","Configuracion","Usuarios"};
//		String acciones[] = new String[] {"Entrar","Editar","Crear","ABML","Listado","Asistencia","Horarios"};			// Lo comenté hasta que lo use para que no me moleste.
		
		if(identificacion.getNivelAcceso() == 0) {
			
			return true;
		}
		
		if(accion.contentEquals("Entrar")) {
			
			for(int i = 0 ; i < modulos.length ; i++) {
				
				if(identificacion.getNivelAcceso() < 50) {
					
					return true;
				}
			}
		}
		JOptionPane.showMessageDialog(null, "No posee permisos para realizar esta operación.");
		return bandera;
	}
}
