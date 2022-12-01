import controlador.CtrlArgumentos;
import controlador.CtrlLogin;
import interfaceUsuario.IngresoUsuario;
import modelo.DtosConfiguracion;

/********************************************************************************/
/*		Sistema ERP de gesti�n offline para academias - LECSys					*/
/*------------------------------------------------------------------------------*/
/*		Revisi�n:				1.00											*/
/*		IDE:					Eclipse IDE Ver. 2022-09 (4.25.0)				*/
/*		Lenguaje:				Java SE-17										*/
/*		Versionado:				git - GitHub.com/MarianoDeville/ERP-LECSys		*/
/*		Base de Datos:			MySQL Workbench 8.00 CE							*/
/*		Plugin:					WindowBuilder 1.9.8								*/
/*								UMLet 14.3										*/
/*		Estado:					Desarrollo.										*/
/*		Fecha creaci�n:			15/09/2022										*/
/*		�ltima modificaci�n:	01/12/2022										*/
/********************************************************************************/

public class LECSys {

	public static void main(String[] args) {

		CtrlArgumentos ctrlArgumentos = new CtrlArgumentos();
		
		if(ctrlArgumentos.procesar(args))
			return;
		
		DtosConfiguracion config = new DtosConfiguracion();
		config.getConfig();
		IngresoUsuario interfaceUsuario = new IngresoUsuario();
		CtrlLogin ctrlIngreso = new CtrlLogin(interfaceUsuario);
		ctrlIngreso.iniciar();
	}
}