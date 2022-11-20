package modelo;

import javax.swing.JOptionPane;

import dao.OperadorSistema;

public class DtosAcceso {
	
	public boolean chkAcceso(String modulo, String accion) {
		
		OperadorSistema identificacion = new OperadorSistema();
		String modulos[] = new String[] {"Administrativo","Alumnos","Personal","Cursos","Configuracion","Usuarios"};
//		String acciones[] = new String[] {"Entrar","Editar","Crear","ABML","Listado","Tomar asistencia",
//										+ "Registro asistencia","Horarios","Listar actividad","Cambiar contraseña","Cobrar y habilitar"};		// Lo comenté hasta que lo use para que no me moleste.
		
		if(identificacion.getNivelAcceso() == 0) {
			
			return true;
		}
		
	//	if(accion.equals("Entrar")) {
			
			for(int i = 0 ; i < modulos.length ; i++) {
				
				if(identificacion.getNivelAcceso() < 90) {
					
					return true;
				}
			}
//		}
		JOptionPane.showMessageDialog(null, "No posee permisos para realizar esta operación.");
		return false;
	}
}
