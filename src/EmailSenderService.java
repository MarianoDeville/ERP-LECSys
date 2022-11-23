import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSenderService {

	public void mandarcorreo(String destinatario) {
		
		Properties propiedad = new Properties();
		propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
		propiedad.setProperty("mail.smtp.port", "587");
		propiedad.setProperty("mail.smtp.auth","true");
		propiedad.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
		propiedad.setProperty("mail.smtp.starttls.enable", "true");
		
		Session sesion = Session.getDefaultInstance(propiedad);
		String correoEnvia = "mariano.deville@gmail.com";
		String contrasena = "lkxokocyudnmfkyk";
		String receptor = "mariano_deville@hotmail.com";
		String asunto = "Primer email";
		String mensaje = "Email enviado desde java.";
		MimeMessage mail = new MimeMessage(sesion);
		
		try {
		
			mail.setFrom(new InternetAddress (correoEnvia));
			mail.addRecipient(Message.RecipientType.TO, new InternetAddress (receptor));
			mail.setSubject(asunto);
			mail.setText(mensaje);
			Transport transportar = sesion.getTransport("smtp");
			transportar.connect(correoEnvia,contrasena);
			transportar.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));          
			transportar.close();
System.out.println("Listo, revise su correo");
		} catch (AddressException ex) {
		
			ex.printStackTrace();
		} catch (MessagingException ex) {
		
			ex.printStackTrace();
		}
	}
}
