package modelo;

import java.net.InetAddress;
import java.util.Calendar;
import java.util.GregorianCalendar;
import dao.ActividadDAO;
import dao.OperadorSistema;

public class DtosActividad {
	
	private Calendar fechaSistema;
	
	public void registrarActividad(String accion, String modulo) {
		
		ActividadDAO actividadDAO = new ActividadDAO();
		OperadorSistema identificacion = new OperadorSistema();
		String miIP = null;
		String fecha = null;
		String hora = null;
		try {
			
			miIP = InetAddress.getLocalHost().getHostAddress();
			fechaSistema = new GregorianCalendar();
			fecha = fechaSistema.get(Calendar.YEAR) + "/" 
				  + (fechaSistema.get(Calendar.MONTH)+1) + "/" 
				  + fechaSistema.get(Calendar.DAY_OF_MONTH);
			hora = (fechaSistema.get(Calendar.AM_PM)==0? fechaSistema.get(Calendar.HOUR):fechaSistema.get(Calendar.HOUR)+12) 
				 + ":" +fechaSistema.get(Calendar.MINUTE)
				 + ":" +fechaSistema.get(Calendar.SECOND);
			
		
		} catch (Exception e) {
			
			System.err.println(e.getMessage());
		} 
		actividadDAO.setActividad(identificacion.getFichaEmpleado(), fecha, hora, accion, modulo, miIP);
	}
	
	public String [] getTituloTabla() {
		
		return new String [] {"Id:","Usuario:","Fecha:","Hora:","Acci�n:","M�dulo:","IP:"};
	}
	
	public String [][] getActividad(String mes, String a�o) {
		
		ActividadDAO actividadDAO = new ActividadDAO();
		if(mes.contentEquals("") || a�o.contentEquals("")) {
			
			fechaSistema = new GregorianCalendar();
			a�o = fechaSistema.get(Calendar.YEAR) + "";
			mes = fechaSistema.get(Calendar.MONTH) + 1 + "";
		}
		
		return actividadDAO.getActividad(mes, a�o);
	}
	
	public int getMesActual() {
		
		fechaSistema = new GregorianCalendar();
		return fechaSistema.get(Calendar.MONTH);
	}
	
	public String [] getMeses() {
		
		String meses[] = new String[] {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
		return meses;
	}
	
	public String [] getA�os() {
		
		fechaSistema = new GregorianCalendar();
		String respuesta[] = new String[5];
		
		for(int i = 4 ; i >= 0 ; i--) {
			
			respuesta[i] = fechaSistema.get(Calendar.YEAR) - i +""; 
		}
		return respuesta;
	}
}
