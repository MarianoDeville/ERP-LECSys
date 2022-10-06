package principal;

import controlador.CtrlLogin;
import interfaceUsuario.IngresoUsuario;

/********************************************************************************/
/*		Sistema ERP de gesti�n offline para academias - LECSys					*/
/*------------------------------------------------------------------------------*/
/*		Revisi�n:				2.00											*/
/*		IDE:					Eclipse IDE Ver. 2022-09 (4.25.0)				*/
/*		Lenguaje:				Java SE-17										*/
/*		Versionado:				git - GitHub.com/MarianoDeville					*/
/*		Base de Datos:			MySQL Workbench 8.00 CE							*/
/*		Plugin:					WindowBuilder 1.9.8								*/
/*								UMLet 14.3										*/
/*		Estado:					Desarrollo.										*/
/*		Fecha creaci�n:			15/09/2022										*/
/*		�ltima modificaci�n:	06/10/2022										*/
/********************************************************************************/

public class LECSys {

	public static void main(String[] args) {

		IngresoUsuario interfaceUsuario = new IngresoUsuario();
		CtrlLogin ctrlIngreso = new CtrlLogin(interfaceUsuario);
		ctrlIngreso.iniciar();
	}
}
