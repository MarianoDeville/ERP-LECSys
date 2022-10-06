package principal;

import controlador.CtrlLogin;
import interfaceUsuario.IngresoUsuario;

/********************************************************************************/
/*		Sistema ERP de gestión offline para academias - LECSys					*/
/*------------------------------------------------------------------------------*/
/*		Revisión:				2.00											*/
/*		IDE:					Eclipse IDE Ver. 2022-09 (4.25.0)				*/
/*		Lenguaje:				Java SE-17										*/
/*		Versionado:				git - GitHub.com/MarianoDeville					*/
/*		Base de Datos:			MySQL Workbench 8.00 CE							*/
/*		Plugin:					WindowBuilder 1.9.8								*/
/*								UMLet 14.3										*/
/*		Estado:					Desarrollo.										*/
/*		Fecha creación:			15/09/2022										*/
/*		Última modificación:	06/10/2022										*/
/********************************************************************************/

public class LECSys {

	public static void main(String[] args) {

		IngresoUsuario interfaceUsuario = new IngresoUsuario();
		CtrlLogin ctrlIngreso = new CtrlLogin(interfaceUsuario);
		ctrlIngreso.iniciar();
	}
}
