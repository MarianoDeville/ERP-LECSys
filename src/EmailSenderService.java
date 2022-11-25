import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSenderService {

	public void mandarcorreo(String destinatario, String asunto) {
		
		Properties propiedad = new Properties();
		propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
		propiedad.setProperty("mail.smtp.port", "587");
		propiedad.setProperty("mail.smtp.auth","true");
		propiedad.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
		propiedad.setProperty("mail.smtp.starttls.enable", "true");
		Session sesion = Session.getDefaultInstance(propiedad);
		String correoEnvia = "mariano.deville@gmail.com";
		String contrasena = "lkxokocyudnmfkyk";
		String mensaje = "Email enviado desde java.";
		MimeMessage mail = new MimeMessage(sesion);
		
		try {
		
			mail.setFrom(new InternetAddress (correoEnvia));
			mail.addRecipient(Message.RecipientType.TO, new InternetAddress (destinatario));
			mail.setSubject(asunto);
			mail.setText(mensaje);
			Transport transporte = sesion.getTransport("smtp");
			transporte.connect(correoEnvia,contrasena);
			transporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));          
			transporte.close();
System.out.println("Listo, revise su correo");
		} catch (Exception e) {
			
			System.out.println("Error al intentar enviar el email.");
			e.printStackTrace();
		}
	}
}
