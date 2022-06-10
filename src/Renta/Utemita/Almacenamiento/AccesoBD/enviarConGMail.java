package Renta.Utemita.Almacenamiento.AccesoBD;

//import java.net.Authenticator;
//import java.net.PasswordAuthentication;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 *
 * @author Marcos
 */
public class enviarConGMail {
        /**
	   Outgoing Mail (SMTP) Server
	   requires TLS or SSL: smtp.gmail.com (use authentication)
	   Use Authentication: Yes
	   Port for TLS/STARTTLS: 587
        
        * @param args
        */
   public static void main(String[] args) {
		final String fromEmail = "yogui201098@gmail.com"; //requires valid gmail id
		final String password = "7064170641"; // correct password for gmail id
		final String toEmail = "sasj981020@gs.utm.mx"; // can be any email id 
		
		System.out.println("TLSEmail Start");
		/*se define las propiedades del envio, como el server,autenticacion y transporte*/
                Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.port", "587"); //TLS Port
		props.put("mail.smtp.auth", "true"); //enable authentication
                props.setProperty("mail.user", "myuser");
		props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
		
                //create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
                        @Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
                /*se establece la sesión con el servidor del correo*/
		Session session = Session.getInstance(props, auth);
		
		EmailUtil.sendEmail(session, toEmail,"TLSEmail Testing Subject", "TLSEmail Testing Body");
		
    }
}
